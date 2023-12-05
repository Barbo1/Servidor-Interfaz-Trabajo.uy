package swing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import model.datatype.DtOferta;
import model.datatype.EstadoOferta;
import model.exceptions.NonExistentException;
import model.interfacess.IcontroladorLaboral;
import model.interfacess.IcontroladorUsuario;

public class EstadoOfertaLaboral extends JInternalFrame {
    private IcontroladorLaboral controladorLaboral; 
    private IcontroladorUsuario controladorUsuario;
    private JPanel pnlEmpresas;
    private JButton[] botonesEmpresas = new JButton[0];
    private JButton[] botonesOfertas = new JButton[0];
    private int buttonHeight = 25;
    private JPanel pnlOfertas;
    private JInternalFrame ofertaFrame;
    private JTextArea txtNombre;
    private JTextArea txtTipo;
    private JTextArea txtEmpresa;
    private Object[] options = {"Si", "No"};
    private String empresaSeleccionada = "";

	/**
	 * Create the frame.
	 */
	public EstadoOfertaLaboral(IcontroladorLaboral ctrlLabo, IcontroladorUsuario ctrlUsu) {

        controladorLaboral = ctrlLabo; 
        controladorUsuario = ctrlUsu;

		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 482, 383);
		getContentPane().setLayout(null);
		
		ofertaFrame = new JInternalFrame("Oferta");
		ofertaFrame.setClosable(true);
		ofertaFrame.setBounds(55, 0, 346, 267);
		getContentPane().add(ofertaFrame);
		ofertaFrame.getContentPane().setLayout(null);
		
		txtTipo = new JTextArea();
		txtTipo.setEditable(false);
		txtTipo.setBounds(129, 114, 195, 39);
		ofertaFrame.getContentPane().add(txtTipo);
		
		JLabel lblTipoDeOferta = new JLabel("Tipo de oferta:");
		lblTipoDeOferta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoDeOferta.setBounds(12, 124, 118, 21);
		ofertaFrame.getContentPane().add(lblTipoDeOferta);
		
		txtNombre = new JTextArea();
		txtNombre.setEditable(false);
		txtNombre.setBounds(129, 12, 195, 39);
		ofertaFrame.getContentPane().add(txtNombre);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(12, 22, 118, 21);
		ofertaFrame.getContentPane().add(lblNombre);
		
		txtEmpresa = new JTextArea();
		txtEmpresa.setEditable(false);
		txtEmpresa.setBounds(129, 63, 195, 39);
		ofertaFrame.getContentPane().add(txtEmpresa);
		
		JLabel lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmpresa.setBounds(12, 73, 118, 21);
		ofertaFrame.getContentPane().add(lblEmpresa);

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (JOptionPane.showOptionDialog(
                		ofertaFrame,
                        "Seguro que desea CONFIRMAR la oferta",
                        "Validacion",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options, 
                        options[0]
                    ) == 0) {
                        controladorLaboral.cambiarEstadoOferta(txtNombre.getText(), EstadoOferta.Confirmada);
                        cargarOfertas(empresaSeleccionada);
                        ofertaFrame.setVisible(false);
                    }
			}
		});
		btnConfirmar.setBounds(42, 177, 117, 39);
		ofertaFrame.getContentPane().add(btnConfirmar);
		
		JButton btnRechazar = new JButton("Rechazar");
		btnRechazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                if (JOptionPane.showOptionDialog(
                		ofertaFrame,
                        "Seguro que desea RECHAZAR la oferta",
                        "Validacion",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options, 
                        options[0]
                    ) == 0) {
                        controladorLaboral.cambiarEstadoOferta(txtNombre.getText(), EstadoOferta.Rechazada);
                        cargarOfertas(empresaSeleccionada);
                        ofertaFrame.setVisible(false);
                    }
			}
		});
		btnRechazar.setBounds(182, 177, 117, 39);
		ofertaFrame.getContentPane().add(btnRechazar);
		ofertaFrame.setVisible(true);
		
		pnlEmpresas = new JPanel();
		pnlEmpresas.setBorder(new TitledBorder(null, "Empresas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlEmpresas.setBounds(12, 12, 309, 327);
		
		JScrollPane scpEmpresas = new JScrollPane(pnlEmpresas);
		pnlEmpresas.setLayout(null);
		scpEmpresas.setBounds(12, 12, 218, 327);
		scpEmpresas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scpEmpresas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scpEmpresas);
		
		pnlOfertas = new JPanel();
		pnlOfertas.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Ofertas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		pnlOfertas.setBounds(241, 12, 217, 324);
		pnlOfertas.setLayout(null);
		
		JScrollPane scrOfertas = new JScrollPane(pnlOfertas);
		scrOfertas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrOfertas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrOfertas.setBounds(242, 12, 217, 327);
		getContentPane().add(scrOfertas);
	}
	
	public void cargarEmpresas() throws NonExistentException {
    	ofertaFrame.setVisible(false);

		String[] empresas = controladorUsuario.listarEmpresas();
		if (empresas.length == 0) {
			throw new NonExistentException("No hay empresas en el sistema.");
		}
		
		for (JButton boton: botonesEmpresas) {
            pnlEmpresas.remove(boton);
        }
 
        botonesEmpresas = new JButton[empresas.length];

		int i = 0;
        for (String empresa: empresas) {
        	JButton boton = new JButton(empresa);
    		boton.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent arg0) {
                    cargarOfertas(empresa);
    			}
    		});
    		boton.setBackground(new Color(238, 238, 238));
    		boton.setBounds(10, 31 + buttonHeight*i, 180, buttonHeight);
    		pnlEmpresas.add(boton);
            botonesEmpresas[i] = boton;
    		i++;
        }
	}
	
    public void cargarOfertas(String empresa) {
    	empresaSeleccionada = empresa;
    	pnlOfertas.setVisible(false);
    	List<DtOferta> ofertas = controladorLaboral.listarOfertasPorEmpresa(empresa, EstadoOferta.Ingresada);
        
        for (JButton boton: botonesOfertas ) {
            pnlOfertas.remove(boton);
        }
        botonesOfertas = new JButton[ofertas.size()];
		int i = 0;
        for (DtOferta oferta: ofertas) {
        	JButton boton = new JButton(oferta.getNombre());
    		boton.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent arg0) {
                    mostrarOferta(oferta.getNombre());
    			}
    		});
    		boton.setBackground(new Color(238, 238, 238));
    		boton.setBounds(10, 31 + buttonHeight*i, 180, buttonHeight);
    		pnlOfertas.add(boton);
            botonesOfertas[i] = boton;
    		i++;
        }
        pnlOfertas.setVisible(true);
	}

    public void mostrarOferta(String oferta) {
    	DtOferta info = controladorLaboral.mostrarOferta(oferta);
        txtNombre.setText(info.getNombre());
        txtEmpresa.setText(info.getEmpresa());
        txtTipo.setText(info.getTipoDeOferta());
    	ofertaFrame.setVisible(true);
    }
}
