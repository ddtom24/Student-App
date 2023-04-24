package com.example.studentapp.Adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.BusinessRecycler;
import com.example.studentapp.EconomicsRecycler;
import com.example.studentapp.Models.LibraryBook;
import com.example.studentapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EconomicsAdaptor extends RecyclerView.Adapter<EconomicsAdaptor.EconomicsHolder> {

    private ArrayList<LibraryBook> listEconomics;
    private EconomicsHolder.onBookClicked listener;


    public EconomicsAdaptor(ArrayList<LibraryBook> listEconomics, EconomicsRecycler _listener){
        this.listEconomics = listEconomics;
        listener = (EconomicsHolder.onBookClicked) _listener;
    }

    @NonNull
    @Override
    public EconomicsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.economicscard, parent, false);
        EconomicsHolder holder = new EconomicsHolder(v, listener);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull EconomicsHolder holder, int position) {

        holder.title.setText(listEconomics.get(position).getTitle());
        holder.author.setText(listEconomics.get(position).getAuthor());
        holder.edition.setText(listEconomics.get(position).getEdition());
        holder.isbn.setText(listEconomics.get(position).getIsbn());
        Picasso.get().load(listEconomics.get(position).getUrl()).fit().into(holder.iv);


    }

    @Override
    public int getItemCount() {
        return listEconomics.size();
    }

    public static class EconomicsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        onBookClicked listener;
        TextView title, author, edition, isbn;
        ImageView iv;


        public EconomicsHolder(@NonNull View itemView, onBookClicked _listener) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_economicscard);
            title = itemView.findViewById(R.id.tv_economicscard_title);
            author = itemView.findViewById(R.id.tv_economicscard_author);
            edition = itemView.findViewById(R.id.tv_economicscard_edition);
            isbn = itemView.findViewById(R.id.tv_economicscard_isbn);

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
