package com.rgfp.psd.logbook.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class NoteTest {

    @Test
    public void summaryShouldReturnAllTheContentWhenContentIsShorterThan240() {

        Note note = new Note();
        note.setId(1l);
        note.setTitle("Texto corto");
        String textoCorto = "uno dos tres cuatro cinco";
        note.setContent(textoCorto);

        assertEquals(textoCorto, note.getSummary());

    }

    @Test
    public void summaryShouldReturn240CharactersOfTheContentWhenContentIsLargerThan240() {

        Note note = new Note();
        note.setId(1l);
        note.setTitle("Del 1 al 40");
        note.setContent("uno dos tres cuatro cinco seis siete ocho nueve diez once doce trece catorce quince dieciséis diecisiete dieciocho diecinueve veinte veintiuno veintidós veintitrés veinticuatro veinticinco veintiséis veintisiete veintiocho veintinueve treinta Treinta-y-uno Treinta-y-dos Treinta-y-tres Treinta-y-cuatro Treinta-y-cinco treinta-y-seis treinta-y-siete treinta-y-ocho treinta-y-nueve cuarenta cuarenta-y-uno cuarenta-y-dos cuarenta-y-tres cuarenta-y-cuatro cuarenta-y-cinco cuarenta-y-seis cuarenta-y-siete cuarenta-y-ocho cuarenta-y-nueve cincuenta");

        String tweet = "uno dos tres cuatro cinco seis siete ocho nueve diez once doce trece catorce quince dieciséis diecisiete dieciocho diecinueve veinte veintiuno veintidós veintitrés veinticuatro veinticinco veintiséis veintisiete veintiocho veintinueve trei";

        assertEquals(tweet, note.getSummary());

    }

    @Test
    public void cloneShouldReturnANewNoteWithSameContentAndTitle() throws Exception {

        String title = "Texto";
        String content = "Texto para la nota";
        long id = 1l;

        Note note = new Note();
        note.setId(id);
        note.setTitle(title);
        note.setContent(content);

        Note clone = note.clone();

        assertEquals(title, clone.getTitle());
        assertEquals(content, clone.getContent());
        assertNotEquals(id, clone.getId());


    }
}