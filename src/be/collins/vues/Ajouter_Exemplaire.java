package be.collins.vues;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.collins.pojo.Console;
import be.collins.pojo.Exemplaire;
import be.collins.pojo.Jeu;
import be.collins.pojo.Preteur;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.Color;

public class Ajouter_Exemplaire extends JFrame {

	private JPanel contentPane;
	private Jeu jeuAModifier;
	private Preteur currentPreteur;
	private JList listConsoles;
	private JButton btnValider;
	private JButton btnRetour;
	private JLabel labelMsgErreur;
	private JSpinner spinnerNombreExemplaires;
	private List<Console> listConsole;
	private JLabel lblNom2;
	private JCheckBox chckbxDisponibilite;
	private JLabel lblTarif2;
	
	/**
	 * Create the frame.
	 */
	public Ajouter_Exemplaire(Preteur currentPreteur,Jeu jeuAModifier) {
		this.currentPreteur=currentPreteur;
		this.jeuAModifier = jeuAModifier;
		initComponents();
		createEvents();
	}

	private void createEvents() {
		// TODO Auto-generated method stub
		btnValider.addActionListener(new ActionListener() {

			public boolean champsVide() {
				boolean valid = true;

				if ((int) spinnerNombreExemplaires.getValue() <= 0) {
					labelMsgErreur.setText("Veuillez entrez un nombre supérieur à 0.");
					valid = false;
				}

				return valid;
			}

			public void actionPerformed(ActionEvent e) {
				if (champsVide()) {
					Jeu jeu = new Jeu();
					Exemplaire ex = new Exemplaire();
					jeuAModifier.setNom(lblNom2.getText());
					jeuAModifier.setDispo((chckbxDisponibilite.isSelected()));
					jeuAModifier.setTarif((Double.parseDouble(lblTarif2.getText())));
					jeuAModifier.setConsole(listConsole.get(listConsoles.getSelectedIndex()));

					int nombreExemplaires = (int) spinnerNombreExemplaires.getValue();

					Exemplaire exemplaire = null;
					for (int i = 0; i < nombreExemplaires; i++) {
						exemplaire = new Exemplaire(jeuAModifier);
						currentPreteur.AjouterExemplaire(exemplaire);
						ex.create_Exemplaire(exemplaire, currentPreteur);
					}
					jeuAModifier.setDispo(true);
					jeu.update_Dispo(jeuAModifier);

					if (jeuAModifier.alreadyExist(jeuAModifier)) {
						labelMsgErreur.setText("Ce jeu existe déjà pour cette console.");

					} else {
						dispose();
						Liste_Jeux liste_Jeux = new Liste_Jeux(currentPreteur);
						liste_Jeux.setVisible(true);
						liste_Jeux.setResizable(false);
					}
				}

			}
		});
		
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Liste_Jeux liste_jeux = new Liste_Jeux(currentPreteur);
				liste_jeux.setVisible(true);
				liste_jeux.setResizable(false);
			}
		});
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		setBackground(Color.WHITE);
		setTitle("Projet pr\u00EAt de jeu -Ajoute d'exemplaire");
		setTitle("Ajouter un exemplaire");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 549);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNom = new JLabel("Nom du jeu");
		lblNom.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNom.setBounds(69, 48, 89, 14);
		contentPane.add(lblNom);

		JLabel lblDisponibilite = new JLabel("Disponibilite");
		lblDisponibilite.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblDisponibilite.setBounds(69, 86, 89, 20);
		contentPane.add(lblDisponibilite);

		JLabel lblTarif = new JLabel("Tarif");
		lblTarif.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblTarif.setBounds(69, 129, 89, 14);
		contentPane.add(lblTarif);

		lblNom2 = new JLabel("Nom du jeu");
		lblNom2.setBounds(251, 45, 131, 20);
		lblNom2.setText(jeuAModifier.getNom());
		contentPane.add(lblNom2);

		chckbxDisponibilite = new JCheckBox("Disponible");
		chckbxDisponibilite.setBackground(Color.WHITE);
		chckbxDisponibilite.setBounds(251, 88, 130, 20);
		chckbxDisponibilite.setSelected(true);
		chckbxDisponibilite.setEnabled(false);
		contentPane.add(chckbxDisponibilite);

		lblTarif2 = new JLabel("Tarif");
		lblTarif2.setBounds(251, 126, 131, 20);
		lblTarif2.setText(String.valueOf(jeuAModifier.getTarif()));
		contentPane.add(lblTarif2);

		JLabel lblListeConsoles = new JLabel("Console");
		lblListeConsoles.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblListeConsoles.setBounds(170, 172, 77, 14);
		contentPane.add(lblListeConsoles);
		
		Console c = new Console();
		listConsole = c.findAll();

		Object[] donnees = new Object[listConsole.size()];
		for (int i = 0; i < listConsole.size(); i++) {
			donnees[i] = listConsole.get(i).getNom();
		}

		listConsoles = new JList(donnees);
		listConsoles.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listConsoles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listConsoles.setBounds(53, 207, 329, 158);
		listConsoles.setEnabled(false);

		int index = -1;
		for (int i = 0; i < listConsole.size(); i++) {
			if (jeuAModifier.getConsole().getId() == listConsole.get(i).getId()) {
				index = i;
			}
		}
		
		listConsoles.setSelectedIndex(index);
		contentPane.add(listConsoles);

		JLabel lblNombreExemplaires = new JLabel("Nombre d'exemplaires");
		lblNombreExemplaires.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNombreExemplaires.setBounds(53, 388, 162, 20);
		contentPane.add(lblNombreExemplaires);

		spinnerNombreExemplaires = new JSpinner();
		spinnerNombreExemplaires.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerNombreExemplaires.setBounds(257, 391, 125, 20);
		contentPane.add(spinnerNombreExemplaires);

		labelMsgErreur = new JLabel("");
		labelMsgErreur.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		labelMsgErreur.setForeground(Color.RED);
		labelMsgErreur.setBounds(53, 435, 329, 29);
		contentPane.add(labelMsgErreur);

		btnValider = new JButton("Valider");
		btnValider.setBounds(53, 474, 89, 23);
		contentPane.add(btnValider);

		btnRetour = new JButton("Retour");
		
		btnRetour.setBounds(282, 474, 100, 23);
		contentPane.add(btnRetour);
	}

}
