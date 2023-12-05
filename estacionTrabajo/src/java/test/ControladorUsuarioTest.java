package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import model.datatype.DtEmpresa;
import model.datatype.DtEmpresaCompleto;
import model.datatype.DtPostulante;
import model.datatype.DtPostulanteCompleto;
import model.datatype.EstadoOferta;
import model.controllers_managers.ControladorLaboral;
import model.controllers_managers.ControladorUsuario;
import model.exceptions.DuplicationException;
import model.exceptions.NonExistentException;

class ControladorUsuarioTest {
	private static ControladorUsuario controladorUsuario = new ControladorUsuario();
	private static ControladorLaboral controladorLaboral = new ControladorLaboral();
	private static LocalDate nacimientoTest = LocalDate.of(2013, 1, 1);
	
	void ingresarUsuario() {

		controladorUsuario.ingresarPostulante(
				nacimientoTest, 
				"Estadounidense", 
				"Mordecai", 
				"Rigby", 
				"XxXWarriorXxX",
				"rigby@hotmail.com", 
				"51182839", 
				"");
		DtPostulante postulante = controladorUsuario.mostrarPostulante("XxXWarriorXxX").getPostulante();

		assertEquals(postulante.getNickname(), "XxXWarriorXxX");
		assertEquals(postulante.getCorreoElectronico(), "rigby@hotmail.com");
		assertEquals(postulante.getNombre(), "Mordecai");
		assertEquals(postulante.getApellido(), "Rigby");
		assertEquals(postulante.getNacimiento(), nacimientoTest);
		assertEquals(postulante.getNacionalidad(), "Estadounidense");

		controladorUsuario.ingresarEmpresa(
				"https://gitlab.fing.edu.uy/tprog/tpgr38/", 
				"Dejgraziao ijoputa", 
				"Hitler", 
				"HelloKitty", 
				"MeGustanLasFotos",
				"granadasAtomicas@hotmail.com",
				"megustalafafa",
				"");
		DtEmpresa empresa = controladorUsuario.mostrarEmpresa("MeGustanLasFotos").getEmpresa();

		assertEquals(empresa.getNickname(), "MeGustanLasFotos");
		assertEquals(empresa.getCorreoElectronico(), "granadasAtomicas@hotmail.com");
		assertEquals(empresa.getNombre(), "Hitler");
		assertEquals(empresa.getApellido(), "HelloKitty");
		assertEquals(empresa.getDescripcion(), "Dejgraziao ijoputa");
		assertEquals(empresa.getLink(), "https://gitlab.fing.edu.uy/tprog/tpgr38/");
	}
	
	@Test
	void ingresarPostulanteNicknameRepetido() {
		controladorUsuario.ingresarPostulante(
				nacimientoTest, 
				"Egipcio", 
				"Marcelo", 
				"Storoszczuk", 
				"EstornudoDeVaca", 
				"alfonso@gmail.com",
				"sisisisi",
				"");
		assertThrows(DuplicationException.class, 
				()-> controladorUsuario.ingresarPostulante(
						nacimientoTest, 
						"Holandez", 
						"Ignacio", 
						"Santos", 
						"EstornudoDeVaca", 
						"uncorreoProminente@gmail.com",
						"holas",
						"")
			);	
	}
	
	@Test
	void ingresarEmpresaNicknameRepetido() {
		controladorUsuario.ingresarEmpresa(
				"https://www.youtube.com/watch?v=_6XzJPyAJDI", 
				"La primera es la mejor de las 5 canciones. La verdad, una locura", 
				"HoyMeVoyAlSol", 
				"PorqueDiosMeLlamoDesdeElDowntown", 
				"AKcuarentaYsiete",
				"DevoDespertarPorqueNoSe@caloramia.lma", 
				"PeroMeSalve", 
				"");
		assertThrows(DuplicationException.class, 
				() -> controladorUsuario.ingresarEmpresa(
						"https://www.youtube.com/@ThePrimeTimeagen", 
						"programa bien, 10/10", 
						"Juan", 
						"Jose", 
						"AKcuarentaYsiete",
						"notoriedad@gmail.com", 
						"contra",
						"")
				);	
	}
	
	@Test
	void ingresarPostulanteCorreoRepetido() {
		controladorUsuario.ingresarPostulante(
				nacimientoTest, 
				"Estadounidence", 
				"Jhon", 
				"Carmack",
				"ElMejorProgramadorDelMundo",
				"sisisinononaysisisifumandocañaaa@hotmail.com", 
				"contra",
				"");
			
		assertThrows(DuplicationException.class, 
				() -> controladorUsuario.ingresarPostulante(
						nacimientoTest, 
						"Español", 
						"Bizarrap", 
						"Duki", 
						"juan", 
						"sisisinononaysisisifumandocañaaa@hotmail.com",
						"contra",
						"")
				);	
	}
	
	@Test
	void ingresarEmpresaCorreoRepetido() {
		controladorUsuario.ingresarEmpresa(
				"https://ocw.mit.edu/", 
				"ojala tener plata para poder ir", 
				"MIT", 
				"Institute", 
				"MIT Institute",
				"josejose123@hotmail.com", 
				"contra", 
				"");
		assertThrows(DuplicationException.class, 
				() -> controladorUsuario.ingresarEmpresa(
						"https://www.youtube.com/watch?v=rEfyrIFcvVc",
						"ojala poder escucharla todo el dia", 
						"Jose", 
						"Juan", 
						"Depredador", 
						"josejose123@hotmail.com",
						"contra",
						"")
				);	
	}
	
	@Test
	void testListarEmpresas() {
		String [] empresas = controladorUsuario.listarEmpresas();
		for(int i = 0; i < empresas.length; i++) {
			assertEquals(true, controladorUsuario.esEmpresa(empresas[i]));
		}
	}

	@Test
	void testListarPostulantes() {
		String [] postulantes = controladorUsuario.listarPostulantes();
		for(int i = 0; i < postulantes.length; i++) {
			assertEquals(true, controladorUsuario.esPostulante(postulantes[i]));
		}	
	}
	
	@Test
	void testListarUsuarios() {
		String [] usuarios = controladorUsuario.listarUsuarios();
		for(int i = 0; i < usuarios.length; i++) {
			assertEquals(true, controladorUsuario.esEmpresa(usuarios[i]) || controladorUsuario.esPostulante(usuarios[i]));
		}
	}

	@Test
    void test2ModificarDatosEmpresa() {
        controladorUsuario.ingresarEmpresa(
        		"https://www.youtube.com/watch?v=u2Zg6p1_Klw&list=RDu2Zg6p1_Klw&index=1", 
        		"El infierno es un lugar frio y oscuro", 
        		"Pariente", 
        		"hijo", 
        		"Promiscuo", 
        		"robertoRodriguez@rod.com",
        		"contra",
        		"");

        controladorLaboral.ingresarKeyword("KW1");

        Set<String> kw = new HashSet<String>();
        kw.add("KW1");

        LocalDate date = LocalDate.of(2023, 3, 1);

        controladorLaboral.altaTipoOferta(
        		"Aristoteles en silla electrica", 
        		"Es aristoteles, pero en silla electrica", 
        		3, 
        		4, 
        		4, 
        		date);

        controladorLaboral.ingresarOfertaLaboral(
        		"Promiscuo", 
        		"Aristoteles en silla electrica", 
        		"Osiris", 
        		"Fortuna", 
        		"Anubis", 
        		(float) 3.14, 
        		"Apolo", 
        		"Ra", 
        		date, 
        		kw,
        		"",
        		EstadoOferta.Confirmada);
        
        controladorUsuario.listarOfertasEmpresa("Promiscuo");

        assertThrows(NonExistentException.class, ()->controladorUsuario.listarOfertasEmpresa("LALALALALALALLALAL"));
    }
	
    @Test
	void testModificarDatosEmpresa() {

		controladorUsuario.ingresarEmpresa(
				"https://www.youtube.com/watch?v=i9CBKGLVCME", 
				"Parametros repetidos se consideran fervientemente", 
				"ApologiaAlNazismo", 
				"AlegoriaDeLaCavernaDePlaton", 
				"JuanMigule",
				"ApareamientoDeTerodactilos@hotmail.com",
                "contra",
                "");
		
		controladorUsuario.modificarDatosEmpresa(
				"JuanMigule", 
				"Copernico", 
				"DescraciadoConchetumadre",
				"https://www.youtube.com/watch?v=BnSkt6V3qF0", 
				"DescripcionMonstuosa",
                "",
                "");
    
		DtEmpresa data = controladorUsuario.mostrarEmpresa("JuanMigule").getEmpresa();

		assertTrue(data.getNombre() =="Copernico");
		assertTrue(data.getApellido() == "DescraciadoConchetumadre");
		assertTrue(data.getLink() == "https://www.youtube.com/watch?v=BnSkt6V3qF0");
		assertTrue(data.getDescripcion() == "DescripcionMonstuosa");
	}
	
    @Test
    void ModificarDatosPostulante() {
		controladorUsuario.ingresarPostulante(
				LocalDate.of(2001, 9, 25), 
				"Guatemalteco", 
				"Chanflinas", 
				"Papanatas", 
				"TontinYBobin",
				"EscupitajoDeMosca@LocoLoco.si",
                "contra",
                "");

		controladorUsuario.modificarDatosPostulante(
				"TontinYBobin", 
				"Copernico", 
				"DescraciadoConchetumadre",
                LocalDate.of(2003, 1, 11),
				"Uruguay", 
                "",
                "");
    
        DtPostulante data = controladorUsuario.mostrarPostulante("TontinYBobin").getPostulante();

        assertTrue(data.getNombre() == "Copernico");
        assertTrue(data.getApellido() == "DescraciadoConchetumadre");
        assertTrue(data.getNacimiento().equals("11-01-2003"));
        assertTrue(data.getNacionalidad() == "Uruguay");
    }
    
    @Test
    void getDTUsuarios_test() {
        controladorUsuario.getDtUsuarios();
    }
    
    @Test
    void estaPostulado() {
        controladorUsuario.ingresarEmpresa(
        		"https://www.youtube.com/watch?v=GCdwKhTtNNw", 
        		"Prototipo de generica regular", 
        		"Pariente", 
        		"hijo", 
        		"DarkSoulsRemasterizado", 
        		"Pacotilla@rod.com",
        		"contra",
        		"");

        controladorLaboral.altaTipoOferta(
        		"EmpiricamenteLoPeorDeLaCasta", 
        		"NoPuedoTenerTantoParaHacerLaPutaQueLosPario", 
        		3, 
        		4, 
        		4, 
        		LocalDate.of(2023, 3, 1));

        controladorLaboral.ingresarOfertaLaboral(
        		"DarkSoulsRemasterizado", 
        		"EmpiricamenteLoPeorDeLaCasta", 
        		"PateandoAncianasjiji", 
        		"Fortuna", 
        		"Anubis", 
        		(float) 3.14, 
        		"Apolo", 
        		"Ra", 
        		LocalDate.now(), 
        		new HashSet<String>(),
        		"",
        		EstadoOferta.Confirmada);

		controladorUsuario.ingresarPostulante(
				LocalDate.of(2001, 9, 25), 
				"Guatemalteco", 
				"Chanflinas", 
				"Papanatas", 
				"LaPacienciaEsInsuperable",
				"SexoAEscondidas@LocoLoco.si",
                "contra",
                "");

        assertTrue(!controladorUsuario.estaPostulado("LaPacienciaEsInsuperable", "PateandoAncianasjiji"));
		
        controladorLaboral.postular(
                "LaPacienciaEsInsuperable", 
                "PateandoAncianasjiji",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
                LocalDate.now(),
                ""
                );

        assertTrue(controladorUsuario.estaPostulado("LaPacienciaEsInsuperable", "PateandoAncianasjiji"));
    }
	
    /** 
     * comprueba que una empresa es la que posee una oferta 
     * creada, y otra no lo hace. 
     */
    @Test
    void ofertaDeEmpresa_test1() {
        controladorUsuario.ingresarEmpresa(
        		"https://en.wikipedia.org/wiki/Epictetus", 
        		"Prototipo de generica regular", 
        		"Pariente", 
        		"hijo", 
        		"Epicteto", 
        		"parodiadeelxocas@rod.com",
        		"contra",
        		"");

        controladorUsuario.ingresarEmpresa(
        		"https://en.wikipedia.org/wiki/Archimedes", 
        		"Esta es la peor tortura de mi existencia", 
        		"Pariente", 
        		"hijo", 
        		"AgenteP", 
        		"ParpadeoSeductor@rod.com",
        		"contra",
        		"");

        controladorLaboral.altaTipoOferta(
        		"gatoDetonador", 
        		"SiNoSeMeOcurreNadaMasVoyAEmpezarConLasReferenciasSexuales", 
        		3, 
        		4, 
        		4, 
        		LocalDate.of(2023, 3, 1));

        controladorLaboral.ingresarOfertaLaboral(
        		"AgenteP", 
        		"gatoDetonador", 
        		"ElTipoDeOfertaMasGenericoDelMundoMundial", 
        		"Fortuna", 
        		"Anubis", 
        		(float) 3.14, 
        		"Apolo", 
        		"Ra", 
        		LocalDate.now(), 
        		new HashSet<String>(),
        		"",
        		EstadoOferta.Confirmada);
       
        assertTrue(controladorUsuario.ofertaDeEmpresa("AgenteP", "ElTipoDeOfertaMasGenericoDelMundoMundial"));
        assertTrue(!controladorUsuario.ofertaDeEmpresa("Epicteto", "ElTipoDeOfertaMasGenericoDelMundoMundial"));
    }
}
