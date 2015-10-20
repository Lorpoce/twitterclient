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
	 *             si la réponse est <code>null</code> ou si son code est
	 *             différent de 200
	 */
	public static void validateResponse(Response response) throws TwitterException {

		if (response == null || response.getCode() != HttpStatus.SC_OK) {

			throw new TwitterException(response);
		}
	}
}
