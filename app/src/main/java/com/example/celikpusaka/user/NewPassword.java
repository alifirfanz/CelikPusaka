package com.example.celikpusaka.user;

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

public class NewPassword extends AppCompatActivity {

    ImageView backbutton;
    TextInputLayout verifypassword, newpassword;
    TextView nameText,usernameText,emailText,phoneNoText,genderText,UniText;
    Button savePassword;

    //Global variables hold user inside activity
    String _USERNAME,_UNIVERSITY,_NAME,_EMAIL,_PHONENO,_PASSWORD, _GENDER;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        //Hooks
        reference = FirebaseDatabase.getInstance().getReference("users");

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
        genderText = findViewById(R.id.gender);
        UniText = findViewById(R.id.uni);

        //show all user data
        showAllUserData();

        //back button
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewPassword.this, ForgetPassword.class);
                startActivity(intent);
            }
        });

    }

    private void showAllUserData() {
        Intent intent = getIntent();
        _USERNAME = intent.getStringExtra("username");
        _PASSWORD = intent.getStringExtra("password");
        _NAME = intent.getStringExtra("name");
        _GENDER = intent.getStringExtra("gender");
        _PHONENO = intent.getStringExtra("phoneNo");
        _EMAIL = intent.getStringExtra("email");
        _UNIVERSITY = intent.getStringExtra("university");

        usernameText.setText(_USERNAME);
        UniText.setText(_UNIVERSITY);
        nameText.setText(_NAME);
        //password.setText(_PASSWORD);
        genderText.setText(_GENDER);
        phoneNoText.setText(_PHONENO);
        emailText.setText(_EMAIL);
    }

    public void update(View view){

        if(validateNewpassword() && validateVerifypassword()) {

            if (isVerifyPasswordChanged()) {

                Intent intent = new Intent(NewPassword.this, Login.class);
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





/**
 //save button
 savePassword.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
rootNode = FirebaseDatabase.getInstance();
reference = rootNode.getReference("users");
newPassword();
}
});


 }
 private Boolean validatePassword(){
 String val = newpassword.getEditText().getText().toString();

 if (val.isEmpty()){
 newpassword.setError("Ruangan ini tidak diisi");
 return false;
 }
 else{
 newpassword.setError(null);
 return true;
 }
 }

 private Boolean validatePasswordVerify(){
 String val = verifypassword.getEditText().getText().toString();

 if (val.isEmpty()){
 verifypassword.setError("Ruangan ini tidak diisi");
 return false;
 }
 else{
 newpassword.setError(null);
 return true;
 }
 }

 private void newPassword() {

 if ( !validatePassword() || !validatePasswordVerify()){
 Toast toast = Toast.makeText(getApplicationContext(), "Isi setiap ruangan.", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
 toast.show(); // display the Toast
 return;
 }
 //get all the values in String

 String username = usernameText.getText().toString();
 String password = newpassword.getEditText().getText().toString();
 String name = nameText.getText().toString();
 String phoneNo = phoneNoText.getText().toString();
 String email = emailText.getText().toString();
 String gender = genderText.getText().toString();
 String university =UniText.getText().toString();

 UserHelperClass helperClass = new UserHelperClass(username, password,name, email, phoneNo,gender,university);
 reference.child(username).setValue(helperClass);

 Intent intent = new Intent(NewPassword.this, Login.class);
 Toast toast = Toast.makeText(getApplicationContext(), "Kata Laluan baharu berjaya disimpan", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
 toast.show(); // display the Toast
 startActivity(intent);
 }

 }
 **/