package com.example.lenovo.firevideo.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.activity.VideoPlayerActivity;
import com.example.lenovo.firevideo.bean.Fruit;
import com.example.lenovo.firevideo.bean.Video;

import java.util.List;

public class FruitAdapter1 extends RecyclerView.Adapter<FruitAdapter1.ViewHolder> {
    private List<Fruit> mFruitList;
    String VideoUrl;
    String VideoId;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView fruitImage;
        TextView fruitName;
        View fruitView;
        public ViewHolder(View view){
            super(view);
            fruitView = view;
            fruitImage = (ImageView)view.findViewById(R.id.fruit_image);
            fruitName = (TextView)view.findViewById(R.id.fruit_name);
        }
    }
    public FruitAdapter1(List<Fruit> fruitList){
        mFruitList=fruitList;
    }
    @Override
    public FruitAdapter1.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item1,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
            }
        });
        holder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                VideoUrl = fruit.getVideoUrl();
                VideoId = fruit.getVideoId();
                Intent intent = new Intent(view.getContext(), VideoPlayerActivity.class);
                intent.putExtra("VideoUrl",VideoUrl);
                intent.putExtra("VideoId",VideoId);
                view.getContext().startActivity(intent);

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(FruitAdapter1.ViewHolder holder, int position, List<Object> payloads) {
        Fruit fruit = mFruitList.get(position);
        //holder.fruitImage.setImageResource(fruit.getFruit_image());
        Glide.with(holder.fruitImage.getContext()).load(fruit.getFruitUrl()).into(holder.fruitImage);
        holder.fruitName.setText(fruit.getFruit_name());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
