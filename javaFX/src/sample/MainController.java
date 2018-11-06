package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.math.NoVaccineModel;
import sample.math.VaccineModel;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static final String BETTA_TEXTFIELD_COMMON_ID = "betta";

    public AnchorPane bettaAnchor;
    public AnchorPane vParamAnchor;
    public TextField regionPopulation;
    public TextField startIllNumber;

    public TableView<TableViewItem> noVaccTable;
    public TableView vaccTable;

    public LineChart noVaccChart;
    public LineChart vaccChart;

    private static double[] INTENSITY_INFLUENZA_TRANSMISSION = new double[12];
    private static double TOTAL_POPULATION;
    private static double START_SICK_NUMBER;
    private static double START_HEALTHY_NUMBER;

    private static double[] noVacS = {START_HEALTHY_NUMBER,0,0,0,0,0,0,0,0,0,0,0};
    private static double[] noVacI = {START_SICK_NUMBER,0,0,0,0,0,0,0,0,0,0,0};

    private static double[] vacS = {START_HEALTHY_NUMBER,0,0,0,0,0,0,0,0,0,0,0};
    private static double[] vacI = {START_SICK_NUMBER,0,0,0,0,0,0,0,0,0,0,0};
    private static double[] vacV = {0,0,0,0,0,0,0,0,0,0,0,0};
    private static double[] vacVParam = {0,0,0,0,0,0,0,0,0,0,0,0};
    private static double[] mb = {0,0,0,0,0,0,0,0,0,0,0,0};

    public static final String[] MONTHS = {
            "січень", "лютий", "березень",
            "квітень", "травень", "червень",
            "липень", "серпень", "вересень",
            "жовтень", "листопад", "грудень"
    };
    public TableView mbTable;
    public LineChart mbChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTextFormator();
    }

    public void calculateNoVacc(ActionEvent event) {
        if (noVaccTable.getItems() != null){
            noVaccTable.getItems().clear();
        }
        if (noVaccChart.getData() != null){
            noVaccChart.getData().clear();
        }

        readInitParams();

        calculateNoVacc();

        populateNoVacTable();
        populateNoVacChart();
    }

    public void calculateVacc(ActionEvent event) {

        calculateNoVacc(event);

        if (vaccTable.getItems() != null){
            vaccTable.getItems().clear();
        }
        if (vaccChart.getData() != null){
            vaccChart.getData().clear();
        }
        if (mbTable.getItems() != null){
            mbTable.getItems().clear();
        }
        if (mbChart.getData() != null){
            mbChart.getData().clear();
        }

        readInitParams();
        readVParam();

        calculateVacc();

        populateVacTable();
        populateVacChart();
        populateMBTable();
        populateMBChart();
    }

    private void readInitParams(){
        readBettas();
        readPopulationParam();
        readIllNumberParam();

        START_HEALTHY_NUMBER = TOTAL_POPULATION - START_SICK_NUMBER;
        noVacS[0] = START_HEALTHY_NUMBER;
        noVacI[0] = START_SICK_NUMBER;

        vacS[0] = START_HEALTHY_NUMBER;
        vacI[0] = START_SICK_NUMBER;
    }

    private void readBettas(){
        int iter = 0;

        for (Node child : bettaAnchor.getChildren()) {
            if (child.getId() != null && child.getId().contains(BETTA_TEXTFIELD_COMMON_ID)){
                INTENSITY_INFLUENZA_TRANSMISSION[iter++] = Double.valueOf(((TextField) child).getText());
            }
        }
    }

    private void readVParam(){
        int iter = 0;

        for (Node child : vParamAnchor.getChildren()) {
            if (child.getTypeSelector().toLowerCase().contains("text")){
                vacVParam[iter++] = Double.valueOf(((TextField) child).getText());
            }
        }
    }

    private void readPopulationParam(){
        TOTAL_POPULATION = Double.valueOf(regionPopulation.getText());
    }

    private void readIllNumberParam(){
        START_SICK_NUMBER = Double.valueOf(startIllNumber.getText());
    }

    @FXML
    private void calculateNoVacc(){
        for (int i = 1; i < 12; i++) {
            noVacS[i] = NoVaccineModel.calculateNextS(
                    noVacS[i-1],
                    noVacI[i-1],
                    INTENSITY_INFLUENZA_TRANSMISSION[i-1],
                    TOTAL_POPULATION
            );

            noVacI[i] = NoVaccineModel.calculateNextI(
                    noVacS[i-1],
                    noVacI[i-1],
                    INTENSITY_INFLUENZA_TRANSMISSION[i-1],
                    TOTAL_POPULATION
            );
        }
    }

    @FXML
    private  void calculateVacc(){
        for (int i = 1; i < 12; i++) {
            vacS[i] = VaccineModel.calculateNextS(
                    vacS[i-1],
                    vacI[i-1],
                    INTENSITY_INFLUENZA_TRANSMISSION[i-1],
                    TOTAL_POPULATION,
                    vacVParam[i-1]
            );

            vacI[i] = VaccineModel.calculateNextI(
                    vacS[i-1],
                    vacI[i-1],
                    INTENSITY_INFLUENZA_TRANSMISSION[i-1],
                    TOTAL_POPULATION
            );

            vacV[i] = VaccineModel.calculateNextV(
                    vacS[i-1],
                    vacV[i-1],
                    vacVParam[i-1]
            );
        }
        for (int i = 0; i < mb.length; i++) {
            mb[i] = Math.abs(noVacI[i] - vacI[i])/vacV[i] * TOTAL_POPULATION;
        }
    }

    private void setTextFormator(){
        for (Node child : bettaAnchor.getChildren()) {
            if (child.getId() != null && child.getId().contains(BETTA_TEXTFIELD_COMMON_ID)){
                ((TextField) child).textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        if (!newValue.matches("\\d{0,10}([\\.]\\d{0,15})?")) {
                            ((TextField) child).setText(oldValue);
                        }
                    }
                });
            }
        }
        for (Node child : vParamAnchor.getChildren()) {
            if (child.getTypeSelector().toLowerCase().contains("text")){
                ((TextField) child).textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        if (!newValue.matches("\\d{0,10}([\\.]\\d{0,15})?")) {
                            ((TextField) child).setText(oldValue);
                        }
                    }
                });
            }
        }
        regionPopulation.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,15}?")) {
                    regionPopulation.setText(oldValue);
                }
            }
        });
        startIllNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,10}([\\.]\\d{0,15})?")) {
                    startIllNumber.setText(oldValue);
                }
            }
        });
    }

    private void populateNoVacTable(){
        for (int i = 0; i < 12; i++) {
            noVaccTable.getItems().add(new TableViewItem().setMonth(MONTHS[i]).setHealthyNumber(noVacS[i]).setIllNumber(noVacI[i]));
        }
    }

    private void populateNoVacChart(){
        XYChart.Series<String, Double> illData = new XYChart.Series<>();
        //XYChart.Series<String, Double> healthyData = new XYChart.Series<>();
        illData.setName("Ill number");
        //healthyData.setName("Healthy number");

        for (int i = 0; i < 12; i++) {
            illData.getData().add(new XYChart.Data<>(MONTHS[i], noVacI[i]));
            //healthyData.getData().add(new XYChart.Data<>(MONTHS[i], noVacS[i]));
        }

        noVaccChart.getData().addAll(illData);
    }

    private void populateVacTable(){
        for (int i = 0; i < 12; i++) {
            vaccTable.getItems().add(new TableViewItem().
                    setMonth(MONTHS[i]).
                    setHealthyNumber(vacS[i]).
                    setIllNumber(vacI[i]).
                    setVaccNumber(vacV[i])
            );
        }
    }

    private void populateVacChart(){
        XYChart.Series<String, Double> illData = new XYChart.Series<>();
        //XYChart.Series<String, Double> healthyData = new XYChart.Series<>();
        //XYChart.Series<String, Double> vaccData = new XYChart.Series<>();
        illData.setName("Ill number");
        //healthyData.setName("Healthy number");
        //vaccData.setName("Vaccinated number");

        for (int i = 0; i < 12; i++) {
            illData.getData().add(new XYChart.Data<>(MONTHS[i], vacI[i]));
           // healthyData.getData().add(new XYChart.Data<>(MONTHS[i], vacS[i]));
           // vaccData.getData().add(new XYChart.Data<>(MONTHS[i], vacV[i]));
        }

        vaccChart.getData().addAll(illData);
    }

    private void populateMBChart() {
        XYChart.Series<String, Double> mbData = new XYChart.Series<>();
        mbData.setName("Marginal benefit");

        for (int i = 0; i < 12; i++) {
            mbData.getData().add(new XYChart.Data<>(MONTHS[i], mb[i]));
        }

        mbChart.getData().add(mbData);
    }

    private void populateMBTable() {
        for (int i = 0; i < 12; i++) {
            mbTable.getItems().add(new TableViewItem().
                    setMonth(MONTHS[i]).
                    setMb(mb[i])
            );
        }
    }
}
