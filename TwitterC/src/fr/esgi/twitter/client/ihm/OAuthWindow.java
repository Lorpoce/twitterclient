package fr.esgi.twitter.client.ihm;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import fr.esgi.twitter.client.service.OAuthTwitterService;

public class OAuthWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	private JButton btnRequestCode;
	private JButton btnOk;
	private JTextField txtCode;

	private OAuthTwitterService oAuthTwitterService;

	public OAuthWindow() {

		oAuthTwitterService = new OAuthTwitterService();

		txtCode = new JTextField();
		txtCode.setText("Code");
		txtCode.setColumns(10);

		buildRequestCodeButton();
		buildOkButton();

		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		getContentPane().add(btnRequestCode);
		getContentPane().add(txtCode);
		getContentPane().add(btnOk);

		setLocationRelativeTo(null);
		setResizable(false);
		setSize(200, 100);
		setVisible(true);
	}

	private void buildRequestCodeButton() {

		btnRequestCode = new JButton("Request code");

		btnRequestCode.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				oAuthTwitterService.ask();
			}
		});
	}

	private void buildOkButton() {

		btnOk = new JButton("OK");

		btnOk.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				/*
				 * Si le champ de texte du code est rempli et que
				 * l'authentification s'est correctement déroulée
				 */
				if (txtCode.getText() != null && txtCode.getText().length() > 0
						&& oAuthTwitterService.auth(txtCode.getText())) {

					// Fermer la fenêtre
					setVisible(false);
					dispose();

					// Ouvrir MainWindow
					new MainWindow();
				}
			}
		});
	}
}
