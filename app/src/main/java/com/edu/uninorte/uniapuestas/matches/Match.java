package com.edu.uninorte.uniapuestas.matches;

/**
 * Created by erwin on 3/28/2018.
 */

public class Match {

    private int id;
    private String group;
    private String date;
    private boolean finished;
    private String homeTeam;
    private String awayTeam;

    public Match(int id, String group, String date, boolean finished, String homeTeam, String awayTeam) {
        this.id = id;
        this.group = group;
        this.date = date;
        this.finished = finished;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }
}
