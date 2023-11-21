package com.authentication.inventoryauthservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @GetMapping
    public List<Note> getAllNotes() {
        return this.noteService.getNotes();
    }

    @PostMapping
    public ResponseEntity<Object> createNote(@RequestBody Note note){
        return this.noteService.newNote(note);

    }

    @PutMapping
    public ResponseEntity<Object> updateNote(@RequestBody Note note){
        return this.noteService.updateNote(note);

    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteNote(@PathVariable("id") Long id){
        return this.noteService.deleteNote(id);

    }


}
