package Service;

import java.util.List;

import dao.DAOException;
import dao.InterBaseDAO;
import dao.ObjDupliException;

public abstract class AbstractService<T, DAO extends InterBaseDAO<T>> implements InterBaseDAO<T>{
	
	protected DAO dao;
	
	public AbstractService(DAO dao) {
		this.dao = dao;
	}
	
	public void guardar(T x) throws DAOException, ObjDupliException{
		try {
            dao.guardar(x);
        }catch (ObjDupliException | DAOException e) {
            throw new DAOException(e.getMessage());
        }
	}
	
	public void eliminar(int enti) throws DAOException, ServiceException{
		try {
			dao.eliminar(enti);
		}catch(DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void modificar(T x) throws DAOException{
		try {
			dao.modificar(x);
        }
		catch (DAOException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }
	}
	
	public T mostrar(int x) throws DAOException{
		try {
			return dao.mostrar(x);
        }
		catch (DAOException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }
	}
	
	public List<T> listaTodosLosObjetos() throws DAOException{
		try {
			return dao.listaTodosLosObjetos();
		}catch(DAOException e){
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}
	}
}
