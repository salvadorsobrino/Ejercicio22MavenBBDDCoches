package modelo.persistencia.interfaces;

import java.util.List;
import modelo.entidad.Coche;

public interface DAOCocheInterfaz {
	
	boolean altaCoche(Coche c); //Alta de coche
	boolean eliminarCoche(int id); //Eliminar coche por id
	boolean modificarCoche(Coche c); //Modificar coche por id
	Coche buscarCocheId(int id); //Buscar coche por id
	Coche buscarCocheMatricula(String matricula); //Buscar coche por matricula
	List<Coche> buscarCocheMarca(String marca); //Buscar coches por marca
	List<Coche> buscarCocheModelo(String modelo); //Buscar coches por modelo
	List<Coche> listarCoches(); //Listar todos los coches
}