package com.example.studentapp.Adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.BusinessRecycler;
import com.example.studentapp.Models.LibraryBook;
import com.example.studentapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BusinessAdaptor extends RecyclerView.Adapter<BusinessAdaptor.BusinessHolder> {

    ArrayList<LibraryBook> list;
    BusinessHolder.onBookClicked listener;


    public BusinessAdaptor(ArrayList<LibraryBook> list, BusinessRecycler _listener){
        this.list = list;
        listener = _listener;
    }

    @NonNull
    @Override
    public BusinessHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.businesscard, parent, false);
        BusinessHolder holder = new BusinessHolder(v, listener);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull BusinessHolder holder, int position) {
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

    public static class BusinessHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        onBookClicked listener;
        TextView title, author, edition, isbn;
        ImageView iv;

        public BusinessHolder(@NonNull View itemView, onBookClicked _listener) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_businesscard);
            title = itemView.findViewById(R.id.tv_businesscard_title);
            author = itemView.findViewById(R.id.tv_businesscard_author);
            edition = itemView.findViewById(R.id.tv_businesscard_edition);
            isbn = itemView.findViewById(R.id.tv_businesscard_isbn);

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



