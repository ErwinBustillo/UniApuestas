package com.edu.uninorte.uniapuestas.matches;



import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Visitante on 5/03/2018.
 */

public class MatchViewModel extends AndroidViewModel {


    private LiveData<List<MatchEntity>> matches;


    public MatchViewModel(@NonNull Application application) {
        super(application);
        //appDatabase=AppDatabase.getInstance(this.getApplication());

        //data= appDatabase.userDao().getAll();
    }

    public LiveData<List<MatchEntity>> getMatches(){return matches;} // metodo que devueve todos los partidos


    // para testing

    public void addMatch(MatchEntity match){

        new AddItemTask().execute(match); // la ejecuta
    }
    // tarea asyncrona que escribe en el dao e inserta el usuario
    private class AddItemTask extends AsyncTask<MatchEntity, Void, Void> {

        @Override
        protected Void doInBackground(MatchEntity... item) {
            //appDatabase.userDao().insert(item[0]);
            return null;
        }
    }

}
