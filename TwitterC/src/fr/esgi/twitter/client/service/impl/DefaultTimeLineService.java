package fr.esgi.twitter.client.service.impl;

import org.json.JSONArray;
import org.scribe.model.Verb;
import org.springframework.stereotype.Service;

import fr.esgi.twitter.client.consts.URLEnum;
import fr.esgi.twitter.client.error.TwitterException;
import fr.esgi.twitter.client.model.TimeLine;
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

	@Override
	/** {@inheritDoc} */
	public TimeLine getHomeTimeLine() throws TwitterException {

		return getTimeLine(URLEnum.STATUSES__HOME_TIMELINE);
	}

	@Override
	/** {@inheritDoc} */
	public TimeLine getUserTimeLine() throws TwitterException {

		return getTimeLine(URLEnum.STATUSES__USER_TIMELINE);
	}

	/**
	 * 
	 * @param url
	 * @return {@link TimeLine}
	 * @throws TwitterException
	 */
	private TimeLine getTimeLine(URLEnum url) throws TwitterException {

		if (!URLEnum.STATUSES__HOME_TIMELINE.equals(url) && !URLEnum.STATUSES__USER_TIMELINE.equals(url)) {
			throw new TwitterException("Can not get timeline");
		}

		TimeLine timeline = new TimeLine();

		timeline.load(new JSONArray(OAuthScribeUtils.getResponse(Verb.GET, url.getUrl()).getBody()));

		return timeline;
	}
}
