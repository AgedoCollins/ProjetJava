package be.collins.vues;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.collins.pojo.Emprunteur;
import be.collins.pojo.Pret;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.Font;

@SuppressWarnings("serial")
public class Voir_Reservation extends JFrame {

	private JPanel contentPane;
	private Emprunteur currentEmprunteur;
	private JButton btnRetour;

	/**
	 * Create the frame.
	 */
	public Voir_Reservation(Emprunteur currentEmprunteur) {
		this.currentEmprunteur = currentEmprunteur;
		initComponents();
		createEvents();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initComponents() {
		// TODO Auto-generated method stub
		setBackground(Color.WHITE);
		setTitle("Projet pr\u00EAt de jeu - Liste de réservation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 987, 533);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblListeReservations = new JLabel("Liste de mes r\u00E9servations");
		lblListeReservations.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblListeReservations.setBounds(10, 24, 230, 14);
		contentPane.add(lblListeReservations);

		Pret p = new Pret();
		List<Pret> listPret = p.findAllPretByEmprunteur(currentEmprunteur);
		Object[] donnees = new Object[listPret.size()];

		for (int i = 0; i < listPret.size(); i++) {

			String confirmer_pret = " ";
			if (listPret.get(i).isConfirmer_pret()) {
				confirmer_pret = "Confirmé par " + listPret.get(i).getPreteur().getNom() + " "
						+ listPret.get(i).getPreteur().getPrenom();
			} else {
				confirmer_pret = "En attente de confirmation par le prêteur";
			}

			donnees[i] = "Jeu : " + listPret.get(i).getExemplaire().getJeu().getNom() + " - " + "Console : "
					+ listPret.get(i).getExemplaire().getJeu().getConsole().getNom() + " - " + " - " + "Réservation : "
					+ "du " + listPret.get(i).getDateDebut() + " au " + listPret.get(i).getDateFin() + " - " + " - "
					+ "État : " + confirmer_pret;

		}

		btnRetour = new JButton("Retour");
		btnRetour.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnRetour.setBounds(872, 460, 89, 23);
		contentPane.add(btnRetour);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 950, 400);
		contentPane.add(scrollPane);

		JList listReservations = new JList(donnees);
		listReservations.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setViewportView(listReservations);
	}

	private void createEvents() {
		// TODO Auto-generated method stub
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Dashboard_Emprunteur dashboard_Emprunteur = new Dashboard_Emprunteur(currentEmprunteur);
				dashboard_Emprunteur.setVisible(true);
				dashboard_Emprunteur.setResizable(false);
			}
		});
	}

}
