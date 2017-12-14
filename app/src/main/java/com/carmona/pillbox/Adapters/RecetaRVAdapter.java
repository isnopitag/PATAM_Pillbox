package com.carmona.pillbox.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carmona.pillbox.Models.Receta;
import com.carmona.pillbox.R;

import java.util.ArrayList;

/**
 * Created by carmona on 3/12/17.
 */

public class RecetaRVAdapter extends RecyclerView.Adapter<RecetaRVAdapter.ViewHolder> {

    private ArrayList<Receta> mDataset;
    private Context mContext;

    public interface RecetaRVAdapterListener{
        void OnItemClicked(Receta aReceta);
    }

    public RecetaRVAdapter(Context context, ArrayList<Receta> comments){
        mDataset = comments;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_medicinas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Receta aReceta= mDataset.get(position);
        holder.txtMedicina.setText(aReceta.getMedicina());
        holder.txtHora.setText("Tomarse a las: "+aReceta.getHora());
        holder.txtFechaini.setText("Fecha Inicio: "+aReceta.getFechaini());
        holder.txtFechafin.setText("Fecha Fin: "+aReceta.getFechafin());
        holder.txtDescripcion.setText("Dosis: "+aReceta.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        TextView txtMedicina;
        TextView txtHora;
        TextView txtDescripcion;
        TextView txtFechaini;
        TextView txtFechafin;
        ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;

            txtMedicina = (TextView) itemView.findViewById(R.id.txtMedicina);
            txtHora = (TextView) itemView.findViewById(R.id.txtHora);
            txtDescripcion = (TextView) itemView.findViewById(R.id.txtDescripcion);
            txtFechaini = (TextView) itemView.findViewById(R.id.txtFechaini);
            txtFechafin = (TextView) itemView.findViewById(R.id.txtFechafin);
        }
    }
}
