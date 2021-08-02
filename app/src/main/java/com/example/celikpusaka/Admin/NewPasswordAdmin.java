package com.example.celikpusaka.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celikpusaka.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewPasswordAdmin extends AppCompatActivity {

    ImageView backbutton;
    TextInputLayout verifypassword, newpassword;
    TextView nameText,usernameText,emailText,phoneNoText;
    Button savePassword;

    //Global variables hold user inside activity
    String _USERNAME,_NAME,_EMAIL,_PHONENO,_PASSWORD;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password_admin);

        //Hooks
        reference = FirebaseDatabase.getInstance().getReference("admin");

        //hooks
        backbutton = findViewById(R.id.back);
        verifypassword = findViewById(R.id.verifypassword);
        newpassword = findViewById(R.id.newpassword);
        savePassword = findViewById(R.id.savePassword);

        //exclude
        nameText = findViewById(R.id.name);
        usernameText = findViewById(R.id.username);
        emailText = findViewById(R.id.email);
        phoneNoText = findViewById(R.id.phoneNo);


        //show all user data
        showAllUserData();

        //back button
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewPasswordAdmin.this, ForgetPasswordAdmin.class);
                startActivity(intent);
            }
        });

    }

    private void showAllUserData() {
        Intent intent = getIntent();
        _USERNAME = intent.getStringExtra("username");
        _PASSWORD = intent.getStringExtra("password");
        _NAME = intent.getStringExtra("name");
        _PHONENO = intent.getStringExtra("phoneNo");
        _EMAIL = intent.getStringExtra("email");

        usernameText.setText(_USERNAME);
        nameText.setText(_NAME);
        phoneNoText.setText(_PHONENO);
        emailText.setText(_EMAIL);
    }

    public void update(View view){

        if(validateNewpassword() && validateVerifypassword()) {

            if (isVerifyPasswordChanged()) {

                Intent intent = new Intent(NewPasswordAdmin.this, LoginAdmin.class);
                Toast.makeText(this, "Kata Laluan Baharu berjaya disimpan", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            } else
                Toast.makeText(this, "Kata Laluan yang sama tidak boleh disimpan", Toast.LENGTH_SHORT).show();


        }
        else
            Toast.makeText(this, "Sila isi ruangan", Toast.LENGTH_SHORT).show();
    }

    private boolean validateVerifypassword() {
        String verifypass =verifypassword.getEditText().getText().toString().trim();

        if(verifypass.isEmpty()){
            verifypassword.setError("Sah kata laluan perlu diisi");
            verifypassword.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validateNewpassword() {
        String newpass =newpassword.getEditText().getText().toString().trim();

        if(newpass.isEmpty()){
            newpassword.setError("Kata laluan baharu perlu diisi");
            newpassword.requestFocus();
            return false;
        }
        return true;
    }

    private boolean isVerifyPasswordChanged() {
        if (!_PASSWORD.equals(verifypassword.getEditText().getText().toString())){
            reference.child(_USERNAME).child("password").setValue(verifypassword.getEditText().getText().toString());
            _PASSWORD = verifypassword.getEditText().getText().toString();
            return true;
        }else{
            return false;

        }
    }
}

