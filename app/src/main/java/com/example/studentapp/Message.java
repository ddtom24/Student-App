/*package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class Message extends AppCompatActivity {
EditText message, subject;
Button send;
DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        message = findViewById(R.id.et_message_message);
        subject = findViewById(R.id.et_message_subject);
        send = findViewById(R.id.btn_message_send);

        dbref = FirebaseDatabase.getInstance().getReference("_messages_");

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
             Message message = new Message(FirebaseAuth.getInstance().getUid(), "gagag", subject.getText().toString(), message.getText().toString(),date.toString());

            }
        });

    }
} */