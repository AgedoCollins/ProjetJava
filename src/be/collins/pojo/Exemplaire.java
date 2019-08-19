package be.collins.pojo;

import java.util.List;

import be.collins.dao.AbstractDAOFactory;
import be.collins.dao.ExemplaireDAO;

public class Exemplaire {
	private int id;
	private int nbrExemplaire;
	private Jeu jeu;

	public Exemplaire() {

	}


	public Exemplaire(int nbrExemplaire, Jeu jeu) {
		this.nbrExemplaire = nbrExemplaire;
		this.jeu = jeu;
	}

	public Exemplaire(Jeu jeu) {
		this.jeu = jeu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbrExemplaire() {
		return nbrExemplaire;
	}

	public void setNbrExemplaire(int nbrExemplaire) {
		this.nbrExemplaire = nbrExemplaire;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	public void create_Exemplaire(Exemplaire exemplaire, Preteur preteur)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		ExemplaireDAO exemplaireDAO = adf.getExemplaireDAO();
		exemplaireDAO.create_Exemplaire(exemplaire, preteur);
	}
	
	public void update(Exemplaire exemplaire)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		ExemplaireDAO exemplaireDAO = adf.getExemplaireDAO();
		exemplaireDAO.update(exemplaire);
	}
	
	public List<Exemplaire> findAll(Preteur preteur)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		ExemplaireDAO exemplaireDAO = adf.getExemplaireDAO();
		List<Exemplaire> listExemplaire = exemplaireDAO.findAll(preteur);
		
		return listExemplaire;
	}
	
	public List<Exemplaire> findAll(Emprunteur emprunteur)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		ExemplaireDAO exemplaireDAO = adf.getExemplaireDAO();
		List<Exemplaire> listExemplaire = exemplaireDAO.findAll(emprunteur);
		
		return listExemplaire;
	}
	
	public Exemplaire findExemplaireByIdJeu(Jeu jeu)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		ExemplaireDAO exemplaireDAO = adf.getExemplaireDAO();
		List<Exemplaire> listExemplaire = exemplaireDAO.findAll();
		Exemplaire exemplaire = new Exemplaire();
		
		for(Exemplaire e : listExemplaire)
		{
			if(e.getJeu().getId() == jeu.getId())
			{
				exemplaire.setId(e.getId());
			}
		}
		
		return exemplaire;
	}

	public boolean isLastExemplaire(Jeu jeu, Preteur preteur)
	{
		
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		ExemplaireDAO exemplaireDAO = adf.getExemplaireDAO();
		boolean lastExemplaire = false;
		lastExemplaire = exemplaireDAO.isLastExemplaire(jeu);
		return lastExemplaire;
	}
	
	@Override
	public String toString() {
		return "Exemplaire [id=" + id + ", nbrExemplaire=" + nbrExemplaire + ", jeu=" + jeu + "]";
	}

}
