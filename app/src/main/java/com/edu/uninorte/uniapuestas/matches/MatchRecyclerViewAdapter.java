package com.edu.uninorte.uniapuestas.matches;

import android.app.AlertDialog;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.edu.uninorte.uniapuestas.DataSingleton;
import com.edu.uninorte.uniapuestas.R;
import com.edu.uninorte.uniapuestas.bets.BetEntity;
import com.edu.uninorte.uniapuestas.bets.BetViewModel;

import java.util.List;

/**
 * Created by Visitante on 5/03/2018.
 */

public class MatchRecyclerViewAdapter extends RecyclerView.Adapter<MatchRecyclerViewAdapter.ViewHolder>{

    private List<MatchEntity> matchesData;
    private BetViewModel betModel;
    private MatchViewModel matchModel;

    public MatchRecyclerViewAdapter(List<MatchEntity> data){
        this.matchesData=data;
    }
    @Override
    public MatchRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_card,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MatchRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.textoNombrePartido.setText(matchesData.get(position).getTeamA()+" VS "+ matchesData.get(position).getTeamB()+"");
        holder.textoFecha.setText(matchesData.get(position).getDate()+"");
        holder.textoFase.setText("Fase de grupos");

        holder.btnApostar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Hacer apuestas");
                View dialog = LayoutInflater.from(view.getContext()).inflate(R.layout.bet_form, null, false);
                TextView textoHome = dialog.findViewById(R.id.textoHome);
                TextView textoAway = dialog.findViewById(R.id.textoAway);
                EditText editTextHome = dialog.findViewById(R.id.editTextHomeTeam);
                EditText editTextAway = dialog.findViewById(R.id.editTextAwayTeam);
                textoHome.setText(matchesData.get(position).getTeamA());
                textoAway.setText(matchesData.get(position).getTeamB());


                builder.setView(dialog);
                builder.setPositiveButton("Apostar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        betModel = ViewModelProviders.of((FragmentActivity) dialog.getContext()).get(BetViewModel.class);
                        betModel.addBet(new BetEntity((position + 1) + "", DataSingleton.currentUser.getUid() + "", DataSingleton.matches.get(position).getId() + "",editTextHome.getText().toString(),editTextAway.getText().toString()));

                        betModel.getAllBets().observe((LifecycleOwner) dialog.getContext(), betEntities -> {
                            for (BetEntity test: betEntities) {
                                Log.d("Apuestas", test.toString());
                            }
                        });

                        matchModel = ViewModelProviders.of((FragmentActivity) dialog.getContext()).get(MatchViewModel.class);
                        MatchEntity editMatch = DataSingleton.matches.get(position);
                        if(Integer.valueOf(editTextHome.getText().toString()) > Integer.valueOf(editTextAway.getText().toString())) {
                            editMatch.setUsersTeamA(String.valueOf(Integer.valueOf(editMatch.getUsersTeamA()) + 1));
                        } else if (Integer.valueOf(editTextHome.getText().toString()) < Integer.valueOf(editTextAway.getText().toString())) {
                            editMatch.setUsersTeamB(String.valueOf(Integer.valueOf(editMatch.getUsersTeamB()) + 1));
                        } else {
                            editMatch.setUsersDraw(String.valueOf(Integer.valueOf(editMatch.getUsersDraw()) + 1));
                        }
                        Log.d("Aguaecoco", editMatch.toString());
                        matchModel.addMatch(editMatch);
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.setCancelable(false);
                builder.show();
            }
        });


        holder.btnDefinir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Cerrar partido");
                View dialog = LayoutInflater.from(view.getContext()).inflate(R.layout.bet_form, null, false);
                TextView textoHome = dialog.findViewById(R.id.textoHome);
                TextView textoAway = dialog.findViewById(R.id.textoAway);
                EditText editTextHome = dialog.findViewById(R.id.editTextHomeTeam);
                EditText editTextAway = dialog.findViewById(R.id.editTextAwayTeam);
                textoHome.setText(matchesData.get(position).getTeamA());
                textoAway.setText(matchesData.get(position).getTeamB());


                builder.setView(dialog);
                builder.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        matchModel = ViewModelProviders.of((FragmentActivity) dialog.getContext()).get(MatchViewModel.class);
                        MatchEntity editMatch = DataSingleton.matches.get(position);
                        Log.d("Aguaecoco", editMatch.toString());
                        editMatch.setOpen(false);
                        editMatch.setReal_score_teamA(editTextHome.getText().toString());
                        editMatch.setReal_score_teamB(editTextAway.getText().toString());
                        matchModel.addMatch(editMatch);
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.setCancelable(false);
                builder.show();
            }
        });

        holder.textoVotosTeamA.setText("Gana equipo A: "+matchesData.get(position).getUsersTeamA()+" Usuarios");
        holder.textoEmpates.setText("Empate: "+matchesData.get(position).getUsersDraw()+"Usuarios");
        holder.textoVotosTeamB.setText("Gana equipo B: "+matchesData.get(position).getUsersTeamB()+"Usuarios");
    }

    @Override
    public int getItemCount() {
        return matchesData.size();
    }

    public void setData(List<MatchEntity> data){this.matchesData=data;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textoNombrePartido;
        TextView textoFecha;
        TextView textoFase;
        TextView textoVotosTeamA;
        TextView textoEmpates;
        TextView textoVotosTeamB;
        Button btnApostar;
        Button btnDefinir;

        public ViewHolder(View itemView){
            super(itemView);
            textoNombrePartido = itemView.findViewById(R.id.textoNombrePartido);
            textoFecha= itemView.findViewById(R.id.textoFecha);
            textoFase = itemView.findViewById(R.id.textoFase);
            textoVotosTeamA = itemView.findViewById(R.id.textoVotosTeamA);
            textoEmpates = itemView.findViewById(R.id.textoEmpates);
            textoVotosTeamB = itemView.findViewById(R.id.textoVotosTeamB);
            btnApostar = itemView.findViewById(R.id.btnApostar);
            btnDefinir = itemView.findViewById(R.id.btnDefinir);

            if (DataSingleton.currentUser.isAdmin()) {
                btnDefinir.setVisibility(View.VISIBLE);
                btnApostar.setVisibility(View.INVISIBLE);
            } else {
                btnDefinir.setVisibility(View.INVISIBLE);
                btnApostar.setVisibility(View.VISIBLE);
            }
        }

        public Button getBtnApostar() {
            return btnApostar;
        }
    }

}
