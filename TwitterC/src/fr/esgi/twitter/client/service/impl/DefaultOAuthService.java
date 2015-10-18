package fr.esgi.twitter.client.service.impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONObject;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.springframework.stereotype.Service;

import fr.esgi.twitter.client.consts.URLEnum;
import fr.esgi.twitter.client.error.TwitterException;
import fr.esgi.twitter.client.model.CurrentUser;
import fr.esgi.twitter.client.service.OAuthService;
import fr.esgi.twitter.client.utils.DesktopUtils;
import fr.esgi.twitter.client.utils.OAuthScribeUtils;

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

			DesktopUtils.openWebpage(new URI(OAuthScribeUtils.getInstance().getService()
					.getAuthorizationUrl(OAuthScribeUtils.getInstance().getRequestToken())));

		} catch (URISyntaxException e) {

			e.printStackTrace();
		}
	}

	@Override
	/** {@inheritDoc} */
	public void auth(String code) throws TwitterException {

		try {

			OAuthScribeUtils.getInstance().setAccessToken(OAuthScribeUtils.getInstance().getService()
					.getAccessToken(OAuthScribeUtils.getInstance().getRequestToken(), new Verifier(code)));

		} catch (OAuthException e) {

			throw new TwitterException("Can not auth to Twitter. Please check the code.");
		}

		// Initialiser CurrentUser
		CurrentUser.init(new JSONObject(
				OAuthScribeUtils.getResponse(Verb.GET, URLEnum.ACCOUNT__VERIFY_CREDENTIALS.getUrl()).getBody()));
	}

}
