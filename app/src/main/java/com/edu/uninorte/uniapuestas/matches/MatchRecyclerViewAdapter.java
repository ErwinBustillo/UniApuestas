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
        TextView textoId;
        TextView textoNombre;
        TextView textoApellido;
        TextView textoCity;
        TextView textoBooks;


        public ViewHolder(View itemView){
            super(itemView);
            textoId = itemView.findViewById(R.id.textoId);
            textoNombre= itemView.findViewById(R.id.textoNombre);
            textoApellido = itemView.findViewById(R.id.textoApellido);
            textoCity = itemView.findViewById(R.id.textoCity);
            textoBooks = itemView.findViewById(R.id.textoBooks);
        }
    }

}
