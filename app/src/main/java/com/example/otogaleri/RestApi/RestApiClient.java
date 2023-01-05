package com.example.otogaleri.restapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestApiClient {
    private final RestApi mRestApi;

    public RestApiClient(String restApiServiceUrl) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS);

        OkHttpClient okHttpClient = builder.build();
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(restApiServiceUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mRestApi = retrofit.create(RestApi.class);
    }

    public RestApi getRestApi() {
        return mRestApi;
    }
}
/*java classtan oluşturuld*/
