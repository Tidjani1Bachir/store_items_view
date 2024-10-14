package com.example.tp5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProduitAdapter extends RecyclerView.Adapter<ProduitAdapter.viewholder> {
    ArrayList<Produit> items;
    Context context;
    Categorie categorie;
    public ProduitAdapter(ArrayList<Produit> items,Categorie categorie) {
this.categorie=categorie;
        this.items = items;
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.ma_ligne1, parent, false);

        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.nom.setText(items.get(position).getNom());
        holder.description.setText(items.get(position).getDescription());
        holder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position >= 0 && position < categorie.getListProduit().size()) {

                    Toast.makeText(context, String.valueOf(categorie.getListProduit().size()), Toast.LENGTH_SHORT).show();
                    categorie.getListProduit().remove(position);

                    // Notify the adapter about the removal
                    notifyItemRemoved(position);
                } else {
                    // Handle the case where the position is invalid
                    Log.e("Remove Item", "Invalid position: " + position);
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        if(items !=null) {
            return items.size();
        }else{
            return 0;
        }
    }
    public class viewholder extends RecyclerView.ViewHolder {
        TextView nom;
        TextView description;
Button Delete,Edit;
        ImageView pic;

        public viewholder(@NonNull View itemView) {

            super(itemView);
nom = itemView.findViewById(R.id.nom);
description=itemView.findViewById(R.id.description);
pic =itemView.findViewById(R.id.pic);
Edit=itemView.findViewById(R.id.button2);
Delete=itemView.findViewById(R.id.button3);

        }
    }
}
