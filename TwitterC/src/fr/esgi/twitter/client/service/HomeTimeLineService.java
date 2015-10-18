package fr.esgi.twitter.client.service;

import fr.esgi.twitter.client.error.TwitterException;
import fr.esgi.twitter.client.model.TimeLine;

/**
 * Service pour gérer la TimeLine
 * 
 * @author Benjamin
 *
 */
public interface HomeTimeLineService {

	/**
	 * Charge la timeline
	 * 
	 * @return {@link TimeLine}
	 * @throws TwitterException
	 */
	TimeLine getTimeLine() throws TwitterException;
}
