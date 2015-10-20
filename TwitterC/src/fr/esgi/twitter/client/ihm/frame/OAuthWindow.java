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
import javax.swing.WindowConstants;

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

	private JTextField txtCode;
	private JButton btnOk;

	/**
	 * Afficher la fenêtre
	 */
	public void open() {

		buildRequestCodeButton();
		buildCodeTextFieldOkButton();

		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(200, 100);
		setVisible(true);
	}

	/**
	 * Création du bouton pour demander un code d'auth
	 */
	private void buildRequestCodeButton() {

		JButton btnRequestCode = new JButton("Request code");
		getContentPane().add(btnRequestCode);

		btnRequestCode.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				oAuthService.ask();

				txtCode.setEditable(true);
				btnOk.setEnabled(true);
			}
		});
	}

	/**
	 * Création du champs de texte du code et du bouton OK pour s'auth sur
	 * Twitter
	 */
	private void buildCodeTextFieldOkButton() {

		txtCode = new JTextField();
		txtCode.setText("Code");
		txtCode.setColumns(10);
		txtCode.setEditable(false);
		txtCode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Vider le text field lors du click
				txtCode.setText("");
			}
		});
		getContentPane().add(txtCode);

		btnOk = new JButton("OK");
		btnOk.setEnabled(false);
		getContentPane().add(btnOk);

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
