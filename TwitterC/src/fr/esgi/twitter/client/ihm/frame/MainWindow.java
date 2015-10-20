package fr.esgi.twitter.client.ihm.frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;
import javax.swing.BoundedRangeModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.springframework.stereotype.Component;

import fr.esgi.twitter.client.error.TwitterException;
import fr.esgi.twitter.client.ihm.renderer.TweetCellRenderer;
import fr.esgi.twitter.client.model.CurrentUser;
import fr.esgi.twitter.client.model.HomeTimeLine;
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

	private DefaultListModel<Tweet> tweets;
	private Timer timer;
	private JTextField txtTweet;

	/**
	 * Afficher la MainWindow
	 */
	public void open() {

		buildProfileImageIcon();
		buildTweetTextFieldUpdateButton();
		buildTimeLineList();

		getContentPane().setLayout(null);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Twitter SOA");
		// setLocationRelativeTo(null);
		setResizable(false);
		setSize(1000, 800);
		setVisible(true);

		initTimeLine();
		scheduleTimeLineLoading();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// Arrêter le timer lors de la fermeture
				timer.cancel();
				timer.purge();
			}
		});
	}

	/**
	 * Construire l'avatar de l'utilisateur
	 */
	private void buildProfileImageIcon() {

		JLabel lblProfileImage = new JLabel("");
		lblProfileImage.setIcon(CurrentUser.getInstance().getProfileImage());
		lblProfileImage.setForeground(Color.BLUE);
		lblProfileImage.setBackground(Color.BLUE);
		lblProfileImage.setBounds(10, 702, 48, 48);
		getContentPane().add(lblProfileImage);
	}

	/**
	 * Construire le bouton pour envoyer un tweet et le champs de texte tweet
	 */
	private void buildTweetTextFieldUpdateButton() {

		txtTweet = new JTextField();
		txtTweet.setColumns(10);
		txtTweet.setBounds(71, 727, 800, 23);
		getContentPane().add(txtTweet);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(907, 727, 80, 23);
		getContentPane().add(btnUpdate);

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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void buildTimeLineList() {

		JScrollPane jScrollPane = new JScrollPane();

		tweets = new DefaultListModel<Tweet>();

		JList listTimeline = new JList(tweets);
		listTimeline.setCellRenderer(new TweetCellRenderer());
		listTimeline.setVisibleRowCount(20);
		jScrollPane.setViewportView(listTimeline);

		jScrollPane.setBounds(10, 11, 980, 680);
		getContentPane().add(jScrollPane);

		jScrollPane.getVerticalScrollBar().getModel().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent event) {

				// Si on arrive à la fin des tweets, charger d'anciens tweets

				BoundedRangeModel model = (BoundedRangeModel) event.getSource();

				if ((model.getValue() + model.getExtent() == model.getMaximum()) && (!tweets.isEmpty())) {
					loadOldTweets();
				}
			}
		});

		listTimeline.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {

				if (evt.getClickCount() == 2) {

					// Si double tweet sur un tweet

					txtTweet.setText(
							"@" + tweets.getElementAt(((JList) evt.getSource()).locationToIndex(evt.getPoint()))
									.getUser().getScreenName() + " ");

				}
			}
		});
	}

	/**
	 * Compléter la DefaultListModel à partir de la {@link HomeTimeLine}
	 * 
	 * @param newTweets
	 *            : <code>true</code> pour que les nouveaux tweets apparaissent
	 *            au début de la liste, <code>false</code> s'ils doivent
	 *            apparaitre à la fin
	 */
	private void completeTweetsListModel(boolean newTweets) {

		/*
		 * Si newTweets, on prends la timeline à partir de la fin, sinon du
		 * début
		 */

		Set<Tweet> set = newTweets ? HomeTimeLine.getInstance().getTimeline().descendingSet()
				: HomeTimeLine.getInstance().getTimeline();

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				for (Tweet tweet : set) {

					if (!tweets.contains(tweet)) {

						// Si newTweets
						if (newTweets) {

							// Ajouter le tweet au début
							tweets.add(0, tweet);

						} else {

							// Ajouter le tweet à la fin
							tweets.addElement(tweet);
						}
					}
				}
			}
		});
	}

	/**
	 * Charger d'anciens tweets
	 */
	private void loadOldTweets() {

		try {

			homeTimeLineService.completeHomeTimeLineOldTweets();

			completeTweetsListModel(false);

		} catch (TwitterException e) {

			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	/**
	 * Initialiser la timeline
	 */
	private void initTimeLine() {

		try {

			homeTimeLineService.initHomeTimeLine();

			completeTweetsListModel(false);

		} catch (TwitterException e) {

			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	/**
	 * Charger la timeline avec de nouveaux tweets
	 */
	private void loadNewTweets() {

		try {

			homeTimeLineService.completeHomeTimeLineNewTweets();

			completeTweetsListModel(true);

		} catch (TwitterException e) {

			JOptionPane.showMessageDialog(this, e.getMessage());
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

				loadNewTweets();
			}
		}, 75000, 75000);
	}
}
