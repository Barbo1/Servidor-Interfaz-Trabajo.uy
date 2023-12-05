package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.Test;

import model.clases.TipoDeOferta;

import model.clases.Cantidad;

class CantidadTest {

	@Test
    void getCantidad_test1() {
        TipoDeOferta of = new TipoDeOferta("Alacran", "Ofertisima", 6, 4, (float) 1, LocalDate.now());
        Cantidad ca = new Cantidad(2, of);

        assertTrue(ca.getCantidad() == 2);
        assertTrue(ca.getTipodeoferta().getNombre().equals("Alacran"));

        ca.setCantidad(4);
        ca.setTipodeoferta(new TipoDeOferta("Escorpio", "El rey del fuego", 2, 2, (float) 4, LocalDate.now()));

        assertTrue(ca.getCantidad() == 4);
        assertTrue(ca.getTipodeoferta().getNombre().equals("Escorpio"));

        assertTrue(ca.getDtCantidad().getCantidad() == 4);
    }

}
