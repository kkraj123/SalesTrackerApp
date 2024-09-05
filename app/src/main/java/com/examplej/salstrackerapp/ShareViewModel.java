package com.examplej.salstrackerapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShareViewModel extends ViewModel {
    private MutableLiveData<String> sharedData;

    public ShareViewModel() {
        sharedData = new MutableLiveData<>();
    }

    public LiveData<String> getSharedData() {
        return sharedData;
    }

    public void setSharedData(String data) {
        sharedData.setValue(data);
    }
}
