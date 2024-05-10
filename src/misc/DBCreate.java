package misc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import dao.DBManager;

public class DBCreate {
	
	public void crearTablaUsuarios() throws SQLException{
		try {
			Connection c = DBManager.connect();
	        Statement s = c.createStatement();
	        
	        String sql2 = "drop table users1";
	        String sql = "CREATE TABLE USER" 
	        			+ " (dni int not null,"
	        			+ " password varchar(200) not null,"
	        			+ " nya varchar(200) not null,"
	        			+ " fecha_nac varchar(200) not null,"
	        			+ " obraSocial varchar(200) not null,"
	        			+ " tipo_usu int not null,"
	        			+ " PRIMARY KEY (dni))";
	        
	        s.executeUpdate(sql2);
	        s.executeUpdate(sql);
	        c.commit();
	        
		}catch(SQLException e){
			System.out.println("No se creo la tabla: " + e.getMessage());
		}
	}
	
	
	public void crearTablaMedicos() throws SQLException {
		try {
			Connection c = DBManager.connect();
	        Statement s = c.createStatement();
	        
	        String sql2 = "CREATE TABLE MEDICOS" 
	        			+ " (legMedico int not null," 
	        			+ " cobra int not null,"
	        			+ " PRIMARY KEY (legMedico))";
	        			//no puse la obra social porque yo la tengo en el usuario
	        
	        s.executeUpdate(sql2);
	        c.commit();
	        
		}catch(SQLException e){
			System.out.println("No se creo la tabla: " + e.getMessage());
		} 
	}
	
	
	public void crearTablaTurnos() throws SQLException {
		try {
			Connection c = DBManager.connect();
	        Statement s = c.createStatement();
	        
	        String sql2 = "drop TABLE turnos";
	        String sql = "CREATE TABLE turnos" 
	        			+ " (dni int not null,"
	        			+ " legMedico int not null,"
	        			+ " turno int not null,"
	        			+ " fecha varchar(200),"
	        			+ " hora varchar(200),"
	        			+ " consultorio int not null,"
	        			+ " PRIMARY KEY (turno))";
	        
	        s.executeUpdate(sql2);
	        s.executeUpdate(sql);
	        c.commit();
	        		
		}catch(SQLException e){
			System.out.println("no se creo la tabla:"+e.getMessage());
		}
	}
	
	
	public void eliminarTablaTurno() throws SQLException {
		try {
			Connection c = DBManager.connect();
	        Statement s = c.createStatement();
	        
	        String sql = "drop table turnos";
	        
	        s.executeUpdate(sql);
	        c.commit();
	        
		}catch(SQLException e){
			
			System.out.println("no se creo la tabla:"+e.getMessage());
		}
	}
	
	
	/*ésta funcion está para ser el usuario inicial, sobre el que podes trabajar, porque si no hay nada no podes editar.
	Es como para hacer un piso y no interactuar directamente con la base de datos ni bien arrancas el programa, ya podes
	manejarte desde el usuario creado*/
	public void cargarAdmin() throws SQLException {
		try {
			Connection c = DBManager.connect();
	        Statement s = c.createStatement();
	        
	        String sql = "insert into USERS1 (dni,pass,nya,fecha_nac,obra,tipo_user) values ('1','admin','admin','00/00/0000','TEST','1')";
	        
	        s.executeUpdate(sql);
	        c.commit();
        
        
		}catch(SQLException e){
			
			System.out.println("no se cargo admin: " + e.getMessage());
		}
	}
}
