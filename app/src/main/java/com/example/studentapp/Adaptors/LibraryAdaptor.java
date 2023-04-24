package com.example.studentapp.Adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.studentapp.Models.LibraryBook;
import com.example.studentapp.R;
import com.example.studentapp.Views.LibraryRecycler;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LibraryAdaptor extends RecyclerView.Adapter<LibraryAdaptor.LibraryHolder> {
    ArrayList<LibraryBook> list;
    LibraryHolder.onBookClicked listener;
    public LibraryAdaptor(ArrayList<LibraryBook> list, LibraryRecycler _listener){
        this.list = list;
        listener = _listener;
    }

    @NonNull
    @Override
    public LibraryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.librarybookcard, parent, false);
        LibraryHolder holder = new LibraryHolder(v, listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.author.setText(list.get(position).getAuthor());
        holder.edition.setText(list.get(position).getEdition());
        holder.isbn.setText(list.get(position).getIsbn());

        Picasso.get().load(list.get(position).getUrl()).fit().into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class LibraryHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        onBookClicked listener;
        TextView title, author, edition, isbn;
        ImageView iv;

        public LibraryHolder(@NonNull View itemView, onBookClicked _listener) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_librarybookcard);
            title = itemView.findViewById(R.id.tv_librarybookcard_title);
            author = itemView.findViewById(R.id.tv_librarybookcard_author);
            edition = itemView.findViewById(R.id.tv_librarybookcard_edition);
            isbn = itemView.findViewById(R.id.tv_librarybookcard_isbn);

            listener = _listener;
            itemView.setOnClickListener(this);

        }

        public void onClick(View view){
            listener.onBookClick(getAdapterPosition());
        }

        public interface onBookClicked{

            public void onBookClick(int pos);

        }
    }


}
