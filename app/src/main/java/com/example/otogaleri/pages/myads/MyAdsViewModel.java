package com.example.otogaleri.pages.myads;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.otogaleri.models.Iller;
import com.example.otogaleri.restapi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyAdsViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    public MutableLiveData<String> resultmessage;
    public MutableLiveData<List<Iller>> resultlist;
    public MutableLiveData<Boolean> resultiserror = new MutableLiveData<>();
    public MutableLiveData<Boolean> resultdialog = new MutableLiveData<>();

    public void illeridoldur() {
        resultmessage = new MutableLiveData<>();
        resultlist = new MutableLiveData<>();
        Call<List<Iller>> il = ManagerAll.getInstance().illist();
        il.enqueue(new Callback<List<Iller>>() {
            @Override
            public void onResponse(Call<List<Iller>> call, Response<List<Iller>> response) {
                if (response.isSuccessful()) {

                    resultlist.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Iller>> call, Throwable t) {
                resultlist.setValue(null);
            }
        });

    }

}