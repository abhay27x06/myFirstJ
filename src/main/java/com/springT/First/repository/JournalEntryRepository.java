package com.springT.First.repository;

import com.springT.First.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository <JournalEntry, String> {
}
