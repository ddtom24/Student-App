package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Rooms extends AppCompatActivity {
    //declaration of variables/reference objects
    private ImageView back, home;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rooms);
        back = findViewById(R.id.iv_rooms_back);
        home = findViewById(R.id.iv_rooms_home);
        lv = findViewById(R.id.lv_rooms);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rooms.super.onBackPressed();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Rooms.this, Dashboard.class));
            }
        });
        String[] rooms_areas =  {"Reception                                                                 GF",
                                 "Student Wellbeing                                                    GF",
                                 "Student Services                                                      GF",
                                 "Student Finance                                                       GF",
                                 "Room 101                                                                  GF",
                                 "Room 102                                                                  GF",
                                 "Room 103                                                                  GF",
                                 "Room 104                                                                  GF",
                                 "Room 105                                                                  GF",
                                 "Room 106                                                                  GF" };



        ArrayAdapter<String> rooms_areasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rooms_areas);

        lv.setAdapter(rooms_areasAdapter);

    }
}