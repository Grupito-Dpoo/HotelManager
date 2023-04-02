package proyecto1implementacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

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
	float valor, boolean Pagado) {
		
		Huesped huesped = buscarHuesped(nombreHuesped);
		if (huesped != null) {
		Habitacion habitacionHuesped = huesped.getHabitacionAsociada();
		Consumo nuevoConsumo = new Consumo(Fecha, areaAsociada, huesped, habitacionHuesped, valor, Pagado);
		huesped.agregarConsumo(nuevoConsumo.getIdentificador(), nuevoConsumo);
		return nuevoConsumo;
		}
		else {
			return null;
		}
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

	public HashMap<String,Servicio> getServicios() {
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
        sb.append(", empleados=").append(empleados);
        sb.append(", servicios=").append(servicios);
        sb.append('}');
        return sb.toString();
	}


}
