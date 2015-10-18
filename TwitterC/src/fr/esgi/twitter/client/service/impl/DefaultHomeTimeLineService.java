package fr.esgi.twitter.client.service.impl;

import org.json.JSONArray;
import org.scribe.model.Verb;
import org.springframework.stereotype.Service;

import fr.esgi.twitter.client.consts.URLEnum;
import fr.esgi.twitter.client.error.TwitterException;
import fr.esgi.twitter.client.model.TimeLine;
import fr.esgi.twitter.client.service.HomeTimeLineService;
import fr.esgi.twitter.client.utils.OAuthScribeUtils;

/**
 * Service pour gérer la TimeLine
 * 
 * @author Benjamin
 *
 */
@Service
public class DefaultHomeTimeLineService implements HomeTimeLineService {

	@Override
	/** {@inheritDoc} */
	public TimeLine getTimeLine() throws TwitterException {

		TimeLine timeline = new TimeLine();

		timeline.load(new JSONArray(
				OAuthScribeUtils.getResponse(Verb.GET, URLEnum.STATUSES__HOME_TIMELINE.getUrl()).getBody()));

		return timeline;
	}
}
