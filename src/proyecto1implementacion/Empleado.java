package proyecto1implementacion;

import proyecto1implementacion.Servicio.areaAsociada;

public class Empleado extends UsuarioSistema {

	
	
	public Empleado(String login, String password, Hotel hotel) {
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
	
    private areaAsociada AreaAsociada;

    private void RegistrarConsumoHuesped(String nombreHuesped) {
        
    }
    private void RegistrarPago(String nombreHuesped) {
    }

}
