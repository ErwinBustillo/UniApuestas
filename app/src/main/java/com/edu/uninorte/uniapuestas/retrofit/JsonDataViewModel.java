package com.edu.uninorte.uniapuestas.retrofit;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;


import com.edu.uninorte.uniapuestas.data.JSONData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Visitante on 12/03/2018.
 */

public class JsonDataViewModel extends AndroidViewModel {

    private MutableLiveData<JSONData> liveData;
    public JsonDataViewModel(@NonNull Application application) {
        super(application);
        if (liveData == null){
            liveData= new MutableLiveData<>();

        }
        getJson();
    }

    private void getJson(){
        ApiService api= RetroClient.getApiService();
        Call<JSONData> dataCall = api.getJsonData();
        dataCall.enqueue(new Callback<JSONData>() {
            @Override
            public void onResponse(Call<JSONData> call, Response<JSONData> response) {
                Log.d("JSON","CALL ME ONRESPONSE");
                setData(response.body());
            }

            @Override
            public void onFailure(Call<JSONData> call, Throwable t) {
                Log.d("JSON","CALL ME onfaliure");
            }
        });
    }

    public MutableLiveData<JSONData> getLiveData(){
        return liveData;
    }

    public void setData(JSONData data) {
        liveData.setValue(data);
    }
}
