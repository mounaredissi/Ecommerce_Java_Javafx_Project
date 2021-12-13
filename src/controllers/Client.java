package controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.produit;
import utils.ConnectionUtil;
public class Client implements Initializable{


	    @FXML

	    private TableColumn<produit, String> catCol;
	    @FXML

	    private TableColumn<produit, String> idCol;

	    @FXML
	    private TableColumn<produit, String> nomCol;
	    @FXML
	    private TableColumn<produit, String> prixCol;

	    @FXML
	    private TableView<produit> productTab;
	    @FXML
	    private TableColumn<produit,String> ajoutPanierCol;

	    @FXML
	    private TableColumn<produit, String> qteCol;

	    @FXML
	    private Button panier;

		@FXML
		private Button btnlogout;
	    ObservableList<produit> data;
	    String query = null;
	    Connection connection = null ;
	    PreparedStatement preparedStatement = null ;
	    ResultSet resultSet = null ;
	    produit produit = null ;


	    ObservableList<produit>  produitList = FXCollections.observableArrayList();

	    public void isRegister(int idProd ,int user) throws SQLException {
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        String sql = "INSERT INTO elementpanier (elementId ,panierId ) VALUES (?, ?)";
	        try {
	            preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setString(1, "elementId");
	            preparedStatement.setString(2, "panierId");


	            resultSet = preparedStatement.executeQuery();


	            System.out.println("A new user was inserted successfully!");


	        } catch (Exception e) {
	            // TODO: handle exception
	            System.out.println("A new user was insertion failed!");
	        }


	    }
	      
	    public void initialize(URL arg0, ResourceBundle arg1) {
	        try {
	            loadDate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        //TableColumn<produit,String> ajoutPanierCol =new TableColumn<produit,String>("ajout au panier ");
	       // productTab.getColumns().addAll(id , nom ,qte , cat,prix );

	    }
	    @FXML
	    private void refreshTable() throws SQLException {

	            produitList.clear();

	            query = "SELECT * FROM produits";
	            preparedStatement = connection.prepareStatement(query);
	            resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()){
	                produitList.add(new  produit(
	                        resultSet.getInt("id"),
	                        resultSet.getString("nom"),
	                        resultSet.getInt("qte"),
	                        resultSet.getString("cat"),
	                        resultSet.getString("prix")));
	                productTab.setItems(produitList);

	            }

	    }
	    private void loadDate() throws SQLException {

	        connection  = ConnectionUtil.conDB();
	        refreshTable();

	        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
	        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
	        qteCol.setCellValueFactory(new PropertyValueFactory<>("qte"));
	        catCol.setCellValueFactory(new PropertyValueFactory<>("cat"));
	        prixCol.setCellValueFactory(new PropertyValueFactory<>("prix"));




	        //add cell of button edit
	        //add cell of button edit
	        Callback<TableColumn<produit, String>, TableCell<produit, String>> cellFoctory = (TableColumn<produit, String> param) -> {
	            // make cell containing buttons
	            final TableCell<produit, String> cell = new TableCell<produit, String>() {
	                @Override
	                public void updateItem(String item, boolean empty) {
	                    super.updateItem(item, empty);
	                    //that cell created only on non-empty rows
	                    if (empty) {
	                        setGraphic(null);
	                        setText(null);

	                    } else {
	                        TextField b = new TextField();
	                        Button ajoutIcon = new Button("+");
	                        //Button editIcon = new Button("edit");
	                        

	                        ajoutIcon.setStyle(
	                                " -fx-cursor: hand ;"
	                                        + "-glyph-size:28px;"
	                                        + "-fx-fill:#ff1744;"
	                        );
                        	

	                       
	                        
	                        ajoutIcon.setOnMouseClicked((MouseEvent event) -> {
	                        	
	                            PreparedStatement preparedStatement = null ;
	                           // ResultSet resultSet = null ;

	                            
	                            produit = productTab.getSelectionModel().getSelectedItem();
	                            	if(Integer.parseInt(b.getText())>produit.getQte()){
	                            		Alert alert = new Alert(AlertType.CONFIRMATION);
	                            		
	                            		alert.setHeaderText("non quantité en stock est limité!");
	                            		alert.setContentText("Entrer!");
	                            		
	                            		if (alert.showAndWait().get() == ButtonType.OK){
	                            			System.out.println("c'est bon");
	                            			
	                            		} 
	
	                        	}
	                            	else {
	                            		Singleton f = Singleton.getInstance();
	    	                         	Globe g =Globe.getGlobe();
	    	                         	g.putContext(produit);
	    	                         	//g.getContext();
	    	                         	System.out.println("la quantité est "+b.getText());
	    	                         	element m = element.getElement();
	    	                         	m.putContext( produit, b.getText());
	    	                         	m.affichage();
	    	                         	 for(int i=0; i<	g.getContext().size() ;i++ )
	    		                            {
	    		                                System.out.printf(String.valueOf(produit));
	    		                            }
	                            	}
	                            
	                         	

	                         	//f.addProduitList(produit);

		                   	   // ObservableList<produit>  liste = FXCollections.observableArrayList();
		                   	   // liste.add(produit);
		                   	   // f.setL(liste);
	                           //List<produit> prodList =Singleton.getListproduit();
	                            //prodList.add(produit);
		                   	   
	                            //System.out.printf(String.valueOf(produit));
	                           

	                         
	                        });
	                        HBox managebtn = new HBox(b, ajoutIcon);
	                        managebtn.setStyle("-fx-alignment:center");
	                        HBox.setMargin(b, new Insets(1, 1, 0, 0));
	                        HBox.setMargin(ajoutIcon, new Insets(1, 2, 0, 1));
	                        setGraphic(managebtn);

	                        setText(null);

	                    }
	                }
	            };
	            return cell;
	        };
	        ajoutPanierCol.setCellFactory(cellFoctory);
	        productTab.setItems(produitList);



	    }
	    @FXML
	    void voirPanier(ActionEvent event) {
	    	

	    }
	    @FXML
	    void loadPanier(ActionEvent event) {
        	try {

                //add you loading or delays - 
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/ViewCategory.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
	    }
	    @FXML
	    void logout(ActionEvent event) {
			if (event.getSource() == btnlogout) {
				Singleton s = Singleton.getInstance() ;
				int idClient = s.getId() ;
				s.setInstance(0);
				System.out.println(s.getInstance());
				try {

	                //add you loading or delays - 😉
	                Node node = (Node) event.getSource();
	                Stage stage = (Stage) node.getScene().getWindow();
	                //stage.setMaximized(true);
	                stage.close();
	                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Login.fxml")));
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

