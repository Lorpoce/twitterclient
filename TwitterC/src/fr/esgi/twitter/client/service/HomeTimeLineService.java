package fr.esgi.twitter.client.service;

import java.util.concurrent.Future;

import fr.esgi.twitter.client.model.TimeLine;

/**
 * Service pour gérer la TimeLine
 * 
 * @author Benjamin
 *
 */
public interface HomeTimeLineService {

	/**
	 * 
	 * @return {@link TimeLine}
	 */
	Future<TimeLine> getTimeLine();
}
