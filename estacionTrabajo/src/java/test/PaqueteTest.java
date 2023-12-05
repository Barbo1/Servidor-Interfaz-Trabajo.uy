package test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.clases.Fabrica;
import model.clases.Paquete;
import model.clases.Empresa;
import model.controllers_managers.ManejadorPaquete;
import model.datatype.DtPaquete;
import model.datatype.DtPaqueteCompleto;
import model.interfacess.IcontroladorLaboral;
import model.interfacess.IcontroladorUsuario;

class PaqueteTest {
	
	private static IcontroladorUsuario ctrlUsuario;
	private static IcontroladorLaboral ctrlLaboral;
	private static ManejadorPaquete manager;
	
	@BeforeAll
	public static void iniciar() {
		Fabrica fabrica = Fabrica.getInstance();
		ctrlLaboral = fabrica.getIcontroladorLaboral();
		ctrlUsuario = fabrica.getIControladorUsuario();
		manager = ManejadorPaquete.getInstance();

	}
	
	@Test
	void listarPaquetes_test() {
		Paquete paq = new Paquete("paquete1", "descripcion1", 1, 1, LocalDate.of(2001, 10, 25), "");
		manager.addPaquete(paq);
		manager.addPaquete(new Paquete("paquete2", "descripcion2", 2, 2, LocalDate.of(1980, 2, 4), ""));

        List<DtPaquete> dataPaquetes = ctrlLaboral.getDtPaquetes();
        for (DtPaquete dataPaquete : dataPaquetes) {

            if(dataPaquete.getNombre() == "paquete1") {
                assertTrue(dataPaquete.getDescripcion() == "descripcion1");
                assertTrue(dataPaquete.getImagen() == "");
                assertTrue(dataPaquete.getDescuento() == 1);
                assertTrue(dataPaquete.getValidez() == 1);
                
            } else if (dataPaquete.getNombre() == "paquete2") {
                assertTrue(dataPaquete.getDescripcion() == "descripcion2");
                assertTrue(dataPaquete.getImagen() == "");
                assertTrue(dataPaquete.getDescuento() == 2);
                assertTrue(dataPaquete.getValidez() == 2);
            }
        }
        
        paq.setNombre("BenjaminElPa");
        paq.setDescripcion("CaraDeEstiercol");
        paq.setValidez(13);
        paq.setDescuento((float)0.4);
        paq.setCosto(14000);
        paq.setFechaAlta(LocalDate.now());
        paq.setImagen("");

        assertTrue(paq.getNombre().equals("BenjaminElPa"));
        assertTrue(paq.getDescripcion().equals("CaraDeEstiercol"));
        assertTrue(paq.getValidez() == 13);
        assertTrue(paq.getDescuento() == (float)0.4);
        assertTrue(paq.getFechaAlta().compareTo(LocalDate.now()) == 0);
        assertTrue(paq.getImagen().equals(""));
        assertTrue(paq.getCosto() == 14000);

        String[] listaPaqueteStrings = ctrlLaboral.listarPaquetes();
        DtPaquete dataPaqueteSimple = ctrlLaboral.getDtPaquete("paquete1");
        DtPaqueteCompleto dataPaqueteCompleto = ctrlLaboral.getDtPaqueteCompleto("paquete1");
    }
	
	@Test
	void tienePaquete_test() throws Exception {
		ctrlLaboral.altaPaquete("PaqueteQueTeHaceLlorarDeLaRisa", "desc1", 1, 1, LocalDate.now(), "");

		ctrlUsuario.ingresarEmpresa(
                "https://www.youtube.com/watch?v=NOaM16H38r8",
                "Deeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeescripcion",
                "Benjamin",
                "Barboza",
                "TorneadorTortilla",
                "HoyMeV@yal.sol",
                "",
                "contra");

        ctrlLaboral.comprarPaquete("TorneadorTortilla", "PaqueteQueTeHaceLlorarDeLaRisa", LocalDate.now());
        assertTrue(ctrlLaboral.tienePaquete("TorneadorTortilla", "PaqueteQueTeHaceLlorarDeLaRisa"));
	}
}
