package be.collins.dao;

import java.sql.Connection;

public class DAOFactory extends AbstractDAOFactory {
	
	protected static final Connection conn = ConnexionDB.getInstance();

	@Override
	public AdministrateurDAO getAdministrateurDAO() {
		// TODO Auto-generated method stub
		return new AdministrateurDAO(conn);
	}
	@Override
	public ConsoleDAO getConsoleDAO() {
		// TODO Auto-generated method stub
		return new ConsoleDAO(conn);
	}
	
	@Override
	public EmprunteurDAO getEmprunteurDAO() {
		// TODO Auto-generated method stub
		return new EmprunteurDAO(conn);
	}
	
	@Override
	public PreteurDAO getPreteurDAO() {
		// TODO Auto-generated method stub
		return new PreteurDAO(conn);
	}
	
	@Override
	public ExemplaireDAO getExemplaireDAO() {
		// TODO Auto-generated method stub
		return new ExemplaireDAO(conn);
	}
	
	@Override
	public JeuDAO getJeuDAO() {
		// TODO Auto-generated method stub
		return new JeuDAO(conn);
	}
	
	@Override
	public JoueurDAO getJoueurDAO() {
		// TODO Auto-generated method stub
		return new JoueurDAO(conn);
	}
	
	@Override
	public PersonneDAO getPersonneDAO() {
		// TODO Auto-generated method stub
		return new PersonneDAO(conn);
	}
	
	@Override
	public PretDAO getPretDAO() {
		// TODO Auto-generated method stub
		return new PretDAO(conn);
	}
	
	@Override
	public ReservationDAO getReservationDAO() {
		// TODO Auto-generated method stub
		return new ReservationDAO(conn);
	}
}
