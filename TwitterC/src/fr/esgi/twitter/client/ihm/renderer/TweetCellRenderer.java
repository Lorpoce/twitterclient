package fr.esgi.twitter.client.ihm.renderer;

import java.awt.Color;
import java.awt.Component;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import fr.esgi.twitter.client.model.Tweet;

public class TweetCellRenderer extends JLabel implements ListCellRenderer<Tweet> {
	private static final long serialVersionUID = 1L;

	public TweetCellRenderer() {
		setOpaque(true);
		setIconTextGap(12);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Tweet> list, Tweet value, int index,
			boolean isSelected, boolean cellHasFocus) {

		try {

			setText(value.getText());
			setIcon(new ImageIcon(ImageIO.read(value.getUser().getProfileImageUrl())));

			if (isSelected) {
				setBackground(Color.blue);
				setForeground(Color.white);
			} else {
				setBackground(Color.white);
				setForeground(Color.black);
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		return this;
	}

}
