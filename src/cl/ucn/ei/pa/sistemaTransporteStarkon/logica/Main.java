package cl.ucn.ei.pa.sistemaTransporteStarkon.logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		SistemaTransporteStarkon sistema = new SistemaTransporteStarkonImpl();
		Scanner input = new Scanner(System.in);
		cargarLocalizaciones(sistema);
		cargarClientes(sistema);
		cargarEntregas(sistema);
		boolean isAppRun = true;
		while (isAppRun) {
			System.out.print("Ingrese rut: ");
			String rut = input.next();
			if (sistema.isClienteRegistrado(rut)) {
				corriendoMenuCliente(sistema, input, rut);
				isAppRun = false;
			}
			else if (sistema.isUsuarioAdministrador(rut)) {
				System.out.print("Ingrese contrasena: ");
				String contrasena = input.next();
				if (sistema.isContrasenaAdministradorCorrecta(contrasena)) {
					corriendoMenuAdmin(sistema, input);
					isAppRun = false;
				}
				else {
					System.out.println("Contraseña incorrecta.");
				}
			}
			else {
				System.out.println("Panel de registrar");
				System.out.print("Ingrese su nombre: ");
				String nombre = input.next();
				System.out.print("Ingrese su apellido: ");
				String apellido = input.next();
				System.out.print("Ingrese su ciudad de origen: ");
				String ciudadOrigen = input.next();
				sistema.ingresarCliente(rut, nombre, apellido, 0, ciudadOrigen);
				System.out.println("Se ha registrado exitosamente, vuelva a iniciar sesión.");
			}
		}

		sobrescribirCliente(sistema);
		sobrescribirArchivoEntrega(sistema);
		input.close();
	}

	public static void corriendoMenuAdmin(SistemaTransporteStarkon sistema, Scanner input) {
		boolean isCorriendoMenuAdmin = true;
		while (isCorriendoMenuAdmin) {
			System.out.println("[Menu Administrador]");
			System.out.println("1) Entregas por tipo.");
			System.out.println("2) Entregas por localización.");
			System.out.println("3) Entregas por cliente.");
			System.out.println("4) Registro de ganancias.");
			System.out.println("5) Cerrar sistema.");
			System.out.print("Ingrese una opcion: ");
			int opcion = input.nextInt();
			switch (opcion) {
				case 1:
					//ds
					break;

				case 2:
					System.out.println(sistema.obtenerEntregasPorLocalizacion());
					break;

				case 3:
					System.out.println(sistema.obtenerDatosActualizadosClientes());
					break;

				case 4:
					System.out.println("Ganancias oficinas");
					System.out.println(sistema.obtenerGananciasOficinasStarkon());
					System.out.println("Balance total");
					System.out.println(sistema.obtenerBalanceTotalOficinasStarkon());
					break;

				case 5:
				isCorriendoMenuAdmin = false;
				System.out.println("Cerrando sistema...");
				break;
			}
		}
	}


	public static void corriendoMenuCliente(SistemaTransporteStarkon sistema, Scanner input, String rut) {
		boolean isCorriendoMenuCliente = true;
		while (isCorriendoMenuCliente) {
			System.out.println("[Menu cliente]");
			System.out.println("1) Realizar una entrega.");
			System.out.println("2) Recargar saldo.");
			System.out.println("3) Ver tus entregas.");
			System.out.println("4) Cerrar sistema.");
			System.out.print("Ingrese una opcion: ");
			int opcion = input.nextInt();
			switch (opcion) {
				case 1:
					System.out.print("Ingrese la primera letra del tipo de entrega (D)ocumento | (E)ncomienda | (V)alija: ");
					String nombreTipoEntrega = input.next().toUpperCase();
					while (sistema.isTipoEntregaPermitido(nombreTipoEntrega)) {
						if (sistema.isTipoEntregaPermitido(nombreTipoEntrega)) {
							System.out.print("Entrega no disponible, intentelo de nuevo.");
						}

						System.out.print("Ingrese la primera letra del tipo de entrega (D)ocumento | (E)ncomienda | (V)alija: ");
						nombreTipoEntrega = input.next().toUpperCase();
					}

					int codigoEntrega = generarCodigoAleatorio(100000, 999999);
					while (!sistema.isAllCodigosOcupados() && !sistema.isCodigoEntregaOcupado(codigoEntrega)) {
						codigoEntrega = generarCodigoAleatorio(100000, 999999);
					}

					if (!sistema.isAllCodigosOcupados()) {
						if (sistema.canClienteEnviarEntregas(rut)) {
							System.out.print("Ingrese el rut del destinatario: ");
							String rutClienteDestinatario = input.next();
							if (sistema.isClienteRegistrado(rutClienteDestinatario)) {
								System.out.print("Ingrese el peso de la entrega: ");
								double peso = input.nextDouble();
								if (sistema.isTipoEntregaDocumento(nombreTipoEntrega)) {
									System.out.print("Ingrese el grosor de la entrega: ");
									double grosor = input.nextDouble();
									if (sistema.haveSaldoSuficienteCliente(peso, grosor, rutClienteDestinatario)) {
										sistema.ingresarDocumento(codigoEntrega, rut, rutClienteDestinatario, peso, grosor);
									}
									else {
										System.out.println("No tienes saldo suficiente");
									}

								}
								else if (sistema.isTipoEntregaEncomienda(nombreTipoEntrega)) {
									System.out.print("Ingrese el largo de la entrega: ");
									double largo = input.nextDouble();
									System.out.print("Ingrese el ancho de la entrega: ");
									double ancho = input.nextDouble();
									System.out.print("Ingrese la profundidad de la entrega: ");
									double profundidad = input.nextDouble();
									if (sistema.haveSaldoSuficienteCliente(peso, largo, ancho, profundidad, rutClienteDestinatario)) {
										sistema.ingresarEncomienda(codigoEntrega, rut, rutClienteDestinatario, peso, largo, ancho, profundidad);
									}
									else {
										System.out.println("No tienes saldo suficiente");
									}
								}
								else if (sistema.isTipoEntregaValija(nombreTipoEntrega)) {
									System.out.print("Ingrese el material de la entrega: ");
									String material = input.next().trim();
									if (sistema.haveSaldoSuficienteCliente(peso, material, rutClienteDestinatario)) {
										sistema.ingresarValija(codigoEntrega, rut, rutClienteDestinatario, peso, material);
									}
									else {
										System.out.println("No tienes saldo suficiente");
									}
								}
							}
							else {
								System.out.println("El cliente destinario no se encuentra registrado en el sistema.");
							}
						}
						else {
							System.out.println("Lo siento, no puedes enviar entregas.");
						}
					}
					else {
						System.out.println("Lo siento, no hay codigo de entregas disponible");
					}

					break;

				case 2:
					System.out.print("Ingrese la cantidad de recarga ($): ");
					int recarga = input.nextInt();
					sistema.recargarSaldoCliente(rut, recarga);
					break;

				case 3:
					System.out.println(sistema.obtenerEntregasCliente(rut));
					break;

				case 4:
					isCorriendoMenuCliente = false;
					System.out.println("Cerrando sistema...");
					break;
			}
		}
	}

	public static void cargarLocalizaciones(SistemaTransporteStarkon sistema) {
		File archivoLocalizacion = new File("archivos/Localización.txt");
		try (Scanner scannerFile = new Scanner(archivoLocalizacion)) {
			while (scannerFile.hasNext()) {
				String nombreCiudad = scannerFile.nextLine();
				sistema.ingresarLocalizacion(nombreCiudad);
			}
		}
		catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

	public static void cargarClientes(SistemaTransporteStarkon sistema) {
		File archivoCliente = new File("archivos/Cliente.txt");
		try (Scanner scannerFile = new Scanner(archivoCliente)) {
			while (scannerFile.hasNext()) {
				String linea = scannerFile.nextLine();
				String[] partes = linea.split(",");
				String rut = partes[0].trim();
				String nombre = partes[1].trim();
				String apellido = partes[2].trim();
				double saldo = Double.parseDouble(partes[3].trim());
				String ciudadOrigen = partes[4].trim();
				sistema.ingresarCliente(rut, nombre, apellido, saldo, ciudadOrigen);
			}
		}
		catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

	public static void cargarEntregas(SistemaTransporteStarkon sistema) {
		File archivoEntrega = new File("archivos/Entrega.txt");
		try (Scanner scannerFile = new Scanner(archivoEntrega)) {
			while (scannerFile.hasNext()) {
				String linea = scannerFile.nextLine();
				String[] partes = linea.split(",");
				int codigo = Integer.parseInt(partes[0].trim());
				String tipoEntrega = partes[1].trim();
				String rutClienteRemitente = partes[2].trim();
				String rutClienteDestinatario = partes[3].trim();
				if (tipoEntrega.equals("D")) {
					double peso = Double.parseDouble(partes[4]);
					double grosor = Double.parseDouble(partes[5].trim());
					sistema.ingresarDocumento(codigo, rutClienteRemitente, rutClienteDestinatario, peso, grosor);
				}
				else if (tipoEntrega.equals("E")) {
					double peso = Double.parseDouble(partes[4].trim());
					double largo = Double.parseDouble(partes[5].trim());
					double ancho = Double.parseDouble(partes[6].trim());
					double profundidad = Double.parseDouble(partes[7].trim());
					sistema.ingresarEncomienda(codigo, rutClienteRemitente, rutClienteDestinatario, peso, largo, ancho, profundidad);
				}
				else {
					String material = partes[4].trim();
					double peso = Double.parseDouble(partes[5].trim());
					sistema.ingresarValija(codigo, rutClienteRemitente, rutClienteDestinatario, peso, material);
				}
			}
		}
		catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

	public static int generarCodigoAleatorio(int min, int max) {
		return (int) Math.floor(Math.random()*(max - min + 1) + min);
	}

	public static void sobrescribirCliente(SistemaTransporteStarkon sistema) {
		try (FileWriter fileWriter = new FileWriter("archivos/Cliente.txt")) {
			fileWriter.write(sistema.obtenerDatosActualizadosClientes());
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public static void sobrescribirArchivoEntrega(SistemaTransporteStarkon sistema) {
		try (FileWriter fileWriter = new FileWriter("archivos/Entrega.txt")) {
			fileWriter.write(sistema.obtenerDatosActualizadosEntregas());
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
