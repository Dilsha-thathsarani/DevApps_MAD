package com.example.bookmark.payment_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmark.R;

import android.os.Bundle;

public class SavedMethods extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_methods);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
    }
}