package com.example.capstone.Model;

public class User {
    private String Phone;
    private String Name;
    private String Password;
    private String BirthDate;
    private String familyID;

    public User(){}

    public User(String phone, String name, String password, String birthDate) {
        Phone = phone;
        Name = name;
        Password = password;
        BirthDate = birthDate;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() { return Phone; }

    public void setPhone(String phone) { Phone = phone; }

    public String getBirthDate() { return BirthDate; }

    public void setBirthDate(String birthDate) { BirthDate = birthDate; }

    public String getFamilyID() {
        return familyID;
    }

    public void setFamilyID(String familyID) {
        this.familyID = familyID;
    }
}
