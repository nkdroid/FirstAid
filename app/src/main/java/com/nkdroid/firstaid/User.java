package com.nkdroid.firstaid;

/**
 * Created by Android on 18-03-2015.
 */
public class User {

    public boolean isLogin=false;

    private String username;
    private String password;

    public boolean isLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
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