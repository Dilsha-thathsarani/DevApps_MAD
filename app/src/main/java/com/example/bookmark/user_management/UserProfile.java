package com.example.bookmark.user_management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmark.R;
import com.example.bookmark.product_review.MyReview;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class UserProfile extends AppCompatActivity {

   Button button2;
   ImageButton imageButton2,imageButton3;
   TextView email;
   EditText name;
   String name1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_user_profile);

        button2= findViewById(R.id.button2);
        imageButton2= findViewById(R.id.imageButton2);
        imageButton3= findViewById(R.id.imageButton3);
        email= findViewById(R.id.textView3);
        name= findViewById(R.id.editTextPersonName);
        name1=name.getText().toString();

        loadData();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!name1.isEmpty()){
               editName();
                }else{
                    name.setError("Name can not be empty");
                }
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserProfile.this, MyReview.class);
                startActivity(intent);
            }
        });

    }

    private void loadData() {
      String uid=FirebaseAuth.getInstance().getUid();
      DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Users");
              ref.child(uid)
              .addValueEventListener(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot snapshot) {
                      String mail=""+snapshot.child("email").getValue();
                      String uName=""+snapshot.child("name").getValue();

                      email.setText(mail);
                      name.setText(uName);
                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError error) {

                  }
              });
    }

    private void editName() {

        String uid=FirebaseAuth.getInstance().getUid();

        FirebaseDatabase.getInstance().getReference("Users").child(uid).child("name").setValue(name.getText().toString());
        Toast.makeText(UserProfile.this, "Successfully Saved", Toast.LENGTH_LONG).show();

    }
}