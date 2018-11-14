package sample;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class DoubleCell<S,T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {
    @Override
    @SuppressWarnings("unchecked")
    public TableCell<S, T> call(TableColumn<S, T> p) {
        TableCell<S, T> cell = new TableCell<S, T>() {
            @Override
            public void updateItem(Object item, boolean empty) {
                super.setText(String.format("%.15f", ((Double) item)));
            }
        };
        return cell;
    }
}
