package cl.ucn.ei.pa.sistemaTransporteStarkon.logica;

import java.util.ArrayList;
import java.util.LinkedList;

import cl.ucn.ei.pa.sistemaTransporteStarkon.dominio.Cliente;
import cl.ucn.ei.pa.sistemaTransporteStarkon.dominio.Documento;
import cl.ucn.ei.pa.sistemaTransporteStarkon.dominio.Encomienda;
import cl.ucn.ei.pa.sistemaTransporteStarkon.dominio.Localizacion;
import cl.ucn.ei.pa.sistemaTransporteStarkon.dominio.Valija;

public class SistemaTransporteStarkonImpl implements SistemaTransporteStarkon {

	private ArrayList<Localizacion> listaLocalizaciones;
	private LinkedList<Cliente> listaClientes;
	private ListaEntregasDobleNexoCircular listaEntregas;

	public SistemaTransporteStarkonImpl() {
		listaLocalizaciones = new ArrayList<Localizacion>();
		listaClientes = new LinkedList<Cliente>();
		listaEntregas = new ListaEntregasDobleNexoCircular();
	}

	@Override
	public boolean ingresarLocalizacion(String nombre) {
		Localizacion localizacion = new Localizacion(nombre);
		return listaLocalizaciones.add(localizacion);
	}

	@Override
	public boolean ingresarCliente(String rut, String nombre, String apellido, double saldo, String ciudadOrigen) {
		Cliente cliente = null;
		boolean canRealizarEntrega = false;
		for (Localizacion localizacion : listaLocalizaciones) {
			if (localizacion.getNombre().equals(ciudadOrigen)) {
				cliente = new Cliente(rut, nombre, apellido, saldo, localizacion);
				canRealizarEntrega = true;
				break;
			}
		}

		if (!canRealizarEntrega) {
			Localizacion nuevaLocalizacion = new Localizacion(ciudadOrigen);
			cliente = new Cliente(rut, nombre, apellido, saldo, nuevaLocalizacion);
		}

		cliente.setCanEnviarEntregas(canRealizarEntrega);
		return listaClientes.add(cliente);
	}

	@Override
	public void ingresarDocumento(int codigo, String rutClienteRemitente, String rutClienteDestinatario, double peso,
								  double grosor) {
		Cliente clienteRemitente = null;
		Cliente clienteDestinatario = null;

		for (Cliente cliente : listaClientes) {
			if (cliente.getRut().equals(rutClienteRemitente)) {
				clienteRemitente = cliente;
				break;
			}
		}

		for (Cliente cliente : listaClientes) {
			if (cliente.getRut().equals(rutClienteDestinatario)) {
				clienteDestinatario = cliente;
				break;
			}
		}

		if (clienteRemitente == null || clienteDestinatario == null) {
			throw new NullPointerException("El Cliente remitente y/o destinatario no existen.");
		}

		Documento documento = new Documento(codigo, clienteRemitente, clienteDestinatario, peso, grosor);
		clienteRemitente.getListaEntregasEnviadas().agregarUltimo(documento);
		clienteDestinatario.getListaEntregasRecibidas().agregarUltimo(documento);
		listaEntregas.agregarUltimo(documento);
	}

	@Override
	public void ingresarEncomienda(int codigo, String rutClienteRemitente, String rutClienteDestinatario, double peso,
								   double largo, double ancho, double profundidad) {
		Cliente clienteRemitente = null;
		Cliente clienteDestinatario = null;

		for (Cliente cliente : listaClientes) {
			if (cliente.getRut().equals(rutClienteRemitente)) {
				clienteRemitente = cliente;
				break;
			}
		}

		for (Cliente cliente : listaClientes) {
			if (cliente.getRut().equals(rutClienteDestinatario)) {
				clienteDestinatario = cliente;
				break;
			}
		}

		if (clienteRemitente == null || clienteDestinatario == null) {
			throw new NullPointerException("El Cliente remitente y/o destinatario no existen.");
		}

		Encomienda encomienda = new Encomienda(codigo, clienteRemitente, clienteDestinatario, peso, largo, ancho, profundidad);
		clienteRemitente.getListaEntregasEnviadas().agregarUltimo(encomienda);
		clienteDestinatario.getListaEntregasRecibidas().agregarUltimo(encomienda);
		listaEntregas.agregarUltimo(encomienda);
	}

	@Override
	public void ingresarValija(int codigo, String rutClienteRemitente, String rutClienteDestinatario, double peso,
							   String material) {
		Cliente clienteRemitente = null;
		Cliente clienteDestinatario = null;

		for (Cliente cliente : listaClientes) {
			if (cliente.getRut().equals(rutClienteRemitente)) {
				clienteRemitente = cliente;
				break;
			}
		}

		for (Cliente cliente : listaClientes) {
			if (cliente.getRut().equals(rutClienteDestinatario)) {
				clienteDestinatario = cliente;
				break;
			}
		}

		if (clienteRemitente == null || clienteDestinatario == null) {
			throw new NullPointerException("El Cliente remitente y/o destinatario no existen.");
		}

		Valija valija = new Valija(codigo, clienteRemitente, clienteDestinatario, peso, material);
		clienteRemitente.getListaEntregasEnviadas().agregarUltimo(valija);
		clienteDestinatario.getListaEntregasRecibidas().agregarUltimo(valija);
		listaEntregas.agregarUltimo(valija);
	}

	@Override
	public boolean isClienteRegistrado(String rutCliente) {
		for (Cliente cliente : listaClientes) {
			if (cliente.getRut().equals(rutCliente)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isUsuarioAdministrador(String rut) {
		return rut.equals("Admin");
	}

	@Override
	public boolean isContrasenaAdministradorCorrecta(String contrasena) {
		return contrasena.equals("choripan123");
	}

	@Override
	public void recargarSaldoCliente(String rutCliente, int recarga) {
		for (Cliente cliente : listaClientes) {
			if (cliente.getRut().equals(rutCliente)) {
				cliente.setSaldo(cliente.getSaldo() + recarga);
				break;
			}
		}
	}

	@Override
	public boolean isTipoEntregaPermitido(String nombreTipoEntrega) {
		return !nombreTipoEntrega.equals("D") && !nombreTipoEntrega.equals("E") && !nombreTipoEntrega.equals("V");
	}

	@Override
	public boolean isTipoEntregaDocumento(String nombreTipoEntrega) {
		return nombreTipoEntrega.equals("D");
	}

	@Override
	public boolean isTipoEntregaEncomienda(String nombreTipoEntrega) {
		// TODO Auto-generated method stub
		return nombreTipoEntrega.equals("E");
	}

	@Override
	public boolean isTipoEntregaValija(String nombreTipoEntrega) {
		// TODO Auto-generated method stub
		return nombreTipoEntrega.equals("V");
	}

	@Override
	public boolean isCodigoEntregaOcupado(int codigo) {
		return listaEntregas.buscarEntrega(codigo) != null;
	}

	public boolean isAllCodigosOcupados() {
		return listaEntregas.getSize() == 999999;
	}

	@Override
	public boolean canClienteEnviarEntregas(String rut) {
		boolean canEnviarEntregas = false;
		for (Cliente cliente : listaClientes) {
			if (cliente.getRut().equals(rut)) {
				canEnviarEntregas = cliente.canEnviarEntregas();
				break;
			}
		}

		return canEnviarEntregas;
	}

	@Override
	public boolean haveSaldoSuficienteCliente(double peso, double grosor, String rutCliente) {
		Documento documento = new Documento(peso, grosor);
		documento.calcularPrecio();
		double precioDocumento = documento.getPrecio();
		boolean haveSaldoSuficiente = false;
		for (Cliente cliente : listaClientes) {
			if (cliente.getRut().equals(rutCliente)) {
				double saldo = cliente.getSaldo();
				haveSaldoSuficiente = (saldo - precioDocumento) >= 0;
				if (haveSaldoSuficiente) {
					cliente.setSaldo(saldo - precioDocumento);
				}
				break;
			}
		}

		return haveSaldoSuficiente;
	}

	@Override
	public boolean haveSaldoSuficienteCliente(double peso, double largo, double ancho, double profundidad,
											  String rutCliente) {
		Encomienda encomienda = new Encomienda(peso, largo, ancho, profundidad);
		encomienda.calcularPrecio();
		double precioEncomienda = encomienda.getPrecio();
		boolean haveSaldoSuficiente = false;
		for (Cliente cliente : listaClientes) {
			if (cliente.getRut().equals(rutCliente)) {
				double saldo = cliente.getSaldo();
				haveSaldoSuficiente = (saldo - precioEncomienda) >= 0;

				if (haveSaldoSuficiente) {
					cliente.setSaldo(saldo - precioEncomienda);
				}

				break;
			}
		}

		return haveSaldoSuficiente;
	}

	@Override
	public boolean haveSaldoSuficienteCliente(double peso, String material, String rutCliente) {
		// TODO Auto-generated method stub
		Valija valija = new Valija(peso, material);
		valija.calcularPrecio();
		double precioValija = valija.getPrecio();
		boolean haveSaldoSuficiente = false;
		for (Cliente cliente : listaClientes) {
			if (cliente.getRut().equals(rutCliente)) {
				double saldo = cliente.getSaldo();
				haveSaldoSuficiente = (saldo - precioValija) >= 0;
				if (haveSaldoSuficiente) {
					cliente.setSaldo(saldo - precioValija);
				}

				break;
			}
		}

		return haveSaldoSuficiente;
	}

	@Override
	public String obtenerEntregasCliente(String rutCliente) {
		String salida = "";
		for (Cliente cliente : listaClientes) {
			if (cliente.getRut().equals(rutCliente)) {
				salida += "Entregas enviadas: \n";
				salida += cliente.getListaEntregasEnviadas().toString() + "\n";
				salida += "Entregas recibidas: \n";
				salida += cliente.getListaEntregasRecibidas().toString() + "\n";
				break;
			}
		}
		return salida;
	}

	@Override
	public String obtenerDatosActualizadosEntregas() {
		return listaEntregas.toString();
	}


	@Override
	public String obtenerDatosActualizadosClientes() {
		String salida = "";
		for (int i = 0; i < listaClientes.size(); i++) {
			Cliente cliente = listaClientes.get(i);
			salida += cliente.getRut()
					+ ", "
					+ cliente.getNombre()
					+ ", "
					+ cliente.getApellido()
					+ ", "
					+ cliente.getSaldo()
					+ ", "
					+ cliente.getCiudadOrigen().getNombre()
					+ "\n";
		}

		return salida;
	}
}
