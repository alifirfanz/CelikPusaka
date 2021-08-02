package com.example.celikpusaka.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.celikpusaka.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Nota extends AppCompatActivity {

    FloatingActionButton backBtn;
    RecyclerView recyclerView;
    List<Versions> versionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);

        //hooks
        backBtn=findViewById(R.id.btnback);
        recyclerView = findViewById(R.id.recyclerView);
        initData();
        setRecyclerView();

        //back button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Nota.this, InfoUser.class);
                startActivity(intent);
            }
        });

    }

    private void setRecyclerView() {
        VersionsAdapter versionsAdapter = new VersionsAdapter(versionsList);
        recyclerView.setAdapter(versionsAdapter);
        recyclerView.setHasFixedSize(true);
    }

    private void initData() {
        versionsList = new ArrayList<>();
        versionsList.add(new Versions("Harta Pusaka Kecil" ,"Harta pusaka kecil ialah harta yang ditinggalkan oleh simati dan mempunyai ciri-ciri seperti simati tidak meninggalkan wasiat ( bagi simati bukan Islam ), Harta itu terdiri daripada Harta Tak Alih dan jumlah nilaian keseluruhan harta itu hendaklah tidak melebihi RM2 juta pada tarikh permohonan dibuat.\n"));
        versionsList.add(new Versions("Harta Pusaka Besar" , "1. Harta yang ditinggalkan oleh simati dengan wasiat (kurang atau lebih daripada RM 2 juta)\n\n" + "2. Tanpa wasiat atau sebahagiannya tanpa wasiat\n\n" + "3. Kesemuanya bernilai lebih daripada > RM 2 juta.\n"));
        versionsList.add(new Versions("Pentadbiran Harta Pusaka" , "1. Pengurusan Jenazah.\n\n" + "2. Penyelesaian Hutang-hutang dunia dan akhirat.\n\n" + "3. Harta sepencarian.\n\n" + "4. Hibah.\n\n" + "5. Amanah.\n\n"  + "6. Wasiat 1/3 bahagian kepada bukan waris.\n\n" + "7. Baki agih kepada waris-waris (ikut wasiat/sepakat/faraid).\n"));
        versionsList.add(new Versions("Kesan Lewat Bahagi Harta" , "1. Makan Harta Batil.\n\n" + "2. Hutang Tak Langsai.\n\n" + "3.  Wasiat Tak disempurnakan.\n\n" + "4. Kematian Berlapis (Waris Bertambah).\n\n" + "5. Perbalahan Sesama Waris.\n\n"  + "6. Harta Pusaka Terbiar.\n\n" + "7. Urus Niaga Tanah Tergendala.\n"));
        versionsList.add(new Versions("Waris Layak Terima Harta" , "Waris dari pihak lelaki seperti berikut:  \n" + "\n" + "1.  Anak lelaki  \n" + "2.  Cucu lelaki dari anak lelaki  \n" + "3.  Bapa  \n" + "4.  Datuk  \n" + "5.  Saudara lelaki seibu sebapa  \n" + "6.  Saudara lelaki sebapa  \n" + "7.  Saudara lelaki seibu  \n" + "8.  Anak saudara lelaki dari seibu sebapa  \n" + "9.  Anak saudara lelaki dari seibu atau sebapa \n" + "10. Bapa saudara seibu sebapa \n" + "11. Bapa saudara sebapa \n" + "12. Sepupu dari bapa saudara seibu sebapa \n" + "13. Sepupu dari bapa saudara sebapa 14. Suami \n" + "15. Hamba sahaya   \n" +
                "\n" + "Ahli-ahli waris perempuan yang layak menerima harta pusaka iaitu:  \n" + "1.  Anak perempuan  \n" + "2.  Cucu perempuan dari anak lelaki  \n" + "3.  Ibu  \n" + "4.  Nenek sebelah ibu  \n" + "5.  Nenek sebelah bapa  \n" + "6.  Saudara perempuan seibu sebapa  \n" + "7.  Saudara perempuan seibu  \n" + "8.  Saudara perempuan sebapa  \n" + "9.  Isteri \n" + "10. Petuan perempuan bagi hamba sahaja\n"));
        versionsList.add(new Versions("Punca Lewat Bahagi Harta" , "1. Tamak dan Berkepentingan.\n\n" + "2. Tiada Perancangan Semasa Hidup.\n\n" + "3.  Terlalu Bersopan Santun.\n\n" + "4. Suka Bertangguh-tangguh.\n\n" + "5. Tiada Pengetahuan.\n\n"  + "6. Tidak Prihatin.\n\n" + "7. Gaduh.\n"));
        versionsList.add(new Versions("Pembahagian Faraid" , "Faraid Jika Diterima Sebagai:\n\n" + "1. Suami: 1/4(si mati ada keturunan L/P) atau 1/2(si mati tiada keturunan L/P).\n\n" + "2. Isteri: 1/4(si mati tiada keturunan L/P) atau 1/8(si mati ada keturunan L/P).\n\n" + "3. Anak Lelaki: Asabah(bersama waris fardhu), 2:1(berkongsi dengan anak perempuan), semua baki.\n\n" + "4. Anak Perempuan: 1:2(kongsi dengan anak lelaki), 2/3(anak perempuan lebih,tiada laki), 1/2(anak perempuan tunggal,tiada anak lelaki).\n\n" + "5.  Bapa: 1/6(si mati ada keturunan lelaki), Baki(si mati ada keturunan perempuan), Asabah(tiada keturunan L/P).\n\n"  + "6. Ibu: 1/3(Si mati tiada keturunan L/P), 1/6(Si mati ada keturunan L/P), Baki(suami,bapa masih hidup).\n"));
        versionsList.add(new Versions("Gagal Urus Harta Pusaka" , "1. Manipulasi Data.\n\n" + "2. Silaturahim Terjejas.\n\n" + "3. Dosa Tercipta.\n\n" + "4. Termakan Harta Anak Yatim.\n\n" + "5. Hutang Simati Tak Selesai.\n"));

    }
}