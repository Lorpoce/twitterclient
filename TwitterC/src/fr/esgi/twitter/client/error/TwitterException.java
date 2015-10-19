package fr.esgi.twitter.client.error;

import org.json.JSONObject;
import org.scribe.model.Response;

/**
 * Exception soulevée lorsqu'une requête HTTP est incorrecte
 * 
 * @author Benjamin
 *
 */
public class TwitterException extends Exception {
	private static final long serialVersionUID = 1L;

	public TwitterException(String response) {
		super(response);
	}

	public TwitterException(Response response) {
		this(getMessage(response));
	}

	private static String getMessage(Response response) {
		try {

			String message;

			if (response == null) {

				message = "No response...";

			} else {

				JSONObject error = new JSONObject(response.getBody()).getJSONArray("errors").getJSONObject(0);
				message = "Error " + error.getInt("code") + " : " + error.getString("message");
			}

			return message;

		} catch (Exception e) {

			return e.getMessage();
		}
	}
}
