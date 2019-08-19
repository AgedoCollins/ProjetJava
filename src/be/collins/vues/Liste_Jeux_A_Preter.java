package be.collins.vues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.collins.pojo.Exemplaire;
import be.collins.pojo.Preteur;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.Color;


public class Liste_Jeux_A_Preter extends JFrame {

	private JPanel contentPane;
	private Preteur currentPreteur;
	private JList listJeux;
	private JButton btnRetour;

	/**
	 * Create the frame.
	 */
	public Liste_Jeux_A_Preter(Preteur currentPreteur) {
		this.currentPreteur=currentPreteur;
		initComponents();
		createEvents();
	}

	private void createEvents() {
		// TODO Auto-generated method stub
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Dashboard_Preteur dashboard_Preteur = new Dashboard_Preteur(currentPreteur);
				dashboard_Preteur.setVisible(true);
				dashboard_Preteur.setResizable(false);
			}
		});
	}

	private void initComponents() {
		setBackground(Color.WHITE);
		setTitle("Projet pr\u00EAt de jeu - Liste jeu à louer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 501);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblJeuxAPreter = new JLabel("Liste de mes exemplaires de jeux a pr\u00EAter");
		lblJeuxAPreter.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblJeuxAPreter.setBounds(10, 10, 314, 30);
		contentPane.add(lblJeuxAPreter);
		
		Exemplaire e = new Exemplaire();

		List<Exemplaire> listExemplaires = e.findAll(currentPreteur);
		currentPreteur.setListExamplaire(listExemplaires);
		List<Exemplaire> listExemplaire = currentPreteur.getListExemplaire();

		Object[] donnees = new Object[listExemplaire.size()];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");
		
		for (int i = 0; i < listExemplaire.size(); i++) {
			String dispo = " ";
			if (listExemplaire.get(i).getJeu().isDispo()) {
				dispo = "Disponible";
			} else {
				dispo = "Indisponible";
			}
			
			donnees[i] = "Exemplaire : "+listExemplaire.get(i).getNbrExemplaire() + " - Jeu : " + listExemplaire.get(i).getJeu().getNom()
					+ " - Console" + listExemplaire.get(i).getJeu().getConsole().getNom() + " - " + dispo + " - " + "Tarif : "
					+ listExemplaire.get(i).getJeu().getTarif() + " - " + "Date tarif : "
					+ simpleDateFormat.format(listExemplaire.get(i).getJeu().getDateTarif());

		}

		btnRetour = new JButton("Retour");
		btnRetour.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnRetour.setBounds(466, 428, 89, 23);
		contentPane.add(btnRetour);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 564, 350);
		contentPane.add(scrollPane);
		listJeux = new JList(donnees);
		listJeux.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listJeux.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listJeux);
	}

}
