package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.clases.Keyword;

class KeywordTest {

	@Test
	void test() {
		try {
			Keyword key = new Keyword("Contador");
			
			assertTrue(key.getClave().equals("Contador"));
			
			key.setClave("Necromancer");

			assertTrue(key.getClave().equals("Necromancer"));
		} catch(Exception e) {
			fail(e.getMessage());
			e.fillInStackTrace();
		}
	}

}
