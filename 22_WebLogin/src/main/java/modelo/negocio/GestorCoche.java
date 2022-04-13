package modelo.negocio;
import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.CocheDAOmySQL;
import modelo.persistencia.interfaces.DAOCocheInterfaz;

/*
 * Tendremos las siguientes reglas de negocio: 
 * La matricula tendr� una longitud de 7
 * No puede haber dos matriculas repetidas
 * Los kilometros no podr�n ser negativos 
*/

public class GestorCoche {
	
	private DAOCocheInterfaz daoCocheI = new CocheDAOmySQL();
	
	/** Metodo que da de alta un coche en base de datos.
	 * La matricula debe tener una longitud de 7
	 * @param c el coche a dar de alta 
	 * @return 0 en caso de que hayamos dado de alta el coche, 1 en caso
	 * de algun error de conexi�n con la bbdd y 2 en caso de que la matricula no tenga 7 caracteres
	 * 
	 */
	public int alta(Coche c){
		Coche coche = daoCocheI.buscarCocheMatricula(c.getMatricula());
		if(c.getMatricula().length() == 7 && coche==null && c.getKilometros()>=0) {
			boolean alta = daoCocheI.altaCoche(c);
			if(alta) {
				return 0;
			}else {
				return 1;
			}
		}else {
			return 2;
		}
	}
	
	public boolean baja(int id){
		boolean baja = daoCocheI.eliminarCoche(id);
		return baja;
	}
	public int modificar(Coche c){ //Modificar coche por id!!
		//Aplicamos la regla de negocio 
		if(c.getMatricula().length() == 7 && c.getKilometros()>=0) {
			boolean modificada = daoCocheI.modificarCoche(c);
			if(modificada) {
				return 0;
			}else {
				return 1;
			}
		}
		return 2;
	}
	public Coche buscarId(int id){
		Coche coche = daoCocheI.buscarCocheId(id);
		return coche;
	}
	public Coche buscarMatricula(String matricula){
		Coche coche = daoCocheI.buscarCocheMatricula(matricula);
		return coche;
	}
	public List<Coche> buscarMarca(String marca){
		List<Coche> listaPersonas = daoCocheI.buscarCocheMarca(marca);
		return listaPersonas;
	}
	public List<Coche> buscarModelo(String modelo){
		List<Coche> listaPersonas = daoCocheI.buscarCocheModelo(modelo);
		return listaPersonas;
	}
	public List<Coche> listar(){
		List<Coche> listaPersonas = daoCocheI.listarCoches();
		return listaPersonas;
	}
}
