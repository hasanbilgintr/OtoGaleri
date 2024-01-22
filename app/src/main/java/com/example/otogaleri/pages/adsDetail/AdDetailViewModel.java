package com.example.otogaleri.pages.adsDetail;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.otogaleri.models.AdDetailModel;
import com.example.otogaleri.models.SliderModel;
import com.example.otogaleri.models.Uyeler;
import com.example.otogaleri.restapi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdDetailViewModel extends ViewModel {
    public MutableLiveData<String> resultmessage;
    public MutableLiveData<AdDetailModel> resultDetail;
    public MutableLiveData<List<SliderModel>> resultDetailPicturesList;
    public MutableLiveData<Uyeler> resultlist;
    public MutableLiveData<Boolean> resultiserror = new MutableLiveData<>();
    public MutableLiveData<Boolean> resultdialog = new MutableLiveData<>();

    public void adDetail(String ilanid) {
        resultDetail = new MutableLiveData<>();
        Call<AdDetailModel> adDetail = ManagerAll.getInstance().adDetail(ilanid);
        adDetail.enqueue(new Callback<AdDetailModel>() {
            @Override
            public void onResponse(Call<AdDetailModel> call, Response<AdDetailModel> response) {
                if (response.isSuccessful()) {
                    Log.i("deneme", response.body().toString());

                    switch (response.body().getIlantipi()) {
                        case "1":
                            response.body().setIlantipi("Satılık");
                            break;
                        case "2":
                            response.body().setIlantipi("Kiralık");
                            break;
                        case "3":
                            response.body().setIlantipi("Teklif");
                            break;
                        case "4":
                            response.body().setIlantipi("Açık Arttırma");
                            break;
                        default:
                            break;
                    }
                    switch (response.body().getKimden()) {
                        case "1":
                            response.body().setKimden("Sahibinden");
                            break;
                        case "2":
                            response.body().setKimden("Galeriden");
                            break;
                        case "3":
                            response.body().setKimden("Yetkili Bayi");
                            break;
                        default:
                            break;
                    }
                    resultDetail.setValue(response.body());
                } else {
                    resultDetail.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<AdDetailModel> call, Throwable t) {
                resultDetail.setValue(null);
            }
        });
    }

    public  void  adDetailPictures(String ilanid){
        resultDetailPicturesList = new MutableLiveData<>();
        Call<List<SliderModel>> adDetailPictures=ManagerAll.getInstance().adDetailPictures(ilanid);
        adDetailPictures.enqueue(new Callback<List<SliderModel>>() {
            @Override
            public void onResponse(Call<List<SliderModel>> call, Response<List<SliderModel>> response) {
                if(response.isSuccessful()){
                    resultDetailPicturesList.setValue(response.body());
                }else{
                    resultDetailPicturesList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<SliderModel>> call, Throwable t) {
                resultDetailPicturesList.setValue(null);
            }
        });
    }
}
