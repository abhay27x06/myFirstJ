package com.springT.First.controller;

import com.springT.First.entity.JournalEntry;
import com.springT.First.entity.User;
import com.springT.First.service.JournalEntryService;
import com.springT.First.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
        Optional<User> user = userService.findByUserName(userName);
        if (user.isPresent()){
            List <JournalEntry> all=user.get().getJournalEntries();
            if (!all.isEmpty()){
                return new ResponseEntity<>(all, HttpStatus.FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @Transactional
    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry newEntry){
           return journalEntryService.saveEntry(newEntry);
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
    @Transactional
    @DeleteMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable String userName, @PathVariable String myId){
        Optional<JournalEntry> entry=journalEntryService.getEntryById(myId);
        if (entry.isPresent()){
            journalEntryService.deleteEntryById(myId);
            Optional<User> user = userService.findByUserName(userName);
            if (user.isPresent()){
                user.get().getJournalEntries().remove(entry.get());
                userService.saveUser(user.get());
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @Transactional
    @PutMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> updateEntryById(@PathVariable String myId, @RequestBody JournalEntry newEntry, @PathVariable String userName){
        Optional<User> user = userService.findByUserName(userName);
        Optional<JournalEntry> entry = journalEntryService.getEntryById(myId);
        if (user.isPresent() && entry.isPresent()){
            user.get().getJournalEntries().removeIf(x -> x.getId().equals(entry.get().getId()));
            entry.get().setTitle(newEntry.getTitle());
            entry.get().setContent(newEntry.getContent());
            user.get().getJournalEntries().add(entry.get());
            userService.saveUser(user.get());
            journalEntryService.updateEntryById(myId, entry.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
