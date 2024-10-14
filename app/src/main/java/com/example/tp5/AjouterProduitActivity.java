package com.example.tp5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.picerie.databinding.ActivityAjouterCategorieBinding;
import com.example.picerie.databinding.ActivityAjouterProduitBinding;
import com.example.picerie.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class AjouterProduitActivity extends AppCompatActivity{
    ActivityAjouterProduitBinding binding;
    ArrayList<Produit> myArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAjouterProduitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());







        binding.adzzz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String Label = String.valueOf(binding.name.getText());
                    String Description = String.valueOf(binding.Description1.getText());
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("nom",Label);
                    returnIntent.putExtra("description",Description);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();


                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "An error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }});
        binding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
