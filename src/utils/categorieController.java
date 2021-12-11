package utils;
import java.sql.PreparedStatement;
import java.sql.*;
import models.categorie;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class categorieController {

	public static ArrayList<String> getCat() throws ClassNotFoundException, SQLException{
        Connection con = ConnectionUtil.conDB();
        Statement stm=con.createStatement();
        ResultSet rst=stm.executeQuery("Select categorie From categories");
        ArrayList<String>categorieList=new ArrayList<>();
        while(rst.next()){
            
        	categorieList.add(rst.getString("categorie"));
        }
        return categorieList;}

	
}
