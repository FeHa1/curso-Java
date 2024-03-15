package Service;
import java.util.List;
import dao.DAOException;
import dao.InterUsuarioDAO;
import dao.UsuarioDAO;

import entidades.Usuario;

public class UsuarioService {
	
	private InterUsuarioDAO usuarioDAO;
	
	public UsuarioService() {
		usuarioDAO = new UsuarioDAO();
	}
	
	public void guardar(Usuario usu) throws ServiceException{
		try {
			usuarioDAO.guardar(usu);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Service.Guardar: " + e.getMessage());
		}
	}
	
	public void eliminar(int dni) throws ServiceException {
		try {
			usuarioDAO.eliminar(dni);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Service.Eliminar: " + e.getMessage());
		}
	}
	
	public void modificar(Usuario usu) throws ServiceException {
		try {
			usuarioDAO.modificar(usu);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Service.modificar: " + e.getMessage());
		}
	}
	
	public Usuario recuperar(int dni) throws ServiceException {
		try {
			return usuarioDAO.recuperar(dni);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Service.Recuperar: " + e.getMessage());
		}
	}
	
	public List<Usuario> recuperarTodos() throws ServiceException {
		try {
			return usuarioDAO.recuperarTodos();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Service.RecuperarTodos: " + e.getMessage());
		}
	}
	
}
