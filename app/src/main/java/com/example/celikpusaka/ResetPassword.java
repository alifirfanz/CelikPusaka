package com.example.celikpusaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.celikpusaka.user.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResetPassword extends AppCompatActivity {

    private TextInputLayout emailEditText;
    private Button resetpasswordButton;
    private ImageView backbutton;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        backbutton =(ImageView) findViewById(R.id.back);
        emailEditText =(TextInputLayout) findViewById(R.id.email);
        resetpasswordButton = (Button) findViewById(R.id.sentEmailLink);

        auth = FirebaseAuth.getInstance();

        //backbutton
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResetPassword.this, Login.class);
                startActivity(intent);
            }
        });

        resetpasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });


    }

    private void resetPassword() {

        String email = emailEditText.getEditText().getText().toString().trim();

        if(email.isEmpty()){
            emailEditText.setError("Email perlu diisi");
            emailEditText.requestFocus();
            return ;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Sila masukkan email yang sah");
            emailEditText.requestFocus();
            return;
        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ResetPassword.this, "Check email", Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(ResetPassword.this, "Try again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}