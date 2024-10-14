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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.picerie.AjouterProduitActivity;
import com.example.picerie.Categorie;
import com.example.picerie.Produit;
import com.example.picerie.ProduitAdapter;
import com.example.picerie.databinding.ActivityProduitBinding;

import java.security.cert.CertPathBuilderSpi;
import java.util.ArrayList;

public class ProduitActivity extends AppCompatActivity {
    ActivityProduitBinding binding;
    RecyclerView.Adapter adapter;
    private Categorie object;
    ArrayList<Produit> listeProduit;
    Toolbar toolbar;
    private static final int REQUEST_CODE_A = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProduitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.produitRecycleView.setLayoutManager(new GridLayoutManager(ProduitActivity.this, 4));

        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        // Initialize object and listeProduit
        object = getIntentExtra();
if(listeProduit !=null) {
    adapter = new ProduitAdapter(listeProduit, object);
    binding.produitRecycleView.setAdapter(adapter);
    adapter.notifyDataSetChanged();
}
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult activityResult) {
                    int result = activityResult.getResultCode();
                    Intent data = activityResult.getData();

                    if (result == RESULT_OK) {
                        if (object != null) {
                            String nom = data.getStringExtra("nom");
                            String description = data.getStringExtra("description");
                            if(object.getListProduit() != null) {
                                listeProduit = new ArrayList<Produit>(object.getListProduit());
                            }else{
                                listeProduit = new ArrayList<Produit>();
                            }
                            listeProduit.add(new Produit(nom,description));
                            object.setListProduit(listeProduit);
                            Toast.makeText(ProduitActivity.this,String.valueOf(object.getListProduit().size()),Toast.LENGTH_LONG).show();
                            ProduitAdapter produitAdapter= new ProduitAdapter(listeProduit,object);
                            adapter = produitAdapter;
                            binding.produitRecycleView.setAdapter(adapter);

                            adapter.notifyDataSetChanged();
                            Toast.makeText(ProduitActivity.this, "Added with success ", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(ProduitActivity.this, "Operation canceled", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
    );




    private Categorie getIntentExtra() {
        object = (Categorie) getIntent().getSerializableExtra("object");

        if (object != null && object.getListProduit() != null) {
            listeProduit = object.getListProduit();
            adapter = new ProduitAdapter(listeProduit,object);
            binding.produitRecycleView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            Toast.makeText(ProduitActivity.this, String.valueOf(object.getNom()), Toast.LENGTH_SHORT).show();
            if (object.getListProduit().size() == 3) {
                Toast.makeText(ProduitActivity.this, String.valueOf(object.getListProduit().get(0).getNom() + " " + object.getListProduit().get(1).getNom()), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ProduitActivity.this, "Empty", Toast.LENGTH_SHORT).show();
            }
        }

            return object;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add1) {
            object = (Categorie) getIntent().getSerializableExtra("object");
            int positon = getIntent().getIntExtra("position",0);
            Intent intent = new Intent(ProduitActivity.this, AjouterProduitActivity.class);
            intent.putExtra("object",object);
            intent.putExtra("position",positon);

            activityResultLauncher.launch(intent);
        }
        if(item.getItemId()==R.id.save){
            Intent intent = new Intent(ProduitActivity.this, MainActivity.class);

           // Toast.makeText(this, String.valueOf(positon), Toast.LENGTH_SHORT).show();
           // Toast.makeText(this, String.valueOf(object.getListProduit().get(2).getNom()), Toast.LENGTH_SHORT).show();



        }
        return super.onOptionsItemSelected(item);
    }
}
