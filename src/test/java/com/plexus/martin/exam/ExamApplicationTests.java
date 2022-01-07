package com.plexus.martin.exam;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

class ExamApplicationTests {
		
	@BeforeEach
	void init() {
		 MockitoAnnotations.openMocks(this);
	}

	@Test
	void DemoTest() {
		assertTrue(Boolean.TRUE);
	}
	
}
