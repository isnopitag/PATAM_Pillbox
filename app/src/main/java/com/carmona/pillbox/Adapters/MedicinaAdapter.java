package com.carmona.pillbox.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carmona.pillbox.Models.Post;
import com.carmona.pillbox.R;

import java.util.ArrayList;

/**
 * Created by carmona on 25/11/17.
 */

public class MedicinaAdapter extends RecyclerView.Adapter<MedicinaAdapter.ViewHolder> {
    private ArrayList<Post> mDataset;
    private Context mContext;

    public interface MedicinaAdapterListener{
       // void OnItemClicked(Post aPost);
    }

    public MedicinaAdapter(Context context, ArrayList<Post> posts){
        mDataset = posts;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_medicinas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       // final Post aPost = mDataset.get(position);
       // holder.mTvTitle.setText(aPost.getTitle());
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mContext instanceof MedicinaAdapterListener) {
         //           ((PostRVAdapterListener) mContext).OnItemClicked(aPost);
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return mDataset.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        TextView mTvTitle;
        ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            //mTvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }
}
