package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidades.Medico;

public class MedicoDAO implements InterMedicoDAO{

	@Override
	public void guardar(Medico med) throws DAOException {
		//INSERTAR (INSERT)
        try {
        	//1.-Levantar el driver de la base de datos
			Class.forName("org.h2.Driver");
			
			//2.-Conectar a la base de datos
			Connection con1 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			
			//3.-Operar sobre la base de datos. Ya sea consultar o modificar datos. 
			Statement statement = con1.createStatement();
			
			//4.-Ingresar datos
			int cantidadRegistros = statement.executeUpdate("INSERT INTO MEDICO VALUES(" + med.getLegMedico() + ", '" + med.isCobra() + "')");

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
	public void eliminar(int legMedico) throws DAOException {
		//DELETE
        try {
        	//1.-Levantar el driver de la base de datos
			Class.forName("org.h2.Driver");
			
			//2.-Conectar a la base de datos
			Connection con4 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			
			//3.-Creamos sentencia SQL 
			Statement statement4 = con4.createStatement();
			
			//4.-Evaluamos consulta
			int cantidadRegistrosEliminados = statement4.executeUpdate("DELETE MEDICOS WHERE LEGAJO = " + legMedico);
			
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
	public void modificar(Medico med) throws DAOException {
		//MODIFICAR (UPDATE) 
        try {
        	//1.-Levantar el driver de la base de datos
			Class.forName("org.h2.Driver");
			
			//2.-Conectar a la base de datos
			Connection con3 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			
			//3.-Creamos sentencia SQL 
			Statement statement3 = con3.createStatement();
			
			//4.-Evaluamos consulta
			int cantidadRegistrosModificados = statement3.executeUpdate("UPDATE MEDICOS SET COBRA = '" + med.isCobra() + "' WHERE LEGAJO = " + med.getLegMedico());
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
	public Medico recuperar(int legMedico) throws DAOException {
		
		Medico med = new Medico(); //como me va a recuperar un medico voy a tener que instanciar antes un medico
		
		//Select *
        try {
        	//1.-Levantar el driver de la base de datos
			Class.forName("org.h2.Driver");
			
			//2.-Conectar a la base de datos
			Connection con5 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			
			//3.-Creamos sentencia SQL 
			Statement statement5 = con5.createStatement();
			
			//4.-Evaluamos consulta
			ResultSet resultSet2 = statement5.executeQuery("SELECT * FROM MEDICOS WHERE LEGAJO = " + legMedico);
			
			while(resultSet2.next()) {
				med.setLegMedico(resultSet2.getInt("LEGAJO"));
				med.setCobra(resultSet2.getBoolean("COBRA"));
			}
			
			con5.close();
			
			
			//excepcion del paso 1
		} catch (ClassNotFoundException e) {
			throw new DAOException("DAO.Recuperar: " + e.getMessage());
			
			//excepcion del paso 2
		} catch (SQLException throwables) {
			throw new DAOException("DAO.Recuperar: " + throwables.getMessage());
		}
        
        return med;
	}

	@Override
	public List<Medico> recuperarTodos() throws DAOException {
		List<Medico> medico = new ArrayList<Medico>(); //aca devolvemos una lista de usuarios
		
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
				
				Medico med = new Medico(); //cada vez que encuentro un registro, en realidad voy a instanciar un medico nuevo y se agrega a la coleccion, y van a ser tantos medicos como registro devuelva ésta linea
				
				med.setLegMedico(resultSet.getInt("LEGAJO"));
				med.setCobra(resultSet.getBoolean("COBRA"));
				
				medico.add(med);
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
        
		return medico;  
	}

}
