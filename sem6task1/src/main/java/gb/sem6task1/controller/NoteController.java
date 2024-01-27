package gb.sem6task1.controller;

import gb.sem6task1.model.Note;
import gb.sem6task1.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NoteController {
    private final NoteService noteService;
    @GetMapping("/notes")
    public List<Note> getNotes() {
        return noteService.getAllNotes();
    }

    @PostMapping("/notes/add")
    public Note addNote(@RequestBody Note note) {
        return noteService.addNote(note);
    }

    @GetMapping("/notes/get/{id}")
    public Note getNote(@PathVariable  Long id) {
        return noteService.getNoteById(id);
    }

    @PutMapping("/notes/edit/{id}")
    public ResponseEntity<?> editNote(@PathVariable Long id, @RequestBody Note note) {
        try {
            Note foundNote = noteService.findNoteById(id);
            if (foundNote == null) {
                throw new NoSuchElementException(String.format("Note number %s not found", id));
            }
            Note editedNote = noteService.updateNoteById(id, note);
            return ResponseEntity.ok(editedNote);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @DeleteMapping("/notes/delete/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteService.deleteNoteById(id);
    }



}
