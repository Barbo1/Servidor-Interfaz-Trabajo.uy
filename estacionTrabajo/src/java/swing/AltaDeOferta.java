package swing;

import javax.swing.JInternalFrame;
import javax.swing.JTextArea;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import model.exceptions.DuplicationException;
import model.exceptions.NonExistentException;
import model.datatype.EstadoOferta;
import model.interfacess.IcontroladorLaboral;
import model.interfacess.IcontroladorUsuario;
import javax.swing.JCheckBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.Panel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class AltaDeOferta extends JInternalFrame {
	
	private static volatile Integer puntoIngresado; // Esto es para la remuneracion
	
	private JComboBox<String> comboBoxEmpresas;
	private JComboBox<String> comboBoxTiposDeOfertas;
	private JTextField textNombre;
	private JTextArea textDescripcion;
	private JTextField textHorario;
	private JTextField textRemuneracion;
	private JTextField textCiudad;
	private JTextField textDepartamento;
	private JDateChooser chooserFechaDeAlta;
	private JMenu JMenuKeywords;
	private JScrollPane scrollPane;
	
	private IcontroladorLaboral ctrlLaboral;
	private IcontroladorUsuario ctrlUsuario;
	
	//ELECCIONES DEL ADMINISTRADOR
	private String empresaElegida;
	private String tipoDeOfertaElegida;
	private ArrayList<JCheckBox> keywordsElegidas = new ArrayList<JCheckBox>();
	
	//CREADOR
	public AltaDeOferta(IcontroladorUsuario contrUsuario,IcontroladorLaboral contLaboral) {
		
		this.ctrlUsuario = contrUsuario;
		this.ctrlLaboral = contLaboral;
		
		// CONGIGURACION FRAME
		setBounds(100, 100, 450, 505);
		setRootPaneCheckingEnabled(false);
		setRequestFocusEnabled(false);
		setClosable(true);
		setResizable(true);
		setIconifiable(true);
		//setMaximum(true);
		setMaximizable(true);
		setTitle("Alta de oferta laboral");
		getContentPane().setLayout(null);
		
		// EMPRESA
		JLabel lblNewLabel = new JLabel("Seleccione una empresa:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(6, 24, 214, 14);
		getContentPane().add(lblNewLabel);
		
		comboBoxEmpresas = new JComboBox<String>();
		comboBoxEmpresas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Elige una empresa que dara de alta la oferta
				empresaElegida = (String)((JComboBox) e.getSource()).getSelectedItem();
			}
		});
		comboBoxEmpresas.setBounds(223, 20, 173, 22);
		getContentPane().add(comboBoxEmpresas);
		
		// TIPO DE OFERTA
		JLabel lblNewLabel_1 = new JLabel("Seleccione un tipo de oferta :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1.setBounds(6, 50, 214, 14);
		getContentPane().add(lblNewLabel_1);
		
		comboBoxTiposDeOfertas = new JComboBox<String>();
		comboBoxTiposDeOfertas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Elige un tipo de oferta que se le quiere asociar a la oferta
				tipoDeOfertaElegida = (String)((JComboBox) e.getSource()).getSelectedItem();
			}
		});
		comboBoxTiposDeOfertas.setBounds(223, 48, 173, 22);
		getContentPane().add(comboBoxTiposDeOfertas);
			
			JLabel lblNewLabel_2 = new JLabel("Ingrese los datos de la oferta laboral");
			lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 12));
			lblNewLabel_2.setBounds(122, 82, 287, 14);
			getContentPane().add(lblNewLabel_2);
			
			// NOMBRE
			JLabel lblNewLabel_3 = new JLabel("Nombre:");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 12));
			lblNewLabel_3.setBounds(39, 108, 134, 14);
			getContentPane().add(lblNewLabel_3);
			
			textNombre = new JTextField();
			textNombre.setBounds(182, 107, 214, 20);
			getContentPane().add(textNombre);
			textNombre.setColumns(10);
			
			// DESCRIPCION
			JLabel lblNewLabel_4 = new JLabel("Descripcion:");
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 12));
			lblNewLabel_4.setBounds(33, 169, 140, 14);
			getContentPane().add(lblNewLabel_4);
			
			textDescripcion = new JTextArea();
			textDescripcion.setWrapStyleWord(true);
			textDescripcion.setLineWrap(true);
			textDescripcion.setBounds(182, 132, 214, 91);
			getContentPane().add(textDescripcion);
			textDescripcion.setColumns(10);
			
			// HORARIO
			JLabel lblNewLabel_5 = new JLabel("Horario:");
			lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_5.setVerticalAlignment(SwingConstants.BOTTOM);
			lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 12));
			lblNewLabel_5.setBounds(33, 237, 140, 14);
			getContentPane().add(lblNewLabel_5);
			
			textHorario = new JTextField();
			textHorario.setBounds(182, 234, 214, 20);
			getContentPane().add(textHorario);
			textHorario.setColumns(10);
			
			// REMUNERACION
			JLabel lblNewLabel_6 = new JLabel("Remuneracion (UYU):");
			lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_6.setFont(new Font("Dialog", Font.BOLD, 12));
			lblNewLabel_6.setBounds(19, 268, 153, 14);
			getContentPane().add(lblNewLabel_6);
			
			textRemuneracion = new JTextField();
			// Esta parte es para que en el JTextField solo se puedan escribir numeros
			puntoIngresado = 0;
			textRemuneracion.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if (Integer.valueOf(e.getKeyChar()) == 8) { // KeyEvent.VK_BACK_SPACE == 8
						if (textRemuneracion.getCaretPosition() + 1 == puntoIngresado) {
							puntoIngresado = 0;					
						}
					} else if (e.getKeyChar() == '.' && puntoIngresado == 0) {
						puntoIngresado = textRemuneracion.getText().length()+1;
					} else if (!Character.isDigit(e.getKeyChar())) {
						e.consume();
					}
				}
			});
			textRemuneracion.setBounds(182, 265, 214, 19);
			getContentPane().add(textRemuneracion);
			
			// CIUDAD
			JLabel lblNewLabel_9 = new JLabel("Ciudad:");
			lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_9.setFont(new Font("Dialog", Font.BOLD, 12));
			lblNewLabel_9.setBounds(39, 298, 134, 13);
			getContentPane().add(lblNewLabel_9);
			
			textCiudad = new JTextField();
			textCiudad.setBounds(182, 295, 214, 19);
			getContentPane().add(textCiudad);
			textCiudad.setColumns(10);
			
			// DEPARTAMENTO
			JLabel lblNewLabel_10 = new JLabel("Departamento:");
			lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_10.setFont(new Font("Dialog", Font.BOLD, 12));
			lblNewLabel_10.setBounds(39, 328, 134, 13);
			getContentPane().add(lblNewLabel_10);
			
			textDepartamento = new JTextField();
			textDepartamento.setBounds(182, 325, 214, 19);
			getContentPane().add(textDepartamento);
			textDepartamento.setColumns(10);

			// FECHA DE ALTA 
			JLabel lblNewLabel_7 = new JLabel("Fecha de alta:");
			lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_7.setFont(new Font("Dialog", Font.BOLD, 12));
			lblNewLabel_7.setBounds(20, 360, 153, 14);
			getContentPane().add(lblNewLabel_7);
			
			chooserFechaDeAlta = new JDateChooser();
			chooserFechaDeAlta.setIgnoreRepaint(true);
			chooserFechaDeAlta.setBounds(182, 355, 214, 19);
			getContentPane().add(chooserFechaDeAlta);
			
	        // KEYWORDS
			JLabel lblNewLabel_8 = new JLabel("Keywords:");
			lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_8.setFont(new Font("Dialog", Font.BOLD, 12));
			lblNewLabel_8.setBounds(0, 386, 177, 22);
			getContentPane().add(lblNewLabel_8);
			
			Panel panel = new Panel();
			panel.setBounds(202, 385, 167, 22);
			getContentPane().add(panel);
			panel.setLayout(null);
			
			JMenuBar menuBar = new JMenuBar();
			menuBar.setBounds(0, 0, 167, 22);
			panel.add(menuBar);
			
			JMenuKeywords = new JMenu("Seleccione keywords              ");
			JMenuKeywords.setFont(new Font("Dialog", Font.BOLD, 12));
			menuBar.add(JMenuKeywords);
			
			// BOTON REGISTAR
			JButton btnRegistrar = new JButton("Registrar");
			btnRegistrar.setFont(new Font("Dialog", Font.PLAIN, 12));
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (empresaElegida == "-" || tipoDeOfertaElegida == "-" || textNombre.getText().strip() == "" || textDescripcion.getText().strip() == "" || 
							textHorario.getText().strip() == "" || textRemuneracion.getText().strip() == "" || textCiudad.getText().strip() == "" || textDepartamento.getText().strip() == "" || chooserFechaDeAlta.getDate() == null) {
			            JOptionPane.showMessageDialog(null, "No puede haber campos vacíos", "Alta de Oferta", JOptionPane.ERROR_MESSAGE);
					}
					else {
				        // Paso las Keywords seleccionadas en las ChechBox a un HashSet para pasarsela como parametros a ctrlLaboral.ingresarOfertaLaboral()
				        HashSet<String> keywordsCreador = new HashSet<String>();
						for(JCheckBox elem: keywordsElegidas) {
							if(elem.isSelected()) {
								String nombreKeyword = elem.getLabel();
								keywordsCreador.add(nombreKeyword);
							}
						}
						
						try {
							Date date = chooserFechaDeAlta.getDate();
							LocalDate fechaAlta = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
							ctrlLaboral.ingresarOfertaLaboral(
                                    empresaElegida, tipoDeOfertaElegida, textNombre.getText(), 
                                    textDescripcion.getText(), textHorario.getText(), Float.valueOf(textRemuneracion.getText()), 
                                    textCiudad.getText(),  textDepartamento.getText(), fechaAlta, keywordsCreador, "", EstadoOferta.Ingresada);
							JOptionPane.showMessageDialog(null,"La oferta se ha creado con exito","Alta de Oferta",JOptionPane.INFORMATION_MESSAGE);
							limpiarFormulario();
							limpiarEmpresaTipoDeOfertaEnCombobox();
							cargarFechaActual();
						
						} catch (DuplicationException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}// Fin else
				} // Fin action performed
			}); // Fin action listener
			btnRegistrar.setBounds(102, 430, 118, 34);
			getContentPane().add(btnRegistrar);
			
			// BOTON CANCELAR
			JButton btnCancelar = new JButton("Cancelar");
			btnCancelar.setFont(new Font("Dialog", Font.PLAIN, 12));
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
	               setVisible(false);
				}
			});
			btnCancelar.setBounds(244, 430, 125, 34);
			getContentPane().add(btnCancelar);
			
			JLabel lblnoObligatorio = new JLabel("(no obligatorio)");
			lblnoObligatorio.setHorizontalAlignment(SwingConstants.RIGHT);
			lblnoObligatorio.setBounds(39, 404, 140, 15);
			getContentPane().add(lblnoObligatorio);
			
			scrollPane = new JScrollPane(textDescripcion);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(182, 132, 214, 91);
			getContentPane().add(scrollPane);
				
	} // FIN CREADOR

	public void cargarFechaActual() {
		LocalDate t = LocalDate.now();
		chooserFechaDeAlta.setDate(Date.from(t.atStartOfDay(ZoneId.systemDefault()).toInstant()));
	}
	
	// FUNCIONES PARA CARGAR
	public void cargarEmpresas() throws NonExistentException {
		String[] aux = {"-"};
		String[] emprs = ctrlUsuario.listarEmpresas();
		String[] empresas = new String[aux.length + emprs.length];
		System.arraycopy(aux, 0, empresas, 0, aux.length);
        System.arraycopy(emprs, 0, empresas, aux.length, emprs.length);
		if (empresas.length == 1) // Si pasa esto es porque no habia empresas en el sistema 
			throw new NonExistentException("No hay empresas en el sistema");
		DefaultComboBoxModel<String> model;
        model = new DefaultComboBoxModel<String>(empresas);
        comboBoxEmpresas.setModel(model);
	}
	
	public void cargarTiposDeOfertas() throws NonExistentException {
		String[] aux = {"-"};
		String[] tdo = ctrlLaboral.listarTipoDeOferta();
		String[] tiposDeOfertas = new String[aux.length + tdo.length];
		System.arraycopy(aux, 0, tiposDeOfertas, 0, aux.length);
        System.arraycopy(tdo, 0, tiposDeOfertas, aux.length, tdo.length);
		if (tiposDeOfertas.length == 1) // Si pasa esto es porque no habia tipos de ofertas en el sistema
			throw new NonExistentException("No hay tipos de ofertas en el sistema");
		DefaultComboBoxModel<String> model;
        model = new DefaultComboBoxModel<String>(tiposDeOfertas);
        comboBoxTiposDeOfertas.setModel(model);
	}

	public void cargarKeywords() {
		// No controlo que hayan keywords en el sistema porque se puede ingresar una oferta sin keywords
		String[] keywords = ctrlLaboral.listarKeywords();
		keywordsElegidas = new ArrayList<JCheckBox>();
		for(int i = 0; i < keywords.length; i++) {
			String nombreKeyword = keywords[i];
			JCheckBox keywordCheckBox = new JCheckBox(nombreKeyword);
			keywordsElegidas.add(keywordCheckBox);
			JMenuKeywords.add(keywordCheckBox);
		}
    }
	
	// Limpia el formulario	
    public void limpiarFormulario() {
    	textNombre.setText("");
        textDescripcion.setText("");
        textHorario.setText("");
        textRemuneracion.setText("");
    	textCiudad.setText("");
    	textDepartamento.setText("");
    	// Fecha de alta la inicializo con la actual del sistema al abrir la ventana
    }
    
    // Limpio la empresa y el tipo de oferta elegido en los combobox luego de apretar Registrar
    public void limpiarEmpresaTipoDeOfertaEnCombobox() {
    	comboBoxEmpresas.setSelectedItem("-");
    	comboBoxTiposDeOfertas.setSelectedItem("-");
    }
    
    // Limpia las keywords seleccionadas por el administrador
	public void limpiarKeywords() {
		keywordsElegidas.clear();
		JMenuKeywords.removeAll();
	}
} // FIN CLASE	

