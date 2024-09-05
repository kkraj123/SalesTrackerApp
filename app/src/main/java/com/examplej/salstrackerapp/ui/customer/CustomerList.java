package com.examplej.salstrackerapp.ui.customer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.examplej.salstrackerapp.R;
import com.examplej.salstrackerapp.addItems.customer.adapter.CustomerAdapter;
import com.examplej.salstrackerapp.addItems.customer.interfaces.ClickListener;
import com.examplej.salstrackerapp.addItems.customer.viewmodel.Customer;
import com.examplej.salstrackerapp.addItems.customer.viewmodel.CustomerFactory;
import com.examplej.salstrackerapp.addItems.customer.viewmodel.CustomerViewModel;
import com.examplej.salstrackerapp.databinding.FragmentCustomerListBinding;
import com.examplej.salstrackerapp.databinding.FragmentDailyDetailDataBinding;
import com.examplej.salstrackerapp.ui.CustomerRecordDetailsView;
import com.examplej.salstrackerapp.ui.customer.adapter.CustomerListAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomerList extends Fragment {

    private CustomerViewModel customerVM;
    private CustomerFactory customerFactory;

    private List<Customer> customerList = new ArrayList<>();

    CustomerListAdapter customerAdapter = new CustomerListAdapter(customerList);

    private FragmentCustomerListBinding binding;

    public CustomerList() {

    }

    public static CustomerList newInstance() {
        return new CustomerList();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCustomerListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        customerFactory = new CustomerFactory(requireActivity().getApplication());
        customerVM = new ViewModelProvider(this, customerFactory).get(CustomerViewModel.class);

        configureCustomerRV();
        loadCustomerFromDB();

    }

    private void configureCustomerRV() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity());
        binding.customerRV.setLayoutManager(layoutManager);
        binding.customerRV.setAdapter(customerAdapter);
        customerAdapter.setOnClickListener(new ClickListener() {
            @Override
            public void clickListener(int position) {
                Customer customer = customerList.get(position);
                Intent intent = new Intent(requireActivity(), CustomerRecordDetailsView.class);
                intent.putExtra("customer", customer);
                startActivity(intent);
            }
        });
    }

    private void loadCustomerFromDB() {
        customerVM.getAllCustomer().observe(requireActivity(), customers -> {
            customerList = customers;
            customerAdapter.updateCustomer(customers);
        });
    }

}