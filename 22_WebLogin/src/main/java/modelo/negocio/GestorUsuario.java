package modelo.negocio;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import modelo.entidad.Usuario;
import modelo.persistencia.DaoUsuarioMySQL;
import modelo.persistencia.interfaces.DaoUsuario;

public class GestorUsuario {
	private DaoUsuario daoUsuario = new DaoUsuarioMySQL();

	/**
	 * Metodo que da de alta un usuario en la base de datos. El nombre del usuario
	 * debe de tener al menos 3 caracteres. El email contener @ y la clave mas de 3
	 * caracteres
	 * 
	 * @param u el usuario a dar de alta
	 * @return 0 en caso de que hayamos dado de alta el usuario 1 en caso de algun
	 *         error de conexion con la bbdd 2 en caso de que nombreusuario o clave
	 *         tenga menos de 3 caracteres o no tenga @ en el email
	 * 
	 */
	public int alta(Usuario u) {
		if (u.getNombreUsuario().length() >= 3 && u.getClave().length() >= 3 && u.getEmail().contains("@")) {
			boolean alta = daoUsuario.alta(u);
			if (alta) {
				return 0;
			} else {
				return 1;
			}
		} else {
			return 2;
		}
	}

	public boolean baja(String nombreUsuario) {
		boolean baja = daoUsuario.baja(nombreUsuario);
		return baja;
	}

	/**
	 * Metodo que da modifica un usuario en base de datos. El nombre de la persona
	 * debe de tener al menos 3 caracteres. La modificarcion sera a partir del id
	 * del usuario
	 * 
	 * @param u el usuario a modificar. Dentro tiene que tener el id
	 * @return 0 en caso de que hayamos modificado el usuario 1 en caso de algun
	 *         error de conexion con la bbdd 2 en caso de que nombreusuario o clave
	 *         tenga menos de 3 caracteres o no tenga @ en el email
	 */
	public int modificar(Usuario u) {
		// aplicamos la regla de negocio
		if (u.getNombreUsuario().length() >= 3 && u.getClave().length() >= 3 && u.getEmail().contains("@")) {
			boolean modificada = daoUsuario.modificar(u);
			if (modificada) {
				return 0;
			} else {
				return 1;
			}
		}
		return 2;
	}

	public Usuario obtener(String nombreUsuario) {
		Usuario usuario = daoUsuario.obtener(nombreUsuario);
		return usuario;
	}

	public boolean validarUsuario(Usuario u) throws Exception {
		JsonObject json = null;
		try {
			// Consumimos el servicio web
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI("http://localhost:8080/21_WebLogin/LoginServlet?nombreUsuario=" + u.getNombreUsuario()
							+ "&clave=" + u.getClave()))
					.GET().build();

			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			json = JsonParser.parseString(response.body()).getAsJsonObject();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.get("validado").getAsBoolean();
	}
}
