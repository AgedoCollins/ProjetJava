package be.collins.pojo;

import java.util.Date;

public abstract class Joueur extends Personne {
	//Variable
	private Date date_en;

	//Constructeur
	public Joueur(Date date_en) {
		this.date_en = date_en;
	}

	public Joueur(int id, String nom, String prenom, int age, String email, String password, Date date_en) {
		super(id, nom, prenom, age, email, password);
		this.date_en = date_en;
	}

	public Joueur(int id, String nom, String prenom, int age, String email, String password) {
		super(id, nom, prenom, age, email, password);
	}

	public Joueur(String nom, String prenom, int age, String email, String password) {
		super(nom, prenom, age, email, password);
	}

	public Joueur() {
		super();
	}
	
	//Getter & Setter
	public Date getDate_en() {
		return date_en;
	}

	public void setDate_en(Date date_en) {
		this.date_en = date_en;
	}

	//Methode

}
