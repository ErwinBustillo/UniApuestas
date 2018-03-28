package com.edu.uninorte.uniapuestas;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.edu.uninorte.uniapuestas.users.UserDao;
import com.edu.uninorte.uniapuestas.users.UserEntity;

import static android.arch.persistence.room.Room.databaseBuilder;

/**
 * Created by mauri on 27/03/2018.
 */

@Database(entities = {UserEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = databaseBuilder(context.getApplicationContext(), AppDatabase.class, "demo_uniapuesta").build();
        }

        return INSTANCE;
    }
}
