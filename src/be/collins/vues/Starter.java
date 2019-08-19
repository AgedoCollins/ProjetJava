package be.collins.vues;

public class Starter {
	
	public static void main(String[] args) {
		Starter.creerConnexion();
	}
	public static void creerConnexion() {
		Connexion connect =  new Connexion();
		connect.setResizable(false);
		connect.setVisible(true);
	}
}
