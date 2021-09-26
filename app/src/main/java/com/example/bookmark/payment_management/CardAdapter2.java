package com.example.bookmark.payment_management;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmark.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;

public class CardAdapter2 extends RecyclerView.Adapter<CardAdapter2.CardViewHolder> {
    Context context;
    ArrayList<Cards> list;
    String uid = "user001";

    static boolean flag = false;


    public CardAdapter2(Context context, ArrayList<Cards> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item2,parent,false);
        return new CardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CardViewHolder holder, int position) {

        Cards cards = list.get(position);
        holder.cardname.setText(cards.getCardname());
        holder.cardnumber.setText(cards.getDecNumber());
        holder.expdate.setText(cards.getExpdate());
        holder.cvv.setText(cards.getCv());




        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, activity_payStatus2.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder{

        TextView cardname, cardnumber, expdate, cvv;

        Button btnEdit,btnDelete;

        public CardViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            cardname = itemView.findViewById(R.id.txtcardname);
            cardnumber = itemView.findViewById(R.id.txtcardnumber);
            expdate = itemView.findViewById(R.id.txtexpdate);
            cvv = itemView.findViewById(R.id.txtcv);

            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button)itemView.findViewById(R.id.btnDelete);

        }
    }

    public static boolean getflag(){
        return flag;
    }

    public static void setflag(){
        flag=false;
    }



}
