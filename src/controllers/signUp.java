package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import utils.ConnectionUtil;

public class signUp implements Initializable{
	  String test;
	    Connection con = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	  @FXML
	    private Label lblErrors;
    @FXML
    private Button Back;

    @FXML
    private Button addUser;

    @FXML
    private ComboBox<String> loadCombo;

    @FXML
    private TextField nom;

    @FXML
    private TextField password;

    @FXML
    private Button reset;

    @FXML
    private AnchorPane root;

    @FXML
    void Back(ActionEvent event) {

    }
    Stage stage;
    public signUp() {
        con = ConnectionUtil.conDB();
    }

    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }

    @FXML
    void addUser(ActionEvent event) {
    	 String status = "Success";
         String nomS = nom.getText();
         String passwordS = password.getText();
         String role =loadCombo.getValue().toString();
    	String sql = "SELECT * FROM users Where email = ? and password = ? and role = ?";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, nomS);
            preparedStatement.setString(2, passwordS);
            preparedStatement.setString(3, role);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next() ) {
            	System.out.println(nomS);
                if(nomS.isEmpty() || passwordS.isEmpty() || role.isEmpty()) {
                    setLblError(Color.TOMATO, "Empty credentials");
                    status = "Error";
                } else {
                    //query
               	 String query = null;
        	        Connection connection = null;
        	        ResultSet resultSet = null;
        	        PreparedStatement preparedStatement = null;
        	        connection = ConnectionUtil.conDB();
        	        query = "INSERT INTO `users`( `email`, `password`,`role`) VALUES (?,?,?)";
        	       
        	        try {
        	            preparedStatement = connection.prepareStatement(query);
        	            preparedStatement.setString(1,nomS);
        	            preparedStatement.setString(2, passwordS);
        	            preparedStatement.setString(3, role);
        	            preparedStatement.execute();

                    
                    } catch (SQLException ex) {
                        System.err.println(ex.getMessage());
                        status = "Exception";
                    }
                }
            } else {
                setLblError(Color.GREEN, "Compte Exite deja");
                
    				compteExiste(stage);	
    			
             	
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            status = "Exception";
        }
    	

         
    }

    @FXML
    void reset(ActionEvent event) {

    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("woh");
		   if (con == null) 	{
				System.out.println("woh");
	            lblErrors.setTextFill(Color.TOMATO);
	            lblErrors.setText("Server Error : Check");
	        } else {
	    		System.out.println("woh");
	            lblErrors.setTextFill(Color.GREEN);
	            lblErrors.setText("Server is up : Good to go");
	            loadCombo.getItems().addAll("Client", "Admin", "Livreur");
	            
	            
	        }
		
	}
public void compteExiste (Stage stage){	
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		
		alert.setHeaderText("compte existe!");
		alert.setContentText("Entrer!");
		
		if (alert.showAndWait().get() == ButtonType.OK){
			System.out.println("You successfully logged out");
			
		} 
	}
    private ObservableList<ObservableList> data;
    String SQL = "SELECT * from users";
    private void fetColumnList() {

        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);

            //SQL FOR SELECTING ALL OF CUSTOMER
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });


                System.out.println("Column [" + i + "] ");

            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());

        }}
        private void fetRowList() {
            data = FXCollections.observableArrayList();
            ResultSet rs;
            try {
                rs = con.createStatement().executeQuery(SQL);

                while (rs.next()) {
                    //Iterate Row
                    ObservableList row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        //Iterate Column
                        row.add(rs.getString(i));
                    }
                    System.out.println("Row [1] added " + row);
                    data.add(row);

                }

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

}

}