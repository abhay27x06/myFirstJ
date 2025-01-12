package com.springT.First.controller;

import com.springT.First.entity.JournalEntry;
import com.springT.First.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAllEntry();
    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry newEntry){
        journalEntryService.saveEntry(newEntry);
        return true;
    }
    @DeleteMapping
    public boolean deleteAll(){
        journalEntryService.deleteAllEntry();
        return true;
    }
    @GetMapping("/id/{myId}")
    public JournalEntry getEntryById(@PathVariable String myId){
        return journalEntryService.getEntryById(myId).orElse(null);
    }
    @DeleteMapping("/id/{myId}")
    public void deleteEntryById(@PathVariable String myId){
        journalEntryService.deleteEntryById(myId);
    }
    @PutMapping("/id/{myId}")
    public boolean updateEntryById(@PathVariable String myId, @RequestBody JournalEntry newEntry){
        journalEntryService.updateEntryById(myId, newEntry);
        return true;
    }
}
