package be.collins.dao;

public abstract class AbstractDAOFactory {
	
public static  final int DAO_FACTORY = 0;
	
	public abstract AdministrateurDAO getAdministrateurDAO();
	
	public abstract ConsoleDAO getConsoleDAO();
	
	public abstract EmprunteurDAO getEmprunteurDAO();
	
	public abstract PreteurDAO getPreteurDAO();
	
	public abstract ExemplaireDAO getExemplaireDAO();
	
	public abstract JeuDAO getJeuDAO();
	
	public abstract JoueurDAO getJoueurDAO();
	
	public abstract PersonneDAO getPersonneDAO();
	
	public abstract PretDAO getPretDAO();
	
	public abstract ReservationDAO getReservationDAO();
	
	public static AbstractDAOFactory getFactory(int type)
	{
		switch(type)
		{
			case DAO_FACTORY:
				return new DAOFactory();
			default:
				return null;
		}
	}
}
