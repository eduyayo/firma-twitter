package com.pigdroid.twitter.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pigdroid.twitter.model.entity.StatusEntry;
import com.pigdroid.twitter.service.StatusEntryService;

@RestController
@RequestMapping("/status")
//TODO abstract query controllers like the pagination services
public class StatusEntryCommandController {

	@Autowired
	private StatusEntryService service;

	@PutMapping("/validate/{id}")
	public ResponseEntity<StatusEntry> validate(@PathVariable("id") long id) {
		return ResponseEntity.of(Optional.ofNullable(service.setValidated(id, true)));
	}

	@PutMapping("/invalidate/{id}")
	public ResponseEntity<StatusEntry> invalidate(@PathVariable("id") long id) {
		return ResponseEntity.of(Optional.ofNullable(service.setValidated(id, false)));
	}

}
