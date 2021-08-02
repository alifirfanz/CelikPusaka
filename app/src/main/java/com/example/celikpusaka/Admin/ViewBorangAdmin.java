package com.example.celikpusaka.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celikpusaka.R;
import com.example.celikpusaka.RecyclerViewListBorang;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewBorangAdmin extends AppCompatActivity {

    FloatingActionButton backBtn, addbtn;
    ListView listView;
    ImageView editBorang;
    DatabaseReference databaseReference;
    List<putPDF> uploadedPDF;

    //Array list
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_borang_admin);

        //hooks
        backBtn = findViewById(R.id.btnback);
        addbtn = findViewById(R.id.addbtn);
        listView = findViewById(R.id.listView);
        editBorang = findViewById(R.id.editborang);

        uploadedPDF = new ArrayList<>();

        //view files
        retrievePDFiles();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                putPDF putPDF = uploadedPDF.get(i);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setType("application/pdf");
                intent.setData(Uri.parse(putPDF.getUrl()));
                startActivity(intent);
            }
        });

        //edit btn
        editBorang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewBorangAdmin.this, RecyclerViewListBorang.class);
                startActivity(intent);
            }
        });



        //delete
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int which_item = position;


                new AlertDialog.Builder(ViewBorangAdmin.this)
                        .setTitle("Anda pasti?")
                        .setMessage("Adakah mahu padam borang ini")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {



                                Intent i = new Intent(ViewBorangAdmin.this,InfoAdmin.class);



                                Toast.makeText(ViewBorangAdmin.this,"Borang berjaya dipadam",Toast.LENGTH_LONG).show();
                                startActivity(i);

                            }
                        }).setNegativeButton("No", null).show();

                return true;
            }
        });


        //back button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewBorangAdmin.this, InfoAdmin.class);
                startActivity(intent);
            }
        });


        //add button
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewBorangAdmin.this, AddBorang.class);
                startActivity(intent);
            }
        });
    }

    //method view all files
    private void retrievePDFiles() {

        databaseReference = FirebaseDatabase.getInstance().getReference("borang");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    putPDF putPDF = ds.getValue(com.example.celikpusaka.Admin.putPDF.class);
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