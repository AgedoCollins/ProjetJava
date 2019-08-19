package be.collins.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import be.collins.pojo.Emprunteur;


public class EmprunteurDAO extends DAO<Emprunteur> {

	public EmprunteurDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Emprunteur emprunteur) {
		java.util.Date currentDate = new java.util.Date();

		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Emprunteur (Nom, Prenom, Age,  Email, Password, Unite, Date_en) VALUES ('"
					+ emprunteur.getNom() + "','" + emprunteur.getPrenom() + "','" + emprunteur.getAge()
					+ "','" + emprunteur.getEmail() + "','" + emprunteur.getPassword() + "','" + emprunteur.getUnite()
					+ "','" + new Timestamp(currentDate.getTime()) + "')" + ";";
			System.out.println(query);
			statementResult = true;
			statementResult = statement.execute(query);
		} catch (SQLException e) {
			statementResult = false;
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println(statementResult);
		return statementResult;
	}

	@Override
	public boolean delete(Emprunteur emprunteur) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "DELETE FROM Emprunteur WHERE ID = " + emprunteur.getId() + ";";
			System.out.println(query);
			statementResult = true;
			statementResult = statement.execute(query);
		} catch (SQLException e) {
			statementResult = false;
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println(statementResult);
		return statementResult;
	}

	@Override
	public boolean update(Emprunteur emprunteur) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Emprunteur SET Nom = " + "'" + emprunteur.getNom() + "', " + "Prenom = " + "'"
					+ emprunteur.getPrenom() + "', " + "Age = " + "'"
					+ emprunteur.getAge() + "', " + "Email = " + "'"
					+ emprunteur.getEmail() + "', " + "Password = " + "'" + emprunteur.getPassword() + "'"
					+ " WHERE ID = " + emprunteur.getId() + ";";
			System.out.println(query);
			statementResult = true;
			statementResult = statement.execute(query);
		} catch (SQLException e) {
			statementResult = false;
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println(statementResult);
		return statementResult;
	}
	
	public boolean updateUnite(Emprunteur emprunteur) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Emprunteur SET Unite = " + emprunteur.getUnite() + " WHERE ID = "
					+ emprunteur.getId() + ";";
			System.out.println(query);
			statementResult = true;
			statementResult = statement.execute(query);
		} catch (SQLException e) {
			statementResult = false;
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println(statementResult);
		return statementResult;
	}

	@Override
	public Emprunteur find(int id) {
		Emprunteur emprunteur = new Emprunteur();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Emprunteur WHERE ID = " + id);
			if (result.first())
				emprunteur = new Emprunteur(result.getInt("ID"), result.getString("Nom"), result.getString("Prenom"),
						result.getInt("Age"), result.getString("Email"), result.getString("Password"),
						result.getDate("Date_en"), result.getInt("Unite"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emprunteur;

	}

	public List<Emprunteur> findAll() {
		List<Emprunteur> listEmprunteurs = new ArrayList<>();
		Emprunteur emprunteur = new Emprunteur();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Emprunteur");
			while (result.next()) {
				emprunteur = new Emprunteur(result.getInt("ID"), result.getString("Nom"), result.getString("Prenom"),
						result.getInt("Age"), result.getString("Email"), result.getString("Password"),
						result.getInt("Unite"));
				listEmprunteurs.add(emprunteur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listEmprunteurs;
	}
	
	public List<Emprunteur> findAllExceptcurrentEmprunteur(Emprunteur emprunteur) {
		List<Emprunteur> listEmprunteurs = new ArrayList<>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Emprunteur WHERE ID <> " + emprunteur.getId());
			while(result.next())
			{
				emprunteur = new Emprunteur(result.getInt("ID"), result.getString("Nom"), result.getString("Prenom"),
						result.getInt("Age"), result.getString("Email"), result.getString("Password"), result.getInt("Unite"));
				listEmprunteurs.add(emprunteur);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return listEmprunteurs;
	}
}
