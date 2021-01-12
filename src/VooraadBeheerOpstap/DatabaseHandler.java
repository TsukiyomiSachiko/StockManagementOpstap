package VooraadBeheerOpstap;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DatabaseHandler {
    //Main database Directory
    private String dirName = "StockDatabase";
    //Record folder for articles and their current and minimum stock
    private String articleRecordTable = "ArticleRecords";
    //Record folder for users
    private String userRecordTable = "UserRecords";
    //Record folder to track which users have which articles
    private String stockRecordTable = "StockRecords";

    public boolean isDatabasePresent() {
        File directory = new File(dirName);

        return directory.exists();
    }

    public void createDatabase() {
        File directory = new File(dirName);

        if(!directory.exists()) {
            directory.mkdir();
        }

        File articleSubDirectory = new File(dirName + File.separator + articleRecordTable);

        if(!articleSubDirectory.exists()) {
            articleSubDirectory.mkdir();
        }

        File userSubDirectory = new File(dirName + File.separator + userRecordTable);

        if(!userSubDirectory.exists()) {
            userSubDirectory.mkdir();
        }

        File stockSubDirectory = new File(dirName + File.separator + stockRecordTable);

        if(!stockSubDirectory.exists()) {
            stockSubDirectory.mkdir();
        }
    }

    public void createArticleRecord(String recordId) {
        File file = new File(dirName + File.separator + articleRecordTable + File.separator + recordId);

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isArticleRecordPresent(String recordId) {
        File file = new File(dirName + File.separator + articleRecordTable + File.separator + recordId);

        return file.exists();
    }

    public void updateArticleRecord(String recordId, String name, int currentStock, boolean isLendArticle,
                                    boolean isStockArticle, int minimumStock) throws IOException {
        File file = new File(dirName + File.separator + articleRecordTable + File.separator + recordId);

        if(!file.exists() || !file.canWrite()) {
            throw new IOException("File does not exist or cannot be written");
        } else {
            FileWriter fileWriter = new FileWriter(dirName + File.separator + articleRecordTable + File.separator + recordId);
            fileWriter.write(name + "," + currentStock + "," + isLendArticle + "," + isStockArticle + "," +
                    minimumStock);
            fileWriter.close();
        }
    }

    public String readArticleRecord(String recordId) throws IOException {
        File file = new File(dirName + File.separator + articleRecordTable + File.separator + recordId);

        if(!file.exists() || !file.canRead()) {
            throw new IOException("File does not exist or cannot be read");
        } else {
            Scanner reader = new Scanner(file);
            StringBuilder data = new StringBuilder();

            while(reader.hasNextLine()) {
                data.append(reader.nextLine());
            }

            reader.close();
            return data.toString();
        }
    }

    public void createUserRecord(String recordId) {
        File file = new File(dirName + File.separator + userRecordTable + File.separator + recordId);

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateUserRecord(String recordId, String firstName, String lastName) throws IOException {
        File file = new File(dirName + File.separator + userRecordTable + File.separator + recordId);

        if(!file.exists() || !file.canWrite()) {
            throw new IOException("File does not exist or cannot be written");
        } else {
            FileWriter fileWriter = new FileWriter(dirName + File.separator + userRecordTable + File.separator +
                    recordId);
            fileWriter.write(firstName + "," + lastName);
            fileWriter.close();
        }
    }
}
