package com.example.bookmark.user_management;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmark.R;
import com.example.bookmark.product_review.ReviewBooks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class UserLogin extends AppCompatActivity {

    EditText editTextTextEmailAddress,pwd;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_user_login);

        Button btnsign = findViewById(R.id.btnsign);
        progressDialog= new ProgressDialog(this);

        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        pwd = findViewById(R.id.pwd);

        btnsign.setOnClickListener((view)->{

            if(editTextTextEmailAddress.getText().toString().isEmpty()||pwd.getText().toString().isEmpty()){
                editTextTextEmailAddress.setError("Fill All Fields");
            }



            FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

            if(!(editTextTextEmailAddress.getText().toString().isEmpty()&& pwd.getText().toString().isEmpty())){

                firebaseAuth.signInWithEmailAndPassword(editTextTextEmailAddress.getText().toString(),pwd.getText().toString()).addOnCompleteListener((task) ->{

                    progressDialog.setMessage("Logging In!");
                    progressDialog.setTitle("Login");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    if(task.isSuccessful()){

                        String uid= Objects.requireNonNull(Objects.requireNonNull(task.getResult()).getUser()).getUid();


                        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                        firebaseDatabase.getReference().child("Users").child(uid).child("userType").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                int userType= dataSnapshot.getValue(int.class);

                                if(userType==0){

                                    progressDialog.dismiss();
                                    Intent intent=new Intent(UserLogin.this, ReviewBooks.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    Toast.makeText(UserLogin.this,"Successfully Logged In",Toast.LENGTH_LONG).show();
                                }
                                if(userType==1){

                                    progressDialog.dismiss();
                                    Intent intent=new Intent(UserLogin.this,UserProfile.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    Toast.makeText(UserLogin.this,"Successfully Logged In As Admin",Toast.LENGTH_LONG).show();

                                }



                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {


                            }
                        });

                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(UserLogin.this,""+task.getException(),Toast.LENGTH_LONG).show();
                    }

                });

            }

        });



    }
}