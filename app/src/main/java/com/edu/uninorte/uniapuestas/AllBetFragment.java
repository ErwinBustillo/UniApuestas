package com.edu.uninorte.uniapuestas;

import android.app.Fragment;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.uninorte.uniapuestas.matches.MatchEntity;
import com.edu.uninorte.uniapuestas.matches.MatchRecyclerViewAdapter;
import com.edu.uninorte.uniapuestas.matches.MatchViewModel;
import com.edu.uninorte.uniapuestas.retrofit.JsonDataViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erwin on 3/11/2018.
 */

public class AllBetFragment extends Fragment {
    RecyclerView rv;
    private MatchRecyclerViewAdapter adapter;
    private List<MatchEntity> data; // este es el que tiene la data del partido

    private JsonDataViewModel model;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.all_bet_fragment,container,false);
        rv=view.findViewById(R.id.listaPartidos);

        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new MatchRecyclerViewAdapter(new ArrayList<MatchEntity>());
        rv.setAdapter(adapter);

        model = ViewModelProviders.of((FragmentActivity) view.getContext()).get(JsonDataViewModel.class);

        model.getLiveData().observe((FragmentActivity) view.getContext(), liveData -> {
            Log.d("TAGASO", "Get live data");
        });

        //MatchEntity match = new MatchEntity(1,"Colombia","Japon","junio 13","Colombia gana :3 users","Japon Gana : 1 users","Empate: 5 users",true,"1000");

        //data.add(match);
        //model.addMatch(match);
        //adapter.notifyDataSetChanged();
        //adapter.setData(data);

        //AQUI falla porque faltan cosas esperando retrofit
       /* model.getMatches().observe((LifecycleOwner) view.getContext(), matches -> {
            data=matches;
            adapter.setData(data);
            adapter.notifyDataSetChanged();
        });*/
        return view;
    }
}
