package fr.esgi.twitter.client.service.impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.springframework.stereotype.Service;

import fr.esgi.twitter.client.consts.URLs;
import fr.esgi.twitter.client.model.CurrentUser;
import fr.esgi.twitter.client.scribe.OAuthScribeTwitter;
import fr.esgi.twitter.client.service.OAuthService;
import fr.esgi.twitter.client.utils.DesktopUtils;

/**
 * Service pour s'authentifier sur Twitter. Initialise aussi le
 * {@link CurrentUser}
 * 
 * @author Benjamin
 *
 */
@Service
public class DefaultOAuthService implements OAuthService {

	@Override
	/** {@inheritDoc} */
	public void ask() {

		try {

			DesktopUtils.openWebpage(new URI(OAuthScribeTwitter.getInstance().getService()
					.getAuthorizationUrl(OAuthScribeTwitter.getInstance().getRequestToken())));

		} catch (URISyntaxException e) {

			e.printStackTrace();
		}
	}

	@Override
	/** {@inheritDoc} */
	public boolean auth(String code) {

		try {

			OAuthScribeTwitter.getInstance().setAccessToken(OAuthScribeTwitter.getInstance().getService()
					.getAccessToken(OAuthScribeTwitter.getInstance().getRequestToken(), new Verifier(code)));

			return initTwitterUser();

		} catch (OAuthException e) {

			return false;
		}
	}

	/**
	 * Initialise {@link CurrentUser}
	 */
	private boolean initTwitterUser() {

		// Créer la requête
		OAuthRequest request = new OAuthRequest(Verb.GET, URLs.ACCOUNT__VERIFY_CREDENTIALS);

		OAuthScribeTwitter.signRequest(request);

		Response response = request.send();

		if (response != null && response.getCode() == HttpStatus.SC_OK) {

			// Initialiser l'utilisateur
			CurrentUser.init(new JSONObject(response.getBody()));

			return true;
		}

		return false;
	}

}
