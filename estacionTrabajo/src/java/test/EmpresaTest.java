
package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import model.clases.Empresa;
import model.clases.Keyword;
import model.clases.Oferta;
import model.clases.TipoDeOferta;
import model.datatype.DtEmpresa;
import model.datatype.DtEmpresaCompleto;
import model.datatype.EstadoOferta;

class EmpresaTest {

	@Test
	void test_1() {
		Empresa em = new Empresa(
				"https://en.wikipedia.org/wiki/Null_pointer",
				"El peor error que cometio el ser humano",
				"Porfavor", 
				"ComoOdioEclipse", 
				"XX_Warrior_XX",
				"xxwarriorminecraft@hotmail.com",
				"",
				"contra"
		);
		assertTrue(em.getNombre().equals("Porfavor"));
		assertTrue(em.getApellido().equals("ComoOdioEclipse"));
		assertTrue(em.getNickname().equals("XX_Warrior_XX"));
		assertTrue(em.getCorreoElectronico().equals("xxwarriorminecraft@hotmail.com"));
		assertTrue(em.getDescripcion().equals("El peor error que cometio el ser humano"));
		assertTrue(em.getLink().equals("https://en.wikipedia.org/wiki/Null_pointer"));
		
		em.setLink("https://en.wikipedia.org/wiki/Bram_Moolenaar");
		
		assertTrue(em.getLink().equals("https://en.wikipedia.org/wiki/Bram_Moolenaar"));
	}

	@Test
	void test_2() {
		Empresa emp = new Empresa(
				"https://www.wordreference.com/es/translation.asp?tranword=suppose",
				"unaBuenaPalabra",
				"SISI", 
				"SUN", 
				"MOON",
				"solyluna@hotmail.com",
                "",
                "contra"
		);
		
		// creo una oferta dada un tipo.
		
		TipoDeOferta tdo = new TipoDeOferta(
				"Untipodeofertaespectacular", 
				"descripcion1", 
				12, 3, (float) 1, 
				LocalDate.now());
		
		Set<Keyword> keys = new HashSet<Keyword>();
		emp.crearOfertaPorEmpresa(
				tdo, 
				"ElNombreDeUnaOfertaPiola", 
				"descripcion2", 
				"12:00 - 13:10", 
				(float) 2000, 
				"Montevideo", 
				"Montevideo", 
				LocalDate.now(), 
				keys,
				"",
                EstadoOferta.Confirmada);
		
		// creo otra.
		tdo = new TipoDeOferta(
				"Epiquisimo", 
				"Impecable", 
				12, 3, (float) 1, 
				LocalDate.now());
		
		keys = new HashSet<Keyword>();
		emp.crearOfertaPorEmpresa(
				tdo, 
				"OtraOfertaImpecable", 
				"descripcion2", 
				"12:00 - 13:10", 
				(float) 2000, 
				"Montevideo", 
				"Montevideo", 
				LocalDate.now(), 
				keys,
                "",
                EstadoOferta.Confirmada);
		
		// obtengo los datos de las ofertas.
		
		Set<String> set = Set.of(emp.getNombreOfertas());
		assertTrue(set.contains("ElNombreDeUnaOfertaPiola"));
		assertTrue(set.contains("OtraOfertaImpecable"));
		
		// obtengo los datos de todo.
		
		DtEmpresaCompleto DtEC = emp.getDatosCompletos();
		DtEmpresa dtemp = DtEC.getEmpresa();
		
		assertTrue(dtemp.getNombre().equals("SISI"));
		assertTrue(dtemp.getApellido().equals("SUN"));
		assertTrue(dtemp.getNickname().equals("MOON"));
		assertTrue(dtemp.getCorreoElectronico().equals("solyluna@hotmail.com"));
		assertTrue(dtemp.getDescripcion().equals("unaBuenaPalabra"));
		assertTrue(dtemp.getLink().equals("https://www.wordreference.com/es/translation.asp?tranword=suppose"));
	}
	
	@Test
	void test_3() {
		Empresa emp = new Empresa(
				"https://www.wordreference.com/es/translation.asp?tranword=suppose",
				"unaBuenaPalabra",
				"SISI", 
				"SUN", 
				"MOON",
				"solyluna@hotmail.com",
                "",
                "contra"
		);
		
		// creo una oferta dada un tipo.
		
		TipoDeOferta tdo = new TipoDeOferta(
				"Untipodeofertaespectacular", 
				"descripcion1", 
				12, 3, (float) 1, 
				LocalDate.now());
		
		Set<Keyword> keys = new HashSet<Keyword>();
		emp.crearOfertaPorEmpresa(
				tdo, 
				"ElNombreDeUnaOfertaPiola", 
				"descripcion2", 
				"12:00 - 13:10", 
				(float) 2000, 
				"Montevideo", 
				"Montevideo", 
				LocalDate.now(), 
				keys,
				"",
                EstadoOferta.Confirmada);
		
		// creo otra.
		tdo = new TipoDeOferta(
				"Epiquisimo", 
				"Impecable", 
				12, 3, (float) 1, 
				LocalDate.now());
		
		keys = new HashSet<Keyword>();
		emp.crearOfertaPorEmpresa(
				tdo, 
				"OtraOfertaImpecable", 
				"descripcion2", 
				"12:00 - 13:10", 
				(float) 2000, 
				"Montevideo", 
				"Montevideo", 
				LocalDate.now(), 
				keys,
                "",
                EstadoOferta.Confirmada);
		
		// obtengo los datos de las ofertas.
		
		Set<String> set = Set.of(emp.getNombreOfertas());
		assertTrue(set.contains("ElNombreDeUnaOfertaPiola"));
		assertTrue(set.contains("OtraOfertaImpecable"));
		
		// obtengo los datos de todo.
		
		DtEmpresaCompleto DtEC = emp.getDatosCompletos();
		DtEmpresa dtemp = DtEC.getEmpresa();
		
		assertTrue(dtemp.getNombre().equals("SISI"));
		assertTrue(dtemp.getApellido().equals("SUN"));
		assertTrue(dtemp.getNickname().equals("MOON"));
		assertTrue(dtemp.getCorreoElectronico().equals("solyluna@hotmail.com"));
		assertTrue(dtemp.getDescripcion().equals("unaBuenaPalabra"));
		assertTrue(dtemp.getLink().equals("https://www.wordreference.com/es/translation.asp?tranword=suppose"));
	}
}
