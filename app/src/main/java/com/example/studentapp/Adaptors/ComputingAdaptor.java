package com.example.studentapp.Adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.BusinessRecycler;
import com.example.studentapp.ComputingRecycler;
import com.example.studentapp.Models.LibraryBook;
import com.example.studentapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ComputingAdaptor extends RecyclerView.Adapter<ComputingAdaptor.ComputingHolder> {

    private ArrayList<LibraryBook> listComputing;
    private ComputingHolder.onBookClicked listener;


    public ComputingAdaptor(ArrayList<LibraryBook> listComputing, ComputingRecycler _listener){
        this.listComputing = listComputing;
        listener = (ComputingHolder.onBookClicked) _listener;
    }

    @NonNull
    @Override
    public ComputingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.computingcard, parent, false);
        ComputingHolder holder = new ComputingHolder(v, listener);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ComputingHolder holder, int position) {

        holder.title.setText(listComputing.get(position).getTitle());
        holder.author.setText(listComputing.get(position).getAuthor());
        holder.edition.setText(listComputing.get(position).getEdition());
        holder.isbn.setText(listComputing.get(position).getIsbn());
        Picasso.get().load(listComputing.get(position).getUrl()).fit().into(holder.iv);


    }

    @Override
    public int getItemCount() {
        return listComputing.size();
    }

    public static class ComputingHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        onBookClicked listener;
        TextView title, author, edition, isbn;
        ImageView iv;


        public ComputingHolder(@NonNull View itemView, onBookClicked _listener) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_computingcard);
            title = itemView.findViewById(R.id.tv_computingcard_title);
            author = itemView.findViewById(R.id.tv_computingcard_author);
            edition = itemView.findViewById(R.id.tv_computingcard_edition);
            isbn = itemView.findViewById(R.id.tv_computingcard_isbn);

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
