package com.edu.uninorte.uniapuestas.matches;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edu.uninorte.uniapuestas.R;

import java.util.List;

/**
 * Created by Visitante on 5/03/2018.
 */

public class MatchRecyclerViewAdapter extends RecyclerView.Adapter<MatchRecyclerViewAdapter.ViewHolder>{

    private List<MatchEntity> matchesData;

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

        public ViewHolder(View itemView){
            super(itemView);
            textoNombrePartido = itemView.findViewById(R.id.textoNombrePartido);
            textoFecha= itemView.findViewById(R.id.textoFecha);
            textoFase = itemView.findViewById(R.id.textoFase);
            textoVotosTeamA = itemView.findViewById(R.id.textoVotosTeamA);
            textoEmpates = itemView.findViewById(R.id.textoEmpates);
            textoVotosTeamB = itemView.findViewById(R.id.textoVotosTeamB);
        }
    }

}
