package fr.esgi.twitter.client.utils;

import org.apache.http.HttpStatus;
import org.scribe.model.Response;

import fr.esgi.twitter.client.error.TwitterException;

/**
 * 
 * @author Benjamin
 *
 */
public class ResponseUtils {

	/**
	 * 
	 * @param response
	 * @throws TwitterException
	 */
	public static void validateResponse(Response response) throws TwitterException {

		if (response == null || response.getCode() != HttpStatus.SC_OK) {

			throw new TwitterException(response);
		}
	}
}
