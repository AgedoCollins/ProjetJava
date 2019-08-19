package be.collins.vues;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;

import be.collins.pojo.Emprunteur;
import be.collins.pojo.Exemplaire;
import be.collins.pojo.Jeu;
import be.collins.pojo.Pret;
import be.collins.pojo.Preteur;
import be.collins.pojo.Reservation;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.Font;


public class Passer_Reservation extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Emprunteur currentEmprunteur;
	@SuppressWarnings("rawtypes")
	private JList listJeux;
	private JFormattedTextField dateTextFieldDebut;
	private JFormattedTextField dateTextFieldFin;
	private JButton btnRetour;
	private List<Exemplaire> listExemplaires;
	private JLabel lblMsgError;
	private JButton btnReservation;
	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date dateDebut = new Date();
	Date dateFin = new Date();

	/**
	 * Create the frame.
	 */
	public Passer_Reservation(Emprunteur currentEmprunteur) {
		this.currentEmprunteur = currentEmprunteur;
		initComponents();
		createEvents();
	}

	private void createEvents() {
		// TODO Auto-generated method stub
		btnReservation.addActionListener(new ActionListener() {
			public boolean champsVide() {
				String regex = "^(([0-9])|([0-2][0-9])|([3][0-1]))\\/(01|02|03|04|05|06|07|08|09|10|11|12)\\/\\d{4}$";
				Pattern pattern = Pattern.compile(regex);
				Pattern pattern2 = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(dateTextFieldDebut.getText());

				Matcher matcher2 = pattern2.matcher(dateTextFieldFin.getText());

				boolean valid = true;
				if (dateTextFieldDebut.getText().isEmpty()) {
					lblMsgError.setText("Veuillez insérer une date de début.");
					valid = false;
				} else if (dateTextFieldFin.getText().isEmpty()) {
					lblMsgError.setText("Veuillez insérer une date de fin.");
					valid = false;
				}

				else if (!(matcher.matches()) || !(matcher2.matches())) {
					lblMsgError.setText("Format de date attendu \"dd/MM/yyyy\".");
					valid = false;
				} else {
					
					try {
						dateDebut = formatter.parse(dateTextFieldDebut.getText());
						dateFin = formatter.parse(dateTextFieldFin.getText());
						
						dateDebut = new Timestamp(dateDebut.getTime());
						dateFin = new Timestamp(dateFin.getTime());
						
						int res = dateDebut.compareTo(dateFin);

						if (res == 1) {
							lblMsgError.setText("La date de fin doit être ultérieure à la date de début.");
							valid = false;
						}
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
				}

				return valid;
			}

			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				int index = listJeux.getSelectedIndex();
				if (index == -1) 
				{
					lblMsgError.setText("Veuillez sélectionner un jeu.");
				} 
				else if (champsVide()) 
				{
					int id = listExemplaires.get(index).getJeu().getId();
					dispose();
					Reservation r = new Reservation();
					Jeu jeu = new Jeu(listExemplaires.get(index).getJeu().getId(), listExemplaires.get(index).getJeu().getNom(),
							listExemplaires.get(index).getJeu().isDispo(), listExemplaires.get(index).getJeu().getTarif(),
							listExemplaires.get(index).getJeu().getDateTarif(), listExemplaires.get(index).getJeu().getConsole());
					java.util.Date date = new java.util.Date();
					Exemplaire exemplaire = new Exemplaire(jeu);
					exemplaire = exemplaire.findExemplaireByIdJeu(jeu);
					Preteur preteur = new Preteur();
					preteur.setId(currentEmprunteur.getId());
					if (exemplaire.isLastExemplaire(listExemplaires.get(index).getJeu(), preteur))
					{
						System.out.println("C'est le dernier jeu");

					} else 
					{
						exemplaire.update(exemplaire);
					}
					exemplaire.setJeu(jeu);
					Reservation reservation = new Reservation(new Timestamp(date.getTime()), jeu);
					reservation.setId(-1);
					Pret pret = new Pret(dateDebut, dateFin,currentEmprunteur);
					Emprunteur emprunteur = new Emprunteur();
					emprunteur = emprunteur.findIdByEmprunteurEmail(currentEmprunteur);
					reservation.createReservation(reservation, emprunteur);
					int lastId = reservation.RecoverLastId();
					reservation.setId(lastId);
					reservation.create_Ligne_Reservation(reservation, jeu);
					Pret p = new Pret();
					p.create(pret, emprunteur, exemplaire, reservation);
					Passer_Reservation passer_Reservation = new Passer_Reservation(currentEmprunteur);
					passer_Reservation.setVisible(true);
					passer_Reservation.setResizable(false);
				}
			}
		});

		listJeux.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				int index = listJeux.getSelectedIndex();
				if (currentEmprunteur.getUnite() < listExemplaires.get(index).getJeu().getTarif()) {
					lblMsgError.setText("Vous n'avez pas assez d'unité pour réserver ce jeu.");
					btnReservation.setEnabled(false);
				} else {
					lblMsgError.setText("");
					btnReservation.setEnabled(true);
				}
			}
		});

		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Dashboard_Emprunteur dashboard_Emprunteur = new Dashboard_Emprunteur(currentEmprunteur);
				dashboard_Emprunteur.setVisible(true);
				dashboard_Emprunteur.setResizable(false);
			}
		});
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		setBackground(Color.WHITE);
		setTitle("Projet pr\u00EAt de jeu - Réservation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 498);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblListeJeux = new JLabel("Liste de jeux disponibles");
		lblListeJeux.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblListeJeux.setBounds(10, 26, 199, 19);
		contentPane.add(lblListeJeux);

		Exemplaire e = new Exemplaire();
		listExemplaires = e.findAll(currentEmprunteur);

		Object[] donnees = new Object[listExemplaires.size()];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

		for (int i = 0; i < listExemplaires.size(); i++) {
			String dispo = " ";
			if (listExemplaires.get(i).getJeu().isDispo()) {
				dispo = "Disponible";
			} else {
				dispo = "Indisponible";
			}
			
			donnees[i] = listExemplaires.get(i).getNbrExemplaire() + " - " + listExemplaires.get(i).getJeu().getNom() + " - " + listExemplaires.get(i).getJeu().getConsole().getNom() + " - " + "Tarif : "
					+ listExemplaires.get(i).getJeu().getTarif() + " - " + simpleDateFormat.format(listExemplaires.get(i).getJeu().getDateTarif()) + " - "
					+ dispo;
		}

		JLabel lblDateDebut = new JLabel("Date d\u00E9but");
		lblDateDebut.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblDateDebut.setBounds(24, 319, 139, 19);
		contentPane.add(lblDateDebut);

        dateTextFieldDebut = new JFormattedTextField(formatter);
        dateTextFieldDebut.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        dateTextFieldDebut.setBounds(373, 319, 171, 19);
        contentPane.add(dateTextFieldDebut);

		try {
	        MaskFormatter dateMask;
			dateMask = new MaskFormatter("##/##/####");
	        dateMask.install(dateTextFieldDebut);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel lblDateFin = new JLabel("Date fin");
		lblDateFin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblDateFin.setBounds(24, 360, 139, 19);
		contentPane.add(lblDateFin);

        dateTextFieldFin = new JFormattedTextField(formatter);
        dateTextFieldFin.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        dateTextFieldFin.setBounds(373, 360, 171, 19);
        contentPane.add(dateTextFieldFin);
        
        try {
	        MaskFormatter dateMask;
			dateMask = new MaskFormatter("##/##/####");
	        dateMask.install(dateTextFieldFin);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		lblMsgError = new JLabel("");
		lblMsgError.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblMsgError.setForeground(Color.RED);
		lblMsgError.setBounds(135, 413, 345, 19);
		contentPane.add(lblMsgError);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 564, 214);
		contentPane.add(scrollPane);

		listJeux = new JList(donnees);
		listJeux.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setViewportView(listJeux);

		btnReservation = new JButton("R\u00E9server");
		btnReservation.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnReservation.setBounds(24, 409, 89, 23);
		contentPane.add(btnReservation);
		
		btnRetour = new JButton("Retour");
		btnRetour.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnRetour.setBounds(490, 409, 89, 23);
		contentPane.add(btnRetour);
	}

}
