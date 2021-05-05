package com.pigdroid.twitter.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pigdroid.twitter.model.TagCount;
import com.pigdroid.twitter.service.TagService;

@RestController
@RequestMapping("/tags")
//TODO abstract query controllers like the pagination services
public class TagQueryController {

	@Autowired
	private TagService tagService;

	@GetMapping("/rank")
	public ResponseEntity<Iterable<TagCount>> getCounts() {
		return ResponseEntity.of(Optional.ofNullable(tagService.getCounts()));
	}

}
