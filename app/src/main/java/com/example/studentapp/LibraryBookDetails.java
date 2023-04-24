package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.studentapp.Adaptors.ReviewsAdaptor;
import com.example.studentapp.Models.LibraryBook;
import com.example.studentapp.Models.Reviews;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

public class LibraryBookDetails extends AppCompatActivity implements ReviewsAdaptor.ReviewsHolder.onReviewsClicked{
    //declaration of variables/reference objects
    private RecyclerView rv;
    private ReviewsAdaptor adaptor;
    private final ArrayList<Reviews> list = new ArrayList<>();
    private DatabaseReference dbref;
    private ImageView back, add, home;
    private FirebaseUser mUser;
    private TextView title, author, isbn, edition;
    private Query query;
    private Button makeRes;
    private ImageView iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_book_details);
        rv = findViewById(R.id.rv_reviews);
        back = findViewById(R.id.iv_librarybookdetails_back);
        add = findViewById(R.id.iv_librarybookdetails_addreview);
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        title = findViewById(R.id.tv_librarybookdetails_title);
        author = findViewById(R.id.tv_librarybookdetails_author);
        isbn = findViewById(R.id.tv_librarybookdetails_isbn);
        edition = findViewById(R.id.tv_librarybookdetails_edition);
        home = findViewById(R.id.iv_librarybookdetails_home);
        makeRes = findViewById(R.id.btn_librarybookdetails_reservation);
        iv = findViewById(R.id.iv_librarybookdetails_cover);
        adaptor = new ReviewsAdaptor(list, LibraryBookDetails.this);



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LibraryBookDetails.this, Dashboard.class));
            }
        });

        dbref = FirebaseDatabase.getInstance().getReference("_reviews_");
        rv.setLayoutManager(new LinearLayoutManager(LibraryBookDetails.this));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LibraryBookDetails.super.onBackPressed();
            }
        });
        //returning intent that started current activity
        Intent i = getIntent();
        LibraryBook libraryBook = i.getParcelableExtra("LibraryBook");

        title.setText(libraryBook.getTitle());
        author.setText(libraryBook.getAuthor());
        edition.setText(libraryBook.getEdition());
        isbn.setText(libraryBook.getIsbn());

        Picasso.get().load(libraryBook.getUrl()).fit().into(iv);

      /*  makeRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                libraryBook.setReserved(true);
            }
        }); */
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText addReview = new EditText(v.getContext());
                AlertDialog.Builder reviewDialog = new AlertDialog.Builder(v.getContext());
                reviewDialog.setTitle("Review:");
                //  commentDialog.setMessage("Enter your email address to receive further instructions: ");
                reviewDialog.setView(addReview);
                reviewDialog.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String dialogRev =  addReview.getText().toString();
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("_reviews_");
                        String pk = libraryBook.getBookID();
                        String author = mUser.getEmail();
                        String currentTime = Calendar.getInstance().getTime().toString();
                        Reviews review = new Reviews( dialogRev, author, currentTime, pk );
                        dbref.child(dbref.push().getKey()).setValue(review);
                        finish();
                        startActivity(getIntent());
                    }
                });

                reviewDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                reviewDialog.create().show();
            }
        });

        query = FirebaseDatabase.getInstance().getReference("_reviews_")
                .orderByChild("bookpk").equalTo(libraryBook.getBookID());
        query.addListenerForSingleValueEvent(listener);
    }
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dss : snapshot.getChildren()) {
                list.add(dss.getValue(Reviews.class));
            }
            adaptor = new ReviewsAdaptor(list, LibraryBookDetails.this);
            rv.setAdapter(adaptor);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    @Override
    public void onReviewsClick(int pos) {


    }

}


