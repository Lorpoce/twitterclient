package fr.esgi.twitter.client.utils;

import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 
 * @author Benjamin
 *
 */
public abstract class DesktopUtils {

	/**
	 * Ouvrir le navigateur à partir d'une {@link URI}
	 * 
	 * @param uri
	 */
	public static void openWebpage(URI uri) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(uri);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Ouvrir le navigateur à partir d'une {@link URL}
	 * 
	 * @param url
	 */
	public static void openWebpage(URL url) {
		try {
			openWebpage(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
