package com.example.studentapp.Views;


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
import com.example.studentapp.Adaptors.LibraryAdaptor;
import com.example.studentapp.BookCategories;
import com.example.studentapp.BookSaleRecycler;
import com.example.studentapp.Dashboard;
import com.example.studentapp.LibraryBookAdd;
import com.example.studentapp.LibraryBookDetails;
import com.example.studentapp.Models.LibraryBook;
import com.example.studentapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class LibraryRecycler extends AppCompatActivity implements LibraryAdaptor.LibraryHolder.onBookClicked{
    //declaration of variables/reference objects
    private RecyclerView rv;
    private LibraryAdaptor adaptor;
    private ArrayList<LibraryBook> list = new ArrayList<>();
    private DatabaseReference dbref;
    private ImageView back, add, home;
    private TextView booksale, categ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_recycler);
        rv = findViewById(R.id.rv_library_recycler);
        back = findViewById(R.id.iv_library_back);
        add = findViewById(R.id.iv_library_add);
        booksale= findViewById(R.id.tv_library_goto_booksale);
        categ = findViewById(R.id.tv_library_goto_categ);
        home = findViewById(R.id.iv_library_home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LibraryRecycler.this, Dashboard.class));
            }
        });

        dbref = FirebaseDatabase.getInstance().getReference("_library_book_");
        rv.setLayoutManager(new LinearLayoutManager(LibraryRecycler.this));

        booksale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LibraryRecycler.this, BookSaleRecycler.class);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LibraryRecycler.super.onBackPressed();
            }
        });
        categ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LibraryRecycler.this, BookCategories.class));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LibraryRecycler.this, LibraryBookAdd.class);
                startActivity(i);
            }
        });
         dbref.addListenerForSingleValueEvent(listener);
    }
        ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dss: snapshot.getChildren()){
                list.add(dss.getValue(LibraryBook.class));
            }
            adaptor = new LibraryAdaptor(list, LibraryRecycler.this);
            rv.setAdapter(adaptor);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    @Override
    public void onBookClick(int pos) {
        //passing data to the next activity
        Intent i = new Intent(this, LibraryBookDetails.class);
        i.putExtra("LibraryBook", (Parcelable) list.get(pos));
        startActivity(i);
    }

}

