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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
import utils.ConnectionUtil;
import models.commande;
public class 	livreurController implements Initializable{
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
    private Button refresh;
	protected commande commande;


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
            query = "SELECT * FROM `commande`";
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
private void loadDate() {
        
		connection  = ConnectionUtil.conDB();
        refreshTable();
        
        idCommande.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        idClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        etatCommande.setCellValueFactory(new PropertyValueFactory<>("etat"));
        
        //add cell of button edit 
         Callback<TableColumn<commande, String>, TableCell<commande, String>> cellFoctory = (TableColumn<commande, String> param) -> {
            // make cell containing buttons
            final TableCell<commande, String> cell = new TableCell<commande, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button annuleButton = new Button("annul�");
                        Button livreButton = new Button("livr�");

                        annuleButton.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        livreButton.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        annuleButton.setOnMouseClicked((MouseEvent event) -> {
                        	   try {
                                   commande = commandeTab.getSelectionModel().getSelectedItem();
                                   query = "update  `commande` set etat  ='annul�' where idC  ="+commande.getIdCommande();
                           		connection  = ConnectionUtil.conDB();
                                   preparedStatement = connection.prepareStatement(query);
                                   preparedStatement.execute();
                                   refreshTable();
                               } catch (SQLException ex) {
                                   Logger.getLogger(produitController.class.getName()).log(Level.SEVERE, null, ex);
                               }
                            
                        });
                        livreButton.setOnMouseClicked((MouseEvent event) -> {
                     	   try {
                               commande = commandeTab.getSelectionModel().getSelectedItem();
                               query = "update  `commande` set etat  ='recu' where idC  ="+commande.getIdCommande();
                       		connection  = ConnectionUtil.conDB();
                               preparedStatement = connection.prepareStatement(query);
                               preparedStatement.execute();
                               refreshTable();
                           } catch (SQLException ex) {
                               Logger.getLogger(produitController.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           
                        });

                        HBox managebtn = new HBox(livreButton, annuleButton);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(livreButton, new Insets(2, 2, 0, 3));
                        HBox.setMargin(annuleButton, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }
            };
            return cell;
        };
         editCol.setCellFactory(cellFoctory);
         commandeTab.setItems(commandeList);
         
         
         
    }
@FXML
void printProducts(ActionEvent event) {

}

@FXML
void refreshButton(ActionEvent event) {

	try {
    	commandeList.clear();
        query = "SELECT * FROM `commande`";
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


}
