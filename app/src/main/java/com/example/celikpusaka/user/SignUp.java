package com.example.celikpusaka.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celikpusaka.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    TextInputLayout regName, regUsername,regEmail, regPhoneNo,regPassword,regGender,regUni;
    Button regBtn, regToLoginBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    Spinner regUniSpinner;
    Spinner regGenderSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPassword = findViewById(R.id.reg_password);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        // regGender = findViewById(R.id.reg_gender);
      //  regUni = findViewById(R.id.reg_uni);

        regBtn = findViewById(R.id.reg_btn);
        regToLoginBtn = findViewById(R.id.reg_login_btn);

        regUniSpinner = findViewById(R.id.spinner);
        regGenderSpinner =findViewById(R.id.spinner1);

        //spinner uni
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(SignUp.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.uni));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regUniSpinner.setAdapter(myAdapter);

        //spinner gender
        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(SignUp.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.gender));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regGenderSpinner.setAdapter(myAdapter1);


        regToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });

        //save data in firebase on button click
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                registerUser();

            }
        });
    }

    private Boolean validateName(){
        String val = regName.getEditText().getText().toString();

        if (val.isEmpty()){
            regName.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername(){
        String val = regUsername.getEditText().getText().toString();

        if (val.isEmpty()){
            regUsername.setError("Ruangan ini tidak diisi");
            return false;
        }
        else if (val.length()>=15){
            regUsername.setError("Nama Pengguna terlalu panjang");
            return false;
        }
        else{
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail(){
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()){
            regEmail.setError("Ruangan ini tidak diisi");
            return false;
        }
        else if(!val.matches(emailPattern)){
            regEmail.setError("Emel tidak sah");
            return false;
        }
        else{
            regEmail.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo(){
        String val = regPhoneNo.getEditText().getText().toString();

        if (val.isEmpty()){
            regPhoneNo.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            regPhoneNo.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword(){
        String val = regPassword.getEditText().getText().toString();

        if (val.isEmpty()){
            regPassword.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            regPassword.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateLengthpassword(){
        String val = regPassword.getEditText().getText().toString();

        if(val.length()<6){
            regPassword.setError("Kata laluan terlalu pendek");
            return false;
        }
        else{
            regPassword.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUni() {
        String val = regUni.getEditText().getText().toString();

        if (val.isEmpty()){
            regUni.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            regUni.setError(null);
            regUni.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateGender() {
        String val = regGender.getEditText().getText().toString();

        if (val.isEmpty()){
            regGender.setError("Ruangan ini tidak diisi");
            return false;
        }
        else{
            regGender.setError(null);
            regGender.setErrorEnabled(false);
            return true;
        }
    }


    //save Data in Firebase on button click
    public void  registerUser (){

        if (!validateName() | !validatePassword() | !validatePhoneNo() | !validateUsername()| !validateEmail() | !validateLengthpassword()){
            Toast toast = Toast.makeText(getApplicationContext(), "Sila isi setiap ruangan!", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
            toast.show(); // display the Toast
            return;
        }

            //get all the values in String
            String username = regUsername.getEditText().getText().toString();
            String password = regPassword.getEditText().getText().toString();
            String name = regName.getEditText().getText().toString();
            String email = regEmail.getEditText().getText().toString();
            String phoneNo = regPhoneNo.getEditText().getText().toString();
            String gender = regGenderSpinner.getSelectedItem().toString();
            String university = regUniSpinner.getSelectedItem().toString();

            if(university.equals("Pilih Universiti")){
                Toast.makeText(this,"Sila pilih Universiti",Toast.LENGTH_SHORT).show();
            }
            if(gender.equals("Pilih Jantina")){
                Toast.makeText(this,"Sila pilih Jantina",Toast.LENGTH_SHORT).show();
            }
            else {

                UserHelperClass helperClass = new UserHelperClass(username, password, name, email, phoneNo, gender, university);
                reference.child(username).setValue(helperClass);

                Intent intent = new Intent(SignUp.this, Login.class);
                Toast toast = Toast.makeText(getApplicationContext(), "Pendaftaran berjaya!", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
                toast.show(); // display the Toast
                startActivity(intent);

            }

        }
}