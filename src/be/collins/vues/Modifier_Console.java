package be.collins.vues;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.collins.pojo.Administrateur;
import be.collins.pojo.Console;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.BevelBorder;
public class Modifier_Console extends JFrame {

	private static final long serialVersionUID = 5581557558046069998L;
	private JPanel contentPane;
	private JTextField textFieldNom;
	private JLabel labelMsgErreur;
	@SuppressWarnings("unused")
	private Console consoleAModifier;
	private Administrateur currentAdministrateur;
	private JButton btnModifierConsole;
	private JButton btnRetour;

	/**
	 * Create the frame.
	 */
	public Modifier_Console(Administrateur currentAdministrateur, Console consoleAModifier) {
		this.consoleAModifier = consoleAModifier;
		this.currentAdministrateur = currentAdministrateur;
		initComponents();
		createEvents();
	}

	private void createEvents() {
		// TODO Auto-generated method stub
		btnModifierConsole.addActionListener(new ActionListener() {
			public boolean champsVide() {
				boolean valid = true;
				if (textFieldNom.getText().isEmpty()) {
					labelMsgErreur.setText("Veuillez remplir tous les champs.");
					valid = false;
				}

				return valid;
			}

			public void actionPerformed(ActionEvent e) {
				if (champsVide()) {
					consoleAModifier.setNom(textFieldNom.getText());
					if (consoleAModifier.alreadyExist(consoleAModifier)) {
						labelMsgErreur.setText("Cette console existe déjà.");

					} else {
						consoleAModifier.update(consoleAModifier);
						dispose();
						Dashboard_Console dashboard_Console = new Dashboard_Console(currentAdministrateur);
						dashboard_Console.setVisible(true);
						dashboard_Console.setResizable(false);
					}
				}

			}
		});
		
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Dashboard_Console dashboard_Console = new Dashboard_Console(currentAdministrateur);
				dashboard_Console.setVisible(true);
				dashboard_Console.setResizable(false);

			}
		});
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		setBackground(Color.WHITE);
		setTitle("Projet pr\u00EAt de jeu - Modification Console");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 387, 200);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNom = new JLabel("Nom du console");
		lblNom.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNom.setBounds(44, 44, 130, 23);
		contentPane.add(lblNom);

		textFieldNom = new JTextField();
		textFieldNom.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textFieldNom.setBounds(201, 45, 130, 18);
		textFieldNom.setText(consoleAModifier.getNom());
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);

		labelMsgErreur = new JLabel("");
		labelMsgErreur.setForeground(Color.RED);
		labelMsgErreur.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		labelMsgErreur.setBounds(44, 121, 287, 31);
		contentPane.add(labelMsgErreur);

		btnModifierConsole = new JButton("Modifier");
		btnModifierConsole.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnModifierConsole.setBounds(44, 88, 113, 23);
		contentPane.add(btnModifierConsole);
		btnRetour = new JButton("Retour");
		btnRetour.setFont(new Font("Tahoma", Font.ITALIC, 12));
		btnRetour.setBounds(218, 88, 113, 23);
		contentPane.add(btnRetour);
	}

}
