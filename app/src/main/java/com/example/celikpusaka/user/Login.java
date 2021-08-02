package com.example.celikpusaka.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celikpusaka.Admin.LoginAdmin;
import com.example.celikpusaka.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button callSignUp, login_btn, btn_admin, forgotpass ;
    TextView logoText;
    TextInputLayout username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //Hooks
        callSignUp = findViewById(R.id.signup_screen);
        logoText = findViewById(R.id.logo_name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.loginUser);
        btn_admin = findViewById(R.id.btnadmin);
        forgotpass = findViewById(R.id.forgotpass);

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ForgetPassword.class);
                startActivity(intent);
            }
        });

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String userEnteredUsername = username.getEditText().getText().toString().trim();
                final String userEnteredPassword = password.getEditText().getText().toString().trim();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.exists()){

                            username.setError(null);
                            username.setErrorEnabled(false);

                            String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);

                            if (passwordFromDB.equals(userEnteredPassword)) {

                                username.setError(null);
                                username.setErrorEnabled(false);

                                String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
                                String nameFromDB = dataSnapshot.child(userEnteredUsername).child("name").getValue(String.class);
                                String genderFromDB = dataSnapshot.child(userEnteredUsername).child("gender").getValue(String.class);
                                String phoneNoFromDB = dataSnapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                                String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);
                                String uniFromDB = dataSnapshot.child(userEnteredUsername).child("university").getValue(String.class);

                                Intent intent = new Intent(getApplicationContext(), DashboardUser.class);

                                intent.putExtra("username", usernameFromDB);
                                intent.putExtra("password", passwordFromDB);
                                intent.putExtra("name", nameFromDB);
                                intent.putExtra("gender", genderFromDB);
                                intent.putExtra("phoneNo", phoneNoFromDB);
                                intent.putExtra("email", emailFromDB);
                                intent.putExtra("university", uniFromDB);

                                Toast toast = Toast.makeText(getApplicationContext(), "Selamat Datang ke Celik Pusaka!", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
                                toast.show(); // display the Toast
                                startActivity(intent);
                                finish();

                            }else{
                                password.setError("Kata laluan salah");
                                password.requestFocus();
                            }
                        }
                        else{
                            username.setError("Pengguna tidak wujud");
                            username.requestFocus();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, LoginAdmin.class);
                startActivity(intent);
            }
        });
    }
}