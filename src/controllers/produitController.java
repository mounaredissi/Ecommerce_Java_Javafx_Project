package controllers;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import models.produit;
import utils.ConnectionUtil;
import java.io.*;

public class produitController implements Initializable  {
	 @FXML
	private TableColumn<produit, String> editCol;

	 @FXML

    private TableColumn<produit, String> catCol;
	 @FXML

    private TableColumn<produit, String> idCol;

    @FXML
    private TableColumn<produit, String> nomCol;

    @FXML
    private TableView<produit> productTab;
//
    @FXML
    private TableColumn<produit, String> qteCol;
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    produit produit = null ;
    ObservableList<produit>  produitList = FXCollections.observableArrayList();
  
	@Override
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadDate();        
	}
	@FXML
    public void refreshTable() {
        try {
            produitList.clear();
            
            query = "SELECT * FROM `produits`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
            	produitList.add(new  produit(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getInt("qte"),
                        resultSet.getString("cat")));
                productTab.setItems(produitList);
                
            }
            FileWriter writer = new FileWriter("C:\\testtest2.txt"); 
            for(produit str: produitList) {
              writer.write(str.getId()+ " "+ str.getNom()+" "+ str.getQte() +" "+str.getCat()+ "\n");
            }
            writer.close();
            
            
        } catch (SQLException | IOException ex) {
            Logger.getLogger(produitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
private void loadDate() {
        
		connection  = ConnectionUtil.conDB();
        refreshTable();
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        qteCol.setCellValueFactory(new PropertyValueFactory<>("qte"));
        catCol.setCellValueFactory(new PropertyValueFactory<>("cat"));
        
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

                        Button deleteIcon = new Button("delete");
                        Button editIcon = new Button("edit");

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                produit = productTab.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `produits` WHERE id  ="+produit.getId();
                        		connection  = ConnectionUtil.conDB();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();
                            } catch (SQLException ex) {
                                Logger.getLogger(produitController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        	editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            produit = productTab.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/fxml/AddProduct.fxml"));
                            try {
                                loader.load(); //me too 
                            } catch (IOException ex) {
                                Logger.getLogger(produitController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ajoutProduitController addProductController = loader.getController();
                            addProductController.setUpdate(true);
                            addProductController.setTextField(produit.getId(), produit.getNom(), 
                            produit.getQte(),produit.getCat());
                            //refreshTable();
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }
            };
            return cell;
        };
         editCol.setCellFactory(cellFoctory);
         productTab.setItems(produitList);
         
         
         
    }
@FXML
private Button refresh;



@FXML
void refreshButton(ActionEvent event) {
	refreshTable(); 

}
@FXML
private Button addProduct;


@FXML
private Button deleteAll;


@FXML
private Button printProducts;

private void loadStage(String fxml) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

@FXML
void addProduct(ActionEvent event) {
	loadStage("/fxml/AddProduct.fxml");
}

@FXML
void deleteAll(ActionEvent event) {
	try {
	query = "DELETE  FROM `produits`";
	connection  = ConnectionUtil.conDB();
    preparedStatement = connection.prepareStatement(query);
    preparedStatement.execute();
    refreshTable();
} catch (SQLException ex) {
    Logger.getLogger(produitController.class.getName()).log(Level.SEVERE, null, ex);
}
}

@FXML
void printProducts(ActionEvent event) {

}



	


	
	            
}	            
	         
	         
	         
	 