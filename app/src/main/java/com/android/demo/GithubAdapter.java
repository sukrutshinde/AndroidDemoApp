package com.android.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Sukrut on 9/14/2017.
 */

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.GithubViewHolder>  {

    private  Context context;
    private User[] data;
    GithubAdapter(Context context,User[] data)
    {
        this.context=context;
        this.data=data;
    }
    @Override
    public GithubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.githublayout,parent,false);
        return new GithubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GithubViewHolder holder, int position) {

        User user=data[position];
        holder.textView.setText(user.getLogin());
        Glide.with(holder.imageView.getContext()).load(user.getAvatarUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class GithubViewHolder extends  RecyclerView.ViewHolder
    {

        ImageView imageView;
        TextView textView;
        public GithubViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.user);
            textView= (TextView) itemView.findViewById(R.id.textuser);

        }
    }

}
