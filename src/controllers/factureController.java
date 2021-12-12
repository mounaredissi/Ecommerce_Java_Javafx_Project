package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import models.commande;
import models.produit;
import models.produitC;
import utils.ConnectionUtil;
public class factureController implements Initializable {



	    @FXML
	    private Button btnAdd;

	    @FXML
	    private Button btnRefresh;

	    @FXML
	    private TableColumn<produit, String> clmCatagoryBrand;

	    @FXML
	    private TableColumn<produit, String>clmCatagoryCreator;

	    @FXML
	    private TableColumn<produit, String> clmCatagoryDate;

	    @FXML
	    private TableColumn<produit, String> clmCatagoryDescription;

	    @FXML
	    private TableColumn<produit, String> clmCatagoryId;

	    @FXML
	    private TableColumn<produit, String>clmCatagoryName;

	    @FXML
	    private TableColumn<String, String> clmSupplyer;

	    @FXML
	    private MenuItem miAddNew;

	    @FXML
	    private MenuItem miDelete;

	    @FXML
	    private MenuItem miSearch;

	    @FXML
	    private MenuItem miUpdate;

	    @FXML
	    private MenuItem miView;

	    @FXML
	    private TableColumn<?, ?> tablClmBox;

	    @FXML
	    private TableView<produitC> tblCatagory;

	    @FXML
	    private TextField tfSearch;
	    ObservableList<produitC>  listeProduit = FXCollections.observableArrayList();
  
	    @FXML
	    void btnAddOnAction(ActionEvent event) {

	    }

	    @FXML
	    void btnRefreshOnAction(ActionEvent event) {

	    }

	    @FXML
	    void miAddNewOnAction(ActionEvent event) {

	    }

	    @FXML
	    void miDeleteOnAction(ActionEvent event) {

	    }

	    @FXML
	    void miSearchOnAction(ActionEvent event) {

	    }

	    @FXML
	    void miUpdateOnAction(ActionEvent event) {

	    }

	    @FXML
	    void miViewOnAction(ActionEvent event) {

	    }

	    @FXML
	    void tblCatagoryOnClick(MouseEvent event) {

	    }

	    @FXML
	    void tfSearchOnType(KeyEvent event) {

	    }

		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			 loadDate();
		}
		private void loadDate() {
	        
			
	        refreshTable();
	        
	        clmCatagoryName.setCellValueFactory(new PropertyValueFactory<>("id"));
	        clmCatagoryBrand.setCellValueFactory(new PropertyValueFactory<>("nom"));
	        clmSupplyer.setCellValueFactory(new PropertyValueFactory<>("qte"));
	        clmCatagoryCreator.setCellValueFactory(new PropertyValueFactory<>("cat"));
	       // clmCatagoryDate.setCellValueFactory(new PropertyValueFactory<>("qte"));
	        //clmCatagoryDescription.setCellValueFactory(new PropertyValueFactory<>("cat"));

	       
	        
	         
	         
	         
	    }

		private void refreshTable() {
			// TODO Auto-generated method stub
			element m= element.getElement();
			HashMap <produit , String> contextCollection= m.getElements();
			for(java.util.Map.Entry<produit,String > entry: contextCollection.entrySet()) {
				produitC p = new produitC (entry.getKey().getId(),entry.getKey().getNom(),entry.getValue(),entry.getKey().getCat());
	  	      	listeProduit.add(p);  	     
	  	      } 	
			tblCatagory.setItems(listeProduit);
			
		}

}
