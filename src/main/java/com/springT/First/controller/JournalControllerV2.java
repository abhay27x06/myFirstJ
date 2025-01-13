package com.springT.First.controller;

import com.springT.First.entity.JournalEntry;
import com.springT.First.service.JournalEntryService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List <JournalEntry> all=journalEntryService.getAllEntry();
        if (!all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry newEntry){
        journalEntryService.saveEntry(newEntry);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteAll(){
        journalEntryService.deleteAllEntry();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/id/{myId}")
    public ResponseEntity<?> getEntryById(@PathVariable String myId){
        Optional<JournalEntry> entry=journalEntryService.getEntryById(myId);
        if (entry.isPresent()){
            return new ResponseEntity<JournalEntry>(entry.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable String myId){
        journalEntryService.deleteEntryById(myId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/id/{myId}")
    public ResponseEntity<?> updateEntryById(@PathVariable String myId, @RequestBody JournalEntry newEntry){
        journalEntryService.updateEntryById(myId, newEntry);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
