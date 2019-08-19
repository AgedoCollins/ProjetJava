package be.collins.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import be.collins.dao.AbstractDAOFactory;
import be.collins.dao.PretDAO;

public class Pret {
	//Variable
	private int id;
	private Date dateDebut;
	private Date dateFin;
	private boolean confirmer_pret;
	private Exemplaire exemplaire;
	private Emprunteur emprunteur;
	private Preteur preteur;

	//Constructeur
	public Pret(int id, Date dateDebut, Date dateFin, boolean confirmer_pret, Exemplaire exemplaire,
			Emprunteur emprunteur, Preteur preteur) {
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.confirmer_pret = confirmer_pret;
		this.exemplaire = exemplaire;
		this.emprunteur = emprunteur;
		this.preteur = preteur;

	}

	public Pret(Date dateDebut, Date dateFin, boolean confirmer_pret, Exemplaire exemplaire, Emprunteur emprunteur,
			Preteur preteur) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.confirmer_pret = confirmer_pret;
		this.exemplaire = exemplaire;
		this.emprunteur = emprunteur;
		this.preteur = preteur;

	}

	public Pret(int id, Date dateDebut, Date dateFin, Emprunteur emprunteur) {
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.emprunteur = emprunteur;

	}

	public Pret(Date dateDebut, Date dateFin, Emprunteur emprunteur) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.emprunteur = emprunteur;

	}

	public Pret() {}

	//Getter & Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public boolean isConfirmer_pret() {
		return confirmer_pret;
	}

	public void setConfirmer_pret(boolean confirmer_pret) {
		this.confirmer_pret = confirmer_pret;
	}

	public Exemplaire getExemplaire() {
		return exemplaire;
	}

	public void setExemplaire(Exemplaire exemplaire) {
		this.exemplaire = exemplaire;
	}

	public Emprunteur getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(Emprunteur emprunteur) {
		this.emprunteur = emprunteur;
	}

	public Preteur getPreteur() {
		return preteur;
	}

	public void setPreteur(Preteur preteur) {
		this.preteur = preteur;
	}

	//Methode
	public void create(Pret pret, Emprunteur emprunteur, Exemplaire exemplaire, Reservation reservation)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		PretDAO pretDAO = adf.getPretDAO();
		pretDAO.create_Pret(pret, emprunteur, reservation, exemplaire);
	}
	
	public void updatePretEmprunteur(Emprunteur emprunteur, Pret pret)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		PretDAO pretDAO = adf.getPretDAO();
		pretDAO.update_Pret_Emprunteur(emprunteur, pret);
	}
	
	public void updateConfirmPret(Pret pret)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		PretDAO pretDAO = adf.getPretDAO();
		pretDAO.update_Confirmation(pret);
	}
	
	public void updatePretOfPreteur(Preteur preteur, Pret pret)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		PretDAO pretDAO = adf.getPretDAO();
		pretDAO.update_Pret_Preteur(preteur, pret);
	}
	
	
	public void delete(Pret pret)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		PretDAO pretDAO = adf.getPretDAO();
		pretDAO.delete(pret);
	}
	
	public List<Pret> findAll(Emprunteur emprunteur)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		PretDAO pretDAO = adf.getPretDAO();
		List<Pret> listPrets = pretDAO.findAll();
		List<Pret> listPretsCorrecte= new ArrayList<>();
		
		for(Pret pret : listPrets)
		{
			if(pret.getEmprunteur().getEmail() != emprunteur.getEmail())
			{
				listPretsCorrecte.add(pret);
			}
			
		}
		
		return listPretsCorrecte;
	}
	
	public List<Pret> findAllPretByEmprunteur(Emprunteur emprunteur)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		PretDAO pretDAO = adf.getPretDAO();
		List<Pret> listPrets = pretDAO.findAllPretByEmprunteur(emprunteur);
		
		return listPrets;
	}
	
	public boolean is_confirmed(Pret pret)
	{
		boolean is_confirmed = false;
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		PretDAO pretDAO = adf.getPretDAO();
		is_confirmed = pretDAO.isAlreadyConfirmed(pret);
		
		return is_confirmed;
	}
	
	public boolean sameReservationFound(Exemplaire exemplaire)
	{
		boolean reservationFound = false;
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		PretDAO pretDAO = adf.getPretDAO();
		reservationFound = pretDAO.sameReservationFound(exemplaire);
		
		return reservationFound;
	}
	
	public List<Pret> getPretEmprunteurSortByPriorites(Exemplaire exemplaire)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		PretDAO pretDAO = adf.getPretDAO();
		List<Pret> listPret = pretDAO.getPretEmprunteurSortByPriorites(exemplaire);
		
		return listPret;
	}

}
