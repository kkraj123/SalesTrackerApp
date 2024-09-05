package com.examplej.salstrackerapp.currentMonthDetails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.examplej.salstrackerapp.R;
import com.examplej.salstrackerapp.ShareViewModel;
import com.examplej.salstrackerapp.addItems.modelClass.AddProductItemModel;
import com.examplej.salstrackerapp.addItems.mvvm.ProductSaleVMFactory;
import com.examplej.salstrackerapp.addItems.mvvm.ProductSaleViewModel;
import com.examplej.salstrackerapp.currentMonthDetails.adapter.MonthlyDetailAdapter;
import com.examplej.salstrackerapp.databinding.FragmentMonthlyDetailsBinding;
import com.examplej.salstrackerapp.utility.Utility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MonthlyDetailsFragment extends Fragment {

    private FragmentMonthlyDetailsBinding binding;
    private ProductSaleViewModel productSaleViewModel;
    private ProductSaleVMFactory productSaleVMFactory;
    private MonthlyDetailAdapter monthlyDetailAdapter;
    ShareViewModel shareViewModel;
    int monthlyTotalAmount;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMonthlyDetailsBinding.inflate(inflater, container, false);
        productSaleVMFactory = new ProductSaleVMFactory(requireActivity().getApplication());
        productSaleViewModel = new ViewModelProvider(this, productSaleVMFactory).get(ProductSaleViewModel.class);
        observeData();
        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);

        return binding.getRoot();
    }

    private void observeData() {
        productSaleViewModel.getMonthlyData(Utility.getStartOfMonth(), Utility.getEndOfMonth()).observe(requireActivity(), salesInMonth ->{
            if (!salesInMonth.isEmpty()){
                binding.monthlyRV.setVisibility(View.VISIBLE);
                binding.noDataFound.setVisibility(View.GONE);
                monthlyDetailAdapter = new MonthlyDetailAdapter(salesInMonth);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity());
                binding.monthlyRV.setLayoutManager(linearLayoutManager);
                binding.monthlyRV.setAdapter(monthlyDetailAdapter);

                for (int i = 0; i < salesInMonth.size(); i++){
                    monthlyTotalAmount += Integer.parseInt(salesInMonth.get(i).getAmount());
                }
                shareViewModel.setSharedData(String.valueOf(monthlyTotalAmount));
            }else {
                binding.monthlyRV.setVisibility(View.GONE);
                binding.noDataFound.setVisibility(View.VISIBLE);
            }
        });
    }
}