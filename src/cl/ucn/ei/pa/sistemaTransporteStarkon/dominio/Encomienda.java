package cl.ucn.ei.pa.sistemaTransporteStarkon.dominio;

public class Encomienda extends Entrega {
	private double largo;
	private double ancho;
	private double profundidad;
	
	public Encomienda(int codigo, Cliente clienteRemitente, Cliente clienteDestinatario, double peso, double largo, double ancho, double profundidad) {
		super(codigo, clienteRemitente, clienteDestinatario, peso);
		this.largo = largo;
		this.ancho = ancho;
		this.profundidad = profundidad;
	}
	
	public Encomienda(double peso, double largo, double ancho, double profundidad) {
		super(peso);
		this.largo = largo;
		this.ancho = ancho;
		this.profundidad = profundidad;
	}

	public double getLargo() {
		return largo;
	}

	public void setLargo(double largo) {
		this.largo = largo;
	}

	public double getAncho() {
		return ancho;
	}

	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	public double getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(double profundidad) {
		this.profundidad = profundidad;
	}

	@Override
	public void calcularPrecio() {
		double volumen = largo * ancho * profundidad;
		double precio = (super.getPeso() * 1/1000.0) * (volumen) * 100;
		super.setPrecio(precio);
	}
}
