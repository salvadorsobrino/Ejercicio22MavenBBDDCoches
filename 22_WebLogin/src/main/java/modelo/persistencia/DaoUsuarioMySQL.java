package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.entidad.Usuario;
import modelo.persistencia.interfaces.DaoUsuario;

public class DaoUsuarioMySQL implements DaoUsuario{
	
	private Connection conexion;
	static{
		try {
			//Class.forName("com.mysql.jdbc.Driver"); 
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			System.out.println("Driver cargado");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver NO cargado");
			e.printStackTrace();
		}
	}
	public boolean abrirConexion(){
		
		String url = "jdbc:mysql://localhost:3306/usuarios";
		String usuario = "root";
		String password = "";
		try {
			conexion = DriverManager.getConnection(url,usuario,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public boolean alta(Usuario u) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		String query = "insert into usuario (NOMBREUSUARIO,CLAVE,EMAIL) values(?,?,?)";
		try {
			//preparamos la query con valores parametrizables(?)
			//nombreUsuario,clave,email
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, u.getNombreUsuario());
			ps.setString(2, u.getClave());
			ps.setString(3, u.getEmail());
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				alta = false;
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + u);
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		return alta;
	}

	@Override
	public boolean baja(String nombreUsuario) {
		if(!abrirConexion()){
			return false;
		}
		boolean borrado = true;
		String query = "delete from usuario where NOMBREUSUARIO = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			//sustituimos la primera interrogante por la id
			ps.setString(1, nombreUsuario);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
		} catch (SQLException e) {
			borrado = false;
			System.out.println("baja -> No se ha podido dar de baja a " + nombreUsuario);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return borrado; 
	}

	@Override
	public boolean modificar(Usuario u) {
		if(!abrirConexion()){
			return false;
		}
		boolean modificado = true;
		String query = "update usuario set NOMBREUSUARIO=?, CLAVE=?, EMAIL=? WHERE ID=?";
		//nombreUsuario,clave,email
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, u.getNombreUsuario());
			ps.setString(2, u.getClave());
			ps.setString(3, u.getEmail());
			ps.setInt(4, u.getId());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				modificado = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("modificar -> error al modificar el usuario " + u);
			modificado = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return modificado;
	}

	@Override
	public Usuario obtener(String nombreUsuario) {
		if(!abrirConexion()){
			return null;
		}		
		Usuario usuario = null;
		//nombreUsuario,clave,email
		String query = "select ID,NOMBREUSUARIO,CLAVE,EMAIL from usuario where nombreUsuario = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, nombreUsuario);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				usuario  = new Usuario();
				usuario.setId(rs.getInt(1));
				usuario.setNombreUsuario(rs.getString(2));
				usuario.setClave(rs.getString(3));
				usuario.setEmail(rs.getString(4));
			}
		} catch (SQLException e) {
			System.out.println("obtener -> error al obtener el usuario " + nombreUsuario);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return usuario;
	}

}
