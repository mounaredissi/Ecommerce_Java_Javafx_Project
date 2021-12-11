package controllers;

import java.util.List;

import models.produit;


 
    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    class Singleton {
        // Static variable reference of single_instance
        // of type Singleton
        private static Singleton single_instance = null;

        // Declaring a variable of type String
        public static int s;
        private static List<produit> produitL;

        // Constructor
        // Here we will be creating private constructor
        // restricted to this class itself


        public static List<produit> getListproduit() {
            return produitL;
        }

        public  void setListproduit(produit produit) {
            produitL.add(produit);
        }

        public void addProduitList (produit p)
    {
        produitL.add(p);
    }

        public Singleton()
        {
            this.s = 0;
        }
        public void setInstance(int r) {
            this.s=r;
        }
        public int getId() {
            return s;
        }


        // Static method
        // Static method to create instance of Singleton class
        public static Singleton getInstance()
        {
            if (single_instance == null)
                single_instance = new Singleton();

            return single_instance;
        }
    }

 

