package com.example.studentapp.Adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.MathematicsRecycler;
import com.example.studentapp.Models.LibraryBook;
import com.example.studentapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MathematicsAdaptor extends RecyclerView.Adapter<MathematicsAdaptor.MathematicsHolder> {

    private ArrayList<LibraryBook> listMathematics;
    private MathematicsHolder.onBookClicked listener;


    public MathematicsAdaptor(ArrayList<LibraryBook> listMathematics, MathematicsRecycler _listener){
        this.listMathematics = listMathematics;
        listener = (MathematicsHolder.onBookClicked) _listener;
    }

    @NonNull
    @Override
    public MathematicsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mathematicscard, parent, false);
        MathematicsHolder holder = new MathematicsHolder(v, listener);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull MathematicsHolder holder, int position) {

        holder.title.setText(listMathematics.get(position).getTitle());
        holder.author.setText(listMathematics.get(position).getAuthor());
        holder.edition.setText(listMathematics.get(position).getEdition());
        holder.isbn.setText(listMathematics.get(position).getIsbn());
        Picasso.get().load(listMathematics.get(position).getUrl()).fit().into(holder.iv);


    }

    @Override
    public int getItemCount() {
        return listMathematics.size();
    }

    public static class MathematicsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        onBookClicked listener;
        TextView title, author, edition, isbn;
        ImageView iv;


        public MathematicsHolder(@NonNull View itemView, onBookClicked _listener) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_mathematicscard);
            title = itemView.findViewById(R.id.tv_mathematicscard_title);
            author = itemView.findViewById(R.id.tv_mathematicscard_author);
            edition = itemView.findViewById(R.id.tv_mathematicscard_edition);
            isbn = itemView.findViewById(R.id.tv_mathematicscard_isbn);

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
