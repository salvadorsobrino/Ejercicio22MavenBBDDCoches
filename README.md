1a PARTE
--------

Hacer un servicio web '21_WebLogin' (con servlets) que diga a las demás aplicaciones que lo consumen si 
un usuario esta autenticado.

Un usuario esta autenticado si su usuario y password son correcto. Los usuarios estarán almacenados
en una tabla de base de datos.

Para consumir el servicio la URL del recurso sera GET '/usuarios/login' y se pasarán los datos del usuario
por parametros de query. Ej: /usuarios/login?nombre=tony&password=1234

El servicio enviará un objeto json con la respuesta con el siguiente formato

{"validado":true}
o
{"validado":false}

La aplicación debe estar construtida con capas vista en clase.

	- Capa controladora (Servlet)
	- Capa de modelo 
		- entidades
		- negocio
		- Dao o acceso a datos	

Para probar esto podéis utilizar un navegador o postman

Posible mejora: Se quiere enviar los datos dentro del body de la petición HTTP para incrementar la
seguridad de la aplicacion. Ej:

	Linea de petición: POST /usuarios/login
	body de petición: nombre=tony&password=1234
	
Para probar esto podéis utilizar postman

2º Parte

Vamos a usar alguna aplicación que hemos construido para probar dicho servicio.

1) Al arrancar la aplicación del ejercicio 20 y antes de mostrar ningun tipo de menu, la aplicación
nos pedira un usuario y un password para validarlo con el servicio web que hemos creado antes.
(HTTP cliente Java 11). Podemos hacer un proyeco nuevi (22)

Si el usuario esta "validado" nos dejará entrar en la aplicación y nos mostrará el menu. En caso
contrario, la aplicación nos dirá que el usuario y el password no es correcto y nos permitira 
inserta otra vez el usuario y el password. La aplicación hará esta funcionalidad un maximo de 3
veces antes de acabar el programa. Hacerlo tambien por capas.

Posible mejora: Hacer la peticion consumiendo "POST /usuarios/login"

2) Repetiremos lo mismo pero para el ejercicio de Angular. OJO AQUI CON EL XSS, tendremos que poner
en la peticion cabeceras que permitan hacer la petición al servicio. Tendreis que meter alguna cabecera
en la request que permita.  Otro proyecto nuevo (23)

https://www.stackhawk.com/blog/angular-xss-guide-examples-and-prevention/

Creación de tabla:

CREATE TABLE usuario ( id int(11) NOT NULL AUTO_INCREMENT,
                      nombreUsuario varchar(45) NOT NULL, 
                      clave varchar(45) NOT NULL, 
                      email varchar(100) NOT NULL, 
                      PRIMARY KEY (id),
                      UNIQUE KEY unique_nombreUsuario (nombreUsuario),
                      UNIQUE KEY unique_clave (clave),
                      UNIQUE KEY unique_email (email)
                     );
GET:
http://localhost:8080/21_WebLogin/LoginServlet?nombreUsuario=tony&clave=1234
POST:
http://localhost:8080/21_WebLogin/LoginServlet?
Utilizando application/x-www-form-urlencoded y de clave-valor: nombre=tony&password=1234