package com.example.lenovo.firevideo.bean;

public class Fruit {
    private String fruit_name;
    private String fruitUrl;
    public Fruit(String fruit_name,String fruitUrl){
        this.fruit_name=fruit_name;
        this.fruitUrl=fruitUrl;
    }

    public void setFruit_name(String fruit_name) {
        this.fruit_name = fruit_name;
    }

    public void setFruitUrl(String fruitUrl) {
        this.fruitUrl = fruitUrl;
    }


    public String getFruit_name() {
        return fruit_name;
    }

    public String getFruitUrl() {
        return fruitUrl;
    }
}
