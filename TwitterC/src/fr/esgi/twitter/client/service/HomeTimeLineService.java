package fr.esgi.twitter.client.service;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;

import fr.esgi.twitter.client.consts.URLs;
import fr.esgi.twitter.client.model.TimeLine;
import fr.esgi.twitter.client.scribe.OAuthScribeTwitter;

/**
 * Service pour gérer la TimeLine
 * 
 * @author Benjamin
 *
 */
public abstract class HomeTimeLineService {

	/**
	 * 
	 * @return {@link TimeLine}
	 */
	public static TimeLine getTimeLine() {

		OAuthRequest request = new OAuthRequest(Verb.GET, URLs.STATUSES__HOME_TIMELINE);

		OAuthScribeTwitter.signRequest(request);

		Response response = request.send();

		TimeLine timeline = new TimeLine();

		if (response != null && response.getCode() == HttpStatus.SC_OK) {

			timeline.load(new JSONArray(response.getBody()));

		}

		return timeline;
	}
}
