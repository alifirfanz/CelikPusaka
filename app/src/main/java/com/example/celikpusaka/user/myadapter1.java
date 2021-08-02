package com.example.celikpusaka.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.celikpusaka.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter1 extends FirebaseRecyclerAdapter<model1,myadapter1.myviewholder> {



    public myadapter1(@NonNull FirebaseRecyclerOptions<model1> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull final model1 model)
    {
        holder.name.setText(model.getName());
        holder.gender.setText(model.getGender());
        holder.bakiharta.setText(model.getBakiharta());
        holder.isteri.setText(model.getHartaIsteri());
        holder.suami.setText(model.getHartaSuami());
        holder.bapa.setText(model.getHartaBapa());
        holder.ibu.setText(model.getHartaIbu());
        holder.anakP.setText(model.getHartaAnakP());
        holder.anakL.setText(model.getHartaAnakL());

    }
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowlaporan,parent,false);
        return new myviewholder(view);
    }

    public class  myviewholder extends RecyclerView.ViewHolder
   {
       TextView name,gender,bakiharta,isteri,suami,ibu,bapa,anakL,anakP;

       public myviewholder(@NonNull View itemView) {
           super(itemView);

           name = (TextView) itemView.findViewById(R.id.namatext);
           gender = (TextView) itemView.findViewById(R.id.gendertext);

           bakiharta = (TextView) itemView.findViewById(R.id.bakihartatext);
           isteri = (TextView) itemView.findViewById(R.id.isteritext);
           suami = (TextView) itemView.findViewById(R.id.suamitext);
           ibu = (TextView) itemView.findViewById(R.id.ibutext);
           bapa = (TextView) itemView.findViewById(R.id.bapatext);
           anakL = (TextView) itemView.findViewById(R.id.anakLtext);
           anakP = (TextView) itemView.findViewById(R.id.anakPtext);

       }

   }

}

