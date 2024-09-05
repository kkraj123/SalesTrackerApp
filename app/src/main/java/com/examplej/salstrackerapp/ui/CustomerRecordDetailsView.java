package com.examplej.salstrackerapp.ui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.examplej.salstrackerapp.R;
import com.examplej.salstrackerapp.addItems.customer.viewmodel.Customer;
import com.examplej.salstrackerapp.dailyDataDetail.DailyDetailDataFragment;
import com.examplej.salstrackerapp.databinding.ActivityCustomerRecordDetailsViewBinding;

public class CustomerRecordDetailsView extends AppCompatActivity {

    private ActivityCustomerRecordDetailsViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerRecordDetailsViewBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        Customer customer = (Customer) getIntent().getSerializableExtra("customer");
        /*if (customer != null){
            replaceFragment(DailyDetailDataFragment.newInstance( customer));
        }*/
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}