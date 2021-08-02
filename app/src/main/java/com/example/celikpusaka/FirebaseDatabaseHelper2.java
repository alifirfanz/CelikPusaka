package com.example.celikpusaka;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper2 {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceBooks;
    private List<Book2> books = new ArrayList<>();


    public interface DataStatus1{
        void DataIsLoaded(List<Book2> books,List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper2(){
        mDatabase =FirebaseDatabase.getInstance();
       mReferenceBooks = mDatabase.getReference("pengiraan").child("alifirfanz");
      //mReferenceBooks = mDatabase.getReference("pengiraanharta");
    }

    //read data
    public void readBook(final DataStatus1 dataStatus){
        mReferenceBooks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                books.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Book2 book = keyNode.getValue(Book2.class);
                    books.add(book);

                }
                dataStatus.DataIsLoaded(books,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //insert data to firebase tukar Book
    public void addBook(Book2 book,final DataStatus1 dataStatus){
        String key = mReferenceBooks.push().getKey();
        mReferenceBooks.child(key).setValue(book)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInserted();
                    }
                });
    }

    //update data into firebase
    public void updateBook(String key,Book2 book,final DataStatus1 dataStatus) {
        mReferenceBooks.child(key).setValue(book)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                });
    }

    //delete data from firebase
    public void deleteBook(String key,final DataStatus1 dataStatus) {
        mReferenceBooks.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }


}
