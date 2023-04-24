package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.studentapp.Models.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    //declaration of variables/reference objects
    private FirebaseAuth m_auth;
    private EditText fn, sn, id, email, pw, conf_pw;
    private Button register;
    private ImageView back;
    private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fn = findViewById(R.id.et_reg_fn);
        sn = findViewById(R.id.et_reg_sn);
        id = findViewById(R.id.et_registration_id);
        email = findViewById(R.id.et_email);
        pw = findViewById(R.id.et_reg_pw);
        conf_pw = findViewById(R.id.et_cf_reg_pw);
        register = findViewById(R.id.btn_register);
        back = findViewById(R.id.iv_reg_back);
        checkBox = findViewById(R.id.cb_reg_checkbox);

        //Instantiate instance firebase auth
        m_auth = FirebaseAuth.getInstance();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.super.onBackPressed();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(id.getText().toString())
                        && !TextUtils.isEmpty(email.getText().toString())
                && !TextUtils.isEmpty(pw.getText().toString())
                && !TextUtils.isEmpty(conf_pw.getText().toString())
                && pw.getText().toString().compareTo(conf_pw.getText().toString()) == 0
                && pw.getText().toString().length() >= 6
                && checkBox.isChecked()){
                    m_auth.createUserWithEmailAndPassword(email.getText().toString(), pw.getText().toString())
                            .addOnCompleteListener(task -> {
                                Toast.makeText(RegisterActivity.this, "Registration completed", Toast.LENGTH_LONG).show();
                                final String authid = m_auth.getUid();
                                //some people do:
                                m_auth.signInWithEmailAndPassword(email.getText().toString(), pw.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                    }
                                });

                                DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("_student_");
                                dbref.child(authid).setValue(new Student(id.getText().toString(), fn.getText().toString(), sn.getText().toString(), email.getText().toString()));
                                m_auth.signOut();
                                Intent i = new Intent(RegisterActivity.this, Login.class);
                                startActivity(i);


                            }).addOnFailureListener(e -> Toast.makeText(RegisterActivity.this, "Registration Failed!", Toast.LENGTH_LONG).show());

                }else {
                    Toast.makeText(RegisterActivity.this, "Something went wrong!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}