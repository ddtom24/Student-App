package com.example.studentapp;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.studentapp.Models.SaleBook;
import com.squareup.picasso.Picasso;


public class SaleBookDetails extends AppCompatActivity {
    //declaration of variables/reference objects
    private ImageView iv, back, home;
    private TextView title, author, description, price;
    private Button contactSeller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salebook_details);
        iv = findViewById(R.id.iv_details_iv);
        title = findViewById(R.id.tv_salebookdetails_title);
        author = findViewById(R.id.tv_salebookdetails_author);
        description = findViewById(R.id.tv_salebookdetails_description);
        price = findViewById(R.id.tv_salebookdetails_price);
        contactSeller = findViewById(R.id.btn_salebookdetails_contactseller);
        back = findViewById(R.id.iv_salebookdetails_back);
        home = findViewById(R.id.iv_salebookdetails_home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SaleBookDetails.this, Dashboard.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaleBookDetails.super.onBackPressed();
            }
        });


        Intent i = getIntent();
        if (i.hasExtra("SaleBook")) {
            SaleBook saleBook = i.getParcelableExtra("SaleBook");
            Picasso.get().load(saleBook.getUrl()).fit().into(iv);
            title.setText(saleBook.getTitle());
            author.setText(saleBook.getAuthor());
            description.setText(saleBook.getDescription());
            price.setText(saleBook.getPrice());
        } else {
            Toast.makeText(SaleBookDetails.this, "No available titles!", Toast.LENGTH_LONG).show();
        }

    }



}