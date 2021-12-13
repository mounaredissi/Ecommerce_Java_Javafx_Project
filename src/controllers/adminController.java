package controllers;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class adminController implements Initializable{

	 @FXML
	    private Button btnDashboard;

	    @FXML
	    private Button btnStudents;

	    @FXML
	    private Button btn_Timetable;

	    @FXML
	    private Button btnSettings;

	    @FXML
	    private Button btnUpdate;

	    @FXML
	    private Button btnClasses;
	    @FXML
	    private Button commandes;
	    @FXML
	    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
	        if (mouseEvent.getSource() == btnDashboard) {
	            loadStage("/fxml/dashboard.fxml");
	        } else if (mouseEvent.getSource() == btnStudents) {
	            loadStage("/fxml/produits.fxml");
	        } else if (mouseEvent.getSource() == btn_Timetable) {
	            loadStage("/fxml/RegisterStaff.fxml");
	        }
	    }

	@FXML
	private Button btnlogout;


    @FXML
    void logout(ActionEvent event) {
		if (event.getSource() == btnlogout) {
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
    @FXML
    void commandes(ActionEvent event) {
		if (event.getSource() == commandes) {
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
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/VoirCommandes.fxml")));
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


	    @Override
	    public void initialize(URL location, ResourceBundle resources) {

	    }

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
	    
}