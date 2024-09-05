package com.examplej.salstrackerapp.roomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.examplej.salstrackerapp.addItems.customer.viewmodel.Customer;
import com.examplej.salstrackerapp.addItems.modelClass.AddProductItemModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {AddProductItemModel.class, Customer.class}, version = 1)
public abstract class ProductSalesDatabase extends RoomDatabase {

    public abstract ProductSaleDao productSaleDao();
    public abstract CustomerDao customerDao();

    private static final int NUMBER_OF_THREADS = 4;

    private static volatile ProductSalesDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ProductSalesDatabase getInstance(Context context){
        if(INSTANCE == null){
            synchronized (ProductSalesDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProductSalesDatabase.class,"sales_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
