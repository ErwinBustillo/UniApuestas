package com.edu.uninorte.uniapuestas.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Visitante on 12/03/2018.
 */

public class RetroClient {
    private static final String BASE_URL = "https://github.com/lsv/fifa-worldcup-2018/blob/";

    private  static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static ApiService getApiService(){
        return getRetrofitInstance().create(ApiService.class);
    }
}
