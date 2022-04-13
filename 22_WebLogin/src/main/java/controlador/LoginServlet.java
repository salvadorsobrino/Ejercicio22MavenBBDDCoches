package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import modelo.entidad.Usuario;
import modelo.negocio.GestorUsuario;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "LoginServlet", urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("GET");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		// Permitir acceso desde distintos dominios
		response.addHeader("Access-Control-Allow-Origin", "*");

		JsonObject innerObject = new JsonObject();
		PrintWriter out = response.getWriter();

		String nombreUsuario = request.getParameter("nombreUsuario");
		String clave = request.getParameter("clave");

		GestorUsuario gu = new GestorUsuario();
		Usuario uExistente = gu.obtener(nombreUsuario); // Busca el usuario en la BBDD

		if (uExistente != null && clave.equals(uExistente.getClave())) {
			innerObject.addProperty("validado", "true");
		} else {
			innerObject.addProperty("validado", "false");
		}

		out.print(innerObject.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("POST");
		// La petición en el siguiente formato: application/x-www-form-urlencoded
		// (POSTMAN)
		// Utilizando en el body x-www-form-urlencoded escribimos como clave y valor lo
		// siguiente nombre=tony&password=1234
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		// Permitir acceso desde distintos dominios
		response.addHeader("Access-Control-Allow-Origin", "*");
		JsonObject innerObject = new JsonObject();
		PrintWriter out = response.getWriter();

		String nombreUsuario = request.getParameter("nombreUsuario");
		String clave = request.getParameter("clave");

		GestorUsuario gu = new GestorUsuario();
		Usuario uExistente = gu.obtener(nombreUsuario); // Busca el usuario en la BBDD

		if (uExistente != null && clave.equals(uExistente.getClave())) {
			innerObject.addProperty("validado", "true");
		} else {
			innerObject.addProperty("validado", "false");
		}

		out.print(innerObject.toString());

	}

}
