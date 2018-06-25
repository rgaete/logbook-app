package com.rgfp.psd.logbook.service;

import com.rgfp.psd.logbook.domain.Note;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NoteServiceTest {

    private ArrayList<Note> notes;

    @Mock
    private NoteRepository noteRepositoryMock;

    private NoteService noteService;
    private Note n1;
    private Note n2;
    private Note n3;

    @Before
    public void setup() {
        notes = new ArrayList<>();
        n1 = new Note();
        n1.setId(1l);
        n1.setTitle("firstNote");
        n1.setContent("First note, with words java spring automation.");

        n2 = new Note();
        n2.setId(2l);
        n2.setTitle("secondNote");
        n2.setContent("Second note: with words java spring integration.");

        n3 = new Note();
        n3.setId(3l);
        n3.setTitle("thirdNote");
        n3.setContent("Third note! with words java, any more?");

        notes.add(n1);
        notes.add(n2);
        notes.add(n3);

        when(noteRepositoryMock.findAll()).thenReturn(notes);

        noteService = new NoteService();
        noteService.setNoteRepository(noteRepositoryMock);
    }

    @Test
    public void shouldSyncAllNotesWhenCallingFindAll() {
        // arrange
        // act
        noteService.findAll();
        // assert
        assertEquals(3, noteService.getAllNotes().size());
    }

    @Test
    public void shouldSyncAllNotesWhenCallingSaveNote() {
        // arrange
        Note note = new Note();
        note.setTitle("New Note Title");
        note.setContent("new note content");
        notes.add(note);

        // act
        noteService.saveNote(note);

        // assert
        assertEquals(4, noteService.getAllNotes().size());

        // tear down
        notes.remove(note);
    }

    @Test
    public void itShouldAutomaticallyAddATimestampWhenSavingANote() {
        // arrange
        Note note = new Note();
        note.setTitle("New Note Title");
        note.setContent("new note content");
        notes.add(note);

        // act
        noteService.saveNote(note);

        // assert
        int indexOf = noteService.getAllNotes().indexOf(note);
        Note savedNote = noteService.getAllNotes().get(indexOf);

        assertNotNull(savedNote.getTimestamp());

        // tear down
        notes.remove(note);
    }

    @Test
    public void shouldSyncAllNotesWhenCallingDeleteNote() {
        // arrange
        notes.remove(n2);

        // act
        noteService.deleteNote(2l);

        // assert
        assertEquals(2, noteService.getAllNotes().size());

        // tear down
        notes.add(n2);
    }


    @Test
    public void topWordsShouldReturnWordsRepeatedMoreThanTwice() {

        noteService.syncAllNotes();

        List<String> topWords = noteService.getRepeatedWords(2);

        assertNotEquals(-1, topWords.indexOf("note"));
        assertNotEquals(-1, topWords.indexOf("with"));
        assertNotEquals(-1, topWords.indexOf("words"));
        assertNotEquals(-1, topWords.indexOf("java"));

        assertEquals(-1, topWords.indexOf("spring"));
        assertEquals(-1, topWords.indexOf("first"));
        assertEquals(-1, topWords.indexOf("second"));
        assertEquals(-1, topWords.indexOf("third"));
        assertEquals(-1, topWords.indexOf("automation"));
        assertEquals(-1, topWords.indexOf("integration"));
        assertEquals(-1, topWords.indexOf("any"));
        assertEquals(-1, topWords.indexOf("more"));

    }

    @Test
    public void filterShouldReturnNotesOnlyWithTheSpecifiedWord() {
        noteService.syncAllNotes();

        List<Note> notes = noteService.findAllBy("spring");

        assertTrue(notes.contains(n1));
        assertTrue(notes.contains(n2));
        assertFalse(notes.contains(n3));
    }

}