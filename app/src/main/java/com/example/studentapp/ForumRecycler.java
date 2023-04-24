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

import com.example.studentapp.Adaptors.ForumAdaptor;
import com.example.studentapp.Models.ForumTopic;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ForumRecycler extends AppCompatActivity implements ForumAdaptor.ForumHolder.onForumTopicClicked{
    //declaration of variables/reference objects
    private RecyclerView rv;
    private ForumAdaptor adaptor;
    private ArrayList<ForumTopic> list = new ArrayList<>();
    private   DatabaseReference dbref;
    private ImageView back, add, home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum_recycler);
        rv = findViewById(R.id.rv_forum);
        back = findViewById(R.id.iv_forumRecycler_back);
        add = findViewById(R.id.iv_forum_addtopic);
        home = findViewById(R.id.iv_forumrecycler_home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForumRecycler.this, Dashboard.class));
            }
        });

        dbref = FirebaseDatabase.getInstance().getReference("_forum_topic_");
        rv.setLayoutManager(new LinearLayoutManager(ForumRecycler.this));
        back. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForumRecycler.super.onBackPressed();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForumRecycler.this, ForumTopicAdd.class));
            }
        });
        dbref.addListenerForSingleValueEvent(listener);
        rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ForumRecycler.this, CommentsRecycler.class);
            }
        });
    }
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dss: snapshot.getChildren()){
                list.add(dss.getValue(ForumTopic.class));
            }
            adaptor = new ForumAdaptor(list, ForumRecycler.this);
            rv.setAdapter(adaptor);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    @Override
    public void onForumTopicClick(int pos) {
        Intent i = new Intent(this, CommentsRecycler.class);
        i.putExtra("ForumTopic", (Parcelable) list.get(pos));

        startActivity(i);
    }
}