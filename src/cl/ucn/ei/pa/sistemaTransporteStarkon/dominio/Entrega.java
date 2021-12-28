package cl.ucn.ei.pa.sistemaTransporteStarkon.dominio;

public abstract class Entrega {
	private int codigo;
	private Cliente clienteRemitente;
	private Cliente clienteDestinatario;
	private double peso;
	private double precio;
	public Entrega(int codigo, Cliente clienteRemitente, Cliente clienteDestinatario, double peso) {
		super();
		this.codigo = codigo;
		this.clienteRemitente = clienteRemitente;
		this.clienteDestinatario = clienteDestinatario;
		this.peso = peso;
	}
	
	public Entrega(double peso) {
		this.peso = peso;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public Cliente getClienteRemitente() {
		return clienteRemitente;
	}
	
	public void setClienteRemitente(Cliente clienteRemitente) {
		this.clienteRemitente = clienteRemitente;
	}
	
	public Cliente getClienteDestinatario() {
		return clienteDestinatario;
	}
	
	public void setClienteDestinatario(Cliente clienteDestinatario) {
		this.clienteDestinatario = clienteDestinatario;
	}
	
	public double getPeso() {
		return peso;
	}
	
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}	
	
	
	public abstract void calcularPrecio();
}