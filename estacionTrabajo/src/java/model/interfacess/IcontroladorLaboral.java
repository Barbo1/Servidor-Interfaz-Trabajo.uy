package model.interfacess;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import model.clases.Oferta;
import model.datatype.DtOferta;
import model.datatype.DtOfertaCompleto;
import model.datatype.DtPaquete;
import model.datatype.DtPaqueteCompleto;
import model.datatype.DtPostulacionCompleto;
import model.datatype.DtTipoDeOferta;
import model.datatype.EstadoOferta;
import model.exceptions.DuplicationException;
import model.exceptions.NonExistentException;
import model.exceptions.OutOfMarginException;
public interface IcontroladorLaboral {
	
	public void cambiarEstadoOferta(
			String nombreOferta, 
			EstadoOferta estado
		) throws NonExistentException;
	
	public void ingresarOfertaLaboral(
			String empresaNickname, 
			String tipoOferta, 
			String nombreOferta, 
			String descripcion, 
			String horario, 
			Float remuneracion, 
			String ciudad, 
			String departamento, 
			LocalDate fechaAlta, 
			Set<String> keywords,
			String imagen,
			EstadoOferta est
	);
	
	public DtOfertaCompleto mostrarOfertaCompleto(String nombreOferta);
	
	public void altaTipoOferta(
			String nombre, 
			String descripcion, 
			int duracion, 
			int expisicion, 
			float costo, 
			LocalDate fecha
	) throws DuplicationException, OutOfMarginException;
	
	public void altaPaquete(
			String nombre, 
			String descripcion, 
			int periodoValido, 
			float descuento, 
			LocalDate fecha,
			String imagen
	);
	
	public String[] listarPaquetes();
	
	public String[] listarTipoDeOferta();
	
	public void agregarTipoDePublicacionAPaquete(
			String paquete , 
			String tipoOferta, 
			int cantidad
	);
	
	public Set<String> listarTiposDeOfertaSTR();
	
	public void ingresarKeyword(String keyword);
	
	public void postular(
			String postulante, 
			String oferta, 
			String curriculum, 
			String motivo, 
			LocalDate fecha,
			String video
	) throws DuplicationException, NonExistentException, OutOfMarginException;
	
	public DtPaqueteCompleto mostrarInfoPaquete(String nombrePaquete);
	
	public DtOferta mostrarOferta(String nombreOferta);
	
	public String[] listarKeywords(); 
	
	public DtTipoDeOferta mostrarTipoOferta(String tipoOf);

	public DtOfertaCompleto mostrarOfertaCompletoPostulante(String nombreOferta, String nickname);

	public List<DtPaquete> getDtPaquetes();	

	public DtPaquete getDtPaquete(String nombrePaquete);

	public DtPaqueteCompleto getDtPaqueteCompleto(String nombrePaquete);	

	public List<DtOferta> listarOfertasSinOrdenar();
	public List<DtOferta> listarOfertas();
	public List<DtOferta> listarOfertasPorKeyword(String key);
	public List<DtOferta> listarOfertasPorEmpresa(String nombre_empresa, EstadoOferta state);

	public void comprarPaquete(String nombreEmpresa, String nombrePaquete, LocalDate fechaAltaPaquete) throws DuplicationException;

	public boolean tienePaquete(String nickEmpresa, String nombrePaquete);

	public void ingresarOfertaLaboralConPaquete(String empresaNickname, String tipoOferta, String nombreOferta,
			String descripcion, String horario, Float remuneracion, String ciudad, String departamento, LocalDate fechaAlta,
			Set<String> keywords, String imagen, String paquete, EstadoOferta est)
			throws OutOfMarginException, DuplicationException, NonExistentException;

	public String[] listarTipoDeOfertaFaltante(String paquete);

	String[] listarPaquetesNoComprados();

	void postularCargaDatos(String pos, String nombreOferta, String curriculum, String motivacion, LocalDate fecha, String video)
			throws DuplicationException, NonExistentException, OutOfMarginException;
	
	public DtPostulacionCompleto getPostulanteDeOferta(String post, String nombreOferta);
	
	public void addVisita(String nombreOferta);
	
	public String[][] getOfertasMasVisitadas(int cantidad);
	
	public void addOfertaFavorito(String nombreOferta, String nickPostulante);
	
	public void removeOfertaFavorito(String nombreOferta, String nickPostulante);
	
	public int getReputacionOferta(String nombreOferta);
	
	public boolean tienePostulanteFavorito(String nombreOferta, String nicknamePostulante);
	
	public void setVisita(String nombreOferta, int cantidad);
	
    public void cargarOrdenAPost(int orden,String nombreOferta, String nickPostulante);
    
    public void finalizarSeleccion(String nombreOferta);
    
    public boolean seleccionFinalizada(String nombreOferta);
    public void limpiarOrden(String nombreOferta);

}
