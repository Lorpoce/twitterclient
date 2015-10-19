package fr.esgi.twitter.client.ihm.frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.springframework.stereotype.Component;

import fr.esgi.twitter.client.error.TwitterException;
import fr.esgi.twitter.client.ihm.renderer.TweetCellRenderer;
import fr.esgi.twitter.client.model.CurrentUser;
import fr.esgi.twitter.client.model.TimeLine;
import fr.esgi.twitter.client.model.Tweet;
import fr.esgi.twitter.client.service.TimeLineService;
import fr.esgi.twitter.client.service.UpdateStatusesService;

@Component
public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	@Inject
	private UpdateStatusesService updateStatusesService;

	@Inject
	private TimeLineService homeTimeLineService;

	@SuppressWarnings("rawtypes")
	private JList listTimeline;
	private JLabel lblProfileImage;
	private JButton btnUpdate;
	private JTextField txtTweet;
	private DefaultListModel<Tweet> tweets;
	private Timer timer;

	/**
	 * Afficher la MainWindow
	 */
	public void open() {

		buildProfileImageIcon();
		buildUpdateButton();
		buildTimeLineList();

		txtTweet = new JTextField();
		txtTweet.setColumns(10);
		txtTweet.setBounds(71, 727, 800, 23);

		getContentPane().setLayout(null);
		getContentPane().add(btnUpdate);
		getContentPane().add(lblProfileImage);
		getContentPane().add(txtTweet);

		setTitle("Twitter SOA");
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(1000, 800);
		setVisible(true);

		loadTimeLine();
		scheduleTimeLineLoading();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				timer.cancel();
				timer.purge();
			}
		});
	}

	/**
	 * Construire l'avatar de l'utilisateur
	 */
	private void buildProfileImageIcon() {

		lblProfileImage = new JLabel("");
		lblProfileImage.setIcon(CurrentUser.getInstance().getProfileImage());
		lblProfileImage.setForeground(Color.BLUE);
		lblProfileImage.setBackground(Color.BLUE);
		lblProfileImage.setBounds(10, 702, 48, 48);
	}

	/**
	 * Construire le bouton pour envoyer un tweet
	 */
	private void buildUpdateButton() {

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(907, 727, 80, 23);

		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (txtTweet.getText() != null) {

					String tweet = txtTweet.getText().trim();

					if (tweet.length() > 0 && tweet.length() <= 140) {

						try {

							updateStatusesService.update(txtTweet.getText());

							txtTweet.setText("");

							JOptionPane.showMessageDialog(btnUpdate, "Tweet posted.");

						} catch (TwitterException exception) {

							JOptionPane.showMessageDialog(btnUpdate, exception.getMessage());
						}

					} else {

						JOptionPane.showMessageDialog(btnUpdate,
								"You must fill the text field with 1 to 140 characters.");
					}
				}
			}
		});
	}

	/**
	 * Construire la liste des tweets
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void buildTimeLineList() {

		JScrollPane jScrollPane = new JScrollPane();

		tweets = new DefaultListModel<Tweet>();

		listTimeline = new JList(tweets);
		listTimeline.setCellRenderer(new TweetCellRenderer());
		listTimeline.setVisibleRowCount(20);
		jScrollPane.setViewportView(listTimeline);

		jScrollPane.setBounds(10, 11, 980, 680);
		getContentPane().add(jScrollPane);
	}

	/**
	 * Charger la timeline
	 */
	private void loadTimeLine() {

		TimeLine timeline;

		try {
			// Récupérer la timeline
			timeline = homeTimeLineService.getHomeTimeLine();

		} catch (TwitterException e) {

			JOptionPane.showMessageDialog(this, e.getMessage());

			return;
		}

		// Vider la liste
		tweets.clear();

		for (Tweet tweet : timeline.getTimeline()) {

			tweets.addElement(tweet);
		}
	}

	/**
	 * Programmer le rechargement de la timeline toutes les 75s
	 */
	private void scheduleTimeLineLoading() {

		timer = new Timer();

		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {

				loadTimeLine();
			}
		}, 75000, 75000);
	}
}
