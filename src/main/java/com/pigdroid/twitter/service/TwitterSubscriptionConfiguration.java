package com.pigdroid.twitter.service;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Configuration
@ConfigurationProperties(prefix = "twitter.subscription")

@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class TwitterSubscriptionConfiguration {

	@Builder.Default
	@Getter
	private Set<String> langs = new HashSet<>(Arrays.asList("es", "fr", "it"));

	@Builder.Default
	@Getter
	private int followersThreshold = 1500;

	@Builder.Default
	@Getter
	private int maxTagsToShow = 10;


}
