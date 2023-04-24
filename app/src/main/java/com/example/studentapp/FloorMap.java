package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FloorMap extends AppCompatActivity {
    //declaration of variables/reference objects
    private ImageView back, home;
    private Button rooms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.floor_map);
        back = findViewById(R.id.iv_fmap_back);
        home = findViewById(R.id.iv_floormap_home);
        rooms = findViewById(R.id.btn_floormap_goto_rooms);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FloorMap.this, Dashboard.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              FloorMap.super.onBackPressed();
            }
        });

        rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FloorMap.this, Rooms.class));
            }
        });
    }
}