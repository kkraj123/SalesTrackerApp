package com.examplej.salstrackerapp.addItems.customer.viewmodel;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.examplej.salstrackerapp.addItems.mvvm.ProductSaleViewModel;


public class CustomerFactory extends ViewModelProvider.NewInstanceFactory {
    private Application application;

    public CustomerFactory(Application application) {
        this.application = application;
    }
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new CustomerViewModel(application);
    }
}
