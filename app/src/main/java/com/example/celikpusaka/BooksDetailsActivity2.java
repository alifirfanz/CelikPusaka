package com.example.celikpusaka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class BooksDetailsActivity2 extends AppCompatActivity {

    private EditText mTitle,mDetails;
    private Button mUpdate,mDelete,mKira;
    private String key,title,details;

    private String name,gender,bakiharta,hartaSuami,hartaIsteri,hartaBapa, hartaIbu, hartaAnakL,hartaAnakP;
    private String harta,bilSuami,bilIsteri,bilBapa, bilIbu, bilAnakL,bilAnakP,pengurusan;

    private EditText mNama, mJantina;
    private EditText mPengurusan, mHarta,mBilSuami,mBilIsteri,mBilBapa,mBilIbu, mBilAnakL, mBilAnakP;
    private TextView mBaki, mSuami, mIsteri, mBapa, mIbu, mAnakL, mAnakP;

    FloatingActionButton backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_details2);

        //get data from firebase
        key =getIntent().getStringExtra("key");
        name =getIntent().getStringExtra("name");
        gender=getIntent().getStringExtra("gender");
        harta=getIntent().getStringExtra("jumharta");
        pengurusan=getIntent().getStringExtra("pengurusan");
        bakiharta=getIntent().getStringExtra("bakiharta");
        hartaSuami=getIntent().getStringExtra("hartaSuami");
        hartaIsteri=getIntent().getStringExtra("hartaIsteri");
        hartaBapa=getIntent().getStringExtra("hartaBapa");
        hartaIbu=getIntent().getStringExtra("hartaIbu");
        hartaAnakL=getIntent().getStringExtra("hartaAnakL");
        hartaAnakP=getIntent().getStringExtra("hartaAnakP");
        bilSuami=getIntent().getStringExtra("suami");
        bilIsteri=getIntent().getStringExtra("isteri");
        bilBapa=getIntent().getStringExtra("bapa");
        bilIbu=getIntent().getStringExtra("ibu");
        bilAnakL=getIntent().getStringExtra("anakLelaki");
        bilAnakP=getIntent().getStringExtra("anakPerempuan");


        //declare
        mNama=findViewById(R.id.namasimati);
        mNama.setText(name);
        mJantina =findViewById(R.id.jantinasimati);
        mJantina.setText(gender);
        mHarta =findViewById(R.id.totalharta);
        mHarta.setText(harta);
        mPengurusan= findViewById(R.id.pengurusanjenazah);
        mPengurusan.setText(pengurusan);

        mBaki =findViewById(R.id.bakihartaText);
        mBaki.setText(bakiharta);
        mSuami = findViewById(R.id.suamiText);
        mSuami.setText(hartaSuami);
        mIsteri = findViewById(R.id.isteriText);
        mIsteri.setText(hartaIsteri);
        mBapa =findViewById(R.id.bapaText);
        mBapa.setText(hartaBapa);
        mIbu = findViewById(R.id.ibuText);
        mIbu.setText(hartaIbu);
        mAnakL = findViewById(R.id.anakLText);
        mAnakL.setText(hartaAnakL);
        mAnakP = findViewById(R.id.anakPText);
        mAnakP.setText(hartaAnakP);

        mBilSuami = findViewById(R.id.suami);
        mBilSuami.setText(bilSuami);
        mBilIsteri = findViewById(R.id.isteri);
        mBilIsteri.setText(bilIsteri);
        mBilBapa = findViewById(R.id.bapa);
        mBilBapa .setText(bilBapa);
        mBilIbu = findViewById(R.id.ibu);
        mBilIbu.setText(bilIbu);
        mBilAnakL = findViewById(R.id.anaklelaki);
        mBilAnakL.setText(bilAnakL);
        mBilAnakP = findViewById(R.id.anakperempuan);
        mBilAnakP.setText(bilAnakP);

        mDelete =(Button)findViewById(R.id.delete_btn);
        mUpdate =(Button)findViewById(R.id.update_btn);
        mKira =(Button)findViewById(R.id.kira_btn);
        backbtn=findViewById(R.id.backbtn);


        mKira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateName() | !validateGender() | !validateHarta() | !validatePengurusan() | !validateBilSuami()
                        | !validateBilIsteri() | !validateBilBapa()| !validateBilIbu()| !validateBilAnakL() | !validateBilAnakP()){
                    Toast toast = Toast.makeText(getApplicationContext(), "Sila isi setiap ruangan!", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
                    toast.show(); // display the Toast
                    return;
                }

                else if(Integer.parseInt(mBilAnakL.getText().toString()) == 0 && Integer.parseInt(mBilAnakP.getText().toString()) ==0) {

                    //baca nilai harta dan perbelanjaan pengurusan jenazah
                    int JumlahHartaSiMati = Integer.parseInt(mHarta.getText().toString());
                    int PengurusanJenazah = Integer.parseInt(mPengurusan.getText().toString());

                    //pengiraan untuk baki harta
                    double BakiHarta1 = JumlahHartaSiMati - PengurusanJenazah;

                    //baca nilai untuk setiap ahli keluarga
                    int JumlahBapa = Integer.parseInt(mBilBapa.getText().toString());
                    int JumlahIbu = Integer.parseInt(mBilIbu.getText().toString());
                    int JumlahIsteri = Integer.parseInt(mBilIsteri.getText().toString());
                    int JumlahSuami = Integer.parseInt(mBilSuami.getText().toString());

                    //pengiraan untuk setiap ahli keluarga
                    double husband = BakiHarta1 * (JumlahSuami * 0.50);
                    double wife = BakiHarta1 * (JumlahIsteri * 0.25);
                    double mom = BakiHarta1 * (JumlahIbu * 0.1667);
                    double dad = BakiHarta1 * (JumlahBapa * 0.1667);

                    //baki perunit utk anak
                    double BakiHartaAnak = BakiHarta1 - (husband + wife + mom + dad);

                    //pengiraan untuk anak lelaki dan perempuan
                    int JumlahAnakLelaki = Integer.parseInt(mBilAnakL.getText().toString());
                    int JumlahAnakPerempuan = Integer.parseInt(mBilAnakP.getText().toString());
                    double perunit = BakiHartaAnak / ((JumlahAnakLelaki * 2) + (JumlahAnakPerempuan * 1));
                    double anakL = perunit * (JumlahAnakLelaki * 0);
                    double anakP = perunit * (JumlahAnakPerempuan * 0);

                    //print values
                    mBaki.setText("RM " + String.format("%.2f", BakiHarta1));
                    mIsteri.setText("RM " + String.format("%.2f", wife) + "  (1/4)");
                    mSuami.setText("RM " + String.format("%.2f", husband) + "  (1/2)");
                    mIbu.setText("RM " + String.format("%.2f", mom) + "  (1/6)");
                    mBapa.setText("RM " + String.format("%.2f", dad) + "  (1/6)");
                    mAnakL.setText("RM 0.00"  + "  (2:1)");
                    mAnakP.setText("RM 0.00" + "  (1/2)");

                }

                else if(Integer.parseInt(mBilAnakL.getText().toString()) == 0 | Integer.parseInt(mBilAnakL.getText().toString()) == 1 &&
                        Integer.parseInt(mBilAnakP.getText().toString()) == 0 | Integer.parseInt(mBilAnakP.getText().toString()) == 1) {

                    //baca nilai harta dan perbelanjaan pengurusan jenazah
                    int JumlahHartaSiMati = Integer.parseInt(mHarta.getText().toString());
                    int PengurusanJenazah = Integer.parseInt(mPengurusan.getText().toString());

                    //pengiraan untuk baki harta
                    double BakiHarta1 = JumlahHartaSiMati - PengurusanJenazah;

                    //baca nilai untuk setiap ahli keluarga
                    int JumlahBapa = Integer.parseInt(mBilBapa.getText().toString());
                    int JumlahIbu = Integer.parseInt(mBilIbu.getText().toString());
                    int JumlahIsteri = Integer.parseInt(mBilIsteri.getText().toString());
                    int JumlahSuami = Integer.parseInt(mBilSuami.getText().toString());

                    //pengiraan untuk setiap ahli keluarga
                    double husband = BakiHarta1 * (JumlahSuami * 0.25);
                    double wife = BakiHarta1 * (JumlahIsteri * 0.125);
                    double mom = BakiHarta1 * (JumlahIbu * 0.1667);
                    double dad = BakiHarta1 * (JumlahBapa * 0.1667);

                    //baki perunit utk anak
                    double BakiHartaAnak = BakiHarta1 - (husband + wife + mom + dad);

                    //pengiraan untuk anak lelaki dan perempuan
                    int JumlahAnakLelaki = Integer.parseInt(mBilAnakL.getText().toString());
                    int JumlahAnakPerempuan = Integer.parseInt(mBilAnakP.getText().toString());
                    double perunit = BakiHartaAnak / ((JumlahAnakLelaki * 2) + (JumlahAnakPerempuan * 1));
                    double anakL = perunit * (JumlahAnakLelaki * 2);
                    double anakP = perunit * (JumlahAnakPerempuan * 0.5);

                    //print values
                    mBaki.setText("RM " + String.format("%.2f", BakiHarta1));
                    mIsteri.setText("RM " + String.format("%.2f", wife) + "  (1/8)");
                    mSuami.setText("RM " + String.format("%.2f", husband) + "  (1/4)");
                    mIbu.setText("RM " + String.format("%.2f", mom) + "  (1/6)");
                    mBapa.setText("RM " + String.format("%.2f", dad) + "  (1/6)");
                    mAnakL.setText("RM " + String.format("%.2f", anakL) + "  (2:1)");
                    mAnakP.setText("RM " + String.format("%.2f", anakP) + "  (1/2)");

                }

                else{

                    //baca nilai harta dan perbelanjaan pengurusan jenazah
                    int JumlahHartaSiMati = Integer.parseInt(mHarta.getText().toString());
                    int PengurusanJenazah = Integer.parseInt(mPengurusan.getText().toString());

                    //pengiraan untuk baki harta
                    double BakiHarta1 = JumlahHartaSiMati - PengurusanJenazah;

                    //baca nilai untuk setiap ahli keluarga
                    int JumlahBapa = Integer.parseInt(mBilBapa.getText().toString());
                    int JumlahIbu = Integer.parseInt(mBilIbu.getText().toString());
                    int JumlahIsteri = Integer.parseInt(mBilIsteri.getText().toString());
                    int JumlahSuami = Integer.parseInt(mBilSuami.getText().toString());

                    //pengiraan untuk setiap ahli keluarga
                    double husband = BakiHarta1 * (JumlahSuami * 0.25);
                    double wife = BakiHarta1 * (JumlahIsteri * 0.125);
                    double mom = BakiHarta1 * (JumlahIbu * 0.1667);
                    double dad = BakiHarta1 * (JumlahBapa * 0.1667);

                    //baki perunit utk anak
                    double BakiHartaAnak = BakiHarta1 - (husband + wife + mom + dad);

                    //pengiraan untuk anak lelaki dan perempuan
                    int JumlahAnakLelaki = Integer.parseInt(mBilAnakL.getText().toString());
                    int JumlahAnakPerempuan = Integer.parseInt(mBilAnakP.getText().toString());
                    double perunit = BakiHartaAnak / ((JumlahAnakLelaki * 2) + (JumlahAnakPerempuan * 1));
                    double anakL = perunit * (JumlahAnakLelaki * 2);
                    double anakP = perunit * (JumlahAnakPerempuan * 0.6667);

                    //print values
                    mBaki.setText("RM " + String.format("%.2f", BakiHarta1));
                    mIsteri.setText("RM " + String.format("%.2f", wife) + "  (1/8)");
                    mSuami.setText("RM " + String.format("%.2f", husband) + "  (1/4)");
                    mIbu.setText("RM " + String.format("%.2f", mom) + "  (1/6)");
                    mBapa.setText("RM " + String.format("%.2f", dad) + "  (1/6)");
                    mAnakL.setText("RM " + String.format("%.2f", anakL) + "  (Asabah)");
                    mAnakP.setText("RM " + String.format("%.2f", anakP) + "  (2/3)");

                }

            }
        });

        //update button
        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateName() | !validateGender() | !validateHarta() | !validatePengurusan() | !validateBilSuami()
                        | !validateBilIsteri() | !validateBilBapa() | !validateBilIbu() | !validateBilAnakL() | !validateBilAnakP()){
                    Toast toast = Toast.makeText(getApplicationContext(), "Sila isi setiap ruangan!", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
                    toast.show(); // display the Toast
                    return;
                }

                Book2 book = new Book2();

                book.setName(mNama.getText().toString());
                book.setGender(mJantina.getText().toString());
                book.setJumharta(mHarta.getText().toString());
                book.setPengurusan(mPengurusan.getText().toString());

                book.setSuami(mBilSuami.getText().toString());
                book.setIsteri(mBilIsteri.getText().toString());
                book.setBapa(mBilBapa.getText().toString());
                book.setIbu(mBilIbu.getText().toString());
                book.setAnakLelaki(mBilAnakL.getText().toString());
                book.setAnakPerempuan(mBilAnakP.getText().toString());

                book.setBakiharta(mBaki.getText().toString());
                book.setHartaIsteri(mIsteri.getText().toString());
                book.setHartaSuami(mSuami.getText().toString());

                book.setHartaBapa(mBapa.getText().toString());
                book.setHartaIbu(mIbu.getText().toString());
                book.setHartaAnakL(mAnakL.getText().toString());
                book.setHartaAnakP(mAnakP.getText().toString());

                new FirebaseDatabaseHelper2().updateBook(key, book, new FirebaseDatabaseHelper2.DataStatus1() {
                    @Override
                    public void DataIsLoaded(List<Book2> books, List<String> keys) {

                    }
                    @Override
                    public void DataIsInserted() {

                    }
                    @Override
                    public void DataIsUpdated() {
                        Intent i =new Intent(BooksDetailsActivity2.this,BookListActivity2.class);
                        Toast.makeText(BooksDetailsActivity2.this,"Laporan Harta berjaya dikemaskini",Toast.LENGTH_LONG).show();
                        startActivity(i);

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });


        //delete button
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelper2().deleteBook(key, new FirebaseDatabaseHelper2.DataStatus1() {
                    @Override
                    public void DataIsLoaded(List<Book2> books, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Intent i =new Intent(BooksDetailsActivity2.this,BookListActivity2.class);
                        Toast.makeText(BooksDetailsActivity2.this,"Laporan berjaya dipadam",Toast.LENGTH_LONG).show();
                        startActivity(i);
                        finish();
                        return;
                    }
                });
            }
        });
    }



    private boolean validateBilAnakP() {
        String val = mBilAnakP.getText().toString();

        if (val.isEmpty()){
            mBilAnakP.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            mBilAnakP.setError(null);
            return true;
        }
    }

    private boolean validateBilAnakL() {
        String val = mBilAnakL.getText().toString();

        if (val.isEmpty()){
            mBilAnakL.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            mBilAnakL.setError(null);
            return true;
        }
    }

    private boolean validateBilIbu() {
        String val = mBilIbu.getText().toString();

        if (val.isEmpty()){
            mBilIbu.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            mBilIbu.setError(null);
            return true;
        }
    }

    private boolean validateBilBapa() {
        String val = mBilBapa.getText().toString();

        if (val.isEmpty()){
            mBilBapa.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            mBilBapa.setError(null);
            return true;
        }
    }

    private boolean validateBilIsteri() {
        String val = mBilIsteri.getText().toString();

        if (val.isEmpty()){
            mBilIsteri.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            mBilIsteri.setError(null);
            return true;
        }
    }

    private boolean validateBilSuami() {
        String val = mBilSuami.getText().toString();

        if (val.isEmpty()){
            mBilSuami.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            mBilSuami.setError(null);
            return true;
        }
    }

    private boolean validateGender() {
        String val = mJantina.getText().toString();

        if (val.isEmpty()){
            mJantina.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            mJantina.setError(null);
            return true;
        }
    }

    private boolean validateName() {
        String val = mNama.getText().toString();

        if (val.isEmpty()){
            mNama.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            mNama.setError(null);
            return true;
        }
    }

    private boolean validateHarta(){
        String val = mHarta.getText().toString();

        if (val.isEmpty()){
            mHarta.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            mHarta.setError(null);
            return true;
        }
    }

    private boolean validatePengurusan(){
        String val = mPengurusan.getText().toString();

        if (val.isEmpty()){
            mPengurusan.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            mPengurusan.setError(null);
            return true;
        }
    }


}