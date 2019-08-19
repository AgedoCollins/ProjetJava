package be.collins.pojo;

import java.util.*;

import be.collins.dao.*;

public class Preteur extends Joueur {
	//Variable
	private List<Exemplaire> listExemplaire = new ArrayList<>();

	//Constructeur
	public Preteur(List<Exemplaire> listExemplaire) {
		this.listExemplaire = listExemplaire;
	}

	public Preteur(int id, String nom, String prenom, int age, String email, String password) {
		super(id, nom, prenom, age, email, password);
	}
	public Preteur(String nom, String prenom, int age, String email, String password) {
		super(nom, prenom, age, email, password);
	}

	public Preteur() {}

	//Getter & Setter
	public List<Exemplaire> getListExemplaire() {
		return listExemplaire;
	}

	public void setListExamplaire(List<Exemplaire> listExemplaire) {
		this.listExemplaire = listExemplaire;
	}
	//Méthode de liste
	public void AjouterExemplaire(Exemplaire exemplaire) {
		listExemplaire.add(exemplaire);
	}

	public void SupprimerExemplaire(Exemplaire exemplaire) {
		listExemplaire.remove(exemplaire);
	}

	//Méthode
	public void create(Preteur preteur)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		PreteurDAO preteurDAO = adf.getPreteurDAO();
		preteurDAO.create(preteur);
	}
	
	public void update(Preteur preteur)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		PreteurDAO preteurDAO = adf.getPreteurDAO();
		preteurDAO.update(preteur);
	}
	
	public Preteur find(int id)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		PreteurDAO preteurDAO = adf.getPreteurDAO();
		Preteur preteur = preteurDAO.find(id);
		
		return preteur;
	}
	
	public List<Preteur> findAll()
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		PreteurDAO preteurDAO = adf.getPreteurDAO();
		List<Preteur> listPreteurs = preteurDAO.findAll();
		
		for(Preteur p : listPreteurs)
		{
			this.setNom(p.getNom());
			this.setPrenom(p.getPrenom());
			this.setAge(p.getAge());
			this.setEmail(p.getEmail());
		}
				
		return listPreteurs;
	}
	
	public boolean connexionPreteur(String email, String password) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		PreteurDAO preteurDAO = adf.getPreteurDAO();
		List<Preteur> listPreteurs = preteurDAO.findAll();
		boolean is_existe = false;		
		
		for(Preteur p : listPreteurs)
		{
			if(p.getEmail().toLowerCase().equals(email.toLowerCase()) && p.getPassword().toLowerCase().equals(password.toLowerCase()))
			{
				is_existe = true;
			}
		}
	
		return is_existe;
	}
	
	public Preteur findPreteurByEmailPassword(String email, String password) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		PreteurDAO preteurDAO = adf.getPreteurDAO();
		List<Preteur> listPreteurs = preteurDAO.findAll();
		Preteur preteur = new Preteur();

		for(Preteur p : listPreteurs)
		{
			if(p.getEmail().toLowerCase().equals(email.toLowerCase()) && p.getPassword().toLowerCase().equals(password.toLowerCase()))
			{
				preteur = new Preteur(p.getId(), p.getNom(),
						p.getPrenom(), p.getAge(), email, password);
			}
		}
		return preteur;
	}
	
	public List<Preteur> findAllExceptcurrentPreteur(Preteur preteur)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		PreteurDAO preteurDAO = adf.getPreteurDAO();
		List<Preteur> listPreteurs = preteurDAO.findAllExceptcurrentPreteur(preteur);
		
		
		return listPreteurs;
	}
	
	public boolean isExistPreteur(Preteur preteur) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		PreteurDAO preteurDAO = adf.getPreteurDAO();
		List<Preteur> listPreteurs = preteurDAO.findAll();
		boolean is_existe = false;

		for(Preteur p : listPreteurs)
		{
			if(p.getEmail().toLowerCase().equals(preteur.getEmail().toLowerCase()))
			{
				is_existe = true;
			}
		}
		
		return is_existe;
	}
}
