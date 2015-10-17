package fr.esgi.twitter.client.service;

import fr.esgi.twitter.client.model.CurrentUser;

/**
 * Service pour s'authentifier sur Twitter. Initialise aussi le
 * {@link CurrentUser}
 * 
 * @author Benjamin
 *
 */
public interface OAuthService {

	/**
	 * Ouvre le navigateur et demande � Twitter pour que l'utilisateur autorise
	 * l'application
	 */
	void ask();

	/**
	 * Demander � Twitter si l'utilisateur a autoris� l'application
	 * 
	 * @param code
	 * @return <code>true</code> si l'authentification s'est correctement
	 *         d�roul�e, <code>false</code> si non
	 */
	boolean auth(String code);

}
