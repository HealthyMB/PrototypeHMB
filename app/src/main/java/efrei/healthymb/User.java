package efrei.healthymb;

/**
 * Created by Richard on 05/03/2016.
 */
public class User {

    private String login;
    private String password;

    public User(String password, String login) {
        this.password = password;
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
