package swing;
import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import model.exceptions.DuplicationException;
import model.exceptions.NonExistentException;
import model.exceptions.OutOfMarginException;
import model.datatype.DtOferta;
import model.datatype.DtOfertaCompleto;
import model.datatype.EstadoOferta;
import model.interfacess.IcontroladorLaboral;
import model.interfacess.IcontroladorUsuario;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;


public class PostulacionAOfertaLaboral extends JInternalFrame {
	private JTextArea txtDesc;
	private JTextField txtCiudad;
	private JTextField txtDepto;
	private JTextField txtHorario;
	private JTextField txtRem;
	private JTextField txtFecha;
	private JTextField txtCosto;
	private JTextArea txtCV;
	private JTextArea txtMot;
	private IcontroladorLaboral ctrlLaboral;
	private IcontroladorUsuario ctrlUsuario;
	private JComboBox<String> comboBoxEmpresas;
	private JComboBox<String> comboBoxOfertas;
	private JComboBox<String> comboBoxPostulantes;
	private JInternalFrame FrameInfoOferta;
	private JLabel lblNoOf;
	private JLabel lblOferta;

	private String ofertaElegida;
	private String postElegido;
	private JTextField fecha;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JTextField textField_1;
	
	public PostulacionAOfertaLaboral(IcontroladorUsuario cu,IcontroladorLaboral cl) {
		
		this.ctrlUsuario = cu;
		this.ctrlLaboral = cl;
		

		
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Postulacion a Oferta Laboral");
		setBounds(10, 40, 1099, 451);
		getContentPane().setLayout(null);
		
/*---------------------------------------- ELEGIR EMPRESA ----------------------------------------*/
		JLabel lblEmpresa = new JLabel("Empresa :");
		lblEmpresa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmpresa.setFont(new Font("Dialog", Font.BOLD, 12));
		lblEmpresa.setBounds(20, 20, 94, 25);
		getContentPane().add(lblEmpresa);
		
	 	comboBoxEmpresas = new JComboBox<String>();
	 	comboBoxEmpresas.setFont(new Font("Arial", Font.PLAIN, 12));
	 	comboBoxEmpresas.setBounds(124, 20, 325, 25);
		getContentPane().add(comboBoxEmpresas);
		comboBoxEmpresas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String empresaElegida = (String)((JComboBox) e.getSource()).getSelectedItem();
				FrameInfoOferta.setVisible(false);
				if(empresaElegida != "-") {
					try { //Es necesario esto??
					
						List<DtOferta> ofertasLista = ctrlLaboral.listarOfertasPorEmpresa(empresaElegida, EstadoOferta.Confirmada);
						String[] ofertas = new String[ofertasLista.size()];
						int aux2 = 0;
						for (DtOferta oferta : ofertasLista) {
							ofertas[aux2] = oferta.getNombre();
							aux2++;
						}
						
						if(ofertas.length==0) {
							lblOferta.setVisible(false);
							comboBoxOfertas.setVisible(false);
							lblNoOf.setVisible(true);
							
						} else {
							lblOferta.setVisible(true);
							comboBoxOfertas.setVisible(true);
							lblNoOf.setVisible(false);
							String[] aux = {"-"};
							String[] mostOf = new String[1 + ofertas.length];
							System.arraycopy(aux, 0, mostOf, 0, aux.length);
					        System.arraycopy(ofertas, 0, mostOf, aux.length, ofertas.length);
					        DefaultComboBoxModel<String> model;
					        model = new DefaultComboBoxModel<String>(mostOf);
					        comboBoxOfertas.setModel(model);
						}
					} catch (NonExistentException ex) {}
				} else {
					comboBoxOfertas.setVisible(false);
					lblNoOf.setVisible(false);
					lblOferta.setVisible(false);
				}	
			}
		});
/*---------------------------------------- VENTANA OFERTA ----------------------------------------*/
		FrameInfoOferta = new JInternalFrame("Informacion de la Oferta Seleccionada");
		FrameInfoOferta.moveToFront();
		FrameInfoOferta.setResizable(true);
		FrameInfoOferta.setClosable(true);
		FrameInfoOferta.setBounds(505, 10, 562, 370);
		getContentPane().add(FrameInfoOferta);
		FrameInfoOferta.getContentPane().setLayout(null);
		FrameInfoOferta.setVisible(false);
		
		JPanel panelInfoOferta = new JPanel();
		panelInfoOferta.setBounds(0, 0, 555, 340);
		FrameInfoOferta.getContentPane().add(panelInfoOferta);
		panelInfoOferta.setLayout(null);
		
		JLabel lblDescripcion = new JLabel("Descripcion :");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcion.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDescripcion.setBounds(20, 58, 115, 30);
		panelInfoOferta.add(lblDescripcion);
		
		txtDesc = new JTextArea();//
		txtDesc.setWrapStyleWord(true);
		txtDesc.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDesc.setEditable(false);
		txtDesc.setLineWrap(true);
		txtDesc.setColumns(10);
		txtDesc.setBounds(142, 27, 366, 82);
		panelInfoOferta.add(txtDesc);
		
		JLabel lblCiudad = new JLabel("Ciudad :");
		lblCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCiudad.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCiudad.setBounds(20, 125, 115, 30);
		panelInfoOferta.add(lblCiudad);
		
		txtCiudad = new JTextField(); //
		txtCiudad.setEditable(false);
		txtCiudad.setColumns(10);
		txtCiudad.setBounds(144, 130, 364, 22);
		panelInfoOferta.add(txtCiudad);
		
		JLabel lblDepartamento = new JLabel("Departamento :");
		lblDepartamento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDepartamento.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDepartamento.setBounds(20, 165, 115, 30);
		panelInfoOferta.add(lblDepartamento);
		
		txtDepto = new JTextField(); //
		txtDepto.setEditable(false);
		txtDepto.setColumns(10);
		txtDepto.setBounds(142, 170, 364, 22);
		panelInfoOferta.add(txtDepto);
		
		JLabel lblHorario = new JLabel("Horario :");
		lblHorario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHorario.setFont(new Font("Dialog", Font.BOLD, 12));
		lblHorario.setBounds(20, 205, 115, 30);
		panelInfoOferta.add(lblHorario);
		
		txtHorario = new JTextField();
		txtHorario.setEditable(false);
		txtHorario.setColumns(10);
		txtHorario.setBounds(142, 210, 364, 22);
		panelInfoOferta.add(txtHorario);
		
		JLabel lblRem = new JLabel("Remuneracion :");
		lblRem.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRem.setFont(new Font("Dialog", Font.BOLD, 12));
		lblRem.setBounds(20, 245, 115, 30);
		panelInfoOferta.add(lblRem);
		
		txtRem = new JTextField();//
		txtRem.setEditable(false);
		txtRem.setColumns(10);
		txtRem.setBounds(144, 250, 364, 22);
		panelInfoOferta.add(txtRem);
		
		JLabel lblFechaOferta = new JLabel("Fecha de Alta :");
		lblFechaOferta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaOferta.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFechaOferta.setBounds(20, 285, 115, 30);
		panelInfoOferta.add(lblFechaOferta);
		
		
		txtFecha = new JTextField();//
		txtFecha.setEditable(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(144, 290, 364, 22);
		panelInfoOferta.add(txtFecha);
		
		scrollPane_2 = new JScrollPane(txtDesc);
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setBounds(142, 27, 366, 82);
		panelInfoOferta.add(scrollPane_2);
		
		
		
/*---------------------------------------- ELEGIR OFERTA ----------------------------------------*/
		
		lblNoOf = new JLabel("La empresa elegida no tiene ofertas.");
		lblNoOf.setFont(new Font("Arial", Font.BOLD, 12));
		lblNoOf.setBounds(126, 71, 323, 19);
		getContentPane().add(lblNoOf);
		
		lblOferta = new JLabel("Oferta :");
		lblOferta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOferta.setFont(new Font("Dialog", Font.BOLD, 12));
		lblOferta.setBounds(20, 65, 94, 25);
		getContentPane().add(lblOferta);
		
		comboBoxOfertas = new JComboBox<String>();
		comboBoxOfertas.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxOfertas.setBounds(124, 65, 325, 25);
		getContentPane().add(comboBoxOfertas);
		
		comboBoxOfertas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ofertaElegida = (String) ((JComboBox)e.getSource()).getSelectedItem();
				if (ofertaElegida!="-") {
					FrameInfoOferta.setVisible(true);
					
					ofertaElegida = (String) ((JComboBox)e.getSource()).getSelectedItem();
					DtOfertaCompleto dtoc = ctrlLaboral.mostrarOfertaCompleto(ofertaElegida);
					DtOferta dto = dtoc.getOferta();
					txtDesc.setText(dto.getDescripcion());
					txtCiudad.setText(dto.getCiudad());
					txtDepto.setText(dto.getDepartamento());
					txtHorario.setText(dto.getHorarioTrabajo());
					txtRem.setText(String.valueOf(dto.getRemuneracion()) + "$"); 
					txtFecha.setText(dto.getFechaAlta());
				} else {
					FrameInfoOferta.setVisible(false);
				}
				
					
			}
		});
		
		
/*---------------------------------------- ELEGIR POSTULANTE ----------------------------------------*/
		JLabel lblPostulante = new JLabel("Postulante :");
		lblPostulante.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPostulante.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPostulante.setBounds(20, 108, 94, 30);
		getContentPane().add(lblPostulante);
		
		comboBoxPostulantes = new JComboBox<String>();
		comboBoxPostulantes.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxPostulantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				postElegido = (String) ((JComboBox)e.getSource()).getSelectedItem();
				
			}
		});
		comboBoxPostulantes.setBounds(124, 111, 325, 25);
		getContentPane().add(comboBoxPostulantes);
		
		
/*---------------------------------------- CARGA DATOS ----------------------------------------*/
		JLabel lblCV = new JLabel("CV Reducido :");
		lblCV.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCV.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCV.setBounds(12, 174, 102, 30);
		getContentPane().add(lblCV);
		
		txtCV = new JTextArea();
		txtCV.setWrapStyleWord(true);
		txtCV.setLineWrap(true);
		txtCV.setFont(new Font("Arial", Font.PLAIN, 12));
		txtCV.setBounds(124, 156, 325, 73);
		getContentPane().add(txtCV);
		txtCV.setColumns(10);
		
		JLabel lblMotivacion = new JLabel("Motivación :");
		lblMotivacion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMotivacion.setFont(new Font("Dialog", Font.BOLD, 12));
		lblMotivacion.setBounds(20, 261, 94, 30);
		getContentPane().add(lblMotivacion);
		
		txtMot = new JTextArea();
		txtMot.setLineWrap(true);
		txtMot.setWrapStyleWord(true);
		txtMot.setFont(new Font("Arial", Font.PLAIN, 12));
		txtMot.setBounds(124, 241, 325, 73);
		getContentPane().add(txtMot);
		txtMot.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha Actual :");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFecha.setBounds(20, 330, 94, 30);
		getContentPane().add(lblFecha);
		fecha = new JTextField();
		fecha.setFont(new Font("Arial", Font.PLAIN, 12));
		fecha.setEditable(false);
		fecha.setBounds(126, 334, 323, 25);
		getContentPane().add(fecha);
		fecha.setColumns(10);
		
		
/*---------------------------------------- CONFIRMACION ----------------------------------------*/		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancelar.setBounds(340, 377, 96, 30);
		getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				postularAceptar();
				
			}
		});
		btnAceptar.setBounds(207, 377, 96, 30);
		getContentPane().add(btnAceptar);
		
		scrollPane = new JScrollPane(txtCV);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(124, 156, 325, 73);
		getContentPane().add(scrollPane);
		
		scrollPane_1 = new JScrollPane(txtMot);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(124, 241, 325, 73);
		getContentPane().add(scrollPane_1);	
	}
	
	public void cargarEmpresas() throws NonExistentException {
		String[] empresas = ctrlUsuario.listarEmpresas();
		if (empresas.length == 0) 
			throw new NonExistentException("No hay empresas en el sistema");
		String[] aux = {"-"};
		String[] mostEmp = new String[1 + empresas.length];
		System.arraycopy(aux, 0, mostEmp, 0, aux.length);
        System.arraycopy(empresas, 0, mostEmp, aux.length, empresas.length);
		DefaultComboBoxModel<String> model;
        model = new DefaultComboBoxModel<String>(mostEmp);
        comboBoxEmpresas.setModel(model);
	}
	public void cargarPostulantes() throws NonExistentException {
		String[] postulantes = ctrlUsuario.listarPostulantes();
		if (postulantes.length == 0) 
			throw new NonExistentException("No hay postulantes en el sistema");
		String[] aux = {"-"};
		String[] mostPost = new String[1 + postulantes.length];
		System.arraycopy(aux, 0, mostPost, 0, aux.length);
        System.arraycopy(postulantes, 0, mostPost, aux.length, postulantes.length);
		DefaultComboBoxModel<String> model;
        model = new DefaultComboBoxModel<String>(mostPost);
        comboBoxPostulantes.setModel(model);
	}
	
	protected void postularAceptar() {
		if (checkVacio()) {
			String CV = this.txtCV.getText();
			String mot = this.txtMot.getText();
			try {
				ctrlLaboral.postular(postElegido, ofertaElegida, CV, mot, LocalDate.now(),"");
				JOptionPane.showMessageDialog(this, "La postulación se ha realizado con éxito.", "Postulacion a Oferta Laboral",
                        JOptionPane.INFORMATION_MESSAGE);
				limpiarFormulario();
				cargarFechaActual();
			} catch (DuplicationException | OutOfMarginException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Postulacion a Oferta Laboral", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public boolean checkVacio() {
		String CV = this.txtCV.getText();
		String mot = this.txtMot.getText();
	    
	   
	    if (CV.isEmpty() || mot.isEmpty() || this.ofertaElegida == null || this.postElegido == null|| this.ofertaElegida == "-" || this.postElegido == "-") {
	        JOptionPane.showMessageDialog(this, "No puede haber campos vacíos.", "Postulacion a Oferta Laboral", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }		
	    return true;
	}
	
	// Limpia el formulario	
	public void limpiarFormulario() {
    	txtCV.setText("");
        txtMot.setText("");

        comboBoxEmpresas.setSelectedItem("-");
    	comboBoxPostulantes.setSelectedItem("-");
    	
		FrameInfoOferta.setVisible(false);
		comboBoxOfertas.setVisible(false);
		lblNoOf.setVisible(false);
		lblOferta.setVisible(false);
    }
	
	public void cargarFechaActual() {
		LocalDate date = LocalDate.now();
		fecha.setText(String.valueOf(date.getDayOfMonth()) + "/" + String.valueOf(date.getMonthValue()) + "/" + String.valueOf(date.getYear()));
		
	}
}



