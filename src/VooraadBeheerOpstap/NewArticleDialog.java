package VooraadBeheerOpstap;

import javafx.application.Platform;
import javafx.fxml.FXML;
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
        okClicked = true;

        dialogStage.close();
    }

    @FXML
    private void cancelButtonPressed() {
        dialogStage.close();
    }

    @FXML
    private void isStockArticleToggle() {
        minimumStock.setVisible(isStockArticle.isSelected());
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
