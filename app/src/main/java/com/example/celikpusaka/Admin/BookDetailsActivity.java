package com.example.celikpusaka.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.celikpusaka.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class BookDetailsActivity extends AppCompatActivity {

    private EditText mTitle,mDetails;
    private Button mUpdate,mDelete;

    private String key,title,details;
    FloatingActionButton backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        key =getIntent().getStringExtra("key");
        title = getIntent().getStringExtra("title");
        details = getIntent().getStringExtra("details");

        mTitle =(EditText) findViewById(R.id.title_editTxt);
        mTitle.setText(title);
        mDetails =(EditText) findViewById(R.id.details_editTxt);
        mDetails.setText(details);
        mDelete =(Button)findViewById(R.id.delete_btn);
        mUpdate =(Button)findViewById(R.id.update_btn);
        backbtn=findViewById(R.id.backbtn);


        //update button
        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setTajukNota(mTitle.getText().toString());
                book.setMaklumatNota(mDetails.getText().toString());

                new FirebaseDatabaseHelper().updateBook(key, book, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Book> books, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Intent i =new Intent(BookDetailsActivity.this, BookListActivity.class);
                        Toast.makeText(BookDetailsActivity.this,"Nota berjaya dikemaskini",Toast.LENGTH_LONG).show();
                        startActivity(i);

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        //delete button
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelper().deleteBook(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Book> books, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Intent i =new Intent(BookDetailsActivity.this,BookListActivity.class);
                        Toast.makeText(BookDetailsActivity.this,"Nota berjaya dipadam",Toast.LENGTH_LONG).show();
                        startActivity(i);
                        finish();
                        return;
                    }
                });
            }
        });

        //back floating button
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BookDetailsActivity.this,BookListActivity.class);
                startActivity(i);
            }
        });
    }
}