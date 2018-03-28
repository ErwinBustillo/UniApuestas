package com.edu.uninorte.uniapuestas.matches;



import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.edu.uninorte.uniapuestas.AppDatabase;

import java.util.List;

/**
 * Created by Visitante on 5/03/2018.
 */

public class MatchViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;
    private LiveData<MatchEntity> data;

    public MatchViewModel(@NonNull Application application) {
        super(application);
        appDatabase= AppDatabase.getInstance(this.getApplication());
        //data = appDatabase.matchDao()

    }

    /*public LiveData<List<MatchEntity>> getAllMatches() {
        //return appDatabase.matchDao().allMatches();
    }*/


    public void addMatch(MatchEntity match){

        new AddItemTask().execute(match);
    }
    // tarea asyncrona que escribe en el dao e inserta el usuario
    private class AddItemTask extends AsyncTask<MatchEntity, Void, Void> {

        @Override
        protected Void doInBackground(MatchEntity... item) {
            //appDatabase.matchDao().insert(item[0]);
            return null;
        }
    }

}
