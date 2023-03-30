package proyecto1implementacion;

import java.util.ArrayList;
import java.util.HashMap;

public class EmpleadoRecepcion extends UsuarioSistema {

	public EmpleadoRecepcion(String login, String password, Hotel hotel) {
		super(login, password, hotel);

	}

	public String getLogin() {
		return super.getLogin();
	}
	
	public String getPassword() {
		return super.getPassword();
	}
	
	public Hotel getHotel() {
		return super.getHotel();
	}
	
	
    private void CancelarReserva(Huesped Huesped) {
    }

    
    private void VerInventarioHabitaciones() {
    }

    private void GenerarFacturas() {
    }

    private void Registro(ArrayList<HashMap<String, String>> DatosHuespedes) {
    }

}
