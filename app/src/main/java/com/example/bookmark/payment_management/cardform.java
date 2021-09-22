package com.example.bookmark.payment_management;
import com.example.bookmark.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import androidx.appcompat.app.AppCompatActivity;

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
                String name = String.valueOf(txtCusName.getText());
                int num = Integer.parseInt(txtCrdNo.getText().toString().trim());
                String exp = String.valueOf(txtExp.getText());
                int cv = Integer.parseInt(txtCvv.getText().toString().trim());

                card.setCusname(name);
                card.setCrdNo(num);
                card.setExp(exp);
                card.setCvv(cv);
                card.setUid(txtUid);

                reff.push().setValue(card);
                Toast.makeText(cardform.this, "Data added!", Toast.LENGTH_LONG).show();
            }
        });


        /*********************************************************/

       // button = (Button) findViewById(R.id.btnSubmit);
/*
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
        //Intent intent = new Intent(this, activity_ver2.class);
        //startActivity(intent);
    }
}