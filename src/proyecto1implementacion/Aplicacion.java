package proyecto1implementacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import proyecto1implementacion.Alimento.tipoAlimento;
import proyecto1implementacion.Servicio.areaAsociada;

public class Aplicacion {

	private Hotel hotel;
	private UsuarioSistema usuarioActual;
	private File archivoUsuarios = new File("data/Usuarios.csv");
	private static Integer facturasExpedidas;

	public void ejecutarOpcion() {
		hotel = new Hotel("House System");

		try {
			cargarUsuarios(archivoUsuarios);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		boolean continuar = true;

		while (continuar) {

			mostrarMenu();
			int opcion_seleccionada = Integer.parseInt(input("\nPor favor seleccione una opcion"));
			if (opcion_seleccionada == 1) {
				try {

					Hotel NewHotel = CargarAplicacion();
					hotel = NewHotel;
					System.out.println("Informacion de la seccion anterior cargado correctamente");
					System.out.println(hotel.toString());
					System.out.println(hotel);

				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}

			else if (opcion_seleccionada == 2) {
				try {
					GuardarAplicacion(hotel);
					System.out.println("Informacion guardada correctamente");
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			} else if (opcion_seleccionada == 3) {
				try {
					registrarNuevoEmpleado();
				} catch (Exception e) {

					System.err.println(e.getMessage());
				}
			} else if (opcion_seleccionada == 4) {
				try {
					iniciarSesionEmpleado();
					menuUsuario();
				} catch (Exception e) {

					System.err.println(e.getMessage());
				}
			} else if (opcion_seleccionada == 5) {
				System.out.println("\nSaliendo de la aplicacion ...");
				continuar = false;
			} else {
				System.out.println("\nPor favor seleccione una opcion valida.");
			}
		}
	}

	public void mostrarMenu() {

		String nombre = hotel.getNombre();

		System.out.println("Bienvenido al hotel " + nombre);
		System.out.println("1. Cargar aplicacion");
		System.out.println("2. Guardar aplicacion");
		System.out.println("3. Registrar nuevo empleado");
		System.out.println("4. Iniciar sesion empleado");
		System.out.println("5. Salir");
	}

	public void cargarUsuarios(File archivoUsuarios) throws Exception {
		Administrador admin = null;

		BufferedReader br = new BufferedReader(new FileReader(archivoUsuarios));
		br.readLine();
		String linea = br.readLine();

		while (linea != null) {
			String[] partes = linea.split(";");
			String login = partes[0];
			String password = partes[1];
			String tipoEmpleado = partes[2];

			if (!tipoEmpleado.equals("administrador") && !tipoEmpleado.equals("recepcionista")
					&& !tipoEmpleado.equals("empleado")) {
				br.close();
				throw new Exception("La informacion en el archivo es incorrecta");
			}

			if (tipoEmpleado.equals("administrador")) {
				if (admin != null) {
					br.close();
					throw new Exception("Existe mas de un administrador en el archivo.");
				}

				admin = new Administrador(login, password, hotel);
				Map<String, UsuarioSistema> empleados = hotel.getEmpleados();
				empleados.put(login, admin);
			} else if (tipoEmpleado.equals("recepcionista")) {
				EmpleadoRecepcion recep = new EmpleadoRecepcion(login, password, hotel);
				Map<String, UsuarioSistema> empleados = hotel.getEmpleados();
				empleados.put(login, recep);
			} else {

				Empleado emple = new Empleado(login, password, hotel);
				Map<String, UsuarioSistema> empleados = hotel.getEmpleados();
				empleados.put(login, emple);

			}

			linea = br.readLine();
		}

		br.close();

	}

	private void registrarNuevoEmpleado() throws Exception {

		String login = input("Usuario");
		String password = input("Contraseña");
		String tipo = input("Tipo de empleado");

		hotel.registrarNuevoEmpleado(login, password, tipo);

		actualizarArchivoUsuarios(login, password, tipo);

		System.out.println("Empleado registrado exitosamente.");
	}

	private void actualizarArchivoUsuarios(String login, String password, String tipoEmpleado) throws IOException {

		BufferedWriter bw = new BufferedWriter(new FileWriter(archivoUsuarios, true));
		bw.write("\n" + login + ";" + password + ";" + tipoEmpleado);
		bw.close();

	}

	private void iniciarSesionEmpleado() throws Exception {

		String login = input("Usuario");
		String password = input("Contraseña");

		UsuarioSistema usuario = hotel.getEmpleadoByLogin(login);

		if (usuario == null) {
			throw new Exception("No existe un usuario con este nombre");
		}
		if (!usuario.getPassword().equals(password)) {
			throw new Exception("La contraseña es incorrecta");
		}

		usuarioActual = usuario;
		System.out.println("Has iniciado sesion correctamente.");
	}

	public String input(String mensaje) {
		try {
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		} catch (IOException e) {
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

	private void menuUsuario() {
		if (usuarioActual instanceof Administrador) {
			menuAdministrador();
		} else if (usuarioActual instanceof Empleado) {
			menuEmpleado();
		} else if (usuarioActual instanceof EmpleadoRecepcion) {
			menuEmpleadoRecepcion();
		}
	}

	private void menuEmpleadoRecepcion() {

		int opcion = 0;
		boolean salirmenu = false;

		while (!salirmenu) {
			System.out.println("------Menu Recepcionista del hotel------");
			System.out.println("\nBienvenido, " + usuarioActual.getLogin());
			System.out.println("Por favor seleccione una opcion:");
			System.out.println("1. Consultar inventario");
			System.out.println("2. Consultar habitaciones por ID");
			System.out.println("3. Consultar habitaciones por rango de fechas");
			System.out.println("4. Registrar reserva");
			System.out.println("5. Cancelar reserva");
			System.out.println("6. Registrar Check-in");
			System.out.println("7. Hacer Check-out según grupo");
			System.out.println("8. Volver al menu anterior");

			opcion = Integer.parseInt(input("Seleccione una opcion"));

			switch (opcion) {
			case 1:

				break;

			case 2:

				break;

			case 3:

				break;

			case 4:

				break;

			case 5:

				break;

			case 6:

				break;

			case 7:

				String nombrePrincipal = input("Nombre del huésped principal");
				Huesped huespedPrincipal = hotel.buscarHuesped(nombrePrincipal);

				if (huespedPrincipal == null) {
					System.out.println("No existe huésped con este nombre");
					break;
				}
				GrupoHuespedes grupo = huespedPrincipal.getGrupoAsociado();

				try {
					guardarFactura(grupo);
					System.out.println("Factura generada correctamente");
				} catch (IOException e) {
					System.err.println("No se pudo generar la factura");
				}
				
				break;

			case 8:
				salirmenu = true;
				System.out.println("Hasta pronto! " + usuarioActual.getLogin());
				break;

			default:
				System.out.println("Seleccione una opción válida");
				break;
			}
		}
	}

	private String generarTextoFactura(GrupoHuespedes grupo){
	
		double total = 0;
		String texto = "factura\n\nNúmero: " + facturasExpedidas + "\nHuéspedes:\n- " + grupo.getHuespedPrincipal().getNombre() + "\n";
		
		for(Huesped huesped : grupo.getAcompaniantes()) {
			
		texto += "- " + huesped.getNombre() + "\n";
		}
		
		texto += "\nHabitaciones:\n";
		
		for(Habitacion habitacion : grupo.getHabitacionesAsignadas()) {
			
			texto += "- " + habitacion.getIdentificador() + "\n";
		}
		texto += "\nConsumos:\nID\tFecha\tHuesped\tArea consumo\tValor neto\tIVA\tValor total";
		
		for(Consumo consumo : grupo.getConsumosAsociados()) {
			
			if(!consumo.isPagado()) {
				texto += consumo.generarTextoFactura() + "\n";
				total+=consumo.getValorTotal();
			}
		}
		texto += "\nTotal: $" + total;
		
		return texto;
	}
	
	private void guardarFactura(GrupoHuespedes grupo) throws IOException {
		
		File archivo = new File("facturas/factura" + facturasExpedidas + ".txt");

		PrintWriter pw = new PrintWriter(new FileWriter(archivo));
		pw.println(generarTextoFactura(grupo));
		
		pw.close();
		facturasExpedidas++;
	}

	private void menuEmpleado() {

		int opcion = 0;
		boolean salirmenu = false;

		while (!salirmenu) {
			System.out.println("------Menu Personal del hotel------");
			System.out.println("Bienvenido, " + usuarioActual.getLogin());
			System.out.println("Por favor seleccione una opcion:");
			System.out.println("1. Registrar un nuevo consumo");
			System.out.println("2. Registrar un pago");
			System.out.println("3. Volver al menu anterior");

			opcion = Integer.parseInt(input("Seleccione una opcion"));

			switch (opcion) {
			case 1:
				areaAsociada area = null;
				boolean Pagado = false;
				boolean salir2 = false;
				boolean salir3 = false;
				Scanner scanner = new Scanner(System.in);
				int opcion2 = scanner.nextInt();
				while (salir2 != true) {

					System.out.println("Seleccione el area de consumo asociada:");
					System.out.println("1. SPA");
					System.out.println("2. GUIA TURISTICO");
					System.out.println("3. RESTAURANTE");

					if (opcion2 == 1) {
						area = areaAsociada.SPA;
						salir2 = true;
					} else if (opcion2 == 2) {
						area = areaAsociada.GUIATURISTICO;
						salir2 = true;
					} else if (opcion2 == 3) {
						area = areaAsociada.RESTAURANTE;
						salir2 = true;
					} else {
						System.out.println("Opcion no disponible, por favor seleccione una opcion valida");
					}
				}

				while (salir3 != true) {

					System.out.println("¿El consumo ya fue pagado?");
					System.out.println("1. Si, ya fue pagado");
					System.out.println("2. No, queda registrado");
					opcion2 = scanner.nextInt();

					if (opcion2 == 1) {
						Pagado = true;
						salir3 = true;
					} else if (opcion2 == 2) {
						salir3 = true;
					} else {
						System.out.println("Seleccione una opcion valida");
					}
				}

				String Fecha = input("Ingrese la fecha del consumo");
				String NombreHuesped = input("Ingrese el nombre del huesped");
				float valor = Float.parseFloat(input("Ingrese el valor del consumo"));

				Consumo consumoRegistrado;
				try {
					consumoRegistrado = hotel.RegistrarConsumoHuesped(Fecha, NombreHuesped, area, valor, Pagado);
					System.out.println(
							"Consumo registrado satisfactoriamente con el id:" + consumoRegistrado.getIdentificador());
				} catch (Exception e) {
					System.err.println(e.getMessage());
					System.out.println("El consumo no se pudo registrar, intente nuevamente");

				}

				break;

			case 2:
				System.out.println("");
				break;

			case 3:
				salirmenu = true;
				System.out.println("Hasta pronto! " + usuarioActual.getLogin());
				break;

			default:
				System.out.println("");
				break;
			}
		}
	}

	private void menuAdministrador() {

		int opcion = 0;
		boolean salir = false;

		while (!salir) {
			System.out.println("------Menu Administrador------");
			System.out.println("Bienvenido, " + usuarioActual.getLogin());
			System.out.println("Por favor seleccione una opcion:");
			System.out.println("1. Subir archivo");
			System.out.println("2. Cambiar informacion individualmente");
			System.out.println("3. Volver al menu anterior");

			opcion = Integer.parseInt(input("Seleccione una opcion"));

			switch (opcion) {
			case 1:
				int opcionadmin = 0;

				while (opcionadmin != 4) {
					System.out.println("----------------------------");
					System.out.println("1. Cargar menú del restaurante");
					System.out.println("2. Cargar inventario de habitaciones");
					System.out.println("3. Cargar etc");
					System.out.println("4. Salir");
					opcionadmin = Integer.parseInt(input("Seleccione una opcion"));

						switch (opcionadmin) {
							case 1:
								cargarMenu();
								break;
							case 2:
								cargarInventarioHabitaciones();
								break;
							case 3:
								break;
							case 4:
								System.out.println("Regresando al menu...");
								break;
							default:
								System.out.println("Opción invalida, por favor seleccione una opción valida.");
						}

					}
					break;
				case 2:
					int opcionadmin2 = 0;

					while (opcionadmin2 != 4) {
						System.out.println("----------------------------");
						System.out.println("1. Modificar plato individual");
						System.out.println("2. Modificar una habitacion individual");
						System.out.println("3. Modificar tarifa por tipo de habitacion");
						System.out.println("4. Salir");
						opcionadmin2 = Integer.parseInt(input("Seleccione una opcion"));

						switch (opcionadmin2) {
							case 1:
								modificarAlimentoPrint();
								break;
							case 2:
								cargarInventarioHabitaciones();
								break;
							case 3:
								break;
							case 4:
								System.out.println("Regresando al menu...");
								break;
							default:
								System.out.println("Opción invalida, por favor seleccione una opción valida.");
								System.out.println("Se ha cambiado la informacion de un plato.");
								break;
						}
					}
				case 3:
					salir = true;
					System.out.println("Hasta pronto! " + usuarioActual.getLogin());
					break;
				default:
					System.out.println("Opcion no valida.");
					break;

			}
		}
	}

	public void modificarAlimentoPrint(){
		imprimirMenu(((Restaurante) hotel.getServicios().get("Restaurante")).getMenu());
		String id = input("Ingrese el Id del plato a modificar");
		Alimento nuevoAlimento = ((Restaurante) hotel.getServicios().get("Restaurante"))
				.buscarAlimento(id);
		if (nuevoAlimento != null) {
			System.out.println("1. Bebida");
			System.out.println("2. Plato");
			String tipo = input("Seleccione el nuevo tipo del plato");
			tipoAlimento tAlimento = tipoAlimento.PLATO;
			if (Integer.parseInt(tipo) == 1) {
				tAlimento = tipoAlimento.BEBIDA;
			} else if (Integer.parseInt(tipo) == 2) {
				tAlimento = tipoAlimento.PLATO;
			} else {
				System.out
						.println("Opción incorrecta, el alimento quedo como Plato por defecto");
			}
			String nombreString = input("Nuevo nombre del alimento");
			float NuevaTarifa = Float.parseFloat(input("Nueva tarifa del alimento"));
			System.out.println("1. Para restaurante");
			System.out.println("2. Para habitacion");
			String paraHabitacion = input("Seleccione la disponibilidad del plato");
			Boolean paraHabitacionBoolean = false;
			if (Integer.parseInt(paraHabitacion) == 1) {
				paraHabitacionBoolean = false;
			} else if (Integer.parseInt(paraHabitacion) == 2) {
				paraHabitacionBoolean = true;
			} else {
				System.out
						.println("Opción incorrecta, el alimento para restaurante por defecto");
			}
			System.out.println("1. Desayuno");
			System.out.println("2. Almuerzo");
			System.out.println("2. Cena");
			String Horario = input("Seleccione el horario de alimento");
			((Restaurante) hotel.getServicios().get("Restaurante")).modificarAlimento(
					nuevoAlimento,
					tAlimento, nombreString, NuevaTarifa, paraHabitacionBoolean, Horario);
			System.out.println("Alimento modificado satisfactoriamente");
			System.out.println(nuevoAlimento);
		} else {
			System.out.println("Alimento no encontrado");
		}
	}

	public void cargarMenu() {
		String Ruta = input("Escriba la ruta del archivo a subir");
		Restaurante restaurante = (Restaurante) hotel.getServicios().get("Restaurante");
		restaurante.CargarAlimentos(Ruta);
		System.out.println("Se está cargando el menú del restaurante...");
		System.out.println("Se han subido los archivos de alimentos.");
		System.out.println("MENU");
		imprimirMenu(restaurante.getMenu());

	}

	public static void imprimirTablaHabitaciones(HashMap<Integer, Habitacion> habitaciones) {
		System.out.printf("%-14s%-10s%-18s%-10s%-18s%-10s%-50s%-20s%-20s\n",
				"Identificador", "Capacidad", "Tipo", "Balcones", "Vista", "Cocinas", "Camas", "Huesped", "Ubicacion");

		for (Habitacion habitacion : habitaciones.values()) {
			String camasStr = "";
			for (Cama cama : habitacion.getCamas()) {
				camasStr += String.format("%d (%s, %d), ", cama.getIdentificador(), cama.getTamanio(),
						cama.getCapacidad());
			}
			if (camasStr.length() > 2) {
				camasStr = camasStr.substring(0, camasStr.length() - 2);
			}

			System.out.printf("%-14s%-10s%-18s%-10s%-18s%-10s%-50s%-20s%-20s\n",
					habitacion.getIdentificador(), habitacion.getCapacidad(),
					habitacion.getTipoHabitacion().toString(), habitacion.getBalcon(),
					habitacion.getVista(), habitacion.getCocinaIntegrada(),
					camasStr, habitacion.getHuespedActual() == null ? "" : habitacion.getHuespedActual().toString(),
					habitacion.getUbicacion());
		}
	}

	public void cargarInventarioHabitaciones() {
		String Ruta = input("Escriba la ruta del archivo a subir");
		hotel.cargarHabitaciones(Ruta);

		System.out.println("Se está cargando el inventario de habitaciones...");
		System.out.println("Se ha subido la informacion de las habitaciones.");
		System.out.println("INFORMACION DEL TOTAL DE HABITACIONES:");
		imprimirTablaHabitaciones(hotel.getTotalHabitaciones());
	}

	public void cargarEtc() {
		System.out.println("Se está cargando etc...");
		// Aquí iría el código para cargar etc
	}

	public static void imprimirMenu(ArrayList<Alimento> alimentos) {

		System.out.printf("%-15s %-10s %-20s %-10s %-15s %-10s\n",
				"Tipo", "Id", "Nombre", "Tarifa", "LugarDisponibilidad", "Horario");
		System.out.println("-----------------------------------------------------------------------------");
		for (Alimento alimento : alimentos) {
			System.out.printf("%-15s %-10s %-20s %-10s %-15s %-10s\n",
					alimento.getTipo(), alimento.getIdentificador(), alimento.getNombre(), alimento.getTarifa(),
					alimento.isLugarDisponibilidad() ? "Habitacion" : "Restaurante", alimento.getHorario());
		}
	}

	public void GuardarAplicacion(Hotel HotelGuardar) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream("datosApp.txt");
		ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(fileOutputStream);
		ObjectOutputStream.writeObject(HotelGuardar);
		try {
			ObjectOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Hotel CargarAplicacion() throws IOException, ClassNotFoundException {
		FileInputStream FileInputStream = new FileInputStream("datosApp.txt");
		ObjectInputStream ObjectInputStream = new ObjectInputStream(FileInputStream);
		Hotel HotelEnFichero = (Hotel) ObjectInputStream.readObject();
		ObjectInputStream.close();
		return HotelEnFichero;
	}

	public static void main(String[] args) {
		Aplicacion app = new Aplicacion();
		app.ejecutarOpcion();

	}

}
