package sample;

import javafx.event.ActionEvent;
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
    public static final String[] MONTHS = {
            "січень", "лютий", "березень",
            "квітень", "травень", "червень",
            "липень", "серпень", "вересень",
            "жовтень", "листопад", "грудень"
    };
    private static double[] intensityInfluenzaTransmission = new double[12];
    private static double[] vaccParam = new double[12];
    private static double totalPopulation;
    private static double startIllNumber;
    private static double startHealthNumber;
    private static double[] noVacS = {startHealthNumber, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static double[] noVacI = {startIllNumber, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static double[] vacS = {startHealthNumber, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static double[] vacI = {startIllNumber, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static double[] vacV = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static double mb = 0;
    public AnchorPane bettaAnchor;
    public AnchorPane vacAnchor;
    public TextField initPopulationSize;
    public TextField initIllSize;
    public LineChart noVaccChart;
    public LineChart vaccChart;
    public TableView table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTextFormator();
    }

    public void calculate(ActionEvent event) {
        clearData();

        readInitValues();

        calculateNoVacc();
        calculateVacc();

        populateNoVaccChart();
        populateVaccChart();

        populateTable();
    }

    private void clearData() {
        if (noVaccChart.getData() != null) {
            noVaccChart.getData().clear();
        }
        if (vaccChart.getData() != null) {
            vaccChart.getData().clear();
        }
        if (table.getItems() != null) {
            table.getItems().clear();
        }

        mb = 0;
    }

    private void readInitValues() {
        readBettas();
        readVParam();
        readPopulationParam();
        readIllNumberParam();
        startHealthNumber = totalPopulation - startIllNumber;

        noVacS[0] = startHealthNumber;
        noVacI[0] = startIllNumber;
        vacS[0] = startHealthNumber;
        vacI[0] = startIllNumber;
    }

    private void readBettas() {
        int iter = 0;

        for (Node child : bettaAnchor.getChildren()) {
            intensityInfluenzaTransmission[iter++] = Double.valueOf(((TextField) child).getText());
        }
    }

    private void readVParam() {
        int iter = 0;

        for (Node child : vacAnchor.getChildren()) {
            vaccParam[iter++] = Double.valueOf(((TextField) child).getText());
        }
    }

    private void readPopulationParam() {
        totalPopulation = Double.valueOf(initPopulationSize.getText());
    }

    private void readIllNumberParam() {
        startIllNumber = Double.valueOf(initIllSize.getText());
    }

    private void calculateNoVacc(){
        for (int i = 1; i < 12; i++) {
            noVacS[i] = NoVaccineModel.calculateNextS(
                    noVacS[i-1],
                    noVacI[i-1],
                    intensityInfluenzaTransmission[i-1],
                    totalPopulation
            );

            noVacI[i] = NoVaccineModel.calculateNextI(
                    noVacS[i-1],
                    noVacI[i-1],
                    intensityInfluenzaTransmission[i-1],
                    totalPopulation
            );
        }
    }

    private  void calculateVacc(){
        for (int i = 1; i < 12; i++) {
            vacS[i] = VaccineModel.calculateNextS(
                    vacS[i-1],
                    vacI[i-1],
                    intensityInfluenzaTransmission[i-1],
                    totalPopulation,
                    vaccParam[i-1]
            );

            vacI[i] = VaccineModel.calculateNextI(
                    vacS[i-1],
                    vacI[i-1],
                    intensityInfluenzaTransmission[i-1],
                    totalPopulation
            );

            vacV[i] = VaccineModel.calculateNextV(
                    vacS[i-1],
                    vacV[i-1],
                    vaccParam[i-1]
            );
        }
        for (int i = 0; i < vacI.length; i++) {
            if (vacV[i] != 0){
                MainController.mb += Math.abs(noVacI[i] - vacI[i]) / vacV[i] * totalPopulation;
            }
        }
    }

    private void populateNoVaccChart() {
        XYChart.Series<String, Double> illData = new XYChart.Series<>();
        illData.setName("Ill number");

        for (int i = 0; i < 12; i++) {
            illData.getData().add(new XYChart.Data<>(MONTHS[i], noVacI[i]));
        }

        noVaccChart.getData().add(illData);
    }

    private void populateVaccChart() {
        XYChart.Series<String, Double> illData = new XYChart.Series<>();
        illData.setName("Ill number");

        for (int i = 0; i < 12; i++) {
            illData.getData().add(new XYChart.Data<>(MONTHS[i], vacI[i]));
        }

        vaccChart.getData().add(illData);
    }

    private void populateTable(){
        for (int i = 0; i < 12; i++) {
            TableItem item = new TableItem().setMonth(MONTHS[i]).setIllNumberNoVacc(noVacI[i]).setIllNumberVacc(vacI[i]).setVaccNumber(vacV[i]).setHealthyNumberNoVacc(noVacS[i]).setHealthyNumberVacc(vacS[i]);
            if (i == 0){
                item.setMb(mb);
            }
            table.getItems().add(item);
        }
    }

    private void setTextFormator() {
        for (Node child : bettaAnchor.getChildren()) {
            ((TextField) child).textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d{0,10}([\\.]\\d{0,15})?")) {
                    ((TextField) child).setText(oldValue);
                }
            });

        }
        for (Node child : vacAnchor.getChildren()) {
            ((TextField) child).textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("0+(\\.\\d+)?|1\\.0") && !newValue.matches("^0+\\.$") && !newValue.matches("^1$") && !newValue.matches("^1\\.$") && !newValue.matches("^\\s*$")) {
                    ((TextField) child).setText(oldValue);
                }
            });

        }
        initPopulationSize.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,15}?")) {
                initPopulationSize.setText(oldValue);
            }
        });
        initIllSize.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,10}([\\.]\\d{0,15})?")) {
                initIllSize.setText(oldValue);
            }
        });
    }
}
