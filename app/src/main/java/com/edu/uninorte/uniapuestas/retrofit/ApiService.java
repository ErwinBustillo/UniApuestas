package com.edu.uninorte.uniapuestas.retrofit;

import com.edu.uninorte.uniapuestas.data.JSONData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Visitante on 12/03/2018.
 */

public interface ApiService {
    @GET("master/data.json")
    Call<JSONData> getJsonData();


}
