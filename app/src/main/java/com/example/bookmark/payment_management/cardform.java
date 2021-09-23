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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class cardform extends AppCompatActivity {

    EditText txtCusName, txtCrdNo, txtExp, txtCvv;
    String txtUid;
    Button btnsave;
    DatabaseReference reff;
    Card card;
    CheckBox chk;

    protected boolean valid = false;

    private static final Pattern NAME_PATTERN =
            Pattern.compile(".*\\d.*");

    private static final Pattern NUM_PATTERN =
            Pattern.compile("\\d+");

    private static final Pattern CVV_PATTERN =
            Pattern.compile("\\d+"+           //no white spaces
                    ".{3}");

    private static final Pattern EXP_PATTERN =
            Pattern.compile("[0-9][0-9]+/+[0-9][0-9]$");


    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardform);

        /*********************************************************/
        txtCusName=(EditText)findViewById(R.id.txtCusName);
        txtCrdNo =(EditText)findViewById(R.id.txtCrdNo);
        txtExp =(EditText)findViewById(R.id.txtExp);
        txtCvv =(EditText)findViewById(R.id.txtCvv);
        btnsave = (Button)findViewById(R.id.btnSubmit);
        chk = (CheckBox)findViewById(R.id.chkSave);
        txtUid = "user001";




        card = new Card();
        reff = FirebaseDatabase.getInstance().getReference().child("CardData");

        btnsave.setOnClickListener(v -> {
            if(validateInputs()) {
                if (chk.isChecked()) {
                    //String name = String.valueOf(txtCusName.getText());
                    // String num = String.valueOf(txtCrdNo.getText().toString().trim());
                    //String exp = String.valueOf(txtExp.getText());
                    // int Cv = Integer.parseInt(txtCvv.getText().toString().trim());
                    String cardname = String.valueOf(txtCusName.getText());
                    String number = String.valueOf(txtCrdNo.getText().toString().trim());
                    String expdate = String.valueOf(txtExp.getText());
                    String cv = String.valueOf(txtCvv.getText().toString().trim());


                    //card.setCusname(name);
                    //card.setCrdNo(num);
                    //card.setExp(exp);
                    //card.setCvv(Cv);
                    card.setUid(txtUid);
                    card.setCardname(cardname);
                    card.setNumber(number);
                    card.setCv(cv);
                    card.setExpdate(expdate);


                    reff.child(number).setValue(card);
                    Toast.makeText(cardform.this, "Data added!", Toast.LENGTH_LONG).show();
                }

                verifyPin();
            }
            isValid();
        });


        /*********************************************************/

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
            txtExp.setError("Invalid Date Number");
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

    private boolean isValid(){



        String NumInput = txtCrdNo.getText().toString().trim();
        String CvvInput = txtCvv.getText().toString().trim();
        String ExpInput = txtExp.getText().toString().trim();

        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("CardData");
        Query checkCard = dataRef.orderByChild("number").equalTo(NumInput);



        checkCard.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                if (snapshot.exists()){

                    String cvvDB = snapshot.child(NumInput).child("cv").getValue(String.class);
                    String expDB = snapshot.child(NumInput).child("expdate").getValue(String.class);

                    if(cvvDB.equals(CvvInput) && expDB.equals(ExpInput) ){
                        valid = true;
                        Toast.makeText(cardform.this, "Data Matched", Toast.LENGTH_LONG).show();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return valid;
    }

}