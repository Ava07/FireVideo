package com.example.lenovo.firevideo.bean;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.firevideo.R;

public class Fruit {
    public int fruit_img_id;
    public String fruit_name;
    public Fruit(String fruit_name,int fruit_img_id){
        this.fruit_name=fruit_name;
        this.fruit_img_id=fruit_img_id;
    }
    public void setFruit_image(int fruit_img_id) {
        this.fruit_img_id = fruit_img_id;
    }

    public void setFruit_name(String fruit_name) {
        this.fruit_name = fruit_name;
    }

    public int getFruit_image() {
        return fruit_img_id;
    }

    public String getFruit_name() {
        return fruit_name;
    }
}
