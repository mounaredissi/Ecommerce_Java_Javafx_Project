package utils;
import javafx.scene.control.Alert;
import models.produit;
import utils.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;

public class produitsController {
	 public static int Addproduit(produit produit)throws ClassNotFoundException,SQLException {
	        String SQL="INSERT INTO produits VALUES(?,?,?,?)";
	        Connection con = ConnectionUtil.conDB();
	        PreparedStatement stm = con.prepareStatement(SQL);
	        stm.setObject(1, produit.getId());
	        stm.setObject(2, produit.getNom());
	        stm.setObject(3, produit.getQte());
	        stm.setObject(4, produit.getCat());
	        

	        return  stm.executeUpdate();
	    }
	    public static produit searchproduit(Integer adNo) throws ClassNotFoundException, SQLException {
	        String sql = "SELECT * FROM produits WHERE adNo = ? ";
	        Connection con = ConnectionUtil.conDB();
	        PreparedStatement stm = con.prepareStatement(sql);
	        stm.setObject(1, adNo);
	        ResultSet rst=stm.executeQuery();
	        if(rst.next()){
	            produit s= new produit (rst.getInt(1),rst.getString(2),rst.getInt(3), rst.getString(4));
	            return s;
	        }
	        return null;
	    }

	  

	    public static int deleteproduit(String adNo) throws ClassNotFoundException, SQLException {

	        String sql = "DELETE FROM produits WHERE nom ='"+adNo+"'";
	        Connection con = ConnectionUtil.conDB();
	        PreparedStatement stm = con.prepareStatement(sql);


	        return  stm.executeUpdate();
	    }

	    

	    

	    public static int updateproduit(produit produit) throws ClassNotFoundException, SQLException {
	        String sql = "UPDATE produits SET id= ? ,nom= ? ,qte= ?  WHERE id= '" +produit.getId()+ "'";
	        Connection con = ConnectionUtil.conDB();
	        PreparedStatement stm = con.prepareStatement(sql);
	        stm.setObject(1, produit.getId());
	        stm.setObject(2, produit.getNom());
	        stm.setObject(3, produit.getQte());
	        stm.setObject(4, produit.getCat());

	        return  stm.executeUpdate();
	    }

	    public static int updateLeftproduit(produit produit) throws ClassNotFoundException, SQLException {
	        String sql = "UPDATE produits SET id= ? ,nom= ? ,qte= ? ,cat= ?  WHERE id= '" +produit.getId()+ "'";
	        Connection con = ConnectionUtil.conDB();
	        PreparedStatement stm = con.prepareStatement(sql);
	        stm.setObject(1, produit.getId());
	        stm.setObject(2, produit.getNom());
	        stm.setObject(3, produit.getQte());
	        stm.setObject(4, produit.getCat());

	        return  stm.executeUpdate();
	    }

	
}
