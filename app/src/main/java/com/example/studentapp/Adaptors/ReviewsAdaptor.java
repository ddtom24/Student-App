package com.example.studentapp.Adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.LibraryBookDetails;
import com.example.studentapp.Models.Reviews;
import com.example.studentapp.R;


import java.util.List;

public class ReviewsAdaptor extends RecyclerView.Adapter<ReviewsAdaptor.ReviewsHolder> {

    List<Reviews> listReviews;
    ReviewsHolder.onReviewsClicked listener;


    public ReviewsAdaptor(List<Reviews> list, LibraryBookDetails _listener){
        this.listReviews = list;
        listener = _listener;
    }

    @NonNull
    @Override
    public ReviewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviewcard, parent, false);
        ReviewsHolder holder = new ReviewsHolder(v, listener);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsHolder holder, int position) {

        holder.comment.setText(listReviews.get(position).getComment());
        holder.author.setText(listReviews.get(position).getAuthor());
        holder.timeStamp.setText(listReviews.get(position).getDate());


        //  Picasso.get().load(list.get(position).getUrl()).fit().into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return listReviews.size();
    }

    public static class ReviewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        onReviewsClicked listener;
        TextView comment, author, timeStamp;
        // ImageView iv;


        public ReviewsHolder(@NonNull View itemView, onReviewsClicked _listener) {
            super(itemView);
            // iv = itemView.findViewById(R);
            comment = itemView.findViewById(R.id.tv_reviewcard_text);
            author = itemView.findViewById(R.id.tv_reviewcard_author);
            timeStamp = itemView.findViewById(R.id.tv_reviewcard_date);

            listener = _listener;
            itemView.setOnClickListener(this);

        }

        public void onClick(View view){
            listener.onReviewsClick(getAdapterPosition());
        }

        public interface onReviewsClicked{

            public void onReviewsClick(int pos);

        }
    }


}
