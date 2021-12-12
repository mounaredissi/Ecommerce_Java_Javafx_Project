package controllers;

import java.security.KeyStore.Entry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.cj.xdevapi.Statement;

import javafx.scene.paint.Color;

import java.util.*;

import models.produit;
import utils.ConnectionUtil;

public class element {
	   private static element elt;

	    private HashMap<produit,String> contextCollection;

	    public HashMap<produit, String> getElements () {
			return contextCollection;
	    	
	    }
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
	    	for (produit name: contextCollection.keySet()) {
	    		produit key = name;
	    	    String value = contextCollection.get(name).toString();
	    	    System.out.println(key.getNom() + " et la valeur " + value);
	    	}
	    }
	    public String getKey(produit p) {
	    	String s = null;
	    	for(java.util.Map.Entry<produit,String > entry: contextCollection.entrySet()) {

	    	      
	    	      if(entry.getKey() == p) {
	    	       s=entry.getValue();
	    	      } 	       
	    }
			return s;}
	    
	    public void putContext(produit key,String context)
	    {
	    	if(!contextCollection.containsKey(key))contextCollection.put(key, context);
	    	else {
	    		contextCollection.remove(key) ;contextCollection.put(key, context);
	    	}
	        
	        
	    }

	    public void removeContext(String key)
	    {
	        contextCollection.remove(key);
	    }

	    public void emptyGlobe() 
	    {
	        contextCollection.clear();
	    }
	    public void applyPannier() {
	    	String query = null;
	        Connection connection = null;
	        ResultSet resultSet = null;
	        PreparedStatement preparedStatement = null;
	        connection = ConnectionUtil.conDB();
	        query = "INSERT INTO `commandes`( `date`, `idClient`,`etat`) VALUES (?,?,?)";
	        Singleton s = Singleton.getInstance() ; 
	        int idClient = s.getId() ; 
	        LocalDateTime dateN = LocalDateTime.now();   
	        String date =dateN.toString();
	        String m ="bebba";
	        System.out.println(date);
	        try {
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setString(1,date);
	            preparedStatement.setInt(2, idClient);
	            preparedStatement.setString(3, "en cours");
	            preparedStatement.execute();
		        connection = ConnectionUtil.conDB();
		        String sql = "SELECT * FROM  `commandes` having `idClient` ="+idClient+" and `date` ='"+date+"';";
	            try {
	    	        java.sql.Statement st = connection.createStatement();
	                ResultSet rs = st.executeQuery(sql) ;
	               System.out.println("ahla bik ");
	                if (!rs.next() ) {
	                    System.out.println("no");
	                } else {
	                    int commande =  rs.getInt("idC");
	                 	System.out.print(commande);
	                 	query = "INSERT INTO `produitc`(  `idP`, `idC`,`qte`) VALUES (?,?,?)";
	                	for(java.util.Map.Entry<produit,String > entry: contextCollection.entrySet()) {
	                		preparedStatement = connection.prepareStatement(query);
	        	            preparedStatement.setInt(1,entry.getKey().getId());
	        	            preparedStatement.setInt(2, commande);
	        	            preparedStatement.setInt(3,Integer.parseInt(entry.getValue())); 
	        	            preparedStatement.execute();
	        	           String query2 = "select * from  `produits` where `id` ="+entry.getKey().getId();
	        	           try {
	       	    	        java.sql.Statement ste = connection.createStatement();
	       	                ResultSet rse = ste.executeQuery(query2) ;
	       	                System.out.println("ahla bik ");
	       	             if (!rse.next() ) {
	 	                    System.out.println("no");
	 	                  }
	       	              else {
		       	            System.out.println("ahla bik ");
	 	                    int produitS =  rse.getInt("id");
	 	                    int produitQte= rse.getInt("qte");
	 	                 	System.out.print("lid du produit"+produitS+"et la quantité est "+produitQte);
	 	              	 String query4 = "UPDATE `produits` SET "
	 	                       
	 	                        + "`qte`= ? WHERE id = '"+produitS+"'";
	 	              	 try {
	 	              		 int n= produitQte-Integer.parseInt(entry.getValue()) ;
	 	              		 System.out.println("la nouvelle quantité "+n);
	 	                    preparedStatement = connection.prepareStatement(query4);
	 	                    preparedStatement.setInt(1,produitQte-Integer.parseInt(entry.getValue()));
	 	                    preparedStatement.execute();
	 	                    
	 	                    

	 	                } catch (SQLException ex) {
	 	                    Logger.getLogger(ajoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
	 	                }

	 	           }
	       	               
	        	           }
	        	           catch (SQLException ex) {
	       	                System.err.println(ex.getMessage());
	       	            }
	        	           
	       	               
	        	            
	        	                            		}
	                	
	                }
	            } catch (SQLException ex) {
	                System.err.println(ex.getMessage());
	            }
  
	        } catch (SQLException ex) {
	            Logger.getLogger(ajoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	    
	    
}
