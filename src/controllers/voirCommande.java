package controllers;

import java.awt.Desktop;
import java.io.File;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import models.produitC;
import utils.ConnectionUtil;
import models.commande;
public class 	voirCommande implements Initializable{
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    produit produit = null ;
    ObservableList<commande>  commandeList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<commande, String> date;

    @FXML
    private TableColumn<commande, String> editCol;

    @FXML
    private TableColumn<commande, String> etatCommande;

    @FXML
    private TableColumn<commande, String> idClient;

    @FXML
    private TableColumn<commande, String> idCommande;

    @FXML
    private TableView<commande> commandeTab;

    @FXML
    private Button retour;
    @FXML
    private Button refresh;
	protected commande commande;
	@FXML
	private Button btnlogout;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	// TODO Auto-generated method stub
    	System.out.println("chbik");
    
    	loadDate();
    	
    }
    @FXML
    public void refreshTable() {
        try {
        	commandeList.clear();
            query = "SELECT * FROM commandes";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
            			commandeList.add(new  commande(
                        resultSet.getInt("idC"),
                        resultSet.getString("date"),
                        resultSet.getInt("idClient"),
                        resultSet.getString("etat")));
            			
            			commandeTab.setItems(commandeList);
                        System.out.println(resultSet.getInt("idC"));


            }  
        } catch (SQLException ex) {
            Logger.getLogger(livreurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //for ( produit , qte : hashmap  ) {
        //commandList.add( new {produit , qte})
        //}
        //commadTab.setItems(commandList) ; 
        // model : produit , qte 
        
        
    }
    @FXML
    void print(ActionEvent event) throws Exception {
    	FileWriter writer = new FileWriter("C:\\\\testCommandes.txt"); 
    	for(commande p: commandeList) {
    		  writer.write(p.getIdCommande() +"	"+p.getDate()+"	"+p.getEtat() + System.lineSeparator());
    		 
    	}
		  Desktop d = Desktop.getDesktop(); 
    	  File u = new File("C:\\\\\\\\testCommandes.txt");
		  Desktop.getDesktop().open(u);
    	  writer.close();

    }
    
private void loadDate() {
        
		connection  = ConnectionUtil.conDB();
        refreshTable();
        
        idCommande.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        idClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        etatCommande.setCellValueFactory(new PropertyValueFactory<>("etat"));
        
       
         
    }
@FXML
void printProducts(ActionEvent event) {

}

@FXML
void refreshButton(ActionEvent event) {

	try {
    	commandeList.clear();
        query = "SELECT * FROM commandes";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()){
        			commandeList.add(new  commande(
                    resultSet.getInt("idC"),
                    resultSet.getString("date"),
                    resultSet.getInt("idClient"),
                    resultSet.getString("etat")));
        			
        			commandeTab.setItems(commandeList);
                    System.out.println(resultSet.getInt("idC"));


        }  
        
    } catch (SQLException ex) {
        Logger.getLogger(livreurController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
@FXML
void retour(ActionEvent event) {
	if (event.getSource() == retour) {
		Singleton s = Singleton.getInstance() ;
		int idClient = s.getId() ;
		s.setInstance(0);
		System.out.println(s.getInstance());
		try {

            //add you loading or delays - 
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Admin.fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
		

	}
	else{
		System.out.println("erreur pas de passage ");
	}
}
}