package com.example.otogaleri.pages.ads;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.otogaleri.models.Ilanlar;
import com.example.otogaleri.models.Iller;
import com.example.otogaleri.models.Mahalleler;
import com.example.otogaleri.models.Uyeler;
import com.example.otogaleri.restapi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdsViewModel extends ViewModel {


    public MutableLiveData<String> resultmessage;
    public MutableLiveData<String> resultilmessage;
    public MutableLiveData<String> resultIlanlarMahalleMessage;
    public MutableLiveData<String> resultIlanlarilMessage;
    public MutableLiveData<List<Ilanlar>> resultlist;
    public MutableLiveData<List<Iller>> resultillist;
    public MutableLiveData<List<Mahalleler>> resultIlanlarMahalleList;
    public MutableLiveData<List<Iller>> resultIlanlarIlList;

    public MutableLiveData<Boolean> resultiserror = new MutableLiveData<>();
    public MutableLiveData<Boolean> resultdialog = new MutableLiveData<>();

    public void ilanlarList(int sort,String baslik,String aciklama,int ilanturuid,int  kimdenid,String marka,String seri,String model,String minyil,String maxyil,String minkm,String maxkm,String motortipi,String motorHacmi,String minSurat,String maxSurat,String yakitTipi,String ortYakit,String depoHacmi,String minUcret,String maxUcret) {
        resultmessage = new MutableLiveData<>();
        resultlist = new MutableLiveData<>();
        Call<List<Ilanlar>> ilanlist = ManagerAll.getInstance().ilanlarList(sort,baslik,aciklama,ilanturuid, kimdenid,marka, seri, model, minyil, maxyil, minkm, maxkm, motortipi, motorHacmi, minSurat, maxSurat, yakitTipi, ortYakit, depoHacmi, minUcret, maxUcret);
        ilanlist.enqueue(new Callback<List<Ilanlar>>() {
            @Override
            public void onResponse(Call<List<Ilanlar>> call, Response<List<Ilanlar>> response) {
                if (response.isSuccessful()) {
                    //resultmessage bilgisini ilk kayıt get(0) dan yazarak belirttik direk ilan var bilgisini almış olduk
                    resultmessage.setValue(response.body().get(0).getresultmessage());
                    //Log.i("adsviewmodellogtest39",response.body().toString());
//                    for (Ilanlar a :response.body()){
//                        Log.i("adsviewmodellogtest39",a.toString());
//                    }
                    resultlist.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Ilanlar>> call, Throwable t) {
                resultmessage.setValue("0");
                resultlist.setValue(null);
            }
        });
    }


    public void illeridoldur() {
        resultilmessage = new MutableLiveData<>();
        resultillist = new MutableLiveData<>();
        Call<List<Iller>> il = ManagerAll.getInstance().illist();
        il.enqueue(new Callback<List<Iller>>() {
            @Override
            public void onResponse(Call<List<Iller>> call, Response<List<Iller>> response) {
                if (response.isSuccessful()) {
                    resultillist.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Iller>> call, Throwable t) {
                resultillist.setValue(null);
            }
        });
    }

    public void ilanlarlarMahalleList(int sort,String baslik,String aciklama,int ilanturuid,int  kimdenid,String marka,String seri,String model,String minyil,String maxyil,String minkm,String maxkm,String motortipi,String motorHacmi,String minSurat,String maxSurat,String yakitTipi,String ortYakit,String depoHacmi,String minUcret,String maxUcret) {
        resultIlanlarMahalleMessage = new MutableLiveData<>();
        resultIlanlarMahalleList = new MutableLiveData<>();
        Call<List<Mahalleler>> ilanlarMahallelist = ManagerAll.getInstance().ilanlarMahalleList(sort,baslik,aciklama,ilanturuid, kimdenid,marka, seri, model, minyil, maxyil, minkm, maxkm, motortipi, motorHacmi, minSurat, maxSurat, yakitTipi, ortYakit, depoHacmi, minUcret, maxUcret);
        ilanlarMahallelist.enqueue(new Callback<List<Mahalleler>>() {
            @Override
            public void onResponse(Call<List<Mahalleler>> call, Response<List<Mahalleler>> response) {
                if (response.isSuccessful()) {
                    resultIlanlarMahalleMessage.setValue(response.body().get(0).getResultMessage());
                    //Log.i("adsviewmodellogtest86",response.body().toString());
                    resultIlanlarMahalleList.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Mahalleler>> call, Throwable t) {
                resultIlanlarMahalleMessage.setValue("0");
                resultIlanlarMahalleList.setValue(null);
                Log.i("adsviewmodellogtest94",t.toString());
            }
        });
    }

    public void ilanlarlarIlList(int sort, String baslik, String aciklama, int ilanturuid, int  kimdenid,String mahalleSelected, String marka, String seri, String model, String minyil, String maxyil, String minkm, String maxkm, String motortipi, String motorHacmi, String minSurat, String maxSurat, String yakitTipi, String ortYakit, String depoHacmi, String minUcret, String maxUcret) {
//        try {
            resultIlanlarilMessage = new MutableLiveData<>();
            resultIlanlarIlList = new MutableLiveData<>();
            Call<List<Iller>> ilanlarIllist = ManagerAll.getInstance().ilanlarIlList(sort,baslik,aciklama,ilanturuid, kimdenid,mahalleSelected,marka, seri, model, minyil, maxyil, minkm, maxkm, motortipi, motorHacmi, minSurat, maxSurat, yakitTipi, ortYakit, depoHacmi, minUcret, maxUcret);
            ilanlarIllist.enqueue(new Callback<List<Iller>>() {
                @Override
                public void onResponse(Call<List<Iller>> call, Response<List<Iller>> response) {
                    if (response.isSuccessful()) {
                        //resultmessage bilgisini ilk kayıt get(0) dan yazarak belirttik direk ilan var bilgisini almış olduk
                        resultIlanlarilMessage.setValue(response.body().get(0).getResultMessage());
                        //Log.i("adsfragmentlogtest",response.body().toString());
                        resultIlanlarIlList.setValue(response.body());
                    }else{
                        //Log.i("adsviewmodellogtest113",response.body().toString());
                    }
                }
                @Override
                public void onFailure(Call<List<Iller>> call, Throwable t) {
                    resultIlanlarilMessage.setValue("0");
                    resultIlanlarIlList.setValue(null);
                    //Log.i("adsviewmodellogtest113",t.toString());
                }
            });
//        }catch (Exception e )
//        {
//            Log.i("adsviewnodellogtest104",e.toString());
//        }

    }
}