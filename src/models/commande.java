package models;

public class commande {
 int idCommande ; 
 String date; 
 int idClient; 
 String etat; 
 public int getIdCommande() {
	return idCommande;
}
public void setIdCommande(int idCommande) {
	this.idCommande = idCommande;
}
public commande(int idCommande, String date, int idClient, String etat) {
	
	this.idCommande = idCommande;
	this.date = date;
	this.idClient = idClient;
	this.etat = etat;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public int getIdClient() {
	return idClient;
}
public void setIdClient(int idClient) {
	this.idClient = idClient;
}
public String getEtat() {
	return etat;
}
public void setEtat(String etat) {
	this.etat = etat;
}

}
