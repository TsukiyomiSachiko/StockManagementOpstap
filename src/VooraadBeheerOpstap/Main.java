package VooraadBeheerOpstap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private DatabaseHandler databaseHandler = new DatabaseHandler();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("VooraadManagementOpstap");

        if (!databaseHandler.isDatabasePresent()) {
            databaseHandler.createDatabase();
        }

        EmailHandler emailHandler = new EmailHandler();
        emailHandler.sendEmail();

        initRootLayout();

        showMainScreen();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMainScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("MainView.fxml"));
            AnchorPane mainView = (AnchorPane) loader.load();

            rootLayout.setCenter(mainView);

            Controller controller = loader.getController();
            controller.setMainApp(this);
            controller.setDatabaseHandler(databaseHandler);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public String showArticleScanDialog() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("ArticleScanDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Scan Artikel");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ArticleScanDialog controller = loader.getController();
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();

            if(controller.isOkClicked()) {
                return controller.getScanText();
            } else {
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String showNewArticleDialog() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("NewArticleDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nieuw Artikel");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            NewArticleDialog controller = loader.getController();
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();

            if(controller.isOkClicked()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(controller.getArticleName()).append(",1,").append(controller.getIsLendArticle())
                        .append(",").append(controller.getIsStockArticle()).append(",");

                String minimumStock = controller.getMinimumStock();

                if(minimumStock.equals("")) {
                    stringBuilder.append("0");
                } else {
                    stringBuilder.append(minimumStock);
                }

                return stringBuilder.toString();
            } else {
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String showNewUserDialog() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("NewUserDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nieuwe gebruiker");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            NewUserDialog controller = loader.getController();
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();

            if(controller.isOkClicked()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(controller.getFirstName()).append(",").append(controller.getLastName());

                return stringBuilder.toString();
            } else {
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String showUserSelectionDialog() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("UserSelectionDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Gebruiker kiezen");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            UserSelectionDialog controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);

            dialogStage.showAndWait();

            if(controller.isOkClicked()) {
                User user = controller.getSelectedUser();
                return user.getUserId();
            } else {
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String showSmallUserSelectionDialog(String articleId) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("SmallUserSelectionDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Gebruiker kiezen");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            SmallUserSelectionDialog controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            controller.setArticleId(articleId);

            dialogStage.showAndWait();

            if(controller.isOkClicked()) {
                User user = controller.getSelectedUser();
                return user.getUserId();
            } else {
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void showStockViewDialog() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("StockViewDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Huidige vooraad");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            StockViewDialog controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLendViewDialog() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("LendViewDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Uitgeleende artikelen");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            LendViewDialog controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);

            dialogStage.showAndWait();
        } catch (IOException e) {
            System.out.println("Broken");
            e.printStackTrace();
        }
    }

    public ArrayList<User> getAllUsers() {
        try {
            return databaseHandler.getAllUsers();
        } catch(IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ArrayList<User> getUsersByLendItem(String itemId) {
        try {
            ArrayList<Stock> stockList = databaseHandler.getAllStock();
            ArrayList<User> userList = new ArrayList<>();
            ArrayList<String> userIds = new ArrayList<>();

            stockList.forEach((stock) -> {
                if (stock.getArticleId().equals(itemId)) {
                    userIds.add(stock.getUserId());
                }
            });

            userIds.forEach((userId) -> {
                try {
                    String userRaw = databaseHandler.readUserRecord(userId);
                    String[] dataParts = userRaw.split(",");

                    userList.add(new User(userId, dataParts[0], dataParts[1]));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            return userList;
        } catch(IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ArrayList<Article> getAllArticles() {
        try {
            return databaseHandler.getAllArticles();
        } catch(IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ArrayList<Lend> getAllLendArticles() {
        try {
            ArrayList<Lend> lendList = new ArrayList<>();

            ArrayList<Stock> stockRecords = databaseHandler.getAllStock();

            for(Stock stock : stockRecords) {
                String articleRecord = databaseHandler.readArticleRecord(stock.getArticleId());
                String[] articleParts = articleRecord.split(",");
                String articleName = articleParts[0];

                String userRecord = databaseHandler.readUserRecord(stock.getUserId());
                String[] userParts = userRecord.split(",");
                String userName = userParts[0] + " " + userParts[1];

                lendList.add(new Lend(articleName, userName));
            }

            return lendList;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean prepareAndSendStockOverviewMail() {
        try {
            ArrayList<Article> articleRecords = databaseHandler.getAllArticles();
            ArrayList<Article> articlesStock = new ArrayList<>();

            for(Article article : articleRecords) {
                if (article.getIsStock()) {
                    articlesStock.add(article);
                }
            }

            EmailHandler.sendStockEmail(articlesStock);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
