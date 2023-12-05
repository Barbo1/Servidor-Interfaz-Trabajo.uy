package data;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import model.datatype.*;
import model.interfacess.IcontroladorLaboral;
import model.interfacess.IcontroladorUsuario;
import model.utils.Strings;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.CodeSource;
import java.security.ProtectionDomain;

public class cargarDatos {
	private BufferedReader lector;

	private String linea;
	private String[] partes;
	private IcontroladorLaboral controladorLaboral;
	private IcontroladorUsuario controladorUsuario;
	private Map<String, String[]> keywords;
	private Map<String, String[]> ofertas;
	private Map<String, String[]> ofertasKeywords;
	private Map<String, String[]> paquetes;
	private Map<String, String[]> compraPaquetes;
	private Map<String, String[]> postulaciones;
	private Map<String, String[]> tipoOferta;
	private Map<String, String[]> tipoOfertasDePaquete;
	private Map<String, String[]> usuarios;
	private Map<String, String[]> usuariosEmpresas;
	private Map<String, String[]> usuariosPostulantes;
	private Map<String, String[]> seguidores;
	private Map<String, String[]> favoritos;
	private List<String[]> resultadosPostulacion;


    private Strings utils = new Strings();

	
	public cargarDatos(IcontroladorLaboral cl1, IcontroladorUsuario cu1) {
		controladorLaboral = cl1;
		controladorUsuario = cu1;
		
		try {
			/*Guardo las ofertas*/
			InputStream inputStream = cargarDatos.class.getResourceAsStream("OfertasLaborales.csv");
			lector = new BufferedReader(new InputStreamReader(inputStream));
			Map<String, String[]> OfertasMap = new HashMap<>();
			linea = lector.readLine();
			while ((linea = lector.readLine()) != null) {
	            partes = linea.split(";");
	            OfertasMap.put(partes[0], partes);
	        }
	        lector.close();
	        ofertas = OfertasMap;
			
	        /*Guardo los usuarios*/
	        
	        inputStream = cargarDatos.class.getResourceAsStream("Usuarios.csv");
			lector = new BufferedReader(new InputStreamReader(inputStream));
			Map<String, String[]> usuariosMap = new HashMap<>();
			linea = lector.readLine();
	        while ((linea = lector.readLine()) != null) {
	            partes = linea.split(";");
	            usuariosMap.put(partes[0], partes);
	        }
	        lector.close();
	        usuarios = usuariosMap;
	        
	        /*Pido la informacion especifica de los postulantes*/
	        inputStream = cargarDatos.class.getResourceAsStream("Usuarios-Postulantes.csv");
			lector = new BufferedReader(new InputStreamReader(inputStream));
			Map<String, String[]> usuariosMapPostulantes = new HashMap<>();
			linea = lector.readLine();
	        while ((linea = lector.readLine()) != null) {
	            partes = linea.split(";");
	            usuariosMapPostulantes.put(partes[0], partes);
	        }
	        lector.close();
	        usuariosPostulantes = usuariosMapPostulantes;
	        
	        /*Pido la informacion especifica de las empresas*/
	        
	        inputStream = cargarDatos.class.getResourceAsStream("Usuarios-Empresas.csv");
			lector = new BufferedReader(new InputStreamReader(inputStream));
			Map<String, String[]> usuariosMapEmpresas = new HashMap<>();
			linea = lector.readLine();
	        while ((linea = lector.readLine()) != null) {
	            partes = linea.split(";");
	            usuariosMapEmpresas.put(partes[0], partes);
	        }
	        lector.close();
	        usuariosEmpresas = usuariosMapEmpresas;
	        
	        /*Guardo los tipos de oferta*/
	        
	        inputStream = cargarDatos.class.getResourceAsStream("TipoPublicacion.csv");
			lector = new BufferedReader(new InputStreamReader(inputStream));
			Map<String, String[]> tipoOfertaMap = new HashMap<>();
			linea = lector.readLine();
			while ((linea = lector.readLine()) != null) {
	            partes = linea.split(";");
	            tipoOfertaMap.put(partes[0], partes);
	        }
	        lector.close();
	        tipoOferta = tipoOfertaMap;
	        
	        /*Guardo los oferta-keyword*/
	        inputStream = cargarDatos.class.getResourceAsStream("OfertasLaboralesKeywords.csv");
			lector = new BufferedReader(new InputStreamReader(inputStream));
			Map<String, String[]> ofertaKeywordMap = new HashMap<>();
			linea = lector.readLine();
			while ((linea = lector.readLine()) != null) {
	            partes = linea.split(";");
	            ofertaKeywordMap.put(partes[0], partes);
	        }
	        lector.close();
	        ofertasKeywords = ofertaKeywordMap;
	        
	        /*Guardo las keyword*/
	        inputStream = cargarDatos.class.getResourceAsStream("Keywords.csv");
	        lector = new BufferedReader(new InputStreamReader(inputStream));
			Map<String, String[]> keywordMap = new HashMap<>();
			linea = lector.readLine();
			while ((linea = lector.readLine()) != null) {
	            partes = linea.split(";");
	            keywordMap.put(partes[0], partes);
	        }
	        lector.close();
	        keywords = keywordMap;
	        
	        /*Guardo los paquetes*/
	        inputStream = cargarDatos.class.getResourceAsStream("Paquetes.csv");
			lector = new BufferedReader(new InputStreamReader(inputStream));
			Map<String, String[]> paquetesMap = new HashMap<>();
			linea = lector.readLine();
			while ((linea = lector.readLine()) != null) {
	            partes = linea.split(";");
	            paquetesMap.put(partes[0], partes);
	        }
	        lector.close();
	        paquetes = paquetesMap;
	        
	        /* Guardo la informacion de links entre paquetes y tipos de oferta*/
	        
	        inputStream = cargarDatos.class.getResourceAsStream("TiposPublicacionPaquetes.csv");
			lector = new BufferedReader(new InputStreamReader(inputStream));
			Map<String, String[]> paqTipOferMap = new HashMap<>();
			linea = lector.readLine();
			while ((linea = lector.readLine()) != null) {
	            partes = linea.split(";");
	            paqTipOferMap.put(partes[0], partes);
	        }
	        lector.close();
	        tipoOfertasDePaquete = paqTipOferMap;
	        
	        /* Guardo la informacion de compra paquete*/
	        
	        inputStream = cargarDatos.class.getResourceAsStream("PaquetesCompras.csv");
			lector = new BufferedReader(new InputStreamReader(inputStream));
			Map<String, String[]> PCMap = new HashMap<>();
			linea = lector.readLine();
			while ((linea = lector.readLine()) != null) {
	            partes = linea.split(";");
	            PCMap.put(partes[0], partes);
	        }
	        lector.close();
	        compraPaquetes = PCMap;
	        
	        /* Guardo la informacion de postulaciones*/
	        
	        inputStream = cargarDatos.class.getResourceAsStream("Postulaciones.csv");
			lector = new BufferedReader(new InputStreamReader(inputStream));
			Map<String, String[]> PMap = new HashMap<>();
			linea = lector.readLine();
			while ((linea = lector.readLine()) != null) {
	            partes = linea.split(";");
	            PMap.put(partes[0], partes);
	        }
	        lector.close();
	        postulaciones = PMap;
	        
	        /* Guardo la informacion de ofertas favoritas*/
	        
	        inputStream = cargarDatos.class.getResourceAsStream("PostulantesOfertasLaboralesFavoritas.csv");
			lector = new BufferedReader(new InputStreamReader(inputStream));
			Map<String, String[]> Fmap = new HashMap<>();
			linea = lector.readLine();
			while ((linea = lector.readLine()) != null) {
	            partes = linea.split(";");
	            Fmap.put(partes[0], partes);
	        }
	        lector.close();
	        favoritos = Fmap;
	        
	        /* Guardo la informacion de usuarios seguidos*/
	        
	        inputStream = cargarDatos.class.getResourceAsStream("ResultadoPostulacion.csv");
			lector = new BufferedReader(new InputStreamReader(inputStream));
			List<String[]> RPmap = new ArrayList();
			linea = lector.readLine();
			while ((linea = lector.readLine()) != null) {
	            partes = linea.split(";");
	            RPmap.add(partes);
	        }
	        lector.close();
	        resultadosPostulacion = RPmap;
	        
	        inputStream = cargarDatos.class.getResourceAsStream("Usuarios-Seguidores.csv");
			lector = new BufferedReader(new InputStreamReader(inputStream));
			Map<String, String[]> USmap = new HashMap<>();
			linea = lector.readLine();
			while ((linea = lector.readLine()) != null) {
	            partes = linea.split(";");
	            USmap.put(partes[0], partes);
	        }
	        lector.close();
	        seguidores = USmap;
	        
	        /* Guardo la informacion de resultados de postulacion*/
	        
	        
	        
		}catch(Exception e) {
			
		}
		
	}
	

	public void cargaKeyword() {
		for (Map.Entry<String, String[]> key : keywords.entrySet()) {
			String[] info = key.getValue();
			controladorLaboral.ingresarKeyword(info[1]);
		}
	}
	

	public void cargaPaquetes() {
		for (Map.Entry<String, String[]> paq : paquetes.entrySet()) {
			String[] info = paq.getValue();
			String[] fecha = info[5].split("/");
			controladorLaboral.altaPaquete(info[1], info[2], Integer.valueOf(info[3].split(" ")[0]) , Float.valueOf(info[4])/100, LocalDate.of(Integer.valueOf(fecha[2]), Integer.valueOf(fecha[1]), Integer.valueOf(fecha[0])), utils.unico(info[1]) + ".jpg");		}
	}
	

	public void cargaTipoDeOferta() {
		for (Map.Entry<String, String[]> aux : tipoOferta.entrySet()) {
			String[] info = aux.getValue();
			String[] fecha = info[6].split("/");
			controladorLaboral.altaTipoOferta(info[1], info[2], Integer.valueOf(info[4]), Integer.valueOf(info[3]), Float.valueOf(info[5]), LocalDate.of(Integer.valueOf(fecha[2]), Integer.valueOf(fecha[1]), Integer.valueOf(fecha[0])));
			/*  */
		}
	}
	
	public void cargaUsuarios() {  
        for (Map.Entry<String, String[]> user : usuarios.entrySet()) {
            String info[] = user.getValue();
            String[] infoEspecifica;
            if (info[1].equals("P")) {
            	infoEspecifica = usuariosPostulantes.get(info[0]);
            	String[] fecha = infoEspecifica[1].split("/");
				controladorUsuario.ingresarPostulante(LocalDate.of(Integer.valueOf(fecha[2]), Integer.valueOf(fecha[1]), Integer.valueOf(fecha[0])), infoEspecifica[2], info[3], info[4], info[2], info[5], info[6], utils.unico(info[2]) + ".jpg");
            } else {
            	infoEspecifica = usuariosEmpresas.get(info[0]);
            	controladorUsuario.ingresarEmpresa(infoEspecifica[2], infoEspecifica[1], info[3], info[4], info[2], info[5], info[6], utils.unico(info[2]) + ".jpg");
            }
        }
	}
	
	public void cargaPaquetes_TipoOferta() {
        for (Map.Entry<String, String[]> aux : tipoOfertasDePaquete.entrySet()) {
            String info[] = aux.getValue();
            String[] infoPaquete = paquetes.get(info[1].replaceAll("\\s", ""));
            String[] infoTipoDeOferta = tipoOferta.get(info[2].replaceAll("\\s", ""));
            controladorLaboral.agregarTipoDePublicacionAPaquete(infoPaquete[1], infoTipoDeOferta[1], Integer.valueOf(info[3].strip()));
        }
	}

	public void cargaOfertas(){
		for (Map.Entry<String, String[]> aux : ofertas.entrySet()) {
            String info[] = aux.getValue();
            String[] keys = ofertasKeywords.get(info[0]);
            String[] keys2 = keys[1].split(",");
            Set<String> key = new HashSet<>();
            for (String k: keys2)
            	key.add(keywords.get(k.replaceAll("\\s", ""))[1]);
        	String[] fecha = info[9].split("/");
        	EstadoOferta est = null;
        	switch (info[10]) {
        	case "Ingresada":
        		est = EstadoOferta.Ingresada;
        		break;
        	case "Confirmada":
        		est = EstadoOferta.Confirmada;
        		break;
        	case "Rechazada":
        		est = EstadoOferta.Rechazada;
        		break;
        	case "Finalizada":
        		est = EstadoOferta.Finalizada;
        		break;
        	default:
        		break;
        	}
        	if (info[11].equals("Sin paquete")) {
        		
        		controladorLaboral.ingresarOfertaLaboral(usuarios.get(info[7])[2], tipoOferta.get(info[8])[1], info[1], info[2], info[5], Float.valueOf(info[6]), info[4], info[3], LocalDate.of(Integer.valueOf(fecha[2]), Integer.valueOf(fecha[1]), Integer.valueOf(fecha[0])), key, utils.unico(info[1]) + ".jpg" , est);
        	}else 
        		try {
					controladorLaboral.ingresarOfertaLaboralConPaquete(usuarios.get(info[7])[2], tipoOferta.get(info[8])[1], info[1], info[2], info[5], Float.valueOf(info[6]), info[4], info[3], LocalDate.of(Integer.valueOf(fecha[2]), Integer.valueOf(fecha[1]), Integer.valueOf(fecha[0])), key, utils.unico(info[1]) + ".jpg", paquetes.get(info[11])[1], est);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	controladorLaboral.setVisita(info[1], Integer.valueOf(info[13]));
        	
        	}
	}
		
	public void cargaPostulaciones() {
		for (Map.Entry<String, String[]> aux : postulaciones.entrySet()) {
            String info[] = aux.getValue();
			String[] fecha = info[4].split("/");
			String link = info[6];
			if(link.equals("Sin Video")) {
				link="";
			}
			controladorLaboral.postularCargaDatos(usuarios.get(info[1])[2], ofertas.get(info[5])[1], info[2], info[3], LocalDate.of(Integer.valueOf(fecha[2]), Integer.valueOf(fecha[1]), Integer.valueOf(fecha[0])),link);
		}	
	}
	
	public void cargaOrdenPostulacion() {
		for (String[] info : resultadosPostulacion) {
			controladorLaboral.cargarOrdenAPost(Integer.valueOf(info[2]), ofertas.get(info[0])[1], usuarios.get(info[1])[2]);
		}
	}
	
	public void cargaCompraPaquete() {
		for (Map.Entry<String, String[]> aux : compraPaquetes.entrySet()) {
			String[] info = aux.getValue();
			String[] fecha = info[3].split("/");
			try {
				controladorLaboral.comprarPaquete(usuarios.get(info[1])[2], paquetes.get(info[2])[1], LocalDate.of(Integer.valueOf(fecha[2]), Integer.valueOf(fecha[1]), Integer.valueOf(fecha[0])));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void cargarOfertasFavoritas() {
		for (Map.Entry<String, String[]> aux : favoritos.entrySet()) {
			String[] info = aux.getValue();
			controladorLaboral.addOfertaFavorito(ofertas.get(info[1])[1], usuarios.get(info[0])[2]);;
		}
	}
	
	public void cargarSeguidos() {
		for (Map.Entry<String, String[]> aux : seguidores.entrySet()) {
			String[] info = aux.getValue();
			controladorUsuario.setFollow(usuarios.get(info[1])[2], usuarios.get(info[2])[2]);
		}

	}
	
	public void cargarImagenes() {
	    String userHomeDir = System.getProperty("user.home");
	    String directorioDestino = userHomeDir + "/TrabajoUY";
	    String[] carpetas = {"usuarios", "ofertas", "paquetes"};

	    // Crear directorio principal si no existe
	    Path pathTrabajoUY = Paths.get(directorioDestino);
	    if (!Files.exists(pathTrabajoUY)) {
	        try {
	            Files.createDirectories(pathTrabajoUY);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return; // Salir si no se pudo crear el directorio principal
	        }
	    }

	    for (String carpeta : carpetas) {
	        File destino = new File(directorioDestino + "/media/" + carpeta);
	        destino.mkdirs();
	    }

	    for (String carpeta : carpetas) {
	        try (ZipFile zipFile = new ZipFile(cargarDatos.class.getProtectionDomain().getCodeSource().getLocation().getPath())) {
	            Enumeration<? extends ZipEntry> entries = zipFile.entries();
	            while (entries.hasMoreElements()) {
	                ZipEntry entry = entries.nextElement();
	                if (entry.getName().startsWith("media/" + carpeta + "/")) {
	                    String nombreArchivo = entry.getName().substring(entry.getName().lastIndexOf('/') + 1);
	                    File destino = new File(directorioDestino + "/media/" + carpeta, nombreArchivo);

	                    // Verificar si el archivo ya existe antes de copiarlo
	                    if (!destino.exists()) {
	                        try (InputStream inputStream = zipFile.getInputStream(entry)) {
	                            Files.copy(inputStream, destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
	                        }
	                    }
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
}
