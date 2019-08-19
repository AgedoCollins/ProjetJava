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
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Modifier_Jeu extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldTarif;
	private JButton btnModifier;
	private JLabel labelMsgErreur;
	private Administrateur currentAdministrateur;
	private Jeu jeuAModifier;
	private List<Console> listConsole;
	private JList listConsoles;
	private JCheckBox chckbxDisponibilite;
	private JButton btnRetour;


	/**
	 * Create the frame.
	 */
	public Modifier_Jeu(Administrateur currentAdministrateur, Jeu jeuAModifier) {
		this.currentAdministrateur = currentAdministrateur;
		this.jeuAModifier = jeuAModifier;
		initComponents();
		createEvents();
	}

	private void createEvents() {
		// TODO Auto-generated method stub
		btnModifier.addActionListener(new ActionListener() {

			public boolean champsVide() {
				boolean valid = true;
				if (textFieldNom.getText().isEmpty() || textFieldTarif.getText().isEmpty())
				{
					labelMsgErreur.setText("Veuillez remplir tous les champs.");
					valid = false;
				} else if (listConsoles.getSelectedIndex() == -1) {
					labelMsgErreur.setText("Veuillez choisir une console.");
					valid = false;
				}

				return valid;
			}

			public void actionPerformed(ActionEvent e) {
				if (champsVide()) {
					
					Jeu jeu = new Jeu();
					jeuAModifier.setNom(textFieldNom.getText());
					jeuAModifier.setDispo((chckbxDisponibilite.isSelected()));
					jeuAModifier.setTarif((Double.parseDouble(textFieldTarif.getText())));
					jeuAModifier.setConsole(listConsole.get(listConsoles.getSelectedIndex()));
					if (jeuAModifier.alreadyExist(jeuAModifier)) {
						labelMsgErreur.setText("Ce jeu existe déjà pour cette console.");

					} else {
						jeu.update(jeuAModifier);

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
				dispose();
				Dashboard_Jeu dashboard_Jeu = new Dashboard_Jeu(currentAdministrateur);
				dashboard_Jeu.setVisible(true);
				dashboard_Jeu.setResizable(false);
			}
		});
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		setTitle("Projet pr\u00EAt de jeu - Modification jeu");
		setTitle("Modifier un jeu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 531);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNom = new JLabel("Nom du jeu");
		lblNom.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNom.setBounds(69, 48, 89, 17);
		contentPane.add(lblNom);

		JLabel lblDisponibilite = new JLabel("Disponibilite");
		lblDisponibilite.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblDisponibilite.setBounds(69, 91, 89, 14);
		contentPane.add(lblDisponibilite);

		JLabel lblTarif = new JLabel("Tarif");
		lblTarif.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblTarif.setBounds(69, 129, 89, 14);
		contentPane.add(lblTarif);

		textFieldNom = new JTextField();
		textFieldNom.setBounds(251, 45, 131, 20);
		textFieldNom.setText(jeuAModifier.getNom());
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);

		chckbxDisponibilite = new JCheckBox("Disponible");
		chckbxDisponibilite.setBackground(Color.WHITE);
		chckbxDisponibilite.setBounds(251, 88, 130, 20);
		chckbxDisponibilite.setSelected(jeuAModifier.isDispo());
		contentPane.add(chckbxDisponibilite);

		textFieldTarif = new JTextField();
		textFieldTarif.setBounds(251, 126, 131, 20);
		textFieldTarif.setText(String.valueOf(jeuAModifier.getTarif()));
		textFieldTarif.setEnabled(false);
		contentPane.add(textFieldTarif);
		textFieldTarif.setColumns(10);

		JLabel lblListeConsoles = new JLabel("Console");
		lblListeConsoles.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblListeConsoles.setBounds(176, 183, 77, 14);
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
		listConsoles.setBounds(69, 218, 313, 161);
		int index = -1;
		for (int i = 0; i < listConsole.size(); i++) {
			if (jeuAModifier.getConsole().getId() == listConsole.get(i).getId()) {
				index = i;
			}
		}
		listConsoles.setSelectedIndex(index);
		contentPane.add(listConsoles);

		labelMsgErreur = new JLabel("");
		labelMsgErreur.setHorizontalAlignment(SwingConstants.CENTER);
		labelMsgErreur.setForeground(Color.RED);
		labelMsgErreur.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		labelMsgErreur.setBounds(69, 401, 313, 29);
		contentPane.add(labelMsgErreur);

		btnModifier = new JButton("Modifier");
		btnModifier.setFont(new Font("Tahoma", Font.ITALIC, 12));
		
		btnModifier.setBounds(69, 457, 89, 23);
		contentPane.add(btnModifier);

		btnRetour = new JButton("Retour");
		btnRetour.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnRetour.setBounds(282, 457, 100, 23);
		contentPane.add(btnRetour);
	}

}
