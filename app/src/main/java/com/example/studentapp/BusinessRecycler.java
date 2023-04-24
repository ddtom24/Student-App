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

import com.example.studentapp.Adaptors.BusinessAdaptor;
import com.example.studentapp.Models.LibraryBook;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BusinessRecycler extends AppCompatActivity implements BusinessAdaptor.BusinessHolder.onBookClicked {
    //declaration of variables/reference objects
    private RecyclerView rv;
    private BusinessAdaptor businessAdaptor;
    private ArrayList<LibraryBook> list = new ArrayList<>();
    //create Query object to perform the equivalent of the SQL "WHERE" clause
    private Query query;
    private ImageView back, home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_recycler);
        rv = findViewById(R.id.rv_business);
        back = findViewById(R.id.iv_business_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusinessRecycler.super.onBackPressed();
            }
        });
        businessAdaptor = new BusinessAdaptor(list, BusinessRecycler.this);

       query = FirebaseDatabase.getInstance().getReference("_library_book_")
               .orderByChild("category").equalTo("business");
       query.addListenerForSingleValueEvent(listener);

        rv.setLayoutManager(new LinearLayoutManager(BusinessRecycler.this));
        home = findViewById(R.id.iv_business_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessRecycler.this, Dashboard.class));
            }
        });
    }

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dss : snapshot.getChildren()) {
                list.add(dss.getValue(LibraryBook.class));
            }
          businessAdaptor = new BusinessAdaptor(list, BusinessRecycler.this);
           rv.setAdapter(businessAdaptor);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    public void onBookClick(int pos) {
        //passing data to the next activity
        Intent i = new Intent(this, LibraryBookDetails.class);
        i.putExtra("LibraryBook", (Parcelable) list.get(pos));

        startActivity(i);

    }

}




