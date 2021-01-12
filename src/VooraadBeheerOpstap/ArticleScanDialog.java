package VooraadBeheerOpstap;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ArticleScanDialog {
    @FXML
    private TextField scanField;

    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;

    private Stage dialogStage;
    private boolean okClicked = false;
    private String scanText = "";


    @FXML
    private void initialize() {
        Platform.runLater(() -> scanField.requestFocus());
    }

    @FXML
    private void okButtonPressed() {
        okClicked = true;
        scanText = scanField.getText();

        dialogStage.close();
    }

    @FXML
    private void cancelButtonPressed() {
        dialogStage.close();
    }

    public void onEnter(ActionEvent ae){
        okButtonPressed();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public String getScanText() {
        return scanText;
    }
}
