package fr.esgi.twitter.client.test;

import org.junit.Before;

import fr.esgi.twitter.client.service.OAuthTwitterService;

public class Test {

	private OAuthTwitterService oAuthTwitterService;

	@Before
	public void before() {
		oAuthTwitterService = new OAuthTwitterService();
	}

	@org.junit.Test
	public void test() {
		oAuthTwitterService.ask();
	}

}
