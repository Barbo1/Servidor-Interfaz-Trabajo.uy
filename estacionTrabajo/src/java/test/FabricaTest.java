package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.clases.Fabrica;
import model.interfacess.IcontroladorLaboral;
import model.interfacess.IcontroladorUsuario;

class FabricaTest {

	@Test
	void test() {
		try {
			Fabrica f = Fabrica.getInstance();
			
			IcontroladorLaboral cl = f.getIcontroladorLaboral();
			IcontroladorUsuario cu = f.getIControladorUsuario();
		} catch (Exception e) {
			fail(e.getMessage());
			e.setStackTrace(null);
		}
	}

}
