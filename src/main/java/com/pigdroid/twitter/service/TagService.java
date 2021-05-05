package com.pigdroid.twitter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pigdroid.twitter.model.TagCount;
import com.pigdroid.twitter.model.entity.Tag;
import com.pigdroid.twitter.repository.TagRepository;
import com.pigdroid.twitter.service.base.AbstractPagingService;

@Service
public class TagService extends AbstractPagingService<Tag, Long> {

	@Autowired
	private TwitterSubscriptionConfiguration conf;

	@Autowired
	public TagService(TagRepository repo) {
		super(repo);
	}

	public List<TagCount> getCounts() {
		TagRepository repo = (TagRepository) getRepository();
		Pageable pageable = PageRequest.of(0, conf.getMaxTagsToShow());
		return repo.countTags(pageable);
	}

}
