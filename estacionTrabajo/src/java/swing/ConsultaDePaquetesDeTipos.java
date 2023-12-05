package swing;
import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import java.awt.BorderLayout;

import java.awt.Component;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;

import model.exceptions.DuplicationException;
import model.exceptions.NonExistentException;
import model.clases.Cantidad;
import model.clases.Postulacion;
import model.controllers_managers.ManejadorTipoDeOferta;
import model.datatype.DtCantidad;
import model.datatype.DtEmpresa;
import model.datatype.DtEmpresaCompleto;
import model.datatype.DtOferta;
import model.datatype.DtOfertaCompleto;
import model.datatype.DtPaquete;
import model.datatype.DtPaqueteCompleto;
import model.datatype.DtPostulante;
import model.datatype.DtPostulanteCompleto;
import model.datatype.DtTipoDeOferta;
import model.interfacess.IcontroladorLaboral;
import model.interfacess.IcontroladorUsuario;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import java.awt.Choice;
import javax.swing.JTable;
import java.awt.Panel;
import javax.swing.JSpinner;
import java.awt.Scrollbar;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.AbstractListModel;
import javax.swing.JScrollBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;


public class ConsultaDePaquetesDeTipos extends JInternalFrame {
	
	private IcontroladorLaboral ctrlLaboral;
	
	private JComboBox<String> comboBoxPaquete;
	private JComboBox<String> comboBoxTipoCant;
	private JTextField txtValidez;
	private JTextField txtDescuento;
	private JTextField txtCosto;
	private JTextArea txtDesc;
	private JTextArea txtAreaDescTipo;
	
	private String paqueteElegido;
	
	private JInternalFrame FrameInfoTipo;
	private Panel panelPaquete;
	private JLabel lblTipoCantidad;
	
	private Set<JButton> listaTipos;
	private JTextField txtExp;
	private JTextField txtDuracion;
	private JTextField txtFecha;
	private JTextField txtCostoTipo;
	private JLabel lblNoTipo;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	
	public ConsultaDePaquetesDeTipos(IcontroladorLaboral contLaboral) {
		this.ctrlLaboral = contLaboral;
		setTitle("Consulta de Paquete de Tipos de publicación de Ofertas Laborales");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		getContentPane().setLayout(null);
		setBounds(100, 100, 853, 388);
		
		
		FrameInfoTipo = new JInternalFrame("Información del Tipo de Oferta");
		FrameInfoTipo.setClosable(true);
		FrameInfoTipo.setResizable(true);
		FrameInfoTipo.setMaximizable(true);
		FrameInfoTipo.setIconifiable(true);
		FrameInfoTipo.setVisible(false);
		FrameInfoTipo.setBounds(415, 20, 403, 270);
		getContentPane().add(FrameInfoTipo);
		FrameInfoTipo.getContentPane().setLayout(null);
		
		JLabel lblDescTipo = new JLabel("Descripción :");
		lblDescTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescTipo.setFont(new Font("Arial", Font.BOLD, 12));
		lblDescTipo.setBounds(10, 39, 86, 23);
		FrameInfoTipo.getContentPane().add(lblDescTipo);
		
		txtAreaDescTipo = new JTextArea();
		txtAreaDescTipo.setEditable(false);
		txtAreaDescTipo.setWrapStyleWord(true);
		txtAreaDescTipo.setLineWrap(true);
		txtAreaDescTipo.setBounds(97, 20, 265, 59);
		FrameInfoTipo.getContentPane().add(txtAreaDescTipo);
		
		JLabel lblExp = new JLabel("Exposición :");
		lblExp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblExp.setFont(new Font("Arial", Font.BOLD, 12));
		lblExp.setBounds(10, 89, 77, 27);
		FrameInfoTipo.getContentPane().add(lblExp);
		
		txtExp = new JTextField();
		txtExp.setFont(new Font("Arial", Font.PLAIN, 12));
		txtExp.setEditable(false);
		txtExp.setColumns(10);
		txtExp.setBounds(97, 91, 265, 23);
		FrameInfoTipo.getContentPane().add(txtExp);
		
		txtDuracion = new JTextField();
		txtDuracion.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDuracion.setEditable(false);
		txtDuracion.setColumns(10);
		txtDuracion.setBounds(97, 128, 265, 23);
		FrameInfoTipo.getContentPane().add(txtDuracion);
		
		JLabel lblDuracion = new JLabel("Duración :");
		lblDuracion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDuracion.setFont(new Font("Arial", Font.BOLD, 12));
		lblDuracion.setBounds(10, 126, 77, 27);
		FrameInfoTipo.getContentPane().add(lblDuracion);
		
		txtFecha = new JTextField();
		txtFecha.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFecha.setEditable(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(97, 163, 265, 23);
		FrameInfoTipo.getContentPane().add(txtFecha);
		
		JLabel lblFecha = new JLabel("Fecha de alta :");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setFont(new Font("Arial", Font.BOLD, 12));
		lblFecha.setBounds(0, 161, 87, 27);
		FrameInfoTipo.getContentPane().add(lblFecha);
		
		txtCostoTipo = new JTextField();
		txtCostoTipo.setFont(new Font("Arial", Font.PLAIN, 12));
		txtCostoTipo.setEditable(false);
		txtCostoTipo.setColumns(10);
		txtCostoTipo.setBounds(97, 202, 265, 23);
		FrameInfoTipo.getContentPane().add(txtCostoTipo);
		
		JLabel lblCostoTipo = new JLabel("Costo :");
		lblCostoTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCostoTipo.setFont(new Font("Arial", Font.BOLD, 12));
		lblCostoTipo.setBounds(10, 200, 77, 27);
		FrameInfoTipo.getContentPane().add(lblCostoTipo);
		
		scrollPane_1 = new JScrollPane(txtAreaDescTipo);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(97, 20, 265, 59);
		FrameInfoTipo.getContentPane().add(scrollPane_1);
		FrameInfoTipo.setVisible(true);
		
		JLabel lblPaquete = new JLabel("Paquete :");
		lblPaquete.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPaquete.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPaquete.setBounds(20, 20, 56, 27);
		getContentPane().add(lblPaquete);
		
		panelPaquete = new Panel();
		panelPaquete.setBounds(10, 63, 379, 256);
		getContentPane().add(panelPaquete);
		panelPaquete.setLayout(null);
		
		
		lblTipoCantidad = new JLabel("Tipo de Oferta / Cantidad");
		lblTipoCantidad.setBounds(10, 191, 157, 27);
		panelPaquete.add(lblTipoCantidad);
		lblTipoCantidad.setFont(new Font("Arial", Font.BOLD, 12));
		
	
		
		comboBoxPaquete = new JComboBox();
		comboBoxPaquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameInfoTipo.setVisible(false);
				paqueteElegido = (String)((JComboBox) e.getSource()).getSelectedItem();
				
				if (paqueteElegido != null && paqueteElegido != "-") {
					panelPaquete.setVisible(true);
					DtPaqueteCompleto dtpc = ctrlLaboral.mostrarInfoPaquete(paqueteElegido);
					DtPaquete dtp = dtpc.getPaquete();
					txtDesc.setText(dtp.getDescripcion());
					txtValidez.setText(String.valueOf(dtp.getValidez()) + " días.");
					txtDescuento.setText(String.valueOf(dtp.getDescuento() * 100) + "%");
					txtCosto.setText(String.valueOf(dtp.getCosto()) + "$");
					
					
					
					//Cargar en Combobox paquetes

					Set<DtCantidad> tipos = dtpc.getTipos();
					
					if(tipos.size()!=0) {
						lblNoTipo.setVisible(false);
						comboBoxTipoCant.setVisible(true);
						lblTipoCantidad.setVisible(true);
						String[] tiposCant = new String[1 + tipos.size()];
						
						tiposCant[0]="-";
						int i=1;
							Iterator<DtCantidad> it = tipos.iterator();
							while(it.hasNext()) {
								tiposCant[i]=it.next().toString();
								i++;
							}
						DefaultComboBoxModel<String> model;
					    model = new DefaultComboBoxModel<String>(tiposCant);
					    comboBoxTipoCant.setModel(model);
						
						
					} else {
						lblNoTipo.setVisible(true);
						comboBoxTipoCant.setVisible(false);
						lblTipoCantidad.setVisible(false);
					}
					
			    } else {
			    	limpiarFormulario();
			    }
			}
		});
		
		
		comboBoxPaquete.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxPaquete.setBounds(101, 20, 270, 27);
		getContentPane().add(comboBoxPaquete);
		
		JLabel lblDesc = new JLabel("Descripción :");
		lblDesc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesc.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDesc.setBounds(10, 29, 77, 27);
		panelPaquete.add(lblDesc);
		
		JLabel lblValidez = new JLabel("Validez :");
		lblValidez.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValidez.setFont(new Font("Arial", Font.BOLD, 12));
		lblValidez.setBounds(10, 82, 77, 27);
		panelPaquete.add(lblValidez);
		
		txtDesc = new JTextArea();
		txtDesc.setWrapStyleWord(true);
		txtDesc.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDesc.setLineWrap(true);
		txtDesc.setEditable(false);
		txtDesc.setBounds(97, 11, 265, 60);
		panelPaquete.add(txtDesc);
		
		txtValidez = new JTextField();
		txtValidez.setFont(new Font("Arial", Font.PLAIN, 12));
		txtValidez.setEditable(false);
		txtValidez.setBounds(97, 84, 265, 23);
		panelPaquete.add(txtValidez);
		txtValidez.setColumns(10);
		
		JLabel lblDescuento = new JLabel("Descuento :");
		lblDescuento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescuento.setFont(new Font("Arial", Font.BOLD, 12));
		lblDescuento.setBounds(10, 119, 77, 27);
		panelPaquete.add(lblDescuento);
		
		txtDescuento = new JTextField();
		txtDescuento.setEditable(false);
		txtDescuento.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDescuento.setColumns(10);
		txtDescuento.setBounds(97, 121, 265, 23);
		panelPaquete.add(txtDescuento);
		
		JLabel lblCosto = new JLabel("Costo :");
		lblCosto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCosto.setFont(new Font("Arial", Font.BOLD, 12));
		lblCosto.setBounds(10, 154, 77, 27);
		panelPaquete.add(lblCosto);
		
		txtCosto = new JTextField();
		txtCosto.setFont(new Font("Arial", Font.PLAIN, 12));
		txtCosto.setEditable(false);
		txtCosto.setColumns(10);
		txtCosto.setBounds(97, 158, 265, 23);
		panelPaquete.add(txtCosto);
		
		lblNoTipo = new JLabel("El paquete no tiene tipos.");
		lblNoTipo.setFont(new Font("Arial", Font.BOLD, 12));
		lblNoTipo.setBounds(97, 190, 239, 29);
		panelPaquete.add(lblNoTipo);
		
		
		comboBoxTipoCant = new JComboBox();
		comboBoxTipoCant.setBounds(10, 219, 270, 27);
		panelPaquete.add(comboBoxTipoCant);
		comboBoxTipoCant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String tipoCant = (String)((JComboBox) e.getSource()).getSelectedItem();
				if (tipoCant!="-") {
					String tipo = tipoCant.substring(0,tipoCant.indexOf(" /")); //Esta bien?
					DtTipoDeOferta dttipo= ctrlLaboral.mostrarTipoOferta(tipo);
					FrameInfoTipo.setVisible(true);
					
					txtAreaDescTipo.setText(dttipo.getDescripcion());
					txtExp.setText(String.valueOf(dttipo.getExposicion()));
					txtDuracion.setText(String.valueOf(dttipo.getDuracion()) + " días.");
					txtFecha.setText(dttipo.getFechaAlta());
					txtCostoTipo.setText(String.valueOf(dttipo.getCosto()) + "$");
					
				}else {
					FrameInfoTipo.setVisible(false);
					
				}
			}
			}
		);
		comboBoxTipoCant.setFont(new Font("Arial", Font.PLAIN, 12));
		
		scrollPane = new JScrollPane(txtDesc);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(97, 11, 265, 60);
		panelPaquete.add(scrollPane);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
               setVisible(false);
               limpiarFormulario();
			}
		});
		btnCancelar.setBounds(288, 325, 97, 21);
		getContentPane().add(btnCancelar);
		
		JPanel panel = new JPanel();
		panel.setBounds(425, 40, 393, 248);
		getContentPane().add(panel);
	}
	
	
	public void cargarPaquetes() throws NonExistentException {
		String[] aux = {"-"};
		String[] paquetes = ctrlLaboral.listarPaquetes();
		String[] mostrarPaq = new String[aux.length + paquetes.length];
		System.arraycopy(aux, 0, mostrarPaq, 0, aux.length);
        System.arraycopy(paquetes, 0, mostrarPaq, aux.length, paquetes.length);
		if (mostrarPaq.length == 1) // Si pasa esto es porque no habia empresas en el sistema 
			throw new NonExistentException("No hay paquetes en el sistema");
		DefaultComboBoxModel<String> model;
        model = new DefaultComboBoxModel<String>(mostrarPaq);
        comboBoxPaquete.setModel(model);
	}
	
	
	// Limpia el formulario	
    public void limpiarFormulario() {
    	txtDesc.setText("");
		txtValidez.setText("");
		txtDescuento.setText("");
		txtCosto.setText("");
		
		comboBoxPaquete.setSelectedItem("-");
    	
		FrameInfoTipo.setVisible(false);
		panelPaquete.setVisible(false);
		comboBoxTipoCant.setVisible(false);
    	
		
    	// Fecha de alta la inicializo con la actual del sistema al abrir la ventana
    }
}
