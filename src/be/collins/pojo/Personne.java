package be.collins.pojo;

import java.util.Date;

public abstract class Personne {
	protected int id;
	protected String nom;
	protected String prenom;
	protected int age;
	protected String email;
	protected String password;

	public Personne(int id, String nom, String prenom, int age, String email, String password) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.email = email;
		this.password = password;
	}

	public Personne(String nom, String prenom, int age, String email, String password) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.email = email;
		this.password = password;
	}

	public Personne() {

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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
