package fr.esgi.twitter.client.utils;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import fr.esgi.twitter.client.consts.Consumer;
import fr.esgi.twitter.client.error.TwitterException;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Benjamin
 *
 */
public class OAuthScribeUtils {

	@Getter
	private OAuthService service;

	@Getter
	private Token requestToken;

	@Getter
	@Setter
	private Token accessToken;

	@Getter
	private static OAuthScribeUtils instance = new OAuthScribeUtils();

	private OAuthScribeUtils() {

		service = new ServiceBuilder().provider(TwitterApi.class).apiKey(Consumer.KEY).apiSecret(Consumer.SECRET)
				.build();

		requestToken = service.getRequestToken();
	}

	/**
	 * 
	 * @param request
	 */
	public static void signRequest(OAuthRequest request) {

		instance.getService().signRequest(instance.accessToken, request);
	}

	/**
	 * 
	 * @param verb
	 * @param url
	 * @return {@link OAuthRequest}
	 */
	public static OAuthRequest getRequestSigned(Verb verb, String url) {

		OAuthRequest request = new OAuthRequest(verb, url);

		signRequest(request);

		return request;
	}

	/**
	 * 
	 * @param verb
	 * @param url
	 * @return {@link Response}
	 * @throws TwitterException
	 *             si la response est <code>null</code> ou si son code est
	 *             différent de 200
	 */
	public static Response getResponse(Verb verb, String url) throws TwitterException {

		Response response = getRequestSigned(verb, url).send();

		ResponseUtils.validateResponse(response);

		return response;
	}
}
