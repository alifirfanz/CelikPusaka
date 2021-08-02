package com.example.celikpusaka.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celikpusaka.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PengiraanHarta extends AppCompatActivity {

    EditText namasimati,jantinasimati;
    EditText JumlahHarta,Pengurusan;
    EditText Suami,Isteri,Ibu,Bapa,anakP,anakL;

    TextView BakiHarta, Dad,Mom,Wife,Husband;
    TextView AnakL,AnakP;
    TextView NamaSiMati,JantinaSiMati,ICSiMati;
    Button CalBtn,Resetbtn,Savebtn;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengiraan_harta);

       reference = FirebaseDatabase.getInstance().getReference().child("pengiraan");
       // reference = FirebaseDatabase.getInstance().getReference("pengiraan").child("alifirfanz");

        namasimati=findViewById(R.id.namasimati);
        jantinasimati=findViewById(R.id.jantinasimati);

        JumlahHarta=findViewById(R.id.totalharta);
        Pengurusan=findViewById(R.id.pengurusanjenazah);


        Suami=findViewById(R.id.suami);
        Isteri=findViewById(R.id.isteri);
        Ibu=findViewById(R.id.ibu);
        Bapa=findViewById(R.id.bapa);
        anakL=findViewById(R.id.anaklelaki);
        anakP=findViewById(R.id.anakperempuan);

        CalBtn=findViewById(R.id.kirajumlah);

        BakiHarta=findViewById(R.id.bakihartaText);
        Mom=findViewById(R.id.ibuText);
        Dad=findViewById(R.id.bapaText);
        Husband=findViewById(R.id.suamiText);
        Wife=findViewById(R.id.isteriText);
        AnakL=findViewById(R.id.anakLText);
        AnakP=findViewById(R.id.anakPText);
        Resetbtn=findViewById(R.id.resetjumlah);
        Savebtn=findViewById(R.id.save);

        NamaSiMati=findViewById(R.id.namasimati);
        JantinaSiMati=findViewById(R.id.jantinasimati);

        //clear btn
        Resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pengiraan harta
                namasimati.setText("");
                jantinasimati.setText("");
                JumlahHarta.setText("");
                Suami.setText("");
                Isteri.setText("");
                Ibu.setText("");
                Bapa.setText("");
                anakL.setText("");
                anakP.setText("");
                Pengurusan.setText("");

                BakiHarta.setText("");
                Husband.setText("");
                Wife.setText("");
                Dad.setText("");
                Mom.setText("");
                AnakP.setText("");
                AnakL.setText("");
                NamaSiMati.setText("");
                JantinaSiMati.setText("");

            }
        });

        //save btn into firebase
        Savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });


        //calculate btn
        CalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateName() | !validateGender() | !validateHarta() | !validatePengurusan() | !validateSuami() | !validateIsteri() | !validateBapa() | !validateIbu()
                        | !validateAnakL() | !validateAnakP() ){
                    Toast toast = Toast.makeText(getApplicationContext(), "Sila isi setiap ruangan!", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
                    toast.show(); // display the Toast
                    return;
                }

                //baca nilai harta dan perbelanjaan pengurusan jenazah
                int JumlahHartaSiMati = Integer.parseInt(JumlahHarta.getText().toString());
                int PengurusanJenazah = Integer.parseInt(Pengurusan.getText().toString());

                //pengiraan untuk baki harta
                double BakiHarta1 = JumlahHartaSiMati-PengurusanJenazah;

                //baca nilai untuk setiap ahli keluarga
                int JumlahBapa = Integer.parseInt(Bapa.getText().toString());
                int JumlahIbu = Integer.parseInt(Ibu.getText().toString());
                int JumlahIsteri = Integer.parseInt(Isteri.getText().toString());
                int JumlahSuami = Integer.parseInt(Suami.getText().toString());

                //pengiraan untuk setiap ahli keluarga
                double husband= BakiHarta1*(JumlahSuami*0.25);
                double wife= BakiHarta1*(JumlahIsteri*0.125);
                double mom= BakiHarta1*(JumlahIbu*0.1667);
                double dad= BakiHarta1*(JumlahBapa*0.1667);

                //baki perunit utk anak
                double BakiHartaAnak = BakiHarta1-(husband+wife+mom+dad);

                //pengiraan untuk anak lelaki dan perempuan
                int JumlahAnakLelaki= Integer.parseInt(anakL.getText().toString());
                int JumlahAnakPerempuan= Integer.parseInt(anakP.getText().toString());
                double perunit=BakiHartaAnak/((JumlahAnakLelaki*2)+(JumlahAnakPerempuan*1));
                double anakL=perunit*(JumlahAnakLelaki*2);
                double anakP=perunit*(JumlahAnakPerempuan*1);

                //print values
              //  BakiHarta.setText("RM" +String.format("%.2f", BakiHarta1));

                BakiHarta.setText("RM" +String.format("%.2f", BakiHarta1));
                Wife.setText("RM" +String.format("%.2f", wife));
                Husband.setText("RM" +String.format("%.2f", husband));
                Mom.setText("RM" +String.format("%.2f", mom));
                Dad.setText("RM" + String.format("%.2f", dad));
                AnakL.setText("RM" + String.format("%.2f", anakL));
                AnakP.setText("RM" + String.format("%.2f", anakP));


            }
        });
    }

    //insert data
    private void insertData(){

        if (!validateName() | !validateGender() | !validateHarta() | !validatePengurusan() | !validateSuami() | !validateIsteri() | !validateBapa() | !validateIbu()
                | !validateAnakL() | !validateAnakP() ){
            Toast toast = Toast.makeText(getApplicationContext(), "Sila isi setiap ruangan!", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
            toast.show(); // display the Toast
            return;
        }

        String name =NamaSiMati.getText().toString();
        String gender =JantinaSiMati.getText().toString();

        String jumharta =JumlahHarta.getText().toString();
        String pengurusan =Pengurusan.getText().toString();

        String suami =Suami.getText().toString();
        String isteri =Isteri.getText().toString();
        String bapa =Bapa.getText().toString();
        String ibu =Ibu.getText().toString();
        String anakPerempuan =anakP.getText().toString();
        String anakLelaki =anakL.getText().toString();


        String bakiharta = BakiHarta.getText().toString();
        String hartaIbu = Mom.getText().toString();
        String hartaBapa = Dad.getText().toString();
        String hartaSuami = Husband.getText().toString();
        String hartaIsteri = Wife.getText().toString();
        String hartaAnakL = AnakL.getText().toString();
        String hartaAnakP = AnakP.getText().toString();


        Pengiraan pengiraan= new Pengiraan( name, gender, jumharta, pengurusan,suami, isteri, bapa,ibu, anakPerempuan,
                anakLelaki, bakiharta, hartaIbu,hartaBapa,hartaIsteri, hartaSuami, hartaAnakL, hartaAnakP);

        Intent intent = new Intent(PengiraanHarta.this, DashboardPengiraanHarta.class);
        reference.push().setValue(pengiraan);
        Toast.makeText(PengiraanHarta.this,"Pengiraan Berjaya disimpan!",Toast.LENGTH_SHORT).show();

        startActivity(intent);

    }

    private Boolean validateAnakP(){
        String val = anakL.getText().toString();

        if (val.isEmpty()){
            anakL.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            anakL.setError(null);
            return true;
        }
    }

    private Boolean validateAnakL() {
        String val = anakP.getText().toString();

        if (val.isEmpty()){
            anakP.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            anakP.setError(null);
            return true;
        }
    }

    private Boolean validateIbu() {
        String val = Ibu.getText().toString();

        if (val.isEmpty()){
            Ibu.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            Ibu.setError(null);
            return true;
        }
    }

    private Boolean validateBapa() {
        String val = Bapa.getText().toString();

        if (val.isEmpty()){
            Bapa.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            Bapa.setError(null);
            return true;
        }
    }

    private Boolean validateIsteri() {
        String val = Isteri.getText().toString();

        if (val.isEmpty()){
            Isteri.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            Isteri.setError(null);
            return true;
        }
    }

    private Boolean validateSuami() {
        String val = Suami.getText().toString();

        if (val.isEmpty()){
            Suami.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            Suami.setError(null);
            return true;
        }
    }

    private Boolean validatePengurusan() {
        String val = Pengurusan.getText().toString();

        if (val.isEmpty()){
            Pengurusan.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            Pengurusan.setError(null);
            return true;
        }
    }

    private Boolean validateHarta() {
        String val = JumlahHarta.getText().toString();

        if (val.isEmpty()){
            JumlahHarta.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            JumlahHarta.setError(null);
            return true;
        }
    }

    private Boolean validateName(){
        String val = NamaSiMati.getText().toString();

        if (val.isEmpty()){
            NamaSiMati.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            NamaSiMati.setError(null);
            return true;
        }
    }

    private Boolean validateGender(){
        String val = JantinaSiMati.getText().toString();

        if (val.isEmpty()){
            JantinaSiMati.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            JantinaSiMati.setError(null);
            return true;
        }
    }

}
