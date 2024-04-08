package dao;

import java.util.List;
import Service.ServiceException;

//ésto es para no tener que hacer los metodos de las interfaces para todas las entidades. Medico, usuario y turno pueden heredar los métodos
public interface InterBaseDAO<T> { 
	void guardar(T x) throws DAOException, ObjDupliException;
	void eliminar(int enti) throws DAOException, ServiceException;
	void modificar(T x) throws DAOException;
	T mostrar(int x) throws DAOException;
	List<T> listaTodosLosObjetos() throws DAOException;
}
