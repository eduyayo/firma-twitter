package com.pigdroid.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigdroid.twitter.model.entity.StatusEntry;
import com.pigdroid.twitter.repository.StatusEntryRepository;
import com.pigdroid.twitter.service.base.AbstractPagingService;

@Service
public class StatusEntryService extends AbstractPagingService<StatusEntry, Long> {

	@Autowired
	public StatusEntryService(StatusEntryRepository repo) {
		super(repo);
	}

}
