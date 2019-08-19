package be.collins.vues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import be.collins.pojo.Administrateur;
import be.collins.pojo.Console;

import java.awt.Color;
import java.awt.Font;
import javax.swing.border.BevelBorder;


public class Dashboard_Console extends JFrame {

	private JPanel contentPane;
	private Administrateur currentAdministrateur;
	private JLabel lblMsgErrorConsole;
	private JButton btnAjouterConsole;
	private JButton btnDeconnexion;
	private JButton btnModifierConsole;
	private JButton btnSupprimerConsole;
	private JButton btnRetour;
	private List<Console> listConsole;
	private JList listConsoles;

	/**
	 * Create the frame.
	 */
	public Dashboard_Console(Administrateur currentAdministrateur) {
		this.currentAdministrateur = currentAdministrateur;
		initComponents();
		createEvents();
	}

	private void createEvents() {
		// TODO Auto-generated method stub
		btnAjouterConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Ajouter_Console ajouter_Console = new Ajouter_Console(currentAdministrateur);
				ajouter_Console.setVisible(true);
				ajouter_Console.setResizable(false);
			}
		});
		
		btnModifierConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listConsoles.getSelectedIndex();

				if (index == -1) {
					lblMsgErrorConsole.setText("Veuillez sélectionner une console.");
				} else {
					dispose();
					Modifier_Console modifier_Console = new Modifier_Console(currentAdministrateur,listConsole.get(index));
					modifier_Console.setVisible(true);
					modifier_Console.setResizable(false);
				}
			}
		});
		
		btnSupprimerConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listConsoles.getSelectedIndex();
				if (index == -1) {
					lblMsgErrorConsole.setText("Veuillez sélectionner une console.");
				} else {
					int input = JOptionPane.showConfirmDialog(null,
							"Êtes-vous sûr de bien vouloir supprimer cette console ?");
					if (input == 0) {
						Console c = new Console();
						c.delete(listConsole.get(index));

						dispose();
						Dashboard_Console dashboard_Console = new Dashboard_Console(currentAdministrateur);
						dashboard_Console.setVisible(true);
						dashboard_Console.setResizable(false);
					}
				}
			}
		});
		
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Connexion connexion = new Connexion();
				connexion.setVisible(true);
				connexion.setResizable(false);
			}
		});
		
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Dashboard_Administrateur dashboard_Administrateur = new Dashboard_Administrateur(currentAdministrateur);
				dashboard_Administrateur.setVisible(true);
				dashboard_Administrateur.setResizable(false);
			}
		});
		
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		setBackground(Color.WHITE);
		setTitle("Projet pr\u00EAt de jeu - Dashboard console");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 350);
		contentPane = new JPanel();
		contentPane.setForeground(Color.RED);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Liste des consoles");
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		label.setBounds(12, 11, 195, 23);
		contentPane.add(label);
		Console c = new Console();
		listConsole = c.findAll();

		Object[] donnees = new Object[listConsole.size()];

		for (int i = 0; i < listConsole.size(); i++) {
			donnees[i] = listConsole.get(i).getNom();
		}

		lblMsgErrorConsole = new JLabel("");
		lblMsgErrorConsole.setForeground(Color.RED);
		lblMsgErrorConsole.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblMsgErrorConsole.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblMsgErrorConsole.setBounds(268, 280, 281, 23);
		contentPane.add(lblMsgErrorConsole);

		btnAjouterConsole = new JButton("Ajouter une console");
		btnAjouterConsole.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnAjouterConsole.setBounds(12, 232, 175, 23);
		contentPane.add(btnAjouterConsole);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 764, 150);
		contentPane.add(scrollPane);

		listConsoles = new JList(donnees);
		listConsoles.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setViewportView(listConsoles);
		listConsoles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btnModifierConsole = new JButton("Modifier une console");
		btnModifierConsole.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnModifierConsole.setBounds(289, 232, 175, 23);
		contentPane.add(btnModifierConsole);

		btnSupprimerConsole = new JButton("Supprimer une console");
		btnSupprimerConsole.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnSupprimerConsole.setBounds(597, 232, 177, 23);
		contentPane.add(btnSupprimerConsole);

		btnDeconnexion = new JButton("D\u00E9connexion");
		btnDeconnexion.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnDeconnexion.setBounds(10, 280, 118, 23);
		contentPane.add(btnDeconnexion);

		btnRetour = new JButton("Retour");
		btnRetour.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnRetour.setBounds(685, 280, 89, 23);
		contentPane.add(btnRetour);		
	}

}
