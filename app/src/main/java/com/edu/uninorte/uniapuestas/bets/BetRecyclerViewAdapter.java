package com.edu.uninorte.uniapuestas.bets;

import android.app.AlertDialog;
import android.app.Application;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
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
import com.edu.uninorte.uniapuestas.matches.MatchEntity;
import com.edu.uninorte.uniapuestas.matches.MatchViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.edu.uninorte.uniapuestas.DataSingleton.matches;

/**
 * Created by Visitante on 5/03/2018.
 */

public class BetRecyclerViewAdapter extends RecyclerView.Adapter<BetRecyclerViewAdapter.ViewHolder> {

    private List<BetEntity> betsData;
    private List<MatchEntity> matchesData;

    private BetViewModel betModel;
    private MatchViewModel matchModel;

    public BetRecyclerViewAdapter(List<BetEntity> data, List<MatchEntity> data2){
        this.betsData=data;
        this.matchesData=data2;
    } // constructor

    public void setData(List<BetEntity> data, List<MatchEntity> data2){this.betsData=data; this.matchesData=data2;}

    @Override
    public BetRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bet_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BetRecyclerViewAdapter.ViewHolder holder, int position) {

        betModel = ViewModelProviders.of((FragmentActivity) holder.itemView.getContext()).get(BetViewModel.class); //FIXEME



        matchModel = ViewModelProviders.of((FragmentActivity) holder.itemView.getContext()).get(MatchViewModel.class);

        holder.textoPartidoBet.setText(matchesData.get(position).getTeamA()+" VS "+ matchesData.get(position).getTeamB()+"");

        holder.textoFechaBet.setText(matchesData.get(position).getDate()+"");
        holder.textoFase.setText("Fase de grupos");
        holder.textoVotosTeamA.setText("Gana equipo A: "+matchesData.get(position).getUsersTeamA()+" Usuarios");
        holder.textoEmpates.setText("Empate: "+matchesData.get(position).getUsersDraw()+" Usuarios");
        holder.textoVotosTeamB.setText("Gana equipo B: "+matchesData.get(position).getUsersTeamB()+" Usuarios");
        holder.textoStatus.setText("ESTADO: "+ matchesData.get(position).isOpen());
        holder.textoScoreBet.setText("TU RESULTADO : " +betsData.get(position+1).getScoreA() + " a " + betsData.get(position+1).getScoreB());
        //Log.d("BetRecyclerViewAdapter", position + " posición");
        //Log.d("BetRecyclerViewAdapter", matchData.get(position).toString());

        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
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
                        betModel.addBet(new BetEntity((position) + "", DataSingleton.currentUser.getUid() + "", DataSingleton.matches.get(position).getId() + "",editTextHome.getText().toString(),editTextAway.getText().toString()));

                        betModel.getAllBets().observe((LifecycleOwner) dialog.getContext(), betEntities -> {
                            for (BetEntity test: betEntities) {
                                Log.d("Apuestas", test.toString());
                            }
                        });

                        matchModel = ViewModelProviders.of((FragmentActivity) dialog.getContext()).get(MatchViewModel.class);
                        MatchEntity editMatch = DataSingleton.matches.get(position);
//                            if(Integer.valueOf(editTextHome.getText().toString()) > Integer.valueOf(editTextAway.getText().toString())) {
//                                editMatch.setUsersTeamA(String.valueOf(Integer.valueOf(editMatch.getUsersTeamA()) + 1));
//                            } else if (Integer.valueOf(editTextHome.getText().toString()) < Integer.valueOf(editTextAway.getText().toString())) {
//                                editMatch.setUsersTeamB(String.valueOf(Integer.valueOf(editMatch.getUsersTeamB()) + 1));
//                            } else {
//                                editMatch.setUsersDraw(String.valueOf(Integer.valueOf(editMatch.getUsersDraw()) + 1));
//                            }
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
    }

    @Override
    public int getItemCount() {
        return betsData.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textoPartidoBet;
        TextView textoFechaBet;
        TextView textoFase;
        TextView textoVotosTeamA;
        TextView textoEmpates;
        TextView textoVotosTeamB;
        TextView textoScoreBet;

        TextView textoStatus;
        Button btnEditar;


        public ViewHolder(View itemView){
            super(itemView);
            textoPartidoBet = itemView.findViewById(R.id.TextViewNombrePartidoBet);
            textoFechaBet= itemView.findViewById(R.id.TextViewFechaBet);
            textoFase = itemView.findViewById(R.id.TextViewGroup);
            textoVotosTeamA = itemView.findViewById(R.id.TextViewVotosTeamABet);
            textoEmpates = itemView.findViewById(R.id.TextViewEmpatesBet);
            textoVotosTeamB = itemView.findViewById(R.id.TextViewVotosTeamBBet);
            textoScoreBet= itemView.findViewById(R.id.TextViewScoreBet);
            textoStatus = itemView.findViewById(R.id.TextViewStatusBet);
            btnEditar = itemView.findViewById(R.id.btnEditar);
        }
    }
}
