package com.examplej.salstrackerapp.addItems.mvvm;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ProductSaleVMFactory extends ViewModelProvider.NewInstanceFactory {
   private Application application;

    public ProductSaleVMFactory(Application application) {
        this.application = application;
    }
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new ProductSaleViewModel(application);
    }
}
