package com.example.lenovo.firevideo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.bean.Fruit;
import com.example.lenovo.firevideo.bean.UserInf;
import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder>{
    private List<UserInf> userInfList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView head;
        TextView tv_username;
        View groupView;
        public ViewHolder(View view){
            super(view);
            groupView = view;
            head = (ImageView)view.findViewById(R.id.head);
            tv_username = (TextView)view.findViewById(R.id.tv_username);
        }
    }
    public GroupAdapter(List<UserInf> userInfs){
        userInfList=userInfs;
    }
    @Override
    public GroupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_item,parent,false);
        final GroupAdapter.ViewHolder holder = new GroupAdapter.ViewHolder(view);
        holder.head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                UserInf userInf = userInfList.get(position);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(GroupAdapter.ViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(GroupAdapter.ViewHolder holder, int position, List<Object> payloads) {
        UserInf userInf = userInfList.get(position);
        //holder.fruitImage.setImageResource(fruit.getFruit_image());
        Glide.with(holder.head.getContext()).load(userInf.getUserHead()).into(holder.head);
        holder.tv_username.setText(userInf.getUsername());
    }

    @Override
    public int getItemCount() {
        return userInfList.size();
    }

}
