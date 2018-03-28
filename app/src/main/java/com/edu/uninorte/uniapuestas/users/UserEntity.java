package com.edu.uninorte.uniapuestas.users;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by Visitante on 5/03/2018.
 */

@Entity
public class UserEntity implements Serializable {
    @PrimaryKey
    private int uid;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name ="admin")
    private boolean isAdmin;
    @ColumnInfo(name = "points")
    private String points;

    //Arreglo de apuestas FIXME


    public UserEntity(int uid, String name, String email, String password, boolean isAdmin, String points) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.points = points;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", points='" + points + '\'' +
                '}';
    }
}
