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

    /**
     * Добавление новой заметки в БД.
     * @param note - заметка, которую нужно добавить.
     * @return - добавленная заметка.
     */
    public Note addNote(Note note) {
        return noteRepository.save(note);
    }

    /**
     * Получение всех заметок из БД.
     * @return - список всех заметок.
     */
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    /**
     * Получение заметки по её id.
     * @param id - id заметки.
     * @return - заметка с указанным id.
     */
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    /**
     * Обновление заметки по её id.
     * @param id - id заметки.
     * @param note - обновленная заметка.
     * @return - обновленная заметка.
     */
    public Note updateNoteById(Long id, Note note) {
        Note noteFromDB = noteRepository.findById(id).orElse(null);
        if (noteFromDB != null) {
            noteFromDB.setTitle(note.getTitle());
            noteFromDB.setContent(note.getContent());
            return noteRepository.save(noteFromDB);
        }
        return null;
    }

    /**
     * Удаление заметки по её id.
     * @param id - id заметки.
     */
    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }

    /**
     * Поиск заметки по её id.
     * @param id - id заметки.
     * @return - заметка с указанным id.
     */
    public Note findNoteById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }
}
