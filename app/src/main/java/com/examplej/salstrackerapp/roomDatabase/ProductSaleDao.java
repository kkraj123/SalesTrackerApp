package com.examplej.salstrackerapp.roomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.examplej.salstrackerapp.addItems.modelClass.AddProductItemModel;

import java.util.List;

@Dao
public interface ProductSaleDao {

    @Insert
    void insertData(List<AddProductItemModel> productItemModellist);

    @Query("SELECT * FROM sales_list WHERE date BETWEEN :startDate AND :endDate")
    LiveData<List<AddProductItemModel>> getProductSaleInMonth(String startDate, String endDate);

    @Query("SELECT * FROM sales_list WHERE date = :today")
    LiveData<List<AddProductItemModel>> getCurrentDay(String today);


    @Query("SELECT * FROM sales_list WHERE customerId = :customerId And date = :currentDate")
    LiveData<List<AddProductItemModel>> getSalesDataForCustomer(int customerId, String currentDate);
}
