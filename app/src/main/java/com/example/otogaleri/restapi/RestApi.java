package com.example.otogaleri.restapi;


import com.example.otogaleri.constants.Constants;
import com.example.otogaleri.models.Activate;
import com.example.otogaleri.models.AdDetailModel;
import com.example.otogaleri.models.IlanVer;
import com.example.otogaleri.models.Ilanlar;
import com.example.otogaleri.models.Iller;
import com.example.otogaleri.models.Mahalleler;
import com.example.otogaleri.models.Result;
import com.example.otogaleri.models.SliderModel;
import com.example.otogaleri.models.Uyeler;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApi {


    //region LoginPage
    @FormUrlEncoded
    @POST(Constants.loginFile)
    Call<Uyeler> loginUser(@Field("kullaniciadi") String kadi, @Field("kullanicisifre") String ksifre);
    //endregion

    //region NewUserPage
    @FormUrlEncoded
    @POST(Constants.registerFile)
    Call<Result> createuser(@Field("kadi") String kadi, @Field("kemail") String kemail, @Field("ksifre") String ksifre, @Field("appuserid") String appuserid);
    //endregion

    //region Verification
    @FormUrlEncoded
    @POST(Constants.activateFile)
    Call<Activate> useractivate(@Field("kemail") String email, @Field("kod") String kod);
    //endregion

    //region citylist
    @POST(Constants.cityListFile)
    Call<List<Iller>> illist();
    //endregion

    //region ilanVer
    @FormUrlEncoded//bu parametere gönderken ŞARRTTTT
    @POST(Constants.advertiseFile)
    Call<IlanVer> ilanverkayit(@Field("kullaniciadi") String kullaniciadi, @Field("sehirid") int sehirid, @Field("ilce") String ilce, @Field("mahalle") String mahalle, @Field("marka") String marka, @Field("seri") String seri, @Field("model") String model, @Field("yil") String yil, @Field("km") String km, @Field("ilantipi") String ilantipi, @Field("kimden") String kimden, @Field("baslik") String baslik, @Field("aciklama") String aciklama, @Field("motortipi") String motortipi, @Field("motorhacmi") String motorhacmi, @Field("surat") String surat, @Field("yakittipi") String yakittipi, @Field("ortalamayakit") String ortalamayakit, @Field("depohacmi") String depohacmi, @Field("ucret") String ucret);
    //endregion

    //region resimEkle
    @FormUrlEncoded
    @POST(Constants.adPicturesFile)
    Call<Result> resimEkle(@Field("uye_id") String uyeid, @Field("ilan_id") String ilanid, @Field("resim") String base64StringResim);
    //endregion

    //region ilanlarlistesi
    @FormUrlEncoded
    @POST(Constants.adsFile)
    Call<List<Ilanlar>> ilanlarlist(@Field("sort") int sort, @Field("baslik") String ilanbaslik, @Field("aciklama") String ilanaciklama, @Field("ilanTipi") int ilanturuid, @Field("kimden") int kimdenid, @Field("marka") String marka, @Field("seri") String seri, @Field("model") String model, @Field("minYil") String minyil, @Field("maxYil") String maxyil, @Field("minKm") String minkm, @Field("maxKm") String maxkm, @Field("motorTipi") String motortipi, @Field("motorHacmi") String motorHacmi, @Field("minSurat") String minSurat, @Field("maxSurat") String maxSurat, @Field("yakitTipi") String yakitTipi, @Field("ortYakit") String ortYakit, @Field("depoHacmi") String depoHacmi, @Field("minUcret") String minUcret, @Field("maxUcret") String maxUcret);
    //endregion

    //region ilanlarMahalleListesi
    @FormUrlEncoded
    @POST(Constants.adsNeighbourhood)
    Call<List<Mahalleler>> ilanlarMahallelist(@Field("sort") int sort, @Field("baslik") String ilanbaslik, @Field("aciklama") String ilanaciklama, @Field("ilanTipi") int ilanturuid, @Field("kimden") int kimdenid, @Field("marka") String marka, @Field("seri") String seri, @Field("model") String model, @Field("minYil") String minyil, @Field("maxYil") String maxyil, @Field("minKm") String minkm, @Field("maxKm") String maxkm, @Field("motorTipi") String motortipi, @Field("motorHacmi") String motorHacmi, @Field("minSurat") String minSurat, @Field("maxSurat") String maxSurat, @Field("yakitTipi") String yakitTipi, @Field("ortYakit") String ortYakit, @Field("depoHacmi") String depoHacmi, @Field("minUcret") String minUcret, @Field("maxUcret") String maxUcret);
    //endregion

    //region ilanlarIlListesi
    @FormUrlEncoded
    @POST(Constants.adsCityFile)
    Call<List<Iller>> ilanlarIllist(@Field("sort") int sort, @Field("baslik") String ilanbaslik, @Field("aciklama") String ilanaciklama, @Field("ilanTipi") int ilanturuid, @Field("kimden") int kimden,@Field("mahalleSelected") String mahalleSelected,@Field("marka") String marka, @Field("seri") String seri, @Field("model") String model, @Field("minYil") String minyil, @Field("maxYil") String maxyil, @Field("minKm") String minkm, @Field("maxKm") String maxkm, @Field("motorTipi") String motortipi, @Field("motorHacmi") String motorHacmi, @Field("minSurat") String minSurat, @Field("maxSurat") String maxSurat, @Field("yakitTipi") String yakitTipi, @Field("ortYakit") String ortYakit, @Field("depoHacmi") String depoHacmi, @Field("minUcret") String minUcret, @Field("maxUcret") String maxUcret);
    //endregion

    //region ilandetaybilgisi
    @GET(Constants.adDetailFile)
    Call<AdDetailModel> adDetail(@Query("ilanid") String ilanid);
    //endregion

    //region ilandetayresimleri
    @GET(Constants.adDetailPicturesFile)
    Call<List<SliderModel>> adDetailPictures(@Query("ilanid") String ilanid);
    //endregion

}
