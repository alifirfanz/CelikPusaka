package com.example.celikpusaka.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.celikpusaka.R;
import com.example.celikpusaka.RecyclerViewList;

public class DashboardAdmin extends AppCompatActivity {

    CardView Info, Program, Feedback, Statistik, Logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);

        //Hooks
        Info = findViewById(R.id.info);
        Program = findViewById(R.id.program);
        Feedback = findViewById(R.id.feedback);
        Statistik = findViewById(R.id.statistic);
        Logout = findViewById(R.id.logout);

        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdmin.this, InfoAdmin.class);
                startActivity(intent);
            }
        });
        Program.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdmin.this, RecyclerViewList.class);
                startActivity(intent);
            }
        });
        Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdmin.this, ViewFeedback.class);
                startActivity(intent);
            }
        });
        Statistik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdmin.this, ViewStatistik.class);
                startActivity(intent);
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdmin.this, LoginAdmin.class);
                startActivity(intent);
            }
        });
    }
}