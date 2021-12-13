package models;

public class produit {
	 int id; 
	 String nom; 
	 int qte; 
	 String cat;
	 String prix;
	
	
	

	public produit(int id, String nom, int qte, String cat, String prix) {
		this.id = id;
		this.nom = nom;
		this.qte = qte;
		this.cat = cat;
		this.prix = prix;
		
	}



	public String getPrix() {
		return prix;
	}



	public void setPrix(String prix) {
		this.prix = prix;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	public String getCat() {
		return cat;
	}
	public void setCat(String categorie) {
		this.cat = categorie;
	} 
	public String toString() {
	        return "id " + this.id+
	                " : nom " + this.nom+ "cat"+ this.cat; 
	    }
	 
}
