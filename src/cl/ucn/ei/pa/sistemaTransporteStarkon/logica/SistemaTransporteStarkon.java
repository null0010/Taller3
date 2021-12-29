package cl.ucn.ei.pa.sistemaTransporteStarkon.logica;

public interface SistemaTransporteStarkon {
	public boolean ingresarLocalizacion(String nombre);

	public boolean ingresarCliente(String rut, String nombre, String apellido, int saldo, String ciudadOrigen);

	public void ingresarDocumento(int codigo, String rutClienteRemitente, String rutClienteDestinatario, double peso, double grosor);

	public void ingresarEncomienda(int codigo, String rutClienteRemitente, String rutClienteDestinatario, double peso, double largo, double ancho, double profundidad);

	public void ingresarValija(int codigo, String rutClienteRemitente, String rutClienteDestinatario, double peso, String material);

	boolean isClienteRegistrado(String rutCliente);

	boolean isUsuarioAdministrador(String rut);

	boolean isContrasenaAdministradorCorrecta(String contrasena);

	void recargarSaldoCliente(String rutCliente, int recarga);

	boolean isTipoEntregaPermitido(String nombreTipoEntrega);

	boolean isTipoEntregaDocumento(String nombreTipoEntrega);

	boolean isTipoEntregaEncomienda(String nombreTipoEntrega);

	boolean isTipoEntregaValija(String nombreTipoEntrega);

	boolean isCodigoEntregaOcupado(int codigo);

	boolean isAllCodigosOcupados();

	boolean canClienteEnviarEntregas(String rut);

	boolean haveSaldoSuficienteCliente(double peso, double grosor, String rutCliente);

	boolean haveSaldoSuficienteCliente(double peso, double largo, double ancho, double profundidad, String rutCliente);

	boolean haveSaldoSuficienteCliente(double peso, String material, String rutCliente);

	String obtenerEntregasCliente(String rutCliente);
}
