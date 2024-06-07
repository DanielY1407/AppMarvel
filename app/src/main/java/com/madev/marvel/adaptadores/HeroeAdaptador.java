package com.madev.marvel.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.madev.marvel.R;
import com.madev.marvel.clases.Heroe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HeroeAdaptador extends RecyclerView.Adapter<HeroeAdaptador.ViewHolder> {

    private List<Heroe> datos;
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(Heroe heroe);
    }
    public HeroeAdaptador(List<Heroe> datos, OnItemClickListener listener) {
        this.datos = datos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HeroeAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_heroes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroeAdaptador.ViewHolder holder, int position) {
        Heroe dato = datos.get(position);
        holder.bind(dato);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onItemClick(dato);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_id, txt_nombre, txt_descripcion, txt_modificacion;
        ImageView img_heroe;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_id = itemView.findViewById(R.id.txt_id);
            txt_nombre = itemView.findViewById(R.id.txt_nombre);
            txt_descripcion = itemView.findViewById(R.id.txt_descripcion);
            txt_modificacion = itemView.findViewById(R.id.txt_modificacion);
            img_heroe = itemView.findViewById(R.id.img_heroe);

        }
        public void bind(Heroe dato) {
            txt_id.setText(dato.getId());
            txt_nombre.setText(dato.getNombre());
            txt_descripcion.setText(dato.getDescripcion());
            txt_modificacion.setText(dato.getModificacion());
            Picasso.get().load(dato.getMiniatura()).into(img_heroe);
        }
    }
}





