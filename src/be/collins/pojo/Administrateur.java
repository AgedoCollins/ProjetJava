package be.collins.pojo;

import java.util.*;

import be.collins.dao.AbstractDAOFactory;
import be.collins.dao.AdministrateurDAO;
import be.collins.dao.PreteurDAO;

public class Administrateur extends Personne {
	
	public Administrateur(int id, String nom, String prenom, int age, String email, String password) {
		super(id, nom, prenom,age, email, password);
	}

	public Administrateur(String nom, String prenom, int age, String email, String password) {
		super(nom, prenom, age, email, password);
	}

	public Administrateur() {
		super();
	}
	
	public void create(Administrateur administrateur)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		AdministrateurDAO administrateurDAO = adf.getAdministrateurDAO();
		administrateurDAO.create(administrateur);
	}
	
	public void update(Administrateur administrateur)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		AdministrateurDAO administrateurDAO = adf.getAdministrateurDAO();
		administrateurDAO.update(administrateur);
	}
	
	public void delete(Administrateur administrateur)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		AdministrateurDAO administrateurDAO = adf.getAdministrateurDAO();
		administrateurDAO.delete(administrateur);
	}
	
	public List<Administrateur> findAll()
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		AdministrateurDAO administrateurDAO = adf.getAdministrateurDAO();	
		List<Administrateur> listAdministrateurs = administrateurDAO.findAll();	
		return listAdministrateurs;
	}
	
	public boolean connexionAdministrateur(String email, String password) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		AdministrateurDAO administrateurDAO = adf.getAdministrateurDAO();	
		List<Administrateur> listAdministrateurs = administrateurDAO.findAll();	
		boolean is_existe = false;		
		
		for(Administrateur a : listAdministrateurs)
		{
			if(a.getEmail().toLowerCase().equals(email.toLowerCase()) && a.getPassword().toLowerCase().equals(password.toLowerCase()))
			{
				is_existe = true;
			}
		}
	
		return is_existe;
	}
	
	public Administrateur findAdministrateurByEmailPassword(String email, String password) {
		Administrateur administrateur = new Administrateur();
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		AdministrateurDAO administrateurDAO = adf.getAdministrateurDAO();
		List<Administrateur> listAdministrateurs = administrateurDAO.findAll();
		
		for(Administrateur admin : listAdministrateurs)
		{
			if(admin.getEmail().equals(email) && admin.getPassword().equals(password))
			{
				administrateur = new Administrateur(admin.getId(), admin.getNom(),
						admin.getPrenom(), admin.getAge(), email, password);
			}
		}
		return administrateur;
	}
	
	public boolean alreadyExist(Administrateur administrateur) {
		boolean existe = false;
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		AdministrateurDAO administrateurDAO = adf.getAdministrateurDAO();
		List<Administrateur> listAdministrateurs = administrateurDAO.findAll();
		
		for(Administrateur admin : listAdministrateurs)
		{
			if(admin.getId() > 0)
			{
				if(admin.getEmail().equals(administrateur.getEmail()) && admin.getId() != administrateur.getId())
				{
					existe = true;
				}
			}
			else
			{
				if(admin.getEmail().equals(administrateur.getEmail()))
				{
					existe = true;
				}
			}
		}
		
		return existe;
	}

	@Override
	public String toString() {
		return "Administrateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", email=" + email
				+ ", password=" + password + "]";
	}
}
