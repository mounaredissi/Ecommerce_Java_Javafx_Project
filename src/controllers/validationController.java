package controllers;


import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class validationController {

    public static boolean validateEmpty(TextField txt) {
        if (txt.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ajout produit ");
            alert.setHeaderText(null);
            alert.setContentText("rempli tous les boxs..!");
            alert.showAndWait();

            return false;
        }
        return true;
    }

    public boolean validateNIC(TextField txt) {
        if (txt.getText().matches("^(\\d{9}|\\d{12})[VvXx]$")|| (txt.getText().isEmpty())) {

            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("produit Registration");
            alert.setHeaderText(null);
            alert.setContentText("Invalid  Number..!");
            alert.showAndWait();

            return false;
        }
    }

    public boolean numbersOnly(TextField txt) {
        if (txt.getText().matches("[0-9]+")) {

            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("produit Registration");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Input Type..!");
            alert.showAndWait();

            return false;
        }
    }

    public boolean validatePhone(TextField txt) {
        if (txt.getText().matches("^(\\d{10})")) {

            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("produit Registration");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Phone Number..!");
            alert.showAndWait();

            return false;
        }
    }

    public boolean validateDate(TextField txt) {
        if (txt.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {

            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("produit Registration");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Date..!");
            alert.showAndWait();

            return false;
        }
    }
}
