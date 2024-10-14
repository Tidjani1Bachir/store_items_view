package com.example.tp5;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Produit implements Serializable {
   private String nom;
  private   String description;
  private   Bitmap image;

    public Produit(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
