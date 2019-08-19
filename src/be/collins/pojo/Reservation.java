package be.collins.pojo;

import java.util.Date;
import java.util.List;

import be.collins.dao.AbstractDAOFactory;
import be.collins.dao.ReservationDAO;

public class Reservation  {
	//Variable
	private int id;
	private Date dateReservation;
	private Jeu jeu;
	
	//Constructeur
	public Reservation(int id, Date dateReservation, Jeu jeu) {
		this.id = id;
		this.dateReservation = dateReservation;
		this.jeu = jeu;
	}

	public Reservation(int id, Date dateReservation) {
		this.id = id;
		this.dateReservation = dateReservation;
	}

	public Reservation(Date dateReservation, Jeu jeu) {
		this.dateReservation = dateReservation;
		this.jeu = jeu;
	}

	public Reservation() {}

	//Getter & Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	//Méthode
	public void createReservation(Reservation reservation, Emprunteur emprunteur)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		ReservationDAO reservationDAO = adf.getReservationDAO();
		reservationDAO.createReservation(reservation, emprunteur);
	}
	
	public void create_Ligne_Reservation(Reservation reservation, Jeu jeu)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		ReservationDAO reservationDAO = adf.getReservationDAO();
		reservationDAO.create_Ligne_Reservation(reservation, jeu);
	}
	
	public int RecoverLastId()
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		ReservationDAO reservationDAO = adf.getReservationDAO();
		List<Reservation> listReservations = reservationDAO.findAll();
		int lastID = 0;
		Reservation reservation = listReservations.get(listReservations.size()-1);
		lastID = reservation.getId();
		
		return lastID;
	}
}
