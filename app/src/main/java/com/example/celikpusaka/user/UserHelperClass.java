package com.example.celikpusaka.user;

public class UserHelperClass {

    String username,password,name,email,phoneNo;
    String gender,university;

    public UserHelperClass() {
    }

    public UserHelperClass(String fullName, String komen, String suggestion) {
    }

    public UserHelperClass(String username, String password, String name, String email, String phoneNo, String gender, String university) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.university = university;
    }

    public UserHelperClass(String username, String password, String name, String email, String phoneNo) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
