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

import com.example.studentapp.Adaptors.MathematicsAdaptor;
import com.example.studentapp.Models.LibraryBook;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MathematicsRecycler extends AppCompatActivity implements MathematicsAdaptor.MathematicsHolder.onBookClicked{
    //declaration of variables/reference objects
    private RecyclerView rv;
    private MathematicsAdaptor mathematicsAdaptor;
    private ArrayList<LibraryBook> list = new ArrayList<>();
    private Query query;
    private ImageView back, home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mathematics_recycler);
        rv = findViewById(R.id.rv_mathematics);
        back = findViewById(R.id.iv_mathematics_back);
        home = findViewById(R.id.iv_mathematics_home);
        mathematicsAdaptor = new MathematicsAdaptor(list, MathematicsRecycler.this);

        query = FirebaseDatabase.getInstance().getReference("_library_book_")
                .orderByChild("category").equalTo("mathematics");
        query.addListenerForSingleValueEvent(listener);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MathematicsRecycler.this, Dashboard.class));
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(MathematicsRecycler.this));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MathematicsRecycler.super.onBackPressed();
            }
        });
    }
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dss : snapshot.getChildren()) {
                list.add(dss.getValue(LibraryBook.class));
            }
            mathematicsAdaptor = new MathematicsAdaptor(list, MathematicsRecycler.this);
            rv.setAdapter(mathematicsAdaptor);
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

