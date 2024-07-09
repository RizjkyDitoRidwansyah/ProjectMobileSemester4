package com.millenialzdev.logindanregistervolleymysql.Entitas;

public class User {
    private String email, NIM, username, password;

    public User() {

    }

    public User(String email, String NIM, String username, String password) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.NIM = NIM;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNIM() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
