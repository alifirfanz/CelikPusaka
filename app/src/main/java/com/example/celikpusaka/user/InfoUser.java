package com.example.celikpusaka.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.celikpusaka.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InfoUser extends AppCompatActivity {

    FloatingActionButton btnback;
    ImageView nota,video,borang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);

        //Hooks
        btnback =findViewById(R.id.btnback);
        nota =findViewById(R.id.nota);
        video =findViewById(R.id.video);
        borang =findViewById(R.id.borang);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoUser.this, DashboardUser.class);
                startActivity(intent);
            }
        });
        nota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoUser.this, Nota.class);
                startActivity(intent);
            }
        });
        borang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoUser.this, Borang.class);
                startActivity(intent);
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoUser.this, Video.class);
                startActivity(intent);
            }
        });



    }
}