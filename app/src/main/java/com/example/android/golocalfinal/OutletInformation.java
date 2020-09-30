package com.example.android.golocalfinal;

public class OutletInformation {
    String emailId,outletName,outletAddress,outletCity,outletLocality,outletContactName,outletNumber;

    public OutletInformation() {
    }

    public OutletInformation(String emailId, String outletName, String outletAddress, String outletCity, String outletLocality, String outletContactName,String outletNumber) {
        this.emailId = emailId;
        this.outletName = outletName;
        this.outletAddress = outletAddress;
        this.outletCity = outletCity;
        this.outletLocality = outletLocality;
        this.outletContactName = outletContactName;
        this.outletNumber = outletNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getOutletName() {
        return outletName;
    }

    public String getOutletAddress() {
        return outletAddress;
    }

    public String getOutletCity() { return outletCity; }

    public String getOutletLocality() {
        return outletLocality;
    }

    public String getOutletContactName() {
        return outletContactName;
    }

    public String getOutletNumber() {
        return outletNumber;
    }
}
