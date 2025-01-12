package com.springT.First.controller;

import com.springT.First.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalController {

    private Map<String, JournalEntry> journalEntries=new HashMap<>();
    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry newEntry){
        journalEntries.put(newEntry.getId(), newEntry);
        return true;
    }
    @DeleteMapping
    public boolean deleteAll(){
        journalEntries.clear();
        return true;
    }
    @GetMapping("/id/{myId}")
    public JournalEntry getEntryById(@PathVariable String myId){
        return journalEntries.get(myId);
    }
    @DeleteMapping("/id/{myId}")
    public JournalEntry deleteEntryById(@PathVariable String myId){
        return journalEntries.remove(myId);
    }
    @PutMapping("/id/{myId}")
    public boolean updateEntryById(@PathVariable String myId, @RequestBody JournalEntry newEntry){
        journalEntries.put(myId, newEntry);
        return true;
    }
}
