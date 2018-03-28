package com.edu.uninorte.uniapuestas.bets;

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

public class BetViewModel extends AndroidViewModel{
    private AppDatabase appDatabase;
    private LiveData<List<BetEntity>> data;

    public BetViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getInstance(this.getApplication());
        data = appDatabase.betDao().allBets();
    }

    public LiveData<List<BetEntity>> getAllBets() {
        return appDatabase.betDao().allBets();
    }

    public void addBet(BetEntity bet) {
        new BetViewModel.AddItemTask().execute(bet);
    }

    private class AddItemTask extends AsyncTask<BetEntity, Void, Void> {

        @Override
        protected Void doInBackground(BetEntity... item) {
            appDatabase.betDao().insert(item[0]);
            return null;
        }
    }
}
