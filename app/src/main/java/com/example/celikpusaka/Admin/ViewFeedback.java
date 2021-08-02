package com.example.celikpusaka.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.celikpusaka.R;
import com.example.celikpusaka.RecyclerViewList1;
import com.example.celikpusaka.user.DashboardUser;
import com.example.celikpusaka.user.model;
import com.example.celikpusaka.user.myadapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class ViewFeedback extends AppCompatActivity {

    RecyclerView recview;
    myadapter adapter;
    LinearLayoutManager mLinearLayoutManager;
    FloatingActionButton backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);

        recview = (RecyclerView) findViewById(R.id.recview);
        backbtn = findViewById(R.id.backbtn);
       // recview.setLayoutManager(new LinearLayoutManager(this));

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);
        recview.setLayoutManager(mLinearLayoutManager);

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("feedback"), model.class)
                        .build();

        adapter = new myadapter(options);
        recview.setAdapter(adapter);


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewFeedback.this, DashboardAdmin.class);
                startActivity(i);
            }
        });

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
}
