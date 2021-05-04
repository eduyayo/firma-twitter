package com.pigdroid.twitter.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pigdroid.twitter.model.TagCount;
import com.pigdroid.twitter.model.entity.StatusEntry;
import com.pigdroid.twitter.model.entity.Tag;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TagRepositoryTest {

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private StatusEntryRepository statusEntryRepository;

	@Test
	void injectedComponentsAreNotNull() {
		assertEquals(0, tagRepository.count());
		assertEquals(0, statusEntryRepository.count());

		List<Tag> tags = new ArrayList<Tag>();
		tags.add(Tag.builder().value("t").build());
		tags.add(Tag.builder().value("r").build());
		tags.add(Tag.builder().value("r").build());
		tags.add(Tag.builder().value("r").build());
		tags.add(Tag.builder().value("r").build());
		StatusEntry satus = StatusEntry.builder().lang("es").tags(tags).build();
		statusEntryRepository.save(satus);
		Pageable pageable = PageRequest.of(0, 2);
		List<TagCount> found = tagRepository.countTags(pageable);
		assertEquals("r", found.get(0).getTagName());
		assertEquals("t", found.get(1).getTagName());
		assertEquals(4L, found.get(0).getAmount());
		assertEquals(1L, found.get(1).getAmount());

	}
}
