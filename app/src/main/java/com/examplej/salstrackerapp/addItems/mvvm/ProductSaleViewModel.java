package com.examplej.salstrackerapp.addItems.mvvm;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.examplej.salstrackerapp.addItems.modelClass.AddProductItemModel;

import java.util.List;

public class ProductSaleViewModel extends ViewModel {
    private ProductSaleRepository productSaleRepository;

    public ProductSaleViewModel(Application application) {
        productSaleRepository = new ProductSaleRepository(application);
    }

    public void insertProductSale(List<AddProductItemModel> addProductItemModelList) {
        productSaleRepository.insertData(addProductItemModelList);
    }

    public LiveData<List<AddProductItemModel>> getMonthlyData(String startDate, String endDate){
        return productSaleRepository.getCurrentMonthSales(startDate, endDate);
    }
    public LiveData<List<AddProductItemModel>> getTodayData(String today) {
        return productSaleRepository.getTodayItem(today);
    }

    public LiveData<List<AddProductItemModel>> getSalesDataForCustomer(int customerId, String currentDate) {
        return productSaleRepository.getSalesDataForCustomer(customerId, currentDate);
    }
}
