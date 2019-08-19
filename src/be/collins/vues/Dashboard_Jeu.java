package be.collins.vues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
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
import be.collins.pojo.Jeu;

import java.awt.Color;
import java.awt.Font;
import javax.swing.border.BevelBorder;

public class Dashboard_Jeu extends JFrame {

	private JPanel contentPane;
	private JList listJeux;
	private JButton btnAjouterJeu;
	private JButton btnDeconnexion;
	private JButton btnModifierJeu;
	private JButton btnSupprimerJeu;
	private JButton btnRetour;
	private JLabel lblMsgErrorJeux;
	private List<Jeu> listJeu;
	private Administrateur currentAdministrateur;
	/**
	 * Create the frame.
	 */
	public Dashboard_Jeu(Administrateur currentAdministrateur) 
	{
		this.currentAdministrateur = currentAdministrateur;
		initComponents();
		createEvents();
	}
	private void createEvents() {
		// TODO Auto-generated method stub
		btnAjouterJeu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				dispose();
				Ajouter_jeu ajouter_Jeu = new Ajouter_jeu(currentAdministrateur);
				ajouter_Jeu.setVisible(true);
				ajouter_Jeu.setResizable(false);

			}
		});
		
		btnModifierJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listJeux.getSelectedIndex();

				if (index == -1) {
					lblMsgErrorJeux.setText("Veuillez sélectionner un jeu.");
				} else {
					dispose();
					Modifier_Jeu modifier_Jeu = new Modifier_Jeu(currentAdministrateur, listJeu.get(index));
					modifier_Jeu.setVisible(true);
					modifier_Jeu.setResizable(false);
				}

			}
		});
		
		btnSupprimerJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listJeux.getSelectedIndex();
				if (index == -1) {
					lblMsgErrorJeux.setText("Veuillez sélectionner un jeu.");
				} else {
					int input = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de bien vouloir supprimer ce jeu ?");
					if (input == 0) {
						Jeu j = new Jeu();
						j.delete(listJeu.get(index));

						dispose();
						Dashboard_Jeu dashboard_Jeu = new Dashboard_Jeu(currentAdministrateur);
						dashboard_Jeu.setVisible(true);
						dashboard_Jeu.setResizable(false);
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
		setTitle("Projet pr\u00EAt de jeu - Dashboard jeu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 350);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListeJeu = new JLabel("Liste des jeux");
		lblListeJeu.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblListeJeu.setBounds(12, 11, 116, 14);
		contentPane.add(lblListeJeu);

		Jeu j = new Jeu();
		listJeu = j.findAll();

		Object[] donnees2 = new Object[listJeu.size()];
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MM-yy");

		for (int i = 0; i < listJeu.size(); i++) {
			String dispo = " ";
			if (listJeu.get(i).isDispo()) {
				dispo = "Disponible";
			} else {
				dispo = "Indisponible";
			}

			donnees2[i] = listJeu.get(i).getNom() + " - " + dispo + " - " + listJeu.get(i).getTarif() + " - "
					+ simpleDateFormat2.format(listJeu.get(i).getDateTarif()) + " - "
					+ listJeu.get(i).getConsole().getNom();
		}

		lblMsgErrorJeux = new JLabel("");
		lblMsgErrorJeux.setForeground(Color.RED);
		lblMsgErrorJeux.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblMsgErrorJeux.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblMsgErrorJeux.setBounds(282, 266, 230, 23);
		contentPane.add(lblMsgErrorJeux);

		btnAjouterJeu = new JButton("Ajouter un jeu");
		btnAjouterJeu.setFont(new Font("Tahoma", Font.ITALIC, 12));
		
		btnAjouterJeu.setBounds(12, 232, 175, 23);
		contentPane.add(btnAjouterJeu);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 46, 764, 150);
		contentPane.add(scrollPane_1);

		listJeux = new JList(donnees2);
		listJeux.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_1.setViewportView(listJeux);
		listJeux.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btnModifierJeu = new JButton("Modifier un jeu");
		btnModifierJeu.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnModifierJeu.setBounds(289, 232, 175, 23);
		contentPane.add(btnModifierJeu);

		btnSupprimerJeu = new JButton("Supprimer un jeu");
		btnSupprimerJeu.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnSupprimerJeu.setBounds(597, 232, 177, 23);
		contentPane.add(btnSupprimerJeu);
		
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
