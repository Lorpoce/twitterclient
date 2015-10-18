package fr.esgi.twitter.client.model;

import java.util.ArrayList;
import java.util.List;

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
	private List<Tweet> timeline = new ArrayList<Tweet>();

	public void load(JSONArray response) {

		for (int i = 0; i < response.length(); i++) {

			timeline.add(new Tweet(response.getJSONObject(i)));

		}
	}

}
