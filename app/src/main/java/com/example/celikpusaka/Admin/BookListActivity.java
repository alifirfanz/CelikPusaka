package com.example.celikpusaka.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.celikpusaka.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class BookListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    FloatingActionButton addbtn,backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        //hooks
        mRecyclerView = findViewById(R.id.recyclerview_books);
        addbtn = findViewById(R.id.btn_add);
        backbtn = findViewById(R.id.backbtn);

        //backbtn
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BookListActivity.this, InfoAdmin.class);
                startActivity(i);
            }
        });

        //addbtn
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BookListActivity.this, NewBookActivity.class);
                startActivity(i);
            }
        });

        //get firebase data
        new FirebaseDatabaseHelper().readBook(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Book> books, List<String> keys) {
                findViewById(R.id.loading_books_pb).setVisibility(View.GONE);
                new RecyclerView_Config().setConfig(mRecyclerView,BookListActivity.this,books,keys);
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
    /**

     //menu action bar
     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
     getMenuInflater().inflate(R.menu.booklist_activity_menu,menu);
     return super.onCreateOptionsMenu(menu);
     }
     //menu action bar
     @Override
     public boolean onOptionsItemSelected(@NonNull MenuItem item) {
     switch (item.getItemId()){
     case R.id.new_nota:
     startActivity(new Intent(this, NewBookActivity.class));
     return true;
     }

     return super.onOptionsItemSelected(item);
     }**/
}