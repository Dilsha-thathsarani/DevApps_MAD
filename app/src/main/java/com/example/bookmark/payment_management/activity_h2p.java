package com.example.bookmark.payment_management;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.bookmark.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class activity_h2p extends AppCompatActivity {

    private Button button;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_h2p);

        button = (Button) findViewById(R.id.savedbtn);
        button2 = (Button) findViewById(R.id.newbtn);
        button3 = (Button) findViewById(R.id.btnManage);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSaved();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNew();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openManage();
            }
        });

    }

    public void openSaved(){
        Intent intent = new Intent(this, Addedcards2.class);
        startActivity(intent);
    }

    public void openManage(){
        Intent intent = new Intent(this, Addedcards.class);
        startActivity(intent);
    }

    public void openNew(){
        Intent intent = new Intent(this, cardform.class);
        startActivity(intent);
    }

    public  void showSuccess(){
        Intent intent = new Intent(this, activity_payStatus2.class);
        startActivity(intent);
    }



}