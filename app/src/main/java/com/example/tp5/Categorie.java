package com.example.tp5;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class Categorie implements Serializable {

    private String description;
    private String nom;
    private ArrayList<Produit> listProduit;

    public Categorie(String nom, String description, ArrayList<Produit> listProduit) {
        this.description = description;
        this.nom = nom;
        this.listProduit = listProduit;
    }

    public Categorie(String nom, String description) {
        this.description = description;
        this.nom = nom;
    }

    protected Categorie(Parcel in) {
        description = in.readString();
        nom = in.readString();
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Produit> getListProduit() {
        return listProduit;
    }

    public void setListProduit(ArrayList<Produit> listProduit) {
        this.listProduit = listProduit;
    }




}
