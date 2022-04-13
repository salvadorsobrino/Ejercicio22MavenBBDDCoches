package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.entidad.Usuario;
import modelo.negocio.GestorUsuario;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"} )
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombreUsuario = request.getParameter("nombreUsuario");
		String clave = request.getParameter("clave");
		String email = request.getParameter("correo");

		GestorUsuario gu = new GestorUsuario();
		Usuario u = new Usuario();
		u.setNombreUsuario(nombreUsuario);
		u.setClave(clave);
		u.setEmail(email);
		
		int alta = gu.alta(u);
		if(alta == 0) {
			System.out.println("Usuario dado de alta");
		}else if(alta == 1) {
			System.out.println("Error de conexion con la BBDD");
		}else if(alta == 2){
			System.out.println("El usuario o contrasenia tiene menos de tres caracteres o el email no contiene @");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
