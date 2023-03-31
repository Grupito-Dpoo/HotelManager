package proyecto1implementacion;

import java.io.Serializable;

public class Administrador extends UsuarioSistema implements Serializable{
	
	public Administrador(String login, String password, Hotel hotel) {
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
	
	private void SubirArchivoHabitaciones(){
    } 
    private void SubirArchivoTipoCuarto(){
    } 
    private void SubirArchivoInfoServicios(){
    } 
    
    
}
