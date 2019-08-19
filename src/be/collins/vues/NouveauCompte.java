package be.collins.vues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import be.collins.pojo.Emprunteur;
import be.collins.pojo.Preteur;

import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;

public class NouveauCompte extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7016429919484100741L;
	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldAge;
	private JTextField textFieldEmail;
	private JButton btnRetour;
	private JLabel labelMsgErreur;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JButton btnInscription;
	
	/**
	 * Create the frame.
	 */
	public NouveauCompte() {
		setTitle("Projet pr\u00EAt de jeu - Inscription");
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
		setTitle("Projet pr\u00EAt de jeu - Inscription");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 436, 454);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNom = new JLabel("Nom (*)");
		lblNom.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNom.setBounds(24, 24, 72, 14);
		contentPane.add(lblNom);

		JLabel lblPrenom = new JLabel("Prenom (*)");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblPrenom.setBounds(24, 71, 89, 14);
		contentPane.add(lblPrenom);

		JLabel lblAge = new JLabel("Age (*)");
		lblAge.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblAge.setBounds(24, 116, 137, 17);
		contentPane.add(lblAge);

		JLabel lblEmail = new JLabel("Email (*)");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblEmail.setBounds(24, 163, 89, 14);
		contentPane.add(lblEmail);

		JLabel lblPassword = new JLabel("Password (*)");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblPassword.setBounds(24, 213, 106, 17);
		contentPane.add(lblPassword);

		JLabel lblConfirmPassword = new JLabel("Confirmer password (*)");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblConfirmPassword.setBounds(24, 265, 179, 14);
		contentPane.add(lblConfirmPassword);

		textFieldNom = new JTextField();
		textFieldNom.setBounds(233, 21, 162, 20);
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);

		textFieldPrenom = new JTextField();
		textFieldPrenom.setBounds(233, 68, 162, 20);
		contentPane.add(textFieldPrenom);
		textFieldPrenom.setColumns(10);
		
		textFieldAge = new JTextField();
		textFieldAge.setBounds(233, 115, 162, 20);
		contentPane.add(textFieldAge);
		textFieldAge.setColumns(10);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(233, 160, 162, 20);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(233, 209, 162, 20);
		contentPane.add(passwordField);

		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(233, 262, 162, 20);
		contentPane.add(confirmPasswordField);
		
		btnInscription = new JButton("Cr\u00E9er");
		btnInscription.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnInscription.setBounds(41, 341, 89, 23);
		contentPane.add(btnInscription);
		
		btnRetour = new JButton("Retour");
		btnRetour.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnRetour.setBounds(277, 341, 89, 23);
		contentPane.add(btnRetour);
		
		
		labelMsgErreur = new JLabel("");
		labelMsgErreur.setBounds(41, 375, 325, 23);
		contentPane.add(labelMsgErreur);
		
	}

	///////////////////////////////////////////////////////////////////////////
	// Cette méthode contient le code pour la création des evenements       //
	/////////////////////////////////////////////////////////////////////////
	private void createEvents() {
		// TODO Auto-generated method stub
		btnInscription.addActionListener(new ActionListener() {
			public boolean isStringInt(String s)
			{
			    try
			    {
			        Integer.parseInt(s);
			        return true;
			    } catch (NumberFormatException ex)
			    {
			        return false;
			    }
			}
			@SuppressWarnings("deprecation")
			public boolean champsVide() {
				String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(textFieldEmail.getText());
				
				boolean valid = true;
				if (textFieldNom.getText().isEmpty() || textFieldPrenom.getText().isEmpty()
						|| textFieldAge.getText().isEmpty()
						|| textFieldEmail.getText().isEmpty() || passwordField.getText().isEmpty()
						|| confirmPasswordField.getText().isEmpty()) {
					labelMsgErreur.setText("Veuillez remplir tous les champs.");
					valid = false;
				}

				else if (!(matcher.matches())) {
					labelMsgErreur.setText("Veuillez entrer un e-mail valide.");
					valid = false;
				}

				else if (!(isStringInt(textFieldAge.getText()))) {
					labelMsgErreur.setText("Le format de l'age doit être un entier");
					valid = false;
				}

				else if (!passwordField.getText().equals(confirmPasswordField.getText())) {
					labelMsgErreur.setText("Les mots de passes doivent être identiques.");
					valid = false;
				}

				return valid;
			}

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (champsVide()) {
					Preteur preteur = new Preteur(textFieldNom.getText(), textFieldPrenom.getText(),
							Integer.parseInt(textFieldAge.getText()), textFieldEmail.getText(), passwordField.getText());
					preteur.setId(-1);
					Emprunteur emprunteur = new Emprunteur(textFieldNom.getText(), textFieldPrenom.getText(),
							Integer.parseInt(textFieldAge.getText()), textFieldEmail.getText(), passwordField.getText());
					emprunteur.setId(-1);
					if (preteur.isExistPreteur(preteur)) {
						labelMsgErreur.setText("Cet adresse e-mail existe déjà.");
					} else if (emprunteur.isExistEmprunteur(emprunteur)) {
						labelMsgErreur.setText("Cet adresse e-mail existe déjà.");
					} else {
						preteur.create(preteur);

						emprunteur.create(emprunteur);
						dispose();
						Connexion connexion = new Connexion();
						connexion.setVisible(true);
						connexion.setResizable(false);
					}
				}
			}
		});
		
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Connexion connexion = new Connexion();
				connexion.setVisible(true);
				connexion.setResizable(false);
			}
		});
		
	}
}
