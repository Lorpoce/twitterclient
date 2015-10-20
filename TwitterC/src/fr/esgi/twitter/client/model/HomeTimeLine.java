package fr.esgi.twitter.client.model;

import lombok.Getter;

public class HomeTimeLine {

	@Getter
	private static TimeLine instance = new TimeLine();
}
