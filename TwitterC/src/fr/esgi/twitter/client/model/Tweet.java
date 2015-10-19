package fr.esgi.twitter.client.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Représente un tweet
 * 
 * @author Benjamin
 *
 */
@Data
@EqualsAndHashCode(of = "id")
public class Tweet {

	private User user;

	private String text;

	private Date creation;

	private long id;

	public Tweet() {
	}

	/**
	 * 
	 * @param json
	 */
	public Tweet(JSONObject json) {

		setUser(new User(json.getJSONObject("user")));

		setText(json.getString("text"));

		setId(json.getLong("id"));

		try {
			setCreation(new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH)
					.parse(json.getString("created_at")));
		} catch (JSONException | ParseException e) {
			setCreation(null);
		}
	}

}
