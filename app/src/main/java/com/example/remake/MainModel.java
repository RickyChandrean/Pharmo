package com.example.remake;

public class MainModel {
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public MainModel(int index) {
        this.index = index;
    }

    int index;
    String ProductName;
    String ProductType;

    MainModel(){

    }
    public String getProductName() {
        return ProductName;
    }

    public MainModel(String productName, String productType, String productPrice, String productQuantity) {
        ProductName = productName;
        ProductType = productType;
        ProductPrice = productPrice;
        ProductQuantity = productQuantity;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public String getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        ProductQuantity = productQuantity;
    }

    String ProductPrice;
    String ProductQuantity;
}
