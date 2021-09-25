package com.example.bookmark.product_review;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmark.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class MyReview extends AppCompatActivity {

    RecyclerView allReview;
    AdapterMyReview adapterMyReview;
    String uid= FirebaseAuth.getInstance().getUid();
    ImageButton backBtn1,imageButton33;
    ArrayList<ModelReview> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_my_review);

        imageButton33=findViewById(R.id.imageButton33);

        allReview= findViewById(R.id.allReview);
        allReview.setLayoutManager(new LinearLayoutManager(this));

        backBtn1=findViewById(R.id.backBtn1);

        FirebaseRecyclerOptions<ModelReview> options = new FirebaseRecyclerOptions.Builder<ModelReview>()
                .setQuery(FirebaseDatabase.getInstance().getReference("Users").child(uid).child("ReviewedBooks"),ModelReview.class)
                .build();

        adapterMyReview=new AdapterMyReview(options);
        allReview.setAdapter(adapterMyReview);

        backBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imageButton33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort();
            }
        });

    }

    private void sort() {

        String [] optins={"Newest","Oldest"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Sort By")
                .setItems(optins, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==0){

                        }else if(which==1){

                        }
                    }
                });

        builder.show();

    }




    @Override
    protected void onStart() {
        super.onStart();
        adapterMyReview.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapterMyReview.stopListening();
    }
}