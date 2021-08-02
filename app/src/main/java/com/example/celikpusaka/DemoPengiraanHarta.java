package com.example.celikpusaka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celikpusaka.user.DashboardPengiraanHarta;
import com.example.celikpusaka.user.Pengiraan;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DemoPengiraanHarta extends AppCompatActivity {

    EditText Namasimati;
    EditText JumlahHarta, Pengurusan;
    TextView BakiHarta, Dad, Mom, Wife, Husband;
    TextView AnakL, AnakP;
    TextView NamaSiMati;
    Button CalBtn, Resetbtn, Savebtn;

    DatabaseReference reference;
    Spinner jantinasimatiSpinner, suamiSpinner, isteriSpinner, ibuSpinner, bapaSpinner, anakPSpinner, anakLSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_pengiraan_harta);

        //database
        reference = FirebaseDatabase.getInstance().getReference().child("pengiraan").child("alifirfanz");
        //reference = FirebaseDatabase.getInstance().getReference().child("pengiraanharta");

        //hooks
        Namasimati = findViewById(R.id.namasimati);
        jantinasimatiSpinner = findViewById(R.id.jantinasimati);
        suamiSpinner = findViewById(R.id.suami);
        isteriSpinner = findViewById(R.id.isteri);
        bapaSpinner = findViewById(R.id.bapa);
        ibuSpinner = findViewById(R.id.ibu);
        anakLSpinner = findViewById(R.id.anaklelaki);
        anakPSpinner = findViewById(R.id.anakperempuan);

        JumlahHarta = findViewById(R.id.totalharta);
        Pengurusan = findViewById(R.id.pengurusanjenazah);

        Resetbtn = findViewById(R.id.resetjumlah);
        Savebtn = findViewById(R.id.save);
        CalBtn=findViewById(R.id.kirajumlah);

        BakiHarta=findViewById(R.id.bakihartaText);
        Mom=findViewById(R.id.ibuText);
        Dad=findViewById(R.id.bapaText);
        Husband=findViewById(R.id.suamiText);
        Wife=findViewById(R.id.isteriText);
        AnakL=findViewById(R.id.anakLText);
        AnakP=findViewById(R.id.anakPText);

        //reset view
        NamaSiMati = findViewById(R.id.namasimati);


        //spinner gender
        ArrayAdapter<String> myAdapterGender = new ArrayAdapter<String>(DemoPengiraanHarta.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.gender));
        myAdapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jantinasimatiSpinner.setAdapter(myAdapterGender);

        //spinner suami
        ArrayAdapter<String> myAdapterSuami = new ArrayAdapter<String>(DemoPengiraanHarta.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.suami));
        myAdapterSuami.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        suamiSpinner.setAdapter(myAdapterSuami);

        //spinner isteri
        ArrayAdapter<String> myAdapterIsteri = new ArrayAdapter<String>(DemoPengiraanHarta.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.isteri));
        myAdapterIsteri.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        isteriSpinner.setAdapter(myAdapterIsteri);

        //spinner bapa
        ArrayAdapter<String> myAdapterBapa = new ArrayAdapter<String>(DemoPengiraanHarta.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.bapa));
        myAdapterBapa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bapaSpinner.setAdapter(myAdapterBapa);

        //spinner ibu
        ArrayAdapter<String> myAdapterIbu = new ArrayAdapter<String>(DemoPengiraanHarta.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.ibu));
        myAdapterIbu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ibuSpinner.setAdapter(myAdapterIbu);

        //spinner anak l
        ArrayAdapter<String> myAdapterAnakL = new ArrayAdapter<String>(DemoPengiraanHarta.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.anaklelaki));
        myAdapterAnakL.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        anakLSpinner.setAdapter(myAdapterAnakL);

        //spinner anak P
        ArrayAdapter<String> myAdapterAnakP = new ArrayAdapter<String>(DemoPengiraanHarta.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.anakperempuan));
        myAdapterAnakP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        anakPSpinner.setAdapter(myAdapterAnakP);


        //save btn into firebase
        Savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });


        //clear btn
        Resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //maklumat si mati
                Namasimati.setText("");
                JumlahHarta.setText("");
                Pengurusan.setText("");

                BakiHarta.setText("");
                Husband.setText("");
                Wife.setText("");
                Dad.setText("");
                Mom.setText("");
                AnakP.setText("");
                AnakL.setText("");
            }
        });


        //calculate btn
        CalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //hooks declare string
                String suami = suamiSpinner.getSelectedItem().toString();
                String isteri = isteriSpinner.getSelectedItem().toString();
                String bapa = bapaSpinner.getSelectedItem().toString();
                String ibu = ibuSpinner.getSelectedItem().toString();
                String anakPerempuan = anakPSpinner.getSelectedItem().toString();
                String anakLelaki = anakLSpinner.getSelectedItem().toString();
                String gender = jantinasimatiSpinner.getSelectedItem().toString();

                //validatename, harta and pengurusan harta
                if (!validateName() | !validateHarta() | !validatePengurusan()){
                    Toast toast = Toast.makeText(getApplicationContext(), "Sila isi setiap ruangan!", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
                    toast.show(); // display the Toast
                    return;
                }

                //validate suami,isteri,bapa,ibu,anakL,anakP,gender
                if(gender.equals("Pilih Jantina")){
                    Toast.makeText(DemoPengiraanHarta.this,"Sila pilih Jantina",Toast.LENGTH_SHORT).show();
                }
                if(suami.equals("Suami")){
                    Toast.makeText(DemoPengiraanHarta.this,"Sila pilih bilangan Suami",Toast.LENGTH_SHORT).show();
                }
                if(isteri.equals("Isteri")){
                    Toast.makeText(DemoPengiraanHarta.this,"Sila pilih bilangan Isteri",Toast.LENGTH_SHORT).show();
                }
                if(bapa.equals("Bapa")){
                    Toast.makeText(DemoPengiraanHarta.this,"Sila pilih bilangan Bapa",Toast.LENGTH_SHORT).show();
                }
                if(ibu.equals("Ibu")){
                    Toast.makeText(DemoPengiraanHarta.this,"Sila pilih bilangan Ibu",Toast.LENGTH_SHORT).show();
                }
                if(anakPerempuan.equals("Anak Perempuan")){
                    Toast.makeText(DemoPengiraanHarta.this,"Sila pilih bilangan Anak Perempuan",Toast.LENGTH_SHORT).show();
                }
                if(anakLelaki.equals("Anak Lelaki")){
                    Toast.makeText(DemoPengiraanHarta.this,"Sila pilih bilangan Anak Lelaki",Toast.LENGTH_SHORT).show();
                }

                else if(anakLelaki.equals("0") && anakPerempuan.equals("0") ){

                    //baca nilai harta dan perbelanjaan pengurusan jenazah
                    int JumlahHartaSiMati = Integer.parseInt(JumlahHarta.getText().toString());
                    int PengurusanJenazah = Integer.parseInt(Pengurusan.getText().toString());

                    //pengiraan untuk baki harta
                    double BakiHarta1 = JumlahHartaSiMati - PengurusanJenazah;

                    //baca nilai untuk setiap ahli keluarga
                    int JumlahBapa = Integer.parseInt(bapaSpinner.getSelectedItem().toString());
                    int JumlahIbu = Integer.parseInt(ibuSpinner.getSelectedItem().toString());
                    int JumlahIsteri = Integer.parseInt(isteriSpinner.getSelectedItem().toString());
                    int JumlahSuami = Integer.parseInt(suamiSpinner.getSelectedItem().toString());

                    //pengiraan untuk setiap ahli keluarga
                    double husband = BakiHarta1 * (JumlahSuami * 0.50);
                    double wife = BakiHarta1 * (JumlahIsteri * 0.25);
                    double mom = BakiHarta1 * (JumlahIbu * 0.1667);
                    double dad = BakiHarta1 * (JumlahBapa * 0.1667);

                    //baki perunit utk anak
                    double BakiHartaAnak = BakiHarta1 - (husband + wife + mom + dad);

                    //pengiraan untuk anak lelaki dan perempuan
                    int JumlahAnakLelaki = Integer.parseInt(anakLSpinner.getSelectedItem().toString());
                    int JumlahAnakPerempuan = Integer.parseInt(anakPSpinner.getSelectedItem().toString());
                    double perunit = BakiHartaAnak / ((JumlahAnakLelaki * 2) + (JumlahAnakPerempuan * 1));
                    double anakL = perunit * (JumlahAnakLelaki * 0);
                    double anakP = perunit * (JumlahAnakPerempuan * 0);

                    //print values
                    BakiHarta.setText("RM " + String.format("%.2f", BakiHarta1));
                    Wife.setText("RM " + String.format("%.2f", wife) + "  (1/4)");
                    Husband.setText("RM " + String.format("%.2f", husband) + "  (1/2)");
                    Mom.setText("RM " + String.format("%.2f", mom) + "  (1/6)");
                    Dad.setText("RM " + String.format("%.2f", dad) + "  (1/6)");
                    AnakL.setText("RM 0.00" + " (2:1)");
                    AnakP.setText("RM 0.00" + "  (1/2)");
                }


                else if(  anakLelaki.equals("1")| anakLelaki.equals("0") && anakPerempuan.equals("0") | anakPerempuan.equals("1") ){
                    //baca nilai harta dan perbelanjaan pengurusan jenazah
                    int JumlahHartaSiMati = Integer.parseInt(JumlahHarta.getText().toString());
                    int PengurusanJenazah = Integer.parseInt(Pengurusan.getText().toString());

                    //pengiraan untuk baki harta
                    double BakiHarta1 = JumlahHartaSiMati - PengurusanJenazah;

                    //baca nilai untuk setiap ahli keluarga
                    int JumlahBapa = Integer.parseInt(bapaSpinner.getSelectedItem().toString());
                    int JumlahIbu = Integer.parseInt(ibuSpinner.getSelectedItem().toString());
                    int JumlahIsteri = Integer.parseInt(isteriSpinner.getSelectedItem().toString());
                    int JumlahSuami = Integer.parseInt(suamiSpinner.getSelectedItem().toString());

                    //pengiraan untuk setiap ahli keluarga
                    double husband = BakiHarta1 * (JumlahSuami * 0.25);
                    double wife = BakiHarta1 * (JumlahIsteri * 0.125);
                    double mom = BakiHarta1 * (JumlahIbu * 0.1667);
                    double dad = BakiHarta1 * (JumlahBapa * 0.1667);

                    //baki perunit utk anak
                    double BakiHartaAnak = BakiHarta1 - (husband + wife + mom + dad);

                    //pengiraan untuk anak lelaki dan perempuan
                    int JumlahAnakLelaki = Integer.parseInt(anakLSpinner.getSelectedItem().toString());
                    int JumlahAnakPerempuan = Integer.parseInt(anakPSpinner.getSelectedItem().toString());
                    double perunit = BakiHartaAnak / ((JumlahAnakLelaki * 2) + (JumlahAnakPerempuan * 1));
                    double anakL = perunit * (JumlahAnakLelaki * 2);
                    double anakP = perunit * (JumlahAnakPerempuan * 0.5);

                    //print values
                    BakiHarta.setText("RM " + String.format("%.2f", BakiHarta1) );
                    Wife.setText("RM " + String.format("%.2f", wife) + "  (1/8)" );
                    Husband.setText("RM " + String.format("%.2f", husband) + "  (1/4)");
                    Mom.setText("RM " + String.format("%.2f", mom) + "  (1/6)");
                    Dad.setText("RM " + String.format("%.2f", dad) + "  (1/6)");
                    AnakL.setText("RM " + String.format("%.2f", anakL) + "  (2:1)");
                    AnakP.setText("RM " + String.format("%.2f", anakP) + "  (1/2)");

                }


                else {
                    //baca nilai harta dan perbelanjaan pengurusan jenazah
                    int JumlahHartaSiMati = Integer.parseInt(JumlahHarta.getText().toString());
                    int PengurusanJenazah = Integer.parseInt(Pengurusan.getText().toString());

                    //pengiraan untuk baki harta
                    double BakiHarta1 = JumlahHartaSiMati - PengurusanJenazah;

                    //baca nilai untuk setiap ahli keluarga
                    int JumlahBapa = Integer.parseInt(bapaSpinner.getSelectedItem().toString());
                    int JumlahIbu = Integer.parseInt(ibuSpinner.getSelectedItem().toString());
                    int JumlahIsteri = Integer.parseInt(isteriSpinner.getSelectedItem().toString());
                    int JumlahSuami = Integer.parseInt(suamiSpinner.getSelectedItem().toString());

                    //pengiraan untuk setiap ahli keluarga
                    double husband = BakiHarta1 * (JumlahSuami * 0.25);
                    double wife = BakiHarta1 * (JumlahIsteri * 0.125);
                    double mom = BakiHarta1 * (JumlahIbu * 0.1667);
                    double dad = BakiHarta1 * (JumlahBapa * 0.1667);

                    //baki perunit utk anak
                    double BakiHartaAnak = BakiHarta1 - (husband + wife + mom + dad);

                    //pengiraan untuk anak lelaki dan perempuan
                    int JumlahAnakLelaki = Integer.parseInt(anakLSpinner.getSelectedItem().toString());
                    int JumlahAnakPerempuan = Integer.parseInt(anakPSpinner.getSelectedItem().toString());
                    double perunit = BakiHartaAnak / ((JumlahAnakLelaki * 2) + (JumlahAnakPerempuan * 1));
                    double anakL = perunit * (JumlahAnakLelaki * 2);
                    double anakP = perunit * (JumlahAnakPerempuan * 0.6667);

                    //print values
                    BakiHarta.setText("RM " + String.format("%.2f", BakiHarta1) );
                    Wife.setText("RM " + String.format("%.2f", wife) + "  (1/8)" );
                    Husband.setText("RM " + String.format("%.2f", husband) + "  (1/4)");
                    Mom.setText("RM " + String.format("%.2f", mom) + "  (1/6)");
                    Dad.setText("RM " + String.format("%.2f", dad) + "  (1/6)");
                    AnakL.setText("RM " + String.format("%.2f", anakL) + "  (Asabah)");
                    AnakP.setText("RM " + String.format("%.2f", anakP) + "  (2/3)");
                }


            }
        });
    }


    //method to insert data into firebase
    private void insertData() {

        if (!validateName() |  !validateHarta() | !validatePengurusan() ){
           Toast toast = Toast.makeText(getApplicationContext(), "Sila isi setiap ruangan!", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
           toast.show(); // display the Toast

            return;
        }

        String name = Namasimati.getText().toString();
        String jumharta = JumlahHarta.getText().toString();
        String pengurusan = Pengurusan.getText().toString();

        String suami = suamiSpinner.getSelectedItem().toString();
        String isteri = isteriSpinner.getSelectedItem().toString();
        String bapa = bapaSpinner.getSelectedItem().toString();
        String ibu = ibuSpinner.getSelectedItem().toString();
        String anakPerempuan = anakPSpinner.getSelectedItem().toString();
        String anakLelaki = anakLSpinner.getSelectedItem().toString();
        String gender = jantinasimatiSpinner.getSelectedItem().toString();

        String bakiharta = BakiHarta.getText().toString();
        String hartaIbu = Mom.getText().toString();
        String hartaBapa = Dad.getText().toString();
        String hartaSuami = Husband.getText().toString();
        String hartaIsteri = Wife.getText().toString();
        String hartaAnakL = AnakL.getText().toString();
        String hartaAnakP = AnakP.getText().toString();

        //validate suami,isteri,bapa,ibu,anakL,anakP,gender
        if(gender.equals("Pilih Jantina")){
            Toast.makeText(DemoPengiraanHarta.this,"Sila pilih Jantina",Toast.LENGTH_SHORT).show();
        }
        if(suami.equals("Suami")){
            Toast.makeText(DemoPengiraanHarta.this,"Sila pilih bilangan Suami",Toast.LENGTH_SHORT).show();
        }
        if(isteri.equals("Isteri")){
            Toast.makeText(DemoPengiraanHarta.this,"Sila pilih bilangan Isteri",Toast.LENGTH_SHORT).show();
        }
        if(bapa.equals("Bapa")){
            Toast.makeText(DemoPengiraanHarta.this,"Sila pilih bilangan Bapa",Toast.LENGTH_SHORT).show();
        }
        if(ibu.equals("Ibu")){
            Toast.makeText(DemoPengiraanHarta.this,"Sila pilih bilangan Ibu",Toast.LENGTH_SHORT).show();
        }
        if(anakPerempuan.equals("Anak Perempuan")){
            Toast.makeText(DemoPengiraanHarta.this,"Sila pilih bilangan Anak Perempuan",Toast.LENGTH_SHORT).show();
        }
        if(anakLelaki.equals("Anak Lelaki")){
            Toast.makeText(DemoPengiraanHarta.this,"Sila pilih bilangan Anak Lelaki",Toast.LENGTH_SHORT).show();
        }

        else {

            DemoPengiraan pengiraan = new DemoPengiraan(name, jumharta, pengurusan, suami, isteri, bapa, ibu, anakPerempuan, anakLelaki, gender,
                    bakiharta, hartaIbu, hartaBapa, hartaIsteri, hartaSuami, hartaAnakL, hartaAnakP);

            Intent intent = new Intent(DemoPengiraanHarta.this, DashboardPengiraanHarta.class);
            reference.push().setValue(pengiraan);
            Toast.makeText(DemoPengiraanHarta.this, "Pengiraan Harta Berjaya disimpan!", Toast.LENGTH_SHORT).show();

            startActivity(intent);
        }
    }

    //validate pengurusan, harta dan nama
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


}