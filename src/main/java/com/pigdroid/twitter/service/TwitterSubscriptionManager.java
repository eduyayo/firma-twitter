package com.pigdroid.twitter.service;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@Component
public class TwitterSubscriptionManager {

	@Autowired
	private TwitterSubscriptionConfiguration twitterConfiguration;

	private TwitterStream twitterStream = null;

	private Consumer<Status> listener = null;

	public void startListening() {
		if (this.twitterStream == null) {
			synchronized (this) {
				if (this.twitterStream == null) {
					createTwitterStream();
				}
			}
		}
	}

	private void createTwitterStream() {
		this.twitterStream = new TwitterStreamFactory().getInstance().addListener(new StatusListener() {

			@Override
			public void onStatus(Status status) {
				if (listener != null) {
					if (isByUser(status) && isByLang(status)) {
						listener.accept(status);
					}
				}
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
			}

			@Override
			public void onStallWarning(StallWarning warning) {
			}

			@Override
			public void onException(Exception ex) {
				ex.printStackTrace();
			}
		}).sample();
	}

	public void subscribe(Consumer<Status> consumer) {
		this.listener = consumer;
	}

	private boolean isByUser(Status status) {
		return status.getUser().getFollowersCount() >= this.twitterConfiguration.getFollowersThreshold();
	}

	private boolean isByLang(Status status) {
		String lang = status.getLang();
		boolean ret = false;
		if (lang != null) {
			ret = twitterConfiguration.getLangs().contains(lang);
		}
		return ret;
	}

}
