package com.edu.uninorte.uniapuestas;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.edu.uninorte.uniapuestas.Services.VolleySingleton;
import com.edu.uninorte.uniapuestas.matches.MatchEntity;
import com.edu.uninorte.uniapuestas.matches.MatchViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoadJSONFragment extends Fragment {


    Button btnLoadJSON;
    private static final String JSON_ARRAY_REQUEST_URL = "https://api-mundial-movil.herokuapp.com/api/v1/matches";
    private static final String TAG = "MainActivity";
    ProgressDialog progressDialog;
    private View showDialogView;
    private List<MatchEntity> matches; // este es el que tiene la data del partido
    private MatchViewModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.load_json_fragment,container,false);

        btnLoadJSON = view.findViewById(R.id.btnLoadJSON);
        progressDialog = new ProgressDialog(view.getContext());
        matches = new ArrayList<>();
        model = ViewModelProviders.of((FragmentActivity) getActivity()).get(MatchViewModel.class);

        btnLoadJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volleyJsonArrayRequest(JSON_ARRAY_REQUEST_URL);
                Toast.makeText(view.getContext(),"ME ACCIONE EXITOSAMENTE",Toast.LENGTH_SHORT).show();
                getActivity().getFragmentManager().popBackStack(); // esto es experimental
            }
        });
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

                        matches.add(new MatchEntity(id, homeTeam, awayTeam, fecha, "0", "0","0",true,"100", null, null));
                        model.addMatch(new MatchEntity(id, homeTeam, awayTeam, fecha, "0", "0","0",true,"100", null, null));
                    }
                    DataSingleton.matches = matches;

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
