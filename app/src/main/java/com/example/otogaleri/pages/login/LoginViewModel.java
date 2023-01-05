package com.example.otogaleri.pages.login;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.otogaleri.models.Uyeler;
import com.example.otogaleri.restapi.ManagerAll;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {


    public MutableLiveData<String> resultmessage;
    public MutableLiveData<Uyeler> resultlist;
    public MutableLiveData<Boolean> resultiserror = new MutableLiveData<>();
    public MutableLiveData<Boolean> resultdialog = new MutableLiveData<>();

    public void loginuser(String kadi, String ksifre) {
        resultmessage = new MutableLiveData<>();
        resultlist= new MutableLiveData<>();
        Call<Uyeler> login = ManagerAll.getInstance().loginUser(kadi, ksifre);
        login.enqueue(new Callback<Uyeler>() {
            @Override
            public void onResponse(Call<Uyeler> call, Response<Uyeler> response) {

                if (response.isSuccessful()) {
                    if (response.body().getId() != null) {
                        resultmessage.setValue("1");

                        resultlist.setValue(response.body());
                    } else {
                        resultmessage.setValue("2");
                        resultlist.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(Call<Uyeler> call, Throwable t) {
                resultmessage.setValue("0");
            }
        });
    }
}
