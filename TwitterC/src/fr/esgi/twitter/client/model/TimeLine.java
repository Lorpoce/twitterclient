package fr.esgi.twitter.client.model;

import java.util.Comparator;
import java.util.TreeSet;

import org.json.JSONArray;

import lombok.Getter;

/**
 * Représente une TimeLine
 * 
 * @author Benjamin
 *
 */
public class TimeLine {

	@Getter
	private TreeSet<Tweet> timeline = new TreeSet<Tweet>(new TweetComparator());

	@Getter
	private long minId;

	@Getter
	private long maxId;

	/**
	 * Charger la timeline
	 * 
	 * @param response
	 */
	public void load(JSONArray response) {

		for (int i = 0; i < response.length(); i++) {

			timeline.add(new Tweet(response.getJSONObject(i)));
		}

		maxId = timeline.first().getId();
		minId = timeline.last().getId();
	}

	/**
	 * Comparateur de Tweet
	 * 
	 * @author Benjamin
	 *
	 */
	private class TweetComparator implements Comparator<Tweet> {

		@Override
		public int compare(Tweet o1, Tweet o2) {

			return ((Long) o1.getId()).compareTo((Long) o2.getId()) * -1;
		}

	}

}
