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

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    Context context;

    ArrayList<Cards> list;

    String uid = "user001";

    static boolean flag = false;


    public CardAdapter(Context context, ArrayList<Cards> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new CardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CardViewHolder holder, int position) {

        Cards cards = list.get(position);
        holder.cardname.setText(cards.getCardname());
        holder.cardnumber.setText(cards.getNumber());
        holder.expdate.setText(cards.getExpdate());
        holder.cvv.setText(cards.getCv());



        /**********************<< UPDATE >></>*****************************/

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.cardname.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true, 1400)
                        .create();
                //dialogPlus.show();

                View view = dialogPlus.getHolderView();

                EditText name = view.findViewById(R.id.txtcrdnme);
                EditText number = view.findViewById(R.id.txtcrdnum);
                EditText expdate = view.findViewById(R.id.txtcrdexp);
                EditText cv = view.findViewById(R.id.txtcrdcv);

                Button btnUpdate = view.findViewById(R.id.editcrd);

                name.setText(cards.getCardname());
                number.setText(cards.getNumber());
                expdate.setText(cards.getExpdate());
                cv.setText(cards.getCv());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("cardname",name.getText().toString());
                        map.put("number",number.getText().toString());
                        map.put("expdate",expdate.getText().toString());
                        map.put("cv",cv.getText().toString());
                        map.put("uid",uid);

                        flag = true;

                        //String key = String.valueOf(number);
                        String key = number.getText().toString();
                        FirebaseDatabase.getInstance().getReference().child("CardData")
                                .child(key).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.cardname.getContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                        flag = true;

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull @NotNull Exception e) {
                                        Toast.makeText(holder.cardname.getContext(), "Error While Updating", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });



            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(holder.cardname.getContext());
                builder.setTitle("Delete Confirmation");
                builder.setMessage("This card will be deleted");

                builder.setPositiveButton("Delete",new DialogInterface.OnClickListener(){

                    String key = String.valueOf(cards.getNumber());
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        FirebaseDatabase.getInstance().getReference().child("CardData")
                                .child(key).removeValue();
                        Toast.makeText(holder.cardname.getContext(), "Card Removed", Toast.LENGTH_SHORT).show();
                        flag = true;
                    }
                });

                builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        Toast.makeText(holder.cardname.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();


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
