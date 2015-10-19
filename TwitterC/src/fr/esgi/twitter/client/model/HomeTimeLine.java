package fr.esgi.twitter.client.model;

import lombok.Getter;

public class HomeTimeLine extends TimeLine {

	@Getter
	private static TimeLine instance = new HomeTimeLine();

	private HomeTimeLine() {
		super();
	}

}
