package com.examplej.salstrackerapp.addItems;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.examplej.salstrackerapp.R;
import com.examplej.salstrackerapp.addItems.adapter.AddProductItemAdapter;
import com.examplej.salstrackerapp.addItems.customer.AddCustomerActivity;
import com.examplej.salstrackerapp.addItems.customer.adapter.CustomerAdapter;
import com.examplej.salstrackerapp.addItems.customer.interfaces.ClickListener;
import com.examplej.salstrackerapp.addItems.customer.viewmodel.Customer;
import com.examplej.salstrackerapp.addItems.customer.viewmodel.CustomerFactory;
import com.examplej.salstrackerapp.addItems.customer.viewmodel.CustomerViewModel;
import com.examplej.salstrackerapp.addItems.modelClass.AddProductItemModel;
import com.examplej.salstrackerapp.addItems.modelClass.ProductItemModel;
import com.examplej.salstrackerapp.addItems.mvvm.ProductSaleVMFactory;
import com.examplej.salstrackerapp.addItems.mvvm.ProductSaleViewModel;
import com.examplej.salstrackerapp.dashboard.MainActivity;
import com.examplej.salstrackerapp.databinding.ActivityAddItemsBinding;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddItemsActivity extends AppCompatActivity {

    private ActivityAddItemsBinding binding;
    private TextView titleText;
    private ProductItemModel productItemModel;
    private int totalQuantity = 0;
    private int totalAmount;
    private String productName;
    List<AddProductItemModel> addProductItemModelList;
    AddProductItemAdapter addProductItemAdapter;
    AddProductItemModel addProductItemModel;
    String currentDate;
    String customerName;
    private ProductSaleViewModel productSaleViewModel;
    private ProductSaleVMFactory productSaleVMFactory;


    private CustomerViewModel customerVM;
    private CustomerFactory customerFactory;

    private List<Customer> customerList = new ArrayList<>();

    CustomerAdapter customerAdapter = new CustomerAdapter(customerList);

    private Customer selectedCustomer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddItemsBinding.inflate(getLayoutInflater());

        productSaleVMFactory = new ProductSaleVMFactory(getApplication());
        productSaleViewModel = new ViewModelProvider(this, productSaleVMFactory).get(ProductSaleViewModel.class);

        customerFactory = new CustomerFactory(getApplication());
        customerVM = new ViewModelProvider(this, customerFactory).get(CustomerViewModel.class);


        EdgeToEdge.enable(this);
        titleText = binding.toolbarId.findViewById(R.id.toolbarTitle);
        titleText.setText(R.string.add_item);
        addProductItemModelList = new ArrayList<>();
        addProductItemAdapter = new AddProductItemAdapter(addProductItemModelList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(AddItemsActivity.this);
        binding.addProductRV.setLayoutManager(layoutManager);
        binding.addProductRV.setAdapter(addProductItemAdapter);
        setContentView(binding.getRoot());
        if (productItemModel == null) {
            binding.itemDetailView.setVisibility(View.GONE);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        currentDate = simpleDateFormat.format(date);
        customerName = binding.customerET.getText().toString();
        clickListener();
        configureCustomerRV();
        loadCustomerFromDB();
    }


    private void configureCustomerRV() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.customerRV.setLayoutManager(layoutManager);
        binding.customerRV.setAdapter(customerAdapter);
        customerAdapter.setOnClickListener(new ClickListener() {
            @Override
            public void clickListener(int position) {
                selectedCustomer = customerList.get(position);
                binding.customerET.setText(selectedCustomer.getName());
            }
        });
    }

    private void loadCustomerFromDB() {
        customerVM.getAllCustomer().observe(this, customers -> {
            if (!customers.isEmpty()){
                customerList = customers;
                customerAdapter.updateCustomer(customers);
                binding.customerRV.setVisibility(View.VISIBLE);
            }
            else {
                binding.customerRV.setVisibility(View.GONE);
            }
        });
    }

    private void clickListener() {

        binding.addCustomerTV.setOnClickListener(v -> {
            startActivity(new Intent(this, AddCustomerActivity.class));

        });

        binding.submitBtn.setOnClickListener(v -> {
            if (!addProductItemModelList.isEmpty()) {
                productSaleViewModel.insertProductSale(addProductItemModelList);
                Toast.makeText(this, "Sale date added success", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        binding.addItemBtn.setOnClickListener(v -> {
            binding.customerET.getText().toString();
            if (selectedCustomer != null) {
                addProductItemModel = new AddProductItemModel(productName,
                        totalQuantity,
                        String.valueOf(totalAmount),
                        currentDate,
                        customerName,
                        selectedCustomer.getId());
            }

            addProductItemAdapter.addItem(addProductItemModel);
            binding.itemDetailView.setVisibility(View.GONE);
            binding.addItemLayout.setVisibility(View.VISIBLE);
        });

        binding.toolbarId.findViewById(R.id.backImg).setOnClickListener(v -> {
            finish();
        });
        binding.addQuantityIV.setOnClickListener(v -> {
            totalQuantity = Integer.parseInt(binding.quantityCountTV.getText().toString());
            totalQuantity++;
            binding.quantityCountTV.setText(String.valueOf(totalQuantity));
            totalAmount = (totalQuantity * Integer.parseInt(productItemModel.getAmount()));
            binding.totalAmountTV.setText(getString(R.string.npr) + totalAmount);
        });

        binding.decreaseQuantity.setOnClickListener(v -> {
            totalQuantity = Integer.parseInt(binding.quantityCountTV.getText().toString());
            if (totalQuantity > 1) {
                totalQuantity--;
                binding.quantityCountTV.setText(String.valueOf(totalQuantity));
                totalAmount = (totalQuantity * Integer.parseInt(productItemModel.getAmount()));
                binding.totalAmountTV.setText(getString(R.string.npr) + totalAmount);
            }
        });

        binding.addItemFAB.setOnClickListener(v -> {
            if (!binding.customerET.getText().toString().isEmpty()) {
                Intent intent = new Intent(AddItemsActivity.this, ItemsActivity.class);
                productActivityLaunch.launch(intent);
            } else {
                binding.customerET.setError("Please select customer");
            }
        });
    }

    @SuppressLint("SetTextI18n")
    ActivityResultLauncher<Intent> productActivityLaunch = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            if (data != null) {
                binding.itemDetailView.setVisibility(View.VISIBLE);
                productItemModel = new Gson().fromJson(data.getStringExtra("productItemModel"), ProductItemModel.class);
                if (productItemModel != null) {
                    productName = productItemModel.getProductName();
                    totalAmount = Integer.parseInt(productItemModel.getAmount());
                    totalQuantity = Integer.parseInt(binding.quantityCountTV.getText().toString());
                    customerName = binding.customerET.getText().toString();
                    binding.productNameTV.setText(productItemModel.getProductName());
                    binding.amountTV.setText(getString(R.string.npr) + productItemModel.getAmount());
                    binding.totalAmountTV.setText(getString(R.string.npr) + productItemModel.getAmount());
                    binding.dateTV.setText(currentDate);
                    if (totalQuantity > 1) {
                        binding.quantityCountTV.setText("1");
                    }
                }

            }
        }
    });


}