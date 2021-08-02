package com.example.celikpusaka.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celikpusaka.R;
import com.example.celikpusaka.RecyclerViewList1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DashboardUser extends AppCompatActivity {

    CardView infouser,feedback, profile, logout, pengiraan, kuiz, program,calendar;
    TextView fullNameLabel;

    DatabaseReference reference;

    //Global variables hold user inside activity
    String _USERNAME,_UNIVERSITY,_NAME,_EMAIL,_PHONENO,_PASSWORD, _GENDER;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_user);

        reference = FirebaseDatabase.getInstance().getReference("users");

        //hooks
        infouser=findViewById(R.id.infouser);
        feedback=findViewById(R.id.feedback);
        profile = findViewById(R.id.profil_page);
        logout = findViewById(R.id.logout);
        kuiz =findViewById(R.id.quiz);
        pengiraan =findViewById(R.id.calculator);
        program =findViewById(R.id.hebahanprogram);
        calendar =findViewById(R.id.calendar);

        //get current user
        fullNameLabel = findViewById(R.id.fullNameLabel);

        //show all user data
        showAllUserData();

        infouser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardUser.this, InfoUser.class);
                Toast.makeText(DashboardUser.this,"Klik pada gambar nota,video atau borang",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });


        pengiraan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardUser.this, DashboardPengiraanHarta.class);
                Toast.makeText(DashboardUser.this,"Klik pada gambar pengiraan atau laporan",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });


        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardUser.this, AddFeedback.class);
                startActivity(intent);
            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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


                        Intent intent = new Intent(getApplicationContext(), UserProfile.class);

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
               // Intent intent = new Intent(DashboardUser.this, UserProfile.class);
              //  startActivity(intent);

            }
        });


        kuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardUser.this, StartingScreenActivity.class);
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                startActivity(intent);
            }
        });


        program.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardUser.this, RecyclerViewList1.class);
                startActivity(intent);
            }
        });


        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        java.util.Calendar calendarEvent = java.util.Calendar.getInstance();
              Intent i = new Intent(Intent.ACTION_EDIT);
                    i.setType("vnd.android.cursor.item/event");
                   i.putExtra("beginTime", calendarEvent.getTimeInMillis());
                    i.putExtra("allDay", true);
                    i.putExtra("rule", "FREQ=YEARLY");
                    i.putExtra("endTime", calendarEvent.getTimeInMillis() + 60 * 60 * 1000);
                    i.putExtra("title", "Calendar Event");
                    startActivity(i);
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardUser.this, Login.class);
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                Toast toast = Toast.makeText(getApplicationContext(), "Terima kasih menggunakan Celik Pusaka!", Toast.LENGTH_LONG); // initiate the Toast with context, message and duration for the Toast
                toast.show(); // display the Toast
                startActivity(intent);
            }
        });
    }

    private void showAllUserData(){

        Intent intent =getIntent();
        _USERNAME = intent.getStringExtra("username");
        _PASSWORD = intent.getStringExtra("password");
        _NAME = intent.getStringExtra("name");
        _GENDER = intent.getStringExtra("gender");
        _PHONENO = intent.getStringExtra("phoneNo");
        _EMAIL = intent.getStringExtra("email");
        _UNIVERSITY = intent.getStringExtra("university");

    }
}


