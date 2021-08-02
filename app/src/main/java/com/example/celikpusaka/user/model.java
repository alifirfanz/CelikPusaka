package com.example.celikpusaka.user;

public class model {

    String name, comment, suggest;

    model()
    {


    }
    public model(String name, String comment, String suggest) {
        this.name = name;
        this.comment = comment;
        this.suggest = suggest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }
}
