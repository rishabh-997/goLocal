package com.example.android.golocalfinal;

public class Product {
    String Quantity;
    String Name;

    public Product(){}

    public Product(String quantity, String name) {
        Quantity = quantity;
        Name = name;
    }

    public String getQuantity() {
        return Quantity;
    }

    public String getName() {
        return Name;
    }
}
