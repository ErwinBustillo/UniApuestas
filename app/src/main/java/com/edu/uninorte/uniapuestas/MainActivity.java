package com.edu.uninorte.uniapuestas;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.edu.uninorte.uniapuestas.Services.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // NOTA DE ERWIN
    // TODO LO QUE ESTA AQUI SE TIENE QUE IR PARA LOS FRAGMENTOS DE ALLBETS FRAGMENTS Y MY BETS FRAGMENTS PARA MANIPULAR LA DATA de una mejor manera

    private static final String JSON_ARRAY_REQUEST_URL = "https://api-mundial-movil.herokuapp.com/api/v1/matches";//"https://androidtutorialpoint.com/api/volleyJsonArray";
    //https://github.com/lsv/fifa-worldcup-2018/blob/master/data.json
    //https://androidtutorialpoint.com/api/volleyJsonArray
    private static final String TAG = "MainActivity";
    ProgressDialog progressDialog;
    private View showDialogView;
    private TextView outputTextView;
    private Button JsonArrayRequestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        JsonArrayRequestButton = findViewById(R.id.button_get_Json_array);
        JsonArrayRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volleyJsonArrayRequest(JSON_ARRAY_REQUEST_URL);
            }
        });
    }

    public void volleyJsonArrayRequest(String url){

        String  REQUEST_TAG = "com.androidtutorialpoint.volleyJsonArrayRequest";
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayReq = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                LayoutInflater li = LayoutInflater.from(MainActivity.this);
                showDialogView = li.inflate(R.layout.show_dialog, null);
                outputTextView = showDialogView.findViewById(R.id.text_view_dialog);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setView(showDialogView);
                alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                }).setCancelable(false).create();

                /// filtro de datos del json
                try {
                    JSONObject match = response.getJSONObject(0);

                    int id = match.getInt("id");
                    String group = match.getString("group");
                    String fecha = match.getString("date");
                    boolean finished = match.getBoolean("finished");
                    String homeTeam = match.getJSONObject("home_team").getString("name");
                    String awayTeam = match.getJSONObject("away_team").getString("name");
                    outputTextView.setText("id: "+id +"\n" +"grupo: " + group +"\n" +"home team: "+homeTeam +"\n" + "Away team :" + awayTeam + "\n" +"Fecha : " +fecha);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ///

                // outputTextView.setText(response.toString());
                alertDialogBuilder.show();
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
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonArrayReq, REQUEST_TAG);
    }

}


