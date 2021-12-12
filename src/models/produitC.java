package models;

public class produitC {
	int id ; 
	String nom ; 
	String qte;
	String cat;
	public produitC(int id, String nom, String qte, String cat) {
		super();
		this.id = id;
		this.nom = nom;
		this.qte = qte;
		this.cat = cat;
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
	public String getQte() {
		return qte;
	}
	public void setQte(String qte) {
		this.qte = qte;
	}
	public String getCat() {
		return cat;
	}
	public void setCat(String cat) {
		this.cat = cat;
	}

	
	

}
