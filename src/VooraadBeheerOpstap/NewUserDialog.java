package VooraadBeheerOpstap;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewUserDialog {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;

    private Stage dialogStage;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
        Platform.runLater(() -> firstNameField.requestFocus());
    }

    @FXML
    private void okButtonPressed() {
        if(validate()) {
            okClicked = true;

            dialogStage.close();
        }
    }

    @FXML
    private void cancelButtonPressed() {
        dialogStage.close();
    }

    private boolean validate() {
        if(firstNameField.getText().equals("") || firstNameField.getText().contains(",")) {
            firstNameField.setText("");
            firstNameField.requestFocus();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Voornaamveld fout");
            alert.setHeaderText(null);
            alert.setContentText("Voornaamveld mag niet leeg zijn en geen komma's bevatten");

            alert.showAndWait();

            return false;
        }

        if(lastNameField.getText().equals("") || lastNameField.getText().contains(",")) {
            lastNameField.setText("");
            lastNameField.requestFocus();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Achternaamveld fout");
            alert.setHeaderText(null);
            alert.setContentText("Achternaamveld mag niet leeg zijn en geen komma's bevatten");

            alert.showAndWait();

            return false;
        }

        return true;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public String getFirstName() {
        return firstNameField.getText();
    }

    public String getLastName() {
        return lastNameField.getText();
    }
}
