
package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import model.clases.Keyword;
import model.clases.Empresa;
import model.clases.Oferta;
import model.clases.TipoDeOferta;
import model.datatype.DtOferta;
import model.datatype.EstadoOferta;

class OfertaTest {
	@Test
	void test() {
		LocalDate fecha = LocalDate.of(1970, 1, 1);
		
		TipoDeOferta tdo = new TipoDeOferta(
				"Untipodeofertaespectacular", 
				"descripcion1", 
				12, 3, (float) 1, 
				fecha);
		
		Empresa emp = new Empresa(
                "https://refactoring.guru/design-patterns/adapter",
                "este no es el mejor patron del mundo, pero esta bastante bien.",
                "Julian",
                "Guzman",
                "Taladrator 3000",
                "Dartagnan@musketeer.com",
                "",
                "contra");
		
		Set<Keyword> keys = new HashSet<Keyword>();
		Oferta of = new Oferta(
                emp,
				tdo,
				"Esternocleidomastoideo",
				"ElVolcanDeParangaricutirimicuaro",
				"12:00 - 23:00",
				(float)100,
				"AlgunaCiudadDeArtigas",
				"Artigas",
				fecha,
				keys,
				"",
				EstadoOferta.Confirmada);
		
		assertTrue(of.getNombre().equals("Esternocleidomastoideo"));
		assertTrue(of.getDescripcion().equals("ElVolcanDeParangaricutirimicuaro"));
		assertTrue(of.getHorarioTrabajo().equals("12:00 - 23:00"));
		assertTrue(of.getRemuneracion() == 100);
		assertTrue(of.getCiudad().equals("AlgunaCiudadDeArtigas"));
		assertTrue(of.getDepartamento().equals("Artigas"));
		assertTrue(of.getFechaAlta().compareTo(fecha) == 0);
		assertEquals(of.getCosto(), 1);
		assertTrue(of.getTipodeoferta().getNombre().equals(tdo.getNombre()));
		
		LocalDate a = LocalDate.of(2003,11,1);
		
		of.setNombre("ParticularmenteDrogado");
		of.setDescripcion("comoParaTenerUnaDescripcion");
		of.setCiudad("Guatemalteco");
		of.setDepartamento("Tacuarembo");
		of.setHorarioTrabajo("10:00 - 03:00");
		of.setRemuneracion((float)10000);
		of.setFechaAlta(a);
		of.setCosto(100);
		of.setEst(EstadoOferta.Rechazada);
		
		DtOferta data = of.getDtOferta();
		
		assertTrue(data.getNombre().equals("ParticularmenteDrogado"));
		assertTrue(data.getDescripcion().equals("comoParaTenerUnaDescripcion"));
		assertTrue(data.getHorarioTrabajo().equals("10:00 - 03:00"));
		assertTrue(data.getRemuneracion() == (float)10000);
		assertTrue(data.getCiudad().equals("Guatemalteco"));
		assertTrue(data.getDepartamento().equals("Tacuarembo"));
        System.out.println(data.getFechaAlta());
		assertTrue(data.getFechaAlta().equals("01-11-2003"));
		assertTrue(data.getCosto() == 100);
		assertTrue(data.getEstado().equals(EstadoOferta.Rechazada));
		assertTrue(data.getTipoDeOferta().equals("Untipodeofertaespectacular"));
		assertTrue(data.getEmpresa().equals("Taladrator 3000"));

	}
}
