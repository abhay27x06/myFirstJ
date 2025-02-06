package com.springT.First.service;

import com.springT.First.entity.JournalEntry;
import com.springT.First.entity.User;
import com.springT.First.repository.JournalEntryRepository;
import com.springT.First.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;
    public JournalEntry saveEntry(JournalEntry journalEntry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Optional<User> user = userService.findByUserName(userName);
        if (user.isPresent()){
            journalEntryRepository.save(journalEntry);
            List <JournalEntry> allJournalEntries=user.get().getJournalEntries();
            allJournalEntries.add(journalEntry);
            user.get().setJournalEntries(allJournalEntries);
            userService.updateUserById(user.get().getId(), user.get());
        }
        return journalEntry;
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
