package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public AnchorPane bettaAnchor;
    public AnchorPane vaccAnchor;
    public TextField regionName;
    public TextField population;
    public TextField illNumber;
    public TabPane tabPane;
    public Tab rootTab;

    public void addRegion(){
        double[] betta = new double[12];
        double[] vParam = new double[12];
        String regionNameValue;
        int populationValue;
        double illNumberValue;

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
        populationValue = Integer.valueOf(population.getText());
        illNumberValue = Double.valueOf(illNumber.getText());

        Main.regions.put(regionNameValue, new RegionData(betta, populationValue, illNumberValue, vParam));
    }

    public void clearRegions(ActionEvent event) {
    }

    public void calculate1TimeVacc(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
    }
}
