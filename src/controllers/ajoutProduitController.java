package controllers;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import models.produit;
import models.categorie;

import utils.ConnectionUtil;

public class ajoutProduitController implements Initializable  {
	  @FXML
	    private ComboBox<String> cat ; 
    @FXML
    private Button btnClose;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField idProd;

    @FXML
    private Label lblHeader;

    @FXML
    private TextField nomProd;

    @FXML
    private TextField qteProd;

    @FXML

    ObservableList<String> categories = FXCollections.observableArrayList();

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    produit produit = null;
    private boolean update;
    public ajoutProduitController() {
    	  connection = ConnectionUtil.conDB();
    	  getCatList() ;  
    	cat = new ComboBox<String>() ; 
       
    	
	}
	@FXML
    void btnUpdateOnAction(ActionEvent event) {
    	  connection = ConnectionUtil.conDB();
          String id = idProd.getText();
          String nom = nomProd.getText();
          String qte = qteProd.getText();

          if (id.isEmpty() || nom.isEmpty() || qte.isEmpty() || cat.getSelectionModel().getSelectedItem() == null ) {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setHeaderText(null);
              alert.setContentText("Please Fill All DATA");
              alert.showAndWait();

          } else {
              getQuery();
              insert();
              FXMLLoader loader = new FXMLLoader ();
              loader.setLocation(getClass().getResource("/fxml/produits.fxml"));
              try {
                  loader.load();
              } catch (IOException ex) {
                  Logger.getLogger(produitController.class.getName()).log(Level.SEVERE, null, ex);
              }
              produitController addProductController = loader.getController();
              addProductController.refreshTable();
              clean();

          }
          

    }
    @FXML
    private void clean() {
        nomProd.setText(null);
        idProd.setText(null);
        qteProd.setText(null);
        
    }

    private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `produits`( `id`, `nom`, `qte`,`cat`) VALUES (?,?,?,?)";

        }else{
            String id = idProd.getText();

        	 query = "UPDATE `produits` SET "
                     + "`id`=?,"
                     + "`nom`=?,"
                     + "`qte`=?,"
                     + "`cat`= ? WHERE id = '"+id+"'";
        }

    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idProd.getText());
            preparedStatement.setString(2, nomProd.getText());
            preparedStatement.setString(3, qteProd.getText());
            preparedStatement.setString(4, cat.getValue().toString());
            preparedStatement.execute();
            

        } catch (SQLException ex) {
            Logger.getLogger(ajoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   
    void setUpdate(boolean b) {
        this.update = b;

    }
	public void setTextField(int id, String nom, int qte, String cate) {
		idProd.setText(String.valueOf(id)); 
		nomProd.setText(nom);
		qteProd.setText(String.valueOf(qte)); 
		cat.getSelectionModel().select(cate);

	}
	void close() {
		
	}
    @FXML
    void categorie(ActionEvent event) {
         
    }
   void getCatList () {
    	try {

            query = "SELECT * FROM `categories`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next())
            {
            	categories.add(new String(resultSet.getString("nom")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(produitController.class.getName()).log(Level.SEVERE, null, ex);
        }
		
    }
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
	cat.getItems().addAll(categories);
	Singleton f = Singleton.getInstance();
 	System.out.print(f.getId());

	
}
    

}
