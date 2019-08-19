package be.collins.pojo;

import java.util.*;

import be.collins.dao.*;

public class Emprunteur extends Joueur {
	private int unite;
	private Reservation reservation;

	public Emprunteur(int cote) {
		this.unite = 10;
	}

	public Emprunteur(int id, String nom, String prenom, int age, String email, String password) {
		super(id, nom, prenom, age, email, password);
		this.unite = 10;
	}

	public Emprunteur(String nom, String prenom, int age, String email, String password) {
		super(nom, prenom, age, email, password);
		this.unite = 10;
	}

	public Emprunteur(int id, String nom, String prenom, int age, String email, String password, int unite, Reservation reservation) {
		super(id, nom, prenom, age, email, password);
		this.unite = unite;
		this.reservation = reservation;
	}
	
	public Emprunteur(int id, String nom, String prenom, int age, String email, String password, int unite) {
		super(id, nom, prenom, age, email, password);
		this.unite = unite;
	}

	public Emprunteur(int id, String nom, String prenom, int age, String email, String password,
			Date date_en, int unite) {
		super(id, nom, prenom, age, email, password);
		this.unite = unite;
	}

	public Emprunteur(String nom, String prenom, int age, String email, String password, int unite) {
		super(nom, prenom, age, email, password);
		this.unite = unite;
	}

	
	public Emprunteur() {
		super();
		this.unite = 10;
	}

	public int getUnite() {
		return unite;
	}

	public void setUnite(int unite) {
		this.unite = unite;
	}

	
	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public void ajouterUnite(int unite) {
		
		this.unite += unite;
	}
	
	public void soustraireUnite(int unite) {
		this.unite -= unite;
	}
	
	public void create(Emprunteur emprunteur)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		EmprunteurDAO emprunteurDAO = adf.getEmprunteurDAO();
		emprunteurDAO.create(emprunteur);
	}

	public void update(Emprunteur emprunteur)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		EmprunteurDAO emprunteurDAO = adf.getEmprunteurDAO();
		emprunteurDAO.update(emprunteur);
	}
	
	public void updateUnite(Emprunteur emprunteur)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		EmprunteurDAO emprunteurDAO = adf.getEmprunteurDAO();
		emprunteurDAO.updateUnite(emprunteur);
	}
	
	public List<Emprunteur> findAll()
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		EmprunteurDAO emprunteurDAO = adf.getEmprunteurDAO();
		List<Emprunteur> listEmprunteurs = emprunteurDAO.findAll();		
		return listEmprunteurs;
	}
	
	public boolean connexionEmprunteur(String email, String password) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		EmprunteurDAO emprunteurDAO = adf.getEmprunteurDAO();
		List<Emprunteur> listEmprunteurs = emprunteurDAO.findAll();
		boolean is_existe = false;		
		
		for(Emprunteur e : listEmprunteurs)
		{
			if(e.getEmail().toLowerCase().equals(email.toLowerCase()) && e.getPassword().toLowerCase().equals(password.toLowerCase()))
			{
				is_existe = true;
			}
		}
	
		return is_existe;
	}
	
	public Emprunteur findEmprunteurById(Emprunteur emprunteur) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		DAO<Emprunteur> emprunteurDAO = adf.getEmprunteurDAO();
		Emprunteur e = emprunteurDAO.find(emprunteur.getId());
		
		return e;
	}
	
	public Emprunteur findIdByEmprunteurEmail(Emprunteur emprunteur) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		EmprunteurDAO emprunteurDAO = adf.getEmprunteurDAO();
		List<Emprunteur> listEmprunteurs = emprunteurDAO.findAll();
		
		for(Emprunteur e : listEmprunteurs)
		{
			if(e.getEmail().equals(emprunteur.getEmail()))
			{
				emprunteur = new Emprunteur(e.getId(), e.getNom(),
						e.getPrenom(), e.getAge(), emprunteur.getEmail(), emprunteur.getPassword());
			}
		}
		
		return emprunteur;
	}
	
	
	public Emprunteur findEmprunteurByEmailPassword(String email, String password) {
		Emprunteur emprunteur = new Emprunteur();
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		EmprunteurDAO emprunteurDAO = adf.getEmprunteurDAO();
		List<Emprunteur> listEmprunteurs = emprunteurDAO.findAll();
		
		for(Emprunteur e : listEmprunteurs)
		{
			if(e.getEmail().equals(email) && e.getPassword().equals(password))
			{
				emprunteur = new Emprunteur(e.getId(), e.getNom(),
						e.getPrenom(), e.getAge(), email, password);
			}
		}
		return emprunteur;
	}

	public List<Emprunteur> findAllExceptcurrentEmprunteur(Emprunteur emprunteur)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		EmprunteurDAO emprunteurDAO = adf.getEmprunteurDAO();
		List<Emprunteur> listEmprunteurs = emprunteurDAO.findAllExceptcurrentEmprunteur(emprunteur);
		
		return listEmprunteurs;
	}
	
	public boolean isExistEmprunteur(Emprunteur emprunteur) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		EmprunteurDAO emprunteurDAO = adf.getEmprunteurDAO();
		List<Emprunteur> listEmprunteurs = emprunteurDAO.findAll();
		boolean is_existe = false;

		for(Emprunteur e : listEmprunteurs)
		{
			if(e.getEmail().toLowerCase().equals(emprunteur.getEmail().toLowerCase()))
			{
				is_existe = true;
			}
		}
		
		return is_existe;
	}

}
