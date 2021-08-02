package com.example.celikpusaka.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.celikpusaka.BookListActivity2;
import com.example.celikpusaka.DemoPengiraanHarta;
import com.example.celikpusaka.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DashboardPengiraanHarta extends AppCompatActivity {

    ImageView laporanharta,pengiraanharta;
    FloatingActionButton btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_pengiraan_harta);

        btnback= findViewById(R.id.btnback);
        laporanharta =findViewById(R.id.laporanharta);
        pengiraanharta = findViewById(R.id.pengiraan);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardPengiraanHarta.this, DashboardUser.class);
                startActivity(intent);
            }
        });
        laporanharta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardPengiraanHarta.this, BookListActivity2.class);
                startActivity(intent);
            }
        });
        pengiraanharta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardPengiraanHarta.this, DemoPengiraanHarta.class);
                startActivity(intent);
            }
        });



    }
}
