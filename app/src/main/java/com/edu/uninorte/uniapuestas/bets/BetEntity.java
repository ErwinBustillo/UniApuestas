package com.edu.uninorte.uniapuestas.bets;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Visitante on 5/03/2018.
 */

@Entity
public class BetEntity {
    @PrimaryKey
    private String id;
    @ColumnInfo(name = "user_id")
    private String userId;
    @ColumnInfo(name = "match_id")
    private String matchId;
    @ColumnInfo(name = "scoreA")
    private String scoreA;
    @ColumnInfo(name = "scoreB")
    private String scoreB;

    public BetEntity(String id, String userId, String matchId, String scoreA, String scoreB) {
        this.id = id;
        this.userId = userId;
        this.matchId = matchId;
        this.scoreA = scoreA;
        this.scoreB = scoreB;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getScoreA() {
        return scoreA;
    }

    public void setScoreA(String scoreA) {
        this.scoreA = scoreA;
    }

    public String getScoreB() {
        return scoreB;
    }

    public void setScoreB(String scoreB) {
        this.scoreB = scoreB;
    }
}
