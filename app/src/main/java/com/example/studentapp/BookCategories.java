package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.studentapp.Views.LibraryRecycler;

public class BookCategories extends AppCompatActivity {
    //declaration of variables/reference objects
    private ImageView back, home;
    private TextView allbooks, booksale;
    private RelativeLayout business, computing, economics, mathematics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_categories);
        back = findViewById(R.id.iv_categories_back);
        allbooks = findViewById(R.id.tv_categories_search);
        booksale = findViewById(R.id.tv_libcategories_goto_booksale);
        business = findViewById(R.id.rl_categories_business);
        computing = findViewById(R.id.rl_categories_computing);
        economics = findViewById(R.id.rl_categories_economics);
        mathematics = findViewById(R.id.rl_categories_mathematics);
        home = findViewById(R.id.iv_bookcategories_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookCategories.this, Dashboard.class));
            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               BookCategories.super.onBackPressed();
            }
        });

        allbooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BookCategories.this, LibraryRecycler.class);
                startActivity(i);
            }
        });

        booksale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookCategories.this, BookSaleRecycler.class));
            }
        });

        business.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        startActivity(new Intent(BookCategories.this, BusinessRecycler.class).putExtra("Category", "business"));

              }
        });

        computing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookCategories.this, ComputingRecycler.class).putExtra("Category", "computing"));
            }
        });
        economics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookCategories.this, EconomicsRecycler.class).putExtra("Category", "economics"));
            }
        });
        mathematics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookCategories.this, MathematicsRecycler.class).putExtra("Category", "mathematics"));
            }
        });
        }


}