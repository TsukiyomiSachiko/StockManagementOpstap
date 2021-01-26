package VooraadBeheerOpstap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class SmallUserSelectionDialog {

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> firstNameColumn;
    @FXML
    private TableColumn<User, String> lastNameColumn;

    private ObservableList<User> userList = FXCollections.observableArrayList();

    private Stage dialogStage;
    private boolean okClicked = false;
    private User selectedUser;
    private Main mainApp;

    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        userTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.selectedUser = newValue;
        });
    }

    @FXML
    private void okButtonPressed() {
        okClicked = true;

        dialogStage.close();
    }

    @FXML
    private void cancelButtonPressed() {
        dialogStage.close();
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;

        userList.addAll(mainApp.getAllUsers());
    }
}