package fr.esgi.twitter.client.test;

import fr.esgi.twitter.client.service.impl.DefaultOAuthService;

public class OAuthServiceTest {

	@org.junit.Test
	public void test() {

		new DefaultOAuthService().ask();
	}

}
