package com.example.otogaleri.restapi;

import android.util.Log;

import com.example.otogaleri.models.Activate;
import com.example.otogaleri.models.AdDetailModel;
import com.example.otogaleri.models.IlanVer;
import com.example.otogaleri.models.Ilanlar;
import com.example.otogaleri.models.Iller;
import com.example.otogaleri.models.Mahalleler;
import com.example.otogaleri.models.Result;
import com.example.otogaleri.models.SliderModel;
import com.example.otogaleri.models.Uyeler;


import java.util.List;

import retrofit2.Call;

public class ManagerAll extends BaseManager {
    private static ManagerAll ourInstance = new ManagerAll();

    public static synchronized ManagerAll getInstance() {
        return ourInstance;
    }

    public Call<Uyeler> loginUser(String kullaniciadi, String kullanicisifre) {
        Call<Uyeler> a = getRestApi().loginUser(kullaniciadi, kullanicisifre);
        return a;

    }

    public Call<Result> createuser(String kullaniciadi,String kullaniciemail, String kullanicisifre,String appuserid) {
        Call<Result> a = getRestApi().createuser(kullaniciadi,kullaniciemail,kullanicisifre,appuserid);
        return a;
    }

    public Call<Activate> useractivate(String kullaniciemail, String kod) {
        Call<Activate> a = getRestApi().useractivate(kullaniciemail,kod);
        return a;
    }

    public Call<List<Iller>> illist() {
        Call<List<Iller>> a = getRestApi().illist();
        return a;
    }

    public Call<IlanVer> ilanverkayit(String kullanici,int sehirid,String ilce,String mahalle,String marka,String seri,String model,String yil,String km,String ilantipi,String kimden,String baslik,String aciklama,String motortipi,String motorhacmi,String surat,String yakittipi,String ortalamayakit,String depohacmi,String ucret) {
        Call<IlanVer> a = getRestApi().ilanverkayit(kullanici, sehirid,ilce,mahalle,marka,seri,model, yil, km, ilantipi, kimden, baslik, aciklama, motortipi, motorhacmi, surat, yakittipi, ortalamayakit, depohacmi,ucret);
        return a;
    }

    public Call<Result> resimEkle(String uyeid, String ilanid,String base64StringResim) {
        Call<Result> a = getRestApi().resimEkle(uyeid,ilanid,base64StringResim);
        return a;
    }

    public Call<List<Ilanlar>> ilanlarList(int sort,String baslik,String aciklama,int ilanturuid,int  kimdenid,String marka,String seri,String model,String minyil,String maxyil,String minkm,String maxkm,String motortipi,String motorHacmi,String minSurat,String maxSurat,String yakitTipi,String ortYakit,String depoHacmi,String minUcret,String maxUcret) {
        Call<List<Ilanlar>>  a = getRestApi().ilanlarlist(sort,baslik, aciklama, ilanturuid,  kimdenid, marka, seri, model, minyil, maxyil, minkm, maxkm,motortipi, motorHacmi, minSurat, maxSurat, yakitTipi, ortYakit, depoHacmi, minUcret, maxUcret);
        return a;
    }

    public Call<List<Mahalleler>> ilanlarMahalleList(int sort, String baslik, String aciklama, int ilanturuid, int  kimdenid, String marka, String seri, String model, String minyil, String maxyil, String minkm, String maxkm, String motortipi, String motorHacmi, String minSurat, String maxSurat, String yakitTipi, String ortYakit, String depoHacmi, String minUcret, String maxUcret) {
        Call<List<Mahalleler>>  a = getRestApi().ilanlarMahallelist(sort,baslik, aciklama, ilanturuid,  kimdenid, marka, seri, model, minyil, maxyil, minkm, maxkm,motortipi, motorHacmi, minSurat, maxSurat, yakitTipi, ortYakit, depoHacmi, minUcret, maxUcret);
        return a;
    }
      public Call<List<Iller>> ilanlarIlList(int sort, String baslik, String aciklama, int ilanturuid, int  kimdenid, String mahalleSelected, String marka, String seri, String model, String minyil, String maxyil, String minkm, String maxkm, String motortipi, String motorHacmi, String minSurat, String maxSurat, String yakitTipi, String ortYakit, String depoHacmi, String minUcret, String maxUcret) {


        Call<List<Iller>>  a = getRestApi().ilanlarIllist(sort,baslik, aciklama, ilanturuid,  kimdenid,mahalleSelected, marka, seri, model, minyil, maxyil, minkm, maxkm,motortipi, motorHacmi, minSurat, maxSurat, yakitTipi, ortYakit, depoHacmi, minUcret, maxUcret);
        return a;
    }

    public Call<AdDetailModel> adDetail(String ilanid) {
        //Log.i("logtestmanareall",ilanid);
        Call<AdDetailModel> adDetailModelCall = getRestApi().adDetail(ilanid);
        return adDetailModelCall;
    }

    public Call<List<SliderModel>> adDetailPictures(String ilanid) {

        Call<List<SliderModel>> x = getRestApi().adDetailPictures(ilanid);
        return x;
    }









}
