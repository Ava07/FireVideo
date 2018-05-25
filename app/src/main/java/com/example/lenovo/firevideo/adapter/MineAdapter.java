package com.example.lenovo.firevideo.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.activity.VideoPlayerActivity;
import com.example.lenovo.firevideo.bean.Like;
import com.example.lenovo.firevideo.bean.Video;

import java.util.List;

public class MineAdapter  extends RecyclerView.Adapter<MineAdapter.ViewHolder>{
    private List<Video> videoList;
    String VideoUrl;
    String VideoId;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView videoface;
        View mineView;
        public ViewHolder(View view){
            super(view);
            mineView = view;
            videoface = (ImageView)view.findViewById(R.id.videoface);
        }
    }
    public MineAdapter(List<Video> videoList1){
        videoList=videoList1;
    }
    @Override
    public MineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.userinfo_item,parent,false);
        final MineAdapter.ViewHolder holder = new MineAdapter.ViewHolder(view);
        holder.videoface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Video video = videoList.get(position);
                VideoUrl = video.getVideoUrl();
                VideoId = video.getVideoId();
                Intent intent = new Intent(view.getContext(), VideoPlayerActivity.class);
                intent.putExtra("VideoUrl",VideoUrl);
                intent.putExtra("VideoId",VideoId);
                view.getContext().startActivity(intent);
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(MineAdapter.ViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(MineAdapter.ViewHolder holder, int position, List<Object> payloads) {
        Video video = videoList.get(position);
        //holder.fruitImage.setImageResource(fruit.getFruit_image());
        Glide.with(holder.videoface.getContext()).load(video.getVideoFace()).into(holder.videoface);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

}
