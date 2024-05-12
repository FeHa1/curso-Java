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
	        
	        String sql2 = "drop table USUARIOS";
	        String sql = "CREATE TABLE USUARIOS" 
	        			+ " (dni int not null,"
	        			+ " password varchar(200) not null,"
	        			+ " nya varchar(200) not null,"
	        			+ " fecha_nac varchar(200) not null,"
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
	        
	        String sql = "drop table MEDICOS";
	        String sql2 = "CREATE TABLE MEDICOS" 
	        			+ " (legMedico int not null," 
	        			+ " cobra double not null,"
	        			+ " PRIMARY KEY (legMedico))";
	        
	        s.executeUpdate(sql);
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
	        
	        //String sql2 = "drop TABLE TURNOS";
	        String sql = "CREATE TABLE TURNOS" 
	        			+ " (dni int not null,"
	        			+ " legMedico int not null,"
	        			+ " turno int not null,"
	        			+ " fecha varchar(200),"
	        			+ " hora varchar(200),"
	        			+ " consultorio int not null,"
	        			+ " PRIMARY KEY (turno))";
	        
	        //s.executeUpdate(sql2);
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
	        
	        String sql = "drop table TURNOS";
	        
	        s.executeUpdate(sql);
	        c.commit();
	        
		}catch(SQLException e){
			
			System.out.println("no se creo la tabla: " + e.getMessage());
		}
	}
	
	
	/*ésta funcion está para ser el usuario inicial, sobre el que podes trabajar, porque si no hay nada no podes editar.
	Es como para hacer un piso y no interactuar directamente con la base de datos ni bien arrancas el programa, ya podes
	manejarte desde el usuario creado*/
	public void cargarAdmin() throws SQLException {
		try {
			Connection c = DBManager.connect();
	        Statement s = c.createStatement();
	        
	        String sql = "insert into USUARIOS (dni, password, nya, fecha_nac, tipo_usu) values ('1','admin','admin','00/00/0000','1')";
	        
	        s.executeUpdate(sql);
	        c.commit();
        
        
		}catch(SQLException e){
			
			System.out.println("no se cargo admin: " + e.getMessage());
		}
	}
}
