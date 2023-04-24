package com.example.studentapp.Adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.Models.SaleBook;
import com.example.studentapp.R;
import com.example.studentapp.BookSaleRecycler;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookSaleAdaptor extends RecyclerView.Adapter<BookSaleAdaptor.BookHolder> {

    ArrayList<SaleBook> list;
    BookHolder.onBookClicked listener;


    public BookSaleAdaptor(ArrayList<SaleBook> list, BookSaleRecycler _listener){
        this.list = list;
        listener = _listener;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.salebookcard, parent, false);
       BookHolder holder = new BookHolder(v, listener);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.author.setText(list.get(position).getAuthor());
        holder.description.setText(list.get(position).getDescription());
        holder.price.setText(list.get(position).getPrice());


        Picasso.get().load(list.get(position).getUrl()).fit().into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class BookHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        onBookClicked listener;
       private ImageView iv;
       private TextView  title,author, description, price;

        public BookHolder(@NonNull View itemView, onBookClicked _listener) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_salebookcard_cover);
            title = itemView.findViewById(R.id.tv_salebookcard_title);
            author = itemView.findViewById(R.id.tv_salebookcard_author);
            description = itemView.findViewById(R.id.tv_salebookcard_description);
            price = itemView.findViewById(R.id.tv_salebookcard_price);

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
