package com.edu.uninorte.uniapuestas.matches;

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
public interface MatchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MatchEntity match);

    @Query("SELECT * FROM matchentity")
    LiveData<List<MatchEntity>> allMatches();

    @Query("SELECT * FROM matchentity WHERE id = :match_id")
    LiveData<MatchEntity> getMatchById(String match_id);
    
}
