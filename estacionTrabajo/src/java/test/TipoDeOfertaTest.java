
package test;

import static org.junit.jupiter.api.Assertions.*;



import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import model.clases.TipoDeOferta;


public class TipoDeOfertaTest {
	@Test
	public void testTipoDeOferta() {
        LocalDate fecha1 = LocalDate.of(2023, 10, 1);
        LocalDate fecha2 = LocalDate.now();
        
        TipoDeOferta tdo = new TipoDeOferta(
                "TestTipoDeOferta",
                "Descripcion",
                1,
                1,
                1,
                fecha1
            );

        assertTrue(tdo.getNombre().equals("TestTipoDeOferta"));
		assertTrue(tdo.getDescripcion().equals("Descripcion"));
		assertTrue(tdo.getDuracion() == 1);
		assertTrue(tdo.getExposicion() == 1);
		assertTrue(tdo.getCosto() == 1);
		assertTrue(tdo.getFechaAlta().compareTo(fecha1) == 0);

		tdo.setNombre("ParticularmenteUnTipoDeOferta");
		tdo.setDescripcion("ParticularmenteUnaDescripcion");
		tdo.setDuracion(2);
		tdo.setExposicion(3);
		tdo.setFechaAlta(fecha2);
		tdo.setCosto(4);

		assertTrue(tdo.getNombre().equals("ParticularmenteUnTipoDeOferta"));
		assertTrue(tdo.getDescripcion().equals("ParticularmenteUnaDescripcion"));
		assertTrue(tdo.getDuracion() == 2);
		assertTrue(tdo.getExposicion() == 3);
		assertTrue(tdo.getFechaAlta().compareTo(fecha2) == 0);	
	}
}
