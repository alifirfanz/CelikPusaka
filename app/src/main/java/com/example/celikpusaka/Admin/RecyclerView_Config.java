package com.example.celikpusaka.Admin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.celikpusaka.R;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private BooksAdapter mBooksAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Book> books, List<String> keys){
        mContext= context;
        mBooksAdapter = new BooksAdapter(books,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mBooksAdapter);

    }

    class BookItemView extends RecyclerView.ViewHolder{
        private TextView mTitle;
        private TextView mDetails;

        private String key;

        public BookItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.book_list_item,parent,false));

            mTitle = itemView.findViewById(R.id.title_txtView);
            mDetails = itemView.findViewById(R.id.details_txtView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, BookDetailsActivity.class);
                    intent.putExtra("key",key);
                    intent.putExtra("title",mTitle.getText().toString());
                    intent.putExtra("details",mDetails.getText().toString());

                    mContext.startActivity(intent);
                }
            });
        }
        public void bind(Book book, String key){
            mTitle.setText(book.getTajukNota());
            mDetails.setText(book.getMaklumatNota());
            this.key= key;
        }
    }

    class BooksAdapter extends RecyclerView.Adapter<BookItemView>{
        private List<Book> mBookList;
        private List<String> mKeys;

        public BooksAdapter(List<Book> mBookList, List<String> mKeys) {
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
