package com.example.studentapp.Adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.studentapp.CommentsRecycler;
import com.example.studentapp.Models.Comments;
import com.example.studentapp.R;


import java.util.ArrayList;
import java.util.List;

public class CommentsAdaptor extends RecyclerView.Adapter<CommentsAdaptor.CommentsHolder> {

    List<Comments> list;
    CommentsHolder.onCommentsClicked listener;


    public CommentsAdaptor(List<Comments> list, CommentsRecycler _listener){
        this.list = list;
        listener = (CommentsHolder.onCommentsClicked) _listener;
    }

    @NonNull
    @Override
    public CommentsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.commentcard, parent, false);
        CommentsHolder holder = new CommentsHolder(v, listener);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull CommentsHolder holder, int position) {

        holder.comment.setText(list.get(position).getComment());
        holder.author.setText(list.get(position).getAuthor());
        holder.timeStamp.setText(list.get(position).getDate());


      //  Picasso.get().load(list.get(position).getUrl()).fit().into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CommentsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        onCommentsClicked listener;
        TextView comment, author, timeStamp;
       // ImageView iv;


        public CommentsHolder(@NonNull View itemView, onCommentsClicked _listener) {
            super(itemView);
           // iv = itemView.findViewById(R);
            comment = itemView.findViewById(R.id.tv_commentcard_text);
            author = itemView.findViewById(R.id.tv_commentcard_author);
            timeStamp = itemView.findViewById(R.id.tv_commentcard_date);

            listener = _listener;
            itemView.setOnClickListener(this);

        }

        public void onClick(View view){
            listener.onCommentsClick(getAdapterPosition());
        }

        public interface onCommentsClicked{

            public void onCommentsClick(int pos);

        }
    }


}