package VooraadBeheerOpstap;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewArticleDialog {
    @FXML
    private TextField articleName;
    @FXML
    private CheckBox isLendArticle;
    @FXML
    private CheckBox isStockArticle;
    @FXML
    private TextField minimumStock;
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;

    private Stage dialogStage;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
        Platform.runLater(() -> articleName.requestFocus());
    }

    @FXML
    private void okButtonPressed() {
        if(validate()) {
            okClicked = true;

            dialogStage.close();
        } else {
            articleName.setText("");
            articleName.requestFocus();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invoerfout naam");
            alert.setHeaderText(null);
            alert.setContentText("Het naamveld mag niet leeg zijn en geen komma's bevatten");

            alert.showAndWait();
        }
    }

    @FXML
    private void cancelButtonPressed() {
        dialogStage.close();
    }

    @FXML
    private void isStockArticleToggle() {
        minimumStock.setVisible(isStockArticle.isSelected());
    }

    private boolean validate() {
        if(articleName.getText().equals("") || articleName.getText().contains(",")) {
            return false;
        } else {
            return true;
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public String getArticleName() {
        return articleName.getText();
    }

    public boolean getIsLendArticle() {
        return isLendArticle.isSelected();
    }

    public boolean getIsStockArticle() {
        return isStockArticle.isSelected();
    }

    public String getMinimumStock() {
        return minimumStock.getText();
    }
}
