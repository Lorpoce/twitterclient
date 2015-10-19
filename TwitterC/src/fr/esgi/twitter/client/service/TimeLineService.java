package fr.esgi.twitter.client.service;

import fr.esgi.twitter.client.error.TwitterException;
import fr.esgi.twitter.client.model.TimeLine;

/**
 * Service pour gérer la TimeLine
 * 
 * @author Benjamin
 *
 */
public interface TimeLineService {

	/**
	 * Charge la HOME timeline
	 * 
	 * @return {@link TimeLine}
	 * @throws TwitterException
	 */
	TimeLine getHomeTimeLine() throws TwitterException;

	/**
	 * Charge la USER timeline
	 * 
	 * @return
	 * @throws TwitterException
	 */
	TimeLine getUserTimeLine() throws TwitterException;
}
