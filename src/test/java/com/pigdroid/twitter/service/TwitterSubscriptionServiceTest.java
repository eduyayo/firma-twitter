package com.pigdroid.twitter.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.powermock.reflect.Whitebox;

import com.pigdroid.util.AbstractMockitoTest;

import twitter4j.Status;
import twitter4j.User;

public class TwitterSubscriptionServiceTest extends AbstractMockitoTest {

	@Spy
	private TwitterSubscriptionConfiguration twitterSubscriptionConfiguration = new TwitterSubscriptionConfiguration();

	@InjectMocks
	TwitterSubscriptionManager sot = new TwitterSubscriptionManager();

	@Test
	public void testCanFilterByDefaultLangs() throws Exception {
		Status status = Mockito.mock(Status.class);

		when(status.getLang()).thenReturn("ja");
		boolean ret = Whitebox.invokeMethod(sot, "isByLang", status);
		assertFalse(ret);

		when(status.getLang()).thenReturn("es");
		ret = Whitebox.invokeMethod(sot, "isByLang", status);
		assertTrue(ret);
		when(status.getLang()).thenReturn("fr");
		ret = Whitebox.invokeMethod(sot, "isByLang", status);
		assertTrue(ret);
		when(status.getLang()).thenReturn("it");
		ret = Whitebox.invokeMethod(sot, "isByLang", status);
		assertTrue(ret);
	}

	@Test
	public void testCanFilterByUserFollowersThreashold() throws Exception {
		Status status = Mockito.mock(Status.class);

		User user = Mockito.mock(User.class);
		when(user.getFollowersCount()).thenReturn(1499);
		when(status.getUser()).thenReturn(user);
		boolean ret = Whitebox.invokeMethod(sot, "isByUser", status);
		assertFalse(ret);

		when(user.getFollowersCount()).thenReturn(1500);
		ret = Whitebox.invokeMethod(sot, "isByUser", status);
		assertTrue(ret);
	}

}
