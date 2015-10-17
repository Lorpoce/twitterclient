package fr.esgi.twitter.client.model;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Data;

/**
 * Représente un utilisateur de Twitter
 * 
 * @author Benjamin
 *
 */
@Data
public class User {

	private String name;

	private String screenName;

	private String location;

	private String description;

	private URL url;

	private ImageIcon profileImage;

	public User() {
	}

	public User(JSONObject json) {

		setName(json.getString("name"));

		setScreenName(json.getString("screen_name"));

		setLocation(((json.has("location") && !json.isNull("location"))) ? json.getString("location") : null);

		setDescription(
				((json.has("description") && !json.isNull("description"))) ? json.getString("description") : null);

		initURLs(json);
	}

	private void initURLs(JSONObject json) {
		try {

			setUrl(((json.has("url") && !json.isNull("url"))) ? new URL(json.getString("url")) : null);

			setProfileImage(new ImageIcon(ImageIO.read(new URL(json.getString("profile_image_url")))));

		} catch (JSONException | IOException e) {

			e.printStackTrace();
		}
	}

}
