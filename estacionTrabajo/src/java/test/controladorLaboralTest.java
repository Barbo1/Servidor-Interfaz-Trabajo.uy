package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.format.DateTimeFormatter;

import model.exceptions.DuplicationException;
import model.exceptions.ExistentException;
import model.exceptions.NonExistentException;
import model.exceptions.OutOfMarginException;
import model.datatype.DtOferta;
import model.datatype.DtOfertaCompleto;
import model.datatype.DtPaqueteCompleto;
import model.datatype.DtPostulacionCompleto;
import model.datatype.DtTipoDeOferta;
import model.datatype.EstadoOferta;
import model.controllers_managers.ControladorLaboral;
import model.controllers_managers.ControladorUsuario;

class controladorLaboralTest {
	private static ControladorLaboral controladorLaboral;
	private static ControladorUsuario controladorUsuario;
	private static LocalDate fecha1 = LocalDate.now();
	private static LocalDate fecha2 = LocalDate.of(2023, 1, 2);
	
	@BeforeAll
	public static void iniciar() {
		controladorLaboral = new ControladorLaboral();
		controladorUsuario = new ControladorUsuario();
	}

	@Test
	void listarKeywords_test1() {
		controladorLaboral.listarKeywords();
	}
	
	@Test
	void altaPaquete_test1() {
		controladorLaboral.altaPaquete("paqueteTurbulento", "descripcion1", 123, (float)0.243, fecha1, "");
		assertThrows(DuplicationException.class,
				() -> controladorLaboral.altaPaquete("paqueteTurbulento", "descripcion2", 41, (float)0.5, fecha2, "")
            );
	}
	
	@Test
	void altaPaquete_test2() {
		assertThrows(OutOfMarginException.class,
				() -> controladorLaboral.altaPaquete("Madagascar", "descripcion1", -1, (float)0.243, fecha1, "")
			);
	}
	
	@Test
	void altaPaquete_test3() {
		assertThrows(OutOfMarginException.class,
				() -> controladorLaboral.altaPaquete("Makakosapo", "descripcion1", 0, (float)0.243, fecha1, "")
            );
	}
	
	@Test
	void altaPaquete_test4() {
        assertThrows(OutOfMarginException.class,
                () -> controladorLaboral.altaPaquete("UnPaqueteSinIgual", "descripcion1", 1, (float)-0.01, fecha1, "")
            );
        assertThrows(Exception.class,
                () -> controladorLaboral.altaPaquete("UnPaqueteSinIgual", "descripcion1", 1, (float)1.01, fecha1, "")
            );
	}
	
	@Test
	void mostrarInfoPaquete_test1() {
        controladorLaboral.altaPaquete("Culebra", "descripcion122", 3, (float)0.99, fecha2, "");
        DtPaqueteCompleto data = controladorLaboral.mostrarInfoPaquete("Culebra");
        assertTrue(data.getPaquete().getNombre().equals("Culebra"));
        assertTrue(data.getPaquete().getDescripcion().equals("descripcion122"));
        assertEquals((float)0.99, data.getPaquete().getDescuento());
        assertEquals(3, data.getPaquete().getValidez());
	}
	
	@Test
	void mostrarInfoPaquete_test2() {
        assertThrows(Exception.class,
                () -> controladorLaboral.mostrarInfoPaquete("NombreNoExistente")
            );
	}
	
	@Test
	void ingresarKeyword_test2() {
		controladorLaboral.ingresarKeyword("Mago");
        assertThrows(Exception.class,
                () -> controladorLaboral.ingresarKeyword("Mago")
            );
	}
	
	@Test
    void altaTipoOferta_test1() {
        controladorLaboral.altaTipoOferta("tipooferta1", "descripcion5", 4, 2, (float)12, fecha1);
        controladorLaboral.altaTipoOferta("tipooferta2", "descripcion2", 5, 3, (float)13, fecha2);
        controladorLaboral.altaTipoOferta("tipooferta3", "descripcion3", 312123, 1, (float)0.01, fecha1);
        controladorLaboral.altaTipoOferta("tipooferta4", "descripcion4", 2, 1, (float)222222, fecha2);

        Set<String> set = Set.of(controladorLaboral.listarTipoDeOferta());

        assertTrue(set.contains("tipooferta1"));
        assertTrue(set.contains("tipooferta2"));
        assertTrue(set.contains("tipooferta3"));
        assertTrue(set.contains("tipooferta4"));

        DtTipoDeOferta data;

        data = controladorLaboral.mostrarTipoOferta("tipooferta1");
        assertEquals(data.getNombre(), "tipooferta1");
        assertTrue(data.getDescripcion().equals("descripcion5"));
        assertEquals(data.getDuracion(), 4);
        assertEquals(data.getExposicion(), 2);
        assertEquals(data.getCosto(), (float)12);

        data = controladorLaboral.mostrarTipoOferta("tipooferta2");
        assertTrue(data.getNombre().equals("tipooferta2"));
        assertTrue(data.getDescripcion().equals("descripcion2"));
        assertEquals(data.getDuracion(), 5);
        assertEquals(data.getExposicion(), 3);
        assertEquals(data.getCosto(), (float)13);

        data = controladorLaboral.mostrarTipoOferta("tipooferta3");
        assertTrue(data.getNombre().equals("tipooferta3"));
        assertTrue(data.getDescripcion().equals("descripcion3"));
        assertEquals(data.getDuracion(), 312123);
        assertEquals(data.getExposicion(), 1);
        assertEquals(data.getCosto(), (float)0.01);

        data = controladorLaboral.mostrarTipoOferta("tipooferta4");
        assertTrue(data.getNombre().equals("tipooferta4"));
        assertTrue(data.getDescripcion().equals("descripcion4"));
        assertEquals(data.getDuracion(), 2);
        assertEquals(data.getExposicion(), 1);
        assertEquals(data.getCosto(), (float)222222);
    }

    @Test
    void altaTipoOferta_test2() {
        controladorLaboral.altaTipoOferta("Premium", "descripcion1", 3, 2, 1200, fecha1);
        assertThrows(Exception.class,
                () -> controladorLaboral.altaTipoOferta("Premium", "descripcion2", 4, 10, 200, fecha2)
            );
    }
	
	@Test
	void altaTipoOferta_test3() {
        assertThrows(OutOfMarginException.class,
                () -> controladorLaboral.altaTipoOferta("SISISI", "descripcion313", 4, 10, -200, fecha2)
            );
	}
	
	@Test
	void altaTipoOferta_test4() {
        assertThrows(OutOfMarginException.class,
                () -> controladorLaboral.altaTipoOferta("LanzaDeLonginus", "descripcion313", -1, 10, 200, fecha2)
            );
	}
	
	@Test
	void altaTipoOferta_test5() {
        assertThrows(OutOfMarginException.class,
                () -> controladorLaboral.altaTipoOferta("NONONO", "descripcion313", 4, -1, (float)0.01, fecha2)
            );
        assertThrows(OutOfMarginException.class,
                () -> controladorLaboral.altaTipoOferta("NONONO", "descripcion313", 4, 6, (float)0, fecha2)
            );
	}
	
	@Test
	void altaTipoOferta_test6() {
		controladorLaboral.altaTipoOferta("trinitrotolueno", "descripcion313", 4, 1, (float)0.01, fecha2);
        assertThrows(DuplicationException.class,
                () -> controladorLaboral.altaTipoOferta("trinitrotolueno", "descripcion313", 4, 2, (float)0, fecha2)
            );
	}
	
	@Test
	void mostrarTipoOferta_test1() {
        assertThrows(Exception.class,
                () -> controladorLaboral.mostrarTipoOferta("este nombre de oferta no existe")
            );
	}
	
	@Test
	void mostrarOfertaCompleto_test1() {
        assertThrows(Exception.class,
                () -> controladorLaboral.mostrarOfertaCompleto("este nombre de oferta no existe")
            );
	}
	
	@Test
	void mostrarOferta_test1() {
        assertThrows(Exception.class,
                () -> controladorLaboral.mostrarOferta("este nombre de oferta no existe")
            );
	}
	
	@Test
	void ingresarKeyword_test1() {
		controladorLaboral.ingresarKeyword("Programador");
		controladorLaboral.ingresarKeyword("Cajero");
		controladorLaboral.ingresarKeyword("Limpiador");
		controladorLaboral.ingresarKeyword("Fletero");
		
		// estos casos se consideran validos, ya que no hay problema en poner caracteres distintos.
		controladorLaboral.ingresarKeyword("Fletero_");
		controladorLaboral.ingresarKeyword("Fletero11");
	}
	
	@Test
	void listarPaquetes_test1() {
        controladorLaboral.listarPaquetes();			
	}
	
	@Test
    void ingresarOfertaLaboral_test1() {
        Set<String> keywords = new HashSet<String>();

        controladorLaboral.altaTipoOferta("Jose Gervasio Artigas", "descripcion5", 4, 2, (float)12, fecha1);

        controladorUsuario.ingresarEmpresa(
                "https://pagina.com/helados", 
                "descripcion5", 
                "Fernando", 
                "Fernandez", 
                "Xocas", 
                "mimamamemima@gmail.com",
                "sisi",
                ""
                );
        controladorUsuario.ingresarEmpresa(
                "https://LETNA.com/empanadas", 
                "descripcion6", 
                "Hernesto", 
                "Hernandez", 
                "Multicuenta122", 
                "grageas@hotmail.com",
                "nono",
                ""
                );

        // creacion de ofertas laborales

        String desc = "El postulante de esta oferta laboral se compromete a "
            + "afirmarse de un palo firme mientras es arrastrado, "
            + "mediante este, a lo largo de una ventana de 1200m"
            + ", con el fin de que esta sea limpiada.";

        controladorLaboral.ingresarOfertaLaboral(
                "Multicuenta122", 
                "Jose Gervasio Artigas", 
                "Limpia parabrisas", 
                desc,
                "00:00 - 00:00", 
                (float)2, 
                "Mi casa", 
                "Mi casa", 
                fecha1, 
                keywords,
                "",
                EstadoOferta.Confirmada
                );

        controladorLaboral.ingresarOfertaLaboral(
                "Multicuenta122", 
                "Jose Gervasio Artigas", 
                "Buscamos empleo", 
                "Puede ser que te paguemos", 
                "06:00 - 12:00", 
                (float)20000, 
                "Algun Lugar", 
                "Algun otro lugar", 
                fecha1, 
                keywords,
                "",
                EstadoOferta.Confirmada
                );

        keywords.add("Escultor");
        keywords.add("Tonto");

        controladorLaboral.ingresarKeyword("Escultor");
        controladorLaboral.ingresarKeyword("Tonto");

        controladorLaboral.ingresarOfertaLaboral(
                "Xocas", 
                "Jose Gervasio Artigas", 
                "Oferta", 
                "postularse al pueso 'Oferta'", 
                "09:00 - 18:00", 
                (float)10000, 
                "Montevideo", 
                "Montevideo", 
                fecha1,
                keywords,
                "",
                EstadoOferta.Confirmada
                );

        DtOferta data;

        data = controladorLaboral.mostrarOferta("Buscamos empleo");
        assertTrue(data.getNombre().equals("Buscamos empleo"));
        assertTrue(data.getDescripcion().equals("Puede ser que te paguemos"));
        assertTrue(data.getHorarioTrabajo().equals("06:00 - 12:00"));
        assertEquals(data.getRemuneracion(), (float)20000);
        assertTrue(data.getCiudad().equals("Algun Lugar"));
        assertTrue(data.getDepartamento().equals("Algun otro lugar"));
        assertTrue(data.getFechaAlta().equals(fecha1.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));

        data = controladorLaboral.mostrarOferta("Limpia parabrisas");
        assertTrue(data.getNombre().equals("Limpia parabrisas"));
        assertTrue(data.getDescripcion().equals(desc));
        assertTrue(data.getHorarioTrabajo().equals("00:00 - 00:00"));
        assertEquals(data.getRemuneracion(), (float)2);
        assertTrue(data.getCiudad().equals("Mi casa"));
        assertTrue(data.getDepartamento().equals("Mi casa"));
        assertTrue(data.getFechaAlta().equals(fecha1.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));

        data = controladorLaboral.mostrarOferta("Oferta");
        assertTrue(data.getNombre().equals("Oferta"));
        assertTrue(data.getDescripcion().equals("postularse al pueso 'Oferta'"));
        assertTrue(data.getHorarioTrabajo().equals("09:00 - 18:00"));
        assertEquals(data.getRemuneracion(), (float)10000);
        assertTrue(data.getCiudad().equals("Montevideo"));
        assertTrue(data.getDepartamento().equals("Montevideo"));
        assertTrue(data.getFechaAlta().equals(fecha1.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
    }

    @Test
    void ingresarOfertaLaboral_test2() {
        Set<String> keywords = new HashSet<String>();

        controladorLaboral.altaTipoOferta("tipooferta5", "descripcion6", 2, 1, (float)222222, fecha2);

        controladorUsuario.ingresarEmpresa(
                "https://eva.fing.edu.uy", 
                "la pagina del diablo", 
                "Leonardo", 
                "Da Vincci", 
                "Hercules", 
                "messi@gmail.com",
				"lamejorcontraseña",
				""
		);
		
		controladorLaboral.ingresarOfertaLaboral(
				"Hercules", 
				"tipooferta5", 
				"unNombre", 
				"fifty cent == 197629 pesos argentinos", 
				"1:00 - 8:00", 
				(float)10000, 
				"Montevideo", 
				"Montevideo", 
				fecha1, 
				keywords,
				"",
				EstadoOferta.Confirmada
		);

        assertThrows(Exception.class,
			() -> controladorLaboral.ingresarOfertaLaboral(
					"Xocas", 
					"tipooferta1", 
					"unNombre", 
					"Te vamos a pagar", 
					"06:00 - 12:00", 
					(float)20000, 
					"Algun Lugar", 
					"Algun otro lugar", 
					fecha1, 
					keywords,
					"",
					EstadoOferta.Confirmada)
			);
	}
	
	@Test
	void ingresarOfertaLaboral_test3() {
		Set<String> keywords = new HashSet<String>();
		
		controladorLaboral.altaTipoOferta("PremiumPlus", "Mejor que premium", 4, 2, (float)3, fecha1);
		
		controladorUsuario.ingresarEmpresa(
				"https://youtube.com", 
				"no puedo dejar la dopamina", 
				"Albert", 
				"Einstein", 
				"ElAlberto", 
				"alberein@hotmail.com",
				"Esteroides",
				""
		);
        assertThrows(Exception.class,
			() -> controladorLaboral.ingresarOfertaLaboral(
					"ElAlberto", 
					"PremiumPlus", 
					"Ingeniero quimico", 
					"Puesto para alguien que quiera hacer medicamentos", 
					"06:00 - 12:00", 
					(float)-1, 
					"Holando", 
					"Portugal", 
					fecha1, 
					keywords,
					"",
					EstadoOferta.Confirmada)
			);
	}
	
	@Test
	void ingresarOfertaLaboral_test4() {
		Set<String> keywords = new HashSet<String>();
		
		controladorLaboral.altaTipoOferta("PremiumPlusPlus", "Mejor que el que es mejor que premium", 4, 2, (float)3, fecha1);
		
        assertThrows(Exception.class,
			() -> controladorLaboral.ingresarOfertaLaboral(
					"AAAAAA", 
					"PremiumPlus", 
					"Ingeniero quimico", 
					"Puesto para alguien que quiera hacer medicamentos", 
					"06:00 - 12:00", 
					(float)3, 
					"Holando", 
					"Portugal", 
					fecha1, 
					keywords,
					"",
					EstadoOferta.Confirmada)
            );
	}
	
	@Test
	void ingresarOfertaLaboral_test5() {
		Set<String> keywords = new HashSet<String>();
		
		controladorUsuario.ingresarEmpresa(
				"https://youtube.com", 
				"no puedo dejar la dopamina", 
				"German", 
				"Geronimo", 
				"Gladiador1414", 
				"glad@gmail.com",
				"contraseña",
				""
		);
        assertThrows(Exception.class,
			() -> controladorLaboral.ingresarOfertaLaboral(
					"Gladiador1414", 
					"EEEEEO", 
					"Ingeniero quimico", 
					"Puesto para alguien que quiera hacer medicamentos", 
					"06:00 - 12:00", 
					(float)2, 
					"Holando", 
					"Portugal", 
					fecha1, 
					keywords,
					"",
					EstadoOferta.Confirmada)
            );
	}
	
	@Test
	void listarTipoDeOferta_test1() {
        controladorLaboral.listarPaquetes();			
	}

	@Test
	void listarTiposDeOfertaSTR_test1() {
        controladorLaboral.listarTiposDeOfertaSTR();			
	}
	
	@Test
	void agregarTipoDePublicacionAPaquete_test1() {
		controladorLaboral.altaTipoOferta("TipoJoseJuan", "Hora de aventura", 732, 3, (float)432, fecha1);
		controladorLaboral.altaTipoOferta("TipoPatricioPedro", "Llama a tus amigos", 1, 4, (float)4, fecha2);
		controladorLaboral.altaPaquete("Duki", "Modo Diablo", 666, (float)0.666, fecha2, "");
		controladorLaboral.agregarTipoDePublicacionAPaquete("Duki", "TipoJoseJuan", 12);
        assertThrows(Exception.class,
			    () -> controladorLaboral.agregarTipoDePublicacionAPaquete("Duki", "TipoJoseJuan", 11)
            );
		controladorLaboral.agregarTipoDePublicacionAPaquete("Duki", "TipoPatricioPedro", 12);
	}
	
	@Test
	void agregarTipoDePublicacionAPaquete_test2() {
        assertThrows(Exception.class,
			    () -> controladorLaboral.agregarTipoDePublicacionAPaquete("Duki", "TipoJoseJuan", -1)
            );
	}
	
	@Test
	void agregarTipoDePublicacionAPaquete_test3() {
        assertThrows(Exception.class,
			    () -> controladorLaboral.agregarTipoDePublicacionAPaquete("Duki", "UnTipoDeOfertaNoExistente", 11)
            );
	}
	
	@Test
	void agregarTipoDePublicacionAPaquete_test4() {
		controladorLaboral.altaTipoOferta("TipoLuciano", "EOEAEOEA", 732, 3, (float)432, fecha1);
        assertThrows(Exception.class,
			    () -> controladorLaboral.agregarTipoDePublicacionAPaquete("UnNombreNoExistente", "TipoLuciano", 11)
            );
	}
	
	@Test
	void postular_test1() {

		controladorUsuario.ingresarPostulante(
				fecha1, 
				"Uruguayo", 
				"Nicolas",
				"Temsiuk",
				"Nico", 
				"niidea@gmail.com",
				"eoea",
				""
		);
		controladorUsuario.ingresarPostulante(
				fecha1, 
				"Uruguayo", 
				"Mariana", 
				"Apellido", 
				"Hermione", 
				"niidea2@gmail.com",
				"notengoidea",
				""
		);
		controladorUsuario.ingresarPostulante(
				fecha1, 
				"Uruguayo", 
				"Joaquin", 
				"Apellido2", 
				"Ron", 
				"joaquin@gmail.com",
				"nosejaja",
				""
		);
		
		controladorUsuario.ingresarEmpresa(
				"www.lapeorempresadelmundo.org", 
				"descripcion5", 
				"German", 
				"Gimenez", 
				"Tesla", 
				"lapeorempresadelmundo@gmail.com",
				"lamejorempresadelmundo",
				""
		);

		controladorLaboral.altaTipoOferta(
				"HolaYChau", 
				"descripcion1", 
				12, 
				3, 
				(float)12, 
				LocalDate.now()
		);
		
		controladorLaboral.ingresarOfertaLaboral(
				"Tesla", 
				"HolaYChau", 
				"LaPeorOfertaDelMundo", 
				"descripcion12", 
				"09:30 - 18:30", 
				(float)140000, 
				"Montevideo", 
				"Montevideo", 
				LocalDate.now(), 
				new HashSet<String>(),
				"",
				EstadoOferta.Confirmada
		);
		
		controladorLaboral.postular(
				"Nico", 
				"LaPeorOfertaDelMundo", 
				"cvlaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaargo", 
				"motivacionlaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaargo", 
				LocalDate.now(),
				""
		);
		
		controladorLaboral.postular(
				"Hermione", 
				"LaPeorOfertaDelMundo", 
				"cvlaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaargo", 
				"motivacionlaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaargo", 
				LocalDate.now(),
				""
		);
		
		controladorLaboral.postular(
				"Ron", 
				"LaPeorOfertaDelMundo", 
				"cvlaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaargo", 
				"motivacionlaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaargo", 
				LocalDate.now(),
				""
		);
		
		DtOfertaCompleto data1 =  controladorLaboral.mostrarOfertaCompleto("LaPeorOfertaDelMundo");
	
		DtOferta data = data1.getOferta();
		assertTrue(data.getNombre().equals("LaPeorOfertaDelMundo"));
		assertTrue(data.getDescripcion().equals("descripcion12"));
		assertTrue(data.getHorarioTrabajo().equals("09:30 - 18:30"));
		assertEquals(data.getRemuneracion(), (float)140000);
		assertTrue(data.getCiudad().equals("Montevideo"));
		assertTrue(data.getDepartamento().equals("Montevideo"));
        assertTrue(data.getFechaAlta().equals(fecha1.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
		
		DtPostulacionCompleto[] arr = data1.getPostulaciones();
		for (int i = 0; i < 3; i++) {
			if (arr[i].getPostulante().equals("Nico") ||
					arr[i].getPostulante().equals("Hermione") || 
					arr[i].getPostulante().equals("Ron")) {
				assertTrue(arr[i].getPostulacion()
						.getCurriculum()
						.equals("cvlaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaargo"));
				assertTrue(arr[i]
						.getPostulacion()
						.getMotivacion()
						.equals("motivacionlaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaargo"));
				assertTrue(arr[i]
						.getPostulacion()
						.getFecha().equals(fecha1.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
			}
		}
	}
	
	@Test
	void postular_test2() {
		controladorLaboral.altaTipoOferta(
				"MeEstoyQuedandoSinCreatividad", 
				"descripcion1", 
				12, 
				3, 
				(float)12, 
				LocalDate.now()
		);
		controladorUsuario.ingresarPostulante(
				LocalDate.now(), 
				"Uruguayo", 
				"Joaquin", 
				"Barboza", 
				"Barbo", 
				"Joaquin12201@hotmail.com",
				"como_es_que_paso",
				""
		);
		controladorUsuario.ingresarEmpresa(
				"www.lamejorempresadelmundo.org", 
				"descripcion5", 
				"Martin", 
				"Martinez", 
				"EonMask", 
				"lamejorempresadelmundo@gmail.com",
				"nono",
				""
		);
		
		controladorLaboral.ingresarOfertaLaboral(
				"EonMask", 
				"MeEstoyQuedandoSinCreatividad", 
				"LaMejorOfertaDelMundo", 
				"descripcion12", 
				"09:30 - 18:30", 
				(float)140000, 
				"Montevideo", 
				"Montevideo", 
				LocalDate.now(), 
				new HashSet<String>(),
				"",
				EstadoOferta.Confirmada
		);
		
		controladorLaboral.postular(
				"Barbo", 
				"LaMejorOfertaDelMundo", 
				"cvlaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaargo", 
				"motivacionlaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaargo", 
				LocalDate.now(),
				""
		);
        assertThrows(Exception.class,
			    () -> controladorLaboral.postular(
					"Barbo", 
					"LaMejorOfertaDelMundo", 
					"otrocvlaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaargo", 
					"otramotivacionlaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaargo", 
					LocalDate.now(),
					"")
            );
	}		
	
	@Test
	void postular_test3() {
		controladorLaboral.altaTipoOferta(
				"Memori", 
				"descripcion1", 
				12, 
				3, 
				(float)12, 
				LocalDate.now()
		);
		controladorUsuario.ingresarEmpresa(
				"https://en.wikipedia.org/wiki/Tony_Hoare", 
				"descripcion5", 
				"Tony", 
				"Hoare", 
				"Openheimer", 
				"lalalalala@gmail.com",
				"mimimisexymimi",
				""
		);
		
		controladorLaboral.ingresarOfertaLaboral(
				"Openheimer", 
				"Memori", 
				"NikolaTesla", 
				"descripcion12", 
				"09:30 - 18:30", 
				(float)140000, 
				"Montevideo", 
				"Montevideo", 
				fecha2, 
				new HashSet<String>(),
				"",
				EstadoOferta.Confirmada
		);
        assertThrows(Exception.class,
			    () -> controladorLaboral.postular(
					"UnPostulanteNoExistente", 
					"NikolaTesla", 
					"otrocvlaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaargo", 
					"otramotivacionlaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaargo", 
					LocalDate.now(),
					""
			    )           
            );
	}
	
	@Test
	void postular_test4() {
        assertThrows(Exception.class,
			    () -> controladorLaboral.postular(
					"UnPostulanteNoExistente", 
					"UnOfertaNoExistente", 
					"otrocvlaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaargo", 
					"otramotivacionlaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaargo", 
					fecha1,
					""
			    )           
            );
	}
	
    /* Prueba de la funcion cuando todo sale bien. */
	@Test
	void ingresarOfertaLaboralConPaquete_test1() {
		controladorLaboral.altaTipoOferta(
				"YoungMiko", 
				"descripcion1", 
				12, 
				2, 
				(float)323, 
				fecha1
		);
		
		controladorLaboral.altaPaquete("Adan", "Eva", 111, (float)0.2, fecha1, "");
		
        controladorUsuario.ingresarEmpresa(
				"www.miami.com", 
				"descripcion5", 
				"German", 
				"Gimenez", 
				"Miami", 
				"miami@gmail.com",
				"huracan",
				""
		);

        HashSet<String> hola = new HashSet<String>();
        controladorLaboral.ingresarKeyword("UnaKeywordImponente");
        hola.add("UnaKeywordImponente");
        
    	controladorLaboral.comprarPaquete("Miami", "Adan", LocalDate.now());
    	
    	controladorLaboral.agregarTipoDePublicacionAPaquete("Adan", "YoungMiko", 1);

        controladorLaboral.ingresarOfertaLaboralConPaquete(
				"Miami", 
				"YoungMiko", 
				"NombreParaMiOfertitaQuerida", 
				"DescripcionParaMiOfertitaQuerida", 
				"10:20 - 18:20", 
				(float)30000, 
				"NY", 
				"Brasil", 
				LocalDate.now(), 
				hola,
				"",
				"Adan",
				EstadoOferta.Confirmada);
        
        assertThrows(DuplicationException.class,
                () -> controladorLaboral.ingresarOfertaLaboralConPaquete(
                    "Miami", 
                    "YoungMiko", 
                    "NombreParaMiOfertitaQuerida", 
                    "DescripcionParaMiOfertitaQuerida", 
                    "10:20 - 18:20", 
                    (float)30000, 
                    "NY", 
                    "Brasil", 
                    LocalDate.now(), 
                    hola,
                    "",
                    "Adan",
                    EstadoOferta.Confirmada)
            );
	}
	
    /* Prueba de la funcion cuando no existe la empresa. */
    @Test
	void ingresarOfertaLaboralConPaquete_test2() {
		controladorLaboral.altaTipoOferta(
				"ComunDenominadorH", 
				"descripcion1", 
				12, 
				2, 
				(float)323, 
				fecha1
		);
		
		controladorLaboral.altaPaquete("Evangelon 2", "MamaTengoMiedo", 111, (float)0.2, fecha1, "");
		
        assertThrows(NonExistentException.class,
                () -> controladorLaboral.ingresarOfertaLaboralConPaquete(
                    "EmpresaNoExistente", 
                    "ComunDenominadorH", 
                    "NombreGenericoParaUnaOfertaGenerica", 
                    "DescripcionGenericaParaUnaOfertaGenerica", 
                    "00:00 - 10:00", 
                    (float)70000, 
                    "Montevideo", 
                    "UnaCiudad",
                    LocalDate.now(), 
                    new HashSet<String>(),
                    "", 
                    "Evangelon 2", 
                    EstadoOferta.Confirmada)
            );
	}
    
    /* Prueba de la funcion cuando no existe el paquete. */
    @Test
	void ingresarOfertaLaboralConPaquete_test3() {
		controladorLaboral.altaTipoOferta(
				"ComunDenominadorP", 
				"descripcion1", 
				12, 
				2, 
				(float)323, 
				fecha1
		);
		
        controladorUsuario.ingresarEmpresa(
				"https://en.cppreference.com/w/cpp", 
				"descripcion5", 
				"German", 
				"Gimenez", 
				"cpp", 
				"cpp@gmail.com",
				"huracan",
				""
		);
		
        assertThrows(NonExistentException.class,
                () -> controladorLaboral.ingresarOfertaLaboralConPaquete(
                    "cpp", 
                    "ComunDenominadorP", 
                    "NombreGenericoParaUnaOfertaGenerica", 
                    "DescripcionGenericaParaUnaOfertaGenerica", 
                    "00:00 - 10:00", 
                    (float)70000, 
                    "Montevideo", 
                    "UnaCiudad",
                    LocalDate.now(), 
                    new HashSet<String>(),
                    "", 
                    "PaqueteNoExistente", 
                    EstadoOferta.Confirmada)
            );
	}
    
    /* Prueba de la funcion cuando no existe el tipo de oferta. */
    @Test
	void ingresarOfertaLaboralConPaquete_test4() {
		controladorLaboral.altaPaquete("Mitocondria", "MamaHeGomitao", 51, (float)0.1, fecha1, "");
		
        controladorUsuario.ingresarEmpresa(
				"https://en.cppreference.com/w/cpp", 
				"descripcion5", 
				"Mariana", 
				"Cabral", 
				"Mar", 
				"mm@hotmail.com",
				"contra",
				""
		);
		
        assertThrows(NonExistentException.class,
                () -> controladorLaboral.ingresarOfertaLaboralConPaquete(
                    "Mar", 
                    "EsteNoEsElNombreDeUnaOferta", 
                    "NombreGenericoParaUnaOfertaGenerica", 
                    "DescripcionGenericaParaUnaOfertaGenerica", 
                    "00:00 - 10:00", 
                    (float)70000, 
                    "Montevideo", 
                    "UnaCiudad",
                    LocalDate.now(), 
                    new HashSet<String>(),
                    "", 
                    "Mitocondria", 
                    EstadoOferta.Confirmada)
            );
	}
    
    /* Prueba de la funcion cuando remuneracion es menor que 0. */
    @Test
	void ingresarOfertaLaboralConPaquete_test5() {
		controladorLaboral.altaTipoOferta(
				"Neverkraker", 
				"estoy en tu cesped Neverkraker", 
				12, 
				2, 
				(float)323, 
				fecha1
		);
		
		controladorLaboral.altaPaquete("QuienSeFueYQuienSeQuedo", "Eva", 111, (float)0.2, fecha1, "");
		
        controladorUsuario.ingresarEmpresa(
				"www.miami.com", 
				"descripcion5", 
				"German", 
				"Gimenez", 
				"ShengLong", 
				"ShengLong@gmail.com",
				"huracan",
				""
		);
		
        assertThrows(OutOfMarginException.class,
                () -> controladorLaboral.ingresarOfertaLaboralConPaquete(
                    "ShengLong", 
                    "Neverkraker", 
                    "NombreGenericoParaUnaOfertaGenerica", 
                    "DescripcionGenericaParaUnaOfertaGenerica", 
                    "00:00 - 10:00", 
                    (float)-70, 
                    "Montevideo", 
                    "UnaCiudad",
                    LocalDate.now(), 
                    new HashSet<String>(),
                    "", 
                    "QuienSeFueYQuienSeQuedo", 
                    EstadoOferta.Confirmada)
            );
	}

@Test
	void listarOfertas_test1() {
        controladorLaboral.listarOfertas();
	}
    
    @Test
	void listarOfertasPorKeyword_test1() {
        
        HashSet<String> key = new HashSet<String>();
        controladorLaboral.ingresarKeyword("Nietzsche");
        key.add("Nietzsche");
        
		controladorLaboral.altaTipoOferta(
				"HowToOpenTheDoor", 
				"descripcion1", 
				12, 
				2, 
				(float)323, 
				fecha1
		);
		
        controladorLaboral.altaTipoOferta(
				"HowToCloseTheDoor", 
				"descripcion1", 
				12, 
				3, 
				(float)323, 
				fecha1
		);
		
        controladorUsuario.ingresarEmpresa(
				"https://www.youtube.com/watch?v=wFCWtqPEDAY", 
				"EsteSufrimientoEsInsoportable", 
				"NoPuedoMas", 
				"PorFavorMatenme", 
				"ParentescoMarsupial", 
				"ParentescoMarsupial@gmail.com",
				"huracan",
				""
		);

	    controladorLaboral.ingresarOfertaLaboral(
                "ParentescoMarsupial", 
                "HowToOpenTheDoor", 
                "MeEstoyPlanteandoMiExistencia", 
                "From childhood hours, i have not been as others were", 
                "10:00 - 10:22", 
                (float) 1000000, 
                "I have not seen..", 
                "..as others saw", 
                fecha1, 
                key, 
                "", 
                EstadoOferta.Confirmada 
            );
	    
        controladorLaboral.ingresarOfertaLaboral(
                "ParentescoMarsupial", 
                "HowToCloseTheDoor", 
                "GodIsDead", 
                "God remains dead and we have killed him", 
                "10:00 - 10:22", 
                (float) 1000000, 
                "how shall we comfort ourselves", 
                "the murderers of all murderers", 
                fecha1, 
                key, 
                "", 
                EstadoOferta.Confirmada 
            );
        
        List<DtOferta> lista = controladorLaboral.listarOfertasPorKeyword("Nietzsche");

        assertTrue(lista.get(1).getNombre().equals("GodIsDead"));
        assertTrue(lista.get(0).getNombre().equals("MeEstoyPlanteandoMiExistencia"));
	}

    @Test
	void mostrarOfertaCompletoPostulante_test1() {
		controladorLaboral.altaTipoOferta(
				"Asustador", 
				"Monster inc. esta buscando Asustadores", 
				12, 
				2, 
				(float)323, 
				fecha1
		);
		
        controladorUsuario.ingresarEmpresa(
				"https://duckduckgo.com/?t=ffab&q=pejelagarto&ia=web", 
				"Hijo: mama, se metio otro pejelagarto. Madre: otro pejelagarto? *procede a destruir al susodicho a golpes*", 
				"German", 
				"Gimenez", 
				"seniorWaternoose", 
				"Jefaso@monster.inc",
				"Henry",
				""
		);
		
        controladorUsuario.ingresarPostulante(
            LocalDate.now(), 
            "Uruguayo", 
            "Randal", 
            "Mcensi", 
            "Elasustapendejas", 
            "elasustapendejas@monster.inc", 
            "contra", 
            ""
        );

        controladorLaboral.ingresarOfertaLaboral(
                "seniorWaternoose",
                "Asustador", 
                "AsustadorProfesional", 
                "Gana plata haciendo que los pendejos se cagen de miedo.", 
                "Completo", 
                (float) 1000000, 
                "Estados Unidos", 
                "Tacuarembo", 
                LocalDate.now(), 
                new HashSet<String>(), 
                "", 
                EstadoOferta.Confirmada 
        );
        
        controladorLaboral.postular(
                "Elasustapendejas", 
                "AsustadorProfesional", 
                "aa", 
                "bb", 
                LocalDate.now(),
                ""
        );

        DtPostulacionCompleto[] arr = controladorLaboral
            .mostrarOfertaCompletoPostulante("AsustadorProfesional", "Elasustapendejas")
            .getPostulaciones();

        assertTrue(arr.length == 1);
        
        /* hay un problema aca pero lo de arriba de bien. */
        assertTrue(arr[0].getPostulante().equals("Elasustapendejas"));
	}

	@Test
	void listarOfertasPorEmpresa_test1() {
		controladorLaboral.altaTipoOferta(
				"HowToShutTheFuckingUp", 
				"descripcion1", 
				12, 
				2, 
				(float)323, 
				fecha1
		);
		
        controladorLaboral.altaTipoOferta(
				"ILikeToKillPeople", 
				"descripcion1", 
				12, 
				3, 
				(float)323, 
				fecha1
		);
		
        controladorUsuario.ingresarEmpresa(
				"https://www.youtube.com/watch?v=wFCWtqPEDAY", 
				"EsteSufrimientoEsInsoportable", 
				"Luciano", 
				"Murciano", 
				"BuzzLightYear", 
				"NoSeQueNombreDeEmailPoner@hotmail.com",
				"huracan",
				""
		);

	    controladorLaboral.ingresarOfertaLaboral(
                "BuzzLightYear", 
                "HowToShutTheFuckingUp", 
                "CosasQuePasan", 
                "From childhood hours, i have not been as others were", 
                "10:00 - 10:22", 
                (float) 1000000, 
                "I have not seen..", 
                "..as others saw", 
                fecha1, 
                new HashSet<String>(), 
                "", 
                EstadoOferta.Ingresada
            );
	    
        controladorLaboral.ingresarOfertaLaboral(
                "BuzzLightYear", 
                "ILikeToKillPeople", 
                "EstoNoMeLoEsperaba", 
                "God remains dead and we have killed him", 
                "10:00 - 10:22", 
                (float) 1000000, 
                "how shall we comfort ourselves", 
                "the murderers of all murderers", 
                fecha1, 
                new HashSet<String>(), 
                "", 
                EstadoOferta.Confirmada 
            );
        
        List<DtOferta> lista = controladorLaboral.listarOfertasPorEmpresa("BuzzLightYear", EstadoOferta.Confirmada);
        assertTrue(lista.get(0).getNombre().equals("EstoNoMeLoEsperaba"));
        
        lista = controladorLaboral.listarOfertasPorEmpresa("BuzzLightYear", EstadoOferta.Ingresada);
        assertTrue(lista.get(0).getNombre().equals("CosasQuePasan"));
	}
	
	@Test
	void getPostulanteDeOferta_test1() {
		controladorUsuario.ingresarPostulante(
				LocalDate.now(), 
				"Uruguayo", 
				"Javier", 
				"gutierrez", 
				"EnsaladaConPapas", 
				"Esquizofrenia112@hotmail.com",
				"como_es_que_paso",
				""
		);

        controladorLaboral.altaTipoOferta(
				"BuenasTardesCompañeroAYAYAYAY", 
				"descripcion1", 
				12, 
				3, 
				(float)323, 
				LocalDate.now()
		);
		
        controladorUsuario.ingresarEmpresa(
				"https://www.youtube.com/watch?v=jIqA5sg5gZM", 
				"COmandoAlAire", 
				"Enciclopedia", 
				"Martinez", 
				"BarraBraba", 
				"CandidatoAPprecidente@hotmail.com",
				"huracan",
				""
		);

        assertThrows(NonExistentException.class,
                () -> controladorLaboral.getPostulanteDeOferta("EnsaladaConPapas", "EstupidezCronica")
            );

	    controladorLaboral.ingresarOfertaLaboral(
                "BarraBraba", 
                "BuenasTardesCompañeroAYAYAYAY", 
                "EstupidezCronica", 
                "Hail Hitler", 
                "08:00 - 18:00", 
                (float) 1000, 
                "I have not seen..", 
                "..as others saw", 
                LocalDate.now(), 
                new HashSet<String>(), 
                "", 
                EstadoOferta.Confirmada
            );
		
        controladorLaboral.postular(
				"EnsaladaConPapas", 
				"EstupidezCronica", 
				"cvlaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaargo", 
				"motivacionlaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaargo", 
                LocalDate.now(), 
				""
		);

        DtPostulacionCompleto data = controladorLaboral.getPostulanteDeOferta("EnsaladaConPapas", "EstupidezCronica");

        assertTrue(data.getPostulante().equals("EnsaladaConPapas"));
        assertTrue(data.getOferta().equals("EstupidezCronica"));
	}
	
	@Test
	void listarTipoDeOfertaFaltante_test1() {
        controladorLaboral.altaPaquete(
                "EstupefacientesDeAzucar", 
                "Binchilin", 
                123, 
                (float)0.243, 
                fecha1, 
                "");

		controladorLaboral.listarTipoDeOfertaFaltante("EstupefacientesDeAzucar");
	}
	
	@Test
	void cambiarEstadoOferta_test1() {
        controladorLaboral.altaTipoOferta(
				"LikeABOUNERHAHAHAHA", 
				"descripcion1", 
				12, 
				1, 
				(float)323, 
				fecha1
		);
		
        controladorUsuario.ingresarEmpresa(
				"https://scp-wiki.wikidot.com/tuftos-proposal", 
				"ElMejorSCP", 
				"Andrea", 
				"Gimenez", 
				"LaComilona", 
				"LaComilona@hotmail.com",
				"huracan",
				""
		);

	    controladorLaboral.ingresarOfertaLaboral(
                "LaComilona", 
                "LikeABOUNERHAHAHAHA", 
                "Pancer", 
                "Hail Hitler", 
                "08:00 - 18:00", 
                (float) 1000, 
                "I have not seen..", 
                "..as others saw", 
                fecha1, 
                new HashSet<String>(), 
                "", 
                EstadoOferta.Confirmada
            );

	    controladorLaboral.cambiarEstadoOferta("Pancer", EstadoOferta.Rechazada);
	    
        assertTrue(controladorLaboral.mostrarOferta("Pancer").getEstado() == EstadoOferta.Rechazada);
	}
}
