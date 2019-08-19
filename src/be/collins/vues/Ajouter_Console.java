package be.collins.vues;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.collins.pojo.Administrateur;
import be.collins.pojo.Console;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.BevelBorder;

public class Ajouter_Console extends JFrame {

	private static final long serialVersionUID = -7907158422454634533L;
	private JPanel contentPane;
	private JTextField textFieldNom;
	private JButton btnAjouterConsole;
	private JLabel labelMsgErreur;
	private JButton btnRetour;
	private Administrateur currentAdministrateur;
	
	/**
	 * Create the frame.
	 */
	public Ajouter_Console(Administrateur currentAdministrateur) {
		this.currentAdministrateur = currentAdministrateur;
		initComponents();
		createEvents();
	}
	
	///////////////////////////////////////////////////////////////////////////////
	// Cette méthode contient le code pour la création et l'initialisation      //
	//des composants														   //
	////////////////////////////////////////////////////////////////////////////
	private void initComponents() {
		// TODO Auto-generated method stub
		setBackground(new Color(240, 240, 240));
		setTitle("Projet pr\u00EAt de jeu - Ajoute console");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 384, 242);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNom = new JLabel("Nom du console");
		lblNom.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNom.setBounds(53, 66, 125, 14);
		contentPane.add(lblNom);

		textFieldNom = new JTextField();
		textFieldNom.setBounds(230, 64, 107, 23);
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);

		labelMsgErreur = new JLabel("");
		labelMsgErreur.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		labelMsgErreur.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		labelMsgErreur.setForeground(Color.RED);
		labelMsgErreur.setBounds(53, 162, 278, 33);
		contentPane.add(labelMsgErreur);

		btnAjouterConsole = new JButton("Ajouter");
		btnAjouterConsole.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnAjouterConsole.setBounds(53, 108, 113, 23);
		contentPane.add(btnAjouterConsole);

		btnRetour = new JButton("Retour");
		btnRetour.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnRetour.setBounds(230, 108, 107, 23);
		contentPane.add(btnRetour);
	}

	///////////////////////////////////////////////////////////////////////////
	// Cette méthode contient le code pour la création des evenements       //
	/////////////////////////////////////////////////////////////////////////
	private void createEvents() 
	{
		btnAjouterConsole.addActionListener(new ActionListener() {
			public boolean champsVide() {
				boolean valid = true;
				if (textFieldNom.getText().isEmpty()) {
					labelMsgErreur.setText("Veuillez remplir tous les champs.");
					valid = false;
				}

				return valid;
			}

			public void actionPerformed(ActionEvent e) {
				if (champsVide()) {
					Console console = new Console(textFieldNom.getText());
					console.setId(-1);
					if (console.alreadyExist(console)) {
						labelMsgErreur.setText("Cette console existe déjà.");
					} else {
						console.create(console);
						dispose();
						Dashboard_Console dashboard_Console = new Dashboard_Console(currentAdministrateur);
						dashboard_Console.setVisible(true);
						dashboard_Console.setResizable(false);
					}
				}
			}
		});
		
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Dashboard_Console dashboard_Console = new Dashboard_Console(currentAdministrateur);
				dashboard_Console.setVisible(true);
				dashboard_Console.setResizable(false);

			}
		});
	}
}
