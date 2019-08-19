package be.collins.vues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import be.collins.pojo.Emprunteur;
import be.collins.pojo.Exemplaire;
import be.collins.pojo.Jeu;
import be.collins.pojo.Pret;
import be.collins.pojo.Preteur;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.Color;

public class Liste_Reservations extends JFrame {

	private JPanel contentPane;
	private Preteur currentPreteur;
	private JButton btnConfirmer;
	private JLabel lblMsgError;
	private JButton btnRetour;
	private JList listPrets;
	
	private List<Pret> listPret;
	private Emprunteur emprunteur;
	/**
	 * Create the frame.
	 */
	public Liste_Reservations(Preteur currentPreteur) {
		this.currentPreteur=currentPreteur;
		initComponents();
		createEvents();
	}


	private void createEvents() {
		// TODO Auto-generated method stub
		Pret p = new Pret();
		
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Dashboard_Preteur dashboard_Preteur = new Dashboard_Preteur(currentPreteur);
				dashboard_Preteur.setVisible(true);
				dashboard_Preteur.setResizable(false);
			}
		});
		
		listPrets.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				int index = listPrets.getSelectedIndex();
				if (p.is_confirmed(listPret.get(index))) {
					lblMsgError.setText("Ce prêt a déjà été confirmé.");
					btnConfirmer.setEnabled(false);
				} else if (listPret.get(index).getEmprunteur().getUnite() < listPret.get(index).getExemplaire().getJeu()
						.getTarif()) {
					lblMsgError.setText("L'emprunteur n'a pas assez d'unité.");
					btnConfirmer.setEnabled(false);
				} else {
					lblMsgError.setText("");
					btnConfirmer.setEnabled(true);
				}
			}
		});
		
		btnConfirmer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = listPrets.getSelectedIndex();
				if (index == -1) {
					lblMsgError.setText("Veuillez sélectionner un prêt.");
				} else {
					if (p.sameReservationFound(listPret.get(index).getExemplaire())) {
						List<Pret> listPret = p.findAll(emprunteur);
						Emprunteur emprunteur = new Emprunteur();
						List<Pret> listPretEmprunteur = p.getPretEmprunteurSortByPriorites(listPret.get(index).getExemplaire());

						Pret pret = new Pret();
						pret.setId(listPretEmprunteur.get(0).getId());
						emprunteur.setId(listPretEmprunteur.get(0).getEmprunteur().getId());
						pret.updatePretEmprunteur(emprunteur, pret);

						for (int i = 1; i < listPretEmprunteur.size(); i++) {
							pret.delete(listPretEmprunteur.get(i));
						}

						dispose();
						listPretEmprunteur.get(0).setConfirmer_pret(true);
						Emprunteur emprunteurADebiter = listPretEmprunteur.get(0).getEmprunteur();
						Emprunteur emprunteurACrediter = new Emprunteur();
						emprunteurACrediter.setId(currentPreteur.getId());
						emprunteurACrediter = emprunteur.findEmprunteurById(emprunteurACrediter);
						emprunteurADebiter
								.soustraireUnite((int) listPretEmprunteur.get(0).getExemplaire().getJeu().getTarif());

						emprunteurACrediter.setId(currentPreteur.getId());
						emprunteurACrediter
								.ajouterUnite(((int) listPretEmprunteur.get(0).getExemplaire().getJeu().getTarif()));
						emprunteur.updateUnite(emprunteurADebiter);
						emprunteur.updateUnite(emprunteurACrediter);
						pret.updateConfirmPret(listPretEmprunteur.get(0));
						pret.updatePretOfPreteur(currentPreteur, listPretEmprunteur.get(0));
						listPretEmprunteur.get(0).getExemplaire().getJeu().setDispo(false);
						Exemplaire exemplaire = new Exemplaire();
						exemplaire.update(listPretEmprunteur.get(0).getExemplaire());
						Jeu jeu = new Jeu();
						jeu.update_Dispo_False(listPretEmprunteur.get(0).getExemplaire().getJeu());
						Liste_Reservations liste_Reservations = new Liste_Reservations(currentPreteur);
						liste_Reservations.setVisible(true);
						liste_Reservations.setResizable(false);
					} else {
						dispose();
						listPret.get(index).setConfirmer_pret(true);
						Emprunteur emprunteurADebiter = listPret.get(index).getEmprunteur();
						Emprunteur e = new Emprunteur();
						Emprunteur emprunteurACrediter = new Emprunteur();
						emprunteurACrediter.setId(currentPreteur.getId());
						emprunteurACrediter = e.findEmprunteurById(emprunteurACrediter);
						emprunteurADebiter
								.soustraireUnite((int) listPret.get(index).getExemplaire().getJeu().getTarif());

						emprunteurACrediter.setId(currentPreteur.getId());
						emprunteurACrediter
								.ajouterUnite(((int) listPret.get(index).getExemplaire().getJeu().getTarif()));
						e.updateUnite(emprunteurADebiter);
						e.updateUnite(emprunteurACrediter);
						p.updateConfirmPret(listPret.get(index));
						p.updatePretOfPreteur(currentPreteur, listPret.get(index));
						Liste_Reservations liste_Reservations = new Liste_Reservations(currentPreteur);
						liste_Reservations.setVisible(true);
						liste_Reservations.setResizable(false);
					}
				}
			}
		});

	}


	private void initComponents() {
		// TODO Auto-generated method stub
		setBackground(Color.WHITE);
		setTitle("Projet pr\u00EAt de jeu - Liste réservation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1192, 699);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblListePrets = new JLabel("Liste des pr\u00EAts");
		lblListePrets.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblListePrets.setBounds(10, 11, 124, 14);
		contentPane.add(lblListePrets);

		emprunteur = new Emprunteur();
		emprunteur.setId(currentPreteur.getId());

		Pret p = new Pret();
		listPret = p.findAll(emprunteur);

		Object[] donnees = new Object[listPret.size()];

		for (int i = 0; i < listPret.size(); i++) {
			String confirmer_pret = " ";
			if (listPret.get(i).isConfirmer_pret()) {
				confirmer_pret = "Confirmé";
			} else {
				confirmer_pret = "En attente de confirmation";
			}

			donnees[i] = i+ ") Emprunteur : " + listPret.get(i).getEmprunteur().getNom() + " - "
					+ listPret.get(i).getEmprunteur().getPrenom() + " - "
					 + " - " + "Date de réservation : "+ listPret.get(i).getEmprunteur().getReservation().getDateReservation() +
					 " - " + " - " + "Prêt : "+ "du " + listPret.get(i).getDateDebut() + " au " + listPret.get(i).getDateFin() + 
					 " - " + " - "+ "Jeu : " + listPret.get(i).getExemplaire().getJeu().getNom() + " - " + "Console : "
					+ listPret.get(i).getExemplaire().getJeu().getConsole().getNom() + " - " + " - " + "Etat : "+ confirmer_pret;
		}

		btnRetour = new JButton("Retour");
		btnRetour.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnRetour.setBounds(1071, 614, 89, 23);
		contentPane.add(btnRetour);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 1150, 550);
		contentPane.add(scrollPane);

		lblMsgError = new JLabel("");
		lblMsgError.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblMsgError.setForeground(Color.RED);
		lblMsgError.setBounds(313, 614, 241, 23);
		contentPane.add(lblMsgError);

		listPrets = new JList(donnees);
		listPrets.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listPrets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listPrets);

		btnConfirmer = new JButton("Confirmer");
		btnConfirmer.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnConfirmer.setBounds(10, 614, 99, 23);
		contentPane.add(btnConfirmer);
	}

}
