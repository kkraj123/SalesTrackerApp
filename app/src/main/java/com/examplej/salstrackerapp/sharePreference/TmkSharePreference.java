package com.examplej.salstrackerapp.sharePreference;

import android.content.Context;
import android.content.SharedPreferences;

public class TmkSharePreference {
    Context context;
    public static final String SHARED_NAME = "tmkPreference";
    private final SharedPreferences mSharedPreferences;
    private final String TODAY_AMOUNT = "today_amount";

    public TmkSharePreference(Context context) {
        this.context = context;
        mSharedPreferences = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
    }

    public void setTodayAmount(String totalAmount) {
        mSharedPreferences.edit().putString(TODAY_AMOUNT, totalAmount).apply();
    }

    public String getTodayAmount() {
        return mSharedPreferences.getString(TODAY_AMOUNT, null);
    }

}
