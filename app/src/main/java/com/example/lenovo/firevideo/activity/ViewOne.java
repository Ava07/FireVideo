package com.example.lenovo.firevideo.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.adapter.FruitAdapter1;
import com.example.lenovo.firevideo.bean.Fruit;

import java.util.ArrayList;
import java.util.List;

//为主界面的每一个item，生成相应的类，并每个页面分开，做相应的点击处理
public class ViewOne {
    private MainActivity activity;
    private List<Fruit> fruitList = new ArrayList<>();
    private View view;
    public ViewOne(MainActivity activity) {
        this.activity = activity;
        view=activity.getLayoutInflater().inflate(R.layout.activity_recyclerview1, null, false);
        init();
    }

    public View getView() {
        return view;
    }
    public void init(){
       // TextView textView=(TextView) view.findViewById(R.id.txt);//根据获取到的View，实现对控件的获取。
        initFruits();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter1 adapter = new FruitAdapter1(fruitList);
        recyclerView.setAdapter(adapter);
    }
    public void initFruits(){
        for (int i=0;i<2;i++){
            Fruit honey1 = new Fruit("honey1",R.mipmap.bee_01);
            fruitList.add(honey1);
            Fruit honey2 = new Fruit("honey2",R.mipmap.bee_02);
            fruitList.add(honey2);
            Fruit honey3 = new Fruit("honey3",R.mipmap.bee_03);
            fruitList.add(honey3);
            Fruit honey4 = new Fruit("honey4",R.mipmap.bee_04);
            fruitList.add(honey4);
            Fruit honey5 = new Fruit("honey5",R.mipmap.bee_05);
            fruitList.add(honey5);
        }

    }
}
