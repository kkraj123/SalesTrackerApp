package com.examplej.salstrackerapp.addItems.modelClass;

public class ProductItemModel {
    String amount, productName;

    public ProductItemModel(String amount, String productName) {
        this.amount = amount;
        this.productName = productName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
