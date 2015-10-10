package fr.esgi.twitter.client.model;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;

public class User {

	@Getter
	private String name;

	@Getter
	private String screenName;

	@Getter
	private String location;

	@Getter
	private String description;

	@Getter
	private URL url;

	@Getter
	private URL profileImageUrl;

	private static User INSTANCE = new User();

	private User() {
	}

	public static User getInstance() {
		return INSTANCE;
	}

	/**
	 * Initialiser l'utilisateur
	 * 
	 * @param response
	 */
	public static void init(JSONObject response) {

		INSTANCE.name = response.getString("name");

		INSTANCE.screenName = response.getString("screen_name");

		INSTANCE.location = ((response.has("location") && !response.isNull("location")))
				? response.getString("location") : null;

		INSTANCE.description = ((response.has("description") && !response.isNull("description")))
				? response.getString("description") : null;

		initURLs(response);
	}

	/**
	 * Initialiser les URLs de l'utilisateur
	 * 
	 * @param response
	 */
	private static void initURLs(JSONObject response) {
		try {

			INSTANCE.url = ((response.has("url") && !response.isNull("url"))) ? new URL(response.getString("url"))
					: null;

			INSTANCE.profileImageUrl = new URL(response.getString("profile_image_url"));

		} catch (MalformedURLException | JSONException e) {

			e.printStackTrace();
		}
	}
}
