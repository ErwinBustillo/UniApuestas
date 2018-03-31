package com.edu.uninorte.uniapuestas.bets;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Visitante on 5/03/2018.
 */

@Dao
public interface BetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BetEntity bet);

    @Query("SELECT * FROM betentity")
    LiveData<List<BetEntity>> allBets();

    @Query("SELECT * FROM betentity WHERE user_id = :user_id AND match_id = :match_id")
    LiveData<BetEntity> getUserBet(String user_id, String match_id);
}
