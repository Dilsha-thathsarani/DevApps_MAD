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

    public boolean valid;
    protected  int n=0;
    protected int exist = 0;

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

                    String encryptedNumber = "";
                    String sourceStr = number;
                    try {
                        encryptedNumber = AESUtils.encrypt(sourceStr);
                        System.out.println("TEST" + "encrypted:" + encryptedNumber);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    card.setUid(txtUid);
                    card.setCardname(cardname);
                    card.setNumber(encryptedNumber);
                    card.setCv(cv);
                    card.setExpdate(expdate);

                    isValid();

                    }


                }

               // verifyPin();
            });
            // isValid();
  //      });


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

        /********************************************************/

        String encrypted = "";
        String sourceStr = NumInput;
        try {
            encrypted = AESUtils.encrypt(sourceStr);
            System.out.println("TEST" + "encrypted:" + encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //encrypted = "ANY_ENCRYPTED_STRING_HERE";
        String decrypted = "";
        try {
            decrypted = AESUtils.decrypt(encrypted);
            System.out.println("TEST"+ "decrypted:" + decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }

       // Toast.makeText(cardform.this, decrypted + " = " + encrypted, Toast.LENGTH_LONG).show();



        /********************************************************/


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

        Toast.makeText(cardform.this, "in Valid", Toast.LENGTH_LONG).show();

        String NumInput = txtCrdNo.getText().toString().trim();
        String CvvInput = txtCvv.getText().toString().trim();
        String ExpInput = txtExp.getText().toString().trim();

        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("SavedCardData");
        Query checkCard = dataRef.orderByChild("number").equalTo(enc(NumInput));

        if(n==0){
            valid = !valid;
            n++;
        }else{
            //valid = false;
        }

        Toast.makeText(cardform.this, "N ="+String.valueOf(n), Toast.LENGTH_LONG).show();

        checkCard.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

               // Toast.makeText(cardform.this, NumInput, Toast.LENGTH_LONG).show();

                if (snapshot.exists()){

                    //Toast.makeText(cardform.this, "In snap", Toast.LENGTH_LONG).show();

                    String cvvDB = snapshot.child(NumInput).child("cv").getValue(String.class);
                    String expDB = snapshot.child(NumInput).child("expdate").getValue(String.class);

                    Toast.makeText(cardform.this, "inner Class", Toast.LENGTH_LONG).show();

                    if(cvvDB.equals(CvvInput) && expDB.equals(ExpInput) ){
                        setValid(true);
                        reff.child(NumInput).setValue(card);
                        verifyPin();
                        exist=1;
                        Toast.makeText(cardform.this, "Data Matched", Toast.LENGTH_LONG).show();
                    }
                    else{
                        exist=2;
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
            System.out.println("TEST" + "encrypted:" + encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypted;

    }

    public void setValid(boolean res){
        valid = res;
    }

    public boolean getValid(){

        if(n==1){
            return valid;
        }else{
            return !valid;
        }
    }

}