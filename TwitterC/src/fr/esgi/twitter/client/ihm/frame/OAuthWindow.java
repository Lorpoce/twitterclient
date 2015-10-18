package fr.esgi.twitter.client.ihm.frame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.inject.Inject;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.springframework.stereotype.Component;

import fr.esgi.twitter.client.error.TwitterException;
import fr.esgi.twitter.client.service.OAuthService;

@Component
public class OAuthWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	@Inject
	private OAuthService oAuthService;

	@Inject
	private MainWindow mainWindow;

	private JButton btnRequestCode;
	private JButton btnOk;
	private JTextField txtCode;

	/**
	 * Afficher la fenêtre
	 */
	public void open() {

		txtCode = new JTextField();
		txtCode.setText("Code");
		txtCode.setColumns(10);

		txtCode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Vider le text field lors du click
				txtCode.setText("");
			}
		});

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

	/**
	 * Création du bouton pour demander un code d'auth
	 */
	private void buildRequestCodeButton() {

		btnRequestCode = new JButton("Request code");

		btnRequestCode.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				oAuthService.ask();
			}
		});
	}

	/**
	 * Création du bouton OK pour s'auth sur Twitter
	 */
	private void buildOkButton() {

		btnOk = new JButton("OK");

		btnOk.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (txtCode.getText() != null) {

					String code = txtCode.getText().trim();

					try {

						// S'auth sur Twitter
						oAuthService.auth(code);

						// Fermer la fenêtre
						setVisible(false);
						dispose();

						// Ouvrir MainWindow
						mainWindow.open();

					} catch (TwitterException exception) {

						JOptionPane.showMessageDialog(btnOk, exception.getMessage());
					}
				}
			}
		});
	}
}
