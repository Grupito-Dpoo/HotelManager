package proyecto1implementacion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import proyecto1implementacion.Habitacion.tipoHabitacion;
import proyecto1implementacion.Servicio.areaAsociada;

public class Hotel implements Serializable {

	private String nombre;

	private ArrayList<Habitacion> habitacionesDisponibles;
	private HashMap<Integer, Habitacion> totalHabitaciones;
	private HashMap<String, Huesped> huespedes;
	private HashMap<Integer, Reserva> reservaciones;
	private HashMap<String, UsuarioSistema> empleados;
	private HashMap<String, Servicio> servicios;

	public Hotel(String nombre) {
		this.nombre = nombre;
		this.totalHabitaciones = new HashMap<>();
		this.habitacionesDisponibles = new ArrayList<>();
		this.huespedes = new HashMap<>();
		this.reservaciones = new HashMap<>();
		this.empleados = new HashMap<>();
		this.servicios = new HashMap<>();
		Restaurante restaurante = new Restaurante();
		this.servicios.put("Restaurante", restaurante);

	}

	public Huesped buscarHuesped(String nombreHuesped) {
		return huespedes.get(nombreHuesped);
	}

	public void agregarHuesped(String nombreHuesped, Huesped nuevoHuesped) {
		this.huespedes.put(nombreHuesped, nuevoHuesped);
	}

	public Consumo RegistrarConsumoHuesped(String Fecha, String nombreHuesped, areaAsociada areaAsociada,
			double valor, boolean Pagado) throws Exception {

		Huesped huesped = buscarHuesped(nombreHuesped);
		if (huesped == null) {
			throw new Exception("No existe huesped asociado al nombre");
		}
		
		Habitacion habitacionHuesped = huesped.getHabitacionAsociada();
		Consumo nuevoConsumo = new Consumo(Fecha, areaAsociada, huesped, habitacionHuesped, valor, Pagado);
		huesped.agregarConsumo(nuevoConsumo);
		GrupoHuespedes grupo = huesped.getGrupoAsociado();
		grupo.agregarConsumo(nuevoConsumo);
		
		return nuevoConsumo;
	}

	public void agregarReservacion(Reserva nuevaReservacion) {
		this.reservaciones.put(nuevaReservacion.getIdentificador(), nuevaReservacion);
	}

	public Habitacion buscarHabitacion(Integer identificadorHabitacion) {
		return totalHabitaciones.get(identificadorHabitacion);
	}

	public Reserva buscarReservacion(Integer identificadorHabitacion) {
		return reservaciones.get(identificadorHabitacion);
	}

	public void registrarNuevoEmpleado(String login, String password, String tipoEmpleado) throws Exception {
		UsuarioSistema nuevoEmpleado = empleados.get(login);

		if (nuevoEmpleado != null) {
			throw new Exception("Este nombre de usuario ya fue registrado.");
		}

		if (login.isEmpty()) {
			throw new Exception("Nombre de usuario invalido.");
		}

		if (password.isEmpty()) {
			throw new Exception("Contraseña invalida.");
		}

		if (tipoEmpleado.equals("recepcionista")) {

			nuevoEmpleado = new EmpleadoRecepcion(login, password, this);
			empleados.put(login, nuevoEmpleado);

		}

		else if (tipoEmpleado.equals("empleado")) {
			nuevoEmpleado = new Empleado(login, password, this);
			empleados.put(login, nuevoEmpleado);
		}

		else {

			throw new Exception("El tipo de empleado no es válido.");

		}

	}

	public HashMap<Integer, Habitacion> cargarHabitaciones(String nombreArchivo) {
		HashMap<Integer, Habitacion> habitaciones = new HashMap<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo));
			String line;
			Habitacion habitacionActual = null;
			while ((line = reader.readLine()) != null) {
				String[] partes = line.split(";");
				if (partes[0].equals("Habitacion")) {
					tipoHabitacion typeHabitacion = tipoHabitacion.ESTANDAR;
					if (partes[1].equals("Suite")){
						typeHabitacion = tipoHabitacion.SUITE;
					} else if (partes[1].equals("Suite Doble")){
						typeHabitacion = tipoHabitacion.SUITEDOBLE;
					} 
					int balcones = Integer.parseInt(partes[2]);
					String vista = partes[3];
					int cocinaIntegrada = Integer.parseInt(partes[4]);
					String ubicacion = partes[5];
					ArrayList<Cama> camas = new ArrayList<>();
					habitacionActual = new Habitacion(typeHabitacion, balcones, vista, cocinaIntegrada, camas, ubicacion);
					habitaciones.put(habitacionActual.getIdentificador(), habitacionActual);
				} else if (partes[0].equals("Cama")) {
					String tamanio = partes[1];
					int capacidad = Integer.parseInt(partes[2]);
					Cama cama = new Cama(tamanio, capacidad);
					habitacionActual.getCamas().add(cama);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return habitaciones;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Habitacion> getHabitacionesDisponibles() {
		return habitacionesDisponibles;
	}

	public void setHabitacionesDisponibles(ArrayList<Habitacion> habitacionesDisponibles) {
		this.habitacionesDisponibles = habitacionesDisponibles;
	}

	public HashMap<String, Huesped> getHuespedes() {
		return huespedes;
	}

	public void setHuespedes(HashMap<String, Huesped> huespedes) {
		this.huespedes = huespedes;
	}

	public HashMap<String, UsuarioSistema> getEmpleados() {
		return empleados;
	}

	public UsuarioSistema getEmpleadoByLogin(String login) {

		return empleados.get(login);

	}

	public void setEmpleados(HashMap<String, UsuarioSistema> empleados) {
		this.empleados = empleados;
	}

	public HashMap<String, Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(HashMap<String, Servicio> servicios) {
		this.servicios = servicios;
	}

	public HashMap<Integer, Habitacion> getTotalHabitaciones() {
		return totalHabitaciones;
	}

	public void setTotalHabitaciones(HashMap<Integer, Habitacion> totalHabitaciones) {
		this.totalHabitaciones = totalHabitaciones;
	}

	public HashMap<Integer, Reserva> getReservaciones() {
		return reservaciones;
	}

	public void setReservaciones(HashMap<Integer, Reserva> reservaciones) {
		this.reservaciones = reservaciones;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Hotel{");
		sb.append("nombre=").append(nombre);
		sb.append(", habitacionesDisponibles=").append(habitacionesDisponibles);
		sb.append(", totalHabitaciones=").append(totalHabitaciones);
		sb.append(", huespedes=").append(huespedes);
		sb.append(", reservaciones=").append(reservaciones);
		sb.append(", empleados=").append(empleados.keySet());
		sb.append(", servicios=").append(servicios.keySet());
		sb.append('}');
		return sb.toString();
	}

}
