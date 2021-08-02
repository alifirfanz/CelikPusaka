package com.example.celikpusaka.Admin;

public class VideoAdmin {
    private String name;
    private String Videourl;

    public VideoAdmin() {
    }

    public VideoAdmin(String name, String videourl) {
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
