package VooraadBeheerOpstap;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Stock {
    private final StringProperty stockId;
    private final StringProperty userId;
    private final StringProperty articleId;

    public Stock(String stockId, String userId, String articleId) {
        this.stockId = new SimpleStringProperty(stockId);
        this.userId = new SimpleStringProperty(userId);
        this.articleId = new SimpleStringProperty(articleId);
    }

    public String getStockId() {
        return stockId.get();
    }

    public void setStockId(String stockId) {
        this.stockId.set(stockId);
    }

    public StringProperty stockIdProperty() {
        return stockId;
    }

    public String getUserId() {
        return userId.get();
    }

    public void setUserId(String userId) {
        this.userId.set(userId);
    }

    public StringProperty userIdProperty() {
        return userId;
    }

    public String getArticleId() {
        return articleId.get();
    }

    public void setArticleId(String articleId) {
        this.articleId.set(articleId);
    }

    public StringProperty articleIdProperty() {
        return articleId;
    }
}
