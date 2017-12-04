package com.carmona.pillbox.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carmona.pillbox.Models.Cita;
import com.carmona.pillbox.Models.Receta;
import com.carmona.pillbox.R;

import java.util.ArrayList;

/**
 * Created by carmona on 3/12/17.
 */

public class CitaRVAdapter extends RecyclerView.Adapter<CitaRVAdapter.ViewHolder> {

    private ArrayList<Cita> mDataset;
    private Context mContext;

    public interface CitaRVAdapterListener{
        void OnItemClicked(Cita aCita);
    }

    public CitaRVAdapter(Context context, ArrayList<Cita> comments){
        mDataset = comments;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_citas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Cita aCita = mDataset.get(position);
        holder.txtTitulo.setText("Titulo: "+aCita.getTitulo());
        holder.txtFecha.setText("Fecha: "+aCita.getFecha());
        holder.txtDetalle.setText("Detalle: "+aCita.getDetalle());
        /*holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mContext instanceof PostRVAdapterListener) {
                    ((PostRVAdapterListener) mContext).OnItemClicked(aReceta);
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        TextView txtTitulo;
        TextView txtFecha;
        TextView txtDetalle;

        ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;

            txtTitulo = (TextView) itemView.findViewById(R.id.txtTitulo);
            txtFecha = (TextView) itemView.findViewById(R.id.txtFecha);
            txtDetalle = (TextView) itemView.findViewById(R.id.txtDetalle);
        }
    }
}
