package com.edu.uninorte.uniapuestas.bets;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.edu.uninorte.uniapuestas.R;
import com.edu.uninorte.uniapuestas.matches.MatchViewModel;

import java.util.List;

/**
 * Created by Visitante on 5/03/2018.
 */

public class BetRecyclerViewAdapter extends RecyclerView.Adapter<BetRecyclerViewAdapter.ViewHolder> {

    private List<BetEntity> betsData;

    private BetViewModel betModel;
    private MatchViewModel matchModel;

    public BetRecyclerViewAdapter(List<BetEntity> data){
        this.betsData=data;
    } // constructor

    public void setData(List<BetEntity> data){this.betsData=data;}

    @Override
    public BetRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bet_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BetRecyclerViewAdapter.ViewHolder holder, int position) {

        betModel = ViewModelProviders.of((FragmentActivity) holder.itemView.getContext()).get(BetViewModel.class); //FIXEME



        holder.textoScoreBet.setText("TU RESULTADO : " +betsData.get(position).getScoreA() + " a " + betsData.get(position).getScoreB());

        //matchModel = ViewModelProviders.of((FragmentActivity) getActivity()).get(MatchViewModel.class);
        //AQUI FALTA ITERAR LOS BETS DEL USUARIO LUEGO SACAR EL ID DE CADA BET PARA FILTRAR LOS MATCHES Y SETEAR LA DATA DE MATCHES EN ESTOS HOLDER TEXT
        /*
        holder.textoPartidoBet.setText(matchesData.get(position).getTeamA()+" VS "+ matchesData.get(position).getTeamB()+"");
        holder.textoFechaBet.setText(matchesData.get(position).getDate()+"");
        holder.textoFase.setText("Fase de grupos");
        holder.textoVotosTeamA.setText("Gana equipo A: "+matchesData.get(position).getUsersTeamA()+" Usuarios");
        holder.textoEmpates.setText("Empate: "+matchesData.get(position).getUsersDraw()+"Usuarios");
        holder.textoVotosTeamB.setText("Gana equipo B: "+matchesData.get(position).getUsersTeamB()+"Usuarios");
        holder.textoStatus.setText("ESTADO: "+ matches.getFinished);
        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

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
