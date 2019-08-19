package be.collins.vues;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.collins.pojo.Administrateur;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Dashboard_Administrateur extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 133568831609201915L;
	private JPanel contentPane;
	private JButton btnDeconnexion;
	private JButton btnGestionJeux;
	private JButton btnGestionConsole;
	private Administrateur currentAdministrateur;
	/**
	 * Create the frame.
	 */
	public Dashboard_Administrateur(Administrateur currentAdministrateur) {
		this.currentAdministrateur = currentAdministrateur;
		initComponents();
		createEvents();
	}
	
	private void initComponents() {
		// TODO Auto-generated method stub
		setBackground(Color.WHITE);
		setTitle("Projet pr\u00EAt de jeu - Dashboard administrateur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 437, 259);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBienvenue = new JLabel("Bienvenue "+currentAdministrateur.getNom()+" "+currentAdministrateur.getPrenom()+"");
		lblBienvenue.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenue.setFont(new Font("Sitka Heading", Font.ITALIC, 12));
		lblBienvenue.setBounds(109, 28, 187, 46);
		contentPane.add(lblBienvenue);

		btnDeconnexion = new JButton("D\u00E9connexion");
		btnDeconnexion.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnDeconnexion.setBounds(109, 174, 187, 23);
		contentPane.add(btnDeconnexion);
		
		btnGestionJeux = new JButton("Gestion des jeux");
		btnGestionJeux.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnGestionJeux.setBounds(109, 75, 187, 28);
		contentPane.add(btnGestionJeux);

		btnGestionConsole = new JButton("Gestion des consoles");
		btnGestionConsole.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnGestionConsole.setBounds(109, 123, 187, 28);
		contentPane.add(btnGestionConsole);
	}
	
	private void createEvents() {
		// TODO Auto-generated method stub
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Starter.creerConnexion();
			}
		});
		
		btnGestionConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Dashboard_Console dashboard_Console = new Dashboard_Console(currentAdministrateur);
				dashboard_Console.setVisible(true);
				dashboard_Console.setResizable(false);
			}
		});
		
		btnGestionJeux.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Dashboard_Jeu dashboard_Jeu = new Dashboard_Jeu(currentAdministrateur);
				dashboard_Jeu.setVisible(true);
				dashboard_Jeu.setResizable(false);
			}
		});
	}

}
