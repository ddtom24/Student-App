package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.studentapp.Models.Activity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewActivity extends AppCompatActivity {
//declaring the variables/reference objects
   private EditText name, description, room, date;
   private Button submit;
   private ImageView home, back;
   private CalendarView cv;
   private String selectedDate;
    //Create Database Reference object:
   private DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_activity);
        name = findViewById(R.id.et_activity_name);
        description = findViewById(R.id.et_activity_description);
        room = findViewById(R.id.et_activity_room);
        back = findViewById(R.id.iv_addactivity_back);
        home = findViewById(R.id.iv_addnewactivity_home);
        cv = findViewById(R.id.cv_addnewactivity);
        submit = findViewById(R.id.btn_forumTopic_submit);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddNewActivity.this, Dashboard.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewActivity.super.onBackPressed();
            }
        });

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Link database reference object to the node we want to search in firebase:
                dbref = FirebaseDatabase.getInstance().getReference("_activity_");

                if(name.getText().length() > 0 && room.getText().length() > 0 && description.getText().length() > 0){

                    Activity activity = new Activity(name.getText().toString(), room.getText().toString(), description.getText().toString(), selectedDate);
                    dbref.child(dbref.push().getKey()).setValue(activity);

                    Toast.makeText(AddNewActivity.this, "Upload successful!", Toast.LENGTH_LONG).show();
                    //navigating to another activity
                    Intent i = new Intent(AddNewActivity.this, ActivitiesCalendar.class);
                    startActivity(i);
                }else {
                    Toast.makeText(AddNewActivity.this, "Please check all the fields!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}