package com.example.lenovo.firevideo.bean;

public class Fruit {
    private String fruit_name;
    private String fruitUrl;
    private String videoUrl;
    private String videoId;
    public Fruit(String videoId,String fruit_name,String fruitUrl,String videoUrl){
        this.videoId = videoId;
        this.fruit_name=fruit_name;
        this.fruitUrl=fruitUrl;
        this.videoUrl = videoUrl;
    }

//    public Fruit(String fruit_name,String fruitUrl,String videoUrl){
////        this.videoId = videoId;
//        this.fruit_name=fruit_name;
//        this.fruitUrl=fruitUrl;
//        this.videoUrl = videoUrl;
//    }
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setFruit_name(String fruit_name) {
        this.fruit_name = fruit_name;
    }

    public void setFruitUrl(String fruitUrl) {
        this.fruitUrl = fruitUrl;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getFruit_name() {
        return fruit_name;
    }

    public String getFruitUrl() {
        return fruitUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
}
