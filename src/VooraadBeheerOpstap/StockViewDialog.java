package VooraadBeheerOpstap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

        stockTable.setItems(stockList);
    }

    @FXML
    private void closeButtonPressed() {
        this.dialogStage.close();
    }

    @FXML
    private void sendStockOverview() {
        if(mainApp.prepareAndSendStockOverviewMail()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mail sturen successvol");
            alert.setHeaderText(null);
            alert.setContentText("Het sturen van de mail met vooraadoverzicht is successvol verlopen");

            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mail sturen mislukt");
            alert.setHeaderText(null);
            alert.setContentText("Het sturen van de mail met vooraadoverzicht is mislukt, probeer het later opnieuw");

            alert.showAndWait();
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;

        stockList.addAll(mainApp.getAllArticles());
    }
}
