package com.examplej.salstrackerapp.dailyDataDetail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.examplej.salstrackerapp.R;
import com.examplej.salstrackerapp.ShareViewModel;
import com.examplej.salstrackerapp.addItems.modelClass.AddProductItemModel;
import com.examplej.salstrackerapp.addItems.mvvm.ProductSaleVMFactory;
import com.examplej.salstrackerapp.addItems.mvvm.ProductSaleViewModel;
import com.examplej.salstrackerapp.dailyDataDetail.adapter.TodayDataAdapter;
import com.examplej.salstrackerapp.databinding.FragmentDailyDetailDataBinding;
import com.examplej.salstrackerapp.sharePreference.TmkSharePreference;
import com.examplej.salstrackerapp.utility.Utility;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DailyDetailDataFragment extends Fragment {
    private FragmentDailyDetailDataBinding binding;
    private ProductSaleViewModel productSaleViewModel;
    private ProductSaleVMFactory productSaleVMFactory;
    String currentDate;
    private TodayDataAdapter todayDataAdapter;
    private TmkSharePreference tmkSharePreference;
    int amount;
    ShareViewModel shareViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDailyDetailDataBinding.inflate(inflater, container, false);
        tmkSharePreference = new TmkSharePreference(requireActivity());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        currentDate = simpleDateFormat.format(date);
        shareViewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);

        productSaleVMFactory = new ProductSaleVMFactory(requireActivity().getApplication());
        productSaleViewModel = new ViewModelProvider(this, productSaleVMFactory).get(ProductSaleViewModel.class);
        productSaleViewModel.getTodayData(currentDate).observe(requireActivity(), todayData -> {
            if (!todayData.isEmpty()) {
                binding.todayRV.setVisibility(View.VISIBLE);
                binding.noDataFound.setVisibility(View.GONE);
                todayDataAdapter = new TodayDataAdapter(todayData);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity());
                binding.todayRV.setLayoutManager(linearLayoutManager);
                binding.todayRV.setAdapter(todayDataAdapter);
                for (int i = 0; i < todayData.size(); i++) {
                    amount += Integer.parseInt(todayData.get(i).getAmount());
                }
                int totalAmount = amount;
                shareViewModel.setSharedData(String.valueOf(totalAmount));


            } else {
                binding.noDataFound.setVisibility(View.VISIBLE);
                binding.todayRV.setVisibility(View.GONE);
            }
        });
        return binding.getRoot();
    }
}