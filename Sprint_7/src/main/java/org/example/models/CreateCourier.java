package org.example.models;

public class CreateCourier {
    private String login;
    private String password;
    private String firstName;


    public CreateCourier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public CreateCourier() {
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public CreateCourier withLogin(String login) {
        this.login = login;
        return this;
    }

    public CreateCourier withPassword(String password) {
        this.password = password;
        return this;
    }

    public CreateCourier withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
}

