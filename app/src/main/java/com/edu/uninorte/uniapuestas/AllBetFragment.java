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
import com.edu.uninorte.uniapuestas.retrofit.JsonDataViewModel;

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
    private List<Match> matches; // este es el que tiene la data del partido

    private static final String JSON_ARRAY_REQUEST_URL = "https://api-mundial-movil.herokuapp.com/api/v1/matches";
    private static final String TAG = "MainActivity";
    ProgressDialog progressDialog;
    private View showDialogView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.all_bet_fragment,container,false);
        rv=view.findViewById(R.id.listaPartidos);

        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new MatchRecyclerViewAdapter(new ArrayList<Match>());
        rv.setAdapter(adapter);

        progressDialog = new ProgressDialog(view.getContext());

        matches = new ArrayList<>();

        volleyJsonArrayRequest(JSON_ARRAY_REQUEST_URL);

        return view;
    }

    public void volleyJsonArrayRequest(String url){

        String  REQUEST_TAG = "com.androidtutorialpoint.volleyJsonArrayRequest";
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayReq = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                LayoutInflater li = LayoutInflater.from(getActivity());
                showDialogView = li.inflate(R.layout.show_dialog, null);

                /// filtro de datos del json
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject match = response.getJSONObject(i);

                        Log.d("TAGASO", match.toString());

                        int id = match.getInt("id");
                        String group = match.getString("group");
                        String fecha = match.getString("date");
                        boolean finished = match.getBoolean("finished");
                        String homeTeam = match.getJSONObject("home_team").getString("name");
                        String awayTeam = match.getJSONObject("away_team").getString("name");

                        matches.add(new Match(id, group, fecha, finished, homeTeam, awayTeam));
                    }

                    adapter.setData(matches);
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                progressDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                progressDialog.hide();
            }
        });
        // Adding JsonObject request to request queue
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(jsonArrayReq, REQUEST_TAG);
    }
}
