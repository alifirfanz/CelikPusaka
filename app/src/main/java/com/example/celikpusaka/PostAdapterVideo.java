package com.example.celikpusaka;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;

import java.util.HashMap;
import java.util.Map;

public class PostAdapterVideo extends FirebaseRecyclerAdapter<PostModelVideo,PostAdapterVideo.ViewHolder> {

    private Context context;

    public PostAdapterVideo(@NonNull FirebaseRecyclerOptions<PostModelVideo> options,Context context) {
        super(options);
        this.context =context;
    }

    @Override
    protected void onBindViewHolder(@NonNull PostAdapterVideo.ViewHolder holder, final int position, @NonNull final PostModelVideo model) {

        holder.NamaVideo.setText(model.getName());

        //delete btn
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("video")
                        .child(getRef(position).getKey())
                        .removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context,"Berjaya dipadam",Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });

        //update btn
        holder.Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DialogPlus dialog = DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.contentvideo))
                        .setExpanded(false)
                        .setContentBackgroundResource(R.drawable.bg_calen)
                        .create();

                View holderView = dialog.getHolderView();
                final EditText nama =holderView.findViewById(R.id.editnamavideo);
                Button btnUpdate =holderView.findViewById(R.id.updateBtnvideo);

                nama.setText(model.getName());


                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("name",nama.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("video")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(context,"Berjaya dikemaskini",Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                });
                    }
                });
                dialog.show();
            }
        });
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.design_row_for_recyclerview_video,parent,false);

        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView NamaVideo;
        ImageView Update,Delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            NamaVideo = itemView.findViewById(R.id.namavideo);

            Update = itemView.findViewById(R.id.update_imagevideo);
            Delete = itemView.findViewById(R.id.delete_imagevideo);
        }
    }
}
