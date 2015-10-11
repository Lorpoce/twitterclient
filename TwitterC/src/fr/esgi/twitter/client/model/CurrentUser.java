package fr.esgi.twitter.client.model;

import org.json.JSONObject;

import lombok.Getter;

/**
 * Représente l'utilisateur actuel de l'application
 * 
 * @author Benjamin
 *
 */
public class CurrentUser extends User {

	@Getter
	private static User instance = null;

	/**
	 * Initialiser l'utilisateur
	 * 
	 * @param response
	 */
	public static void init(JSONObject response) {

		if (instance == null) {

			instance = new User(response);
		}
	}
}
