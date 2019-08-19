package be.collins.vues;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.collins.pojo.Administrateur;
import be.collins.pojo.Console;
import be.collins.pojo.Jeu;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;

public class Ajouter_jeu extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNom;
	private JButton btnAjouter;
	private JButton btnRetour;
	private JSpinner spinnerAjouterTarif;
	private JCheckBox chckbxDisponibilite;
	private JLabel labelMsgErreur;
	List<Console> listConsole;
	private JList listConsoles;
	private Administrateur currentAdministrateur;

	/**
	 * Create the frame.
	 */
	public Ajouter_jeu(Administrateur currentAdministrateur) {
		this.currentAdministrateur = currentAdministrateur;
		initComponents();
		createEvents();
	}
	
	private void createEvents() {
		// TODO Auto-generated method stub
		btnAjouter.addActionListener(new ActionListener() {
			
			public boolean champsVide() {
				boolean valid = true;
				if (textFieldNom.getText().isEmpty() || chckbxDisponibilite.getText().isEmpty()) {
					labelMsgErreur.setText("Veuillez remplir tous les champs.");
					valid = false;
				} else if ((double) spinnerAjouterTarif.getValue() <= 0.0) {
					labelMsgErreur.setText("Le tarif ne peut pas être égal à 0.");
					valid = false;
				} else if (listConsoles.getSelectedIndex() == -1) {
					labelMsgErreur.setText("Veuillez choisir une console.");
					valid = false;
				}

				return valid;
			}

			public void actionPerformed(ActionEvent e) {
				if (champsVide()) {
					Date date = new Date();
					int index = listConsoles.getSelectedIndex();
					Console consoleChoisie = listConsole.get(index);
					Jeu jeu = new Jeu(textFieldNom.getText(), chckbxDisponibilite.isSelected(),
							(double) spinnerAjouterTarif.getValue(), new Timestamp(date.getTime()), consoleChoisie);
					jeu.setId(-1);
					if (jeu.alreadyExist(jeu)) {
						labelMsgErreur.setText("Ce jeu existe déjà pour cette console.");
					} else {

						jeu.create(jeu, currentAdministrateur);
						int lastId = jeu.findLastIdJeu();
						jeu.setId(lastId);
						jeu.create_Ligne_Jeu(jeu, currentAdministrateur);
						dispose();
						Dashboard_Jeu dashboard_Jeu = new Dashboard_Jeu(currentAdministrateur);
						dashboard_Jeu.setVisible(true);
						dashboard_Jeu.setResizable(false);
					}
				}

			}
		});
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard_Jeu dashboard_Jeu = new Dashboard_Jeu(currentAdministrateur);
				dashboard_Jeu.setVisible(true);
				dashboard_Jeu.setResizable(false);
				dispose();
			}
		});
	}
	
	private void initComponents() {
		// TODO Auto-generated method stub
		setBackground(Color.WHITE);
		setTitle("Projet pr\u00EAt de jeu - ajoute jeu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 457, 507);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNom = new JLabel("Nom du jeu");
		lblNom.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNom.setBounds(69, 48, 89, 20);
		contentPane.add(lblNom);

		JLabel lblDisponibilite = new JLabel("Disponibilite");
		lblDisponibilite.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblDisponibilite.setBounds(69, 91, 89, 20);
		contentPane.add(lblDisponibilite);

		JLabel lblTarif = new JLabel("Tarif");
		lblTarif.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblTarif.setBounds(69, 129, 89, 20);
		contentPane.add(lblTarif);

		textFieldNom = new JTextField();
		textFieldNom.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textFieldNom.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textFieldNom.setBounds(250, 45, 131, 20);
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);

		chckbxDisponibilite = new JCheckBox("Disponible");
		chckbxDisponibilite.setBackground(Color.WHITE);
		chckbxDisponibilite.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxDisponibilite.setBounds(251, 88, 130, 20);
		contentPane.add(chckbxDisponibilite);

		JLabel lblListeConsoles = new JLabel("Console");
		lblListeConsoles.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblListeConsoles.setBounds(184, 196, 77, 14);
		contentPane.add(lblListeConsoles);
		Console c = new Console();
		listConsole = c.findAll();

		Object[] donnees = new Object[listConsole.size()];

		for (int i = 0; i < listConsole.size(); i++) {
			donnees[i] = listConsole.get(i).getNom();
		}

		listConsoles = new JList(donnees);
		listConsoles.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listConsoles.setBackground(SystemColor.control);
		listConsoles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listConsoles.setBounds(61, 220, 320, 138);
		contentPane.add(listConsoles);

		labelMsgErreur = new JLabel("");
		labelMsgErreur.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		labelMsgErreur.setHorizontalAlignment(SwingConstants.CENTER);
		labelMsgErreur.setForeground(Color.RED);
		labelMsgErreur.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		labelMsgErreur.setBounds(69, 432, 313, 29);
		contentPane.add(labelMsgErreur);

		spinnerAjouterTarif = new JSpinner();
		spinnerAjouterTarif.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		spinnerAjouterTarif.setFont(new Font("Tahoma", Font.PLAIN, 11));
		spinnerAjouterTarif.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		spinnerAjouterTarif.setBounds(250, 126, 44, 20);
		contentPane.add(spinnerAjouterTarif);

		btnAjouter = new JButton("Ajouter");
		btnAjouter.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnAjouter.setBounds(61, 387, 89, 23);
		contentPane.add(btnAjouter);
		
		btnRetour = new JButton("Retour");
		btnRetour.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnRetour.setBounds(282, 387, 100, 23);
		contentPane.add(btnRetour);
	}

}
