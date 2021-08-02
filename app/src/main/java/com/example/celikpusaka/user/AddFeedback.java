package com.example.celikpusaka.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celikpusaka.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AddFeedback extends AppCompatActivity {

    FloatingActionButton btnback;
    EditText fullname, usercomment,suggestion;
    Button btnsave;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedback);

        //reference feedback
       reference = FirebaseDatabase.getInstance().getReference().child("feedback");

        //Hooks
        btnback = findViewById(R.id.btnback);
        fullname = findViewById(R.id.fullname_feedback);
        usercomment = findViewById(R.id.comment_feedback);
        suggestion = findViewById(R.id.suggestion_feedback);
        btnsave = findViewById(R.id.save_btn);


        //back to dashboard
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddFeedback.this, DashboardUser.class);
                startActivity(intent);
            }
        });


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertFeedbackData();
            }
        });
    }


    //insert feedback
    private void insertFeedbackData(){

           if (!validateName() | !validateComment() | !validateSuggest()  ){
               Toast toast = Toast.makeText(getApplicationContext(), "Sila isi setiap ruangan!", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
               toast.show(); // display the Toast
               return;
           }

           String name =fullname.getText().toString();
           String comment =usercomment.getText().toString();
           String suggest =suggestion.getText().toString();

           Feedback feedback = new Feedback(name,comment,suggest);
           Intent intent = new Intent(AddFeedback.this, DashboardUser.class);
           reference.push().setValue(feedback);
           Toast.makeText(AddFeedback.this,"Berjaya disimpan!",Toast.LENGTH_SHORT).show();

           startActivity(intent);
       }

    private Boolean validateName(){
        String val = fullname.getText().toString();

        if (val.isEmpty()){
            fullname.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            fullname.setError(null);
            return true;
        }
    }

    private Boolean validateComment(){
        String val = usercomment.getText().toString();

        if (val.isEmpty()){
            usercomment.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            usercomment.setError(null);
            return true;
        }
    }

    private Boolean validateSuggest(){
        String val = suggestion.getText().toString();

        if (val.isEmpty()){
           suggestion.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            suggestion.setError(null);
            return true;
        }
    }

}