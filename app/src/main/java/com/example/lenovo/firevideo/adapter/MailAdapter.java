package com.example.lenovo.firevideo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.bean.Like;
import com.example.lenovo.firevideo.bean.UserInf;

import java.util.List;

public class MailAdapter extends RecyclerView.Adapter<MailAdapter.ViewHolder>{
    private List<Like> likeList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView head;
        TextView tv_username;
        ImageView video;
        View groupView;
        public ViewHolder(View view){
            super(view);
            groupView = view;
            head = (ImageView)view.findViewById(R.id.head);
            tv_username = (TextView)view.findViewById(R.id.tv_username);
            video = (ImageView)view.findViewById(R.id.video);
        }
    }
    public MailAdapter(List<Like> likeList1){
        likeList=likeList1;
    }
    @Override
    public MailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mail_item,parent,false);
        final MailAdapter.ViewHolder holder = new MailAdapter.ViewHolder(view);
        holder.head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Like like = likeList.get(position);
            }
        });
        holder.video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Like like = likeList.get(position);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(MailAdapter.ViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(MailAdapter.ViewHolder holder, int position, List<Object> payloads) {
        Like like = likeList.get(position);
        //holder.fruitImage.setImageResource(fruit.getFruit_image());
        Glide.with(holder.head.getContext()).load(like.getLikeUserHead()).into(holder.head);
        holder.tv_username.setText(like.getLikeUsername());
        Glide.with(holder.video.getContext()).load(like.getVideoFace()).into(holder.video);
    }

    @Override
    public int getItemCount() {
        return likeList.size();
    }

}