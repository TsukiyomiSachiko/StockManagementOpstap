package VooraadBeheerOpstap;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Lend {
    private StringProperty articleName;
    private StringProperty userName;

    public Lend(String articleName, String userName) {
        this.articleName = new SimpleStringProperty(articleName);
        this.userName = new SimpleStringProperty(userName);
    }

    public String getArticleName() {
        return articleName.get();
    }

    public void setArticleName(String articleName) {
        this.articleName.set(articleName);
    }

    public StringProperty articleNameProperty() {
        return articleName;
    }

    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public StringProperty userNameProperty() {
        return userName;
    }
}
