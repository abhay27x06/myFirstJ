package com.springT.First.service;

import com.springT.First.entity.JournalEntry;
import com.springT.First.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getAllEntry(){
        return journalEntryRepository.findAll();
    }
    public void deleteAllEntry(){
        journalEntryRepository.deleteAll();
    }
    public Optional<JournalEntry> getEntryById(String Id){
        return journalEntryRepository.findById(Id);
    }
    public void deleteEntryById(String Id){
        journalEntryRepository.deleteById(Id);
    }
    public void updateEntryById(String Id, JournalEntry newEntry){
        journalEntryRepository.save(newEntry);
    }

}
