package com.example.bookmark.cart_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookmark.R;

public class ConfirmFinalOrderActivity extends AppCompatActivity {
    private EditText nameEditText, phoneEditText, addressEditText, cityEditText;
    private Button confirmOrderBtn;
    private String totalAmount = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final_order);
        totalAmount = getIntent().getStringExtra("Total Price");


        Toast.makeText(this, "Total Price = $ ", Toast.LENGTH_SHORT).show();

        confirmOrderBtn = (Button) findViewById(R.id.confirm_final_order_btn);
        nameEditText = (EditText)findViewById(R.id.shipment_name);
        phoneEditText =(EditText) findViewById(R.id.shipment_phone);
        addressEditText = (EditText)  findViewById(R.id.shipment_city);

    }
}