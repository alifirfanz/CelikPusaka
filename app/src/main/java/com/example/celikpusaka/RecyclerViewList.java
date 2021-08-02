package com.example.celikpusaka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.celikpusaka.Admin.DashboardAdmin;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class RecyclerViewList extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager mLinearLayoutManager;
    FloatingActionButton addbtn,backbtn;

    PostAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_list);

        recyclerView = findViewById(R.id.recyclerView_id);
        addbtn = findViewById(R.id.btn_add);
        backbtn = findViewById(R.id.backbtn);

        recyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<PostModel> options = new FirebaseRecyclerOptions
                .Builder<PostModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("program"), PostModel.class)
                .build();

        //update tambah (, this)
        adapter= new PostAdapter(options,this);
        recyclerView.setAdapter(adapter);

        //add utk admin sahaja
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecyclerViewList.this,DashboardAdmin.class);
                startActivity(i);
            }
        });

        //add utk admin sahaja
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecyclerViewList.this,AddHebahan.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}