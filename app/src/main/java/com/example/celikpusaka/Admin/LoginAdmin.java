package com.example.celikpusaka.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.celikpusaka.user.Login;
import com.example.celikpusaka.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginAdmin extends AppCompatActivity {

    Button login_btn, btn_mahasiswa,forgotpassword;
    TextInputLayout username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        //Hooks
        btn_mahasiswa = findViewById(R.id.btnmahasiswa);
        login_btn = findViewById(R.id.login_btnAdmin);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        forgotpassword =findViewById(R.id.forgotpass);

        //return to ui mahasiswa
        btn_mahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginAdmin.this, Login.class);
                startActivity(intent);
            }
        });

        //forgot pass btn
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginAdmin.this, ForgetPasswordAdmin.class);
                startActivity(i);

            }
        });

        //login btn
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(Login.this, DashboardUser.class);
                // startActivity(intent);

                final String userEnteredUsername = username.getEditText().getText().toString().trim();
                final String userEnteredPassword = password.getEditText().getText().toString().trim();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("admin");
                Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists()) {

                            username.setError(null);
                            username.setErrorEnabled(false);

                            String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);
                            Toast toast = Toast.makeText(getApplicationContext(), "Selamat datang ke Celik Pusaka!", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
                            toast.show(); // display the Toast

                            if (passwordFromDB.equals(userEnteredPassword)) {

                                username.setError(null);
                                username.setErrorEnabled(false);

                                String nameFromDB = dataSnapshot.child(userEnteredUsername).child("name").getValue(String.class);
                                String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
                                String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);
                                String phoneNoFromDB = dataSnapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);

                                Intent intent = new Intent(getApplicationContext(), DashboardAdmin.class);

                                intent.putExtra("name", nameFromDB);
                                intent.putExtra("username", usernameFromDB);
                                intent.putExtra("email", emailFromDB);
                                intent.putExtra("phoneNo", phoneNoFromDB);
                                intent.putExtra("password", passwordFromDB);

                                startActivity(intent);

                            } else {
                                password.setError("Kata Laluan Salah");
                                password.requestFocus();
                            }
                        } else {
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
    }
}