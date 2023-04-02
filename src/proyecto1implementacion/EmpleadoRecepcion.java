package proyecto1implementacion;

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

}
