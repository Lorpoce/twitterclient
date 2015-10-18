package fr.esgi.twitter.client.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.scribe.model.Verb;
import org.springframework.stereotype.Service;

import fr.esgi.twitter.client.consts.URLEnum;
import fr.esgi.twitter.client.error.TwitterException;
import fr.esgi.twitter.client.model.CurrentUser;
import fr.esgi.twitter.client.service.UpdateStatusesService;
import fr.esgi.twitter.client.utils.OAuthScribeUtils;

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
	public void update(String tweet) throws TwitterException {

		String url = getUrl(tweet);

		if (url == null) {
			throw new TwitterException("Can not update status");
		}

		OAuthScribeUtils.getResponse(Verb.POST, url);
	}

	/**
	 * 
	 * @param tweet
	 * @return URL
	 */
	private String getUrl(String tweet) {

		try {

			return URLEnum.STATUSES__UPDATE.getUrl() + "?" + STATUS + "=" + URLEncoder.encode(tweet, "UTF-8");

		} catch (UnsupportedEncodingException e) {

			return null;
		}
	}

}
