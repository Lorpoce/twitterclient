package fr.esgi.twitter.client.ihm.renderer;

import java.awt.Color;
import java.awt.Component;

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

		setBackground(Color.white);
		setText(value.getText());
		setIcon(value.getUser().getProfileImage());

		return this;
	}

}
