package com.example.otogaleri.pages.newuser;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.otogaleri.models.Result;
import com.example.otogaleri.models.Uyeler;
import com.example.otogaleri.restapi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewUserViewModel extends ViewModel {

    public MutableLiveData<String> resultmessage;
    public MutableLiveData<Result> resultlist ;
    public MutableLiveData<Boolean> resultiserror = new MutableLiveData<>();
    public MutableLiveData<Boolean> resultdialog = new MutableLiveData<>();

    public void createuser(String kadi,String email,String ksifre,String appuserid) {
        try {
            resultmessage = new MutableLiveData<>();
            resultlist= new MutableLiveData<>();
            Call<Result> createuser = ManagerAll.getInstance().createuser(kadi,email,ksifre,appuserid);
            createuser.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    if (response.isSuccessful()) {
                            resultmessage.setValue(response.body().getResultmessage());
                            resultlist.setValue(response.body());
                    }else{
                        resultmessage.setValue(response.body().getResultmessage());
                    }
                }
                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    Log.i("deneme",""+t.toString());
                    resultmessage.setValue("0");
                }
            });


        }catch (Exception e){
            Log.i("e",e.toString());
            resultmessage.setValue("0");
        }

    }
}
