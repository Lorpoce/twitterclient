package fr.esgi.twitter.client.service;

import fr.esgi.twitter.client.error.TwitterException;
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
	 * @throws TwitterException
	 */
	void update(String tweet) throws TwitterException;

}
