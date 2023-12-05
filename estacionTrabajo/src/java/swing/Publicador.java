package swing;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import publicador.WebServices;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Publicador extends JInternalFrame  {
	private JTextField campoIp;
	private JTextField campoPuerto;
	public Publicador() {
		setResizable(true);
		getContentPane().setLayout(null);
		setTitle("Publicador");
		setClosable(true);
		setMaximizable(true);
		setBounds(0, 0, 500, 249);
		
		JLabel lblIpHost = new JLabel("IP HOST:");
		lblIpHost.setBounds(63, 55, 70, 15);
		getContentPane().add(lblIpHost);
		
		campoIp = new JTextField();
		campoIp.setBounds(164, 47, 236, 31);
		getContentPane().add(campoIp);
		campoIp.setColumns(10);
		
		campoPuerto = new JTextField();
		campoPuerto.setColumns(10);
		campoPuerto.setBounds(164, 90, 236, 31);
		getContentPane().add(campoPuerto);
		
		JLabel lblPuerto = new JLabel("PUERTO:");
		lblPuerto.setBounds(63, 98, 70, 15);
		getContentPane().add(lblPuerto);
		
		JButton btnPublicar = new JButton("Publicar");
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				publicarCambios();
			}
		});
		btnPublicar.setBounds(283, 181, 117, 25);
		getContentPane().add(btnPublicar);
		
		JButton btnAplicarCambios = new JButton("Aplicar cambios");
		btnAplicarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aplicarCambios();
			}
		});
		btnAplicarCambios.setBounds(63, 181, 155, 25);
		getContentPane().add(btnAplicarCambios);
		
		
		
	}
	
	public void cargarDatos() {
		String puerto = "";
		String ip =  "";
        String directorioInicioUsuario = System.getProperty("user.home");
        Path path = Paths.get(directorioInicioUsuario + "/TrabajoUY/server.properties");
		if (Files.exists(path)) {
	        Properties properties = new Properties();
			try (FileInputStream input = new FileInputStream(path.toString())) {
	            properties.load(input);
	            puerto = properties.getProperty("port");
	            ip = properties.getProperty("host");
			} catch (Exception e) {
				
			}
		} else {
			 try (FileWriter writer = new FileWriter(path.toString(),false)) {
		            // Escribe contenido en el archivo
		            writer.write("host=\n");
		            writer.write("port=\n");
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
		}
		this.campoIp.setText(ip);
		this.campoPuerto.setText(puerto);
	}
	
	public void aplicarCambios() {
		String puerto = campoPuerto.getText();
		String ip = campoIp.getText();
		if (ip.replaceAll("\\s", "").equals("") || puerto.replaceAll("\\s", "").equals("")) {
    		JOptionPane.showMessageDialog(null, "Campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
		}else {
	        String directorioInicioUsuario = System.getProperty("user.home");
	        Path path = Paths.get(directorioInicioUsuario + "/TrabajoUY/server.properties");
			try (FileWriter writer = new FileWriter(path.toString(),false)) {
	            writer.write("host="+ ip +"\n");
	            writer.write("port="+puerto+"\n");
	    		JOptionPane.showMessageDialog(null, "Se aplicaron los cambios en el archivo server.properties", "Exito", JOptionPane.INFORMATION_MESSAGE);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
		}
	}
	
	public void publicarCambios() {
		this.aplicarCambios();
		WebServices p = new WebServices();
        p.publicar();
		JOptionPane.showMessageDialog(null, "Se publicaron los web service.", "Exito", JOptionPane.INFORMATION_MESSAGE);
	}
}
