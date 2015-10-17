package fr.esgi.twitter.client.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpStatus;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.springframework.stereotype.Service;

import fr.esgi.twitter.client.consts.URLs;
import fr.esgi.twitter.client.model.CurrentUser;
import fr.esgi.twitter.client.scribe.OAuthScribeTwitter;
import fr.esgi.twitter.client.service.UpdateStatusesService;

/**
 * Service pour mettre à jour le status du {@link CurrentUser} (poster un tweet)
 * 
 * @author Benjamin
 *
 */
@Service
public class DefaultUpdateStatusesService implements UpdateStatusesService {

	private static final String STATUS = "status";

	@Override
	/** {@inheritDoc} */
	public boolean update(String tweet) {

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
	private String getUrl(String tweet) {

		try {

			return URLs.STATUSES__UPDATE + "?" + STATUS + "=" + URLEncoder.encode(tweet, "UTF-8");

		} catch (UnsupportedEncodingException e) {

			return null;
		}
	}

}
