package com.example.android.golocalfinal;

public class BuyerInformation {
    String userEmail,userName,userAddress,userCity,userNumber;
    public BuyerInformation(){

    }
    public BuyerInformation(String userEmail,String userName, String userAddress, String userCity, String userNumber) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userAddress = userAddress;
        this.userCity = userCity;
        this.userNumber = userNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserCity() {
        return userCity;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }
}

