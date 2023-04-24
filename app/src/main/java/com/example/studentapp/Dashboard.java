package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.studentapp.Views.LibraryRecycler;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Dashboard extends AppCompatActivity {
    //declaration of variables/reference objects
private Button fmap, timet, library, moodle, activities, forum, signout;
//create Firebase Auth object to call the signOut() method on the current user
private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        fmap = findViewById(R.id.btn_dash_map);
        timet = findViewById(R.id.btn_dash_timetable);
        library = findViewById(R.id.btn_dash_library);
        moodle = findViewById(R.id.btn_dash_moodle);
        activities = findViewById(R.id.btn_dash_activities);
        forum = findViewById(R.id.btn_dash_forum);
        signout = findViewById(R.id.btn_dash_signout);
        firebaseAuth = FirebaseAuth.getInstance();

        fmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, FloorMap.class);
                startActivity(i);
            }
        });
        timet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Dashboard.this, Timetable.class));
            }
        });
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, LibraryRecycler.class);
                startActivity(i);
            }
        });
        moodle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //  method for accessing an external web page
                goToUrl("https://partnerships.moodle.roehampton.ac.uk");
            }
        });
        activities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, ActivitiesCalendar.class);
                startActivity(i);
            }
        });
        forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent(Dashboard.this, ForumRecycler.class);
               startActivity(i);
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                firebaseAuth.signOut();
                startActivity(new Intent(Dashboard.this, Login.class));
            }
        });

    }

    private void goToUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }





}