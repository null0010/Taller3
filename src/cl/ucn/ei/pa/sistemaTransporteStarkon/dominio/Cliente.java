package cl.ucn.ei.pa.sistemaTransporteStarkon.dominio;

import cl.ucn.ei.pa.sistemaTransporteStarkon.logica.ListaEntregasDobleNexoCircular;

public class Cliente  {
	private String rut;
	private String nombre;
	private String apellido;
	private double saldo;
	private boolean canEnviarEntregas;
	private Localizacion ciudadOrigen;
	private ListaEntregasDobleNexoCircular listaEntregasEnviadas;
	private ListaEntregasDobleNexoCircular listaEntregasRecibidas;
	
	public Cliente(String rut, String nombre, String apellido, int saldo, Localizacion ciudadOrigen) {
		super();
		this.rut = rut;
		this.nombre = nombre;
		this.apellido = apellido;
		this.saldo = saldo;
		this.ciudadOrigen = ciudadOrigen;
		this.listaEntregasEnviadas = new ListaEntregasDobleNexoCircular();
		this.listaEntregasRecibidas = new ListaEntregasDobleNexoCircular();
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public boolean canEnviarEntregas() {
		return canEnviarEntregas;
	}

	public void setCanEnviarEntregas(boolean canEnviarEntregas) {
		this.canEnviarEntregas = canEnviarEntregas;
	}

	public Localizacion getCiudadOrigen() {
		return ciudadOrigen;
	}

	public void setCiudadOrigen(Localizacion ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}

	public ListaEntregasDobleNexoCircular getListaEntregasEnviadas() {
		return listaEntregasEnviadas;
	}

	public void setListaEntregasEnviadas(ListaEntregasDobleNexoCircular listaEntregasEnviadas) {
		this.listaEntregasEnviadas = listaEntregasEnviadas;
	}

	public ListaEntregasDobleNexoCircular getListaEntregasRecibidas() {
		return listaEntregasRecibidas;
	}

	public void setListaEntregasRecibidas(ListaEntregasDobleNexoCircular listaEntregasRecibidas) {
		this.listaEntregasRecibidas = listaEntregasRecibidas;
	}
}
