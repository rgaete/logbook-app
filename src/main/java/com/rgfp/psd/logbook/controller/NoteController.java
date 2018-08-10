package com.rgfp.psd.logbook.controller;

import com.rgfp.psd.logbook.domain.Note;
import com.rgfp.psd.logbook.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;

    // displays all notes
    @RequestMapping(value={"/", "notes"})
    public String noteList(Model model, @RequestParam(required = false, name = "filter") String filter) {
        // filter feature not yet implemented
        List<Note> notes = noteService.findAll();
        model.addAttribute("noteList", notes);
        model.addAttribute("repeatedWords", noteService.getRepeatedWords());
        return "noteList";
    }

    // display one note with details
    @RequestMapping(value={"noteView/{id}"}, method = RequestMethod.GET)
    public String noteView(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("note", noteService.findOne(id).get());
        return "noteView";
    }

    // displays edit note template
    @RequestMapping(value={"/noteEdit","/noteEdit/{id}"}, method = RequestMethod.GET)
    public String noteEditForm(Model model, @PathVariable(required = false, name = "id") Long id) {
        if (null != id) {
            model.addAttribute("note", noteService.findOne(id));
        } else {
            model.addAttribute("note", new Note());
        }
        return "noteEdit";
    }

    // creates a new note
    @RequestMapping(value="/noteEdit", method = RequestMethod.POST)
    public String noteEdit(Model model, Note note) {
        noteService.saveNote(note);
        model.addAttribute("noteList", noteService.findAll());
        return "noteList";
    }

    // deletes a note
    @RequestMapping(value="/noteDelete/{id}", method = RequestMethod.GET)
    public String noteDelete(Model model, @PathVariable(required = true, name = "id") Long id) {
        noteService.deleteNote(id);
        model.addAttribute("noteList", noteService.findAll());
        return "noteList";
    }



    // not implemented, should clone a note. creating a new one
    @RequestMapping(value={"/noteClone","/noteClone/{id}"}, method = RequestMethod.GET)
    public String noteClone(Model model, @PathVariable(name = "id") Long id) {
        // not implemented

        noteService.cloneNote(id);

        model.addAttribute("noteList", noteService.findAll());
        return "noteList";

    }


}