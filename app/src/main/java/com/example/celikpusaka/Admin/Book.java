package com.example.celikpusaka.Admin;

public class Book  {
        private String tajukNota;
        private String maklumatNota;

        public Book() {
        }

        public Book(String tajukNota, String maklumatNota) {
            this.tajukNota = tajukNota;
            this.maklumatNota = maklumatNota;
        }

        public String getTajukNota() {
            return tajukNota;
        }

        public void setTajukNota(String tajukNota) {
            this.tajukNota = tajukNota;
        }

        public String getMaklumatNota() {
            return maklumatNota;
        }

        public void setMaklumatNota(String maklumatNota) {
            this.maklumatNota = maklumatNota;
        }
}

