package com.example.celikpusaka.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.celikpusaka.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ForgetPassword extends AppCompatActivity {

    private TextInputLayout username;
    private Button resetpasswordButton;
    private ImageView backbutton;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        backbutton =(ImageView) findViewById(R.id.back);
        username =(TextInputLayout) findViewById(R.id.username);
        resetpasswordButton = (Button) findViewById(R.id.sentEmailLink);


        //backbutton
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgetPassword.this, Login.class);
                startActivity(intent);
            }
        });


        //reset button
        resetpasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  rootNode = FirebaseDatabase.getInstance();
                //  reference = rootNode.getReference("users");
                //  resetPassword();
                //Toast.makeText(ForgetPassword.this,"Emel anda betul dan sah!",Toast.LENGTH_LONG).show();


                final String userEnteredUsername = username.getEditText().getText().toString().trim();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists()) {

                            username.setError(null);
                            username.setErrorEnabled(false);

                            String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);
                            String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
                            String nameFromDB = dataSnapshot.child(userEnteredUsername).child("name").getValue(String.class);
                            String genderFromDB = dataSnapshot.child(userEnteredUsername).child("gender").getValue(String.class);
                            String phoneNoFromDB = dataSnapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                            String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);
                            String uniFromDB = dataSnapshot.child(userEnteredUsername).child("university").getValue(String.class);

                            Intent intent = new Intent(getApplicationContext(), NewPassword.class);

                            intent.putExtra("username", usernameFromDB);
                            intent.putExtra("password", passwordFromDB);
                            intent.putExtra("name", nameFromDB);
                            intent.putExtra("gender", genderFromDB);
                            intent.putExtra("phoneNo", phoneNoFromDB);
                            intent.putExtra("email", emailFromDB);
                            intent.putExtra("university", uniFromDB);


                            startActivity(intent);
                            finish();

                        }else {
                            username.setError("Pengguna tidak wujud");
                            username.requestFocus();

                        }
                        validateUsername();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }


    private boolean validateUsername() {
        String namapengguna =username.getEditText().getText().toString().trim();

        if(namapengguna.isEmpty()){
            username.setError("Nama Pengguna perlu diisi");
            username.requestFocus();
            return false;
        }
        return true;
    }
    /**
     private void resetPassword() {

     if ( !validateEmail()){
     Toast toast = Toast.makeText(getApplicationContext(), "Isi setiap ruangan.", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
     toast.show(); // display the Toast
     return;
     }
     //get all the values in String

     Intent intent = new Intent(ForgetPassword.this, NewPassword.class);
     Toast toast = Toast.makeText(getApplicationContext(), "Email anda telah disahkan", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
     toast.show(); // display the Toast
     startActivity(intent);
     }   **/
}