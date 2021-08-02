package com.example.celikpusaka;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.celikpusaka.user.SignUp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class AddHebahan extends AppCompatActivity {

    ImageButton imageButton;
    EditText namaprogram, tempatprogram, tarikhprogram, masaprogram;
    Button btnadd;
    FloatingActionButton backbtn;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    StorageReference mStorage;
    ProgressDialog mprogress;
    DatePickerDialog.OnDateSetListener setListener;
    int t1hour,t1Minute;


    Spinner hariprogramSpinner;

    private static final int Gallery_Code =1;
    private Uri imageUri = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hebahan);

        //hooks
        namaprogram = findViewById(R.id.txtnamaprogram);
        tempatprogram = findViewById(R.id.txttempatprogram);
        tarikhprogram = findViewById(R.id.txttarikhprogram);
        masaprogram = findViewById(R.id.txtmasaprogram);
        imageButton = findViewById(R.id.btn_imageProgram);
        hariprogramSpinner =findViewById(R.id.txthariprogram);

        mprogress = new ProgressDialog(this);

        //button
        btnadd = findViewById(R.id.addbtnProgram);
        backbtn= findViewById(R.id.btnback);
        mDatabase = FirebaseDatabase.getInstance();
        mRef =mDatabase.getReference().child("program");
        mStorage = FirebaseStorage.getInstance().getReference("Program");


        //spinner uni
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddHebahan.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.day));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hariprogramSpinner.setAdapter(myAdapter);


        //calendar pick for tarikh
        final java.util.Calendar calendar = java.util.Calendar.getInstance();
        final DatePickerDialog datePickerDialog = new DatePickerDialog(AddHebahan.this
                , new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                java.util.Calendar updateDate = java.util.Calendar.getInstance();
                updateDate.set(year, month,dayOfMonth);
                tarikhprogram.setText(dayOfMonth + "." + (month+1) + "." + year);
            }
        },calendar.get(java.util.Calendar.DAY_OF_MONTH),calendar.get(java.util.Calendar.MONTH),calendar.get(Calendar.YEAR));

        tarikhprogram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        //pick time
        masaprogram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddHebahan.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        t1hour =hourOfDay;
                        t1Minute=minute;

                        Calendar calendar = Calendar.getInstance();

                        calendar.set(0,0,0,t1hour,t1Minute);
                        masaprogram.setText(DateFormat.format("hh:mm aa", calendar));
            }
        },12,0,false);
                timePickerDialog.updateTime(t1hour,t1Minute);
                timePickerDialog.show();
            }
        });



        //image button
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,Gallery_Code);
            }
        });


        //back btn
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddHebahan.this,RecyclerViewList.class);
                startActivity(i);
            }
        });
    }


    //get uri file extension
    private String GetFileExtension(Uri uri) {

        ContentResolver cR = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cR.getType(uri));
    }



    //pick image in gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Gallery_Code && resultCode == RESULT_OK) {
            imageUri = data.getData();
            imageButton.setImageURI(imageUri);
        }


        //add into firebase
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String namaProgram = namaprogram.getText().toString().trim();
                final String tempatProgram = tempatprogram.getText().toString().trim();
                final String tarikhProgram = tarikhprogram.getText().toString().trim();
                final String hariProgram = hariprogramSpinner.getSelectedItem().toString();
                final String masaProgram = masaprogram.getText().toString().trim();

                //if gambar tidak dipilih
                if (imageUri == null){
                    Toast.makeText(AddHebahan.this,"Sila pilih gambar program",Toast.LENGTH_SHORT).show();
                    return;
                }

                //if ruangan tidak diisi
                if (TextUtils.isEmpty(namaProgram)  ) {
                    namaprogram.setError("Ruangan ini tidak diisi");
                    Toast.makeText(AddHebahan.this,"Nama program tidak diisi",Toast.LENGTH_SHORT).show();
                    return;
                }

                //if ruangan tidak diisi
                if (TextUtils.isEmpty(tempatProgram)) {
                    tempatprogram.setError("Ruangan ini tidak diisi");
                    Toast.makeText(AddHebahan.this,"Tempat program tidak diisi",Toast.LENGTH_SHORT).show();
                    return;
                }

                //if hari program tk select
                if(hariProgram.equals("Pilih Hari")){
                    Toast.makeText(AddHebahan.this,"Sila pilih hari program",Toast.LENGTH_SHORT).show();
                    return;
                }

                //if ruangan tidak diisi
                if (TextUtils.isEmpty(tarikhProgram)) {
                    tarikhprogram.setError("Ruangan ini tidak diisi");
                    Toast.makeText(AddHebahan.this,"Tarikh program tidak diisi",Toast.LENGTH_SHORT).show();
                    return;
                }

                //if ruangan tidak diisi
                if (TextUtils.isEmpty(masaProgram)) {
                    masaprogram.setError("Ruangan ini tidak diisi");
                    Toast.makeText(AddHebahan.this,"Masa Program tidak diisi",Toast.LENGTH_SHORT).show();
                    return;
                }


                //if ruangan sudah diisi
                if(!namaProgram.isEmpty() && !tempatProgram.isEmpty() && !tarikhProgram.isEmpty() && !hariProgram.isEmpty() && !masaProgram.isEmpty() && imageUri!= null )
                {
                    mprogress.setMessage("Sedang dimuatnaik..");
                    mprogress.show();

                    StorageReference filepath = mStorage.child(System.currentTimeMillis() + "." +
                            GetFileExtension(imageUri));
                    filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    Intent intent = new Intent(AddHebahan.this,RecyclerViewList.class);
                                    String t =task.getResult().toString();
                                    DatabaseReference newPost = mRef.push();
                                    newPost.child("namaProgram").setValue(namaProgram);
                                    newPost.child("tempatProgram").setValue(tempatProgram);
                                    newPost.child("tarikhProgram").setValue(tarikhProgram);
                                    newPost.child("hariProgram").setValue(hariProgram);
                                    newPost.child("masaProgram").setValue(masaProgram);
                                    newPost.child("mImageUrl").setValue(t);

                                    mprogress.dismiss();
                                    Toast.makeText(AddHebahan.this,"Program berjaya disimpan",Toast.LENGTH_LONG).show();
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                }
            }
        });


    }
}