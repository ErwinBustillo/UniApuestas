package com.edu.uninorte.uniapuestas.bets;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Visitante on 5/03/2018.
 */

@Entity
public class BetEntity {
    @PrimaryKey @NonNull
    private String bid;
    @ColumnInfo(name = "user_id")
    private String userId;
    @ColumnInfo(name = "match_id")
    private String matchId;
    @ColumnInfo(name = "scoreA")
    private String scoreA;
    @ColumnInfo(name = "scoreB")
    private String scoreB;

    public BetEntity(String bid, String userId, String matchId, String scoreA, String scoreB) {
        this.bid = bid;
        this.userId = userId;
        this.matchId = matchId;
        this.scoreA = scoreA;
        this.scoreB = scoreB;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String id) {
        this.bid = id;
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

    @Override
    public String toString() {
        return "BetEntity{" +
                "bid='" + bid + '\'' +
                ", userId='" + userId + '\'' +
                ", matchId='" + matchId + '\'' +
                ", scoreA='" + scoreA + '\'' +
                ", scoreB='" + scoreB + '\'' +
                '}';
    }
}
