package fr.esgi.twitter.client.ihm.frame;

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

import fr.esgi.twitter.client.ihm.renderer.TweetCellRenderer;
import fr.esgi.twitter.client.model.CurrentUser;
import fr.esgi.twitter.client.service.HomeTimeLineService;
import fr.esgi.twitter.client.service.UpdateStatusesService;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel lblProfileImage;
	private JButton btnUpdate;
	private JTextField txtTweet;
	private JList listTimeline; // FIXME

	public MainWindow() {

		buildProfileImageIcon();
		buildUpdateButton();
		buildTimeLineList();

		txtTweet = new JTextField();
		txtTweet.setColumns(10);
		txtTweet.setBounds(71, 727, 326, 23);

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
			lblProfileImage.setIcon(new ImageIcon(ImageIO.read(CurrentUser.getInstance().getProfileImageUrl())));
			lblProfileImage.setForeground(Color.BLUE);
			lblProfileImage.setBackground(Color.BLUE);
			lblProfileImage.setBounds(10, 702, 48, 48);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private void buildUpdateButton() {

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(407, 727, 67, 23);

		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (txtTweet.getText() != null) {

					String tweet = txtTweet.getText().trim();

					if (tweet.length() > 0 && tweet.length() <= 140) {

						if (UpdateStatusesService.update(txtTweet.getText())) {

							txtTweet.setText("");

							JOptionPane.showMessageDialog(btnUpdate, "Tweet posted.");

						} else {

							JOptionPane.showMessageDialog(btnUpdate,
									"An error occurred... I can not update your status.");
						}

					} else {

						JOptionPane.showMessageDialog(btnUpdate,
								"You must fill the text field with 1 to 140 characters.");
					}
				}
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void buildTimeLineList() {

		/*
		 * FIXME
		 */

		listTimeline = new JList(HomeTimeLineService.getTimeLine().getTimeline().toArray());
		listTimeline.setCellRenderer(new TweetCellRenderer());
		listTimeline.setVisibleRowCount(20);

		listTimeline.setBounds(10, 11, 464, 680);
		getContentPane().add(listTimeline);
	}
}
