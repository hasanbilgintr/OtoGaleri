package com.example.otogaleri.pages.veritification;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.otogaleri.models.Activate;
import com.example.otogaleri.models.Result;
import com.example.otogaleri.restapi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerificationViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    public MutableLiveData<String> resultmessage;
    public MutableLiveData<Activate> resultlist ;
    public MutableLiveData<Boolean> resultiserror = new MutableLiveData<>();
    public MutableLiveData<Boolean> resultdialog = new MutableLiveData<>();

    public void userActivate(String email,String kod) {
        try {
            resultmessage = new MutableLiveData<>();
            resultlist= new MutableLiveData<>();
            Call<Activate> useractivate = ManagerAll.getInstance().useractivate(email,kod);
            useractivate.enqueue(new Callback<Activate>() {
                @Override
                public void onResponse(Call<Activate> call, Response<Activate> response) {
                    if (response.isSuccessful()) {
                        Log.i("deneme",""+response.body().toString());
                        resultmessage.setValue(response.body().getResultmessage());
                        resultlist.setValue(response.body());
                    }else{
                        Log.i("deneme2",""+response.body().toString());
                        resultmessage.setValue(response.body().getResultmessage());
                    }
                }
                @Override
                public void onFailure(Call<Activate> call, Throwable t) {
                    Log.i("deneme3",""+t.toString());
                    resultmessage.setValue("0");
                }
            });
        }catch (Exception e){
            Log.i("deneme4",e.toString());
            resultmessage.setValue("0");
        }

    }
}