package swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

import model.exceptions.DuplicationException;
import model.interfacess.IcontroladorLaboral;
import javax.swing.JFrame;
import com.toedter.calendar.JDateChooser;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;

public class CrearPaquete extends JInternalFrame {
	public CrearPaquete(IcontroladorLaboral crtlLaboral) {
		setTitle("Crear Paquete");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 360, 346);
		getContentPane().setLayout(null);
		
		JTextArea txtNom = new JTextArea();
		txtNom.setBounds(149, 22, 177, 21);
		getContentPane().add(txtNom);
		
		Label lblNom = new Label("Nombre :");
		lblNom.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNom.setAlignment(Label.RIGHT);
		lblNom.setBounds(22, 22, 121, 21);
		getContentPane().add(lblNom);
		
		Label lblDes = new Label("Descripcion :");
		lblDes.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDes.setBounds(22, 49, 100, 21);
		getContentPane().add(lblDes);
		
		Label lblPer = new Label("Periodo de validez :");
		lblPer.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPer.setAlignment(Label.RIGHT);
		lblPer.setBounds(0, 169, 178, 21);
		getContentPane().add(lblPer);
		
		Label lblDesc = new Label("Descuento :");
		lblDesc.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDesc.setAlignment(Label.RIGHT);
		lblDesc.setBounds(22, 196, 156, 21);
		getContentPane().add(lblDesc);
		
		JSpinner spnPer = new JSpinner();
		spnPer.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spnPer.setBounds(184, 169, 142, 20);
		getContentPane().add(spnPer);
		
		JSpinner spnDesc = new JSpinner();
		spnDesc.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spnDesc.setBounds(184, 197, 142, 20);
		getContentPane().add(spnDesc);
		
		JTextArea txtDes = new JTextArea();
		txtDes.setWrapStyleWord(true);
		txtDes.setLineWrap(true);
		txtDes.setBounds(59, 76, 267, 81);
		getContentPane().add(txtDes);
		
		LocalDate fecha = LocalDate.now();
		JDateChooser txtFecha = new JDateChooser();
		txtFecha.setDate(Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		txtFecha.setBounds(184, 225, 142, 19);
		getContentPane().add(txtFecha);
		
		JButton btnAlta = new JButton("Aceptar");
		btnAlta.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNom.getText().isEmpty() || txtDes.getText().isEmpty() || txtFecha.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Existen campos sin rellenar", "Sistema", 1);
				} else {
					try {
                    
                        Date aux = txtFecha.getDate();
            			LocalDate date = aux.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						crtlLaboral.altaPaquete(
								txtNom.getText(), 
								txtDes.getText(), 
								(int)spnPer.getValue(), 
								Float.valueOf((int)spnDesc.getValue())/100,
								date,
                                "default.png"
							);	
						txtNom.setText("");
						txtDes.setText("");
						spnPer.setValue(1);
						spnDesc.setValue(0);
						JOptionPane.showMessageDialog(null, "Tipo de oferta registrado", "Sistema", 1);
					} catch (DuplicationException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Sistema", 1);
					}
				}
			}
		});
		btnAlta.setBounds(51, 266, 112, 31);
		getContentPane().add(btnAlta);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNom.setText("");
				txtDes.setText("");
				spnPer.setValue(1);
				spnDesc.setValue(0);
				txtFecha.setDate(Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant()));
				setVisible(false);
			}
		});
		btnCancelar.setBounds(190, 267, 121, 31);
		getContentPane().add(btnCancelar);
		
		Label lblDesc_1 = new Label("fecha :");
		lblDesc_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDesc_1.setAlignment(Label.RIGHT);
		lblDesc_1.setBounds(22, 223, 156, 21);
		getContentPane().add(lblDesc_1);
		
		JScrollPane scrollPane = new JScrollPane(txtDes);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(59, 76, 267, 81);
		getContentPane().add(scrollPane);
	}
}
