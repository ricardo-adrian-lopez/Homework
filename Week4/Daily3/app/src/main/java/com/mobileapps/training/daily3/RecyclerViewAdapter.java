package com.mobileapps.training.daily3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobileapps.training.daily3.model.Repo;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";
    private Context context;
    private List<Repo> list;

    public RecyclerViewAdapter(ArrayList<Repo> repos, Context applicationContext) {
        this.context=applicationContext;
        this.list = repos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        holder.repoName.setText("Name: " +list.get(position).getName());
        holder.repoId.setText(String.valueOf("ID: " + list.get(position).getId()));
        holder.repoURL.setText("URL: " + list.get(position).getHtmlUrl());
        holder.repoBranch.setText("Branch:" + list.get(position).getDefaultBranch());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView repoId;
        TextView repoName;
        TextView repoURL;
        TextView repoBranch;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            repoId = itemView.findViewById(R.id.repoId);
            repoName = itemView.findViewById(R.id.repoName);
            repoURL = itemView.findViewById(R.id.repoURL);
            repoBranch = itemView.findViewById(R.id.repoDefaultBranch);
            linearLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
