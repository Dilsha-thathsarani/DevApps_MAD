package com.example.bookmark.payment_management;
import com.example.bookmark.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class cardform extends AppCompatActivity {

    EditText txtCusName, txtCrdNo, txtExp, txtCvv;
    String txtUid;
    Button btnsave,btnCancel;
    DatabaseReference reff;
    Card card;
    CheckBox chk;

    public boolean valid;
    protected  int n=0;
    protected int exist = 0;

    private static final Pattern NAME_PATTERN =
            Pattern.compile(".*\\d.*");

    private static final Pattern NUM_PATTERN =
            Pattern.compile("\\d+");

    private static final Pattern CVV_PATTERN =
            Pattern.compile("\\d+"+
                    ".{3}");

    private static final Pattern EXP_PATTERN =
            Pattern.compile("[0-9][0-9]+/+[0-9][0-9]$");


    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cardform);

        txtCusName=(EditText)findViewById(R.id.txtCusName);
        txtCrdNo =(EditText)findViewById(R.id.txtCrdNo);
        txtExp =(EditText)findViewById(R.id.txtExp);
        txtCvv =(EditText)findViewById(R.id.txtCvv);
        btnsave = (Button)findViewById(R.id.btnSubmit);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        chk = (CheckBox)findViewById(R.id.chkSave);
        txtUid = "user001";
        card = new Card();
        reff = FirebaseDatabase.getInstance().getReference().child("CardData");

        btnsave.setOnClickListener(v -> {
            if(validateInputs()) {            //Form Validation
                    String cardname = String.valueOf(txtCusName.getText());
                    String number = (txtCrdNo.getText().toString().trim());
                    String expdate = String.valueOf(txtExp.getText());
                    String cv = (txtCvv.getText().toString().trim());

                    String encryptedNumber = enc(number); //encrypt card number

                    card.setUid(txtUid);
                    card.setCardname(cardname);
                    card.setNumber(encryptedNumber);
                    card.setCv(cv);
                    card.setExpdate(expdate);
                    }
                isValid();  //validate card data with database
            });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cardform.this, activity_h2p.class);
                startActivity(intent);
            }
        });
    }

    public void verifyPin(){
        Intent intent = new Intent(this, Addedcards.class);
        startActivity(intent);
    }

    public boolean validateName(){


        String NameInput = txtCusName.getText().toString().trim();

        if (NameInput.isEmpty()) {
            txtCusName.setError("Field can't be empty");
            return false;
        } else if (NAME_PATTERN.matcher(NameInput).matches()) {
            txtCusName.setError("Incorrect Name");
            return false;
        } else {
            txtCusName.setError(null);
            return true;
        }
    }

    public boolean validateNumber(){
        String NumInput = txtCrdNo.getText().toString().trim();


        if (NumInput.isEmpty()) {
            txtCrdNo.setError("Field can't be empty");
            return false;
        } else if (!NUM_PATTERN.matcher(NumInput).matches()) {
            txtCrdNo.setError("Invalid Card Number");
            return false;
        } else {
            txtCrdNo.setError(null);
            return true;
        }
    }

    public boolean validateExp(){

        String ExpInput = txtExp.getText().toString().trim();

        int len = ExpInput.length();


        if (ExpInput.isEmpty()) {
            txtExp.setError("Field can't be empty");
            return false;
        } else if (!EXP_PATTERN.matcher(ExpInput).matches() || len!=5) {
            txtExp.setError("Invalid Date");
            return false;
        } else {
            txtExp.setError(null);
            return true;
        }


    }

    public boolean validateCvv(){
        String CvvInput = txtCvv.getText().toString().trim();

        int len = CvvInput.length();


        if (CvvInput.isEmpty()) {
            txtCvv.setError("Field can't be empty");
            return false;
        } else if (CVV_PATTERN.matcher(CvvInput).matches() || len!=3) {
            txtCvv.setError("Invalid CVV Number");
            return false;
        } else {
            txtCvv.setError(null);
            return true;
        }
    }

    public boolean validateInputs(){
        boolean tmp = true;


        boolean name = validateName();
        boolean num = validateNumber();
        boolean cvv = validateCvv();
        boolean exp = validateExp();

        if(!(name && num && cvv && exp)){
            tmp = false;
        }

        return tmp;
    }

    private void isValid(){
        String NumInput = txtCrdNo.getText().toString().trim();
        String CvvInput = txtCvv.getText().toString().trim();
        String ExpInput = txtExp.getText().toString().trim();

        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("SavedCardData");
        Query checkCard = dataRef.orderByChild("number").equalTo(enc(NumInput));

        checkCard.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                if (snapshot.exists()){

                    String cvvDB = snapshot.child(NumInput).child("cv").getValue(String.class);
                    String expDB = snapshot.child(NumInput).child("expdate").getValue(String.class);

                    if(cvvDB.equals(CvvInput) && expDB.equals(ExpInput) ){
                        if (chk.isChecked()) {
                            reff.child(NumInput).setValue(card); //Insert data to db
                            Toast.makeText(cardform.this, "Card Saved", Toast.LENGTH_LONG).show();
                        }
                        showSuccess();
                    }
                    else{
                        Intent intent = new Intent(cardform.this, activity_payStatus.class);
                          startActivity(intent);
                    }
                }else{
                    Intent intent = new Intent(cardform.this, activity_payStatus.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


    }

    public String enc(String NumInput){
        String encrypted = "";
        String sourceStr = NumInput;
        try {
            encrypted = AESUtils.encrypt(sourceStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypted;

    }


    public  void showSuccess(){
        Intent intent = new Intent(this, activity_payStatus2.class);
        startActivity(intent);
    }

}