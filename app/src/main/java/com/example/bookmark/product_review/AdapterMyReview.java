package com.example.bookmark.product_review;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmark.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AdapterMyReview extends FirebaseRecyclerAdapter<ModelReview,AdapterMyReview.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdapterMyReview(@NonNull FirebaseRecyclerOptions<ModelReview> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ModelReview model) {
        holder.myRating.setRating(model.getRate());
        //holder.rDate.setText(model.getTimestamp());
        holder.rBooks.setText(model.getBookName());

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_my_review,parent,false);
        return new myViewHolder(view);

    }

    class  myViewHolder extends RecyclerView.ViewHolder{

        TextView rBooks,rDate;
        RatingBar myRating;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            rBooks=itemView.findViewById(R.id.rBooks);
            rDate=itemView.findViewById(R.id.rDate);
            myRating=itemView.findViewById(R.id.myRating);
        }
    }
}
