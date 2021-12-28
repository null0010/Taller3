package cl.ucn.ei.pa.sistemaTransporteStarkon.dominio;

public class Localizacion {
	private String nombre;
	private int cantidadEntregasEnviadas;
	private int cantidadEntregasRecibidas;
	private double ganancias;

	public Localizacion(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getCantidadEntregasEnviadas() {
		return cantidadEntregasEnviadas;
	}
	
	public void setCantidadEntregasEnviadas(int cantidadEntregasEnviadas) {
		this.cantidadEntregasEnviadas = cantidadEntregasEnviadas;
	}
	
	public int getCantidadEntregasRecibidas() {
		return cantidadEntregasRecibidas;
	}
	
	public void setCantidadEntregasRecibidas(int cantidadEntregasRecibidas) {
		this.cantidadEntregasRecibidas = cantidadEntregasRecibidas;
	}
	
	public double getGanancias() {
		return ganancias;
	}
	
	public void setGanancias(double ganancias) {
		this.ganancias = ganancias;
	}	
}