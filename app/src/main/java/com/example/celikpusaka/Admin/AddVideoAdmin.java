package com.example.celikpusaka.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.celikpusaka.AddHebahan;
import com.example.celikpusaka.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddVideoAdmin extends AppCompatActivity {

    private static final int PICK_VIDEO= 1;

    FloatingActionButton backBtn;
    VideoView videoView;
    Button button;
    EditText editText;

    ProgressBar progressBar;
    private Uri videoUri;
    private MediaController mediaController;

    StorageReference storageReference;
    DatabaseReference databaseReference;
    Member member;
    UploadTask uploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_video_admin);

        member = new Member();
        storageReference =FirebaseStorage.getInstance().getReference("Video");
        databaseReference =FirebaseDatabase.getInstance().getReference("video");

        //hooks
        backBtn =findViewById(R.id.btnback);
        videoView =findViewById(R.id.videoview_main);
        button =findViewById(R.id.button_upload_main);
        progressBar =findViewById(R.id.progressBar_main);
        editText =findViewById(R.id.et_video_name);

        videoView.setMediaController(new android.widget.MediaController(this));
        videoView.start();

       // mediaController = new MediaController(this);
       // videoView.setMediaController(mediaController);
       // videoView.start();

        //back button to view videos
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start activity to add views
                startActivity(new Intent(AddVideoAdmin.this, ViewVideoAdmin.class));
            }
        });
        //upload button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadVideo();
            }
        });

    }
    //upload video
    private void UploadVideo() {

        final String videoName = editText.getText().toString();

        //if ruangan tidak diisi
        if (TextUtils.isEmpty(videoName)  ) {
            editText.setError("Nama video tidak diisi");
            return;
        }

        //if video tidak dipilih
        if (videoUri == null){
            Toast.makeText(AddVideoAdmin.this,"Sila pilih Video",Toast.LENGTH_SHORT).show();
            return;
        }

        //if video sudah dipilh dan nama telah diisi
        if (videoUri != null || !TextUtils.isEmpty(videoName)){

            progressBar.setVisibility(View.VISIBLE);

            final StorageReference reference =storageReference.child(System.currentTimeMillis() + "." + getExt(videoUri));
            uploadTask = reference.putFile(videoUri);
            Toast.makeText(this,"Video sedang dimuat naik",Toast.LENGTH_SHORT).show();
            Task<Uri> urltask =uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return reference.getDownloadUrl();

                }
            })
                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {

                            if(task.isSuccessful()){
                                Uri downloadUrl = task.getResult();
                                progressBar.setVisibility(View.INVISIBLE);
                                Intent intent = new Intent(AddVideoAdmin.this,ViewVideoAdmin.class);
                                Toast.makeText(AddVideoAdmin.this,"Video telah disimpan",Toast.LENGTH_SHORT).show();

                                member.setName(videoName);
                                member.setVideourl(downloadUrl.toString());

                                String i = databaseReference.push().getKey();
                                databaseReference.child(i).setValue(member);
                                startActivity(intent);
                            }else{
                                Toast.makeText(AddVideoAdmin.this,"Data gagal disimpan",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }else{
            Toast.makeText(this,"Semua ruangan mesti diisi",Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==PICK_VIDEO || resultCode == RESULT_OK ||
        data != null ||data.getData() != null ){
             videoUri =data.getData();

             videoView.setVideoURI(videoUri);
        }
    }

    //get string
    private String getExt(Uri uri){
        ContentResolver contentResolver =getContentResolver();
        MimeTypeMap mimeTypeMap =MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    //choose video
    public void ChooseVideo(View view) {

        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_VIDEO);
    }
}
