package com.example.bookmark.product_review;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmark.R;
import com.example.bookmark.user_management.UserRegister;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Objects;

public class ReviewBooks extends AppCompatActivity {

    FirebaseDatabase db;
    EditText comment;
    TextView bookname,rateSum;
    Button btnsave,btnedit,btndelete;
    Button btnReview;
    RatingBar ratingbar,rating1;

     //ImageView bookimage;
    //String bookid;

    private FirebaseAuth firebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_review_books);


        comment= findViewById(R.id.textedit11);
        btnsave= findViewById(R.id.button);
        btnedit= findViewById(R.id.button2);
        btndelete= findViewById(R.id.button3);
        ratingbar= findViewById(R.id.ratingBar2);
        bookname=findViewById(R.id.textView);
        rating1=findViewById(R.id.rating1);
        rateSum=findViewById(R.id.rateSum);

        String book1=bookname.getText().toString();

        btnReview=findViewById(R.id.reviewbtn);

        //bookid=getIntent().getStringExtra("bookId");
        firebaseauth=FirebaseAuth.getInstance();

        db=FirebaseDatabase.getInstance();


        loadMyReview();
        loadReviews();

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReview();
            }
        });


        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editReview();
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ReviewBooks.this);
                alertDialog.setTitle("Delete Your Review");
                alertDialog.setMessage("Are you sure want to remove the review");

                final EditText edtAddress = new EditText(ReviewBooks.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                );
                edtAddress.setLayoutParams(lp);
                alertDialog.setIcon(R.drawable.ic_noun_bin_3661721);

                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       deleteReview();
                    }
                });

                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }


        });

        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pass bookName and redirect new activity

                Intent intent=new Intent(ReviewBooks.this,ReviewBooks.class);
                intent.putExtra("book1",book1);
                startActivity(intent);

            }
        });

    }

    //Read
    private void loadMyReview() {

        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Users");
        String  book = bookname.getText().toString().trim();
        ref.child(firebaseauth.getUid()).child("ReviewedBooks").child(book).
                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){

                            String rate=""+dataSnapshot.child("rate").getValue();
                            String myComment=""+dataSnapshot.child("comment").getValue();

                            float myRating=Float.parseFloat(rate);
                            ratingbar.setRating(myRating);
                            comment.setText(myComment);

                            //btnsave.setVisibility(View.INVISIBLE);

                            btnsave.setEnabled(false);
                            btnedit.setEnabled(true);
                            btndelete.setEnabled(true);

                        }else{


                            btnedit.setEnabled(false);
                            btndelete.setEnabled(false);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

//Show Rating
    private  float ratingSum=0;
    private void loadReviews() {
        String b1=bookname.getText().toString();
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("ReviewedBooks");
        ref.child(b1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                ratingSum=0;

                for(DataSnapshot ds:snapshot.getChildren()){
                    float rating=Float.parseFloat(""+ds.child("rate").getValue());
                    ratingSum=ratingSum+rating;


                }



                long numberOfReviews=snapshot.getChildrenCount();
                float avgRating=ratingSum/numberOfReviews;

                rateSum.setText(String.format("%.2f",avgRating)+"["+numberOfReviews+"]");
                rating1.setRating(avgRating);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    //Create

    private void addReview() {
        String  book = bookname.getText().toString().trim();
        String  comment1=comment.getText().toString().trim();
        String uid=firebaseauth.getUid();
        float ratecount=ratingbar.getRating();
        String timestamp=""+System.currentTimeMillis();

        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(timestamp));
        String reviewDate= DateFormat.format("dd/MM/yyyy",calendar).toString();


        if(ratecount>=1.0) {
            Review review = new Review(book, uid, ratecount, comment1,reviewDate);

            DatabaseReference Reviews=FirebaseDatabase.getInstance().getReference("Users");

            DatabaseReference ReviewB=FirebaseDatabase.getInstance().getReference("ReviewedBooks");

            Reviews.child(firebaseauth.getUid()).child("ReviewedBooks").child(book).setValue(review);   // To Get user review count

            ReviewB.child(book).child(firebaseauth.getUid()).setValue(review);                          // To Get book review count

            Toast.makeText(ReviewBooks.this, "Thanks for your valuable review", Toast.LENGTH_LONG).show();

            Intent intent=new Intent(ReviewBooks.this, ReviewBooks.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);          //Prevent comeback to register
            startActivity(intent);

        }else
            Toast.makeText(ReviewBooks.this, "Can not submit empty review", Toast.LENGTH_LONG).show();

    }

    //Update

    private void editReview() {
        addReview();
        Toast.makeText(ReviewBooks.this, "Review updated successfully", Toast.LENGTH_LONG).show();

    }


    //Delete
    private void deleteReview() {
        String book = bookname.getText().toString().trim();

        FirebaseDatabase.getInstance().getReference("Users").child(firebaseauth.getUid()).child("ReviewedBooks").
                child(book).removeValue();
        FirebaseDatabase.getInstance().getReference("ReviewedBooks").child(book).
                child(firebaseauth.getUid()).removeValue();

        Toast.makeText(ReviewBooks.this, "Review Deleted",Toast.LENGTH_LONG).show();

        Intent intent=new Intent(ReviewBooks.this, UserRegister.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);          //Prevent comeback to register
        startActivity(intent);

    }

}
