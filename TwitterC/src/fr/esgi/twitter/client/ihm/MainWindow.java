package fr.esgi.twitter.client.ihm;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lombok.Getter;

public class MainWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	@Getter
	private JLabel icone; // Icone

	@Getter
	private JTextField tweet; // Tweet

	@Getter
	private JList<String> timeline; // TimeLine
	private JTextField txtTweet;
	private JButton btnUpdate;

	public MainWindow() {
		setTitle("Twitter SOA");

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(407, 727, 67, 23);
		btnUpdate.addActionListener(this);
		getContentPane().setLayout(null);
		getContentPane().add(btnUpdate);

		txtTweet = new JTextField();
		txtTweet.setBounds(71, 727, 326, 23);
		getContentPane().add(txtTweet);
		txtTweet.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Benjamin\\Desktop\\default_logo.png")); // FIXME
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBackground(Color.BLUE);
		lblNewLabel.setBounds(10, 702, 48, 48);
		getContentPane().add(lblNewLabel);

		JList list = new JList();
		list.setBounds(10, 11, 464, 680);
		getContentPane().add(list);

		JPanel timelinePlanel = new JPanel();

		setResizable(false);
		setSize(500, 800);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnUpdate)) {

		}

	}
}
