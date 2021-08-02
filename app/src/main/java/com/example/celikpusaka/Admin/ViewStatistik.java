package com.example.celikpusaka.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.celikpusaka.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewStatistik extends AppCompatActivity {

    FloatingActionButton btnback;
    ImageView barchart,paichart,linechart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_statistik);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Hooks
        btnback= findViewById(R.id.btnback);
        barchart= findViewById(R.id.barchart);
        linechart= findViewById(R.id.linechart);
        paichart= findViewById(R.id.paichart);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewStatistik.this, DashboardAdmin.class);
                startActivity(intent);
            }
        });
        barchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewStatistik.this, bardemo.class);
                startActivity(intent);
            }
        });
        linechart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewStatistik.this,linedemo.class);
                startActivity(intent);
            }
        });
        paichart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewStatistik.this, paidemo.class);
                startActivity(intent);
            }
        });



    }
}
