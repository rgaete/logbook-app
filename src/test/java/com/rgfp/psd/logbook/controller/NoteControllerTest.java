package com.rgfp.psd.logbook.controller;

import com.rgfp.psd.logbook.domain.Note;
import com.rgfp.psd.logbook.service.NoteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NoteControllerTest {

    @Mock
    private NoteService noteService;

    @Captor
    private ArgumentCaptor<Note> noteCaptor;

    @InjectMocks
    private NoteController controller;

    private Long dummyId = 1l;
    private Model model;
    private Optional<Note> noteOptional;
    private Note note;

    @Before
    public void setup() {
        model = mock(Model.class);
        note = new Note();
        note.setTitle("Testing controller");
        noteOptional = Optional.of(note);
        when(noteService.findOne(dummyId)).thenReturn(noteOptional);
    }

    @Test
    public void noteViewShouldReturnTheNoteViewTemplate() {

        String view = controller.noteView(model, dummyId);

        assertEquals("noteView", view);

    }

    @Test
    public void noteViewShouldInjectTheNoteOnTheView() {

        controller.noteView(model, dummyId);

        verify(model).addAttribute(anyString() , noteCaptor.capture());

        Note capturedNote = noteCaptor.getValue();

        assertEquals(this.note.getTitle(), capturedNote.getTitle());

    }

    @Test
    public void noteViewShouldCallFindOneMethodOnService() {

        controller.noteView(model, dummyId);

        verify(noteService).findOne(dummyId);

    }



}