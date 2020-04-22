package com.example.booktracking;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    // 以Singleton模式建立
    private static RetrofitClient mInstance = new RetrofitClient();

    private ApiService myAPIService;

    private RetrofitClient() {

        // 設置baseUrl即要連的網站，addConverterFactory用Gson作為資料處理Converter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.getApiUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myAPIService = retrofit.create(ApiService.class);
    }

    public static RetrofitClient getInstance() {
        return mInstance;
    }

    public ApiService getAPI() {
        return myAPIService;
    }

}
