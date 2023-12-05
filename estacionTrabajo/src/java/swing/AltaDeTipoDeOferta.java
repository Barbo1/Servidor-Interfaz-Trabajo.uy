
package swing;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import javax.swing.JSpinner;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.interfacess.IcontroladorLaboral;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.TitledBorder;

import model.exceptions.DuplicationException;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;
import com.toedter.calendar.JDateChooser;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class AltaDeTipoDeOferta extends JInternalFrame {
	private static volatile Integer puntoIngresado;
	private JScrollPane scrollPane;

	public AltaDeTipoDeOferta(IcontroladorLaboral crtlLaboral) {
		setTitle("Alta de tipo de oferta");
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 342, 377);
		getContentPane().setLayout(null);
		
		JTextArea txtNom = new JTextArea();
		txtNom.setWrapStyleWord(true);
		txtNom.setLineWrap(true);
		txtNom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (Integer.valueOf(e.getKeyChar()) == KeyEvent.VK_ENTER) {
					e.consume();
				}
			}
		});
		txtNom.setBounds(137, 22, 171, 21);
		getContentPane().add(txtNom);
		
		JSpinner spnExp = new JSpinner();
		spnExp.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spnExp.setBounds(166, 169, 142, 20);
		getContentPane().add(spnExp);
		
		JSpinner spnDur = new JSpinner();
		spnDur.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spnDur.setBounds(166, 197, 142, 20);
		getContentPane().add(spnDur);
		
		JTextArea txtDes = new JTextArea();
		txtDes.setLineWrap(true);
		txtDes.setWrapStyleWord(true);
		txtDes.setBounds(41, 76, 267, 81);
		getContentPane().add(txtDes);
		
		LocalDate t = LocalDate.now();
		JDateChooser txtFecha = new JDateChooser();
		txtFecha.setDate(Date.from(t.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		txtFecha.setBounds(166, 250, 142, 19);

		getContentPane().add(txtFecha);
		
		TextField txtCos = new TextField();
		puntoIngresado = 0;
		txtCos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (Integer.valueOf(e.getKeyChar()) == 8) { // KeyEvent.VK_BACK_SPACE == 8
					if (txtCos.getCaretPosition() + 1 == puntoIngresado) {
						puntoIngresado = 0;					
					}
				} else if (e.getKeyChar() == '.' && puntoIngresado == 0) {
					puntoIngresado = txtCos.getText().length()+1;
				} else if (!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		txtCos.setBounds(166, 223, 142, 21);
		getContentPane().add(txtCos);
		
		JButton btnAlta = new JButton("Aceptar");
		btnAlta.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNom.getText().strip() == ""  || txtCos.getText().strip() == "" || txtDes.getText().strip() == "" || txtFecha.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Existen campos sin rellenar", "Sistema", 1);
				} else {
					try {
						Date date = txtFecha.getDate();
						LocalDate fechaAlta = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						crtlLaboral.altaTipoOferta(
								txtNom.getText(), 
								txtDes.getText(), 
								(int)spnDur.getValue(), 
								(int)spnExp.getValue(), 
								Float.valueOf(txtCos.getText()),
						    	fechaAlta	
							);	
						txtNom.setText("");
						txtDes.setText("");
						spnDur.setValue(1);
						spnExp.setValue(1);
						txtCos.setText("");
						puntoIngresado = 0;
						JOptionPane.showMessageDialog(null, "Tipo de oferta registrado", "Sistema", 1);
					} catch (DuplicationException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Sistema", 1);
					}
				}
			}
		});
		btnAlta.setBounds(46, 296, 104, 31);
		getContentPane().add(btnAlta);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNom.setText("");
				txtDes.setText("");
				spnDur.setValue(1);
				spnExp.setValue(1);
				txtCos.setText("");
				txtFecha.setDate(Date.from(t.atStartOfDay(ZoneId.systemDefault()).toInstant()));
				setVisible(false);
			}
		});
		btnCancelar.setBounds(180, 296, 104, 31);
		getContentPane().add(btnCancelar);
		
		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(15, 22, 109, 15);
		getContentPane().add(lblNombre);
		
		JLabel lblDescripcin = new JLabel("Descripción :");
		lblDescripcin.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDescripcin.setBounds(22, 55, 128, 15);
		getContentPane().add(lblDescripcin);
		
		JLabel lblExposicion = new JLabel("Exposición :");
		lblExposicion.setFont(new Font("Dialog", Font.BOLD, 12));
		lblExposicion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblExposicion.setBounds(28, 171, 96, 15);
		getContentPane().add(lblExposicion);
		
		JLabel lblDuracin = new JLabel("Duración :");
		lblDuracin.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDuracin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDuracin.setBounds(20, 199, 104, 15);
		getContentPane().add(lblDuracin);
		
		JLabel lblCosto = new JLabel("Costo :");
		lblCosto.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCosto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCosto.setBounds(54, 229, 70, 15);
		getContentPane().add(lblCosto);
		
		JLabel lblFecha = new JLabel("Fecha :");
		lblFecha.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setBounds(54, 254, 70, 15);
		getContentPane().add(lblFecha);
		
		scrollPane = new JScrollPane(txtDes);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(41, 76, 267, 81);
		getContentPane().add(scrollPane);
	}
}
