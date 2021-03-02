package VooraadBeheerOpstap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Boolean.parseBoolean;
import static jdk.nashorn.internal.runtime.GlobalFunctions.parseInt;

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

    public ArrayList<Article> getAllArticles() throws IOException{
        File directory = new File(dirName + File.separator + articleRecordTable);
        File[] listOfFiles = directory.listFiles();

        ArrayList<Article> listOfArticles = new ArrayList<>();

        for(File file : listOfFiles) {
            if(file.isFile()) {
                if(!file.exists() || !file.canRead()) {
                    throw new IOException("File does not exist or cannot be read");
                } else {
                    Scanner reader = new Scanner(file);
                    StringBuilder data = new StringBuilder();

                    while(reader.hasNextLine()) {
                        data.append(reader.nextLine());
                    }

                    reader.close();

                    String stringData = data.toString();
                    String[] dataParts = stringData.split(",");

                    listOfArticles.add(new Article(file.getName(), dataParts[0], parseBoolean(dataParts[3]),
                            parseBoolean(dataParts[2]), Integer.parseInt(dataParts[4]), Integer.parseInt(dataParts[1])));
                }
            }
        }

        return listOfArticles;
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

    public String readUserRecord(String recordId) throws IOException {
        File file = new File(dirName + File.separator + userRecordTable + File.separator + recordId);

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

    public ArrayList<User> getAllUsers() throws IOException {
        File directory = new File(dirName + File.separator + userRecordTable);
        File[] listOfFiles = directory.listFiles();

        ArrayList<User> listOfUsers = new ArrayList<>();

        for (File file: listOfFiles) {
            if(file.isFile()) {
                if(!file.exists() || !file.canRead()) {
                    throw new IOException("File does not exist or cannot be read");
                } else {
                    Scanner reader = new Scanner(file);
                    StringBuilder data = new StringBuilder();

                    while(reader.hasNextLine()) {
                        data.append(reader.nextLine());
                    }

                    reader.close();

                    String stringData = data.toString();
                    String[] parts = stringData.split(",");

                    listOfUsers.add(new User(file.getName(), parts[0], parts[1]));
                }
            }
        }

        return listOfUsers;
    }

    public void createStockRecord(String recordId) {
        File file = new File(dirName + File.separator + stockRecordTable + File.separator + recordId);

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateStockRecord(String recordId, String userId, String articleId) throws IOException {
        File file = new File(dirName + File.separator + stockRecordTable + File.separator + recordId);

        if(!file.exists() || !file.canWrite()) {
            throw new IOException("File does not exist or cannot be written");
        } else {
            FileWriter fileWriter = new FileWriter(dirName + File.separator + stockRecordTable + File.separator +
                    recordId);
            fileWriter.write(userId + "," + articleId);
            fileWriter.close();
        }
    }

    public String getStockRecordIdByContent(String userId, String articleId) throws IOException {
        File directory = new File(dirName + File.separator + stockRecordTable);
        File[] listOfFiles = directory.listFiles();

        for(File file : listOfFiles) {
            if (file.isFile()) {
                if(!file.exists() || !file.canRead()) {
                    throw new IOException("File does not exist or cannot be read");
                } else {
                    Scanner reader = new Scanner(file);
                    StringBuilder data = new StringBuilder();

                    while(reader.hasNextLine()) {
                        data.append(reader.nextLine());
                    }

                    reader.close();

                    String stringData = data.toString();
                    String[] dataParts = stringData.split(",");

                    if(dataParts[0].equals(userId) && dataParts[1].equals(articleId)) {
                        return file.getName();
                    }
                }
            }
        }

        return "";
    }

    public void deleteStockRecord(String stockId) throws IOException {
        File file = new File(dirName + File.separator + stockRecordTable + File.separator + stockId);

        if(file.exists() && file.canWrite()) {
            file.delete();
        } else {
            throw new IOException("File does not exist or cannnot be read");
        }
    }

    public ArrayList<Stock> getAllStock() throws IOException {
        File directory = new File(dirName + File.separator + stockRecordTable);
        File[] listOfFiles = directory.listFiles();

        ArrayList<Stock> listOfStock = new ArrayList<>();

        for(File file : listOfFiles) {
            if(file.isFile()) {
                if(!file.exists() || !file.canRead()) {
                    throw new IOException("File does not exist or cannot be read");
                } else {
                    Scanner reader = new Scanner(file);
                    StringBuilder data = new StringBuilder();

                    while(reader.hasNextLine()) {
                        data.append(reader.nextLine());
                    }

                    reader.close();

                    String stringData = data.toString();
                    String[] dataParts = stringData.split(",");

                    listOfStock.add(new Stock(file.getName(), dataParts[0], dataParts[1]));
                }
            }
        }

        return listOfStock;
    }
}
