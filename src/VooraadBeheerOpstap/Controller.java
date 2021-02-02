package VooraadBeheerOpstap;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class Controller {
    @FXML
    private Button scanInButton;
    @FXML
    private Button scanOutButton;
    @FXML
    private Button newUserButton;
    @FXML
    private Button checkStockButton;

    private Main mainApp;
    private DatabaseHandler databaseHandler;

    @FXML
    private void scanInButtonPressed() {
        System.out.println("Scan Artikel In");
        String scan = mainApp.showArticleScanDialog();

        if (scan.equals("")) {
            return;
        }

        if(databaseHandler.isArticleRecordPresent(scan)) {
            try {
                String rawData = databaseHandler.readArticleRecord(scan);
                String[] dataParts = rawData.split(",");

                String name = dataParts[0];
                int currentStock = Integer.parseInt(dataParts[1]);
                boolean isLendItem = Boolean.parseBoolean(dataParts[2]);
                boolean isStockItem = Boolean.parseBoolean(dataParts[3]);
                int minimumStock = Integer.parseInt(dataParts[4]);

                currentStock += 1;

                databaseHandler.updateArticleRecord(scan, name, currentStock, isLendItem, isStockItem, minimumStock);

                if (isLendItem) {
                    ArrayList<User> userList = mainApp.getUsersByLendItem(scan);
                    if(userList.size() == 0) {
                        return;
                    }

                    String userId = mainApp.showSmallUserSelectionDialog(scan);

                    if(userId.equals("")) {
                        return;
                    } else {
                        databaseHandler.deleteStockRecord(databaseHandler.getStockRecordIdByContent(userId, scan));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            String rawData = mainApp.showNewArticleDialog();
            String[] dataParts = rawData.split(",");

            String name = dataParts[0];
            int currentStock = Integer.parseInt(dataParts[1]);
            boolean isLendItem = Boolean.parseBoolean(dataParts[2]);
            boolean isStockItem = Boolean.parseBoolean(dataParts[3]);
            int minimumStock = Integer.parseInt(dataParts[4]);

            databaseHandler.createArticleRecord(scan);
            try {
                databaseHandler.updateArticleRecord(scan, name, currentStock, isLendItem, isStockItem, minimumStock);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void scanOutButtonPressed() {
        System.out.println("Scan Artikel Uit");
        String scan = mainApp.showArticleScanDialog();

        if (scan.equals("")) {
            return;
        }

        if(databaseHandler.isArticleRecordPresent(scan)) {
            try {
                String rawData = databaseHandler.readArticleRecord(scan);
                String[] dataParts = rawData.split(",");

                String name = dataParts[0];
                int currentStock = Integer.parseInt(dataParts[1]);
                boolean isLendItem = Boolean.parseBoolean(dataParts[2]);
                boolean isStockItem = Boolean.parseBoolean(dataParts[3]);
                int minimumStock = Integer.parseInt(dataParts[4]);

                currentStock -= 1;

                if(currentStock <= minimumStock && isStockItem && currentStock+1 >= 1) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Nieuw Bestellen");
                    alert.setHeaderText(null);
                    alert.setContentText("Minimum vooraad voor artikel " + name + " bereikt, bestel nieuwe");

                    alert.showAndWait();
                }

                if (currentStock+1 <= 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Artikel op");
                    alert.setHeaderText(null);
                    alert.setContentText("Vooraad voor dit artikel is 0, scan eerst nieuwe in");

                    currentStock = 0;

                    alert.showAndWait();
                    return;
                }

                if(isLendItem) {
                    String userId = mainApp.showUserSelectionDialog();

                    if (userId.equals("")) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Gebruiker Selecteren Verplicht");
                        alert.setHeaderText(null);
                        alert.setContentText("Het is verplicht een gebruiker te selecteren");

                        currentStock += 1;

                        alert.showAndWait();
                        return;
                    } else {
                        UUID id = UUID.randomUUID();

                        databaseHandler.createStockRecord(id.toString());

                        try {
                            databaseHandler.updateStockRecord(id.toString(), userId, scan);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                databaseHandler.updateArticleRecord(scan, name, currentStock, isLendItem, isStockItem, minimumStock);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Artikel niet herkend");
            alert.setHeaderText(null);
            alert.setContentText("Artikel is niet herkend, scan het eerst in voordat je het uitgeeft");

            alert.showAndWait();
        }
    }

    @FXML
    private void newUserButtonPressed() {
        String rawData = mainApp.showNewUserDialog();

        if(rawData.equals("")) {
            return;
        }

        String[] dataParts = rawData.split(",");

        String firstName = dataParts[0];
        String lastName = dataParts[1];

        UUID id = UUID.randomUUID();

        databaseHandler.createUserRecord(id.toString());

        try {
            databaseHandler.updateUserRecord(id.toString(), firstName, lastName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDatabaseHandler(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}
