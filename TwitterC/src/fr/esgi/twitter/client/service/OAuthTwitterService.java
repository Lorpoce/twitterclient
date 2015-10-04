package fr.esgi.twitter.client.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import fr.esgi.twitter.client.consts.Consumer;
import fr.esgi.twitter.client.utils.DesktopUtils;

public class OAuthTwitterService {

	private OAuthService service;
	private Token requestToken;

	public OAuthTwitterService() {

		service = new ServiceBuilder().provider(TwitterApi.class).apiKey(Consumer.KEY).apiSecret(Consumer.SECRET)
				.build();

		requestToken = service.getRequestToken();
	}

	/**
	 * Ouvre le navigateur et demande à Twitter pour que l'utilisateur autorise
	 * l'application
	 */
	public void ask() {

		try {

			DesktopUtils.openWebpage(new URI(service.getAuthorizationUrl(requestToken)));

		} catch (URISyntaxException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Demander à Twitter si l'utilisateur a autorisé l'application
	 * 
	 * @param code
	 * @return <code>true</code> si l'authentification s'est correctement
	 *         déroulée, <code>false</code> si non
	 */
	public boolean auth(String code) {

		try {

			Verifier verifier = new Verifier(code);

			Token accessToken = service.getAccessToken(requestToken, verifier);

			// FIXME : Juste pour tester
			OAuthRequest request = new OAuthRequest(Verb.GET,
					"https://api.twitter.com/1.1/account/verify_credentials.json");
			service.signRequest(accessToken, request);
			Response response = request.send();
			System.out.println(response.getBody());
			//

			return true;

		} catch (OAuthException e) {

			return false;
		}
	}

}
