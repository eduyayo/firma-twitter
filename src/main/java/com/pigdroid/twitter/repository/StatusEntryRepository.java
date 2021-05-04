package com.pigdroid.twitter.repository;

import org.springframework.data.repository.CrudRepository;

import com.pigdroid.twitter.model.entity.StatusEntry;

public interface StatusEntryRepository extends CrudRepository<StatusEntry, Long> {

}
