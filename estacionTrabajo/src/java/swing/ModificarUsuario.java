package swing;

import javax.swing.JInternalFrame;

import model.datatype.DtEmpresa;
import model.datatype.DtPostulante;
import model.datatype.DtPostulanteCompleto;
import model.interfacess.IcontroladorUsuario;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import model.exceptions.DuplicationException;
import model.exceptions.NonExistentException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class ModificarUsuario extends JInternalFrame {
	
    private IcontroladorUsuario controladorUsuario;
   
    @SuppressWarnings("rawtypes")
	private JComboBox comboBoxUsuarios;
    
    private JTextField fieldNicknameEmpresa;
    private JTextField fieldCorreoEmpresa;
    private JTextField fieldNombreEmpresa;
    private JTextField fieldApellidoEmpresa;
    private JTextField fieldLink;
    private JTextArea fieldDescripcion;
    private JScrollPane scrollPane;
    
    JButton btnModificarEmpresa;
    
    private JTextField fieldNicknamePostulante;
    private JTextField fieldCorreoPostulante;
    private JTextField fieldNombrePostulante;
    private JTextField fieldApellidoPostulante;
    private JDateChooser fieldNacimiento;
    private JTextField fieldNacionalidad;
    private JTextField fieldPasswordEmpresa;
    private JTextField fieldPasswordPostulante;
    private String imagen;

	@SuppressWarnings("rawtypes")
	public ModificarUsuario(IcontroladorUsuario icu) {
		
        controladorUsuario = icu;
		
		setAutoscrolls(true);
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("Modificar Usuario");
        setBounds(10, 40, 430, 491);
		getContentPane().setLayout(null);	
		
		// InternalFrame Postulante
		JInternalFrame internalFramePostulante = new JInternalFrame("Postulante");
		internalFramePostulante.setBounds(12, 12, 398, 299);
		getContentPane().add(internalFramePostulante);
		internalFramePostulante.getContentPane().setLayout(null);
		internalFramePostulante.setVisible(false);
		
		JLabel lblNickname_1 = new JLabel("Nickname:");
		lblNickname_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNickname_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNickname_1.setBounds(18, 31, 93, 15);
		internalFramePostulante.getContentPane().add(lblNickname_1);
		
		JLabel lblCorreo_1 = new JLabel("Correo:");
		lblCorreo_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCorreo_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblCorreo_1.setBounds(41, 63, 70, 15);
		internalFramePostulante.getContentPane().add(lblCorreo_1);
		
		JLabel lblNombre_1 = new JLabel("Nombre:");
		lblNombre_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNombre_1.setBounds(41, 90, 70, 15);
		internalFramePostulante.getContentPane().add(lblNombre_1);
		
		JLabel lblApellido_1 = new JLabel("Apellido:");
		lblApellido_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblApellido_1.setBounds(41, 117, 70, 15);
		internalFramePostulante.getContentPane().add(lblApellido_1);
		
		JLabel lblNacimiento = new JLabel("Nacimiento:");
		lblNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNacimiento.setFont(new Font("Arial", Font.BOLD, 12));
		lblNacimiento.setBounds(21, 171, 90, 15);
		internalFramePostulante.getContentPane().add(lblNacimiento);
		
		JLabel lblNacionalidad = new JLabel("Nacionalidad:");
		lblNacionalidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNacionalidad.setFont(new Font("Arial", Font.BOLD, 12));
		lblNacionalidad.setBounds(12, 198, 99, 15);
		internalFramePostulante.getContentPane().add(lblNacionalidad);
		
		fieldNicknamePostulante = new JTextField();
		fieldNicknamePostulante.setFont(new Font("Arial", Font.PLAIN, 12));
		fieldNicknamePostulante.setEditable(false);
		fieldNicknamePostulante.setBounds(123, 31, 246, 19);
		internalFramePostulante.getContentPane().add(fieldNicknamePostulante);
		fieldNicknamePostulante.setColumns(10);
		
		fieldCorreoPostulante = new JTextField();
		fieldCorreoPostulante.setFont(new Font("Arial", Font.PLAIN, 12));
		fieldCorreoPostulante.setEditable(false);
		fieldCorreoPostulante.setBounds(123, 63, 246, 19);
		internalFramePostulante.getContentPane().add(fieldCorreoPostulante);
		fieldCorreoPostulante.setColumns(10);
		
		fieldNombrePostulante = new JTextField();
		fieldNombrePostulante.setFont(new Font("Arial", Font.PLAIN, 12));
		fieldNombrePostulante.setBounds(123, 90, 246, 19);
		internalFramePostulante.getContentPane().add(fieldNombrePostulante);
		fieldNombrePostulante.setColumns(10);
		
		fieldApellidoPostulante = new JTextField();
		fieldApellidoPostulante.setFont(new Font("Arial", Font.PLAIN, 12));
		fieldApellidoPostulante.setBounds(123, 117, 246, 19);
		internalFramePostulante.getContentPane().add(fieldApellidoPostulante);
		fieldApellidoPostulante.setColumns(10);
		
		fieldNacimiento = new JDateChooser();
		fieldNacimiento.setBounds(123, 171, 246, 19);
		internalFramePostulante.getContentPane().add(fieldNacimiento);
		
		fieldNacionalidad = new JTextField();
		fieldNacionalidad.setFont(new Font("Arial", Font.PLAIN, 12));
		fieldNacionalidad.setBounds(123, 198, 246, 19);
		internalFramePostulante.getContentPane().add(fieldNacionalidad);
		fieldNacionalidad.setColumns(10);
		
		JButton btnModificarPostulante = new JButton("Modificar");
		btnModificarPostulante.setFont(new Font("Arial", Font.PLAIN, 14));
		btnModificarPostulante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarData(e);
				limpiarFormularioPostulante();
				internalFramePostulante.setVisible(false);
			}
		});
		btnModificarPostulante.setBounds(123, 229, 117, 25);
		internalFramePostulante.getContentPane().add(btnModificarPostulante);
		internalFramePostulante.setVisible(false);
		
		JButton btnCancelar_1 = new JButton("Cancelar");
		btnCancelar_1.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancelar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internalFramePostulante.setVisible(false);
			}
		});
		
		btnCancelar_1.setBounds(252, 229, 117, 25);
		internalFramePostulante.getContentPane().add(btnCancelar_1);
		
		JLabel labelPasswordPostulante = new JLabel("Password:");
		labelPasswordPostulante.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPasswordPostulante.setFont(new Font("Dialog", Font.BOLD, 12));
		labelPasswordPostulante.setBounds(21, 144, 90, 15);
		internalFramePostulante.getContentPane().add(labelPasswordPostulante);
		
		fieldPasswordPostulante = new JTextField();
		fieldPasswordPostulante.setFont(new Font("Dialog", Font.PLAIN, 12));
		fieldPasswordPostulante.setColumns(10);
		fieldPasswordPostulante.setBounds(123, 144, 246, 19);
		internalFramePostulante.getContentPane().add(fieldPasswordPostulante);
		
		// InternalFrame Empresa
		JInternalFrame internalFrameEmpresa = new JInternalFrame("Empresa");
		internalFrameEmpresa.setBounds(12, 12, 398, 388);
		getContentPane().add(internalFrameEmpresa);
		internalFrameEmpresa.getContentPane().setLayout(null);
		internalFrameEmpresa.setVisible(false);
		
				JLabel lblNickname = new JLabel("Nickname:");
				lblNickname.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNickname.setFont(new Font("Arial", Font.BOLD, 12));
				lblNickname.setBounds(-1, 37, 95, 15);
				internalFrameEmpresa.getContentPane().add(lblNickname);
				
				JLabel lblCorreo = new JLabel("Correo:");
				lblCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
				lblCorreo.setFont(new Font("Arial", Font.BOLD, 12));
				lblCorreo.setBounds(24, 64, 70, 19);
				internalFrameEmpresa.getContentPane().add(lblCorreo);
				
				JLabel lblNombre = new JLabel("Nombre:");
				lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNombre.setFont(new Font("Arial", Font.BOLD, 12));
				lblNombre.setBounds(24, 104, 70, 15);
				internalFrameEmpresa.getContentPane().add(lblNombre);
				
				JLabel lblApellido = new JLabel("Apellido:");
				lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
				lblApellido.setFont(new Font("Arial", Font.BOLD, 12));
				lblApellido.setBounds(24, 142, 70, 15);
				internalFrameEmpresa.getContentPane().add(lblApellido);
				
				JLabel lblLink = new JLabel("Link:");
				lblLink.setHorizontalAlignment(SwingConstants.RIGHT);
				lblLink.setFont(new Font("Arial", Font.BOLD, 12));
				lblLink.setBounds(24, 202, 70, 15);
				internalFrameEmpresa.getContentPane().add(lblLink);
				
				JLabel lblDescripcion = new JLabel("Descripción:");
				lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
				lblDescripcion.setFont(new Font("Arial", Font.BOLD, 12));
				lblDescripcion.setBounds(-1, 234, 95, 15);
				internalFrameEmpresa.getContentPane().add(lblDescripcion);
				
				fieldNicknameEmpresa = new JTextField();
				fieldNicknameEmpresa.setEditable(false);
				fieldNicknameEmpresa.setFont(new Font("Arial", Font.PLAIN, 12));
				fieldNicknameEmpresa.setBounds(112, 35, 264, 19);
				internalFrameEmpresa.getContentPane().add(fieldNicknameEmpresa);
				fieldNicknameEmpresa.setColumns(10);
				
				fieldCorreoEmpresa = new JTextField();
				fieldCorreoEmpresa.setEditable(false);
				fieldCorreoEmpresa.setFont(new Font("Arial", Font.PLAIN, 12));
				fieldCorreoEmpresa.setBounds(112, 64, 264, 19);
				internalFrameEmpresa.getContentPane().add(fieldCorreoEmpresa);
				fieldCorreoEmpresa.setColumns(10);
				
				fieldNombreEmpresa = new JTextField();
				fieldNombreEmpresa.setFont(new Font("Arial", Font.PLAIN, 12));
				fieldNombreEmpresa.setBounds(112, 102, 264, 19);
				internalFrameEmpresa.getContentPane().add(fieldNombreEmpresa);
				fieldNombreEmpresa.setColumns(10);
				
				fieldApellidoEmpresa = new JTextField();
				fieldApellidoEmpresa.setFont(new Font("Arial", Font.PLAIN, 12));
				fieldApellidoEmpresa.setBounds(112, 140, 264, 19);
				internalFrameEmpresa.getContentPane().add(fieldApellidoEmpresa);
				fieldApellidoEmpresa.setColumns(10);
				
				fieldLink = new JTextField();
				fieldLink.setFont(new Font("Arial", Font.PLAIN, 12));
				fieldLink.setBounds(112, 202, 264, 19);
				internalFrameEmpresa.getContentPane().add(fieldLink);
				fieldLink.setColumns(10);
				
				fieldDescripcion = new JTextArea();
				fieldDescripcion.setWrapStyleWord(true);
				fieldDescripcion.setLineWrap(true);
				fieldDescripcion.setFont(new Font("Arial", Font.PLAIN, 12));
				fieldDescripcion.setBounds(100, 209, 176, 48);
				internalFrameEmpresa.getContentPane().add(fieldDescripcion);
				fieldDescripcion.setColumns(10);
				
				btnModificarEmpresa = new JButton("Modificar");
				btnModificarEmpresa.setFont(new Font("Arial", Font.PLAIN, 14));
				btnModificarEmpresa.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						modificarData(e);
						limpiarFormularioEmpresa();
						internalFrameEmpresa.setVisible(false);
					}
				});
				btnModificarEmpresa.setBounds(112, 322, 117, 25);
				internalFrameEmpresa.getContentPane().add(btnModificarEmpresa);
				
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						limpiarFormularioEmpresa();
						internalFrameEmpresa.setVisible(false);
					}
				});
				
				btnCancelar.setBounds(259, 322, 117, 25);
				internalFrameEmpresa.getContentPane().add(btnCancelar);
				getContentPane().add(internalFrameEmpresa);
				internalFrameEmpresa.getContentPane().setLayout(null);
				
				scrollPane = new JScrollPane(fieldDescripcion);
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				scrollPane.setBounds(112, 233, 264, 77);
				internalFrameEmpresa.getContentPane().add(scrollPane);
				
				fieldPasswordEmpresa = new JTextField();
				fieldPasswordEmpresa.setFont(new Font("Dialog", Font.PLAIN, 12));
				fieldPasswordEmpresa.setColumns(10);
				fieldPasswordEmpresa.setBounds(112, 171, 264, 19);
				internalFrameEmpresa.getContentPane().add(fieldPasswordEmpresa);
				
				JLabel labelPasswordEmpresa = new JLabel("Contraseña:");
				labelPasswordEmpresa.setHorizontalAlignment(SwingConstants.RIGHT);
				labelPasswordEmpresa.setFont(new Font("Dialog", Font.BOLD, 12));
				labelPasswordEmpresa.setBounds(-1, 169, 95, 15);
				internalFrameEmpresa.getContentPane().add(labelPasswordEmpresa);
		
		JLabel lblElegirUsuario = new JLabel("Elegir usuario:");
		lblElegirUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblElegirUsuario.setFont(new Font("Arial", Font.BOLD, 12));
		lblElegirUsuario.setBounds(25, 34, 135, 15);
		getContentPane().add(lblElegirUsuario);		
		
		comboBoxUsuarios = new JComboBox();
		comboBoxUsuarios.setBounds(193, 28, 178, 24);
		getContentPane().add(comboBoxUsuarios);		
		
		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 14));
		btnNewButton.setBounds(164, 380, 85, 21);
		getContentPane().add(btnNewButton);
		
		comboBoxUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nickname = (String)((JComboBox) e.getSource()).getSelectedItem();
				if(controladorUsuario.esEmpresa(nickname)) {
					internalFramePostulante.setVisible(false);
					internalFrameEmpresa.setVisible(true);
					DtEmpresa empresa = controladorUsuario.mostrarEmpresa(nickname).getEmpresa();
					imagen = empresa.getImagen();
					fieldNicknameEmpresa.setText(empresa.getNickname());
					fieldCorreoEmpresa.setText(empresa.getCorreoElectronico());
					fieldNombreEmpresa.setText(empresa.getNombre());
					fieldApellidoEmpresa.setText(empresa.getApellido());
					fieldDescripcion.setText(empresa.getDescripcion());
					fieldLink.setText(empresa.getLink());
					fieldPasswordEmpresa.setText(empresa.getPassword());
				}
				if(controladorUsuario.esPostulante(nickname)) {
					internalFrameEmpresa.setVisible(false);
					internalFramePostulante.setVisible(true);
					DtPostulante postulante = controladorUsuario.mostrarPostulante(nickname).getPostulante();
					fieldNicknamePostulante.setText(postulante.getNickname());
					fieldCorreoPostulante.setText(postulante.getCorreoElectronico());
					fieldNombrePostulante.setText(postulante.getNombre());
					fieldApellidoPostulante.setText(postulante.getApellido());
					imagen = postulante.getImagen();
			        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					LocalDate fecha = LocalDate.parse(postulante.getNacimiento(), formatter);
					Date date = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
					fieldNacimiento.setDate(date);
					fieldNacionalidad.setText(postulante.getNacionalidad());
					fieldPasswordPostulante.setText(postulante.getPassword());
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormularioEmpresa();
				limpiarFormularioPostulante();
				setVisible(false);
				internalFramePostulante.setVisible(false);
				internalFrameEmpresa.setVisible(false);
			}
		});
		
	}
	
	protected void modificarData(ActionEvent arg0) {		
		String nickname = (String) comboBoxUsuarios.getSelectedItem();
		
		if(controladorUsuario.esEmpresa(nickname) && checkFormularioEmpresa()) {
			String nombreE = fieldNombreEmpresa.getText();
			String apellidoE = fieldApellidoEmpresa.getText();
			String link = fieldLink.getText();
			String descripcion = fieldDescripcion.getText();
			String password = fieldPasswordEmpresa.getText();
			controladorUsuario.modificarDatosEmpresa(nickname, nombreE, apellidoE, link, descripcion, imagen, password);
		}
		if(controladorUsuario.esPostulante(nickname) && checkFormularioPostulante()) {
			String nombreP = fieldNombrePostulante.getText();
			String apellidoP = fieldApellidoPostulante.getText();
			String nacionalidad = fieldNacionalidad.getText();
			Date date = fieldNacimiento.getDate();
			LocalDate nacimiento = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			String password = fieldPasswordPostulante.getText();
			controladorUsuario.modificarDatosPostulante(nickname, nombreP, apellidoP, nacimiento, nacionalidad, "", password);
			
		}
	}
	
	private boolean checkFormularioPostulante() {
		String nombreP = this.fieldNombrePostulante.getText();
	    String apellidoP = this.fieldApellidoPostulante.getText();
	    Date fechaNacimientoE = this.fieldNacimiento.getDate();
	    String nacionalidadE = this.fieldNacionalidad.getText();	
        String password = this.fieldPasswordPostulante.getText();
	    if (nombreP.isEmpty() || apellidoP.isEmpty() || fechaNacimientoE == null || nacionalidadE.isEmpty() || nombreP.strip() == "" ||
	    		apellidoP.strip() == "" || nacionalidadE.strip() == "" || password.strip() == "") {
	        JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Postulante", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }		
	    return true;
	}   
	
    private boolean checkFormularioEmpresa() {
    	String linkE = this.fieldLink.getText();
    	String descripcionE = this.fieldDescripcion.getText();
        String nombreE = this.fieldNombreEmpresa.getText();
        String apellidoE = this.fieldApellidoEmpresa.getText();
        String password = this.fieldPasswordEmpresa.getText();
        if (linkE.isEmpty() || descripcionE.isEmpty() || nombreE.isEmpty() || apellidoE.isEmpty() || nombreE.strip() == "" || apellidoE.strip() == ""
        		|| linkE.strip() == "" || descripcionE.strip() == "" || password.strip() == "") {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Empresa", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private void limpiarFormularioEmpresa() {
    	fieldNicknameEmpresa.setText("");
    	fieldNombreEmpresa.setText("");
        fieldApellidoEmpresa.setText("");
        fieldCorreoEmpresa.setText("");
    	fieldDescripcion.setText("");
    	fieldLink.setText("");
    	fieldPasswordEmpresa.setText("");
	 }    
    
    private void limpiarFormularioPostulante() {
    	fieldNicknamePostulante.setText("");
    	fieldNombrePostulante.setText("");
        fieldApellidoPostulante.setText("");
        fieldCorreoPostulante.setText("");
        fieldNacimiento.setCalendar(null);
        fieldNacionalidad.setText("");
        fieldPasswordPostulante.setText("");
	 }    
    
	@SuppressWarnings("unchecked")
	public void listarUsuarios() {
		String[] aux = {"-"};
		String[] lista = controladorUsuario.listarUsuarios();
		String[] usuariosList = new String[aux.length + lista.length];
		System.arraycopy(aux, 0, usuariosList, 0, aux.length);
        System.arraycopy(lista, 0, usuariosList, aux.length, lista.length);
		if (usuariosList.length == 1)
			throw new NonExistentException("No hay usuarios en el sistema");
        DefaultComboBoxModel<String> model;
		model = new DefaultComboBoxModel<String>(usuariosList);
	    comboBoxUsuarios.setModel(model);
    }
}
