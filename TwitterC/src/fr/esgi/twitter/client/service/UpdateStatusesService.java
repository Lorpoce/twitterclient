package fr.esgi.twitter.client.service;

import fr.esgi.twitter.client.model.CurrentUser;

/**
 * Service pour mettre à jour le status du {@link CurrentUser} (poster un tweet)
 * 
 * @author Benjamin
 *
 */
public interface UpdateStatusesService {

	/**
	 * Met à jour le status de l'utilisateur
	 * 
	 * @param tweet
	 * @return <code>true</code> si le tweet a bien été posté,
	 *         <code>false</code> si non
	 */
	boolean update(String tweet);

}
