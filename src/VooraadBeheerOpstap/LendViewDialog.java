package VooraadBeheerOpstap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class LendViewDialog {

    @FXML
    private TableView<Lend> lendTable;
    @FXML
    private TableColumn<Lend, String> articleNameColumn;
    @FXML
    private TableColumn<Lend, String> userNameColumn;

    ObservableList<Lend> lendList = FXCollections.observableArrayList();

    private Stage dialogStage;
    private Main mainApp;

    @FXML
    private void initialize() {
        articleNameColumn.setCellValueFactory(cellData -> cellData.getValue().articleNameProperty());
        userNameColumn.setCellValueFactory(cellData -> cellData.getValue().userNameProperty());

        lendTable.setItems(lendList);
    }

    @FXML
    private void closeButtonPressed() {
        this.dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;

        lendList.addAll(mainApp.getAllLendArticles());
    }
}
