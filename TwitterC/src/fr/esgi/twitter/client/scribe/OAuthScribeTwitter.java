package fr.esgi.twitter.client.scribe;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import fr.esgi.twitter.client.consts.Consumer;
import lombok.Getter;
import lombok.Setter;

public class OAuthScribeTwitter {

	@Getter
	private OAuthService service;

	@Getter
	private Token requestToken;

	@Getter
	@Setter
	private Token accessToken;

	private static OAuthScribeTwitter INSTANCE = new OAuthScribeTwitter();

	private OAuthScribeTwitter() {

		service = new ServiceBuilder().provider(TwitterApi.class).apiKey(Consumer.KEY).apiSecret(Consumer.SECRET)
				.build();

		requestToken = service.getRequestToken();
	}

	public static OAuthScribeTwitter getInstance() {
		return INSTANCE;
	}

	public static void signRequest(OAuthRequest request) {

		INSTANCE.getService().signRequest(INSTANCE.accessToken, request);
	}
}
