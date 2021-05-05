package com.pigdroid.twitter.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pigdroid.twitter.model.entity.StatusEntry;
import com.pigdroid.twitter.service.StatusEntryService;

@RestController
@RequestMapping("/status")
//TODO abstract query controllers like the pagination services
public class StatusEntryQueryController {

	@Autowired
	private StatusEntryService service;

	@GetMapping("/")
	public ResponseEntity<Iterable<StatusEntry>> get() {
		return ResponseEntity.of(Optional.ofNullable(service.findAll()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<StatusEntry> get(@PathVariable("id") long id) {
		return ResponseEntity.of(service.findById(id));
	}

}
