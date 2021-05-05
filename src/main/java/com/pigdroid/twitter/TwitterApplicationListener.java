package com.pigdroid.twitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.pigdroid.twitter.service.StatusEntryService;
import com.pigdroid.twitter.service.TwitterSubscriptionManager;

@Component
public class TwitterApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterApplicationListener.class);

    @Autowired
    private TwitterSubscriptionManager twitterSubscriptionService;

    @Autowired
    private StatusEntryService statusEntryService;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		twitterSubscriptionService.startListening();
		twitterSubscriptionService.subscribe(statusEntryService::createFrom);
		LOGGER.info("Startup event done!");
	}

}
