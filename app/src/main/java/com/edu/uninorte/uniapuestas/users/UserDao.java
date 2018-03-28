package com.edu.uninorte.uniapuestas.users;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

/**
 * Created by Visitante on 5/03/2018.
 */

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserEntity user);

    @Query("SELECT * FROM userentity WHERE admin = :isAdmin")
    LiveData<UserEntity> loadAdminUser(boolean isAdmin);

    @Query("SELECT * FROM userentity WHERE email = :email AND password = :password")
    LiveData<UserEntity> loginUser(String email, String password);

}
