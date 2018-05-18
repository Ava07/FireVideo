package com.example.lenovo.firevideo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.adapter.FruitAdapter;
import com.example.lenovo.firevideo.bean.Fruit;
import java.util.ArrayList;
import java.util.List;

public class TestFruitActivity extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_recyclerview);
        initFruits();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(fruitList);
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
