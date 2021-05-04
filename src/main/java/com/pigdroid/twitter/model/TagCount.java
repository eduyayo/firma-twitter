package com.pigdroid.twitter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class TagCount {

	@Getter
	@Setter
	private long amount;

	@Getter
	@Setter
	private String tagName;
}
