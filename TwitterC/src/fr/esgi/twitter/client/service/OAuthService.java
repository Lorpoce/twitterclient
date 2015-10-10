package fr.esgi.twitter.client.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;

import fr.esgi.twitter.client.consts.URLs;
import fr.esgi.twitter.client.model.User;
import fr.esgi.twitter.client.scribe.OAuthScribeTwitter;
import fr.esgi.twitter.client.utils.DesktopUtils;

public abstract class OAuthService {

	/**
	 * Ouvre le navigateur et demande à Twitter pour que l'utilisateur autorise
	 * l'application
	 */
	public static void ask() {

		try {

			DesktopUtils.openWebpage(new URI(OAuthScribeTwitter.getInstance().getService()
					.getAuthorizationUrl(OAuthScribeTwitter.getInstance().getRequestToken())));

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
	public static boolean auth(String code) {

		try {

			OAuthScribeTwitter.getInstance().setAccessToken(OAuthScribeTwitter.getInstance().getService()
					.getAccessToken(OAuthScribeTwitter.getInstance().getRequestToken(), new Verifier(code)));

			return initTwitterUser();

		} catch (OAuthException e) {

			return false;
		}
	}

	/**
	 * Initialise {@link User}
	 */
	private static boolean initTwitterUser() {

		// Créer la requête
		OAuthRequest request = new OAuthRequest(Verb.GET, URLs.ACCOUNT__VERIFY_CREDENTIALS);

		OAuthScribeTwitter.signRequest(request);

		Response response = request.send();

		if (response != null && response.getCode() == HttpStatus.SC_OK) {

			// Initialiser l'utilisateur
			User.init(new JSONObject(response.getBody()));

			return true;
		}

		return false;
	}

}
