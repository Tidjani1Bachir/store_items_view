package com.example.tp5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyListAdapter  extends RecyclerView.Adapter<MyListAdapter.viewholder>{
    ArrayList<Categorie> items;
    Context context;

    public MyListAdapter(ArrayList<Categorie> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MyListAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.ma_ligne, parent, false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.viewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.text.setText(items.get(position).getNom());
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, ProduitActivity.class);
        intent.putExtra("object",items.get(position));
        intent.putExtra("position",position);
        if(items.get(position)!=null){
            context.startActivity(intent);
        }
    }
});
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView button;
        public viewholder(@NonNull View itemView) {

            super(itemView);
            text =itemView.findViewById(R.id.text);
            button=itemView.findViewById(R.id.button);
        }
    }
}
