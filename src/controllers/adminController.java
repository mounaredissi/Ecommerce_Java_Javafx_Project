package controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
	    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
	        if (mouseEvent.getSource() == btnDashboard) {
	            loadStage("/fxml/dashboard.fxml");
	        } else if (mouseEvent.getSource() == btnStudents) {
	            loadStage("/fxml/produits.fxml");
	        } else if (mouseEvent.getSource() == btn_Timetable) {
	            loadStage("/fxml/RegisterStaff.fxml");
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