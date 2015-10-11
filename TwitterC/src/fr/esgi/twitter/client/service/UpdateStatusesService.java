package fr.esgi.twitter.client.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpStatus;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;

import fr.esgi.twitter.client.consts.URLs;
import fr.esgi.twitter.client.model.CurrentUser;
import fr.esgi.twitter.client.scribe.OAuthScribeTwitter;

/**
 * Service pour mettre à jour le status du {@link CurrentUser} (poster un tweet)
 * 
 * @author Benjamin
 *
 */
public abstract class UpdateStatusesService {

	private static final String STATUS = "status";

	/**
	 * Met à jour le status de l'utilisateur
	 * 
	 * @param tweet
	 * @return <code>true</code> si le tweet a bien été posté,
	 *         <code>false</code> si non
	 */
	public static boolean update(String tweet) {

		OAuthRequest request = new OAuthRequest(Verb.POST, getUrl(tweet));

		OAuthScribeTwitter.signRequest(request);

		Response response = request.send();

		return response != null && response.getCode() == HttpStatus.SC_OK;
	}

	/**
	 * 
	 * @param tweet
	 * @return URL
	 */
	private static String getUrl(String tweet) {

		try {

			return URLs.STATUSES__UPDATE + "?" + STATUS + "=" + URLEncoder.encode(tweet, "UTF-8");

		} catch (UnsupportedEncodingException e) {

			return null;
		}
	}

}
