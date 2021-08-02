package com.example.celikpusaka;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config1 {
    private Context mContext;
    private BooksAdapter mBooksAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Book2> books, List<String> keys){
        mContext= context;
        mBooksAdapter = new BooksAdapter(books,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mBooksAdapter);

    }

    class BookItemView extends RecyclerView.ViewHolder{
        /**   private TextView mTitle;
         private TextView mDetails;  **/

        private TextView NamaSiMati, JantinaSiMati,BakiHarta, Suami, Isteri;
        private TextView Bapa, Ibu, AnakL, AnakP;
        private TextView jumHarta,kosPengurusan;
        private TextView bilBapa, bilIbu, bilAnakL, bilAnakP, bilSuami, bilIsteri;


        private String key;

        public BookItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.pengiraan_list_item,parent,false));

          /*  mTitle = itemView.findViewById(R.id.title_txtView);
            mDetails = itemView.findViewById(R.id.details_txtView); */

            NamaSiMati = itemView.findViewById(R.id.namatext);
            JantinaSiMati = itemView.findViewById(R.id.gendertext);
            jumHarta = itemView.findViewById(R.id.jumhartatext);
            kosPengurusan = itemView.findViewById(R.id.pengurusantext);

            BakiHarta = itemView.findViewById(R.id.bakihartatext);
            Suami = itemView.findViewById(R.id.suamitext);
            Isteri = itemView.findViewById(R.id.isteritext);
            Bapa = itemView.findViewById(R.id.bapatext);
            Ibu = itemView.findViewById(R.id.ibutext);
            AnakL = itemView.findViewById(R.id.anakLtext);
            AnakP = itemView.findViewById(R.id.anakPtext);

            bilBapa = itemView.findViewById(R.id.bilbapatext);
            bilIbu= itemView.findViewById(R.id.bilibutext);
            bilAnakL= itemView.findViewById(R.id.bilanakltext);
            bilAnakP= itemView.findViewById(R.id.bilanakptext);
            bilSuami= itemView.findViewById(R.id.bilsuamitext);
            bilIsteri= itemView.findViewById(R.id.bilisteritext);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,BooksDetailsActivity2.class);
                    intent.putExtra("key",key);
                    /**     intent.putExtra("title",mTitle.getText().toString());
                     intent.putExtra("details",mDetails.getText().toString()); **/
                    intent.putExtra("name",NamaSiMati.getText().toString());
                    intent.putExtra("gender",JantinaSiMati.getText().toString());
                    intent.putExtra("jumharta",jumHarta.getText().toString());
                    intent.putExtra("pengurusan",kosPengurusan.getText().toString());

                    intent.putExtra("bakiharta",BakiHarta.getText().toString());
                    intent.putExtra("hartaSuami",Suami.getText().toString());
                    intent.putExtra("hartaIsteri",Isteri.getText().toString());
                    intent.putExtra("hartaBapa",Bapa.getText().toString());
                    intent.putExtra("hartaIbu",Ibu.getText().toString());
                    intent.putExtra("hartaAnakL",AnakL.getText().toString());
                    intent.putExtra("hartaAnakP",AnakP.getText().toString());


                    intent.putExtra("bapa", bilBapa.getText().toString());
                    intent.putExtra("ibu",bilIbu.getText().toString());
                    intent.putExtra("suami",bilSuami.getText().toString());
                    intent.putExtra("isteri",bilIsteri.getText().toString());
                    intent.putExtra("anakLelaki",bilAnakL.getText().toString());
                    intent.putExtra("anakPerempuan",bilAnakP.getText().toString());


                    mContext.startActivity(intent);
                }
            });
        }
        public void bind(Book2 book, String key){
         /*  mTitle.setText(book.getTajukNota());
            mDetails.setText(book.getMaklumatNota());*/

            NamaSiMati.setText(book.getName());
            JantinaSiMati.setText(book.getGender());
            jumHarta.setText(book.getJumharta());
            kosPengurusan.setText(book.getPengurusan());


            BakiHarta.setText(book.getBakiharta());
            Suami.setText(book.getHartaSuami());
            Isteri.setText(book.getHartaIsteri());
            Bapa.setText(book.getHartaBapa());
            Ibu.setText(book.getHartaIbu());
            AnakP.setText(book.getHartaAnakP());
            AnakL.setText(book.getHartaAnakL());

            bilBapa.setText(book.getBapa());
            bilIbu.setText(book.getIbu());
            bilAnakL.setText(book.getAnakLelaki());
            bilAnakP.setText(book.getAnakPerempuan());
            bilSuami.setText(book.getSuami());
            bilIsteri.setText(book.getIsteri());



            this.key= key;
        }
    }

    class BooksAdapter extends RecyclerView.Adapter<BookItemView>{
        private List<Book2> mBookList;
        private List<String> mKeys;

        public BooksAdapter(List<Book2> mBookList, List<String> mKeys) {
            this.mBookList = mBookList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public BookItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BookItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull BookItemView holder, int position) {
            holder.bind(mBookList.get(position),mKeys.get(position));

        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }
    }
}
