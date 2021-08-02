package com.example.celikpusaka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.celikpusaka.user.DashboardPengiraanHarta;
import com.example.celikpusaka.user.PengiraanHarta;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class BookListActivity2 extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    FloatingActionButton backbtn;
    LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list2);

        //hooks
        mRecyclerView = findViewById(R.id.recyclerview_books);
        backbtn = findViewById(R.id.backbtn);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BookListActivity2.this, DashboardPengiraanHarta.class);
                startActivity(i);
            }
        });

        new FirebaseDatabaseHelper2().readBook(new FirebaseDatabaseHelper2.DataStatus1() {
            @Override
            public void DataIsLoaded(List<Book2> books, List<String> keys) {
                findViewById(R.id.loading_books_pb).setVisibility(View.GONE);
                new RecyclerView_Config1().setConfig(mRecyclerView,BookListActivity2.this,books,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

    }
}