package com.edu.uninorte.uniapuestas.matches;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.edu.uninorte.uniapuestas.DataSingleton;
import com.edu.uninorte.uniapuestas.R;

import java.util.List;

/**
 * Created by Visitante on 5/03/2018.
 */

public class MatchRecyclerViewAdapter extends RecyclerView.Adapter<MatchRecyclerViewAdapter.ViewHolder>{

    private List<Match> matchesData;

    public MatchRecyclerViewAdapter(List<Match> data){
        this.matchesData=data;
    }
    @Override
    public MatchRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_card,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MatchRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.textoNombrePartido.setText(matchesData.get(position).getHomeTeam()+" VS "+ matchesData.get(position).getAwayTeam()+"");
        holder.textoFecha.setText(matchesData.get(position).getDate()+"");
        holder.textoFase.setText("Fase de grupos");

        holder.btnApostar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Hacer apuestas " + DataSingleton.currentUser.getName());
                View dialog = LayoutInflater.from(view.getContext()).inflate(R.layout.bet_form, null, false);
                TextView textoHome = dialog.findViewById(R.id.textoHome);
                TextView textoAway = dialog.findViewById(R.id.textoAway);

                textoHome.setText(matchesData.get(position).getHomeTeam());
                textoAway.setText(matchesData.get(position).getAwayTeam());


                builder.setView(dialog);
                builder.setPositiveButton("Apostar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

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
        //holder.textoVotosTeamA.setText("Gana equipo A: "+matchesData.get(position).getUsersTeamA()+" Usuarios");
        //holder.textoEmpates.setText("Empate: "+matchesData.get(position).getUsersDraw()+"Usuarios");
        //holder.textoVotosTeamB.setText("Gana equipo B: "+matchesData.get(position).getUsersTeamB()+"Usuarios");
    }

    @Override
    public int getItemCount() {
        return matchesData.size();
    }

    public void setData(List<Match> data){this.matchesData=data;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textoNombrePartido;
        TextView textoFecha;
        TextView textoFase;
        TextView textoVotosTeamA;
        TextView textoEmpates;
        TextView textoVotosTeamB;
        Button btnApostar;

        public ViewHolder(View itemView){
            super(itemView);
            textoNombrePartido = itemView.findViewById(R.id.textoNombrePartido);
            textoFecha= itemView.findViewById(R.id.textoFecha);
            textoFase = itemView.findViewById(R.id.textoFase);
            textoVotosTeamA = itemView.findViewById(R.id.textoVotosTeamA);
            textoEmpates = itemView.findViewById(R.id.textoEmpates);
            textoVotosTeamB = itemView.findViewById(R.id.textoVotosTeamB);
            btnApostar = itemView.findViewById(R.id.btnApostar);
        }

        public Button getBtnApostar() {
            return btnApostar;
        }
    }

}
