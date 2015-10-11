package fr.esgi.twitter.client.model;

import org.json.JSONObject;

import lombok.Data;

/**
 * Représente un tweet
 * 
 * @author Benjamin
 *
 */
@Data
public class Tweet {

	private User user;

	private String text;

	public Tweet() {
	}

	public Tweet(JSONObject json) {

		setUser(new User(json.getJSONObject("user")));

		setText(json.getString("text"));
	}

}
