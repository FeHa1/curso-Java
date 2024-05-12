package gui;

import java.sql.Connection;
import java.sql.SQLException;

import dao.DBManager;
import misc.DBCreate;

public class Main {
	
	 public static void main(String[] args) throws SQLException {
    	
		 /*
    	try {
			DBCreate db = new DBCreate(); //para restaurar base de datos
			db.crearTablaTurnos();
			db.crearTablaMedicos();
			db.crearTablaUsuarios();
			db.cargarAdmin();
			
		} catch (Exception e) {
			//TODO: handle exception
		}
		*/
    	
    	PanelManager menu = new PanelManager();
    	menu.executeGUI();
    	
    	/*
    	Connection c = DBManager.connect();
        c.close();
        */
    }
}