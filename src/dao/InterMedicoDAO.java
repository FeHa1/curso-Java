package dao;

import java.util.List;
import entidades.Medico;

public interface InterMedicoDAO {

	public void guardar(Medico med) throws DAOException;
	public void eliminar(int legMedico) throws DAOException;
	public void modificar(Medico med) throws DAOException;
	public Medico recuperar(int legMedico) throws DAOException;
	public List<Medico> recuperarTodos() throws DAOException;
}
