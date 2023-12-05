package swing;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import model.exceptions.DuplicationException;
import model.interfacess.IcontroladorUsuario;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class AltaUsuario extends JInternalFrame{
	
	private IcontroladorUsuario ctrlUsuario;
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	
    private JTextField textFieldNickname;
    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JTextField textFieldCorreo;
    private JDateChooser textFieldFechaNacimiento; 
    private JTextField textFieldNacionalidad;
    private JTextField textFieldLink;
    private JTextArea textFieldDescripcion;
    
    private JLabel lblCheckConfirmacion;
    private JLabel lblIngreseNickname;
    private JLabel lblIngreseNombre;
    private JLabel lblIngreseApellido;
    private JLabel lblIngreseCorreo;
    private JLabel lblIngreseFechaNacimiento;
    private JLabel lblIngreseNacionalidad;
    private JLabel lblIngreseLink;
    private JLabel lblIngreseDescripcion;

    private JButton btnAceptar;
    private JButton btnCancelar;
    private JScrollPane scrollPane;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;
    private JLabel lblPassword;
    private JTextField textField;
    private JPanel pnlPostulante;
    private JPanel pnlEmpresa;

    
	@SuppressWarnings({ "unchecked", "rawtypes" }) 
	AltaUsuario(IcontroladorUsuario icu) {
		
		ctrlUsuario = icu;
        
		// JInternalFrame
        setResizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("Registrar Usuario");
        setBounds(10, 30, 437, 512);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBox.setBounds(149, 10, 236, 25);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarCampos(comboBox.getSelectedItem().toString());
			}
		});
		getContentPane().setLayout(null);
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"-", "Empresa", "Postulante" }));
		getContentPane().add(comboBox);
				
        //Nickname
        lblIngreseNickname = new JLabel("Nickname: ");
        lblIngreseNickname.setFont(new Font("Dialog", Font.BOLD, 12));
        lblIngreseNickname.setBounds(0, 50, 131, 25);
        lblIngreseNickname.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblIngreseNickname);

        textFieldNickname = new JTextField();
        textFieldNickname.setFont(new Font("Arial", Font.PLAIN, 12));
        textFieldNickname.setBounds(149, 50, 236, 25);
        getContentPane().add(textFieldNickname);
        textFieldNickname.setColumns(10);
        
        // Nombre
        lblIngreseNombre = new JLabel("Nombre: ");
        lblIngreseNombre.setFont(new Font("Dialog", Font.BOLD, 12));
        lblIngreseNombre.setBounds(0, 90, 131, 25);
        lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblIngreseNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setFont(new Font("Arial", Font.PLAIN, 12));
        textFieldNombre.setBounds(149, 90, 236, 25);
        getContentPane().add(textFieldNombre);
        textFieldNombre.setColumns(10);
        
        // Apellido
        lblIngreseApellido = new JLabel("Apellido: ");
        lblIngreseApellido.setFont(new Font("Dialog", Font.BOLD, 12));
        lblIngreseApellido.setBounds(0, 133, 131, 19);
        lblIngreseApellido.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblIngreseApellido);
        
        textFieldApellido = new JTextField();
        textFieldApellido.setFont(new Font("Arial", Font.PLAIN, 12));
        textFieldApellido.setBounds(149, 130, 236, 25);
        getContentPane().add(textFieldApellido);
        textFieldApellido.setColumns(10);
        
        // Correo
        lblIngreseCorreo = new JLabel("Correo: ");
        lblIngreseCorreo.setFont(new Font("Dialog", Font.BOLD, 12));
        lblIngreseCorreo.setBounds(0, 170, 131, 24);
        lblIngreseCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(lblIngreseCorreo);
        
        textFieldCorreo = new JTextField();
        textFieldCorreo.setFont(new Font("Arial", Font.PLAIN, 12));
        textFieldCorreo.setBounds(149, 170, 236, 25);
        getContentPane().add(textFieldCorreo);
        textFieldCorreo.setColumns(10);
                
        // Confirmar registro
        btnAceptar = new JButton("Aceptar");
        btnAceptar.setFont(new Font("Dialog", Font.BOLD, 12));
        btnAceptar.setBounds(65, 437, 100, 30);
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
				cmdRegistroUsuarioActionPerformed(arg0);
            }
        });
        getContentPane().add(btnAceptar);
        
        // Salir de la ventana Registrar
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Dialog", Font.BOLD, 12));
        btnCancelar.setBounds(255, 437, 100, 30);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
        getContentPane().add(btnCancelar);
        
        JLabel lblIngreseTipo = new JLabel("Tipo de Usuario: ");
        lblIngreseTipo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblIngreseTipo.setFont(new Font("Dialog", Font.BOLD, 12));
        lblIngreseTipo.setBounds(4, 10, 127, 25);
        getContentPane().add(lblIngreseTipo);

        // password
        lblPassword = new JLabel("Password:");
        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPassword.setBounds(12, 229, 119, 15);
        getContentPane().add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(149, 224, 236, 25);
        getContentPane().add(passwordField);

        // confirmar password
        JLabel lblConfirmar = new JLabel("Confirmar:");
        lblConfirmar.setHorizontalAlignment(SwingConstants.RIGHT);
        lblConfirmar.setBounds(12, 266, 119, 15);
        getContentPane().add(lblConfirmar);

        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(149, 261, 236, 25);
        getContentPane().add(passwordField_1);

        pnlEmpresa = new JPanel();
        pnlEmpresa.setBounds(-18, 311, 420, 114);
        getContentPane().add(pnlEmpresa);
        pnlEmpresa.setLayout(null);
        pnlEmpresa.setVisible(false);

        textFieldDescripcion = new JTextArea();
        textFieldDescripcion.setWrapStyleWord(true);
        textFieldDescripcion.setFont(new Font("Arial", Font.PLAIN, 12));
        textFieldDescripcion.setLineWrap(true);

        textFieldDescripcion.setBounds(115, 330, 270, 58);
        getContentPane().add(textFieldDescripcion);
        textFieldDescripcion.setColumns(10);

        scrollPane = new JScrollPane(textFieldDescripcion);
        scrollPane.setOpaque(false);
        scrollPane.setBounds(168, 42, 233, 60);
        pnlEmpresa.add(scrollPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Descripcion
        lblIngreseDescripcion = new JLabel("Descripcion: ");
        lblIngreseDescripcion.setBounds(12, 58, 138, 19);
        pnlEmpresa.add(lblIngreseDescripcion);
        lblIngreseDescripcion.setFont(new Font("Dialog", Font.BOLD, 12));
        lblIngreseDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);

        textFieldLink = new JTextField();
        textFieldLink.setOpaque(false);
        textFieldLink.setBounds(168, 5, 233, 25);
        pnlEmpresa.add(textFieldLink);
        textFieldLink.setFont(new Font("Arial", Font.PLAIN, 12));
        textFieldLink.setColumns(10);

        // Link 
        lblIngreseLink = new JLabel("Link: ");
        lblIngreseLink.setBounds(12, 8, 138, 19);
        pnlEmpresa.add(lblIngreseLink);
        lblIngreseLink.setFont(new Font("Dialog", Font.BOLD, 12));
        lblIngreseLink.setHorizontalAlignment(SwingConstants.RIGHT);

        pnlPostulante = new JPanel();
        pnlPostulante.setLayout(null);
        pnlPostulante.setBounds(-5, 311, 407, 114);
        getContentPane().add(pnlPostulante);
        pnlPostulante.setVisible(false);

        textFieldFechaNacimiento = new JDateChooser();
        textFieldFechaNacimiento.setOpaque(false);
        textFieldFechaNacimiento.setBounds(156, 6, 233, 25);
        pnlPostulante.add(textFieldFechaNacimiento);

        textFieldNacionalidad = new JTextField();
        textFieldNacionalidad.setBounds(156, 60, 233, 25);
        pnlPostulante.add(textFieldNacionalidad);
        textFieldNacionalidad.setFont(new Font("Arial", Font.PLAIN, 12));
        textFieldNacionalidad.setColumns(10);

        // Fecha Nacimiento
        lblIngreseFechaNacimiento = new JLabel("Nacimiento: ");
        lblIngreseFechaNacimiento.setBounds(0, 6, 150, 19);
        pnlPostulante.add(lblIngreseFechaNacimiento);
        lblIngreseFechaNacimiento.setFont(new Font("Dialog", Font.BOLD, 12));
        lblIngreseFechaNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);

        // Nacionalidad
        lblIngreseNacionalidad = new JLabel("Nacionalidad: ");
        lblIngreseNacionalidad.setBounds(0, 63, 150, 19);
        pnlPostulante.add(lblIngreseNacionalidad);
        lblIngreseNacionalidad.setFont(new Font("Dialog", Font.BOLD, 12));
        lblIngreseNacionalidad.setHorizontalAlignment(SwingConstants.RIGHT);

	} // Fin Constructor
	
	// Chequea que el nickname ni correo esten repetidos y que las pass coincidan
    protected void cmdRegistroUsuarioActionPerformed(ActionEvent arg0) {	    	
		String tipoUsuario = comboBox.getSelectedItem().toString();
        String nicknameU = this.textFieldNickname.getText();
        String correoU = this.textFieldCorreo.getText();
        String nombreU = this.textFieldNombre.getText();
        String apellidoU = this.textFieldApellido.getText();
    	String linkU = this.textFieldLink.getText();
        String descripcionU = this.textFieldDescripcion.getText();
        String nacionalidadU = this.textFieldNacionalidad.getText();

        /* contraseña */
        char[] password1 = this.passwordField.getPassword();
        char[] password2 = this.passwordField_1.getPassword();
        String pass1_string = String.valueOf(password1);
        String pass2_string = String.valueOf(password2);

        if (tipoUsuario == "Empresa" && checkFormularioEmpresa() && concidenPassword(pass1_string, pass2_string) && comprobarCorreo(correoU)) {
            try {
                ctrlUsuario.ingresarEmpresa(linkU, descripcionU, nombreU, apellidoU, nicknameU, correoU, pass1_string, "");
                JOptionPane.showMessageDialog(this, "El Usuario se ha creado con éxito", "Registrar Usuario",
                        JOptionPane.INFORMATION_MESSAGE);
                limpiarFormulario();
            } catch (DuplicationException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
            }

        }
        if (tipoUsuario == "Postulante" && checkFormularioPostulante() && concidenPassword(pass1_string, pass2_string) && comprobarCorreo(correoU)) {
            try {
         
                Date date = textFieldFechaNacimiento.getDate();
    			LocalDate nacimiento = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                ctrlUsuario.ingresarPostulante(nacimiento, nacionalidadU, nombreU, apellidoU, nicknameU, correoU, pass1_string, "");
                JOptionPane.showMessageDialog(this, "El Usuario se ha creado con éxito", "Registrar Usuario",
                        JOptionPane.INFORMATION_MESSAGE);
                limpiarFormulario();
            } catch (DuplicationException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
            }
            // Limpio el internal frame antes de cerrar la ventan

        }else if (tipoUsuario == "-"){
        	JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
        }
    }
    
	private void habilitarCampos(String tipoUsuario) {
		if (tipoUsuario == "Postulante") {
            pnlPostulante.setVisible(true);
            pnlEmpresa.setVisible(false);
		}else if (tipoUsuario == "Empresa"){
            pnlPostulante.setVisible(false);
            pnlEmpresa.setVisible(true);
		} else {
			pnlPostulante.setVisible(false);
			pnlEmpresa.setVisible(false);
		}
    }

    private boolean comprobarCorreo(String correo) {
    	
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher matcher = pattern.matcher(correo);

        if (matcher.find() == true) {
            return true;
        } else {
	        JOptionPane.showMessageDialog(this, "Correo invalido", "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    private boolean concidenPassword(String password1, String password2) {
		if (!password1.equals(password2)) {
	        JOptionPane.showMessageDialog(this, "Pass no coincide", "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
	        return false;
		}else {
			return true;
		}
    }
	
    // Chequea que no haya campos vacios en postulante
	private boolean checkFormularioPostulante() {
		String nicknameP = this.textFieldNickname.getText();
		String nombreP = this.textFieldNombre.getText();
	    String apellidoP = this.textFieldApellido.getText();
	    String correoP = this.textFieldCorreo.getText();
	    Date fechaNacimientoE = this.textFieldFechaNacimiento.getDate();
	    String nacionalidadE = this.textFieldNacionalidad.getText();	
        char[] passwordE1 = this.passwordField.getPassword();
        String pass1_string = String.valueOf(passwordE1);
        char[] passwordE2 = this.passwordField_1.getPassword();
        String pass2_string = String.valueOf(passwordE2);
	    if (nicknameP.isEmpty() || nombreP.isEmpty() || apellidoP.isEmpty() || correoP.isEmpty() || fechaNacimientoE == null || nacionalidadE.isEmpty()
	    		|| nicknameP.strip() == "" || nombreP.strip() == "" || apellidoP.strip() == "" || correoP.strip() == "" || nacionalidadE.strip() == "" 
	    		|| pass1_string.strip()  == "" || pass2_string.strip()  == "") {
	        JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Postulante", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }		
	    return true;
	}   
	
    // Chequea que no haya campos vacios en empresa
    private boolean checkFormularioEmpresa() {
    	String linkE = this.textFieldLink.getText();
    	String descripcionE = this.textFieldDescripcion.getText();
        String nombreE = this.textFieldNombre.getText();
        String apellidoE = this.textFieldApellido.getText();
        String nicknameE = this.textFieldNickname.getText();
        String correoE = this.textFieldCorreo.getText();
        char[] passwordE1 = this.passwordField.getPassword();
        String pass1_string = String.valueOf(passwordE1);
        char[] passwordE2 = this.passwordField_1.getPassword();
        String pass2_string = String.valueOf(passwordE2);
        if (linkE.isEmpty() || descripcionE.isEmpty() || nombreE.isEmpty() || apellidoE.isEmpty() || nicknameE.isEmpty() || correoE.isEmpty()
        		|| nicknameE.strip() == "" || nombreE.strip() == "" || apellidoE.strip() == "" || correoE.strip() == "" || descripcionE.toString() == ""
        		|| linkE.strip() == "" || pass1_string.strip()  == "" || pass2_string.strip()  == "") {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Empresa", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    public void limpiarFormulario() {
    	textFieldNickname.setText("");
    	textFieldNombre.setText("");
        textFieldApellido.setText("");
        textFieldCorreo.setText("");
        textFieldNacionalidad.setText("");
    	textFieldDescripcion.setText("");
    	textFieldLink.setText("");
    	passwordField.setText("");
    	passwordField_1.setText("");
    	comboBox.setSelectedItem("-");
    	cargarFechaActual();
	 }    
    
    public void cargarFechaActual() {
		LocalDate t = LocalDate.now();
		textFieldFechaNacimiento.setDate(Date.from(t.atStartOfDay(ZoneId.systemDefault()).toInstant()));
	}
}
