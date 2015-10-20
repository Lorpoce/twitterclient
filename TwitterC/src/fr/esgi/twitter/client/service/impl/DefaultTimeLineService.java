package fr.esgi.twitter.client.service.impl;

import org.json.JSONArray;
import org.scribe.model.Verb;
import org.springframework.stereotype.Service;

import fr.esgi.twitter.client.consts.URLEnum;
import fr.esgi.twitter.client.error.TwitterException;
import fr.esgi.twitter.client.model.HomeTimeLine;
import fr.esgi.twitter.client.service.TimeLineService;
import fr.esgi.twitter.client.utils.OAuthScribeUtils;

/**
 * Service pour gérer la TimeLine
 * 
 * @author Benjamin
 *
 */
@Service
public class DefaultTimeLineService implements TimeLineService {

	private static final String max_id = "max_id";
	private static final String since_id = "since_id";

	@Override
	/** {@inheritDoc} */
	public void initHomeTimeLine() throws TwitterException {

		loadHomeTimeLine(URLEnum.STATUSES__HOME_TIMELINE.getUrl());
	}

	@Override
	/** {@inheritDoc} */
	public void completeHomeTimeLineOldTweets() throws TwitterException {

		loadHomeTimeLine(getUrl(false));
	}

	@Override
	/** {@inheritDoc} */
	public void completeHomeTimeLineNewTweets() throws TwitterException {

		loadHomeTimeLine(getUrl(true));
	}

	/**
	 * 
	 * @param newTweets
	 * @return URL pour avoir la timeline
	 */
	private String getUrl(boolean newTweets) {

		if (newTweets) {

			return URLEnum.STATUSES__HOME_TIMELINE.getUrl() + "?" + since_id + "="
					+ HomeTimeLine.getInstance().getMaxId();

		} else {

			return URLEnum.STATUSES__HOME_TIMELINE.getUrl() + "?" + max_id + "="
					+ HomeTimeLine.getInstance().getMinId();
		}
	}

	/**
	 * Charge la {@link HomeTimeLine}
	 * 
	 * @param url
	 * @throws TwitterException
	 */
	private void loadHomeTimeLine(String url) throws TwitterException {
		HomeTimeLine.getInstance().load(new JSONArray(OAuthScribeUtils.getResponse(Verb.GET, url).getBody()));
	}
}
