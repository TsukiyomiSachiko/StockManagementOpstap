package VooraadBeheerOpstap;

import javafx.beans.property.*;

public class Article {
    private final StringProperty scan;
    private final StringProperty name;
    private final BooleanProperty isStock;
    private final BooleanProperty isLend;
    private final IntegerProperty minimumStock;
    private final IntegerProperty currentStock;

    public Article(String scan, String name, boolean isStock, boolean isLend, int minimumStock, int currentStock) {
        this.scan = new SimpleStringProperty(scan);
        this.name = new SimpleStringProperty(name);
        this.isStock = new SimpleBooleanProperty(isStock);
        this.isLend = new SimpleBooleanProperty(isLend);
        this.minimumStock = new SimpleIntegerProperty(minimumStock);
        this.currentStock = new SimpleIntegerProperty(currentStock);
    }

    public String getScan() {
        return scan.get();
    }

    public void setScan(String scan) {
        this.scan.set(scan);
    }

    public StringProperty scanProperty() {
        return scan;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public boolean getIsStock() {
        return isStock.get();
    }

    public void setIsStock(boolean isStock) {
        this.isStock.set(isStock);
    }

    public BooleanProperty isStockProperty() {
        return isStock;
    }

    public boolean getIsLend() {
        return isLend.get();
    }

    public void setIsLend(boolean isLend) {
        this.isLend.set(isLend);
    }

    public int getMinimumStock() {
        return minimumStock.get();
    }

    public void setMinimumStock(int minimumStock) {
        this.minimumStock.set(minimumStock);
    }

    public IntegerProperty minimumStockProperty() {
        return minimumStock;
    }

    public int getCurrentStock() {
        return currentStock.get();
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock.set(currentStock);
    }

    public IntegerProperty currentStockProperty() {
        return currentStock;
    }
}
