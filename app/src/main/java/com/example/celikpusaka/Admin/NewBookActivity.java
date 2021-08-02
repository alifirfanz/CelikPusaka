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

public class NewBookActivity extends AppCompatActivity {

    private EditText mTitle_editTxt,mDetails_editTxt;
    private Button mAdd_btn;
    FloatingActionButton mBack_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);

        //hooks
        mTitle_editTxt = findViewById(R.id.title_editTxt);
        mDetails_editTxt = findViewById(R.id.details_editTxt);
        mAdd_btn = findViewById(R.id.add_btn);
        mBack_btn = findViewById(R.id.backbtn);

        //add button
        mAdd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateName() | !validateDetails()   ){
                    Toast toast = Toast.makeText(getApplicationContext(), "Sila isi setiap ruangan!", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
                    toast.show(); // display the Toast
                    return;
                }
                Book book = new Book();
                book.setTajukNota(mTitle_editTxt.getText().toString());
                book.setMaklumatNota(mDetails_editTxt.getText().toString());
                new FirebaseDatabaseHelper().addBook(book, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Book> books, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {
                        Intent i =new Intent(NewBookActivity.this, BookListActivity.class);
                        Toast.makeText(NewBookActivity.this,"Nota berjaya disimpan",Toast.LENGTH_LONG).show();
                        startActivity(i);
                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });
        mBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                return;
            }
        });

    }

    private Boolean validateName(){
        String val =  mTitle_editTxt.getText().toString();

        if (val.isEmpty()){
            mTitle_editTxt.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            mTitle_editTxt.setError(null);
            return true;
        }
    }

    private Boolean validateDetails(){
        String val = mDetails_editTxt.getText().toString();

        if (val.isEmpty()){
            mDetails_editTxt.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            mDetails_editTxt.setError(null);
            return true;
        }
    }
}