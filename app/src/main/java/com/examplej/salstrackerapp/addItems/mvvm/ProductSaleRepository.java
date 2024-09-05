package com.examplej.salstrackerapp.addItems.mvvm;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.examplej.salstrackerapp.addItems.modelClass.AddProductItemModel;
import com.examplej.salstrackerapp.roomDatabase.ProductSaleDao;
import com.examplej.salstrackerapp.roomDatabase.ProductSalesDatabase;

import java.util.List;

public class ProductSaleRepository {
       private ProductSaleDao productSaleDao;

       public ProductSaleRepository(Application application){
              ProductSalesDatabase db = ProductSalesDatabase.getInstance(application);
              productSaleDao = db.productSaleDao();
       }

       public void insertData(List<AddProductItemModel> productSaleList){
              ProductSalesDatabase.databaseWriteExecutor.execute(() -> productSaleDao.insertData(productSaleList));

       }
       public LiveData<List<AddProductItemModel>> getCurrentMonthSales(String startDate, String endDate){
              return productSaleDao.getProductSaleInMonth(startDate, endDate);
       }

       public LiveData<List<AddProductItemModel>> getTodayItem(String today){
              return productSaleDao.getCurrentDay(today);
       }

       public LiveData<List<AddProductItemModel>> getSalesDataForCustomer(int custoemrId, String currentDate){
              return productSaleDao.getSalesDataForCustomer(custoemrId, currentDate);
       }

}

