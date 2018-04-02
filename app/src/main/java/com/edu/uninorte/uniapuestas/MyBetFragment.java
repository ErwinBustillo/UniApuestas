package com.edu.uninorte.uniapuestas;

import android.app.Fragment;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.uninorte.uniapuestas.bets.BetEntity;
import com.edu.uninorte.uniapuestas.bets.BetRecyclerViewAdapter;
import com.edu.uninorte.uniapuestas.bets.BetViewModel;
import com.edu.uninorte.uniapuestas.matches.Match;
import com.edu.uninorte.uniapuestas.matches.MatchEntity;
import com.edu.uninorte.uniapuestas.matches.MatchViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erwin on 3/11/2018.
 */

public class MyBetFragment extends Fragment {

    private BetRecyclerViewAdapter adapter;
    RecyclerView rv;
    private List<BetEntity> apuestas; //este tiene las apuestas
    private BetViewModel betModel;
    private MatchViewModel matchModel;
    private List<MatchEntity> matches;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.bet_fragment,container,false);
        rv = view.findViewById(R.id.listaApuestas);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rv.addItemDecoration(new DividerItemDecoration((view.getContext()),  LinearLayoutManager.VERTICAL));
        adapter = new BetRecyclerViewAdapter(new ArrayList<BetEntity>(), new ArrayList<MatchEntity>());
        rv.setAdapter(adapter);


        apuestas = new ArrayList<>();
        matches = new ArrayList<>();

        betModel = ViewModelProviders.of((FragmentActivity) getActivity()).get(BetViewModel.class);
        matchModel = ViewModelProviders.of((FragmentActivity) getActivity()).get(MatchViewModel.class);


        betModel.getAllBetsUser(DataSingleton.currentUser.getUid()+"").observe((LifecycleOwner) getActivity(), betsEntities -> {
            for (BetEntity entity: betsEntities) {
                Log.d("Aguaecoco", entity.toString());
                matchModel.getMatchById(entity.getMatchId()).observe((LifecycleOwner) getActivity(), matchEntity -> {
                    matches.add(matchEntity);
                });
            }
            apuestas=betsEntities;
            Log.d("elloco", matches.size() + "");
            adapter.setData(apuestas, matches);
            adapter.notifyDataSetChanged();
            DataSingleton.bets = apuestas;
        });

        return view;

    }
}
