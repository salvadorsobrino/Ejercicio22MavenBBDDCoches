package modelo.persistencia.interfaces;
//Esta interfaz define un CRUD para el objeto Usuario

import modelo.entidad.Usuario;

public interface DaoUsuario {
	public boolean alta(Usuario u);
	public boolean baja(String nombreUsuario);
	public boolean modificar(Usuario u);
	public Usuario obtener(String nombreUsuario);
}
