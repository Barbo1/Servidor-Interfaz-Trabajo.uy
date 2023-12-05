package swing;

import java.awt.GridBagConstraints;
import javax.swing.JInternalFrame;

import model.datatype.DtOferta;
import model.datatype.DtOfertaCompleto;
import model.datatype.DtPostulacion;
import model.datatype.DtPostulacionCompleto;
import model.datatype.EstadoOferta;
import model.interfacess.*;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;

import model.exceptions.NonExistentException;

import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import com.toedter.calendar.JDateChooser;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.GridBagLayout;

import java.text.SimpleDateFormat;
import javax.swing.border.EtchedBorder;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;

public class ConsultaDeOferta extends JInternalFrame {
	private IcontroladorUsuario ctrlUsuario;
    private IcontroladorLaboral ctrlLaboral;
    private JComboBox cbxEmpresas; 
    private JComboBox cbxOfertas;
    private JComboBox cbxKeywords;
    private JTextField txtNombre;
    private JTextArea txtDescripcion;
    private JTextField txtCiudad;
    private JTextField txtDepartamento;
    private JTextField txtHorario;
    private JTextField txtRemuneracion;
    private JTextField txtCosto;
    private JLabel lblOfertasAsociadas;
    private JPanel pnlInfoOfertas;
    private JScrollPane pnlInfoPostulacionesBase;
    private JPanel pnlInfoPostulaciones;
    private JScrollPane scrollPane;
    private JTextField txtFecha;
    private JLabel lblFechaDeVencimiento;
    private JTextField campoVencimiento;
    private JTextField textEstado;
    private JLabel lblEstado;

	public ConsultaDeOferta(IcontroladorUsuario cu, IcontroladorLaboral cl) { // CREADOR
		
		// CONGIGURACION FRAME
		getContentPane().setFont(new Font("Dialog", Font.PLAIN, 12));
		setClosable(true);
		ctrlUsuario = cu;
		ctrlLaboral = cl;
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Consulta de ofertas");
		setBounds(100, 100, 885, 634);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		
		// EMPRESA
		JLabel lblSeleccionesLaEmpresa = new JLabel("Selecciones la empresa:");
		lblSeleccionesLaEmpresa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSeleccionesLaEmpresa.setFont(new Font("Dialog", Font.BOLD, 12));
		lblSeleccionesLaEmpresa.setBounds(0, 29, 202, 15);
		getContentPane().add(lblSeleccionesLaEmpresa);
		
		cbxEmpresas = new JComboBox();
		cbxEmpresas.setBounds(217, 24, 215, 24);
		getContentPane().add(cbxEmpresas);
	
		// OFERTAS DE LA EMPRESA
		lblOfertasAsociadas = new JLabel("Ofertas asociadas:");
		lblOfertasAsociadas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOfertasAsociadas.setFont(new Font("Dialog", Font.BOLD, 12));
		lblOfertasAsociadas.setVisible(false);
		lblOfertasAsociadas.setBounds(0, 75, 202, 15);
		getContentPane().add(lblOfertasAsociadas);
		
		cbxOfertas = new JComboBox();
		cbxOfertas.setVisible(false);
		cbxOfertas.setBounds(217, 70, 215, 24);
		getContentPane().add(cbxOfertas);
		
		// PANEL INFORMACION DE LA OFERTA
			// CONFIGURACION PANEL
			pnlInfoOfertas = new JPanel();
			pnlInfoOfertas.setFont(new Font("Arial", Font.PLAIN, 11));
			pnlInfoOfertas.setVisible(false);
			pnlInfoOfertas.setBounds(12, 105, 371, 485);
			pnlInfoOfertas.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Informaci\u00F3n de la oferta", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			getContentPane().add(pnlInfoOfertas);
			pnlInfoOfertas.setLayout(null);
			
			// NOMBRE
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNombre.setFont(new Font("Dialog", Font.BOLD, 12));
			lblNombre.setBounds(12, 29, 115, 15);
			pnlInfoOfertas.add(lblNombre);
			
			txtNombre = new JTextField();
			txtNombre.setEditable(false);
			txtNombre.setBounds(142, 25, 215, 24);
			pnlInfoOfertas.add(txtNombre);
			txtNombre.setColumns(10);
			
			//DESCRIPCION
			JLabel lblDescripcion = new JLabel("Descripción:");
			lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDescripcion.setFont(new Font("Dialog", Font.BOLD, 12));
			lblDescripcion.setBounds(0, 97, 127, 15);
			pnlInfoOfertas.add(lblDescripcion);
			
			txtDescripcion = new JTextArea();
			txtDescripcion.setWrapStyleWord(true);
			txtDescripcion.setLineWrap(true);
			txtDescripcion.setEditable(false);
			txtDescripcion.setBounds(142, 61, 215, 84);
			pnlInfoOfertas.add(txtDescripcion);
			txtDescripcion.setColumns(10);
			
			scrollPane = new JScrollPane(txtDescripcion);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(142, 61, 215, 84);
			pnlInfoOfertas.add(scrollPane);
			
			// CIUDAD
			JLabel lblCiudad = new JLabel("Ciudad:");
			lblCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCiudad.setFont(new Font("Dialog", Font.BOLD, 12));
			lblCiudad.setBounds(0, 162, 127, 15);
			pnlInfoOfertas.add(lblCiudad);
			
			txtCiudad = new JTextField();
			txtCiudad.setEditable(false);
			txtCiudad.setColumns(10);
			txtCiudad.setBounds(142, 157, 215, 24);
			pnlInfoOfertas.add(txtCiudad);
			
			// DEPARTAMENTO
			JLabel lblDepartamento = new JLabel("Departamento:");
			lblDepartamento.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDepartamento.setFont(new Font("Dialog", Font.BOLD, 12));
			lblDepartamento.setBounds(0, 193, 127, 15);
			pnlInfoOfertas.add(lblDepartamento);
			
			txtDepartamento = new JTextField();
			txtDepartamento.setEditable(false);
			txtDepartamento.setColumns(10);
			txtDepartamento.setBounds(142, 188, 215, 24);
			pnlInfoOfertas.add(txtDepartamento);
			
			// HORARIO
			JLabel lblHorario = new JLabel("Horario:");
			lblHorario.setHorizontalAlignment(SwingConstants.RIGHT);
			lblHorario.setFont(new Font("Dialog", Font.BOLD, 12));
			lblHorario.setBounds(0, 228, 127, 15);
			pnlInfoOfertas.add(lblHorario);
			
			txtHorario = new JTextField();
			txtHorario.setEditable(false);
			txtHorario.setColumns(10);
			txtHorario.setBounds(142, 223, 215, 24);
			pnlInfoOfertas.add(txtHorario);
			
			// REMUNEACION
			JLabel lblRemuneracion = new JLabel("Remuneración:");
			lblRemuneracion.setHorizontalAlignment(SwingConstants.RIGHT);
			lblRemuneracion.setFont(new Font("Dialog", Font.BOLD, 12));
			lblRemuneracion.setBounds(0, 263, 127, 15);
			pnlInfoOfertas.add(lblRemuneracion);
			
			txtRemuneracion = new JTextField();
			txtRemuneracion.setEditable(false);
			txtRemuneracion.setColumns(10);
			txtRemuneracion.setBounds(142, 258, 215, 24);
			pnlInfoOfertas.add(txtRemuneracion);
			
			// FECHA
			JLabel lblFecha = new JLabel("Fecha de alta:");
			lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
			lblFecha.setFont(new Font("Dialog", Font.BOLD, 12));
			lblFecha.setBounds(-3, 296, 127, 15);
			pnlInfoOfertas.add(lblFecha);
			
			txtFecha = new JTextField();
			txtFecha.setEditable(false);
			txtFecha.setColumns(10);
			txtFecha.setBounds(142, 292, 215, 24);
			pnlInfoOfertas.add(txtFecha);
			
			// COSTO
			JLabel lblCosto = new JLabel("Costo:");
			lblCosto.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCosto.setFont(new Font("Dialog", Font.BOLD, 12));
			lblCosto.setBounds(0, 404, 127, 15);
			pnlInfoOfertas.add(lblCosto);
			
			txtCosto = new JTextField();
			txtCosto.setEditable(false);
			txtCosto.setColumns(10);
			txtCosto.setBounds(142, 400, 215, 24);
			pnlInfoOfertas.add(txtCosto);
			
			// KEYWORDS
			JLabel lblKeywords = new JLabel("Keywords:");
			lblKeywords.setHorizontalAlignment(SwingConstants.RIGHT);
			lblKeywords.setFont(new Font("Dialog", Font.BOLD, 12));
			lblKeywords.setBounds(0, 441, 127, 15);
			pnlInfoOfertas.add(lblKeywords);
			
			cbxKeywords = new JComboBox();
			cbxKeywords.setBounds(142, 436, 215, 24);
			pnlInfoOfertas.add(cbxKeywords);
			
			lblFechaDeVencimiento = new JLabel("Vencimiento:");
			lblFechaDeVencimiento.setHorizontalAlignment(SwingConstants.RIGHT);
			lblFechaDeVencimiento.setFont(new Font("Dialog", Font.BOLD, 12));
			lblFechaDeVencimiento.setBounds(-20, 332, 147, 15);
			pnlInfoOfertas.add(lblFechaDeVencimiento);
			
			campoVencimiento = new JTextField();
			campoVencimiento.setEditable(false);
			campoVencimiento.setColumns(10);
			campoVencimiento.setBounds(142, 328, 215, 24);
			pnlInfoOfertas.add(campoVencimiento);
			
			textEstado = new JTextField();
			textEstado.setEditable(false);
			textEstado.setColumns(10);
			textEstado.setBounds(142, 364, 215, 24);
			pnlInfoOfertas.add(textEstado);
			
			lblEstado = new JLabel("Estado:");
			lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
			lblEstado.setFont(new Font("Dialog", Font.BOLD, 12));
			lblEstado.setBounds(-20, 368, 147, 15);
			pnlInfoOfertas.add(lblEstado);
		// FIN PANEL INFORMACION DE LA OFERTA
			
		// PANEL POSTULACIONES DE LA OFERTA
			pnlInfoPostulaciones = new JPanel();
			pnlInfoPostulaciones.setBorder(new TitledBorder(null, "Informaci\u00F3n de las postulaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			
			pnlInfoPostulacionesBase = new JScrollPane(pnlInfoPostulaciones,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			pnlInfoPostulacionesBase.setWheelScrollingEnabled(false);
			pnlInfoPostulacionesBase.setBorder(null);
			GridBagLayout gbl_pnlInfoPostulaciones = new GridBagLayout();
			gbl_pnlInfoPostulaciones.columnWidths = new int[]{0, 0, 0, 0, 0};
			gbl_pnlInfoPostulaciones.rowHeights = new int[]{0, 0};
			gbl_pnlInfoPostulaciones.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_pnlInfoPostulaciones.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			pnlInfoPostulaciones.setLayout(gbl_pnlInfoPostulaciones);
			pnlInfoPostulacionesBase.setBounds(395, 105, 468, 189);
			getContentPane().add(pnlInfoPostulacionesBase);
		
		// BOTON CERRAR
		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnNewButton.setBounds(579, 412, 89, 23);
		getContentPane().add(btnNewButton);
		
		// ACCION COMBOBOX EMPRESA
		cbxEmpresas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cargarOfertas();
				} catch (NonExistentException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Sistema", 1);
				}
			}
		});
		
		// ACCION COMBOBOX OFERTAS DE LA EMPRESA
		cbxOfertas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarDatos();
			}
		});
	} // FIN CREADOR
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	
	// FUNCIONES PARA CARGAR
	public void Ingresar() throws NonExistentException {
		cargarEmpresas();
		lblOfertasAsociadas.setVisible(false);
		cbxOfertas.setVisible(false);
		pnlInfoOfertas.setVisible(false);
		pnlInfoPostulacionesBase.setVisible(false);
	}
	
	private void cargarDatos() {
		if ((String)cbxOfertas.getSelectedItem() != "-") {
			pnlInfoOfertas.setVisible(false);
			pnlInfoPostulacionesBase.setVisible(false);
			DtOfertaCompleto datos = ctrlLaboral.mostrarOfertaCompleto((String)cbxOfertas.getSelectedItem());
			pnlInfoPostulaciones.removeAll();
			cbxKeywords.removeAll();
			
			DtOferta of = datos.getOferta();
			String[] keywords = of.getKeywords();
			DefaultComboBoxModel<String> model;
	        model = new DefaultComboBoxModel<String>(keywords);
	        cbxKeywords.setModel(model);
			
	        SimpleDateFormat Formato = new SimpleDateFormat("dd/MM/yyyy");
	        
			txtNombre.setText(of.getNombre());
		    txtDescripcion.setText(of.getDescripcion());
		    txtCiudad.setText(of.getCiudad());;
		    txtDepartamento.setText(of.getDepartamento());
		    txtHorario.setText(of.getHorarioTrabajo());
		    txtRemuneracion.setText(String.valueOf(of.getRemuneracion()));
		    txtCosto.setText(String.valueOf(of.getCosto()));
		    txtFecha.setText( of.getFechaAlta().toString());
		    if (of.getEstado().equals(EstadoOferta.Rechazada))
				campoVencimiento.setText("Rechazada");
			else if (of.getEstado().equals(EstadoOferta.Ingresada))
				campoVencimiento.setText("Pendiente de aprobacion");
			else
				campoVencimiento.setText(of.getVencimiento());
		    textEstado.setText(of.getEstado().toString());
		    
		    
		    
		    GridBagConstraints gridDim1 = new GridBagConstraints();
		    GridBagConstraints gridDim2 = new GridBagConstraints();
		    DtPostulacionCompleto[] arr = datos.getPostulaciones();
		    DtPostulacionCompleto postcomp;
		    DtPostulacion post;
		    JLabel lbl;
		    JTextField txt;
		    JTextArea txta;
		    JSeparator sep;
		    
		    gridDim2.fill = GridBagConstraints.HORIZONTAL;
		    gridDim2.weightx = 3;
		    gridDim1.gridx = 0;
		    gridDim2.gridx = 1;
		    
		    int i = 0;
		    int len = arr.length; 
		    
		    while (i < len) {
		    	postcomp = arr[i];
		    	post = postcomp.getPostulacion();
		    	
		    	gridDim1.gridy = 6*i;
		    	gridDim2.gridy = 6*i;
		    	
		    	// Mostrar nombre
		    	lbl = new JLabel("Nombre del postulante:");
		    	lbl.setFont(new Font("Dialog", Font.BOLD, 12));
		    	pnlInfoPostulaciones.add(lbl, gridDim1);	
				txt = new JTextField(postcomp.getPostulante());
				txt.setFont(new Font("Tahoma", Font.PLAIN, 12));
				txt.setBounds(0, 0, 200, 50);
				txt.setEditable(false);
				pnlInfoPostulaciones.add(txt, gridDim2);
		    	
		    	gridDim1.gridy += 1; 
		    	gridDim2.gridy += 1;
		    	
		    	// Mostrar cv
		    	lbl = new JLabel("CV:");
		    	lbl.setFont(new Font("Dialog", Font.BOLD, 12));
		    	pnlInfoPostulaciones.add(lbl, gridDim1);	
		    	txta = new JTextArea(post.getCurriculum());
				txta.setEditable(false);
				txta.setFont(new Font("Tahoma", Font.PLAIN, 12));
				txta.setLineWrap(true);
				pnlInfoPostulaciones.add(txta, gridDim2);
				
		    	gridDim2.gridy += 1;
		    	
				sep = new JSeparator(SwingConstants.HORIZONTAL);
		    	sep.setPreferredSize(new Dimension(200,3));
		    	pnlInfoPostulaciones.add(sep, gridDim2);
				
				gridDim1.gridy += 2; 
		    	gridDim2.gridy += 1;
		    	
		    	// Mostrar Motivacion
		    	lbl = new JLabel("Motivacion:");
		    	lbl.setFont(new Font("Dialog", Font.BOLD, 12));
		    	pnlInfoPostulaciones.add(lbl, gridDim1);	
		    	txta = new JTextArea(post.getMotivacion());
				txta.setEditable(false);
				txta.setFont(new Font("Tahoma", Font.PLAIN, 12));
				txta.setLineWrap(true);
				pnlInfoPostulaciones.add(txta, gridDim2);
				
				gridDim1.gridy += 1; 
		    	gridDim2.gridy += 1;
				
				// Mostrar fecha
		    	lbl = new JLabel("Fecha:");
		    	lbl.setFont(new Font("Dialog", Font.BOLD, 12));
		    	pnlInfoPostulaciones.add(lbl, gridDim1);	
				txt = new JTextField(post.getFecha().toString());
				txt.setFont(new Font("Tahoma", Font.PLAIN, 12));
				txt.setEditable(false);
				pnlInfoPostulaciones.add(txt, gridDim2);
		    	
		    	gridDim1.gridy += 1; 
		    	gridDim2.gridy += 1;
		    	
		    	sep = new JSeparator(SwingConstants.HORIZONTAL);
		    	sep.setPreferredSize(new Dimension(200,3));
		    	pnlInfoPostulaciones.add(sep, gridDim1);
		    	sep = new JSeparator();
		    	sep.setPreferredSize(new Dimension(200,3));
		    	pnlInfoPostulaciones.add(sep, gridDim2);
		    	
		    	i++;
		    }
		    
		    pnlInfoOfertas.setVisible(true);
			pnlInfoPostulacionesBase.setVisible(true);
		}
    }
	
	public void cargarEmpresas() throws NonExistentException{
		String[] aux = {"-"};
		String[] emprs = ctrlUsuario.listarEmpresas();
		String[] empresas = new String[aux.length + emprs.length];
		System.arraycopy(aux, 0, empresas, 0, aux.length);
        System.arraycopy(emprs, 0, empresas, aux.length, emprs.length);
		if (empresas.length == 1) // Si pasa esto es porque no habia empresas en el sistema 
			throw new NonExistentException("No hay empresas en el sistema");
		DefaultComboBoxModel<String> model;
        model = new DefaultComboBoxModel<String>(empresas);
        cbxEmpresas.setModel(model);
    }
	
	private void cargarOfertas() throws NonExistentException {
		String[] aux = {"-"};
		String[] oferemp = ctrlUsuario.listarOfertasEmpresa((String)cbxEmpresas.getSelectedItem());
		String[] ofertasempresa = new String[aux.length + oferemp.length];
		System.arraycopy(aux, 0, ofertasempresa, 0, aux.length);
        System.arraycopy(oferemp, 0, ofertasempresa, aux.length, oferemp.length);
		if(ofertasempresa.length == 1) // Si pasa esto es porque la empresa no tenia ofertas 
			throw new NonExistentException("No hay ofertas realcionadas a esta empresa.");
        DefaultComboBoxModel<String> model;
        model = new DefaultComboBoxModel<String>(ofertasempresa);
        cbxOfertas.setModel(model);
        
        lblOfertasAsociadas.setVisible(true);
		cbxOfertas.setVisible(true);
    }
}
