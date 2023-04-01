package proyecto1implementacion;

import java.io.Serializable;

public class UsuarioSistema implements Serializable{

	private String login;
    private String password;
    private Hotel hotel;
    
    public UsuarioSistema(String login, String password, Hotel hotel) {
		this.login = login;
		this.password = password;
		this.hotel = hotel;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public Hotel getHotel() {
		return hotel;
	}
    
}
