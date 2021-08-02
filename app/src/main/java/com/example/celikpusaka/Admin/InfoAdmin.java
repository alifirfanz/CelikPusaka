package com.example.celikpusaka.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.celikpusaka.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InfoAdmin extends AppCompatActivity {

    FloatingActionButton backBtn;
    ImageView nota, video, borang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_admin);

        //Hooks
        backBtn= findViewById(R.id.btnback);
        nota= findViewById(R.id.nota);
        video= findViewById(R.id.video);
        borang= findViewById(R.id.borang);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoAdmin.this, DashboardAdmin.class);
                startActivity(intent);
            }
        });
        nota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoAdmin.this, BookListActivity.class);
                startActivity(intent);
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoAdmin.this, ViewVideoAdmin.class);
                startActivity(intent);
            }
        });
        borang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoAdmin.this, ViewBorangAdmin.class);
                startActivity(intent);
            }
        });



    }
}
