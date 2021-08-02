package com.example.celikpusaka.user;

public class VideoUser {
        private String name;
        private String Videourl;
        private String search;

    public VideoUser() {
    }

    public VideoUser(String name, String videourl, String search) {
        this.name = name;
        Videourl = videourl;
        this.search = search;
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

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
