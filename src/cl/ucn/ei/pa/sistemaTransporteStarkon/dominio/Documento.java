package cl.ucn.ei.pa.sistemaTransporteStarkon.dominio;

public class Documento extends Entrega {
	private double grosor;
	
	public Documento(int codigo, Cliente clienteRemitente, Cliente clienteDestinatario, double peso, double grosor) {
		super(codigo, clienteRemitente, clienteDestinatario, peso);
		this.grosor = grosor;
	}
	
	public Documento(double peso, double grosor) {
		super(peso);
		this.grosor = grosor;
	}

	public double getGrosor() {
		return grosor;
	}

	public void setGrosor(double grosor) {
		this.grosor = grosor;
	}

	@Override
	public void calcularPrecio() {
		double precio = (super.getPeso() * 1/1000.0) * grosor * 100;
		super.setPrecio(precio);
	}
}
