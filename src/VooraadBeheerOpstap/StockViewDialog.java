package VooraadBeheerOpstap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class StockViewDialog {

    @FXML
    private TableView<Article> stockTable;
    @FXML
    private TableColumn<Article, String> nameColumn;
    @FXML
    private TableColumn<Article, Number> currentStockColumn;
    @FXML
    private TableColumn<Article, Number> minimumStockColumn;

    ObservableList<Article> stockList = FXCollections.observableArrayList();

    private Stage dialogStage;
    private Main mainApp;

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        currentStockColumn.setCellValueFactory(cellData -> cellData.getValue().currentStockProperty());
        minimumStockColumn.setCellValueFactory(cellData -> cellData.getValue().minimumStockProperty());


    }
}
