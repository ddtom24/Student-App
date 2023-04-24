package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentapp.Models.Activity;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class ActivitiesCalendar extends AppCompatActivity {
    //declaration of variables/reference objects
    private ArrayList<Activity> list = new ArrayList<>();
    private TextView name, room, description;
    private int index;
    private ImageView back, add, left, right, home;
    private CalendarView calendarView;
    private TextView date;
    //Create Database Reference object:
    private DatabaseReference dbref;

    //basic application startup logic, initialization of variables:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_calendar);
        name = findViewById(R.id.tv_activities_name);
        room = findViewById(R.id.tv_activities_room);
        description = findViewById(R.id.tv_activities_description);
        home = findViewById(R.id.iv_activitiescalendar_home);
        left = findViewById(R.id.iv_activities_left);
        right = findViewById(R.id.iv_activities_right);
        back = findViewById(R.id.iv_activitiescalendar_back);
        calendarView = findViewById(R.id.cv_activities_calendar);
        date = findViewById(R.id.tv_activities_date);
        add =findViewById(R.id.iv_activitiescalendar_addactivity);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivitiesCalendar.this, Dashboard.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivitiesCalendar.super.onBackPressed();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivitiesCalendar.this, AddNewActivity.class);
                startActivity(i);
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

             //   selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;

            }
        });
      /*  query = FirebaseDatabase.getInstance().getReference("_activity_")
                .orderByChild("date").equalTo(selectedDate);
        query.addListenerForSingleValueEvent(listener);*/


        // Initialise dbref and point to node to read in firebase
        dbref = FirebaseDatabase.getInstance().getReference("_activity_");

        // Attach listener to dbref object
        dbref.addListenerForSingleValueEvent(listener);

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index < list.size()-1){
                    index++;
                    name.setText(list.get(index).getName());
                    room.setText(list.get(index).getRoom());
                    description.setText(list.get(index).getDescription());
                    date.setText(list.get(index).getDate());

                }
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 0){
                    index--;
                    name.setText(list.get(index).getName());
                    room.setText(list.get(index).getRoom());
                    description.setText(list.get(index).getDescription());
                    date.setText(list.get(index).getDate());
                }
            }
        });

    }
    //initialize Value Event Listener
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dss: snapshot.getChildren()){
                Activity activity = dss.getValue(Activity.class);
                list.add(activity);
            }
            name.setText(list.get(index).getName());
            room.setText(list.get(index).getRoom());
            description.setText(list.get(index).getDescription());
            date.setText(list.get(index).getDate());

         /*   if (!list.isEmpty()){
                name.setText(list.get(index).getName());
                room.setText(list.get(index).getRoom());
                description.setText(list.get(index).getDescription());
                date.setText(list.get(index).getDate());
            } else {

            }  */

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

}
