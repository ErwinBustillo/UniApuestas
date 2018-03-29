package com.edu.uninorte.uniapuestas;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.edu.uninorte.uniapuestas.Services.VolleySingleton;
import com.edu.uninorte.uniapuestas.matches.Match;
import com.edu.uninorte.uniapuestas.matches.MatchEntity;
import com.edu.uninorte.uniapuestas.matches.MatchRecyclerViewAdapter;
import com.edu.uninorte.uniapuestas.matches.MatchViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erwin on 3/11/2018.
 */

public class AllBetFragment extends Fragment {
    RecyclerView rv;
    private MatchRecyclerViewAdapter adapter;
    private List<MatchEntity> matches; // este es el que tiene la data del partido

    private MatchViewModel model;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.all_bet_fragment,container,false);
        rv=view.findViewById(R.id.listaPartidos);

        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new MatchRecyclerViewAdapter(new ArrayList<MatchEntity>());
        rv.setAdapter(adapter);

        matches = new ArrayList<>();

        model = ViewModelProviders.of((FragmentActivity) getActivity()).get(MatchViewModel.class);
        model.getAllMatches().observe((LifecycleOwner) getActivity(), matchEntities -> {
            matches = matchEntities;
            Log.d("TAgaso", matches.get(0) + "");
            adapter.setData(matches);
            adapter.notifyDataSetChanged();
            DataSingleton.matches = matches;
        });

        return view;
    }
}
