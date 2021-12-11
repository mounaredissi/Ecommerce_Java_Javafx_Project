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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import models.produit;
import utils.ConnectionUtil;


public class dashboardController implements Initializable {
	String query = null;
	String querySql= null;
	 int nbr;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    produit produit = null ;
    ObservableList<produit>  produitListe = FXCollections.observableArrayList();
    @FXML
    private TableColumn<produit, String> categorie;
    @FXML
    private TableColumn<produit, String> idProduit;
    @FXML
    private Label nbreClient;

    @FXML
    private Label nbreCommande;

    @FXML
    private Label nbreCommandeNonLivre;

    @FXML
    private Label nbreProduit;

    @FXML
    private TableColumn<produit, String> nomProduit;

    @FXML
    private PieChart pieChart;

    @FXML
    private TableColumn<produit, String> qteEnStock;

    @FXML
    private TableView<produit> tbData;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		loadDate();
		
	}
	@FXML
    public void refreshTable() {
        try {
            produitListe.clear();
            
            query = "SELECT  * FROM `produits`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
            	produitListe.add(new  produit(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getInt("qte"),
                        resultSet.getString("cat")));
                tbData.setItems(produitListe);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(produitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
	
private void loadDate() {
        
		connection  = ConnectionUtil.conDB();
        refreshTable();
        idProduit.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomProduit.setCellValueFactory(new PropertyValueFactory<>("nom"));
        qteEnStock.setCellValueFactory(new PropertyValueFactory<>("qte"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("cat"));

         tbData.setItems(produitListe);
         try {
             
             query = "SELECT  count(*) as n  FROM `produits`";
             preparedStatement = connection.prepareStatement(query);
             resultSet = preparedStatement.executeQuery();
             
             while (resultSet.next()){
            	 nbr= resultSet.getInt("n");
                 nbreProduit.setText(Integer.toString(nbr));
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(produitController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         
         
    }
    
    

}
