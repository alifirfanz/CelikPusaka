package com.example.celikpusaka;

public class PostModel {
    private String NamaProgram;
    private String TempatProgram;
    private String TarikhProgram;
    private String HariProgram;
    private String MasaProgram;
    private String mImageUrl;

    public PostModel() {
    }

    public PostModel(String namaProgram, String tempatProgram, String tarikhProgram, String hariProgram, String masaProgram, String mImageUrl) {
        NamaProgram = namaProgram;
        TempatProgram = tempatProgram;
        TarikhProgram = tarikhProgram;
        HariProgram = hariProgram;
        MasaProgram = masaProgram;
        this.mImageUrl = mImageUrl;
    }

    public String getNamaProgram() {
        return NamaProgram;
    }

    public void setNamaProgram(String namaProgram) {
        NamaProgram = namaProgram;
    }

    public String getTempatProgram() {
        return TempatProgram;
    }

    public void setTempatProgram(String tempatProgram) {
        TempatProgram = tempatProgram;
    }

    public String getTarikhProgram() {
        return TarikhProgram;
    }

    public void setTarikhProgram(String tarikhProgram) {
        TarikhProgram = tarikhProgram;
    }

    public String getHariProgram() {
        return HariProgram;
    }

    public void setHariProgram(String hariProgram) {
        HariProgram = hariProgram;
    }

    public String getMasaProgram() {
        return MasaProgram;
    }

    public void setMasaProgram(String masaProgram) {
        MasaProgram = masaProgram;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
