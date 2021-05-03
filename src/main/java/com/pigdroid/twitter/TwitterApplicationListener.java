package com.pigdroid.twitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class TwitterApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterApplicationListener.class);

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		LOGGER.info("Startup event done!");
	}

}
