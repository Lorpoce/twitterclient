package fr.esgi.twitter.client.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import lombok.Getter;

/**
 * Repr�sente une TimeLine
 * 
 * @author Benjamin
 *
 */
public class TimeLine {

	@Getter
	private List<Tweet> timeline;

	public void load(JSONArray response) {

		timeline = new ArrayList<Tweet>();

		for (int i = 0; i < response.length(); i++) {

			timeline.add(new Tweet(response.getJSONObject(i)));

		}
	}

}
