package model.clases;

import model.controllers_managers.ControladorLaboral;
import model.controllers_managers.ControladorUsuario;
import model.interfacess.IcontroladorLaboral;
import model.interfacess.IcontroladorUsuario;


public class Fabrica {
	
	//Singleton
	private static Fabrica instance;
	
	private Fabrica(){
		
	}
	
	public static Fabrica getInstance() {
		if (instance == null) {
			instance = new Fabrica();
		}
		return instance;
	}
	
	public IcontroladorUsuario getIControladorUsuario() {
		return new ControladorUsuario();
	}
	
	public IcontroladorLaboral getIcontroladorLaboral() {
		return new ControladorLaboral();
	}

}
