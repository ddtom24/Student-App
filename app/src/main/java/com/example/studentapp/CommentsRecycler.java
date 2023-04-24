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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studentapp.Adaptors.BusinessAdaptor;
import com.example.studentapp.Adaptors.CommentsAdaptor;
import com.example.studentapp.Models.Comments;
import com.example.studentapp.Models.ForumTopic;
import com.example.studentapp.Models.LibraryBook;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class CommentsRecycler extends AppCompatActivity implements CommentsAdaptor.CommentsHolder.onCommentsClicked{
    //declaration of variables/reference objects
    private RecyclerView rv;
    private CommentsAdaptor adaptor;
    private ArrayList<Comments> list = new ArrayList<>();
    //create Database Reference object
    private DatabaseReference dbref;
    private ImageView back, add, home;
    private FirebaseUser mUser;
    private TextView title, author, text;
    private Query query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments_recycler);
        rv = findViewById(R.id.rv_commentsRecycler);
        back = findViewById(R.id.iv_commentsRecycler_back);
        add = findViewById(R.id.iv_commentsRecycler_add);
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        title = findViewById(R.id.tv_commentsRecycler_title);
        author = findViewById(R.id.tv_commentsRecycler_author);
        text = findViewById(R.id.tv_commentsRecycler_text);
        home = findViewById(R.id.iv_commentsrecycler_home);
        adaptor = new CommentsAdaptor(list, CommentsRecycler.this);



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CommentsRecycler.this, Dashboard.class));
            }
        });

        dbref = FirebaseDatabase.getInstance().getReference("_comments_");
        rv.setLayoutManager(new LinearLayoutManager(CommentsRecycler.this));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommentsRecycler.super.onBackPressed();
            }
        });

        Intent i = getIntent();
        ForumTopic forumTopic = i.getParcelableExtra("ForumTopic");

        title.setText(forumTopic.getCategory());
        text.setText(forumTopic.getPost());
        author.setText(forumTopic.getAuthor());


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText addComment = new EditText(v.getContext());
                AlertDialog.Builder commentDialog = new AlertDialog.Builder(v.getContext());
                commentDialog.setTitle("Reply");
                commentDialog.setView(addComment);
                commentDialog.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String dialogComm =  addComment.getText().toString();

                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("_comments_");
                        //returning intent that started current activity
                        Intent i = getIntent();
                        ForumTopic topic = i.getParcelableExtra("ForumTopic");
                        String category = topic.getCategory();
                        String author = mUser.getEmail();
                        String currentTime = Calendar.getInstance().getTime().toString();
                        Comments comment = new Comments( dialogComm, author, currentTime, category );
                        dbref.child(dbref.push().getKey()).setValue(comment);


                        finish();
                        startActivity(getIntent());
                    }
                });

                commentDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });


                commentDialog.create().show();


            }


        });

        query = FirebaseDatabase.getInstance().getReference("_comments_")
                .orderByChild("category").equalTo(forumTopic.getCategory());
        query.addListenerForSingleValueEvent(listener);
    }
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dss : snapshot.getChildren()) {
                list.add(dss.getValue(Comments.class));
            }
            adaptor = new CommentsAdaptor(list, CommentsRecycler.this);
            rv.setAdapter(adaptor);
        }


        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };


    @Override
    public void onCommentsClick(int pos) {


    }

}