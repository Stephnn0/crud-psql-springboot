package com.authentication.inventoryauthservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    public List<Note> getNotes(){
        return this.noteRepository.findAll();
    }

    public ResponseEntity<Object> newNote(Note note) {

//       Optional<Note> res = noteRepository.findNoteByName(note.getTitle());
//
//       if(res.isPresent()){
//           throw new IllegalStateException("Note already exists");
//       }

       noteRepository.save(note);
       return new ResponseEntity<>(note, HttpStatus.CREATED);

    }

    public ResponseEntity<Object> updateNote(Note note) {

        HashMap<String, Object> data = new HashMap<>();

        data.put("message", "Note updated");

        noteRepository.save(note);
        return new ResponseEntity<>(data, HttpStatus.OK);

    }


    public ResponseEntity<Object> deleteNote(Long id){
        boolean exists = this.noteRepository.existsById(id);

        HashMap<String, Object> data = new HashMap<>();

        if(!exists){
            data.put("error", true);
            data.put("message", "note with this id doesn't exists");
            return new ResponseEntity<>(data, HttpStatus.CONFLICT);

        }
        noteRepository.deleteById(id);
        data.put("message", "note deleted");
        return new ResponseEntity<>(data, HttpStatus.ACCEPTED);



    }


}
