package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidades.Usuario;

public class UsuarioDAO implements InterUsuarioDAO{

	@Override
	public void guardar(Usuario usu) throws DAOException {
		//INSERTAR (INSERT)
        try {
        	//1.-Levantar el driver de la base de datos
			Class.forName("org.h2.Driver");
			
			//2.-Conectar a la base de datos
			Connection con1 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			
			//3.-Operar sobre la base de datos. Ya sea consultar o modificar datos. 
			Statement statement = con1.createStatement();
			
			//4.-Ingresar datos
			int cantidadRegistros = statement.executeUpdate("INSERT INTO USUARIOS VALUES(" + usu.getDni() + ", '" + usu.getPassword() + "', '" + usu.getNya() + "', '" + usu.getFecha_nac() + "', '" + usu.getObraSocial() + "', '" + usu.getTipo_usu() + "')");

			//5.-Evaluar resultado
			if(cantidadRegistros > 0) {
				System.out.println("Se ha guardado un nuevo registro.");
				
				con1.close(); //si no la cerramos la coneccion sigue abierta
			}
			
			
			//excepcion del paso 1
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("DAO.Guardar: " + e.getMessage());
			
			//excepcion del paso 2
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			throw new DAOException("DAO.Guardar: " + throwables.getMessage());
		}
	}

	@Override
	public void eliminar(int dni) throws DAOException{
		//DELETE
        try {
        	//1.-Levantar el driver de la base de datos
			Class.forName("org.h2.Driver");
			
			//2.-Conectar a la base de datos
			Connection con4 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			
			//3.-Creamos sentencia SQL 
			Statement statement4 = con4.createStatement();
			
			//4.-Evaluamos consulta
			int cantidadRegistrosEliminados = statement4.executeUpdate("DELETE USUARIOS WHERE DNI = " + dni);
			
			//5.-Evaluar resultado
			if(cantidadRegistrosEliminados > 0) {
				System.out.println("Se eliminado el registro.");
				
				con4.close(); //si no la cerramos la coneccion sigue abierta
			}
			
			
			//excepcion del paso 1
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("DAO.Eliminar: " + e.getMessage());
			
			//excepcion del paso 2
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			throw new DAOException("DAO.Eliminar: " + throwables.getMessage());
		}
	}

	@Override
	public void modificar(Usuario usu) throws DAOException{
		//MODIFICAR (UPDATE) 
        try {
        	//1.-Levantar el driver de la base de datos
			Class.forName("org.h2.Driver");
			
			//2.-Conectar a la base de datos
			Connection con3 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			
			//3.-Creamos sentencia SQL 
			Statement statement3 = con3.createStatement();
			
			//4.-Evaluamos consulta
			int cantidadRegistrosModificados = statement3.executeUpdate("UPDATE USUARIOS SET PASSWORD = '" + usu.getPassword() + "', NYA = '" + usu.getNya() + "', FECHA_NAC = '" + usu.getFecha_nac() + "', OBRASOCIAL = '" + usu.getObraSocial() + "', TIPO_USU = '" + usu.getTipo_usu() + "' WHERE DNI = " + usu.getDni());
																		//("UPDATE USUARIOS SET NYA = 'Federico Ruben' WHERE DNI = 1")
			//5.-Evaluar resultado
			if(cantidadRegistrosModificados > 0) {
				System.out.println("Se modifico el registro.");
				
				con3.close(); //si no la cerramos la coneccion sigue abierta
			}
			
			
			//excepcion del paso 1
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("DAO.Modificar: " + e.getMessage());
			
			//excepcion del paso 2
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			throw new DAOException("DAO.Modificar: " + throwables.getMessage());
		}		
	}

	@Override
	public Usuario recuperar(int dni) throws DAOException{
		
		Usuario usu = new Usuario(); //como me va a recuperar un usuario voy a tener que instanciar antes un usuario
		
		//Select *
        try {
        	//1.-Levantar el driver de la base de datos
			Class.forName("org.h2.Driver");
			
			//2.-Conectar a la base de datos
			Connection con5 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			
			//3.-Creamos sentencia SQL 
			Statement statement5 = con5.createStatement();
			
			//4.-Evaluamos consulta
			ResultSet resultSet2 = statement5.executeQuery("SELECT * FROM USUARIOS WHERE DNI = " + dni);
			
			while(resultSet2.next()) {
				usu.setDni(resultSet2.getInt("DNI"));
				usu.setPassword(resultSet2.getString("PASSWORD"));
				usu.setNya(resultSet2.getString("NYA"));
				usu.setFecha_nac(resultSet2.getString("FECHA_NAC"));
				usu.setObraSocial(resultSet2.getString("OBRASOCIAL"));
				usu.setTipo_usu(resultSet2.getInt("TIPO_USU"));
			}
			
			con5.close();
			
			
			//excepcion del paso 1
		} catch (ClassNotFoundException e) {
			throw new DAOException("DAO.Recuperar: " + e.getMessage());
			
			//excepcion del paso 2
		} catch (SQLException throwables) {
			throw new DAOException("DAO.Recuperar: " + throwables.getMessage());
		}
        
        return usu;
	}

	@Override
	public List<Usuario> recuperarTodos() throws DAOException{
		
		List<Usuario> usuarios = new ArrayList<Usuario>(); //aca devolvemos una lista de usuarios
		
		//SELECCIONAR (SELECT)
        try {
        	//1.-Levantar el driver de la base de datos
        	Class.forName("org.h2.Driver");
		
		
			//2.-Conectar a la base de datos
			Connection con2 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");;
			
			
			//3.-Operar sobre la base de datos. Ya sea consultar o modificar datos. 
			Statement statement2 = con2.createStatement();
			
			//4.-Ingresar datos
			ResultSet resultSet = statement2.executeQuery("SELECT * FROM USUARIOS"); 
						
			while(resultSet.next()) {
				
				Usuario usu = new Usuario(); //cada vez que encuentro un registro, en realidad voy a instanciar un usuario nuevo y se agrega a la coleccion, y van a ser tantos usuarios como registro devuelva ésta linea
				
				usu.setDni(resultSet.getInt("DNI"));
				usu.setPassword(resultSet.getString("PASSWORD"));
				usu.setNya(resultSet.getString("NYA"));
				usu.setFecha_nac(resultSet.getString("FECHA_NAC"));
				usu.setObraSocial(resultSet.getString("OBRASOCIAL"));
				usu.setTipo_usu(resultSet.getInt("TIPO_USU"));
				
				usuarios.add(usu);
			}
			
			//excepcion del paso 1
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("DAO.REcuperarTodos: " + e.getMessage());
			
			//excepcion del paso 2
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			throw new DAOException("DAO.RecuperarTodos: " + throwables.getMessage());
		}
        
		return usuarios;  
	}
}
