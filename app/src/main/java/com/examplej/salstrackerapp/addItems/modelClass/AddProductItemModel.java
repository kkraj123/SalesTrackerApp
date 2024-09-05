package com.examplej.salstrackerapp.addItems.modelClass;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sales_list")
public class AddProductItemModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    String productItemName, amount, date, customerName;
    private int customerId;
    int quantity;

    public AddProductItemModel(String productItemName, int quantity, String amount, String date, String customerName, int customerId) {
        this.productItemName = productItemName;
        this.quantity = quantity;
        this.amount = amount;
        this.date = date;
        this.customerName = customerName;
        this.customerId = customerId;
    }

    public String getProductItemName() {
        return productItemName;
    }

    public void setProductItemName(String productItem) {
        this.productItemName = productItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
