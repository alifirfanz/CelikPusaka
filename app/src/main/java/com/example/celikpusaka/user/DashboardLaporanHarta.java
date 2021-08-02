package com.example.celikpusaka.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.celikpusaka.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DashboardLaporanHarta extends AppCompatActivity
{

    RecyclerView reciew;
    myadapter1 adapter;
    ImageView padam;
    DatabaseReference rootDatabaseref;
    LinearLayoutManager mLinearLayoutManager;
    FloatingActionButton backbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_laporan_harta);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        reciew = (RecyclerView) findViewById(R.id.recview);
        padam =  (ImageView) findViewById(R.id.image);
        backbtn = findViewById(R.id.btnback);

      //  reciew.setLayoutManager(new LinearLayoutManager(this));

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);
        reciew.setLayoutManager(mLinearLayoutManager);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardLaporanHarta.this,DashboardPengiraanHarta.class);
                startActivity(i);
            }
        });


       // rootDatabaseref= FirebaseDatabase.getInstance().getReference().child("pengiraan").child("Alif00");
        rootDatabaseref= FirebaseDatabase.getInstance().getReference().child("pengiraanharta");

        FirebaseRecyclerOptions<model1> options =
                new FirebaseRecyclerOptions.Builder<model1>()
                        .setQuery(rootDatabaseref, model1.class)
                        .build();

        adapter = new myadapter1(options);
        reciew.setAdapter(adapter);
    }
        @Override
        protected void onStart(){
        super.onStart();
        adapter.startListening();
    }
        @Override
        protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }

    /**
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView= (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processearch(String s) {

        FirebaseRecyclerOptions<model1> options =
                new FirebaseRecyclerOptions.Builder<model1>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("pengiraan").orderByChild("name").startAt(s).endAt(s +"\uf8ff"), model1.class)
                        .build();

        adapter = new  myadapter1(options);
        adapter.startListening();
        reciew.setAdapter(adapter);
    } **/


}
