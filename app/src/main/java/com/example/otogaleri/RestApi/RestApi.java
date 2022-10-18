package com.example.otogaleri.RestApi;


import com.example.otogaleri.Models.Uyeler;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestApi {


    //region LoginPage
    @FormUrlEncoded
    @POST("/OtoGaleri/LoginPage/login.php")
    Call<Uyeler> loginUser(@Field("kullaniciadi") String kadi, @Field("kullanicisifre") String ksifre);

    //endregion



}
