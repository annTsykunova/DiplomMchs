package by.bsu.modulestatic.entity;

import javax.xml.soap.SAAJResult;

public class User {
    private int userId;
    private String name;
    private String surname;
    private String login;
    private String role;
    private String password;

    public User(){

    }

    public User(int userId, String name, String surname, String login, String role, String password) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.role = role;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
