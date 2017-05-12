package com.alllxt.selenium.framework.models;

/**
 * Created by atribushny on 19.04.2017.
 */
public class User {

    private String email;
    private String password;
    private String fname, lname;
    private String adress;

    public User() {

    }


    private final String adminUserName = "admin";
    private final String adminUserPassword = "admin";


    public String getAdminUserName() {
        return adminUserName;
    }

    public String getAdminUserPassword() {
        return adminUserPassword;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
