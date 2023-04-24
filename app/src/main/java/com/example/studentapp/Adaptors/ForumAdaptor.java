package com.example.studentapp.Adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.ForumRecycler;
import com.example.studentapp.Models.ForumTopic;
import com.example.studentapp.R;

import java.util.ArrayList;

public class ForumAdaptor extends RecyclerView.Adapter<ForumAdaptor.ForumHolder> {

    ArrayList<ForumTopic> list;
    ForumHolder.onForumTopicClicked listener;


    public ForumAdaptor(ArrayList<ForumTopic> list, ForumRecycler _listener){
        this.list = list;
        listener = _listener;
    }

    @NonNull
    @Override
    public ForumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.forumtopiccard, parent, false);
        ForumHolder holder = new ForumHolder(v, listener);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ForumHolder holder, int position) {
        holder.category.setText(list.get(position).getCategory());
        holder.author.setText(list.get(position).getAuthor());
        holder.text.setText(list.get(position).getPost());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ForumHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        onForumTopicClicked listener;
        TextView category, author, text;


        public ForumHolder(@NonNull View itemView, onForumTopicClicked _listener) {
            super(itemView);
            // itemView.setOnClickListener(this);
            category = itemView.findViewById(R.id.tv_forumtopiccard_title);
            author = itemView.findViewById(R.id.tv_forumtopiccard_author);
            text = itemView.findViewById(R.id.tv_forumtopiccard_text);
            listener = _listener;
            //attaching the onClickListener to the entire view holder
            itemView.setOnClickListener(this);
        }

        public void onClick(View view){
            listener.onForumTopicClick(getAdapterPosition());
        }

        public interface onForumTopicClicked{

            public void onForumTopicClick(int pos);

        }
    }


}
