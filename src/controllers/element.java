package controllers;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.*;

import models.produit;

public class element {
	   private static element elt;

	    private HashMap<String,produit> contextCollection;

	    private element()
	    {
	        contextCollection = new HashMap<>();
	    }

	    public static element getElement() 
	    {
	        if (elt == null)
	        	elt = new element();

	        return elt;
	    }

	    public void affichage () {
	    	for (String name: contextCollection.keySet()) {
	    	    String key = name.toString();
	    	    String value = contextCollection.get(name).toString();
	    	    System.out.println(key + " et la valeur " + value);
	    	}
	    }
	    public String getKey(produit p) {
	    	String s = null;
	    	for(java.util.Map.Entry<String, produit> entry: contextCollection.entrySet()) {

	    	      
	    	      if(entry.getValue() == p) {
	    	       s=entry.getKey();
	    	      } 	       
	    }
			return s;}
	    
	    public void putContext(String key,produit context)
	    {
	        contextCollection.put(key, context);
	    }

	    public void removeContext(String key)
	    {
	        contextCollection.remove(key);
	    }

	    public void emptyGlobe() 
	    {
	        contextCollection.clear();
	    }
	    
	    
}
