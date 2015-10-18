package fr.esgi.twitter.client.ihm.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import fr.esgi.twitter.client.model.Tweet;

/**
 * 
 * @author Benjamin
 *
 */
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
		setBorder(new TitledBorder(LineBorder.createGrayLineBorder(),
				value.getUser().getName() + " (@" + value.getUser().getScreenName() + ")"));
		setText(value.getText());
		setIcon(value.getUser().getProfileImage());

		return this;
	}

}
