package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studentapp.Adaptors.BookSaleAdaptor;
import com.example.studentapp.Models.SaleBook;
import com.example.studentapp.Views.LibraryRecycler;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BookSaleRecycler extends AppCompatActivity implements BookSaleAdaptor.BookHolder.onBookClicked{
    //declaration of variables/reference objects
   private RecyclerView rv;
   private BookSaleAdaptor adaptor;
   private ArrayList<SaleBook> list = new ArrayList<>();
    //create Database Reference object
   private   DatabaseReference dbref;
   private TextView categ, allBooks;
   private ImageView back, add, home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_sale_recycler);
        rv = findViewById(R.id.rv_booksale);
        dbref = FirebaseDatabase.getInstance().getReference("_sale_book_");
        rv.setLayoutManager(new LinearLayoutManager(BookSaleRecycler.this));
        categ = findViewById(R.id.tv_booksale_library_categories);
        allBooks = findViewById(R.id.tv_booksale_library_allbooks);
        back =findViewById(R.id.iv_booksale_back);
        add =findViewById(R.id.iv_booksale_add);
        home = findViewById(R.id.iv_booksalerecycler_home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookSaleRecycler.this, Dashboard.class));
            }
        });

        categ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookSaleRecycler.this, BookCategories.class));
            }
        });

        allBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookSaleRecycler.this, LibraryRecycler.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              BookSaleRecycler.super.onBackPressed();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookSaleRecycler.this, BookSaleAdd.class));
            }
        });

         dbref.addListenerForSingleValueEvent(listener);
    }
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dss: snapshot.getChildren()){
                list.add(dss.getValue(SaleBook.class));
            }
            adaptor = new BookSaleAdaptor(list, BookSaleRecycler.this);
            rv.setAdapter(adaptor);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    @Override
    public void onBookClick(int pos) {
       Intent i = new Intent(this, SaleBookDetails.class);
       i.putExtra("SaleBook", (Parcelable) list.get(pos));
        startActivity(i);
    }

}