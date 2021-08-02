package com.example.celikpusaka.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celikpusaka.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {

    TextInputLayout fullName,email,phoneNo,password;
    TextView fullNameLabel,gender, fullUniLabel;
    Button update;
    FloatingActionButton backbtn;

    //Global variables hold user inside activity
    String _USERNAME,_UNIVERSITY,_NAME,_EMAIL,_PHONENO,_PASSWORD, _GENDER;

    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //Hooks
        reference = FirebaseDatabase.getInstance().getReference("users");

        //hooks
        fullName = findViewById(R.id.full_name_field);
        email = findViewById(R.id.email_profile);
        phoneNo = findViewById(R.id.phone_no_profile);
        password = findViewById(R.id.password_profile);
        gender = findViewById(R.id.full_name_gender);
        fullNameLabel = findViewById(R.id.full_name_profile);
        fullUniLabel = findViewById(R.id.full_name_uni);
        update = findViewById(R.id.update_btn);
        backbtn = findViewById(R.id.backbtn);

        //show all user data
        showAllUserData();

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query checkUser = reference.orderByChild("username").equalTo(_USERNAME);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        String passwordFromDB = dataSnapshot.child(_USERNAME).child("password").getValue(String.class);
                        String usernameFromDB = dataSnapshot.child(_USERNAME).child("username").getValue(String.class);
                        String nameFromDB = dataSnapshot.child(_USERNAME).child("name").getValue(String.class);
                        String genderFromDB = dataSnapshot.child(_USERNAME).child("gender").getValue(String.class);
                        String phoneNoFromDB = dataSnapshot.child(_USERNAME).child("phoneNo").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(_USERNAME).child("email").getValue(String.class);
                        String uniFromDB = dataSnapshot.child(_USERNAME).child("university").getValue(String.class);


                        Intent intent = new Intent(getApplicationContext(), DashboardUser.class);

                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("password", passwordFromDB);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("gender", genderFromDB);
                        intent.putExtra("phoneNo", phoneNoFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("university", uniFromDB);

                        startActivity(intent);
                        finish();

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

    }


    //show all user data method
    private void showAllUserData() {
        Intent intent = getIntent();
        _USERNAME = intent.getStringExtra("username");
        _PASSWORD = intent.getStringExtra("password");
        _NAME = intent.getStringExtra("name");
        _GENDER = intent.getStringExtra("gender");
        _PHONENO = intent.getStringExtra("phoneNo");
        _EMAIL = intent.getStringExtra("email");
        _UNIVERSITY = intent.getStringExtra("university");

        fullNameLabel.setText(_USERNAME);
        fullUniLabel.setText(_UNIVERSITY);
        fullName.getEditText().setText(_NAME);
        password.getEditText().setText(_PASSWORD);
        gender.setText(_GENDER);
        phoneNo.getEditText().setText(_PHONENO);
        email.getEditText().setText(_EMAIL);

    }


    //update data
    public void update(View view){

        if(isNameChanged() || isPasswordChanged() || isEmailChanged() || isPhoneNoChanged()){
            /** Intent intent = new Intent(getApplicationContext(), DashboardUser.class);

             intent.putExtra("username", _USERNAME);
             intent.putExtra("password", _PASSWORD);
             intent.putExtra("name", _NAME);
             intent.putExtra("gender", _GENDER);
             intent.putExtra("phoneNo", _PHONENO);
             intent.putExtra("email", _EMAIL);
             intent.putExtra("university", _UNIVERSITY); **/

            Toast.makeText(this,"Profil telah dikemaskini",Toast.LENGTH_SHORT).show();
            // startActivity(intent);
        }
        else
            Toast.makeText(this,"Data yang sama tidak boleh dikemaskini",Toast.LENGTH_SHORT).show();

    }


    //is phone no changed
    private boolean isPhoneNoChanged() {
        if (!_PHONENO.equals(phoneNo.getEditText().getText().toString())){
            reference.child(_USERNAME).child("phoneNo").setValue(phoneNo.getEditText().getText().toString());
            _PHONENO= phoneNo.getEditText().getText().toString();
            return true;
        }else{
            return false;
        }
    }

    //is email changed
    private boolean isEmailChanged() {
        if (!_EMAIL.equals(email.getEditText().getText().toString())){
            reference.child(_USERNAME).child("email").setValue(email.getEditText().getText().toString());
            _EMAIL= email.getEditText().getText().toString();
            return true;
        }else{
            return false;
        }
    }

    //is name changed
    private boolean isNameChanged() {
        if (!_NAME.equals(fullName.getEditText().getText().toString())){
            reference.child(_USERNAME).child("name").setValue(fullName.getEditText().getText().toString());
            _NAME = fullName.getEditText().getText().toString();
            return true;
        }else{
            return false;
        }
    }

    //is password changed
    private boolean isPasswordChanged() {
        if (!_PASSWORD.equals(password.getEditText().getText().toString())){
            reference.child(_USERNAME).child("password").setValue(password.getEditText().getText().toString());
            _PASSWORD = password.getEditText().getText().toString();
            return true;
        }else{
            return false;
        }
    }



}