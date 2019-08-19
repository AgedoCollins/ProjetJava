package be.collins.vues;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.collins.pojo.Emprunteur;
import be.collins.pojo.Preteur;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Dashboard_Preteur extends JFrame {

	private JPanel contentPane;
	private Preteur currentPreteur;
	private JButton btnListeJeux;
	private JButton btnListeJeuxAPreter;
	private JButton btnVoirReservations;
	private JButton btnDeconnexion;
	private JLabel lblNombreUnite;


	/**
	 * Create the frame.
	 */
	public Dashboard_Preteur(Preteur currentPreteur) {
		this.currentPreteur=currentPreteur;
		initComponents();
		createEvents();
	}


	private void initComponents() {
		// TODO Auto-generated method stub
		setBackground(Color.WHITE);
		setTitle("Projet pr\u00EAt de jeu - Dashboad prêteur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 413);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenue = new JLabel("Bienvenue "+currentPreteur.getNom()+" "+currentPreteur.getPrenom()+"");
		lblBienvenue.setFont(new Font("Sitka Heading", Font.ITALIC, 12));
		lblBienvenue.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenue.setBounds(10, 25, 559, 20);
		contentPane.add(lblBienvenue);
		
		btnDeconnexion = new JButton("D\u00E9connexion");
		btnDeconnexion.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnDeconnexion.setBounds(199, 263, 180, 28);
		contentPane.add(btnDeconnexion);
		
		btnListeJeux = new JButton("Voir la liste des jeux");
		btnListeJeux.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnListeJeux.setBounds(199, 76, 180, 28);
		contentPane.add(btnListeJeux);
		
		btnListeJeuxAPreter = new JButton("Voir mes jeux a pr\u00EAter");
		btnListeJeuxAPreter.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnListeJeuxAPreter.setBounds(199, 138, 183, 28);
		contentPane.add(btnListeJeuxAPreter);
		
		btnVoirReservations = new JButton("Voir les r\u00E9servations");
		btnVoirReservations.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnVoirReservations.setBounds(199, 204, 182, 28);
		contentPane.add(btnVoirReservations);
		
		JLabel lblUnite = new JLabel("Unit\u00E9 :");
		lblUnite.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblUnite.setBounds(200, 334, 123, 14);
		contentPane.add(lblUnite);
		
		lblNombreUnite = new JLabel("");
		lblNombreUnite.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNombreUnite.setBounds(277, 334, 46, 14);
		Emprunteur emprunteur = new Emprunteur();
		emprunteur.setId(currentPreteur.getId());
		emprunteur = emprunteur.findEmprunteurById(emprunteur);
		lblNombreUnite.setText(String.valueOf(emprunteur.getUnite()));
		contentPane.add(lblNombreUnite);
	}


	private void createEvents() {
		// TODO Auto-generated method stub
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Starter.main(null);
			}
		});
		
		btnVoirReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Liste_Reservations liste_Reservations = new Liste_Reservations(currentPreteur);
				liste_Reservations.setVisible(true);
				liste_Reservations.setResizable(false);
			}
		});
		
		btnListeJeuxAPreter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Liste_Jeux_A_Preter liste_Jeux_A_Preter = new Liste_Jeux_A_Preter(currentPreteur);
				liste_Jeux_A_Preter.setVisible(true);
				liste_Jeux_A_Preter.setResizable(false);
			}
		});
		
		btnListeJeux.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Liste_Jeux Liste_Jeux = new Liste_Jeux(currentPreteur);
				Liste_Jeux.setVisible(true);
				Liste_Jeux.setResizable(false);
			}
		});
	}

}
