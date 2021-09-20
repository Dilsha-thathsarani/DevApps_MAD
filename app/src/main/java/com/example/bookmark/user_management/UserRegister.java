package com.example.bookmark.user_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookmark.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class UserRegister extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    FirebaseAuth mAuth;
    FirebaseUser mUser;


    String emailPattern= "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    EditText  email,enterName,enterPwd1,enterPwd2;
    ProgressDialog progressDialog;
    CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_user_register);


         Button registerbtn = findViewById(R.id.registerbtn);
         progressDialog= new ProgressDialog(this);
         mAuth = FirebaseAuth.getInstance();
         mUser=mAuth.getCurrentUser();

         email = findViewById(R.id.email);
         enterName = findViewById(R.id.enterName);
         enterPwd1 = findViewById(R.id.enterPwd1);
         enterPwd2 = findViewById(R.id.enterPwd2);
         checkBox=findViewById(R.id.checkBox);


        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PerformAuth();
            }
        });


    }
        private void PerformAuth() {
            String authemail = email.getText().toString();
            String authName = enterName.getText().toString();
            String authenterPwd1 = enterPwd1.getText().toString();
            String authenterPwd2 = enterPwd2.getText().toString();

            if (!authemail.matches(emailPattern)) {

                email.setError("Enter Correct Email");

            } else if (authemail.isEmpty()|| authName.isEmpty()||authenterPwd1.isEmpty()||authenterPwd2.isEmpty()) {

                Toast.makeText(UserRegister.this,"Please Fill All Fields",Toast.LENGTH_LONG).show();

            } else if (!authenterPwd1.equals(authenterPwd2)) {

                enterPwd2.setError("Password Not Matched");

            } else if (!checkBox.isChecked()) {

                checkBox.setError("You must agree to the Terms & Conditions");

            }


            else{

                progressDialog.setMessage("Please Wait While Registering!");
                progressDialog.setTitle("Register");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                mAuth.createUserWithEmailAndPassword(authemail,authenterPwd1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {

                            String uid= Objects.requireNonNull(Objects.requireNonNull(task.getResult()).getUser()).getUid();
                            Users user= new Users(uid,email.getText().toString(),enterName.getText().toString(),0);
                            firebaseDatabase.getReference().child("Users").child(uid).setValue(user);
                            progressDialog.dismiss();
                            sendLogin();
                            Toast.makeText(UserRegister.this,"Registered Successfully",Toast.LENGTH_LONG).show();
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(UserRegister.this,""+task.getException(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }


       private void sendLogin(){

        Intent intent=new Intent(UserRegister.this,UserLogin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);          //Prevent comeback to register
        startActivity(intent);
       }





}