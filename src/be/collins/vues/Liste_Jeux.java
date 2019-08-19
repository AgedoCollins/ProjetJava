package be.collins.vues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.collins.pojo.Jeu;
import be.collins.pojo.Preteur;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.Color;


public class Liste_Jeux extends JFrame {

	private JPanel contentPane;
	private Preteur currentPreteur;
	private JButton btnAjouterExemplaire;
	private JLabel lblMsgErrorJeux;
	private JButton btnRetour;
	private JList listJeux;
	private List<Jeu> listJeu;
	
	
	/**
	 * Create the frame.
	 */
	public Liste_Jeux(Preteur currentPreteur) {
		this.currentPreteur=currentPreteur;
		initComponents();
		createEvents();
	}

	private void createEvents() {
		// TODO Auto-generated method stub
		btnAjouterExemplaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = listJeux.getSelectedIndex();

				if (index == -1) {
					lblMsgErrorJeux.setText("Veuillez sélectionner un jeu.");
				} else {
					Ajouter_Exemplaire ajouter_Exemplaire = new Ajouter_Exemplaire(currentPreteur,listJeu.get(index));
					dispose();
					ajouter_Exemplaire.setVisible(true);
					ajouter_Exemplaire.setResizable(false);
				}

			}
		});
		
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
		// TODO Auto-generated method stub
		setBackground(Color.WHITE);
		setTitle("Projet pr\u00EAt de jeu - Liste jeu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 601, 385);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblMsgErrorJeux = new JLabel("");
		lblMsgErrorJeux.setForeground(Color.RED);
		lblMsgErrorJeux.setBounds(204, 293, 214, 21);
		contentPane.add(lblMsgErrorJeux);

		Jeu j = new Jeu();
		listJeu = j.findAll();


		Object[] donnees = new Object[listJeu.size()];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");

		for (int i = 0; i < listJeu.size(); i++) {
			String dispo = " ";
			if (listJeu.get(i).isDispo()) {
				dispo = "Disponible";
			} else {
				dispo = "Indisponible";
			}
			
			donnees[i] = listJeu.get(i).getNom() + " - " + dispo + " - " + listJeu.get(i).getTarif() + " - "
					+ simpleDateFormat.format(listJeu.get(i).getDateTarif()) + " - "
					+ listJeu.get(i).getConsole().getNom();
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 564, 214);
		contentPane.add(scrollPane);

		listJeux = new JList(donnees);
		listJeux.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listJeux.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listJeux);

		btnAjouterExemplaire = new JButton("Ajouter un exemplaire");
		btnAjouterExemplaire.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnAjouterExemplaire.setBounds(10, 287, 175, 27);
		contentPane.add(btnAjouterExemplaire);

		JLabel lblJeux = new JLabel("Liste des jeux");
		lblJeux.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblJeux.setBounds(10, 23, 120, 14);
		contentPane.add(lblJeux);

		btnRetour = new JButton("Retour");
		btnRetour.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnRetour.setBounds(428, 293, 149, 23);
		contentPane.add(btnRetour);
	}

}
