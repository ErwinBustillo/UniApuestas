package com.edu.uninorte.uniapuestas.users;

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

public class UserViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;
    private LiveData<UserEntity> data;

    public UserViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getInstance(this.getApplication());
        data = appDatabase.userDao().loadAdminUser(true);
    }

    public LiveData<UserEntity> getAdmin() {
        return data;
    }

    public LiveData<UserEntity> getUser(String email, String password) {
        return appDatabase.userDao().loginUser(email, password);
    }

    public void addUser(UserEntity user) {
        new AddItemTask().execute(user);
    }

    private class AddItemTask extends AsyncTask<UserEntity, Void, Void> {

        @Override
        protected Void doInBackground(UserEntity... item) {
            appDatabase.userDao().insert(item[0]);
            return null;
        }
    }
}