package com.example.celikpusaka;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class PostAdapter1  extends FirebaseRecyclerAdapter <PostModel,PostAdapter1.ViewHolder>{


    public PostAdapter1(@NonNull FirebaseRecyclerOptions<PostModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PostAdapter1.ViewHolder holder, int position, @NonNull PostModel model) {

        holder.NamaProgram.setText(model.getNamaProgram());
        holder.TempatProgram.setText(model.getTempatProgram());
        holder.TarikhProgram.setText(model.getTarikhProgram());
        holder.MasaProgram.setText(model.getMasaProgram());
        holder.HariProgram.setText(model.getHariProgram());

        String imageUri=model.getmImageUrl();
        Picasso.get().load(imageUri).into(holder.GambarProgram);
    }

    @NonNull
    @Override
    public PostAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.image_item2,parent,false);

        return new PostAdapter1.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView NamaProgram,TempatProgram,TarikhProgram,HariProgram,MasaProgram;
        ImageView GambarProgram;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            NamaProgram = itemView.findViewById(R.id.nama);
            TempatProgram = itemView.findViewById(R.id.tempat);
            TarikhProgram = itemView.findViewById(R.id.tarikh);
            HariProgram = itemView.findViewById(R.id.hari);
            MasaProgram = itemView.findViewById(R.id.masa);
            GambarProgram = itemView.findViewById(R.id.imageprogram);

        }
    }
}
