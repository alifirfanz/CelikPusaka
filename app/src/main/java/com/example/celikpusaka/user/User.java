package com.example.celikpusaka.user;

class User {
    private String name;
    private String password;
    private String email;
    private String phoneNo;
    private String username;

    public User() {

    }

    public User(String name, String password, String email, String phoneNo, String username) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNo = phoneNo;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
