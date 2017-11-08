package com.jesusramirez.proyecto.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jesusramirez.proyecto.Modelos.Alumnos;
import com.jesusramirez.proyecto.R;

import java.util.ArrayList;

/**
 * Created by Jesus on 06/11/2017.
 */

public class AlumnosAdapter extends RecyclerView.Adapter<AlumnosAdapter.AlumnosViewHolder> implements View.OnClickListener{

    ArrayList<Alumnos> listaAlumnos;
    private View.OnClickListener listener;

    public AlumnosAdapter(ArrayList<Alumnos> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    @Override
    public AlumnosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_alumnos_recycler,null,false);
        view.setOnClickListener(this);
        return new AlumnosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlumnosViewHolder holder, int position) {
        holder.aPaternoLista.setText(listaAlumnos.get(position).aPaternoAlumno);
        holder.aMaternoLista.setText(listaAlumnos.get(position).aMaternoAlumno);
        holder.nombresLista.setText(listaAlumnos.get(position).nombreAlumno);
    }

    @Override
    public int getItemCount() {
        return listaAlumnos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    public class AlumnosViewHolder extends RecyclerView.ViewHolder {

        TextView aPaternoLista,aMaternoLista,nombresLista,justificadoLista,retardoLista;
        CheckBox checkLista;

        public AlumnosViewHolder(View itemView) {
            super(itemView);
            aPaternoLista = (TextView)itemView.findViewById(R.id.apaterno_listaalumnos);
            aMaternoLista = (TextView)itemView.findViewById(R.id.amaterno_listaalumnos);
            nombresLista = (TextView)itemView.findViewById(R.id.nombres_listaalumnos);
            justificadoLista = (TextView)itemView.findViewById(R.id.justificado_listaalumnos);
            retardoLista = (TextView)itemView.findViewById(R.id.retardo_listaalumnos);
            checkLista = (CheckBox) itemView.findViewById(R.id.check_listaalumnos);
        }
    }
}
