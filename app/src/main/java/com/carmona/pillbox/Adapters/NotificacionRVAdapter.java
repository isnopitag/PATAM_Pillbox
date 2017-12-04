package com.carmona.pillbox.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carmona.pillbox.Models.Notificaciones;
import com.carmona.pillbox.Models.Receta;
import com.carmona.pillbox.R;

import java.util.ArrayList;

/**
 * Created by carmona on 3/12/17.
 */

public class NotificacionRVAdapter extends RecyclerView.Adapter<NotificacionRVAdapter.ViewHolder> {

    private ArrayList<Notificaciones> mDataset;
    private Context mContext;

    public interface NotificacionRVAdapterListener{
        void OnItemClicked(Notificaciones aNotificaciones);
    }

    public NotificacionRVAdapter(Context context, ArrayList<Notificaciones> comments){
        mDataset = comments;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_notificaciones, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Notificaciones aNotificaciones= mDataset.get(position);
        holder.txtFecha.setText("Fecha: "+aNotificaciones.getFecha());
        holder.txtMensaje.setText("Mensaje: "+aNotificaciones.getMensaje());
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

        TextView txtFecha;
        TextView txtMensaje;
        ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;

            txtMensaje = (TextView) itemView.findViewById(R.id.txtNotificacion);
            txtFecha = (TextView) itemView.findViewById(R.id.txtFecha);
        }
    }
}
