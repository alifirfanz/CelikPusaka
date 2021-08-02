package com.example.celikpusaka.user;

public class model1 {

    String id;
    String name, ic, gender;
    String suami, isteri, bapa, ibu, anakPerempuan, anakLelaki;
    String bakiharta, hartaIbu,hartaBapa,hartaIsteri,hartaSuami,hartaAnakL,hartaAnakP;

   model1(){

    }
    public model1(String id, String name, String ic, String gender, String suami, String isteri, String bapa, String ibu, String anakPerempuan, String anakLelaki, String bakiharta, String hartaIbu, String hartaBapa, String hartaIsteri, String hartaSuami, String hartaAnakL, String hartaAnakP) {
        this.id =id;
        this.name = name;
        this.ic = ic;
        this.gender = gender;
        this.suami = suami;
        this.isteri = isteri;
        this.bapa = bapa;
        this.ibu = ibu;
        this.anakPerempuan = anakPerempuan;
        this.anakLelaki = anakLelaki;

        this.bakiharta = bakiharta;
        this.hartaIbu = hartaIbu;
        this.hartaBapa = hartaBapa;
        this.hartaIsteri = hartaIsteri;
        this.hartaSuami = hartaSuami;
        this.hartaAnakL = hartaAnakL;
        this.hartaAnakP = hartaAnakP;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSuami() {
        return suami;
    }

    public void setSuami(String suami) {
        this.suami = suami;
    }

    public String getIsteri() {
        return isteri;
    }

    public void setIsteri(String isteri) {
        this.isteri = isteri;
    }

    public String getBapa() {
        return bapa;
    }

    public void setBapa(String bapa) {
        this.bapa = bapa;
    }

    public String getIbu() {
        return ibu;
    }

    public void setIbu(String ibu) {
        this.ibu = ibu;
    }

    public String getAnakPerempuan() {
        return anakPerempuan;
    }

    public void setAnakPerempuan(String anakPerempuan) {
        this.anakPerempuan = anakPerempuan;
    }

    public String getAnakLelaki() {
        return anakLelaki;
    }

    public void setAnakLelaki(String anakLelaki) {
        this.anakLelaki = anakLelaki;
    }

    public String getBakiharta() {
        return bakiharta;
    }

    public void setBakiharta(String bakiharta) {
        this.bakiharta = bakiharta;
    }

    public String getHartaIbu() {
        return hartaIbu;
    }

    public void setHartaIbu(String hartaIbu) {
        this.hartaIbu = hartaIbu;
    }

    public String getHartaBapa() {
        return hartaBapa;
    }

    public void setHartaBapa(String hartaBapa) {
        this.hartaBapa = hartaBapa;
    }

    public String getHartaIsteri() {
        return hartaIsteri;
    }

    public void setHartaIsteri(String hartaIsteri) {
        this.hartaIsteri = hartaIsteri;
    }

    public String getHartaSuami() {
        return hartaSuami;
    }

    public void setHartaSuami(String hartaSuami) {
        this.hartaSuami = hartaSuami;
    }

    public String getHartaAnakL() {
        return hartaAnakL;
    }

    public void setHartaAnakL(String hartaAnakL) {
        this.hartaAnakL = hartaAnakL;
    }

    public String getHartaAnakP() {
        return hartaAnakP;
    }

    public void setHartaAnakP(String hartaAnakP) {
        this.hartaAnakP = hartaAnakP;
    }
}
