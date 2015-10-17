package fr.esgi.twitter.client.service;

import fr.esgi.twitter.client.model.CurrentUser;

/**
 * Service pour mettre � jour le status du {@link CurrentUser} (poster un tweet)
 * 
 * @author Benjamin
 *
 */
public interface UpdateStatusesService {

	/**
	 * Met � jour le status de l'utilisateur
	 * 
	 * @param tweet
	 * @return <code>true</code> si le tweet a bien �t� post�,
	 *         <code>false</code> si non
	 */
	boolean update(String tweet);

}
