package model.interfacess;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.clases.Empresa;
import model.datatype.DtEmpresaCompleto;
import model.datatype.DtPostulanteCompleto;
import model.datatype.DtUsuario;
import model.exceptions.DuplicationException;
import model.exceptions.NonExistentException;

public interface IcontroladorUsuario {
	
	public void ingresarPostulante(LocalDate fechaNacimientoU, String nacionalidad, String nombre, String apellido, String nickname, String correo, String pass, String imagen) throws DuplicationException;
	public void ingresarEmpresa(String link, String descripcion, String nombre, String apellido, String nickname, String correo, String pass, String imagen) throws DuplicationException;
	public String[] listarUsuarios();
	public String[] listarEmpresas();
	public String[] listarPostulantes();
	public boolean esEmpresa(String nick);
	public boolean esPostulante(String nick);
	public String[] listarOfertasEmpresa(String empresaNickname) throws NonExistentException;
	public void modificarDatosPostulante(String nickname, String nombre, String apellido, LocalDate nacimiento, String nacionalidad, String imagen, String password);
	public void modificarDatosEmpresa(String nickname, String nombre, String apellido, String link, String descripcion, String imagen, String password);
	public DtPostulanteCompleto mostrarPostulante(String postulanteID);
	public DtEmpresaCompleto mostrarEmpresa(String empresaID);
	//TODO implement dependencies
	public Empresa getEmpresa(String nombre);
	public boolean estaPostulado(String nickname, String oferta);
	public Map<String, Map<String, Integer>> obtenerPaquetes(String nickname);	
	public boolean ofertaDeEmpresa(String empresa, String oferta);
	public List<DtUsuario> getDtUsuarios();
	public void setFollow(String user, String seguido);
	public void setUnFollow(String user, String seguido);
//	public Map<String, Oferta> getOfertas(String nickPostulante);
	public String[] listarEmpresaAutoCompletar(String nombre_empresa);
}
