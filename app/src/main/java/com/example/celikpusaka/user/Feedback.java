package com.example.celikpusaka.user;

public class Feedback {

    String name;
    String comment;
    String suggest;

    public Feedback(String name, String comment, String suggest) {
        this.name = name;
        this.comment = comment;
        this.suggest = suggest;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public String getSuggest() {
        return suggest;
    }
}
