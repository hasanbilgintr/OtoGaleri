package com.example.otogaleri.pages.myadsimage;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.otogaleri.models.IlanVer;
import com.example.otogaleri.models.Result;
import com.example.otogaleri.models.Uyeler;
import com.example.otogaleri.restapi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyadsImageViewModel extends ViewModel {


    public MutableLiveData<String> resultmessage;
    public MutableLiveData<String> resultmessage2;
    public MutableLiveData<IlanVer> resultlist;
    public MutableLiveData<Result> resultlist2;
    public MutableLiveData<Boolean> resultiserror = new MutableLiveData<>();
    public MutableLiveData<Boolean> resultdialog = new MutableLiveData<>();

    public void ilanver(String kullaniciadi, int sehirid, String ilce, String mahalle, String marka, String seri, String model, String yil, String km, String ilantipi, String kimden, String baslik, String aciklama, String motortipi, String motorhacmi, String surat, String yakittipi, String ortalamayakit, String depohacmi,String ucret) {
        try {
            resultmessage = new MutableLiveData<>();
            resultlist=new MutableLiveData<>();
            Call<IlanVer> ilanver = ManagerAll.getInstance().ilanverkayit(kullaniciadi, sehirid, ilce, mahalle, marka, seri, model, yil, km, ilantipi, kimden, baslik, aciklama, motortipi, motorhacmi, surat, yakittipi, ortalamayakit, depohacmi,ucret);
            ilanver.enqueue(new Callback<IlanVer>() {
                @Override
                public void onResponse(Call<IlanVer> call, Response<IlanVer> response) {
                    if (response.isSuccessful()) {
                        resultmessage.setValue(response.body().getresultmessage());
                        Log.i("log35",""+response.body());
                        resultlist.setValue(response.body());
                    } else {
                        resultmessage.setValue(null);
                        resultlist.setValue(null);
                    }
                }
                @Override
                public void onFailure(Call<IlanVer> call, Throwable t) {
                    resultmessage.setValue(null);
                    resultlist.setValue(null);
                }
            });
        } catch (Exception e) {
            //Log.i("denene11",e.toString());
            resultmessage.setValue(null);
            resultlist.setValue(null);
        }
    }

    public void resimEkle(String uyeid, String ilanid, String resim) {
        resultmessage2 = new MutableLiveData<>();

        Call<Result> resimekle = ManagerAll.getInstance().resimEkle(uyeid, ilanid, resim);
        resimekle.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    resultmessage2.setValue(response.body().getResultmessage());

                }else{
                    resultmessage2.setValue(response.body().getResultmessage());

                }
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                resultmessage2.setValue(null);

            }
        });
    }
}