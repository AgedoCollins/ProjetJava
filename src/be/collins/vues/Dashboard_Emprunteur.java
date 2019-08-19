package be.collins.vues;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.collins.pojo.Emprunteur;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;


public class Dashboard_Emprunteur extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6896228836485397295L;
	private JPanel contentPane;
	private JButton btnDeconnexion;
	private JButton btnPasserReservation;
	private JButton btnVoirReservations;
	private Emprunteur currentEmprunteur;

	/**
	 * Create the frame.
	 */
	public Dashboard_Emprunteur(Emprunteur currentEmprunteur) {
		this.currentEmprunteur = currentEmprunteur;
		initComponents();
		createEvents();
	}

	private void createEvents() 
	{
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Starter.main(null);
			}
		});
		
		btnPasserReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Passer_Reservation passerReservation = new Passer_Reservation(currentEmprunteur);
				passerReservation.setVisible(true);
				passerReservation.setResizable(false);
			}
		});
		
		btnVoirReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Voir_Reservation voir_Reservation = new Voir_Reservation(currentEmprunteur);
				voir_Reservation.setVisible(true);
				voir_Reservation.setResizable(false);
			}
		});
	}

	private void initComponents() 
	{
		setBackground(Color.WHITE);
		setTitle("Projet pr\u00EAt de jeu - Dashboad emprunteur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 324);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBienvenue = new JLabel("Bienvenue "+currentEmprunteur.getNom()+" "+currentEmprunteur.getPrenom()+"");
		lblBienvenue.setFont(new Font("Sitka Heading", Font.ITALIC, 12));
		lblBienvenue.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenue.setBounds(10, 26, 414, 24);
		contentPane.add(lblBienvenue);
		
		btnDeconnexion = new JButton("D\u00E9connexion");
		btnDeconnexion.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnDeconnexion.setBounds(132, 195, 169, 24);
		contentPane.add(btnDeconnexion);

		btnPasserReservation = new JButton("Passer une r\u00E9servation");
		btnPasserReservation.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnPasserReservation.setBounds(130, 75, 171, 24);
		contentPane.add(btnPasserReservation);

		btnVoirReservations = new JButton("Voir mes r\u00E9servations");
		btnVoirReservations.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnVoirReservations.setBounds(130, 139, 171, 24);
		contentPane.add(btnVoirReservations);

		JLabel lblUnite = new JLabel("Unit\u00E9 : ");
		lblUnite.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblUnite.setBounds(132, 244, 118, 14);
		contentPane.add(lblUnite);

		JLabel lblNombreUnite = new JLabel("");
		lblNombreUnite.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNombreUnite.setBounds(255, 244, 46, 14);
		Emprunteur emprunteur = new Emprunteur();
		emprunteur = emprunteur.findEmprunteurById(currentEmprunteur);
		lblNombreUnite.setText(String.valueOf(emprunteur.getUnite()));
		contentPane.add(lblNombreUnite);

		JLabel lblMsgError = new JLabel("");
		lblMsgError.setBounds(10, 271, 434, 14);
		contentPane.add(lblMsgError);

		if (emprunteur.getUnite() <= 0) {
			btnPasserReservation.setEnabled(false);
			lblMsgError.setText("Vous n'avez plus d'unité. Veuillez vous connecter en tant que prêteur.");
		}
	}

}
