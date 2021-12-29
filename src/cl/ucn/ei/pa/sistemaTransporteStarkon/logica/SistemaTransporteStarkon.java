package cl.ucn.ei.pa.sistemaTransporteStarkon.logica;

public interface SistemaTransporteStarkon {
	/**
	 * Se ingresa una localización a la lista general de localizaciones.
	 * @param nombre Nombre de la localización
	 * @return boolean
	 */
	boolean ingresarLocalizacion(String nombre);

	/**
	 * Se ingresa un cliente a la lista general de clientes.
	 * @param rut Rut del cliente
	 * @param nombre Nombre del cliente
	 * @param apellido Apellido del cliente
	 * @param saldo Saldo del cliente
	 * @param ciudadOrigen Ciudad de origen del cliente
	 * @return boolean
	 */
	boolean ingresarCliente(String rut, String nombre, String apellido, int saldo, String ciudadOrigen);

	/**
	 * Se ingresa un documento a la lista general de entregas.
	 * Se ingresa un documento a las listas de entregas enviadas y recibidas del cliente remitente y destinatario.
	 * @param codigo The delivery code
	 * @param rutClienteRemitente  Rut del cliente remitente
	 * @param rutClienteDestinatario  Rut del cliente destinatario
	 * @param peso El peso de la entrega
	 * @param grosor Delivery thickness
	 */
	void ingresarDocumento(int codigo, String rutClienteRemitente, String rutClienteDestinatario, double peso, double grosor);

	/**
	 * Se ingresa una encomienda a la lista general de entregas y a las listas de entregas enviadas y recibidas del cliente remitente y destinatario.
	 * @param codigo The delivery code
	 * @param rutClienteRemitente  Rut del cliente remitente
	 * @param rutClienteDestinatario  Rut del cliente destinatario
	 * @param peso El peso de la entrega
	 * @param largo El largo de la entrega
	 * @param ancho El ancho de la entrega
	 * @param profundidad La profundidad de la entrega
	 */
	void ingresarEncomienda(int codigo, String rutClienteRemitente, String rutClienteDestinatario, double peso, double largo, double ancho, double profundidad);

	/**
	 * Se ingresa una valija a la lista general de entregas y a las listas de entregas enviadas y recibidas del cliente remitente y destinatario.
	 * @param codigo The delivery code
	 * @param rutClienteRemitente  Rut del cliente remitente
	 * @param rutClienteDestinatario  Rut del cliente destinatario
	 * @param peso El peso de la entrega
	 * @param material El material de la entrega
	 */
	void ingresarValija(int codigo, String rutClienteRemitente, String rutClienteDestinatario, double peso, String material);

	/**
	 * Se encarga de comprobar si el cliente ya se encuentra registrado en el sistema.
	 * @param rutCliente Rut del cliente
	 * @return boolean
	 */
	boolean isClienteRegistrado(String rutCliente);

	/**
	 * Se encarga de comprobar si el usuario es administrador.
	 * @param rut Rut del administrador
	 * @return boolean
	 */
	boolean isUsuarioAdministrador(String rut);

	/**
	 *
	 * @param contrasena Contraseña del administrador
	 * @return boolean
	 */
	boolean isContrasenaAdministradorCorrecta(String contrasena);

	/**
	 * Se ingresa una recarga a la cuenta del cliente.
	 * @param rutCliente Rut del cliente
	 * @param recarga La cantidad de recarga
	 */
	void recargarSaldoCliente(String rutCliente, int recarga);

	/**
	 * Se encarga de comprobar si el nombre del tipo de entrega ingresado es permitido.
	 * @param nombreTipoEntrega El nombre del tipo de entrega.
	 * @return boolean
	 */
	boolean isTipoEntregaPermitido(String nombreTipoEntrega);

	/**
	 * Se encarga de comprobar si el tipo de entrega es un documento.
	 * @param nombreTipoEntrega El nombre del tipo de entrega.
	 * @return boolean
	 */
	boolean isTipoEntregaDocumento(String nombreTipoEntrega);

	/**
	 * Se encarga de comprobar si el tipo de entrega es una encomienda.
	 * @param nombreTipoEntrega El nombre del tipo de entrega.
	 * @return boolean
	 */
	boolean isTipoEntregaEncomienda(String nombreTipoEntrega);

	/**
	 * Se encarga de comprobar si el tipo de entrega es una valija.
	 * @param nombreTipoEntrega El nombre del tipo de entrega.
	 * @return boolean
	 */
	boolean isTipoEntregaValija(String nombreTipoEntrega);

	/**
	 * Se encarga de comprobar si el código ingresado ya se encuentra ocupado.
	 * @param codigo El codigo de la entrega
	 * @return boolean
	 */
	boolean isCodigoEntregaOcupado(int codigo);

	/**
	 * Se encarga de comprobar si todos los códigos están ocupados.
	 * @return boolean
	 */
	boolean isAllCodigosOcupados();

	/**
	 * Se encarga de comprobar si todos los códigos están ocupados.
	 * @param rut El rut del cliente
	 * @return boolean
	 */
	boolean canClienteEnviarEntregas(String rut);

	/**
	 * Se encarga de comprobar si el cliente tiene saldo suficiente para hacer una entrega de tipoe documento.
	 * @param peso El peso de la entrega
	 * @param grosor El grosor de la entrega
	 * @param rutCliente El rut del cliente
	 * @return
	 */
	boolean haveSaldoSuficienteCliente(double peso, double grosor, String rutCliente);

	/**
	 * Se encarga de comprobar si el cliente tiene saldo suficiente para hacer una entrega de tipoe encomienda.
	 * @param peso El peso de la entrega
	 * @param largo El largo de la entrega
	 * @param ancho El ancho de la entrega
	 * @param profundidad La profundidad de la entrega
	 * @param rutCliente El rut del cliente
	 * @return boolean
	 */
	boolean haveSaldoSuficienteCliente(double peso, double largo, double ancho, double profundidad, String rutCliente);

	/**
	 * Se encarga de comprobar si el cliente tiene saldo suficiente para hacer una entrega de tipo valija.
	 * @param peso El peso de la entrega
	 * @param material El material de la entrega
	 * @param rutCliente El rut del cliente
	 * @return boolean
	 */
	boolean haveSaldoSuficienteCliente(double peso, String material, String rutCliente);

	/**
	 * Se obtienen todas las entregas que ha realizado el cliente.
	 * @param rutCliente El rut del cliente
	 * @return boolean
	 */
	String obtenerEntregasCliente(String rutCliente);
}
