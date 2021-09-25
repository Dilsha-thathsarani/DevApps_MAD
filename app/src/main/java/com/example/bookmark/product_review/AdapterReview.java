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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AdapterReview extends FirebaseRecyclerAdapter<ModelReview,AdapterReview.myViewHolder> {



    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdapterReview(@NonNull FirebaseRecyclerOptions<ModelReview> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ModelReview model) {
        String uid= model.getUid();
        holder.ratingLi.setRating(model.getRate());
        holder.date1.setText(model.getTimestamp());
        holder.commentR.setText(model.getComment());
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users");
        ref.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String name=""+snapshot.child("name").getValue();
                holder.reviewName.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        //holder.imageProf.setImageResource(R.drawable.list);

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_review,parent,false);
        return new myViewHolder(view);

    }

    class  myViewHolder extends RecyclerView.ViewHolder{
         //ImageView imageProf;
         TextView reviewName,date1,commentR;
         RatingBar ratingLi;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            //imageProf=itemView.findViewById(R.id.imageProf);
            reviewName=itemView.findViewById(R.id.reviewName);
            date1=itemView.findViewById(R.id.date1);
            commentR=itemView.findViewById(R.id.commentR);
            ratingLi=itemView.findViewById(R.id.ratingLi);
        }
    }



}