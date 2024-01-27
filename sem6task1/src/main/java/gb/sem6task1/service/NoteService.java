package gb.sem6task1.service;

import gb.sem6task1.model.Note;
import gb.sem6task1.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NoteService {
    private final NoteRepository noteRepository;

    public Note addNote(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    public Note updateNoteById(Long id, Note note) {
        Note noteFromDB = noteRepository.findById(id).orElse(null);
        if (noteFromDB != null) {
            noteFromDB.setTitle(note.getTitle());
            noteFromDB.setContent(note.getContent());
            return noteRepository.save(noteFromDB);
        }
        return null;
    }


    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }

    public Note findNoteById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }
}
