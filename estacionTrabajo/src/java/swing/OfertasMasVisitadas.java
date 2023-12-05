package swing;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.interfacess.IcontroladorLaboral;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;

public class OfertasMasVisitadas extends JInternalFrame{
	private IcontroladorLaboral controladorLaboral;
	private JTable table;
	
	public OfertasMasVisitadas(IcontroladorLaboral IL) {
		controladorLaboral = IL;
		setTitle("Ofertas mas visitadas");
		setClosable(true);
		setMaximizable(true);
		setBounds(0, 0, 941, 249);
		getContentPane().setLayout(null);
		
		
		JButton btnRecargar = new JButton("Recargar");
		btnRecargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarOfertas();
			}
		});
		btnRecargar.setBounds(811, 174, 100, 27);
		getContentPane().add(btnRecargar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 36, 899, 126);
		
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(20);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", null, null, null, null},
				{"2", null, null, null, null},
				{"3", null, null, null, null},
				{"4", null, null, null, null},
				{"5", null, null, null, null},
			},
			new String[] {
				"#", "Ofertas Laborales", "Empresa", "Tipo de publicacion", "Cantidad de visitas"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(180);
		table.getColumnModel().getColumn(2).setPreferredWidth(180);
		table.getColumnModel().getColumn(3).setPreferredWidth(180);
		table.getColumnModel().getColumn(4).setPreferredWidth(180);
		scrollPane.setViewportView(table);

	}
	
	public void cargarOfertas() {
		String[][] ofertas = controladorLaboral.getOfertasMasVisitadas(5);
		for(int aux = 0; aux < 5; aux++) {
			for(int aux2 = 0; aux2 < 4; aux2++) {
				table.setValueAt(ofertas[aux][aux2], aux, aux2 + 1);
			}
		}
	}
}
