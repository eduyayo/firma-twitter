package com.pigdroid.twitter.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigdroid.twitter.model.entity.StatusEntry;
import com.pigdroid.twitter.model.entity.Tag;
import com.pigdroid.twitter.repository.StatusEntryRepository;
import com.pigdroid.twitter.service.base.AbstractPagingService;

import twitter4j.HashtagEntity;
import twitter4j.Status;

@Service
public class StatusEntryService extends AbstractPagingService<StatusEntry, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusEntryService.class);

	@Autowired
	public StatusEntryService(StatusEntryRepository repo) {
		super(repo);
	}

	public void createFrom(Status status) {
		HashtagEntity[] hts = status.getHashtagEntities();
		List<Tag> tags = Collections.emptyList();
		if (hts != null && hts.length > 0) {
			tags = Arrays.stream(hts).map(each -> each.getText()).map(each -> Tag.builder()
							.value(each)
							.build()).collect(Collectors.toList());
		}
		StatusEntry statusEntry = StatusEntry.builder()
				.lang(status.getLang())
				.tags(tags)
				.build();
		save(statusEntry);
	}

	public StatusEntry setValidated(long id, boolean validated) {
		StatusEntry entry = findById(id).orElse(null);
		entry.setValidated(validated);
		save(entry);
		return entry;
	}

}
