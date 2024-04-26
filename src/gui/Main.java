package gui;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import Service.ServiceException;
import Service.UsuarioService;
import entidades.Usuario;

public class Main {
    public static void main(String[] args) {
    	
    	/*
    	UsuarioService usuarioService = new UsuarioService();
    	
		Usuario usuario1 = new Usuario();
		usuario1.setDni(1);
		usuario1.setPassword("20");
		usuario1.setNya("Franco Alfonso");
		usuario1.setFecha_nac("10/12/1999");
		usuario1.setObraSocial("OSDE");
		usuario1.setTipo_usu(2);
		
		Usuario usuario2 = new Usuario();
		usuario2.setDni(2);
		usuario2.setPassword("10");
		usuario2.setNya("javier Masche");
		usuario2.setFecha_nac("19/10/1990");
		usuario2.setObraSocial("DOSUBA");
		usuario2.setTipo_usu(3);
		*/
		//comente ésta parte para que cuendo ponga para recuperar los usuarios no me traiga dos veces lo mismo
		/*try {
			usuarioService.guardar(usuario1);
			usuarioService.guardar(usuario2);
		} catch (ServiceException e) {
			e.printStackTrace();
		}*/
		/*
		List<Usuario> usuarios = null;
		try {
			usuarios = usuarioService.recuperarTodos();
			for(Usuario usuar: usuarios) {
				System.out.println("ID: " + usuar.getDni() + ", password: " + usuar.getPassword() + ", Nombre: " + usuar.getNya() + ", Nacimiento: " + usuar.getFecha_nac() + ", Obra Social: " + usuar.getObraSocial() + ", Tipo Usuario: " + usuar.getTipo_usu());
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		Usuario alfonso = null;
		try {
			alfonso = usuarioService.recuperar(1);
			alfonso.setObraSocial("Ceros");
			usuarioService.modificar(alfonso);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		
    }*/
}