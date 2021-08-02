package com.example.celikpusaka.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.celikpusaka.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Video extends AppCompatActivity {

    FloatingActionButton backBtn;
    ListView listView;

    DatabaseReference databaseReference;
    List<VideoUser> uploadedPDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        //hooks
        backBtn = findViewById(R.id.btnback);
        listView = findViewById(R.id.listView);

        uploadedPDF = new ArrayList<>();

        //view files
        retrievePDFiles();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                VideoUser putPDF = uploadedPDF.get(i);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setType("application/mp3");
                intent.setData(Uri.parse(putPDF.getVideourl()));
                startActivity(intent);
            }
        });


        //back button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Video.this, InfoUser.class);
                startActivity(intent);
            }
        });

    }
    private void retrievePDFiles() {
        databaseReference = FirebaseDatabase.getInstance().getReference("video");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    VideoUser putPDF = ds.getValue(com.example.celikpusaka.user.VideoUser.class);
                    uploadedPDF.add(putPDF);

                }
                // call file name
                String[] uploadsName = new String[uploadedPDF.size()];
                for (int i = 0; i < uploadsName.length; i++) {
                    uploadsName[i] = uploadedPDF.get(i).getName();

                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, uploadsName) {

                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                        View view = super.getView(position, convertView, parent);

                        TextView myText = (TextView) view.
                                findViewById(android.R.id.text1);

                        myText.setTextColor(Color.BLACK);
                        myText.setTextSize(20);

                        return view;

                    }
                };
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}