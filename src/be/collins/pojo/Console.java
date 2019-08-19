package be.collins.pojo;

import java.util.List;

import be.collins.dao.AbstractDAOFactory;
import be.collins.dao.ConsoleDAO;

public class Console {
	private int id;
	private String nom;

	public Console() {

	}

	public Console(String nom) {
		this.nom = nom;
	}

	public Console(int id, String nom) {
		this.id = id;
		this.nom = nom;
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

	public void create(Console Console)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		ConsoleDAO consoleDAO = adf.getConsoleDAO();
		consoleDAO.create(Console);
	}
	
	public void update(Console Console)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		ConsoleDAO consoleDAO = adf.getConsoleDAO();
		consoleDAO.update(Console);
	}
	
	public void delete(Console console)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		ConsoleDAO consoleDAO = adf.getConsoleDAO();
		consoleDAO.delete(console);
	}
	
	public List<Console> findAll()
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		ConsoleDAO consoleDAO = adf.getConsoleDAO();
		List<Console> listConsoles = consoleDAO.findAll();
		
		return listConsoles;
	}
	
	public boolean alreadyExist(Console console) {
		boolean existe = false;
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		ConsoleDAO consoleDAO = adf.getConsoleDAO();
		List<Console> listConsoles = consoleDAO.findAll();
		
		for(Console c : listConsoles)
		{
			if(c.getId() > 0)
			{
				if(c.getNom().equals(console.getNom()) && c.getId() != console.getId())
				{
					existe = true;
				}
			}
			else
			{
				if(c.getNom().equals(console.getNom()))
				{
					existe = true;
				}
			}
		}
		
		return existe;
	}
	
	@Override
	public String toString() {
		return "Console [nom=" + nom + "]";
	}

}
