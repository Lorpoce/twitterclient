package fr.esgi.twitter.client.ihm;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.esgi.twitter.client.model.User;
import fr.esgi.twitter.client.service.UpdateStatusesService;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel lblProfileImage;
	private JButton btnUpdate;
	private JTextField txtTweet;
	private JList<String> listTimeline;

	public MainWindow() {

		buildProfileImageIcon();
		builderUpdateButton();

		txtTweet = new JTextField();
		txtTweet.setColumns(10);
		txtTweet.setBounds(71, 727, 326, 23);

		listTimeline = new JList<String>();
		listTimeline.setBounds(10, 11, 464, 680);
		getContentPane().add(listTimeline);

		getContentPane().setLayout(null);
		getContentPane().add(btnUpdate);
		getContentPane().add(lblProfileImage);
		getContentPane().add(txtTweet);

		setTitle("Twitter SOA");
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(500, 800);
		setVisible(true);
	}

	private void buildProfileImageIcon() {

		try {

			lblProfileImage = new JLabel("");
			lblProfileImage.setIcon(new ImageIcon(ImageIO.read(User.getInstance().getProfileImageUrl())));
			lblProfileImage.setForeground(Color.BLUE);
			lblProfileImage.setBackground(Color.BLUE);
			lblProfileImage.setBounds(10, 702, 48, 48);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private void builderUpdateButton() {

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(407, 727, 67, 23);

		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (txtTweet.getText() != null) {

					String tweet = txtTweet.getText().trim();

					if (!tweet.isEmpty()) {

						if (UpdateStatusesService.update(txtTweet.getText())) {

							txtTweet.setText("");

							JOptionPane.showMessageDialog(btnUpdate, "Tweet posted.");

						} else {

							JOptionPane.showMessageDialog(btnUpdate,
									"An error occurred... I can not update your status.");
						}

					} else {

						JOptionPane.showMessageDialog(btnUpdate, "You must fill the text field.");
					}
				}
			}
		});
	}
}
