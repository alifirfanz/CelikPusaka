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
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class PostAdapter extends FirebaseRecyclerAdapter <PostModel,PostAdapter.ViewHolder> {

    private Context context;

    public PostAdapter(@NonNull FirebaseRecyclerOptions<PostModel> options, Context context) {
        super(options);
        this.context =context;

    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, final int position, @NonNull final PostModel model) {

        holder.NamaProgram.setText(model.getNamaProgram());
        holder.TempatProgram.setText(model.getTempatProgram());
        holder.TarikhProgram.setText(model.getTarikhProgram());
        holder.HariProgram.setText(model.getHariProgram());
        holder.MasaProgram.setText(model.getMasaProgram());

        String imageUri=model.getmImageUrl();
        Picasso.get().load(imageUri).into(holder.GambarProgram);

        //delete btn
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("program")
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
                        .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.content))
                        .setExpanded(false)
                        .setContentBackgroundResource(R.drawable.bg_calen)
                        .create();

                View holderView = dialog.getHolderView();
                final EditText nama =holderView.findViewById(R.id.editnama);
                final EditText tempat =holderView.findViewById(R.id.edittempat);
                final EditText tarikh =holderView.findViewById(R.id.edittarikh);
                final EditText hari =holderView.findViewById(R.id.edithari);
                final EditText masa =holderView.findViewById(R.id.editmasa);

                Button btnUpdate =holderView.findViewById(R.id.updateBtn);

                nama.setText(model.getNamaProgram());
                tempat.setText(model.getTempatProgram());
                tarikh.setText(model.getTarikhProgram());
                hari.setText(model.getHariProgram());
                masa.setText(model.getMasaProgram());

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("namaProgram",nama.getText().toString());
                        map.put("tempatProgram",tempat.getText().toString());
                        map.put("tarikhProgram",tarikh.getText().toString());
                        map.put("hariProgram",hari.getText().toString());
                        map.put("masaProgram",masa.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("program")
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
                inflate(R.layout.design_row_for_recyclerview,parent,false);

        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView NamaProgram,TempatProgram,TarikhProgram,HariProgram,MasaProgram;
        ImageView GambarProgram,Update,Delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            NamaProgram = itemView.findViewById(R.id.nama);
            TempatProgram = itemView.findViewById(R.id.tempat);
            TarikhProgram = itemView.findViewById(R.id.tarikh);
            HariProgram = itemView.findViewById(R.id.hari);
            MasaProgram = itemView.findViewById(R.id.masa);
            GambarProgram = itemView.findViewById(R.id.imageprogram);

            Update = itemView.findViewById(R.id.update_image);
            Delete = itemView.findViewById(R.id.delete_image);
        }
    }

}
