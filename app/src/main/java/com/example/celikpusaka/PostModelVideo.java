package com.example.celikpusaka;

public class PostModelVideo {
    private String name;
    private String Videourl;

    public PostModelVideo() {
    }

    public PostModelVideo(String name, String videourl) {
        this.name = name;
        Videourl = videourl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideourl() {
        return Videourl;
    }

    public void setVideourl(String videourl) {
        Videourl = videourl;
    }


}
