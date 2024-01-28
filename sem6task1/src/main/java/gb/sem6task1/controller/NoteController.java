package gb.sem6task1.controller;

import gb.sem6task1.model.Note;
import gb.sem6task1.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NoteController {
    private final NoteService noteService;

    /**
     * Обработка запроса на получение всех заметок
     * @return Список заметок или пустой список, если заметок нет
     */
    @GetMapping("/notes")
    public List<Note> getNotes() {
        return noteService.getAllNotes();
    }

    /**
     * Обработка запроса на добавление новой заметки
     * @param note Новая заметка для добавления в БД
     * @return Добавленная заметка или null, если заметка не добавлена в БД
     */
    @PostMapping("/notes/add")
    public Note addNote(@RequestBody Note note) {
        return noteService.addNote(note);
    }

    /**
     * Обработка запроса на получение заметки по её id
     * @param id id заметки для получения из БД
     * @return Заметка или null, если заметка не найдена в БД
     */
    @GetMapping("/notes/get/{id}")
    public Note getNote(@PathVariable  Long id) {
        return noteService.getNoteById(id);
    }

    /**
     * Обработка запроса на редактирование заметки по её id
     * @param id id заметки для редактирования в БД
     * @param note Новая заметка для редактирования в БД
     * @return  Редактированная заметка или сообщение об ошибке, если заметка не найдена в БД или редактирование не удалось.
     */
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

    /**
     * Обработка запроса на удаление заметки по её id.
     * @param id id заметки для удаления из БД.
     */
    @DeleteMapping("/notes/delete/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteService.deleteNoteById(id);
    }



}
