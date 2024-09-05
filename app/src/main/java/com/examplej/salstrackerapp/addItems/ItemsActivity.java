package com.examplej.salstrackerapp.addItems;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.examplej.salstrackerapp.R;
import com.examplej.salstrackerapp.addItems.adapter.ProductItemAdapter;
import com.examplej.salstrackerapp.addItems.modelClass.ProductItemModel;
import com.examplej.salstrackerapp.databinding.ActivityItemsBinding;
import com.google.gson.Gson;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

public class ItemsActivity extends AppCompatActivity{
    private ActivityItemsBinding binding;
    private TextView titleTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityItemsBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);

        titleTxt = binding.toolbarId.findViewById(R.id.toolbarTitle);
        titleTxt.setText(R.string.items);

        List<ProductItemModel> productItemModelList = new ArrayList<>();
        productItemModelList.add(new ProductItemModel("120","Pepsi"));
        productItemModelList.add(new ProductItemModel("150","Mirinda"));
        productItemModelList.add(new ProductItemModel("110","Sprite"));
        productItemModelList.add(new ProductItemModel("140","Thumbs Up"));
        productItemModelList.add(new ProductItemModel("40","Frooti"));
        productItemModelList.add(new ProductItemModel("2200","Hulas Jeera Masino Rice"));
        productItemModelList.add(new ProductItemModel("2300","Thakali Long Grain Rice"));
        productItemModelList.add(new ProductItemModel("80","Coffee"));
        productItemModelList.add(new ProductItemModel("400","Batteries"));
        productItemModelList.add(new ProductItemModel("45","Dish soap"));
        productItemModelList.add(new ProductItemModel("140","Popcorn"));
        productItemModelList.add(new ProductItemModel("220","Cookies"));
        productItemModelList.add(new ProductItemModel("130","Almonds"));
        productItemModelList.add(new ProductItemModel("60","Soup"));

        ProductItemAdapter productItemAdapter = new ProductItemAdapter(productItemModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ItemsActivity.this);
        binding.productItemRV.setLayoutManager(linearLayoutManager);
        binding.productItemRV.setAdapter(productItemAdapter);
        productItemAdapter.setOnProductItemClick(new ProductItemAdapter.ProductItemClick() {
            @Override
            public void onCLick(ProductItemModel productItemModel) {
                setResult(Activity.RESULT_OK, new Intent().putExtra("productItemModel", new Gson().toJson(productItemModel)));
                finish();
            }
        });

        listeners();
        setContentView(binding.getRoot());

    }

    private void listeners() {
        binding.toolbarId.findViewById(R.id.backImg).setOnClickListener(v->{
            finish();
        });
    }
}