package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.studentapp.Models.SaleBook;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class BookSaleAdd extends AppCompatActivity {
    //declaration of variables/reference objects
    private ImageView back, uploadCover, home;
    private EditText title, author, description, price, contact;
    //create Database Reference object
    private DatabaseReference dbref;
    private Button submit;
    //Create Storage Reference object
    private StorageReference sref;
    private Uri imagePath;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_sale_add);
        back = findViewById(R.id.iv_saleBookAdd_back);
        uploadCover = findViewById(R.id.iv_saleBookAdd_cover);
        title = findViewById(R.id.et_saleBookAdd_title);
        author = findViewById(R.id.et_saleBookAdd_author);
        description = findViewById(R.id.et_saleBookAdd_description);
        price = findViewById(R.id.et_saleBookAdd_price);
        submit = findViewById(R.id.btn_saleBookAdd_upload);
        contact = findViewById(R.id.et_saleBookAdd_contact);
        home = findViewById(R.id.iv_booksaleadd_home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookSaleAdd.this, Dashboard.class));
            }
        });


        sref = FirebaseStorage.getInstance().getReference("_sale_book_image_");

        uploadCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i, 100);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookSaleAdd.super.onBackPressed();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("_sale_book_");
                String pk = dbref.push().getKey();

                final StorageReference reference = sref.child(pk + "." + getExtension(imagePath));
                reference.putFile(imagePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url = uri.toString();
                                if(title.getText().length() > 0 && author.getText().length() > 0
                                        && description.getText().length() > 0 && price.getText().length() > 0 && contact.getText().length() > 0){

                                    SaleBook saleBook = new SaleBook(title.getText().toString(),
                                            author.getText().toString(),
                                            description.getText().toString(),
                                            price.getText().toString(),
                                            url, contact.getText().toString() );

                                    dbref.child(dbref.push().getKey()).setValue(saleBook);

                                    Toast.makeText(BookSaleAdd.this, "Upload successful!", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(BookSaleAdd.this, BookSaleRecycler.class);
                                    startActivity(i);
                                }
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        reference.delete();
                        Toast.makeText(BookSaleAdd.this, "There might be a problem with your connection, try again!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data.getData() != null){
            Picasso.get().load(data.getData()).fit().into(uploadCover);
            imagePath = data.getData();
        }
    }

    private String getExtension (Uri _imagePath){
        ContentResolver resolver = getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(resolver.getType(_imagePath));
    }



}