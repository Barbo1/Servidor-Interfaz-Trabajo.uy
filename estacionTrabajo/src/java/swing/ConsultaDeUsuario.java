package swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import org.glassfish.ha.store.spi.Storable;

import model.datatype.DtEmpresa;
import model.datatype.DtEmpresaCompleto;
import model.datatype.DtOferta;
import model.datatype.DtOfertaCompleto;
import model.datatype.DtPostulacion;
import model.datatype.DtPostulacionCompleto;
import model.datatype.DtPostulante;
import model.datatype.DtPostulanteCompleto;
import model.datatype.EstadoOferta;
import model.interfacess.IcontroladorLaboral;
import model.interfacess.IcontroladorUsuario;
import model.exceptions.NonExistentException;

@SuppressWarnings("serial")
public class ConsultaDeUsuario extends JInternalFrame{
	
	//ATRIBUTOS DE CONSULTA DE USUARIO
    private IcontroladorUsuario ctrlUsuario;
    private IcontroladorLaboral ctrlLaboral;
    private JComboBox<String> comboBoxUsuarios;
    private JTextField campoPostulanteNickname;
    private JTextField campoPostulanteNombre;
    private JTextField campoPostulanteApellido;
    private JTextField campoPostulanteCorreo;
    private JTextField campoPostulanteTipoDeUsuario;
    private JTextField campoPostulanteNacimiento;
    private JTextField campoPostulanteNacionalidad;
    private JTextArea campoPostulanteDescripcion;
    private JTextField campoPostulanteDepartamento;
    private JTextField campoPostulanteCiudad;
    private JTextField campoPostulanteHorario;
    private JTextField campoPostulanteRemuneracion;
    private JTextField campoEmpresaNombre;
    private JTextField campoEmpresaNickname;
    private JTextField campoEmpresaApellido;
    private JTextField campoEmpresaCorreo;
    private JTextField campoEmpresaTipoDeUsuario;
    private JTextField campoEmpresaLink;
    private JTextArea campoEmpresaDescripcion;
    private JTextField campoEmpresaNombreOferta;
    private JTextArea campoEmpresaDescripcionDeOferta;
    private JTextField campoEmpresaDepartamento;
    private JTextField campoEmpresaCiudad;
    private JTextField campoEmpresaHorario;
    private JTextField campoEmpresaRemuneracion;
    private JTextField campoEmpresaNombrePostulante;
    private JTextArea campoEmpresaCV;
    private JTextArea campoEmpresaMotivacionPostulante;
    private JTextField campoEmpresaFecha;
    private JTextField campoEmpresaTipoDeOferta;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane_1;
    private JScrollPane scrollPane_2;
    private JScrollPane scrollPane_3;
    private String nickGuardado;
    private JTextField textField;
    private JTextArea campoEmpresaMotivacionPostulante_1;
    private JTextArea campoEmpresaCV_1;
    private JTextField fechaField;
    private JTextField campoEmpresaEstado;
    private JTextField campoEmpresaFechaAlta;
    private JTextField campoEmpresaFechaVencimiento;
    
    //CREADOR DE CONSULTA DE USUARIO
    
	public ConsultaDeUsuario(IcontroladorUsuario contrUsuario,IcontroladorLaboral cl) {
		
		//ASIGNACION CONTROLADORES
			this.ctrlUsuario = contrUsuario;
			this.ctrlLaboral = cl;
			
			//INTERNAL FRAME POSTULACIONES EMPRESA
				JInternalFrame postulacionesEmpresaFrame = new JInternalFrame("Información de la postulación. ");
				postulacionesEmpresaFrame.setBounds(20, 10, 450, 348);
				getContentPane().add(postulacionesEmpresaFrame);
				postulacionesEmpresaFrame.getContentPane().setLayout(null);
				postulacionesEmpresaFrame.setVisible(false);
				
//------------------------------------------------------------ INTERNAL FRAME POSTULACIONES EMPRESA ----------------------------------------------------------------
				
						//TEXTO Y CAMPO NOMBRE POSTULANTE
						JLabel textoEmpresaNombreDelPostulante = new JLabel("Nombre del postulante:");
						textoEmpresaNombreDelPostulante.setHorizontalAlignment(SwingConstants.RIGHT);
						textoEmpresaNombreDelPostulante.setFont(new Font("Dialog", Font.BOLD, 12));
						textoEmpresaNombreDelPostulante.setBounds(10, 10, 189, 22);
						postulacionesEmpresaFrame.getContentPane().add(textoEmpresaNombreDelPostulante);
						
						campoEmpresaNombrePostulante = new JTextField();
						campoEmpresaNombrePostulante.setFont(new Font("Tahoma", Font.PLAIN, 12));
						campoEmpresaNombrePostulante.setBounds(204, 10, 212, 21);
						postulacionesEmpresaFrame.getContentPane().add(campoEmpresaNombrePostulante);
						campoEmpresaNombrePostulante.setEditable(false);
						campoEmpresaNombrePostulante.setColumns(10);
						
						//TEXTO Y CAMPO FECHA
						JLabel textoEmpresaFecha = new JLabel("Fecha:");
						textoEmpresaFecha.setHorizontalAlignment(SwingConstants.RIGHT);
						textoEmpresaFecha.setFont(new Font("Dialog", Font.BOLD, 12));
						textoEmpresaFecha.setBounds(10, 42, 189, 22);
						postulacionesEmpresaFrame.getContentPane().add(textoEmpresaFecha);
						
						campoEmpresaFecha = new JTextField();
						campoEmpresaFecha.setFont(new Font("Arial", Font.PLAIN, 12));
						campoEmpresaFecha.setBounds(204, 42, 212, 22);
						postulacionesEmpresaFrame.getContentPane().add(campoEmpresaFecha);
						campoEmpresaFecha.setEditable(false);
						campoEmpresaFecha.setColumns(10);
						
						//TEXTO Y CAMPO MOTIVACION
						JLabel textoEmpresaMotivacion = new JLabel("Motivación:");
						textoEmpresaMotivacion.setFont(new Font("Dialog", Font.BOLD, 12));
						textoEmpresaMotivacion.setBounds(10, 74, 100, 22);
						postulacionesEmpresaFrame.getContentPane().add(textoEmpresaMotivacion);
						
						campoEmpresaMotivacionPostulante = new JTextArea();
						campoEmpresaMotivacionPostulante.setFont(new Font("Arial", Font.PLAIN, 12));
						campoEmpresaMotivacionPostulante.setEditable(false);
						campoEmpresaMotivacionPostulante.setLineWrap(true);
						campoEmpresaMotivacionPostulante.setBounds(10, 99, 406, 74);
						postulacionesEmpresaFrame.getContentPane().add(campoEmpresaMotivacionPostulante);
						
									
									//TEXTO Y CAMPO CV
									JLabel textoEmpresaCV = new JLabel("CV:");
									textoEmpresaCV.setFont(new Font("Dialog", Font.BOLD, 12));
									textoEmpresaCV.setBounds(10, 173, 75, 22);
									postulacionesEmpresaFrame.getContentPane().add(textoEmpresaCV);
									
									campoEmpresaCV = new JTextArea();
									campoEmpresaCV.setFont(new Font("Arial", Font.PLAIN, 12));
									campoEmpresaCV.setEditable(false);
									campoEmpresaCV.setLineWrap(true);
									campoEmpresaCV.setBounds(10, 205, 406, 74);
									postulacionesEmpresaFrame.getContentPane().add(campoEmpresaCV);
									
									JButton btnNewButton_2 = new JButton("Cerrar");
									btnNewButton_2.setFont(new Font("Dialog", Font.PLAIN, 12));
									btnNewButton_2.setSize(100, 25);
									btnNewButton_2.setLocation(331, 289);
									btnNewButton_2.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											campoEmpresaCV.setText("");
											campoEmpresaMotivacionPostulante.setText("");
											campoEmpresaFecha.setText("");
											campoEmpresaNombrePostulante.setText("");
											postulacionesEmpresaFrame.setVisible(false);
										}
									});
									postulacionesEmpresaFrame.getContentPane().add(btnNewButton_2);
									
									scrollPane = new JScrollPane(campoEmpresaMotivacionPostulante);
									scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
									scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
									scrollPane.setBounds(10, 99, 406, 74);
									postulacionesEmpresaFrame.getContentPane().add(scrollPane);
									
									scrollPane_1 = new JScrollPane(campoEmpresaCV);
									scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
									scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
									scrollPane_1.setBounds(10, 207, 406, 72);
									postulacionesEmpresaFrame.getContentPane().add(scrollPane_1);
		
		//INTERNAL FRAME POSTULACIONES POSTULANTE
			JInternalFrame postulantePostulacionesFrame = new JInternalFrame("Información de oferta.");
			postulantePostulacionesFrame.setMaximizable(true);
			postulantePostulacionesFrame.setBounds(30, 0, 414, 574);
			getContentPane().add(postulantePostulacionesFrame);
			postulantePostulacionesFrame.getContentPane().setLayout(null);
			postulantePostulacionesFrame.setVisible(false);
			
//--------------------------------------------------- DATOS DEL INTERNAL FRAME DE POSTULACION --------------------------------------------------------------
			// TEXTO Y CAMPO DESCRIPCION
			JLabel textoPostulanteDescripcion = new JLabel("Descripción:");
			textoPostulanteDescripcion.setFont(new Font("Dialog", Font.BOLD, 12));
			textoPostulanteDescripcion.setBounds(10, 140, 127, 20);
			postulantePostulacionesFrame.getContentPane().add(textoPostulanteDescripcion);
			textoPostulanteDescripcion.setVisible(true);
			
			campoPostulanteDescripcion = new JTextArea();
			campoPostulanteDescripcion.setEditable(false);
			campoPostulanteDescripcion.setLineWrap(true);
			campoPostulanteDescripcion.setBounds(10, 170, 384, 80);
			postulantePostulacionesFrame.getContentPane().add(campoPostulanteDescripcion);
			campoPostulanteDescripcion.setVisible(true);
			
			// TEXTO Y CAMPO DEPARTAMENTO
			JLabel textoPostulanteDepartamento = new JLabel("Departamento:");
			textoPostulanteDepartamento.setHorizontalAlignment(SwingConstants.RIGHT);
			textoPostulanteDepartamento.setFont(new Font("Dialog", Font.BOLD, 12));
			textoPostulanteDepartamento.setBounds(10, 20, 162, 20);
			postulantePostulacionesFrame.getContentPane().add(textoPostulanteDepartamento);
			textoPostulanteDepartamento.setVisible(true);
			
			campoPostulanteDepartamento = new JTextField();
			campoPostulanteDepartamento.setFont(new Font("Arial", Font.PLAIN, 12));
			campoPostulanteDepartamento.setBounds(182, 21, 212, 20);
			postulantePostulacionesFrame.getContentPane().add(campoPostulanteDepartamento);
			campoPostulanteDepartamento.setEditable(false);
			campoPostulanteDepartamento.setColumns(10);
			campoPostulanteDepartamento.setVisible(true);
			
			// TEXTO Y CAMPO CIUDAD
			JLabel textoPostulanteCiudad = new JLabel("Ciudad:");
			textoPostulanteCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
			textoPostulanteCiudad.setFont(new Font("Dialog", Font.BOLD, 12));
			textoPostulanteCiudad.setBounds(10, 50, 162, 20);
			postulantePostulacionesFrame.getContentPane().add(textoPostulanteCiudad);
			textoPostulanteCiudad.setVisible(true);
			
			campoPostulanteCiudad = new JTextField();
			campoPostulanteCiudad.setFont(new Font("Arial", Font.PLAIN, 12));
			campoPostulanteCiudad.setBounds(182, 51, 212, 20);
			postulantePostulacionesFrame.getContentPane().add(campoPostulanteCiudad);
			campoPostulanteCiudad.setEditable(false);
			campoPostulanteCiudad.setColumns(10);
			campoPostulanteCiudad.setVisible(true);
			
			// TEXTO Y CAMPO HORARIO DE TRABAJO
			JLabel textoPostulanteHorario = new JLabel("Horario de trabajo:");
			textoPostulanteHorario.setHorizontalAlignment(SwingConstants.RIGHT);
			textoPostulanteHorario.setFont(new Font("Dialog", Font.BOLD, 12));
			textoPostulanteHorario.setBounds(10, 80, 162, 20);
			postulantePostulacionesFrame.getContentPane().add(textoPostulanteHorario);
			textoPostulanteHorario.setVisible(true);
			
			campoPostulanteHorario = new JTextField();
			campoPostulanteHorario.setFont(new Font("Arial", Font.PLAIN, 12));
			campoPostulanteHorario.setBounds(182, 81, 212, 20);
			postulantePostulacionesFrame.getContentPane().add(campoPostulanteHorario);
			campoPostulanteHorario.setEditable(false);
			campoPostulanteHorario.setColumns(10);
			campoPostulanteHorario.setVisible(true);
			
			// TEXTO Y CAMPO REMUNERACION
			JLabel textoPostulanteRemuneracion = new JLabel("Remuneracion:");
			textoPostulanteRemuneracion.setHorizontalAlignment(SwingConstants.RIGHT);
			textoPostulanteRemuneracion.setFont(new Font("Dialog", Font.BOLD, 12));
			textoPostulanteRemuneracion.setBounds(10, 110, 162, 20);
			postulantePostulacionesFrame.getContentPane().add(textoPostulanteRemuneracion);
			textoPostulanteRemuneracion.setVisible(true);
			
			campoPostulanteRemuneracion = new JTextField();
			campoPostulanteRemuneracion.setBounds(182, 110, 212, 20);
			postulantePostulacionesFrame.getContentPane().add(campoPostulanteRemuneracion);
			campoPostulanteRemuneracion.setVisible(true);
			campoPostulanteRemuneracion.setEditable(false);
			campoPostulanteRemuneracion.setColumns(10);
			
			JButton btnNewButton = new JButton("Cerrar");
			btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					postulantePostulacionesFrame.setVisible(false);
					campoPostulanteRemuneracion.setText("");
					campoPostulanteHorario.setText("");
					campoPostulanteCiudad.setText("");
					campoPostulanteDepartamento.setText("");
					campoPostulanteDescripcion.setText("");

				}
			});
			btnNewButton.setBounds(292, 510, 100, 25);
			postulantePostulacionesFrame.getContentPane().add(btnNewButton);
			
			scrollPane_2 = new JScrollPane(campoPostulanteDescripcion);
			scrollPane_2.setBounds(10, 172, 384, 78);
			postulantePostulacionesFrame.getContentPane().add(scrollPane_2);
			
			fechaField = new JTextField();
			fechaField.setFont(new Font("Arial", Font.PLAIN, 12));
			fechaField.setEditable(false);
			fechaField.setColumns(10);
			fechaField.setBounds(182, 260, 212, 22);
			postulantePostulacionesFrame.getContentPane().add(fechaField);
			
			JLabel textoEmpresaFecha_1 = new JLabel("Fecha:");
			textoEmpresaFecha_1.setHorizontalAlignment(SwingConstants.RIGHT);
			textoEmpresaFecha_1.setFont(new Font("Dialog", Font.BOLD, 12));
			textoEmpresaFecha_1.setBounds(-17, 260, 189, 22);
			postulantePostulacionesFrame.getContentPane().add(textoEmpresaFecha_1);
			
			JLabel textoEmpresaMotivacion_1 = new JLabel("Motivación:");
			textoEmpresaMotivacion_1.setFont(new Font("Dialog", Font.BOLD, 12));
			textoEmpresaMotivacion_1.setBounds(10, 292, 100, 22);
			postulantePostulacionesFrame.getContentPane().add(textoEmpresaMotivacion_1);
			
			campoEmpresaMotivacionPostulante_1 = new JTextArea();
			campoEmpresaMotivacionPostulante_1.setLineWrap(true);
			campoEmpresaMotivacionPostulante_1.setFont(new Font("Arial", Font.PLAIN, 12));
			campoEmpresaMotivacionPostulante_1.setEditable(false);
			campoEmpresaMotivacionPostulante_1.setBounds(10, 324, 387, 72);
			postulantePostulacionesFrame.getContentPane().add(campoEmpresaMotivacionPostulante_1);
			
			JLabel textoEmpresaCV_1 = new JLabel("CV:");
			textoEmpresaCV_1.setFont(new Font("Dialog", Font.BOLD, 12));
			textoEmpresaCV_1.setBounds(10, 406, 75, 22);
			postulantePostulacionesFrame.getContentPane().add(textoEmpresaCV_1);
			
			campoEmpresaCV_1 = new JTextArea();
			campoEmpresaCV_1.setLineWrap(true);
			campoEmpresaCV_1.setFont(new Font("Arial", Font.PLAIN, 12));
			campoEmpresaCV_1.setEditable(false);
			campoEmpresaCV_1.setBounds(10, 427, 387, 70);
			postulantePostulacionesFrame.getContentPane().add(campoEmpresaCV_1);
		
		//CONFIGURACION PANEL
			setAutoscrolls(true);
			setResizable(true);
	        setIconifiable(true);
	        setMaximizable(true);
	        setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
	        setClosable(false);
	        setTitle("Consulta de usuario");
	        setBounds(10, 40, 510, 662);
			getContentPane().setLayout(null);
			
			//INTERNAL FRAME PUBLICACIONES
				JInternalFrame publicacionesEmpresaFrame = new JInternalFrame("Información de la publicación.");
				publicacionesEmpresaFrame.setBounds(20, 35, 407, 553);
				getContentPane().add(publicacionesEmpresaFrame);
				publicacionesEmpresaFrame.getContentPane().setLayout(null);
				publicacionesEmpresaFrame.setVisible(false);
				
			
	
//----------------------------------------------------------------------- DATOS DEL PANEL DE PUBLICACIONES -----------------------------------------------------------


	
			//TEXTO Y CAMPO NOMBRE
			JLabel textoEmpresaNombreDeOferta = new JLabel("Nombre:");
			textoEmpresaNombreDeOferta.setHorizontalAlignment(SwingConstants.RIGHT);
			textoEmpresaNombreDeOferta.setFont(new Font("Dialog", Font.BOLD, 12));
			textoEmpresaNombreDeOferta.setBounds(10, 10, 163, 22);
			publicacionesEmpresaFrame.getContentPane().add(textoEmpresaNombreDeOferta);
			
			campoEmpresaNombreOferta = new JTextField();
			campoEmpresaNombreOferta.setBounds(180, 12, 212, 19);
			publicacionesEmpresaFrame.getContentPane().add(campoEmpresaNombreOferta);
			campoEmpresaNombreOferta.setEditable(false);
			campoEmpresaNombreOferta.setColumns(10);
			
			//TEXTO Y CAMPO DESCRIPCION
			JLabel textoEmpresaDescripcionOferta = new JLabel("Descripción:");
			textoEmpresaDescripcionOferta.setFont(new Font("Dialog", Font.BOLD, 12));
			textoEmpresaDescripcionOferta.setBounds(10, 42, 100, 22);
			publicacionesEmpresaFrame.getContentPane().add(textoEmpresaDescripcionOferta);
			
			campoEmpresaDescripcionDeOferta = new JTextArea();
			campoEmpresaDescripcionDeOferta.setLineWrap(true);
			campoEmpresaDescripcionDeOferta.setBounds(10, 74, 382, 61);
			publicacionesEmpresaFrame.getContentPane().add(campoEmpresaDescripcionDeOferta);
			campoEmpresaDescripcionDeOferta.setEditable(false);
			campoEmpresaDescripcionDeOferta.setColumns(10);
			
			//TEXTO Y CAMPO DEPARTAMENTO
			JLabel textoEmpresaDepartamento = new JLabel("Departamento:");
			textoEmpresaDepartamento.setHorizontalAlignment(SwingConstants.RIGHT);
			textoEmpresaDepartamento.setFont(new Font("Dialog", Font.BOLD, 12));
			textoEmpresaDepartamento.setBounds(10, 145, 163, 22);
			publicacionesEmpresaFrame.getContentPane().add(textoEmpresaDepartamento);
			
			campoEmpresaDepartamento = new JTextField();
			campoEmpresaDepartamento.setFont(new Font("Arial", Font.PLAIN, 12));
			campoEmpresaDepartamento.setBounds(180, 145, 212, 19);
			publicacionesEmpresaFrame.getContentPane().add(campoEmpresaDepartamento);
			campoEmpresaDepartamento.setEditable(false);
			campoEmpresaDepartamento.setColumns(10);
			
			//TEXTO Y CAMPO CIUDAD
			JLabel textoEmpresaCiudad = new JLabel("Ciudad:");
			textoEmpresaCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
			textoEmpresaCiudad.setFont(new Font("Dialog", Font.BOLD, 12));
			textoEmpresaCiudad.setBounds(10, 175, 163, 22);
			publicacionesEmpresaFrame.getContentPane().add(textoEmpresaCiudad);
			
			campoEmpresaCiudad = new JTextField();
			campoEmpresaCiudad.setFont(new Font("Arial", Font.PLAIN, 12));
			campoEmpresaCiudad.setBounds(180, 177, 212, 19);
			publicacionesEmpresaFrame.getContentPane().add(campoEmpresaCiudad);
			campoEmpresaCiudad.setEditable(false);
			campoEmpresaCiudad.setColumns(10);
			
			//TEXTO Y CAMPO HORARIO DE TRABAJO
			JLabel textoEmpresaHorarioDeTrabajo = new JLabel("Horario de trabajo:");
			textoEmpresaHorarioDeTrabajo.setHorizontalAlignment(SwingConstants.RIGHT);
			textoEmpresaHorarioDeTrabajo.setFont(new Font("Dialog", Font.BOLD, 12));
			textoEmpresaHorarioDeTrabajo.setBounds(10, 207, 163, 22);
			publicacionesEmpresaFrame.getContentPane().add(textoEmpresaHorarioDeTrabajo);
			
			campoEmpresaHorario = new JTextField();
			campoEmpresaHorario.setFont(new Font("Arial", Font.PLAIN, 12));
			campoEmpresaHorario.setBounds(180, 209, 212, 19);
			publicacionesEmpresaFrame.getContentPane().add(campoEmpresaHorario);
			campoEmpresaHorario.setEditable(false);
			campoEmpresaHorario.setColumns(10);
			
			//TEXTO Y CAMPO REMUNERACION
			JLabel textoEmpresaRemuneracion = new JLabel("Remuneración:");
			textoEmpresaRemuneracion.setHorizontalAlignment(SwingConstants.RIGHT);
			textoEmpresaRemuneracion.setFont(new Font("Dialog", Font.BOLD, 12));
			textoEmpresaRemuneracion.setBounds(10, 239, 163, 22);
			publicacionesEmpresaFrame.getContentPane().add(textoEmpresaRemuneracion);
			
			campoEmpresaRemuneracion = new JTextField();
			campoEmpresaRemuneracion.setFont(new Font("Arial", Font.PLAIN, 12));
			campoEmpresaRemuneracion.setBounds(180, 241, 212, 19);
			publicacionesEmpresaFrame.getContentPane().add(campoEmpresaRemuneracion);
			campoEmpresaRemuneracion.setEditable(false);
			campoEmpresaRemuneracion.setColumns(10);
			
			//TEXTO Y CAMPO POSTULACIONES
			JLabel textoEmpresaPostulaciones = new JLabel("Postulaciones:");
			textoEmpresaPostulaciones.setHorizontalAlignment(SwingConstants.RIGHT);
			textoEmpresaPostulaciones.setFont(new Font("Dialog", Font.BOLD, 12));
			textoEmpresaPostulaciones.setBounds(10, 335, 163, 22);
			publicacionesEmpresaFrame.getContentPane().add(textoEmpresaPostulaciones);
			textoEmpresaPostulaciones.setVisible(false);
			
			JLabel textoEmpresaPostulacionesNoHay = new JLabel("No hay postulaciones :(");
			textoEmpresaPostulacionesNoHay.setFont(new Font("Dialog", Font.BOLD, 12));
			textoEmpresaPostulacionesNoHay.setBounds(10, 335, 200, 22);
			publicacionesEmpresaFrame.getContentPane().add(textoEmpresaPostulacionesNoHay);
			textoEmpresaPostulacionesNoHay.setVisible(false);
			
			JComboBox<DtPostulacionCompleto> comboBoxEmpresaPostulacion = new JComboBox<DtPostulacionCompleto>();
			comboBoxEmpresaPostulacion.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxEmpresaPostulacion.setBounds(180, 336, 212, 21);
			publicacionesEmpresaFrame.getContentPane().add(comboBoxEmpresaPostulacion);
			comboBoxEmpresaPostulacion.setVisible(false);
			
			JButton btnNewButton_1 = new JButton("Cerrar");
			btnNewButton_1.setFont(new Font("Dialog", Font.PLAIN, 12));
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comboBoxEmpresaPostulacion.setVisible(false);
					textoEmpresaPostulacionesNoHay.setVisible(false);
					textoEmpresaPostulaciones.setVisible(false);
					campoEmpresaRemuneracion.setText("");
					campoEmpresaHorario.setText("");
					campoEmpresaCiudad.setText("");
					campoEmpresaDepartamento.setText("");
					campoEmpresaDescripcionDeOferta.setText("");
					campoEmpresaNombreOferta.setText("");
					publicacionesEmpresaFrame.setVisible(false);
				}
			});
			btnNewButton_1.setBounds(285, 484, 100, 25);
			publicacionesEmpresaFrame.getContentPane().add(btnNewButton_1);
			
			JLabel textoEmpresaTipoDeOferta = new JLabel("Tipo de oferta:");
			textoEmpresaTipoDeOferta.setHorizontalAlignment(SwingConstants.RIGHT);
			textoEmpresaTipoDeOferta.setFont(new Font("Dialog", Font.BOLD, 12));
			textoEmpresaTipoDeOferta.setBounds(10, 271, 163, 22);
			publicacionesEmpresaFrame.getContentPane().add(textoEmpresaTipoDeOferta);
			
			JLabel textoEmpresaKeywords = new JLabel("Keywords:");
			textoEmpresaKeywords.setHorizontalAlignment(SwingConstants.RIGHT);
			textoEmpresaKeywords.setFont(new Font("Dialog", Font.BOLD, 12));
			textoEmpresaKeywords.setBounds(10, 303, 163, 22);
			publicacionesEmpresaFrame.getContentPane().add(textoEmpresaKeywords);
			
			JLabel textoEmpresaKeywordsNoHay = new JLabel("No tiene keywords.");
			textoEmpresaKeywordsNoHay.setFont(new Font("Dialog", Font.BOLD, 12));
			textoEmpresaKeywordsNoHay.setBounds(10, 303, 153, 22);
			publicacionesEmpresaFrame.getContentPane().add(textoEmpresaKeywordsNoHay);
			
			campoEmpresaTipoDeOferta = new JTextField();
			campoEmpresaTipoDeOferta.setFont(new Font("Arial", Font.PLAIN, 12));
			campoEmpresaTipoDeOferta.setEditable(false);
			campoEmpresaTipoDeOferta.setColumns(10);
			campoEmpresaTipoDeOferta.setBounds(180, 270, 212, 19);
			publicacionesEmpresaFrame.getContentPane().add(campoEmpresaTipoDeOferta);
			
			JComboBox comboBoxEmpresaKeywords = new JComboBox();
			comboBoxEmpresaKeywords.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxEmpresaKeywords.setBounds(180, 304, 212, 21);
			publicacionesEmpresaFrame.getContentPane().add(comboBoxEmpresaKeywords);
			
			campoEmpresaEstado = new JTextField();
			campoEmpresaEstado.setFont(new Font("Dialog", Font.PLAIN, 12));
			campoEmpresaEstado.setEditable(false);
			campoEmpresaEstado.setColumns(10);
			campoEmpresaEstado.setBounds(180, 369, 212, 19);
			publicacionesEmpresaFrame.getContentPane().add(campoEmpresaEstado);
			
			JLabel textoEmpresaEstado = new JLabel("Estado:");
			textoEmpresaEstado.setHorizontalAlignment(SwingConstants.RIGHT);
			textoEmpresaEstado.setFont(new Font("Dialog", Font.BOLD, 12));
			textoEmpresaEstado.setBounds(10, 369, 163, 22);
			publicacionesEmpresaFrame.getContentPane().add(textoEmpresaEstado);
			
			JLabel textoEmpresaFechaAlta = new JLabel("Fecha alta:");
			textoEmpresaFechaAlta.setHorizontalAlignment(SwingConstants.RIGHT);
			textoEmpresaFechaAlta.setFont(new Font("Dialog", Font.BOLD, 12));
			textoEmpresaFechaAlta.setBounds(10, 403, 163, 22);
			publicacionesEmpresaFrame.getContentPane().add(textoEmpresaFechaAlta);
			
			JLabel textoEmpresaFechaVencimiento = new JLabel("Fecha vencimiento:");
			textoEmpresaFechaVencimiento.setHorizontalAlignment(SwingConstants.RIGHT);
			textoEmpresaFechaVencimiento.setFont(new Font("Dialog", Font.BOLD, 12));
			textoEmpresaFechaVencimiento.setBounds(10, 437, 163, 22);
			publicacionesEmpresaFrame.getContentPane().add(textoEmpresaFechaVencimiento);
			
			campoEmpresaFechaAlta = new JTextField();
			campoEmpresaFechaAlta.setFont(new Font("Dialog", Font.PLAIN, 12));
			campoEmpresaFechaAlta.setEditable(false);
			campoEmpresaFechaAlta.setColumns(10);
			campoEmpresaFechaAlta.setBounds(180, 405, 212, 19);
			publicacionesEmpresaFrame.getContentPane().add(campoEmpresaFechaAlta);
			
			campoEmpresaFechaVencimiento = new JTextField();
			campoEmpresaFechaVencimiento.setFont(new Font("Dialog", Font.PLAIN, 12));
			campoEmpresaFechaVencimiento.setEditable(false);
			campoEmpresaFechaVencimiento.setColumns(10);
			campoEmpresaFechaVencimiento.setBounds(180, 439, 212, 19);
			publicacionesEmpresaFrame.getContentPane().add(campoEmpresaFechaVencimiento);
			

//---------------------------------------------------------------------- OPERACIONES DE SELECCION ---------------------------------------------------------------------

					//SELECCIONA UNA POSTULACION SIENDO EMPRESA
					comboBoxEmpresaPostulacion.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							postulacionesEmpresaFrame.setVisible(true);
							DtPostulacionCompleto dtpc = (DtPostulacionCompleto)((JComboBox) e.getSource()).getSelectedItem();
							campoEmpresaNombrePostulante.setVisible(true);
							campoEmpresaNombrePostulante.setText(dtpc.getPostulante());
							DtPostulacion dtp = dtpc.getPostulacion();
							campoEmpresaCV.setVisible(true);
							campoEmpresaCV.setText(dtp.getCurriculum());
							campoEmpresaMotivacionPostulante.setVisible(true);
							campoEmpresaMotivacionPostulante.setText(dtp.getMotivacion());
							campoEmpresaFecha.setVisible(true);
							campoEmpresaFecha.setText(dtp.getFecha().toString());
							
						}
					});
			
			//PANEL EMPRESA
			JPanel empresa = new JPanel();
			empresa.setVisible(false);
			empresa.setBounds(10, 35, 429, 489);
			getContentPane().add(empresa);
			empresa.setLayout(null);
			empresa.setPreferredSize(new Dimension(452,684));
			
			//------------------------------------------------------ DATOS DEL PANEL DE EMPRESA -------------------------------------------------------------------------------
																																																																																							
				//TEXTO Y CAMPO NOMBRE
				JLabel textoEmpresaNombre = new JLabel("Nombre:");
				textoEmpresaNombre.setHorizontalAlignment(SwingConstants.RIGHT);
				textoEmpresaNombre.setFont(new Font("Dialog", Font.BOLD, 12));
				textoEmpresaNombre.setBounds(32, 19, 127, 22);
				empresa.add(textoEmpresaNombre);
				textoEmpresaNombre.setVisible(true);
				
				campoEmpresaNombre = new JTextField();
				campoEmpresaNombre.setFont(new Font("Arial", Font.PLAIN, 12));
				campoEmpresaNombre.setEditable(false);
				campoEmpresaNombre.setBounds(169, 21, 248, 19);
				empresa.add(campoEmpresaNombre);
				campoEmpresaNombre.setColumns(10);
				campoEmpresaNombre.setVisible(true);
				
							//TEXTO Y CAMPO NICKNAME
				JLabel textoEmpresaNickname = new JLabel("Nickname:");
				textoEmpresaNickname.setHorizontalAlignment(SwingConstants.RIGHT);
				textoEmpresaNickname.setFont(new Font("Dialog", Font.BOLD, 12));
				textoEmpresaNickname.setBounds(32, 83, 127, 22);
				empresa.add(textoEmpresaNickname);
				textoEmpresaNickname.setVisible(true);
				
				campoEmpresaNickname = new JTextField();
				campoEmpresaNickname.setFont(new Font("Arial", Font.PLAIN, 12));
				campoEmpresaNickname.setEditable(false);
				campoEmpresaNickname.setColumns(10);
				campoEmpresaNickname.setBounds(169, 85, 248, 19);
				empresa.add(campoEmpresaNickname);
				campoEmpresaNickname.setVisible(true);
				
				//TEXTO Y CAMPO	APELLIDO	
				JLabel textoEmpresaApellido = new JLabel("Apellido:");
				textoEmpresaApellido.setHorizontalAlignment(SwingConstants.RIGHT);
				textoEmpresaApellido.setFont(new Font("Dialog", Font.BOLD, 12));
				textoEmpresaApellido.setBounds(32, 51, 127, 22);
				empresa.add(textoEmpresaApellido);
				textoEmpresaApellido.setVisible(true);
				
				campoEmpresaApellido = new JTextField();
				campoEmpresaApellido.setFont(new Font("Arial", Font.PLAIN, 12));
				campoEmpresaApellido.setEditable(false);
				campoEmpresaApellido.setColumns(10);
				campoEmpresaApellido.setBounds(169, 53, 248, 19);
				empresa.add(campoEmpresaApellido);
				campoEmpresaApellido.setVisible(true);
				
							//TEXTO Y CAMPO CORREO
				JLabel textoEmpresaCorreo = new JLabel("Correo:");
				textoEmpresaCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
				textoEmpresaCorreo.setFont(new Font("Dialog", Font.BOLD, 12));
				textoEmpresaCorreo.setBounds(32, 115, 127, 22);
				empresa.add(textoEmpresaCorreo);
				textoEmpresaCorreo.setVisible(true);
				
				campoEmpresaCorreo = new JTextField();
				campoEmpresaCorreo.setFont(new Font("Arial", Font.PLAIN, 12));
				campoEmpresaCorreo.setEditable(false);
				campoEmpresaCorreo.setColumns(10);
				campoEmpresaCorreo.setBounds(169, 117, 248, 19);
				empresa.add(campoEmpresaCorreo);
				campoEmpresaCorreo.setVisible(true);
				
				//TEXTO Y CAMPO TIPO DE USUARIO
				JLabel textoEmpresaTipoDeUsuario = new JLabel("Tipo de usuario:");
				textoEmpresaTipoDeUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
				textoEmpresaTipoDeUsuario.setFont(new Font("Dialog", Font.BOLD, 12));
				textoEmpresaTipoDeUsuario.setBounds(32, 147, 127, 22);
				empresa.add(textoEmpresaTipoDeUsuario);
				textoEmpresaTipoDeUsuario.setVisible(true);
				
				campoEmpresaTipoDeUsuario = new JTextField();
				campoEmpresaTipoDeUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
				campoEmpresaTipoDeUsuario.setEditable(false);
				campoEmpresaTipoDeUsuario.setColumns(10);
				campoEmpresaTipoDeUsuario.setBounds(169, 149, 248, 19);
				empresa.add(campoEmpresaTipoDeUsuario);
				campoEmpresaTipoDeUsuario.setVisible(true);
				
				//TEXTO Y CAMPO LINK
				JLabel textoEmpresaLink = new JLabel("Link:");
				textoEmpresaLink.setHorizontalAlignment(SwingConstants.RIGHT);
				textoEmpresaLink.setFont(new Font("Dialog", Font.BOLD, 12));
				textoEmpresaLink.setBounds(32, 179, 127, 22);
				empresa.add(textoEmpresaLink);
				textoEmpresaLink.setVisible(true);
				
				campoEmpresaLink = new JTextField();
				campoEmpresaLink.setFont(new Font("Arial", Font.PLAIN, 12));
				campoEmpresaLink.setEditable(false);
				campoEmpresaLink.setColumns(10);
				campoEmpresaLink.setBounds(169, 181, 248, 19);
				empresa.add(campoEmpresaLink);
				campoEmpresaLink.setVisible(true);
				
				//TEXTO Y CAMPO DESCRIPCION
				JLabel textoEmpresaDescripcion = new JLabel("Descripción:");
				textoEmpresaDescripcion.setFont(new Font("Dialog", Font.BOLD, 12));
				textoEmpresaDescripcion.setBounds(32, 211, 107, 22);
				empresa.add(textoEmpresaDescripcion);
				textoEmpresaDescripcion.setVisible(true);
				
				campoEmpresaDescripcion = new JTextArea();
				campoEmpresaDescripcion.setWrapStyleWord(true);
				campoEmpresaDescripcion.setFont(new Font("Arial", Font.PLAIN, 12));
				campoEmpresaDescripcion.setEditable(false);
				campoEmpresaDescripcion.setLineWrap(true);
				campoEmpresaDescripcion.setBounds(new Rectangle(169, 213, 248, 100));
				campoEmpresaDescripcion.setAutoscrolls(false);
				campoEmpresaDescripcion.setBackground(new Color(255, 255, 255));
				campoEmpresaDescripcion.setColumns(1);
				campoEmpresaDescripcion.setBounds(32, 243, 349, 173);
				empresa.add(campoEmpresaDescripcion);
				campoEmpresaDescripcion.setVisible(true);
				
							//TEXTO Y CAMPO PUBLICACIONES
				JLabel textoEmpresaPublicaciones = new JLabel("Publicaciones:");
				textoEmpresaPublicaciones.setFont(new Font("Dialog", Font.BOLD, 12));
				textoEmpresaPublicaciones.setVisible(false);
				textoEmpresaPublicaciones.setBounds(32, 426, 166, 22);
				empresa.add(textoEmpresaPublicaciones);
				
				JLabel textoEmpresaPublicacionesNoHay = new JLabel("El usuario no tiene publicaciones :(");
				textoEmpresaPublicacionesNoHay.setFont(new Font("Dialog", Font.BOLD, 12));
				textoEmpresaPublicacionesNoHay.setVisible(false);
				textoEmpresaPublicacionesNoHay.setBounds(32, 426, 282, 22);
				empresa.add(textoEmpresaPublicacionesNoHay);
				
				JComboBox<String> comboBoxEmpresaPublicaciones = new JComboBox<String>();
				comboBoxEmpresaPublicaciones.setVisible(false);
				comboBoxEmpresaPublicaciones.setBounds(32, 458, 251, 21);
				empresa.add(comboBoxEmpresaPublicaciones);
				
				scrollPane_3 = new JScrollPane(campoEmpresaDescripcion);
				scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				scrollPane_3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollPane_3.setBounds(32, 243, 385, 173);
				empresa.add(scrollPane_3);
				
				//PANEL DE POSTULANTE
				JPanel postulante = new JPanel();
				postulante.setBounds(10, 35, 407, 283);
				getContentPane().add(postulante);
				postulante.setLayout(null);
				postulante.setVisible(false);
				
				
				
				
//---------------------------------------------- DATOS DEL PANEL DE POSTULANTE ----------------------------------------------------------------------------

				// TEXTO Y CAMPO NOMBRE
				JLabel textoPostulanteNombre = new JLabel("Nombre:");
				textoPostulanteNombre.setFont(new Font("Dialog", Font.BOLD, 12));
				textoPostulanteNombre.setBounds(32, 23, 127, 22);
				postulante.add(textoPostulanteNombre);
				
				campoPostulanteNombre = new JTextField();
				campoPostulanteNombre.setFont(new Font("Arial", Font.PLAIN, 12));
				campoPostulanteNombre.setEditable(false);
				campoPostulanteNombre.setColumns(10);
				campoPostulanteNombre.setBounds(169, 57, 212, 19);
				postulante.add(campoPostulanteNombre);
				
				// TEXTO Y CAMPO NICKNAME
				JLabel textoPostulanteNickname = new JLabel("Nickname:");
				textoPostulanteNickname.setFont(new Font("Dialog", Font.BOLD, 12));
				textoPostulanteNickname.setBounds(32, 87, 127, 22);
				postulante.add(textoPostulanteNickname);
				
				campoPostulanteNickname = new JTextField();
				campoPostulanteNickname.setFont(new Font("Tahoma", Font.PLAIN, 12));
				campoPostulanteNickname.setEditable(false);
				campoPostulanteNickname.setBounds(169, 25, 212, 19);
				postulante.add(campoPostulanteNickname);
				campoPostulanteNickname.setColumns(10);
				
				// TEXTO Y CAMPO APELLIDO
				JLabel textoPostulanteApellido = new JLabel("Apellido:");
				textoPostulanteApellido.setFont(new Font("Dialog", Font.BOLD, 12));
				textoPostulanteApellido.setBounds(32, 55, 127, 22);
				postulante.add(textoPostulanteApellido);
				
				campoPostulanteApellido = new JTextField();
				campoPostulanteApellido.setFont(new Font("Arial", Font.PLAIN, 12));
				campoPostulanteApellido.setEditable(false);
				campoPostulanteApellido.setColumns(10);
				campoPostulanteApellido.setBounds(169, 89, 212, 19);
				postulante.add(campoPostulanteApellido);
				
				// TEXTO Y CAMPO CORREO
				JLabel textoPostulanteCorreo = new JLabel("Correo:");
				textoPostulanteCorreo.setFont(new Font("Dialog", Font.BOLD, 12));
				textoPostulanteCorreo.setBounds(32, 119, 127, 22);
				postulante.add(textoPostulanteCorreo);
				
				campoPostulanteCorreo = new JTextField();
				campoPostulanteCorreo.setFont(new Font("Arial", Font.PLAIN, 12));
				campoPostulanteCorreo.setEditable(false);
				campoPostulanteCorreo.setColumns(10);
				campoPostulanteCorreo.setBounds(169, 121, 212, 19);
				postulante.add(campoPostulanteCorreo);
				
				// TEXTO Y CAMPO TIPO DE USUARIO
				JLabel textoPostulanteTipoDeUsuario = new JLabel("Tipo de usuario:");
				textoPostulanteTipoDeUsuario.setFont(new Font("Dialog", Font.BOLD, 12));
				textoPostulanteTipoDeUsuario.setBounds(32, 151, 127, 22);
				postulante.add(textoPostulanteTipoDeUsuario);
				
				campoPostulanteTipoDeUsuario = new JTextField();
				campoPostulanteTipoDeUsuario.setFont(new Font("Arial", Font.PLAIN, 12));
				campoPostulanteTipoDeUsuario.setBounds(169, 153, 212, 19);
				postulante.add(campoPostulanteTipoDeUsuario);
				campoPostulanteTipoDeUsuario.setEditable(false);
				campoPostulanteTipoDeUsuario.setColumns(10);
				
				// TEXTO Y CAMPO NACIMIENTO
				JLabel textoPostulanteNacimiento = new JLabel("Nacimiento:");
				textoPostulanteNacimiento.setFont(new Font("Dialog", Font.BOLD, 12));
				textoPostulanteNacimiento.setBounds(32, 183, 127, 22);
				postulante.add(textoPostulanteNacimiento);
				
				campoPostulanteNacimiento = new JTextField();
				campoPostulanteNacimiento.setFont(new Font("Arial", Font.PLAIN, 12));
				campoPostulanteNacimiento.setBounds(169, 185, 212, 19);
				postulante.add(campoPostulanteNacimiento);
				campoPostulanteNacimiento.setEditable(false);
				campoPostulanteNacimiento.setColumns(10);
				
				// TEXTO Y CAMPO NACIONALIDAD
				JLabel textoPostulanteNacionalidad = new JLabel("Nacionalidad:");
				textoPostulanteNacionalidad.setFont(new Font("Dialog", Font.BOLD, 12));
				textoPostulanteNacionalidad.setBounds(32, 215, 127, 22);
				postulante.add(textoPostulanteNacionalidad);
				
				campoPostulanteNacionalidad = new JTextField();
				campoPostulanteNacionalidad.setFont(new Font("Arial", Font.PLAIN, 12));
				campoPostulanteNacionalidad.setBounds(169, 217, 212, 19);
				postulante.add(campoPostulanteNacionalidad);
				campoPostulanteNacionalidad.setEditable(false);
				campoPostulanteNacionalidad.setColumns(10);
				
				// TEXTO Y CAMPO POSTULACIONES
				JLabel textoPostulantePostulaciones = new JLabel("Postulaciones:");
				textoPostulantePostulaciones.setFont(new Font("Dialog", Font.BOLD, 12));
				textoPostulantePostulaciones.setVisible(false);
				textoPostulantePostulaciones.setBounds(32, 247, 127, 22);
				postulante.add(textoPostulantePostulaciones);
				
				JLabel textoPostulantePostulacionesNoHay = new JLabel("El usuario no tiene postulaciones :(");
				textoPostulantePostulacionesNoHay.setFont(new Font("Dialog", Font.BOLD, 12));
				textoPostulantePostulacionesNoHay.setVisible(false);
				textoPostulantePostulacionesNoHay.setBounds(32, 247, 227, 22);
				postulante.add(textoPostulantePostulacionesNoHay);
				
				JComboBox<String> comboBoxPostulantesPostulaciones = new JComboBox<String>();
				comboBoxPostulantesPostulaciones.setFont(new Font("Arial", Font.PLAIN, 12));
				comboBoxPostulantesPostulaciones.setVisible(false);
				comboBoxPostulantesPostulaciones.setBounds(169, 248, 212, 21);
				postulante.add(comboBoxPostulantesPostulaciones);
				
				//TEXTO USUARIOS REGISTRADOS
				JLabel lblNewLabel = new JLabel("Usuarios registrados:");
				lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
				lblNewLabel.setBounds(10, 12, 176, 13);
				getContentPane().add(lblNewLabel);
				
		
				
				//CERRAR FRAME PRINCIPAL
				JButton cerrarPrincipal = new JButton("Cerrar");
				cerrarPrincipal.setFont(new Font("Dialog", Font.PLAIN, 12));
				getContentPane().add(cerrarPrincipal);
				cerrarPrincipal.setBounds(317, 584, 100, 25);
				
		
		
		//LISTA DE USUARIOS
		comboBoxUsuarios = new JComboBox<String>();
		comboBoxUsuarios.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxUsuarios.setBounds(169, 4, 248, 21);
		getContentPane().add(comboBoxUsuarios);
		
		
		//SELECCIONAR UN USUARIO
		comboBoxUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nick = (String) ((JComboBox)e.getSource()).getSelectedItem();
				if (nick != "-" ) {
					postulantePostulacionesFrame.setVisible(false);
					publicacionesEmpresaFrame.setVisible(false);
					textoPostulantePostulacionesNoHay.setVisible(false);
					textoEmpresaPublicacionesNoHay.setVisible(false);
					comboBoxPostulantesPostulaciones.setVisible(false);
					textoPostulantePostulaciones.setVisible(false);
					textoEmpresaPublicaciones.setVisible(false);
					comboBoxEmpresaPublicaciones.setVisible(false);
	
					empresa.setVisible(false);
					postulante.setVisible(false);
					
					if(!ctrlUsuario.esEmpresa(nick)) {
						postulante.setVisible(true);
						//Obtiene el DtPostulante
						DtPostulanteCompleto dtpc = ctrlUsuario.mostrarPostulante(nick);
						DtPostulante dtp = dtpc.getPostulante();
						nickGuardado = dtp.getNickname();
						//llena los campos basicos
						campoPostulanteNickname.setText(dtp.getNickname());
						campoPostulanteNombre.setText(dtp.getNombre());
						campoPostulanteApellido.setText(dtp.getApellido());
						campoPostulanteCorreo.setText(dtp.getCorreoElectronico());
						campoPostulanteTipoDeUsuario.setText("Postulante");
						campoPostulanteNacimiento.setText(dtp.getNacimiento());
						campoPostulanteNacionalidad.setText(dtp.getNacionalidad());
						String[] ofs = dtpc.getOfertas();
						if(ofs.length != 0){
							DefaultComboBoxModel<String> model;
							model = new DefaultComboBoxModel<String>(ofs);
							comboBoxPostulantesPostulaciones.setModel(model);
							comboBoxPostulantesPostulaciones.setVisible(true);
							textoPostulantePostulaciones.setVisible(true);
						}else {
							textoPostulantePostulacionesNoHay.setVisible(true);
						}
					}else {
						empresa.setVisible(true);
						//obtengo DtEmpresa
						DtEmpresaCompleto dtec = ctrlUsuario.mostrarEmpresa(nick);
						DtEmpresa dte = dtec.getEmpresa();
						//lleno campos
						campoEmpresaNombre.setText(dte.getNombre());
						campoEmpresaNickname.setText(dte.getNickname());
						campoEmpresaApellido.setText(dte.getApellido());
						campoEmpresaCorreo.setText(dte.getCorreoElectronico());
						campoEmpresaDescripcion.setText(dte.getDescripcion());
						campoEmpresaLink.setText(dte.getLink());
						campoEmpresaTipoDeUsuario.setText("Empresa");
						//obtiene ofertas
						String[] ofs = dtec.getOfertas();
						if(ofs.length != 0) {
							comboBoxEmpresaPublicaciones.setVisible(true);
							DefaultComboBoxModel<String> model;
							model = new DefaultComboBoxModel<String>(ofs);
							comboBoxEmpresaPublicaciones.setModel(model);
							textoEmpresaPublicaciones.setVisible(true);
						}else {
							textoEmpresaPublicacionesNoHay.setVisible(true);
						}
					}
			}	
			}	
		});
				cerrarPrincipal.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						campoPostulanteNombre.setText("");
						campoEmpresaNombre.setText("");
						campoPostulanteNickname.setText("");
						campoEmpresaNickname.setText("");
						campoEmpresaApellido.setText("");
						campoPostulanteApellido.setText("");
						campoEmpresaCorreo.setText("");
						campoPostulanteCorreo.setText("");
						campoPostulanteNacimiento.setText("");
						campoPostulanteNacionalidad.setText("");
						campoEmpresaLink.setText("");
						campoEmpresaDescripcion.setText("");
						textoPostulantePostulaciones.setVisible(false);
						textoPostulantePostulacionesNoHay.setVisible(false);
						comboBoxPostulantesPostulaciones.setVisible(false);
						comboBoxEmpresaPublicaciones.setVisible(false);
						textoEmpresaPublicaciones.setVisible(false);
						textoEmpresaPublicacionesNoHay.setVisible(false);
						empresa.setVisible(false);
						postulante.setVisible(false);
						postulantePostulacionesFrame.setVisible(false);
						postulacionesEmpresaFrame.setVisible(false);
						publicacionesEmpresaFrame.setVisible(false);}
				});
				

				
				//SELECCIONA UNA POSTULACION SIENDO POSTULANTE		
				comboBoxPostulantesPostulaciones.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String of = (String) ((JComboBox) e.getSource()).getSelectedItem();
						postulantePostulacionesFrame.setVisible(true);
						//obtengo DtOferta
						DtOfertaCompleto dtoc = ctrlLaboral.mostrarOfertaCompletoPostulante(of,nickGuardado);
						DtOferta dto = dtoc.getOferta();
						DtPostulacionCompleto[] dtps = dtoc.getPostulaciones();
						DtPostulacion dtp = dtps[0].getPostulacion();
						//lleno campos
						campoPostulanteDepartamento.setText(dto.getDepartamento());
						campoPostulanteCiudad.setText(dto.getCiudad());
						campoPostulanteDescripcion.setText(dto.getDescripcion());
						campoPostulanteHorario.setText(dto.getHorarioTrabajo());
						campoPostulanteRemuneracion.setText(dto.getRemuneracion().toString());
						campoEmpresaMotivacionPostulante_1.setText(dtp.getMotivacion());
						campoEmpresaCV_1.setText(dtp.getCurriculum());
						fechaField.setText(dtp.getFecha());
					}
				});
				//SELECCIONAR UNA PUBLICACION SIENDO UNA EMPRESA
				comboBoxEmpresaPublicaciones.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						postulacionesEmpresaFrame.setVisible(false);
						publicacionesEmpresaFrame.setVisible(true);
						textoEmpresaPostulacionesNoHay.setVisible(false);
						comboBoxEmpresaPostulacion.setVisible(false);
						textoEmpresaPostulaciones.setVisible(false);
						String of = (String)((JComboBox) e.getSource()).getSelectedItem();
						//Obtengo DtOferta
				DtOfertaCompleto dtoc = ctrlLaboral.mostrarOfertaCompleto(of);
				DtOferta dto = dtoc.getOferta();
				//Lleno campos
						campoEmpresaNombreOferta.setText(dto.getNombre());
						campoEmpresaDescripcionDeOferta.setText(dto.getDescripcion());
						campoEmpresaDepartamento.setText(dto.getDepartamento());
						campoEmpresaCiudad.setText(dto.getCiudad());
						campoEmpresaHorario.setText(dto.getHorarioTrabajo());
						campoEmpresaRemuneracion.setText(dto.getRemuneracion().toString());
						campoEmpresaTipoDeOferta.setText(dto.getTipoDeOferta());
						campoEmpresaFechaAlta.setText(dto.getFechaAlta());
						if (dto.getEstado().equals(EstadoOferta.Rechazada))
							campoEmpresaFechaVencimiento.setText("Rechazada");
						else if (dto.getEstado().equals(EstadoOferta.Ingresada))
							campoEmpresaFechaVencimiento.setText("Pendiente de aprobacion");
						else
							campoEmpresaFechaVencimiento.setText(dto.getVencimiento());
						campoEmpresaEstado.setText(dto.getEstado().toString());
						String[] keys = dto.getKeywords();
						if (keys.length == 0) {
							comboBoxEmpresaKeywords.setVisible(false);
							textoEmpresaKeywords.setVisible(false);
							textoEmpresaKeywordsNoHay.setVisible(true);
						}else {
							comboBoxEmpresaKeywords.setVisible(true);
							textoEmpresaKeywords.setVisible(true);
							textoEmpresaKeywordsNoHay.setVisible(false);
							DefaultComboBoxModel<String> model;
							model = new DefaultComboBoxModel<String>(keys);
							comboBoxEmpresaKeywords.setModel(model);
						}
						
						DtPostulacionCompleto[] tocs = dtoc.getPostulaciones();
						if(tocs.length != 0) {
							DefaultComboBoxModel<DtPostulacionCompleto> model;
							model = new DefaultComboBoxModel<DtPostulacionCompleto>(tocs);
							comboBoxEmpresaPostulacion.setModel(model);
							comboBoxEmpresaPostulacion.setVisible(true);
							textoEmpresaPostulaciones.setVisible(true);
						} else {
							textoEmpresaPostulacionesNoHay.setVisible(true);
						}
					}
				});
	} //FIN DE CREADOR

//------------------------------------------------------------------------------------------------------------
	
	//FUNCION DE CARGA DE USUARIOS
	public void cargarUsuarios()throws NonExistentException{
		String[] aux = {"-"};
		String[] x = ctrlUsuario.listarUsuarios();
		String[] usuarios = new String[aux.length + x.length];
		System.arraycopy(aux, 0, usuarios, 0, aux.length);
        System.arraycopy(x, 0, usuarios, aux.length, x.length);
		if(usuarios.length == 1) // Si pasa esto es porque no habian usuarios en el sistema
			throw new NonExistentException("No hay usuarios en el sistema.");
        DefaultComboBoxModel<String> model;
        model = new DefaultComboBoxModel<String>(usuarios);
        comboBoxUsuarios.setModel(model);
      
    }
}

