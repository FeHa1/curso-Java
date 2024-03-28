package dao;
import java.util.List;

import entidades.Usuario;

public interface InterUsuarioDAO {
	public void guardar(Usuario usu) throws DAOException;
	public void eliminar(int dni) throws DAOException;
	public void modificar(Usuario usu) throws DAOException;
	public Usuario recuperar(int dni) throws DAOException;
	public List<Usuario> recuperarTodos() throws DAOException;
	
}

//NO USO MAS ÉSTA INTERFACE, CUANDO TERMINE CON USUARIO Y TURNO LA BORRO