package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public AnchorPane bettaAnchor;
    public AnchorPane vaccAnchor;
    public TextField regionName;
    public TextField population;
    public TextField illNumber;
    public TabPane tabPane;
    public Tab rootTab;
    public Button clearRegions;
    public Button oneTimeVaccCalc;
    public ScrollPane oneTimeScrollPane;
    public VBox oneTimeVBox;
    public ScrollPane twoTimeScrollPane;
    public VBox twoTimeVBox;
    public Button twoTimeVaccCalc;
    public TextField vaccStep;
    public ScrollPane partitionScrollPane;
    public VBox partitionVBox;
    public Button partVaccCalc;

    double step;

    public void addRegion(){
        double[] betta = new double[12];
        double[] vParam = new double[12];
        String regionNameValue;
        long populationValue;
        double illNumberValue;
        double vacStep;

        int counter = 0;
        for (Node child : bettaAnchor.getChildren()) {
            if (child.getTypeSelector().toLowerCase().contains("text")){
                betta[counter++] = Double.valueOf(((TextField) child).getText());
            }
        }
        counter = 0;
        for (Node child : vaccAnchor.getChildren()) {
            if (child.getTypeSelector().toLowerCase().contains("text")){
                vParam[counter++] = Double.valueOf(((TextField) child).getText());
            }
        }

        regionNameValue = regionName.getText();
        populationValue = Long.valueOf(population.getText());
        illNumberValue = Double.valueOf(illNumber.getText());
        vacStep = Double.valueOf(vaccStep.getText());

        Main.regions.add(new RegionData(regionNameValue, betta, populationValue, illNumberValue, vParam, vacStep));
    }

    public void clearRegions(ActionEvent event) {
    }

    public void calculate1TimeVacc(ActionEvent event) {
        oneTimeVBox.getChildren().clear();

        int counter = 0;

        for (RegionData region : Main.regions) {
            OneTimeVacc oneTimeVacc = new OneTimeVacc();
            double[] bestMonth = oneTimeVacc.findBestMonth(region);
            RegionData noVacData = NoVaccineModel.calculateVacc(region);

            RegionData dataTmp = null;
            try {
                dataTmp = (RegionData) region.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            dataTmp.setVaccParam(bestMonth);

            RegionData vacData = VaccineModel.calculateVacc(dataTmp);

            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel("Month");
            yAxis.setLabel("Number of people");
            LineChart<String, Number> chart = new LineChart<>(xAxis, yAxis);
            XYChart.Series<String, Number> seriesNoVac = new XYChart.Series<>();
            seriesNoVac.setName("No vacc");
            XYChart.Series<String, Number> seriesVac = new XYChart.Series<>();
            seriesVac.setName("Vacc");

            for (int i = 0; i < 12; i++) {
                seriesNoVac.getData().add(new XYChart.Data<>(RegionData.MONTHS[i], noVacData.getI()[i]));
                seriesVac.getData().add(new XYChart.Data<>(RegionData.MONTHS[i], vacData.getI()[i]));
            }

            chart.getData().addAll(seriesNoVac, seriesVac);
            chart.setLayoutX(5.0);
            chart.setLayoutY(550 * counter++);
            chart.setMinHeight(500.0);
            chart.setMinWidth(1000);

            Label label = new Label(region.getName());
            label.setMinWidth(250);
            label.setFont(Font.font(30));
            label.setAlignment(Pos.CENTER);

            Label mbLabel = new Label("Marginal benefit = " + oneTimeVacc.calculateGV(noVacData, vacData) * 100_000);
            mbLabel.setMinWidth(250);
            mbLabel.setFont(Font.font(20));
            mbLabel.setAlignment(Pos.CENTER);

            Label vaccArr = new Label("Vacc model = " + Arrays.toString(bestMonth));
            vaccArr.setMinWidth(250);
            vaccArr.setFont(Font.font(20));
            vaccArr.setAlignment(Pos.CENTER);

            oneTimeVBox.getChildren().add(label);
            oneTimeVBox.getChildren().add(mbLabel);
            oneTimeVBox.getChildren().add(vaccArr);
            oneTimeVBox.getChildren().add(chart);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTextFormator();
    }

    private void setTextFormator() {
        for (Node child : bettaAnchor.getChildren()) {
            if (child.getTypeSelector().toLowerCase().contains("text")){
                ((TextField) child).textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.matches("\\d{0,10}([\\.]\\d{0,15})?")) {
                        ((TextField) child).setText(oldValue);
                    }
                });
            }
        }
        for (Node child : vaccAnchor.getChildren()) {
            if (child.getTypeSelector().toLowerCase().contains("text")){
                ((TextField) child).textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.matches("0+(\\.\\d+)?|1\\.0") && !newValue.matches("^0+\\.$") && !newValue.matches("^1$") && !newValue.matches("^1\\.$") && !newValue.matches("^\\s*$")) {
                        ((TextField) child).setText(oldValue);
                    }
                });
            }

        }
        population.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,15}?")) {
                population.setText(oldValue);
            }
        });
        illNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,10}([\\.]\\d{0,15})?")) {
                illNumber.setText(oldValue);
            }
        });
        vaccStep.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,10}([\\.]\\d{0,15})?")) {
                vaccStep.setText(oldValue);
            }
        });
    }

    public void disableBottomBar(Event event) {
        clearRegions.setDisable(true);
        oneTimeVaccCalc.setDisable(true);
        twoTimeVaccCalc.setDisable(true);
        partVaccCalc.setDisable(true);
    }

    public void enableBottomBar(Event event) {
        if (clearRegions != null){
            clearRegions.setDisable(false);
        }if (oneTimeVaccCalc != null){
            oneTimeVaccCalc.setDisable(false);
        }if (twoTimeVaccCalc != null){
            twoTimeVaccCalc.setDisable(false);
        }if (partVaccCalc != null){
            partVaccCalc.setDisable(false);
        }
    }

    public void calculate2TimeVacc(ActionEvent event) {
        twoTimeVBox.getChildren().clear();

        int counter = 0;

        for (RegionData region : Main.regions) {
            TwoTimesVacc twoTimesVacc = new TwoTimesVacc();
            double[] bestMonth = twoTimesVacc.findBestMonth(region);
            RegionData noVacData = NoVaccineModel.calculateVacc(region);

            RegionData dataTmp = null;
            try {
                dataTmp = (RegionData) region.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            dataTmp.setVaccParam(bestMonth);

            RegionData vacData = VaccineModel.calculateVacc(dataTmp);

            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel("Month");
            yAxis.setLabel("Number of people");
            LineChart<String, Number> chart = new LineChart<>(xAxis, yAxis);
            XYChart.Series<String, Number> seriesNoVac = new XYChart.Series<>();
            seriesNoVac.setName("No vacc");
            XYChart.Series<String, Number> seriesVac = new XYChart.Series<>();
            seriesVac.setName("Vacc");

            for (int i = 0; i < 12; i++) {
                seriesNoVac.getData().add(new XYChart.Data<>(RegionData.MONTHS[i], noVacData.getI()[i]));
                seriesVac.getData().add(new XYChart.Data<>(RegionData.MONTHS[i], vacData.getI()[i]));
            }

            chart.getData().addAll(seriesNoVac, seriesVac);
            chart.setLayoutX(5.0);
            chart.setLayoutY(550 * counter++);
            chart.setMinHeight(500.0);
            chart.setMinWidth(1000);

            Label label = new Label(region.getName());
            label.setMinWidth(250);
            label.setFont(Font.font(30));
            label.setAlignment(Pos.CENTER);

            Label mbLabel = new Label("Marginal benefit = " + twoTimesVacc.calculateGV(noVacData, vacData) * 100_000);
            mbLabel.setMinWidth(250);
            mbLabel.setFont(Font.font(20));
            mbLabel.setAlignment(Pos.CENTER);

            Label vaccArr = new Label("Vacc model = " + Arrays.toString(bestMonth));
            vaccArr.setMinWidth(250);
            vaccArr.setFont(Font.font(20));
            vaccArr.setAlignment(Pos.CENTER);

            twoTimeVBox.getChildren().add(label);
            twoTimeVBox.getChildren().add(mbLabel);
            twoTimeVBox.getChildren().add(vaccArr);
            twoTimeVBox.getChildren().add(chart);
        }
    }

    public void calculatePartVacc(ActionEvent event) {
        twoTimeVBox.getChildren().clear();

        int counter = 0;


        for (RegionData region : Main.regions) {
            ProportionalVacc proportionalVacc= new ProportionalVacc(Main.regions.stream().mapToDouble(RegionData::getTotalPopulation).sum());
            double[] bestMonth = proportionalVacc.findBestMonth(region);
            RegionData noVacData = NoVaccineModel.calculateVacc(region);

            RegionData dataTmp = null;
            try {
                dataTmp = (RegionData) region.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            dataTmp.setVaccParam(bestMonth);

            RegionData vacData = VaccineModel.calculateVacc(dataTmp);

            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel("Month");
            yAxis.setLabel("Number of people");
            LineChart<String, Number> chart = new LineChart<>(xAxis, yAxis);
            XYChart.Series<String, Number> seriesNoVac = new XYChart.Series<>();
            seriesNoVac.setName("No vacc");
            XYChart.Series<String, Number> seriesVac = new XYChart.Series<>();
            seriesVac.setName("Vacc");

            for (int i = 0; i < 12; i++) {
                seriesNoVac.getData().add(new XYChart.Data<>(RegionData.MONTHS[i], noVacData.getI()[i]));
                seriesVac.getData().add(new XYChart.Data<>(RegionData.MONTHS[i], vacData.getI()[i]));
            }

            chart.getData().addAll(seriesNoVac, seriesVac);
            chart.setLayoutX(5.0);
            chart.setLayoutY(550 * counter++);
            chart.setMinHeight(500.0);
            chart.setMinWidth(1000);

            Label label = new Label(region.getName());
            label.setMinWidth(250);
            label.setFont(Font.font(30));
            label.setAlignment(Pos.CENTER);

            Label mbLabel = new Label("Marginal benefit = " + proportionalVacc.calculateGV(noVacData, vacData) * 100_000);
            mbLabel.setMinWidth(250);
            mbLabel.setFont(Font.font(20));
            mbLabel.setAlignment(Pos.CENTER);

            Label vaccArr = new Label("Vacc model = " + Arrays.toString(bestMonth));
            vaccArr.setMinWidth(250);
            vaccArr.setFont(Font.font(20));
            vaccArr.setAlignment(Pos.CENTER);

            partitionVBox.getChildren().add(label);
            partitionVBox.getChildren().add(mbLabel);
            partitionVBox.getChildren().add(vaccArr);
            partitionVBox.getChildren().add(chart);
        }
    }
}
