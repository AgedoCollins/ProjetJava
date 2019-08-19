package be.collins.dao;

import java.sql.*;
import java.util.*;

import be.collins.pojo.*;

public class PreteurDAO extends DAO<Preteur> {

	public PreteurDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Preteur preteur) {
		java.util.Date currentDate = new java.util.Date();

		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Preteur (Nom, Prenom, Age,  Email, Password, Date_en) VALUES ('"
					+ preteur.getNom() + "','" + preteur.getPrenom() + "','" + preteur.getAge() + "','"
					+ preteur.getEmail() + "','" + preteur.getPassword() + "','" + new Timestamp(currentDate.getTime())
					+ "')" + ";";
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
	public boolean delete(Preteur preteur) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "DELETE FROM Preteur WHERE ID = " + preteur.getId() + ";";
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
	public boolean update(Preteur preteur) {

		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Preteur SET Nom = " + "'" + preteur.getNom() + "', " + "Prenom = " + "'"
					+ preteur.getPrenom() + "', " + "Age = " + "'"
					+ preteur.getAge() + "', " + "Email = " + "'" + preteur.getEmail()
					+ "', " + "Password = " + "'" + preteur.getPassword() + "'" + " WHERE ID = " + preteur.getId()
					+ ";";
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
	public Preteur find(int id) {
		Preteur preteur = null;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Preteur WHERE ID = " + id);
			if (result.first()) {
				preteur = new Preteur(result.getInt("ID"), result.getString("Nom"), result.getString("Prenom"),
						result.getInt("Age"), result.getString("Email"), result.getString("Password"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preteur;
	}

	public List<Preteur> findAll() {
		List<Preteur> listPreteurs = new ArrayList<>();
		Preteur preteur = new Preteur();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Preteur");
			while (result.next()) {
				preteur = new Preteur(result.getInt("ID"), result.getString("Nom"), result.getString("Prenom"),
						result.getInt("Age"), result.getString("Email"), result.getString("Password"));
				listPreteurs.add(preteur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listPreteurs;
	}

	public List<Preteur> findAllExceptcurrentPreteur(Preteur preteur) {
		List<Preteur> listPreteurs = new ArrayList<>();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Preteur WHERE ID <> " + preteur.getId());
			while (result.next()) {
				preteur = new Preteur(result.getInt("ID"), result.getString("Nom"), result.getString("Prenom"),
						result.getInt("Age"), result.getString("Email"), result.getString("Password"));
				listPreteurs.add(preteur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listPreteurs;
	}
}
