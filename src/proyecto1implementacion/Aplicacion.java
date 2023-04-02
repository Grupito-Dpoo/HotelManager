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
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import proyecto1implementacion.Servicio.areaAsociada;

public class Aplicacion {

	private Hotel hotel;
	private UsuarioSistema usuarioActual;
	private File archivoUsuarios = new File("data/Usuarios.csv");

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

				} catch (Exception e) {

					System.err.println(e.getMessage());
				}
				}
			else if (opcion_seleccionada == 2) {
				try {
					GuardarAplicacion(hotel);
				} catch (Exception e) {

					System.err.println(e.getMessage());
				}
				}
			else if (opcion_seleccionada == 3) {
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
				while(salir2 != true){		
								
					System.out.println("Seleccione el area de consumo asociada:");
					System.out.println("1. SPA");
					System.out.println("2. GUIA TURISTICO");
					System.out.println("3. RESTAURANTE");

					if (opcion2 == 1){
						area = areaAsociada.SPA;
						salir2 = true;
					}
					else if (opcion2 == 2){
						area = areaAsociada.GUIATURISTICO;
						salir2 = true;
					}
					else if (opcion2 == 3){
						area = areaAsociada.RESTAURANTE;
						salir2 = true;
					}
					else{
						System.out.println("Opcion no disponible, por favor seleccione una opcion valida");
					}}

				while(salir3 != true){		
							
					System.out.println("¿El consumo ya fue pagado?");
					System.out.println("1. Si, ya fue pagado");
					System.out.println("2. No, queda registrado");
					opcion2 = scanner.nextInt();

					if (opcion2 == 1){
						Pagado = true;
						salir3 = true;
					}
					else if (opcion2 == 2){
						salir3 = true;
					}
					else {
						System.out.println("Seleccione una opcion valida");
					}}

					String Fecha = input("Ingrese la fecha del consumo");
					String NombreHuesped = input("Ingrese el nombre del huesped");
					float valor = Float.parseFloat(input("Ingrese el valor del consumo"));
					
					Consumo consumoRegistrado = hotel.RegistrarConsumoHuesped(Fecha, NombreHuesped, area, valor, Pagado);
				
					if (consumoRegistrado != null){
						System.out.println("Consumo registrado satisfactoriamente con el id:" +consumoRegistrado.getIdentificador());
					} 
					else{
						System.out.println("El consumo no se pudo registrar, intente nuevamente");
					}
					break;

				case 2:
					System.out.println("");
					break;

				case 3:
				salirmenu = true;
					System.out.println("Hasta pronto! "+usuarioActual.getLogin());
					break;
					
				default:
					System.out.println("");
					break;
}}}

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
						String Ruta = input("Escriba la ruta del archivo a subir");
						Restaurante restaurante = (Restaurante) hotel.getServicios().get("Restaurante");
						restaurante.CargarAlimentos(Ruta);
						System.out.println("Se han subido los archivos de alimentos.");
						System.out.println("MENU");
						imprimirMenu(restaurante.getMenu());
						break;
					case 2:
						// Código para cambiar información de un plato
						System.out.println("Se ha cambiado la informacion de un plato.");
						break;
					case 3:
						salir = true;
						System.out.println("Hasta pronto! "+usuarioActual.getLogin());
						break;
					default:
						System.out.println("Opcion no valida.");
						break;
				}
			}
	}

	public static void imprimirMenu(ArrayList<Alimento> alimentos) {
			
		System.out.printf("%-15s %-20s %-10s %-20s %-15s\n",
		"Tipo", "Nombre", "Tarifa", "LugarDisponibilidad", "Horario");
		System.out.println("--------------------------------------------------------------");
		for (Alimento alimento : alimentos) {
				System.out.printf("%-15s %-20s %-10.2f %-20s %-15s\n",
					alimento.getTipo(), alimento.getNombre(), alimento.getTarifa(),
					alimento.isLugarDisponibilidad() ? "Habitacion" : "Restaurante", alimento.getHorario());
			}
		}
		
	public void GuardarAplicacion(Hotel HotelGuardar) throws IOException{
		FileOutputStream fileOutputStream = new FileOutputStream("datosApp.txt");
        ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(fileOutputStream);
        ObjectOutputStream.writeObject(HotelGuardar);
        try {
			ObjectOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public Hotel CargarAplicacion() throws IOException, ClassNotFoundException{
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

	





	// Debería haber una opcion para cambiar de usuario o algo así?
}
