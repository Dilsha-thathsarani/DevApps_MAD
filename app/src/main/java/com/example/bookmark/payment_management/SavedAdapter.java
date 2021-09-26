package com.example.bookmark.payment_management;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.bookmark.R;
import com.firebase.ui.FirebaseRecyclerViewAdapter;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/*
public class SavedAdapter extends FirebaseRecyclerViewAdapter <Card,SavedAdapter.myViewHolder> {



    public SavedAdapter(@NonNull FirebaseRecyclerOptions<Card> options) {
        super(options);
    }

    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Card model){

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        return null;
    }



    class myViewHolder extends RecyclerView.ViewHolder{

        TextView Hname,CrdNo,Exp;

        public myViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            Hname = itemView.findViewById(R.id.txtCusName);
            CrdNo = itemView.findViewById(R.id.txtCrdNo);
            Exp = itemView.findViewById(R.id.txtExp);
        }

    }

}
*/
/*
public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.SavedHolder> {

    Context context;

    ArrayList<Card> list;

    public SavedAdapter(Context context, ArrayList<Card> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public SavedHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull SavedAdapter.SavedHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SavedHolder extends RecyclerView.ViewHolder{

        TextView HolderName, CardNo,Exp;

        public SavedHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            HolderName = itemView.findViewById(R.id.txtCusName);
            CardNo = itemView.findViewById(R.id.txtCrdNo);
            Exp = itemView.findViewById(R.id.txtExp);
        }
    }
}
*/