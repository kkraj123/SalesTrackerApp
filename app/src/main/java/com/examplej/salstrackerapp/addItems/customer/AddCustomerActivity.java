package com.examplej.salstrackerapp.addItems.customer;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.examplej.salstrackerapp.R;
import com.examplej.salstrackerapp.addItems.customer.viewmodel.Customer;
import com.examplej.salstrackerapp.addItems.customer.viewmodel.CustomerFactory;
import com.examplej.salstrackerapp.addItems.customer.viewmodel.CustomerViewModel;
import com.examplej.salstrackerapp.addItems.mvvm.ProductSaleVMFactory;
import com.examplej.salstrackerapp.addItems.mvvm.ProductSaleViewModel;
import com.examplej.salstrackerapp.databinding.ActivityAddCustomerBinding;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AddCustomerActivity extends AppCompatActivity {


    private ActivityAddCustomerBinding binding;

    private CustomerViewModel customerVM;
    private CustomerFactory customerFactory;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCustomerBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());


        customerFactory = new CustomerFactory(getApplication());
        customerVM = new ViewModelProvider(this, customerFactory).get(CustomerViewModel.class);

        binding.saveBtn.setOnClickListener(v -> {
            List<Customer> customerList = new ArrayList<>();
            customerList.add(new Customer(binding.nameET.getText().toString(),
                    binding.addressET.getText().toString(),
                    binding.phoneET.getText().toString()));
            customerVM.insertCustomer(customerList);
            finish();
            Toast.makeText(this, "Customer Added Successfully", Toast.LENGTH_SHORT).show();
        });
        binding.toolbar.findViewById(R.id.backImg).setOnClickListener(v -> {
            finish();
        });
    }
}