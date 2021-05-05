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
		String truncated = truncate(status.getText());
		StatusEntry statusEntry = StatusEntry.builder()
				.lang(status.getLang())
				.tags(tags)
				.text(truncated)
				.userName(status.getUser().getName())
				.build();
		save(statusEntry);
	}

	private String truncate(String text) {
		return text.substring(0, Math.min(text.length(), StatusEntry.MAX_TEXT_TO_STORE));
	}

	public StatusEntry setValidated(long id, boolean validated) {
		StatusEntry entry = findById(id).orElse(null);
		entry.setValidated(validated);
		save(entry);
		return entry;
	}

	//TODO use specs and criteria if it gets more complex or they ask for pagination
	public Iterable<StatusEntry> find(String userName, Boolean validated) {
		StatusEntryRepository repo = (StatusEntryRepository) this.getRepository();
		if (validated != null) {
			if (userName != null) {
				return repo.findByUserNameAndValidated(userName, validated);
			} else {
				return repo.findByValidated(validated);
			}
		} else if (userName != null) {
			return repo.findByUserName(userName);
		} else {
			return repo.findAll();
		}
	}

}
