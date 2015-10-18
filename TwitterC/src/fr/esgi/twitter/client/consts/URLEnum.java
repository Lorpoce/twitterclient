package fr.esgi.twitter.client.consts;

import lombok.Getter;

/**
 * URLs
 * 
 * @author Benjamin
 *
 */
public enum URLEnum {

	/**
	 * GET account/verify_credentials
	 */
	ACCOUNT__VERIFY_CREDENTIALS("https://api.twitter.com/1.1/account/verify_credentials.json"),

	/**
	 * POST statuses/update
	 */
	STATUSES__UPDATE("https://api.twitter.com/1.1/statuses/update.json"),

	/**
	 * GET statuses/home_timeline
	 */
	STATUSES__HOME_TIMELINE("https://api.twitter.com/1.1/statuses/home_timeline.json");

	@Getter
	private String url;

	private URLEnum(String url) {
		this.url = url;
	}

}
