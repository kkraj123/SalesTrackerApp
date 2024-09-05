package com.examplej.salstrackerapp.addItems.customer.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.examplej.salstrackerapp.roomDatabase.CustomerDao;
import com.examplej.salstrackerapp.roomDatabase.ProductSalesDatabase;

import java.util.List;

public class CustomerRepository {

    CustomerDao customerDao;

    public CustomerRepository(Application application) {
        ProductSalesDatabase db = ProductSalesDatabase.getInstance(application);
        customerDao = db.customerDao();
    }

    public void insertData(List<Customer> customerList) {
        ProductSalesDatabase.databaseWriteExecutor.execute(() -> customerDao.insertData(customerList));
    }

    public LiveData<List<Customer>> getAllCustomer() {
        return customerDao.getAllCustomers();
    }
}
