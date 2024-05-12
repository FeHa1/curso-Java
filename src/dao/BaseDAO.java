package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO<T> implements InterBaseDAO<T>{
	Date d = new Date(0);
	
	protected abstract String insertSqlString(T entidad);
	
	public void guardar(T x) throws DAOException, ObjDupliException{
		Connection c = DBManager.connect();
		
		try {
			java.sql.Statement s = c.createStatement();
            String sql = insertSqlString(x);
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
            	if(e.getErrorCode() == 23505) {throw new ObjDupliException("(DAO> entrada duplicada)" + e.getMessage());
            	}
                c.rollback();
                e.printStackTrace();
                if(e.getErrorCode() != 23505) {throw new DAOException("(DAO> ERROR AL CARGAR entidad)" + e.getMessage());}
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new DAOException(e1.getMessage());
            }
            
        } finally {
        	try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new DAOException("(DAO>LA CONEXION CON LA BASE DE DATOS NO FUE CERRADA CORRECTAMENTE)" + e1.getMessage());
            }
      }
	}
	
	protected abstract String updateSqlString(T entidad);
	
	public void modificar(T x) throws DAOException{
		Connection c = DBManager.connect();
        try {
        	java.sql.Statement s = c.createStatement();
            String sql = updateSqlString(x);
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                e.printStackTrace();
                c.rollback();
                throw new DAOException(e.getMessage());
            } catch (SQLException e1) {
                e.printStackTrace();
                throw new DAOException(e1.getMessage());
            }
            
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new DAOException("(DAO>LA CONEXION CON LA BASE DE DATOS NO FUE CERRADA CORRECTAMENTE)" + e1.getMessage());
            }
        }
	}
	
	protected abstract String BusquedaSimpleSqlString(int x);
	
	protected abstract T resultsetToObject(ResultSet rs) throws SQLException;
	
	public T mostrar(int x) throws DAOException {
		String sql = BusquedaSimpleSqlString(x);
        Connection c = DBManager.connect();
        try {
        	java.sql.Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            
            return resultsetToObject(rs);

        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
                throw new DAOException(e.getMessage());
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new DAOException(e1.getMessage());
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new DAOException("(DAO>LA CONEXION CON LA BASE DE DATOS NO FUE CERRADA CORRECTAMENTE)" + e1.getMessage());
            }
        }
	}
	
	protected abstract String BorrarStringSql(int x);
	
	public void eliminar(int x) throws DAOException {
		String sql = BorrarStringSql(x);
		Connection c = DBManager.connect();
        try {
        	java.sql.Statement s = c.createStatement();
            s.executeUpdate(sql);
            c.commit();
        } 
        catch (SQLException e) {
            
        	try {
                c.rollback();
            } catch (SQLException ex) {
            	ex.printStackTrace();
            	throw new DAOException(ex.getMessage());
            }
            throw new DAOException(e.getMessage());
        }
        
        try {
            c.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
            throw new DAOException("(DAO>LA CONEXION CON LA BASE DE DATOS NO FUE CERRADA CORRECTAMENTE)" + e1.getMessage());
        }
    }
	
	
	protected abstract List<T> rsToList(ResultSet rs) throws SQLException;
	
	protected abstract String listaStringSql();
	
	public List<T> listaTodosLosObjetos() throws DAOException {
        String sql = listaStringSql();
        Connection c = DBManager.connect();
        try {
        	java.sql.Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            
            return rsToList(rs);
            
        } catch (SQLException e) {
            try {
                c.rollback();
                throw new DAOException(e.getMessage());
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new DAOException(e1.getMessage());
            }
            
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new DAOException("(DAO>LA CONEXION CON LA BASE DE DATOS NO FUE CERRADA CORRECTAMENTE)" + e1.getMessage());
            }
        }
	}	
}
