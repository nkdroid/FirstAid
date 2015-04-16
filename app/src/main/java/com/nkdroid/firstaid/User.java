package com.nkdroid.firstaid;

public class User {

    public boolean isLogin=false;

    private String username;
    private String password;
    public String hname;
    public String haddr;
    public String phoneno;
    public String type,latitude,services,opentime,closetime,closeddays;
    public String longitude;

    public User(){}

    public User(String hname,String haddr,String phoneno,String type,String latitude,String longitude,String services,String opentime,String closetime,String closeddays) {
        super();
        this.hname = hname;
        this.haddr = haddr;
        this.phoneno = phoneno;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.services = services;
        this.opentime = opentime;
        this.closetime = closetime;
        this.closeddays = closeddays;

    }


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
