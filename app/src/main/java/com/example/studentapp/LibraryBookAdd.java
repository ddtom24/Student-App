package com.example.studentapp;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.studentapp.Models.LibraryBook;
import com.example.studentapp.Views.LibraryRecycler;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class LibraryBookAdd extends AppCompatActivity {
    //declaration of variables/reference objects
    private ImageView cover, back, home;
    private EditText title, author, edition, isbn;
    private Button upload;
    private StorageReference sref;
    private Uri imagePath;
    private ArrayList reviews;
    private RadioGroup rg;
    private RadioButton business, computing, economics, mathematics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_book_add);
        cover = findViewById(R.id.iv_libBookAdd_cover);
        title = findViewById(R.id.et_libBookAdd_title);
        author = findViewById(R.id.et_libBookAdd_author);
        edition = findViewById(R.id.et_libBookAdd_edition);
        isbn = findViewById(R.id.et_libBookAdd_isbn);
        upload = findViewById(R.id.btn_libBookAdd_upload);
        back = findViewById(R.id.iv_libBookAdd_back);
        home = findViewById(R.id.iv_librarybookadd_home);
        rg = findViewById(R.id.rg_librarybookadd);
        business = findViewById(R.id.rb_librarybookadd_business);
        computing = findViewById(R.id.rb_librarybookadd_computing);
        economics = findViewById(R.id.rb_librarybookadd_economics);
        mathematics = findViewById(R.id.rb_librarybookadd_mathematics);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LibraryBookAdd.this, Dashboard.class));
            }
        });
        sref = FirebaseStorage.getInstance().getReference("_library_book_img");
        reviews = null;
        cover.setOnClickListener(new View.OnClickListener() {
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
                LibraryBookAdd.super.onBackPressed();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("_library_book_");
                String pk = dbref.push().getKey();
                StorageReference reference = sref.child(pk + "." + getExtension(imagePath));

                reference.putFile(imagePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url = uri.toString();
                             //   if(categ.getText().length() > 0 && title.getText().length() > 0 && author.getText().length() > 0
                                if((business.isChecked() || computing.isChecked() || economics.isChecked() || mathematics.isChecked())
                                        && title.getText().length() > 0 && author.getText().length() > 0
                                        && edition.getText().length() > 0 && isbn.getText().length() > 0){

                                    if (business.isChecked()){
                                        String category = business.getText().toString();
                                        LibraryBook libraryBook = new LibraryBook(pk, category, title.getText().toString(),
                                                author.getText().toString(),
                                                url,
                                                edition.getText().toString(),
                                                isbn.getText().toString(),
                                                reviews,
                                                false);
                                        dbref.child(dbref.push().getKey()).setValue(libraryBook);

                                    }else if (computing.isChecked()){
                                        String category = computing.getText().toString();
                                        LibraryBook libraryBook = new LibraryBook(pk, category, title.getText().toString(),
                                                author.getText().toString(),
                                                url,
                                                edition.getText().toString(),
                                                isbn.getText().toString(),
                                                reviews,
                                                false);
                                        dbref.child(dbref.push().getKey()).setValue(libraryBook);

                                    }else if (economics.isChecked()){
                                        String category = economics.getText().toString();
                                        LibraryBook libraryBook = new LibraryBook(pk, category, title.getText().toString(),
                                                author.getText().toString(),
                                                url,
                                                edition.getText().toString(),
                                                isbn.getText().toString(),
                                                reviews,
                                                false);
                                        dbref.child(dbref.push().getKey()).setValue(libraryBook);

                                    }else if (mathematics.isChecked()){
                                        String category = mathematics.getText().toString();
                                        LibraryBook libraryBook = new LibraryBook(pk, category, title.getText().toString(),
                                                author.getText().toString(),
                                                url,
                                                edition.getText().toString(),
                                                isbn.getText().toString(),
                                                reviews,
                                                false);
                                        dbref.child(dbref.push().getKey()).setValue(libraryBook);

                                    }


                                    Toast.makeText(LibraryBookAdd.this, "Upload successful!", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(LibraryBookAdd.this, LibraryRecycler.class);
                                    startActivity(i);

                                }
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        reference.delete();
                        Toast.makeText(LibraryBookAdd.this, "There might be a problem with your connection, try again!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data.getData() != null){
            Picasso.get().load(data.getData()).fit().into(cover);
            imagePath = data.getData();
        }
    }

    private String getExtension (Uri _imagePath){
        ContentResolver resolver = getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(resolver.getType(_imagePath));
    }

}

