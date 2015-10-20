package fr.esgi.twitter.client.service;

import fr.esgi.twitter.client.error.TwitterException;
import fr.esgi.twitter.client.model.HomeTimeLine;

/**
 * Service pour gérer la TimeLine
 * 
 * @author Benjamin
 *
 */
public interface TimeLineService {

	/**
	 * Compléter la {@link HomeTimeLine} avec d'anciens tweets
	 * 
	 * @throws TwitterException
	 */
	void completeHomeTimeLineOldTweets() throws TwitterException;

	/**
	 * Compléter la {@link HomeTimeLine} avec de nouveaux tweets
	 * 
	 * @throws TwitterException
	 */
	void completeHomeTimeLineNewTweets() throws TwitterException;

	/**
	 * Initialiser {@link HomeTimeLine}
	 * 
	 * @throws TwitterException
	 */
	void initHomeTimeLine() throws TwitterException;
}
