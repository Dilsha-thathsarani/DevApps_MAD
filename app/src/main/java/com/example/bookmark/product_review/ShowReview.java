package com.example.bookmark.product_review;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmark.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class ShowReview extends AppCompatActivity {
    RecyclerView displayReview;
    String book1;
    AdapterReview adapterReview;
    ImageButton backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_show_review);

        displayReview= findViewById(R.id.displayReview);
        displayReview.setLayoutManager(new LinearLayoutManager(this));

        book1=getIntent().getStringExtra("book1");

        backBtn=findViewById(R.id.backBtn);



        FirebaseRecyclerOptions<ModelReview> options = new FirebaseRecyclerOptions.Builder<ModelReview>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("ReviewedBooks").child(book1),ModelReview.class)
                .build();

        adapterReview=new AdapterReview(options);
        displayReview.setAdapter(adapterReview);


       backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapterReview.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapterReview.stopListening();
    }
}