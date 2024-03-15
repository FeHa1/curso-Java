package Service;

import java.util.List;
import dao.DAOException;
import dao.InterMedicoDAO;
import dao.MedicoDAO;

import entidades.Medico;

public class MedicoService {
	
	private InterMedicoDAO medicoDAO; 
	
	public MedicoService() {
		medicoDAO = new MedicoDAO();
	}
	
	public void guardar(Medico med) throws ServiceException{
		try {
			medicoDAO.guardar(med);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Service.Guardar: " + e.getMessage());
		}		
	}
	
	public void eliminar(int legMedico) throws ServiceException {
		try {
			medicoDAO.eliminar(legMedico);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Service.Eliminar: " + e.getMessage());
		}
	}
	
	public void modificar(Medico med) throws ServiceException {
		try {
			medicoDAO.modificar(med);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Service.modificar: " + e.getMessage());
		}
	}
	
	public Medico recuperar(int legMedico) throws ServiceException {
		try {
			return medicoDAO.recuperar(legMedico);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Service.Recuperar: " + e.getMessage());
		}
	}
	
	public List<Medico> recuperarTodos() throws ServiceException {
		try {
			return medicoDAO.recuperarTodos();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Service.RecuperarTodos: " + e.getMessage());
		}
	}
	
	
	
	
	
	
	
}
