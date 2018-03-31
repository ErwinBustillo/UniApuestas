package com.edu.uninorte.uniapuestas.matches;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Visitante on 5/03/2018.
 */

@Entity
public class MatchEntity {
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "teamA")
    private String teamA;
    @ColumnInfo(name = "teamB")
    private String teamB;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "users_team_a")
    private String usersTeamA;
    @ColumnInfo(name = "users_team_b")
    private String usersTeamB;
    @ColumnInfo(name = "users_draw")
    private String usersDraw;
    @ColumnInfo(name = "open")
    private boolean isOpen;
    @ColumnInfo(name = "match_points")
    private String matchPoints;
    @ColumnInfo(name = "real_score_teamA")
    private String real_score_teamA;
    @ColumnInfo(name = "real_score_teamB")
    private String real_score_teamB;

    public MatchEntity(int id, String teamA, String teamB, String date, String usersTeamA, String usersTeamB, String usersDraw, boolean isOpen, String matchPoints, String real_score_teamA, String real_score_teamB) {
        this.id = id;
        this.teamA = teamA;
        this.teamB = teamB;
        this.date = date;
        this.usersTeamA = usersTeamA;
        this.usersTeamB = usersTeamB;
        this.usersDraw = usersDraw;
        this.isOpen = isOpen;
        this.matchPoints = matchPoints;
        this.real_score_teamA = real_score_teamA;
        this.real_score_teamB = real_score_teamB;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsersTeamA() {
        return usersTeamA;
    }

    public void setUsersTeamA(String usersTeamA) {
        this.usersTeamA = usersTeamA;
    }

    public String getUsersTeamB() {
        return usersTeamB;
    }

    public void setUsersTeamB(String usersTeamB) {
        this.usersTeamB = usersTeamB;
    }

    public String getUsersDraw() {
        return usersDraw;
    }

    public void setUsersDraw(String usersDraw) {
        this.usersDraw = usersDraw;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getMatchPoints() {
        return matchPoints;
    }

    public void setMatchPoints(String matchPoints) {
        this.matchPoints = matchPoints;
    }

    public String getReal_score_teamA() {
        return real_score_teamA;
    }

    public void setReal_score_teamA(String real_score_teamA) {
        this.real_score_teamA = real_score_teamA;
    }

    public String getReal_score_teamB() {
        return real_score_teamB;
    }

    public void setReal_score_teamB(String real_score_teamB) {
        this.real_score_teamB = real_score_teamB;
    }

    @Override
    public String toString() {
        return "MatchEntity{" +
                "id=" + id +
                ", teamA='" + teamA + '\'' +
                ", teamB='" + teamB + '\'' +
                ", date='" + date + '\'' +
                ", usersTeamA='" + usersTeamA + '\'' +
                ", usersTeamB='" + usersTeamB + '\'' +
                ", usersDraw='" + usersDraw + '\'' +
                ", isOpen=" + isOpen +
                ", matchPoints='" + matchPoints + '\'' +
                '}';
    }
}
