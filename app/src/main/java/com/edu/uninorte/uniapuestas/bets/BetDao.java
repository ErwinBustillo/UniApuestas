package com.edu.uninorte.uniapuestas.bets;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

/**
 * Created by Visitante on 5/03/2018.
 */

@Dao
public interface BetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BetEntity bet);
}
