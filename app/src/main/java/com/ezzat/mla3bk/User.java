package com.ezzat.mla3bk;

/**
 * Created by Ahmed Maghawry on 7/8/2017.
 */

public class User {
    private String name;
    private String email;
    private int age;
    private String mobile;
    private String nationalID;
    private String location;

    public User(String name, String email, int age, String mobile, String nationalID, String location) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.mobile = mobile;
        this.nationalID = nationalID;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
