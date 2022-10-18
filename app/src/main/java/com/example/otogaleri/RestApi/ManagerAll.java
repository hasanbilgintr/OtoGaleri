package com.example.otogaleri.RestApi;

import android.util.Log;

import com.example.otogaleri.Models.Uyeler;


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



}
