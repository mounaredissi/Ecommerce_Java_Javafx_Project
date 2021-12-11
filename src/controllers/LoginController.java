package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import utils.ConnectionUtil;
/**
 *
 * @author oXCToo
 */
public class LoginController implements Initializable {
	String user;
	int us; 
    @FXML
    private Label lblErrors;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnSignin;
    @FXML
    private ComboBox<String> txtRole;
  
    /// -- 
    String test;
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    public void handleButtonAction(MouseEvent event) {

        if (event.getSource() == btnSignin) {
            //login here
        	String selected_text = txtRole.getSelectionModel().getSelectedItem();


            if (logIn().equals("Success")) {

            	if(selected_text=="Admin") {
            		try {
                        //add you loading or delays - 😉
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
            	else if(selected_text=="Client") {
            		try {

                        //add you loading or delays - 😉
                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        //stage.setMaximized(true);
                        stage.close();
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Clients.fxml")));
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException ex) {
                        System.err.println(ex.getMessage());
                    }
            	}else if(selected_text=="Livreur") {
            		try {

                        //add you loading or delays - 😉
                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        //stage.setMaximized(true);
                        stage.close();
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Livreur.fxml")));
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException ex) {
                        System.err.println(ex.getMessage());
                    }
            	}
             

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (con == null) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Server Error : Check");
        } else {
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Server is up : Good to go");
            txtRole.getItems().addAll("Client", "Admin", "Livreur");
            fetColumnList();
            fetRowList();
            
        }
    }

    public LoginController() {
        con = ConnectionUtil.conDB();
    }

    private String logIn() {
        String status = "Success";
        String email = txtUsername.getText();
        String password = txtPassword.getText();
        String role =txtRole.getValue().toString();

        if(email.isEmpty() || password.isEmpty() || role.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {
            //query
            String sql = "SELECT * FROM users Where email = ? and password = ? and role = ?";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, role);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next() ) {
                    setLblError(Color.TOMATO, "Enter Correct Email/Password/role");
                    status = "Error";
                } else {
                    setLblError(Color.GREEN, "Login Successful..Redirecting..");
                    user = new String (resultSet.getString("id"));
                    us= Integer.parseInt(user);        
                 	Singleton f = Singleton.getInstance();
                 	f.setInstance(us);
                 	System.out.print(f.getId());
                 	
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }
        
        return status;
    }
    
    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
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
}}