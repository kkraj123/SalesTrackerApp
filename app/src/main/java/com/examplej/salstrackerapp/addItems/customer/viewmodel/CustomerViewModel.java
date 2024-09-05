package com.examplej.salstrackerapp.addItems.customer.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.examplej.salstrackerapp.addItems.modelClass.AddProductItemModel;
import com.examplej.salstrackerapp.addItems.mvvm.ProductSaleRepository;

import java.util.List;

public class CustomerViewModel extends ViewModel {
    
    private CustomerRepository customerRepository;

    public CustomerViewModel(Application application) {
        customerRepository = new CustomerRepository(application);
    }

    public void insertCustomer(List<Customer> customerList) {
        customerRepository.insertData(customerList);
    }

    public LiveData<List<Customer>> getAllCustomer(){
        return customerRepository.getAllCustomer();
    }

}
