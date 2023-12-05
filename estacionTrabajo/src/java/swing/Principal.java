package swing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import data.cargarDatos;
import model.clases.Fabrica;
import model.exceptions.NonExistentException;
import model.interfacess.IcontroladorLaboral;
import model.interfacess.IcontroladorUsuario;
import publicador.WebServices;

public class Principal {

    private JFrame frmGestionDeUsuarios;
    private IcontroladorUsuario IU;
    private IcontroladorLaboral IL;
    private CrearPaquete CrearPaquete;
    private AltaDeTipoDeOferta AltaTipoOfertaFrame;
    private AltaUsuario altaUsuarioInternalFrame;
    private ModificarUsuario modificarUsuarioInternalFrame;
    private ConsultaDeUsuario consultaDeUsuarioInternalFrame;
    private ConsultaDeOferta consultaDeOferta;
    private ATdePdeOLaP ATdePdeOLaPInternalFrame;
    private AltaDeOferta AltaDeOfertaFrame;
    private PostulacionAOfertaLaboral PostulacionFrame;
    private ConsultaDePaquetesDeTipos ConsultaPaquetesFrame;
    private EstadoOfertaLaboral estadoOfertaFrame;
    private OfertasMasVisitadas ofertasMasVisitadasFrame;
    private Publicador publicador;
    private boolean datosCargados;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal window = new Principal();
                    window.frmGestionDeUsuarios.setVisible(true);
                   
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     * @throws PropertyVetoException 
     */
    
    public void limpiarImagenes() {
    	String directorioActual = System.getProperty("user.home");
        String fullPath = directorioActual + "/TrabajoUY/media";
        System.out.println(fullPath);
    	File carpeta = new File(fullPath + "/usuarios");
        if (carpeta.exists() && carpeta.isDirectory()) {
        	File[] archivos = carpeta.listFiles();
            if (archivos != null)
                for (File archivo : archivos)
                    if (archivo.isFile())
                        archivo.delete(); 
        }
        carpeta = new File(fullPath + "/ofertas");
        if (carpeta.exists() && carpeta.isDirectory()) {
        	File[] archivos = carpeta.listFiles();
            if (archivos != null)
                for (File archivo : archivos)
                    if (archivo.isFile()) 
                        archivo.delete(); 
        }
    }
    
    public Principal() throws PropertyVetoException {
        initialize();
        datosCargados = false;
        // Inicialización
        Fabrica fabrica = Fabrica.getInstance();
        IU = fabrica.getIControladorUsuario();
        IL = fabrica.getIcontroladorLaboral();
        frmGestionDeUsuarios.getContentPane().setLayout(null);
        frmGestionDeUsuarios.getContentPane().setLayout(null);
        
        //Consulta de usuario
        consultaDeUsuarioInternalFrame = new ConsultaDeUsuario(IU, IL);
        consultaDeUsuarioInternalFrame.setLocation(12, 12);
        frmGestionDeUsuarios.getContentPane().setLayout(null);
        frmGestionDeUsuarios.getContentPane().add(consultaDeUsuarioInternalFrame);
        // Se crea el InternalFrame
        
        // Alta Tipo Oferta.
        AltaTipoOfertaFrame = new AltaDeTipoDeOferta(IL);
        AltaTipoOfertaFrame.setBounds(83, 72, 342, 377);
        frmGestionDeUsuarios.getContentPane().add(AltaTipoOfertaFrame);
        AltaTipoOfertaFrame.setVisible(false);
        
        //Alta de Oferta
        AltaDeOfertaFrame = new AltaDeOferta(IU,IL);
        AltaDeOfertaFrame.setLocation(12, 12);
        AltaDeOfertaFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().setLayout(null);
        frmGestionDeUsuarios.getContentPane().add(AltaDeOfertaFrame);
        
        //Agregar tipo de publiacion de oferta laboral a paquete
        ATdePdeOLaPInternalFrame = new ATdePdeOLaP(IL);
        ATdePdeOLaPInternalFrame.setVisible(false);
        ATdePdeOLaPInternalFrame.setLocation(43, 80);
        frmGestionDeUsuarios.getContentPane().setLayout(null);
        frmGestionDeUsuarios.getContentPane().add(ATdePdeOLaPInternalFrame);

        // Alta de Usuario
	    altaUsuarioInternalFrame = new AltaUsuario(IU);
	    altaUsuarioInternalFrame.setLocation(12, 12);
	    altaUsuarioInternalFrame.setVisible(false);
	    frmGestionDeUsuarios.getContentPane().setLayout(null);
	    frmGestionDeUsuarios.getContentPane().add(altaUsuarioInternalFrame);
	    
	    // Modificar Usuario     
	    modificarUsuarioInternalFrame = new ModificarUsuario(IU);
	    modificarUsuarioInternalFrame.setLocation(12, 12);
	    modificarUsuarioInternalFrame.setVisible(false);
	    frmGestionDeUsuarios.getContentPane().setLayout(null);
	    frmGestionDeUsuarios.getContentPane().add(modificarUsuarioInternalFrame);   
    
	    // Crear Paquete de tipos de oferta. 
	    CrearPaquete = new CrearPaquete(IL);
	    CrearPaquete.setLocation(43, 80);
	    CrearPaquete.setVisible(false);
        frmGestionDeUsuarios.getContentPane().setLayout(null);
        frmGestionDeUsuarios.getContentPane().add(CrearPaquete);
        
        
     // Postulacion a oferta laboral 
        PostulacionFrame = new PostulacionAOfertaLaboral(IU,IL);
	    PostulacionFrame.setLocation(43, 80);
	    PostulacionFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().setLayout(null);
        frmGestionDeUsuarios.getContentPane().add(PostulacionFrame);

        // Consulta de oferta. 
        consultaDeOferta = new ConsultaDeOferta(IU, IL);
        consultaDeOferta.setLocation(43, 80);
        consultaDeOferta.setVisible(false);
        frmGestionDeUsuarios.getContentPane().setLayout(null);
        frmGestionDeUsuarios.getContentPane().add(consultaDeOferta);
        
     // Consulta de paquete. 
        ConsultaPaquetesFrame = new ConsultaDePaquetesDeTipos(IL);
        ConsultaPaquetesFrame.setLocation(43, 80);
        ConsultaPaquetesFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().setLayout(null);
        frmGestionDeUsuarios.getContentPane().add(ConsultaPaquetesFrame);
        
     // Confirmar o rechazar oferta laboral. 
        estadoOfertaFrame = new EstadoOfertaLaboral(IL, IU);
        estadoOfertaFrame.setLocation(43, 80);
        estadoOfertaFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().setLayout(null);
        frmGestionDeUsuarios.getContentPane().add(estadoOfertaFrame);
        
       //Ofertas mas visitadas.
        ofertasMasVisitadasFrame = new OfertasMasVisitadas(IL);
        ofertasMasVisitadasFrame.setLocation(10, 10);
        ofertasMasVisitadasFrame.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(ofertasMasVisitadasFrame);   
        
        publicador = new Publicador();
        publicador.setLocation(10, 10);
        publicador.setVisible(false);
        frmGestionDeUsuarios.getContentPane().add(publicador); 
        
    }
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        
    	/////////////////////////////////////////////////////// Frame //////////////////////////////////////////////////////////////////////// 
        frmGestionDeUsuarios = new JFrame();
        frmGestionDeUsuarios.setTitle("Administracion trabajo.uy");
        frmGestionDeUsuarios.setBounds(100, 100, 1357, 765);
        frmGestionDeUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMenuBar menuBar = new JMenuBar();
        frmGestionDeUsuarios.setJMenuBar(menuBar);

        /////////////////////////////////////////////////////// Menu Sistema ////////////////////////////////////////////////////////////////////////
        JMenu menuSistema = new JMenu("Sistema");
        menuBar.add(menuSistema);

        JMenuItem menuSalir = new JMenuItem("Salir");
        menuSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Salgo de la aplicación
                frmGestionDeUsuarios.setVisible(false);
                frmGestionDeUsuarios.dispose();
            }
        });
        menuSistema.add(menuSalir);
        
        /////////////////////////////////////////////////////// Menu Usuarios ////////////////////////////////////////////////////////////////////////
        JMenu menuUsuarios = new JMenu("Usuarios");
        menuBar.add(menuUsuarios);
        
        // Alta de Usuario
        JMenuItem menuItemAltaDeUsuario = new JMenuItem("Alta de Usuario");
        menuItemAltaDeUsuario.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		altaUsuarioInternalFrame.setVisible(true);
        		altaUsuarioInternalFrame.limpiarFormulario();
      	}
        });
        menuUsuarios.add(menuItemAltaDeUsuario);
        
        // Modificar Usuario
        JMenuItem menuItemModificarUsuario = new JMenuItem("Modificar Usuario");
        menuItemModificarUsuario.addActionListener(new ActionListener() {      	
            public void actionPerformed(ActionEvent e) {
            	try {
            		modificarUsuarioInternalFrame.listarUsuarios();
                	modificarUsuarioInternalFrame.setVisible(true);
            	} catch (NonExistentException ex) {
            		JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            	}
            }
        });
        menuUsuarios.add(menuItemModificarUsuario);
        
     // Consulta de usuario
        JMenuItem menuItemConsultaDeUsuario = new JMenuItem("Consulta de usuario");
        menuItemConsultaDeUsuario.addActionListener(new ActionListener() {
        	
            public void actionPerformed(ActionEvent e) {
            	try {
					consultaDeUsuarioInternalFrame.cargarUsuarios();
					consultaDeUsuarioInternalFrame.setVisible(true);
				} catch (NonExistentException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
            
            }
        });
        menuUsuarios.add(menuItemConsultaDeUsuario);
        
        /////////////////////////////////////////////////////// Menu Ofertas ////////////////////////////////////////////////////////////////////////
        JMenu menuOfertas = new JMenu("Ofertas");
        menuBar.add(menuOfertas);
        
        JMenuItem menuItemAgregarOferta = new JMenuItem("Alta de oferta");
        menuItemAgregarOferta.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			AltaDeOfertaFrame.limpiarFormulario();
        			AltaDeOfertaFrame.limpiarKeywords();
            		AltaDeOfertaFrame.cargarEmpresas();       // Tiene throw
            		AltaDeOfertaFrame.cargarTiposDeOfertas(); // Tiene throw
            		AltaDeOfertaFrame.limpiarEmpresaTipoDeOfertaEnCombobox();
            		AltaDeOfertaFrame.cargarFechaActual();
            		AltaDeOfertaFrame.setVisible(true);
				} catch (NonExistentException e1) { // Si no hay empresas o tipos de ofertas en el sistema no se abre la ventana
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
        		AltaDeOfertaFrame.cargarKeywords();
        	}
        });
        menuOfertas.add(menuItemAgregarOferta);
        
        JMenuItem menuItemConsultarOferta = new JMenuItem("Consultar oferta");
        menuItemConsultarOferta.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			consultaDeOferta.Ingresar();
        			consultaDeOferta.setVisible(true);
        		} catch (NonExistentException ex) {
        			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
        menuOfertas.add(menuItemConsultarOferta);
        
        JMenuItem menuItemEstadoOferta = new JMenuItem("Cambiar estado de oferta");
        menuItemEstadoOferta .addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			estadoOfertaFrame.cargarEmpresas();
        			estadoOfertaFrame.setVisible(true);
				} catch (NonExistentException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
        	}
        });
        menuOfertas.add(menuItemEstadoOferta);
        
        
        // Alta de tipo de oferta 
        JMenuItem menuItemAltaTipoOferta = new JMenuItem("Alta de tipo de oferta");
        menuItemAltaTipoOferta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	AltaTipoOfertaFrame.setVisible(true);
            }
        });
        menuOfertas.add(menuItemAltaTipoOferta);
        
        
     // Postulacion a Oferta Laboral
        JMenuItem menuItemPostulacionAOfertaLaboral = new JMenuItem("Postulacion a Oferta Laboral");
        menuItemPostulacionAOfertaLaboral.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			PostulacionFrame.cargarEmpresas();
            		PostulacionFrame.cargarPostulantes(); //PQ DA ERROR?
            		PostulacionFrame.cargarFechaActual();
            		PostulacionFrame.limpiarFormulario();
            		
            		PostulacionFrame.setVisible(true);
            		
        		}catch (NonExistentException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
        		
        		
      	}
        });
        menuOfertas.add(menuItemPostulacionAOfertaLaboral);
        
        JMenuItem menuItemOfertasMasVisitadas = new JMenuItem("Ofertas más visitadas");
        menuItemOfertasMasVisitadas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ofertasMasVisitadasFrame.cargarOfertas();
            	ofertasMasVisitadasFrame.setVisible(true);
            }
        });
        menuOfertas.add(menuItemOfertasMasVisitadas);
        
        
        
        /////////////////////////////////////////////////////// Menu Paquetes ////////////////////////////////////////////////////////////////////////
        JMenu menuPaquetes = new JMenu("Paquetes");
        menuBar.add(menuPaquetes);

        JMenuItem mntmNewMenuItem = new JMenuItem("Agregar Tipo de publicación de Oferta Laboral a Paquete");
        mntmNewMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					ATdePdeOLaPInternalFrame.cargarPaquetes();
					ATdePdeOLaPInternalFrame.setVisible(true);
				} catch (NonExistentException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
        	}
        });
        menuPaquetes.add(mntmNewMenuItem);
        
     // Crear Paquete
        JMenuItem menuItemCrearPaquete1 = new JMenuItem("Crear Paquete");
        menuItemCrearPaquete1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CrearPaquete.setVisible(true);
            }
        });
        menuPaquetes.add(menuItemCrearPaquete1);
        
        //
        JMenuItem menuItemConsultaPaquetes = new JMenuItem("Consulta de Paquetes de Tipos de Oferta Laboral");
        menuItemConsultaPaquetes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			ConsultaPaquetesFrame.cargarPaquetes();
        			ConsultaPaquetesFrame.limpiarFormulario();
        			ConsultaPaquetesFrame.setVisible(true);      		
        		} catch (NonExistentException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
        		
        		
        	}
        });
        menuPaquetes.add(menuItemConsultaPaquetes);
        //
        
        JMenu mnNewMenu = new JMenu("Datos");
        menuBar.add(mnNewMenu);
        
        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Cargar datos");
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	if (datosCargados == false) {
        		cargarDatos cd = new cargarDatos(IL,IU);
        		cd.cargaKeyword();
        		cd.cargaUsuarios();
        		cd.cargaTipoDeOferta();
        		cd.cargaPaquetes();
        		cd.cargaPaquetes_TipoOferta();
        		cd.cargaCompraPaquete();
        		cd.cargaOfertas();
        		cd.cargaPostulaciones();
        		cd.cargarOfertasFavoritas();
        		cd.cargarSeguidos();
        		cd.cargarImagenes();
        		cd.cargaOrdenPostulacion();
        		datosCargados = true;
        		JOptionPane.showMessageDialog(null, "Los datos han sido cargados con exito.", "Exito", JOptionPane.INFORMATION_MESSAGE);
        	} else {
        		JOptionPane.showMessageDialog(null, "Los datos ya se encuentran cargados.", "Error", JOptionPane.ERROR_MESSAGE);
        	}
        	}
        });
        mnNewMenu.add(mntmNewMenuItem_1);
        
        JMenuItem limpiarImagenes = new JMenuItem("Limpiar imagenes");
        limpiarImagenes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarImagenes();
			}
		});
        mnNewMenu.add(limpiarImagenes);
        
        JMenuItem mntmPublicar = new JMenuItem("Publicar");
        mntmPublicar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				publicador.cargarDatos();
				publicador.setVisible(true);
			}
		});
        mnNewMenu.add(mntmPublicar);
        
        
    }
}


