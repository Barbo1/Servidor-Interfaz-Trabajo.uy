package model.clases;

import model.datatype.DtCantidad;

public class Cantidad {
	private int cantidad;
	private TipoDeOferta tipodeoferta;
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public TipoDeOferta getTipodeoferta() {
		return tipodeoferta;
	}
	public void setTipodeoferta(TipoDeOferta tipodeoferta) {
		this.tipodeoferta = tipodeoferta;
	}
	
	public Cantidad(int cant, TipoDeOferta tdo) {
		this.cantidad = cant;
		this.tipodeoferta = tdo;
	}
	
	public DtCantidad getDtCantidad() {
		return new DtCantidad(this.tipodeoferta.getNombre(), this.cantidad);
	}
}
