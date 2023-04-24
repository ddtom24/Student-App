package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.studentapp.Models.Student;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    //declaration of variables/reference objects
    private FirebaseAuth m_auth;
    private EditText email, password, student_id;
    private TextView nav_reg, forgot_pass;
    private Button login;
    private DatabaseReference dbref;
    private FirebaseAuth.AuthStateListener mAuthListener;
    //creating array lists to store input values
    private ArrayList<Student> studentsList= new ArrayList<>();
    private ArrayList<String> fnList= new ArrayList<>();
    private ArrayList<String> snList= new ArrayList<>();
    private ArrayList<String> idList= new ArrayList<>();
    private ArrayList<String> emailList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initializing variables:

        email = findViewById(R.id.tv_login_email);
        password = findViewById(R.id.tv_login_pw);
        student_id = findViewById(R.id.tv_login_id);
        forgot_pass = findViewById(R.id.tv_login_forgot_pass);
        nav_reg = findViewById(R.id.tv_login_signup);
        login = findViewById(R.id.btn_login_login);
        m_auth = FirebaseAuth.getInstance();
        dbref = FirebaseDatabase.getInstance().getReference("_student_");

        //Initializing value event listener
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //reading data from firebase and adding it to an array list
                for (DataSnapshot dss : snapshot.getChildren()){
                    Student student = dss.getValue(Student.class);
                    studentsList.add(student);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        //adding a listener to the database reference
        dbref.addListenerForSingleValueEvent(listener);


        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //creating an alert dialog to capture the email address
                EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset your password in just a few steps!");
                passwordResetDialog.setMessage("Enter your email address to receive further instructions: ");
                passwordResetDialog.setView(resetMail);
                passwordResetDialog.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // extract email from the dialog and pass it as a parameter override method sendPasswordResetEmail()
                        String mail = resetMail.getText().toString();
                        m_auth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this, "Check your email!", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, "Incorrect email, try again!", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                passwordResetDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });


                passwordResetDialog.create().show();

            }
        });

        //creating on click listener method to jump to sign up activity
        nav_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        //setting up onClickListener for the Sign In button pressed
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempEmail = email.getText().toString();
                String tempPass = password.getText().toString();
                for (int j = 0; j < studentsList.size(); j++) {
                    idList.add(studentsList.get(j).getId());
                    fnList.add(studentsList.get(j).getFn());
                    snList.add(studentsList.get(j).getSn());
                    emailList.add(studentsList.get(j).getEmail());
                }
                if (email.length() != 0 && password.length() != 0 && student_id.length() != 0) {
                    {
                        m_auth.signInWithEmailAndPassword(tempEmail, tempPass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                //store the input values in the array list and attribute them to the same value object
                                int counter1 = emailList.indexOf(tempEmail);
                                int counter2 = idList.indexOf(student_id.getText().toString());
                                if (1 == 1) {
                                    Toast.makeText(Login.this, "Welcome back!", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(Login.this, Dashboard.class);
                                    i.putExtra(idList.get(counter1), "id");
                                    i.putExtra(fnList.get(counter1), "fn");
                                    i.putExtra(snList.get(counter1), "sn");
                                    i.putExtra(emailList.get(counter1), "email");
                                    startActivity(i);
                                } else {
                                    Toast.makeText(Login.this, "Incorrect details,please try again!", Toast.LENGTH_LONG).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, "Account does not exist, try using 'Sign Up' to register!", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }else {
                    Toast.makeText(Login.this, "Something went wrong, try again!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}



