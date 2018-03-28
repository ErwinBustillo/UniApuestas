package com.edu.uninorte.uniapuestas;

import com.edu.uninorte.uniapuestas.bets.BetEntity;
import com.edu.uninorte.uniapuestas.matches.MatchEntity;
import com.edu.uninorte.uniapuestas.users.UserEntity;

import java.util.List;

public class DataSingleton {
    public static UserEntity currentUser;
    public static List<MatchEntity> matches;
}
