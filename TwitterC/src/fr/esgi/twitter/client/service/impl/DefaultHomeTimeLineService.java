package fr.esgi.twitter.client.service.impl;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.springframework.stereotype.Service;

import fr.esgi.twitter.client.consts.URLs;
import fr.esgi.twitter.client.model.TimeLine;
import fr.esgi.twitter.client.scribe.OAuthScribeTwitter;
import fr.esgi.twitter.client.service.HomeTimeLineService;

/**
 * Service pour gérer la TimeLine
 * 
 * @author Benjamin
 *
 */
@Service
public class DefaultHomeTimeLineService implements HomeTimeLineService {

	// @Async
	@Override
	/** {@inheritDoc} */
	public TimeLine getTimeLine() {

		OAuthRequest request = new OAuthRequest(Verb.GET, URLs.STATUSES__HOME_TIMELINE);

		OAuthScribeTwitter.signRequest(request);

		Response response = request.send();

		if (response != null && response.getCode() == HttpStatus.SC_OK) {

			TimeLine timeline = new TimeLine();

			timeline.load(new JSONArray(response.getBody()));

			return timeline;

		} else {

			return null;
		}
	}
}
