package fr.esgi.twitter.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.esgi.twitter.client.ihm.frame.OAuthWindow;

public class Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

		ctx.register(AppConfig.class);
		ctx.refresh();

		ctx.getBean(OAuthWindow.class).open();

		ctx.close();
	}

}
