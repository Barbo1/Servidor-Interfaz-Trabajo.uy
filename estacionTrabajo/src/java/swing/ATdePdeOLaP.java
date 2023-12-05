package swing;

import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;

import model.exceptions.ExistentException;
import model.exceptions.NonExistentException;
import model.interfacess.IcontroladorLaboral;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ATdePdeOLaP extends JInternalFrame{
	private IcontroladorLaboral ctrlLaboral;
	private JComboBox<String> comboBoxPaquetes;
	private JComboBox<String> comboBoxTiposDeOfertas;
	private String paquete;
	private String tipoDeOferta;
	
	//CREADOR
	public ATdePdeOLaP(IcontroladorLaboral cl) {
		this.ctrlLaboral = cl;
		setAutoscrolls(true);
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Agregar tipo de publicación de oferta laboral a paquete.");
        setBounds(10, 40, 464, 306);
		getContentPane().setLayout(null);
		paquete = null;
		tipoDeOferta = null;
		
	
		//PAQUETES
			JLabel lblNewLabel = new JLabel("Paquete:");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
			lblNewLabel.setBounds(10, 30, 100, 33);
			getContentPane().add(lblNewLabel);
			
			comboBoxPaquetes = new JComboBox<String>();
			comboBoxPaquetes.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxPaquetes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					paquete = (String)((JComboBox) e.getSource()).getSelectedItem();
					comboBoxTiposDeOfertas.removeAll();
					cargarTiposDeOferta(paquete);
				}
			});
			comboBoxPaquetes.setBounds(120, 30, 308, 33);
			getContentPane().add(comboBoxPaquetes);
		
		//TIPO DE OFERTA
			JLabel lblTipoDeOferta = new JLabel("Tipo de oferta:");
			lblTipoDeOferta.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTipoDeOferta.setFont(new Font("Dialog", Font.BOLD, 12));
			lblTipoDeOferta.setBounds(10, 100, 100, 33);
			getContentPane().add(lblTipoDeOferta);
			
			comboBoxTiposDeOfertas = new JComboBox<String>();
			comboBoxTiposDeOfertas.setFont(new Font("Arial", Font.PLAIN, 12));
			comboBoxTiposDeOfertas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tipoDeOferta = (String)((JComboBox) e.getSource()).getSelectedItem();
				}
			});
			comboBoxTiposDeOfertas.setBounds(120, 100, 308, 33);
			getContentPane().add(comboBoxTiposDeOfertas);
		
		//CANTIDAD
			JLabel lblCantidad = new JLabel("Cantidad:");
			lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCantidad.setFont(new Font("Dialog", Font.BOLD, 12));
			lblCantidad.setBounds(10, 170, 100, 33);
			getContentPane().add(lblCantidad);
			
			JSpinner campoCantidad = new JSpinner();
			campoCantidad.setFont(new Font("Arial", Font.PLAIN, 12));
			campoCantidad.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
			campoCantidad.setBounds(120, 177, 49, 20);
			getContentPane().add(campoCantidad);
		
		//BOTON DE ACEPTAR
			JButton btnAceptar = new JButton("Aceptar");
			btnAceptar.setFont(new Font("Dialog", Font.PLAIN, 12));
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(paquete == null || tipoDeOferta == null) {
						JOptionPane.showMessageDialog(null,"No selecciono un paquete o tipo de oferta","Error",JOptionPane.ERROR_MESSAGE);
					}else {
						try {
							ctrlLaboral.agregarTipoDePublicacionAPaquete(paquete,tipoDeOferta,(int) campoCantidad.getValue());
							JOptionPane.showMessageDialog(null,"Creado con exito.","Paquete",JOptionPane.INFORMATION_MESSAGE);
							campoCantidad.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
						} catch (ExistentException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
			btnAceptar.setBounds(143, 228, 100, 25);
			getContentPane().add(btnAceptar);
		
		//BOTON CANCELAR
			JButton btnNewButton = new JButton("Cancelar");
			btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 12));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					campoCantidad.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
				}
			});
		
			btnNewButton.setBounds(280, 228, 100, 25);
			getContentPane().add(btnNewButton);
	
	} //FIN CREADOR
	
	public void cargarPaquetes()throws NonExistentException {
		String[] x = ctrlLaboral.listarPaquetesNoComprados();
		if (x.length == 0) {
			throw new NonExistentException("No hay Paquetes en el sistema");
		}
		DefaultComboBoxModel<String> model;
        model = new DefaultComboBoxModel<String>(x);
        comboBoxPaquetes.setModel(model);
        paquete = null;
	}
	
	public void cargarTiposDeOferta(String paquete)throws NonExistentException {
		String[] x = ctrlLaboral.listarTipoDeOfertaFaltante(paquete);
		if (x == null) {
			throw new NonExistentException("Este paquete tiene todos los tipos de oferta.");
		}
		DefaultComboBoxModel<String> model;
        model = new DefaultComboBoxModel<String>(x);
        comboBoxTiposDeOfertas.setModel(model);
        tipoDeOferta = null;
	}
}
