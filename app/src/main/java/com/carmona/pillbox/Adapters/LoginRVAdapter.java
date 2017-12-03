package com.carmona.pillbox.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carmona.pillbox.Models.Login;
import com.carmona.pillbox.R;


import java.util.ArrayList;


public class LoginRVAdapter extends RecyclerView.Adapter<LoginRVAdapter.ViewHolder> {
    private ArrayList<Login> mDataset;
    private Context mContext;

    /*public interface PostRVAdapterListener{
        void OnItemClicked(Login aPost);
    }*/

    public LoginRVAdapter(Context context, ArrayList<Login> login){
        mDataset = login;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_medicinas, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Login login = mDataset.get(position);

        //holder.mTvTitle.setText(aPost.getTitle());
        /*holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mContext instanceof PostRVAdapterListener) {
                    ((PostRVAdapterListener) mContext).OnItemClicked(aPost);
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
        TextView mTvTitle;
        ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            //mTvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }
}
