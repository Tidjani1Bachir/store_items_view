package com.example.tp5;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp5.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Categorie object;
//    recycleview recycleview;
    ActivityResultLauncher<Intent> ajouterCategorieActivityResultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult activityResult) {

                            int result = activityResult.getResultCode();
                            Intent data = activityResult.getData();

                            if (result == RESULT_OK) {

                                String nom = data.getStringExtra("nom");
                                String description =data.getStringExtra("description");

                                listCategories.add(new Categorie(nom,description));

                                adapter =new MyListAdapter(new ArrayList<Categorie>(listCategories));
                                binding.recycleview.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this,"Added with success "+ listCategories.get(listCategories.size()-1).getNom() + " "+listCategories.size(),Toast.LENGTH_LONG).show();



                            } else {
                                Toast.makeText(MainActivity.this, "Operation canceled", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
            );


    ActivityResultLauncher<Intent> ActivityResultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult activityResult) {

                            int result = activityResult.getResultCode();
                            Intent data = activityResult.getData();

                            if (result == RESULT_OK) {

                                object = (Categorie) data.getSerializableExtra("object");
                                int position = data.getIntExtra("position", 0);
                                Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
                                Toast.makeText(MainActivity.this, String.valueOf(object.getListProduit().get(0).getNom()), Toast.LENGTH_SHORT).show();
                                listCategories.set(position, object);

                                adapter.notifyDataSetChanged();

                                adapter =new MyListAdapter(new ArrayList<Categorie>(listCategories));
                                binding.recycleview.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this,"Added with success "+ listCategories.get(listCategories.size()-1).getNom() + " "+listCategories.size(),Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Operation canceled", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
            );
    private static final int REQUEST_CODE = 1;
    ActivityMainBinding binding;
    private Toolbar toolbar;
    ArrayList<Produit> listeProduit;
    ArrayList<Categorie> listCategories;
    RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        toolbar=findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);


        listCategories = new ArrayList<Categorie>();

        listeProduit =new ArrayList<Produit>();
        listeProduit.add(new Produit("Farine Sim 5kg",""));
        listeProduit.add(new Produit("Farine Soisimie 5kg",""));
        listCategories.add(new Categorie("Farine","",listeProduit));
        listCategories.add(new Categorie("Fromage",""));
        listCategories.add(new Categorie("Huile",""));
        listCategories.add(new Categorie("Semoule",""));




        binding.recycleview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter =new MyListAdapter(new ArrayList<Categorie>(listCategories));
        binding.recycleview.setAdapter(adapter);




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add) {
            Intent intent = new Intent(MainActivity.this, AjouterCategorieActivity.class);
            ajouterCategorieActivityResultLauncher.launch(intent);
        }


            return super.onOptionsItemSelected(item);
    }


}
