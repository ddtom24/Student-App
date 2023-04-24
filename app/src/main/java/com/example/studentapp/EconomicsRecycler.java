package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.studentapp.Adaptors.BookSaleAdaptor;
import com.example.studentapp.Adaptors.BusinessAdaptor;
import com.example.studentapp.Adaptors.ComputingAdaptor;
import com.example.studentapp.Adaptors.EconomicsAdaptor;
import com.example.studentapp.Models.LibraryBook;
import com.example.studentapp.Models.SaleBook;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EconomicsRecycler extends AppCompatActivity implements EconomicsAdaptor.EconomicsHolder.onBookClicked{
    //declaration of variables/reference objects
    private RecyclerView rv;
    private EconomicsAdaptor economicsAdaptor;
    private ArrayList<LibraryBook> list = new ArrayList<>();
    private ImageView back, home;
    private Query query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.economics_recycler);
        rv = findViewById(R.id.rv_economics);
        back = findViewById(R.id.iv_economics_back);
        economicsAdaptor = new EconomicsAdaptor(list, EconomicsRecycler.this);

        query = FirebaseDatabase.getInstance().getReference("_library_book_")
                .orderByChild("category").equalTo("economics");
        query.addListenerForSingleValueEvent(listener);
        rv.setLayoutManager(new LinearLayoutManager(EconomicsRecycler.this));
        home = findViewById(R.id.iv_economicsrecycler_home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EconomicsRecycler.this, Dashboard.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EconomicsRecycler.super.onBackPressed();
            }
        });


    }
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dss : snapshot.getChildren()) {
                list.add(dss.getValue(LibraryBook.class));
            }
            economicsAdaptor = new EconomicsAdaptor(list, EconomicsRecycler.this);
            rv.setAdapter(economicsAdaptor);
        }


        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    @Override
    public void onBookClick(int pos) {
        Intent i = new Intent(this, LibraryBookDetails.class);
        i.putExtra("LibraryBook", (Parcelable) list.get(pos));
        startActivity(i);
    }
}


