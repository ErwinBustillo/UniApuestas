package com.edu.uninorte.uniapuestas;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.edu.uninorte.uniapuestas.bets.BetDao;
import com.edu.uninorte.uniapuestas.matches.MatchDao;
import com.edu.uninorte.uniapuestas.users.UserDao;
import com.edu.uninorte.uniapuestas.users.UserEntity;

import static android.arch.persistence.room.Room.databaseBuilder;

/**
 * Created by mauri on 27/03/2018.
 */

@Database(entities = {UserEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract BetDao betDao();
    public abstract MatchDao matchDao();
    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = databaseBuilder(context.getApplicationContext(), AppDatabase.class, "demo_uniapuesta").build();
        }

        return INSTANCE;
    }
}
