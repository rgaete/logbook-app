package com.rgfp.psd.logbook;

import com.rgfp.psd.logbook.service.NoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogbookApplicationTests {

	@Autowired
	private NoteService noteService;

	@Test
	public void contextLoads() {
		assertNotNull(noteService);
	}

}
