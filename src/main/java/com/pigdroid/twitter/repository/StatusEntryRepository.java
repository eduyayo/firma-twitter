package com.pigdroid.twitter.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.pigdroid.twitter.model.entity.StatusEntry;

public interface StatusEntryRepository extends PagingAndSortingRepository<StatusEntry, Long> {

	Iterable<StatusEntry> findByUserNameAndValidated(String userName, Boolean validated);

	Iterable<StatusEntry> findByValidated(Boolean validated);

	Iterable<StatusEntry> findByUserName(String userName);

}
