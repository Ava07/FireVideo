package com.example.lenovo.firevideo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.bean.Fruit;

import java.util.List;

public class FruitAdapter2 extends RecyclerView.Adapter<FruitAdapter1.ViewHolder> {
    private List<Fruit> mFruitList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView fruitImage;
        TextView fruitName;
        public ViewHolder(View view){
            super(view);
            fruitImage = (ImageView)view.findViewById(R.id.fruit_image);
            fruitName = (TextView)view.findViewById(R.id.fruit_name);
        }
    }
    public FruitAdapter2(List<Fruit> fruitList){
        mFruitList=fruitList;
    }
    @Override
    public FruitAdapter1.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item2,parent,false);
        FruitAdapter1.ViewHolder holder = new FruitAdapter1.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FruitAdapter1.ViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(FruitAdapter1.ViewHolder holder, int position, List<Object> payloads) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getFruit_image());
        holder.fruitName.setText(fruit.getFruit_name());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
