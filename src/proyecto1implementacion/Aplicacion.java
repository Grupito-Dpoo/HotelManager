package proyecto1implementacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;

public class Aplicacion {

	private Hotel hotel;
	private UsuarioSistema usuarioActual;
	private File archivoUsuarios = new File("data/Usuarios.csv");
	Restaurante restaurante = new Restaurante();

	public void ejecutarOpcion() {
		hotel = new Hotel("House System");
		hotel.addServicios(restaurante);

		try {
			cargarUsuarios(archivoUsuarios);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		boolean continuar = true;

		while (continuar && usuarioActual == null) {

			mostrarMenu();
			int opcion_seleccionada = Integer.parseInt(input("\nPor favor seleccione una opcion"));
			if (opcion_seleccionada == 1) {
				try {
					registrarNuevoEmpleado();
				} catch (Exception e) {

					System.err.println(e.getMessage());
				}
			} else if (opcion_seleccionada == 2) {
				try {
					iniciarSesionEmpleado();
					//menuUsuario();
				} catch (Exception e) {

					System.err.println(e.getMessage());
				}
			} else if (opcion_seleccionada == 3) {
				System.out.println("\nSaliendo de la aplicacion ...");
				continuar = false;
			} else {
				System.out.println("\nPor favor seleccione una opción valida.");
			}
		}
	}

	public void mostrarMenu() {

		String nombre = hotel.getNombre();

		System.out.println("Bienvenido al hotel " + nombre);
		System.out.println("1. Registrar nuevo empleado");
		System.out.println("2. Iniciar sesión empleado");
		System.out.println("3. Salir");
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
		System.out.println("Has iniciado sesión correctamente.");
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

	private void menuUsuario(){
		if (usuarioActual instanceof Administrador){
			menuAdministrador();
		}
		else if (usuarioActual instanceof Empleado){
			menuEmpleado();
		}
		else if (usuarioActual instanceof EmpleadoRecepcion){
			menuEmpleadoRecepcion();
		}
	}
	
	
	private void menuEmpleadoRecepcion() {
	}

	private void menuEmpleado() {
	}

	private void menuAdministrador() {
		
			int opcion = 0;
			boolean salir = false;
			
			while (!salir) {
				System.out.println("------Menu Administrador------");
				System.out.println("Bienvenido, " + usuarioActual.getLogin());
           		System.out.println("Por favor seleccione una opción:");
				System.out.println("1. Subir archivo");
				System.out.println("2. Cambiar informacion individualmente");
				System.out.println("3. Cambiar de usuario");
				System.out.println("4. Salir");
			
				opcion = Integer.parseInt(input("Seleccione una opción"));
				
				switch (opcion) {
					case 1:
						String Ruta = input("Escriba la ruta del archivo a subir");
						restaurante.CargarAlimentos(Ruta);

						//subir archivos de platos
						System.out.println("Se han subido los archivos de platos.");
						break;
					case 2:
						// Código para cambiar información de un plato
						System.out.println("Se ha cambiado la información de un plato.");
						break;
					case 3:
						// Código para cambiar de usuario
						System.out.println("Se ha cambiado de usuario.");
						break;
					case 4:
						salir = true;
						System.out.println("Hasta pronto! "+usuarioActual.getLogin());
						break;
					default:
						System.out.println("Opción no valida.");
						break;
				}
			}
	}

	public static void main(String[] args) {
		Aplicacion app = new Aplicacion();
		app.ejecutarOpcion();

	}

	// Debería haber una opcion para cambiar de usuario o algo así?
}
