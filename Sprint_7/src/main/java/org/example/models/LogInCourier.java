package org.example.models;

public class LogInCourier {

    private String login;
    private String password;

    public LogInCourier(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public LogInCourier() {
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
