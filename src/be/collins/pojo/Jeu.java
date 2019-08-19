package be.collins.pojo;

import java.util.Date;
import java.util.List;

import be.collins.dao.AbstractDAOFactory;
import be.collins.dao.ConsoleDAO;
import be.collins.dao.JeuDAO;

public class Jeu {
	private int id;
	private String nom;
	private boolean dispo;
	private double tarif;
	private Date dateTarif;
	private Console console;

	public Jeu(int id, String nom, boolean dispo, double tarif, Date dateTarif, Console console) {
		this.id = id;
		this.nom = nom;
		this.dispo = dispo;
		this.tarif = tarif;
		this.dateTarif = dateTarif;
		this.console = console;
	}

	public Jeu(int id, String nom, boolean dispo, double tarif, Date dateTarif) {
		this.id = id;
		this.nom = nom;
		this.dispo = dispo;
		this.tarif = tarif;
		this.dateTarif = dateTarif;
	}

	public Jeu(String nom, boolean dispo, double tarif, Date dateTarif, Console console) {
		this.nom = nom;
		this.dispo = dispo;
		this.tarif = tarif;
		this.dateTarif = dateTarif;
		this.console = console;
	}

	public Jeu(String nom, boolean dispo, double tarif, Date dateTarif) {
		this.nom = nom;
		this.dispo = dispo;
		this.tarif = tarif;
		this.dateTarif = dateTarif;
	}

	public Jeu() {

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

	public boolean isDispo() {
		return dispo;
	}

	public void setDispo(boolean dispo) {
		this.dispo = dispo;
	}

	public double getTarif() {
		return tarif;
	}

	public void setTarif(double tarif) {
		this.tarif = tarif;
	}

	public Date getDateTarif() {
		return dateTarif;
	}

	public void setDateTarif(Date dateTarif) {
		this.dateTarif = dateTarif;
	}

	public Console getConsole() {
		return console;
	}

	public void setConsole(Console console) {
		this.console = console;
	}

	public void create(Jeu jeu, Administrateur administrateur) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		JeuDAO jeuDAO = adf.getJeuDAO();
		jeuDAO.create(jeu, administrateur);
	}

	public void create_Ligne_Jeu(Jeu jeu, Administrateur administrateur) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		JeuDAO jeuDAO = adf.getJeuDAO();
		jeuDAO.create_Ligne_Jeu(jeu);
	}

	public void update(Jeu jeu) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		JeuDAO jeuDAO = adf.getJeuDAO();
		jeuDAO.update(jeu);
	}

	public void update_Dispo(Jeu jeu) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		JeuDAO jeuDAO = adf.getJeuDAO();
		jeuDAO.update_Dispo(jeu);
	}

	public void update_Dispo_False(Jeu jeu) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		JeuDAO jeuDAO = adf.getJeuDAO();
		jeuDAO.update_Dispo_False(jeu);
	}

	public void delete(Jeu jeu) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		JeuDAO jeuDAO = adf.getJeuDAO();
		jeuDAO.delete(jeu);
	}

	public List<Jeu> findAll() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		JeuDAO jeuDAO = adf.getJeuDAO();
		List<Jeu> listJeux = jeuDAO.findAll();

		return listJeux;
	}

	public int findLastIdJeu() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		JeuDAO jeuDAO = adf.getJeuDAO();
		List<Jeu> listJeux = jeuDAO.findAllJeu();
		int lastID = 0;

		for (Jeu jeu : listJeux) {
			jeu = listJeux.get(listJeux.size() - 1);
			lastID = jeu.getId();
		}

		return lastID;
	}

	public boolean alreadyExist(Jeu jeu) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		JeuDAO jeuDAO = adf.getJeuDAO();
		ConsoleDAO consoleDAO = adf.getConsoleDAO();
		List<Jeu> listJeux = jeuDAO.findAll();
		List<Console> listConsoles = consoleDAO.findAll();
		boolean existe = false;

		for (Jeu j : listJeux) {
			for (Console c : listConsoles) {
				if (j.getId() > 0) {
					if (j.getNom().equals(jeu.getNom()) && c.getNom().equals(j.getConsole().getNom())
							&& j.getId() != jeu.getId()) {
						existe = true;
					}
				} else {
					if (j.getNom().equals(jeu.getNom()) && c.getNom().equals(j.getConsole().getNom())) {
						existe = true;
					}
				}
			}
		}

		return existe;
	}

	@Override
	public String toString() {
		return "Jeu [id=" + id + ", nom=" + nom + ", dispo=" + dispo + ", tarif=" + tarif + ", dateTarif=" + dateTarif
				+ ", console=" + console + "]";
	}

}
