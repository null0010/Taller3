package cl.ucn.ei.pa.sistemaTransporteStarkon.dominio;

public class Valija extends Entrega {
	private String material;
	public Valija(int codigo, Cliente clienteRemitente, Cliente clienteDestinatario, double peso, String material) {
		super(codigo, clienteRemitente, clienteDestinatario, peso);
		this.material = material;
	}
	
	public Valija(double peso, String material) {
		super(peso);
		this.material = material;
	}
	
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Override
	public void calcularPrecio() {
		double precio = 150 * (super.getPeso() * 1/1000.0);
		if (material.toLowerCase().equals("cuero")) {
			precio *= 200;
		}
		else if (material.toLowerCase().equals("plástico") || material.toLowerCase().equals("plastico")) {
			precio *= 150;
		}
		else if (material.toLowerCase().equals("tela")){
			precio *= 100;
		}
		
		super.setPrecio(precio);
	}
}
