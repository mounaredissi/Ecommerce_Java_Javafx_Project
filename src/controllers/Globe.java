package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;

import models.produit;

public class Globe {

    private static Globe globe;

    private ArrayList<produit> contextCollection;

    private Globe()
    {
        contextCollection = new ArrayList();
    }

    public static Globe getGlobe() 
    {
        if (globe == null)
            globe = new Globe();

        return globe;
    }

    public ArrayList getContext()
    {
        return contextCollection;
    }

    public void putContext(produit key)
    {
        contextCollection.add(key);
    }

    public void removeContext(produit key)
    {
        contextCollection.remove(key);
    }

    public void emptyGlobe() 
    {
        contextCollection.clear();
    }
}