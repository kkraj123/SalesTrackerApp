package com.examplej.salstrackerapp.roomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.examplej.salstrackerapp.addItems.customer.viewmodel.Customer;

import java.util.List;

@Dao
public interface CustomerDao {

    @Insert
    void insertData(List<Customer> customerList);

    @Query("SELECT * FROM customer")
    LiveData<List<Customer>> getAllCustomers();

}
