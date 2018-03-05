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

    public MatchEntity(int id, String teamA, String teamB, String scoreA, String scoreB, String usersTeamA, String usersTeamB, String usersDraw, boolean isOpen) {
        this.id = id;
        this.teamA = teamA;
        this.teamB = teamB;
        this.usersTeamA = usersTeamA;
        this.usersTeamB = usersTeamB;
        this.usersDraw = usersDraw;
        this.isOpen = isOpen;
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
}
