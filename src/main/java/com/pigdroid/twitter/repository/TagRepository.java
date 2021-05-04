package com.pigdroid.twitter.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pigdroid.twitter.model.TagCount;
import com.pigdroid.twitter.model.entity.Tag;

public interface TagRepository extends CrudRepository<Tag, Long> {

	@Query("SELECT "
			+ "new com.pigdroid.twitter.model.TagCount(COUNT(t.value) as amount, t.value as tagName) "
			+ "FROM Tag AS t GROUP BY t.value ORDER BY COUNT(t.value) DESC ")
	List<TagCount> countTags(Pageable pageable);

}
