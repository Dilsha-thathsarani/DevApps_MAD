package com.example.bookmark.payment_management;
import com.example.bookmark.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class cardform extends AppCompatActivity {

    EditText txtCusName, txtCrdNo, txtExp, txtCvv;
    String txtUid;
    Button btnsave;
    DatabaseReference reff;
    Card card;
    CheckBox chk;


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
            if(chk.isChecked()) {
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
        });


        /*********************************************************/
/*
        button = (Button) findViewById(R.id.btnSubmit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyPin();
            }
        });

*/
        /*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyPin();
            } */
        /*
        button.setOnClickListener(new view.OnClickListener(){
            @Override
            public void onClick (View v){
                verifyPin();
            }

         */
        //});
    }

    public void verifyPin(){
        Intent intent = new Intent(this, Addedcards.class);
        startActivity(intent);
    }
}