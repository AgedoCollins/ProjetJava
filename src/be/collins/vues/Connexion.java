package be.collins.vues;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;

import java.awt.EventQueue;


import be.collins.pojo.Administrateur;
import be.collins.pojo.Emprunteur;
import be.collins.pojo.Preteur;
import java.awt.Toolkit;

public class Connexion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5802302544469570734L;
	private JPanel contentPane;
	private JTextField textFieldUser;
	private Administrateur currentAdministrateur;
	private Preteur currentPreteur;
	private Emprunteur currentEmprunteur;
	private JPasswordField passwordField;
	private JRadioButton rdbtnPreteur;
	private JRadioButton rdbtnEmprunteur;
	private JRadioButton rdbtnAdministrateur;
	private JLabel labelMsgErreur;
	private JButton btnConnexion;
	private JButton btnCreerCompte;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connexion frame = new Connexion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Connexion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Connexion.class.getResource("/be/collins/ressources/backgroundImage.jpg")));
		setTitle("Projet pr\u00EAt de jeu - Connexion");
		initComponents();
		createEvents();
	}
	
	///////////////////////////////////////////////////////////////////////////////
	// Cette méthode contient le code pour la création et l'initialisation      //
	//des composants														   //
	////////////////////////////////////////////////////////////////////////////
	private void initComponents() {
		// TODO Auto-generated method stub
		setBackground(Color.WHITE);
		setTitle("Projet pr\u00EAt de jeu - Connexion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 307);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JLabel lblUser = new JLabel("User");
		lblUser.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblUser.setBounds(73, 37, 46, 14);
		contentPane.add(lblUser);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblPassword.setBounds(73, 83, 83, 14);
		contentPane.add(lblPassword);

		textFieldUser = new JTextField();
		textFieldUser.setBounds(253, 31, 171, 27);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(253, 80, 171, 27);
		contentPane.add(passwordField);
		
		rdbtnAdministrateur = new JRadioButton("Administrateur");
		rdbtnAdministrateur.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		rdbtnAdministrateur.setBackground(Color.WHITE);
		rdbtnAdministrateur.setBounds(65, 131, 147, 23);
		contentPane.add(rdbtnAdministrateur);
		
		rdbtnPreteur = new JRadioButton("Pr\u00EAteur");
		rdbtnPreteur.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		rdbtnPreteur.setBackground(Color.WHITE);
		rdbtnPreteur.setBounds(224, 131, 109, 23);
		contentPane.add(rdbtnPreteur);
		
		rdbtnEmprunteur = new JRadioButton("Emprunteur");
		rdbtnEmprunteur.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		rdbtnEmprunteur.setBackground(Color.WHITE);
		rdbtnEmprunteur.setBounds(335, 131, 137, 23);
		contentPane.add(rdbtnEmprunteur);

		ButtonGroup personneRadio = new ButtonGroup();
		personneRadio.add(rdbtnAdministrateur);
		personneRadio.add(rdbtnPreteur);
		personneRadio.add(rdbtnEmprunteur);

		labelMsgErreur = new JLabel("");
		labelMsgErreur.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		labelMsgErreur.setForeground(Color.RED);
		labelMsgErreur.setBounds(73, 236, 334, 24);
		contentPane.add(labelMsgErreur);
		
		btnConnexion = new JButton("Connexion");
		btnConnexion.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnConnexion.setForeground(Color.BLACK);
		btnConnexion.setBackground(UIManager.getColor("Button.background"));
		btnConnexion.setBounds(67, 191, 109, 23);
		contentPane.add(btnConnexion);
		
		btnCreerCompte = new JButton("Cr\u00E9er un compte");
		btnCreerCompte.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnCreerCompte.setBounds(273, 191, 153, 23);
		contentPane.add(btnCreerCompte);
		
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Cette méthode contient le code pour la création des evenements       //
	/////////////////////////////////////////////////////////////////////////
	private void createEvents() {
		// TODO Auto-generated method stub
		btnConnexion.addActionListener(new ActionListener() {
			public void choixTypePersonne() {
				labelMsgErreur.setText("Veuillez sélectionner un type de personne.");
			}

			@SuppressWarnings("deprecation")
			public boolean champsVide() {
				boolean vide = false;
				if (textFieldUser.getText().isEmpty() || passwordField.getText().isEmpty()) {
					labelMsgErreur.setText("Veuillez remplir tous les champs.");
					vide = true;
				}
				return vide;
			}

			public void loginpasswordIncorrect() {
				labelMsgErreur.setText("Login et/ou mot de passe incorrect.");

			}

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (rdbtnAdministrateur.isSelected()) {
					if (!champsVide()) {
						Administrateur administrateur = new Administrateur();
						if (administrateur.connexionAdministrateur(textFieldUser.getText(),passwordField.getText())) {
							currentAdministrateur = administrateur.findAdministrateurByEmailPassword(textFieldUser.getText(), passwordField.getText());
							Dashboard_Administrateur dashboard_administrateur = new Dashboard_Administrateur(currentAdministrateur);
							dispose();
							dashboard_administrateur.setVisible(true);
							dashboard_administrateur.setResizable(false);
						} else {
							loginpasswordIncorrect();
						}
					}
				} else if (rdbtnPreteur.isSelected()) {
					if (!champsVide()) {
						Preteur preteur = new Preteur();
						if (preteur.connexionPreteur(textFieldUser.getText(), passwordField.getText())) {
							currentPreteur = preteur.findPreteurByEmailPassword(textFieldUser.getText(),
									passwordField.getText());
							Dashboard_Preteur dashboard_preteur = new Dashboard_Preteur(currentPreteur);
							dispose();
							dashboard_preteur.setVisible(true);
							dashboard_preteur.setResizable(false);
						} else {
							loginpasswordIncorrect();
						}
					}
				} else if (rdbtnEmprunteur.isSelected()) {
					if (!champsVide()) {
						Emprunteur emprunteur = new Emprunteur();
						if (emprunteur.connexionEmprunteur(textFieldUser.getText(), passwordField.getText())) {
							currentEmprunteur = emprunteur.findEmprunteurByEmailPassword(textFieldUser.getText(),
									passwordField.getText());
							Dashboard_Emprunteur dashboard_emprunteur = new Dashboard_Emprunteur(currentEmprunteur);
							dispose();
							dashboard_emprunteur.setVisible(true);
							dashboard_emprunteur.setResizable(false);
						} else {
							loginpasswordIncorrect();
						}
					}
				} else {
					choixTypePersonne();
				}
			}
		});
		
		btnCreerCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NouveauCompte newUser = new NouveauCompte();
				dispose();
				newUser.setVisible(true);
				newUser.setResizable(false);
			}
		});
	}
}
