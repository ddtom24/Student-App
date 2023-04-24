package com.example.studentapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentapp.Models.Activity;
import com.example.studentapp.Models.Comments;
import com.example.studentapp.Models.ForumTopic;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ForumTopicAdd extends AppCompatActivity {
    //declaration of variables/reference objects
    private EditText category, text;
    private Button submit;
    private List<Comments> comments;
    private FirebaseUser mUser;
    private ImageView back, home;
    private DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum_topic_add);

        category = findViewById(R.id.et_forumTopic_title);
        text = findViewById(R.id.et_forumTopic_text);
        submit = findViewById(R.id.btn_forumTopic_submit);
        back = findViewById(R.id.iv_forumtopicAdd_back);
        home = findViewById(R.id.iv_forumtopicadd_home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForumTopicAdd.this, Dashboard.class));
            }
        });
        //2. Link database reference object to the node we want to search in firebase:
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbref = FirebaseDatabase.getInstance().getReference("_forum_topic_");

                if(category.getText().length() > 0 && text.getText().length() > 0 ){
                    //creating a new forum topic object and sending it to the database
                    ForumTopic forumTopic = new ForumTopic(category.getText().toString(), text.getText().toString(), mUser.getEmail().toString());
                    dbref.child(dbref.push().getKey()).setValue(forumTopic);

                    Toast.makeText(ForumTopicAdd.this, "Topic created successfully!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ForumTopicAdd.this, ForumRecycler.class);
                    startActivity(intent);
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForumTopicAdd.super.onBackPressed();
            }
        });

    }

}