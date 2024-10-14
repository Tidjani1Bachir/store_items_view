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
import com.example.picerie.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class AjouterCategorieActivity extends AppCompatActivity{
ActivityAjouterCategorieBinding binding;
ArrayList<Categorie> myArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAjouterCategorieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());








        binding.ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String Label = String.valueOf(binding.Label.getText());
                    String Description = String.valueOf(binding.Description.getText());
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
        binding.annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
