package com.example.celikpusaka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.celikpusaka.Admin.ViewVideoAdmin;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class RecyclerViewListVideo extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager mLinearLayoutManager;
    FloatingActionButton backbtn;

    PostAdapterVideo adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_list_video);

        recyclerView = findViewById(R.id.recyclerView_id);
        backbtn = findViewById(R.id.btnback);

        recyclerView.setHasFixedSize(true);
       // mLinearLayoutManager = new LinearLayoutManager(this);
       // mLinearLayoutManager.setReverseLayout(true);
       // mLinearLayoutManager.setStackFromEnd(true);
       // recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<PostModelVideo> options = new FirebaseRecyclerOptions
                .Builder<PostModelVideo>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("video"),PostModelVideo.class)
                .build();

        //update tambah (, this)
        adapter= new PostAdapterVideo(options,this);
        recyclerView.setAdapter(adapter);


        //back btn
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecyclerViewListVideo.this, ViewVideoAdmin.class);
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