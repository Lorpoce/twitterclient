package fr.esgi.twitter.client.task;

import java.util.TimerTask;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import fr.esgi.twitter.client.service.HomeTimeLineService;

@Component
public class HomeTimeLineTimerTask extends TimerTask {

	@Inject
	private HomeTimeLineService homeTimeLineService;

	@Override
	public void run() {
		homeTimeLineService.getTimeLine();
	}

}
