package com.examplej.salstrackerapp.dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.examplej.salstrackerapp.R;
import com.examplej.salstrackerapp.ShareViewModel;
import com.examplej.salstrackerapp.addItems.AddItemsActivity;
import com.examplej.salstrackerapp.currentMonthDetails.MonthlyDetailsFragment;
import com.examplej.salstrackerapp.dailyDataDetail.DailyDetailDataFragment;
import com.examplej.salstrackerapp.databinding.ActivityMainBinding;
import com.examplej.salstrackerapp.sharePreference.TmkSharePreference;
import com.examplej.salstrackerapp.ui.customer.CustomerList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    int yy, mm, dd;
    List<TextView> textViewList = new ArrayList<>();
    TmkSharePreference tmkSharePreference;
    private ShareViewModel sharedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        tmkSharePreference = new TmkSharePreference(this);
        sharedViewModel = new ViewModelProvider(this).get(ShareViewModel.class);
        setContentView(binding.getRoot());
        textViewList.add(binding.thisMonthBtn);
        textViewList.add(binding.dailyBtn);
        setTextBackgroundColor(binding.dailyBtn);
        setColorToTabBackground(binding.tabRootView);
        replaceFragment(new DailyDetailDataFragment());
        listener();
        setCurrentDate();
        binding.greetingTV.setText(getWishingMessage() + "!");
        getReceiveData();

    }

    private void getReceiveData() {
        sharedViewModel.getSharedData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String amount) {
               if (!amount.isEmpty()){
                   binding.todayTotalAmountTV.setText(getString(R.string.npr) + amount);
                   binding.currentMonthTV.setText(getString(R.string.npr) + amount);
               }else {
                   binding.todayTotalAmountTV.setText(getString(R.string.npr) + "0");
                   binding.currentMonthTV.setText(getString(R.string.npr) + "0");
               }
            }
        });
    }


    public String getWishingMessage() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        String message = "";
        if (timeOfDay >= 0 && timeOfDay < 12) {
            message = "Good Morning ";
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            message = "Good Afternoon ";

        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            message = "Good Evening ";

        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            message = "Good Night ";
        }
        return message;

    }

    private void setCurrentDate() {
        final Calendar c = Calendar.getInstance();
        yy = c.get(Calendar.YEAR);
        mm = c.get(Calendar.MONTH);
        dd = c.get(Calendar.DAY_OF_MONTH);
    }

    private void listener() {
        binding.addFloatingBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddItemsActivity.class));
        });
        binding.thisMonthBtn.setOnClickListener(v -> {
            setTextBackgroundColor(binding.thisMonthBtn);
            replaceFragment(new MonthlyDetailsFragment());

        });
        binding.dailyBtn.setOnClickListener(v -> {
            setTextBackgroundColor(binding.dailyBtn);
            replaceFragment(new DailyDetailDataFragment());

        });
        binding.backBtn.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("App")
                    .setMessage("Are you sure you want exit app")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        });
    }

    private void setColorToTabBackground(View view) {
        GradientDrawable drawable = (GradientDrawable) view.getBackground();
        drawable.setColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
        ((GradientDrawable) view.getBackground()).setAlpha(20);
    }

    private void setTextBackgroundColor(TextView activeTV) {
        for (TextView view : textViewList) {
            GradientDrawable drawable = (GradientDrawable) view.getBackground();
            if (view == activeTV) {
                drawable.setColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                view.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorWhite));
            } else {
                drawable.setColor(ContextCompat.getColor(MainActivity.this, R.color.colorWhite));
                view.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorGray));
            }
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}