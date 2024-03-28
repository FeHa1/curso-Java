package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Usuario;

public class UsuarioDAO extends BaseDAO<Usuario>{

	@Override
	protected String insertSqlString(Usuario UnUser) {
		int dni = UnUser.getDni();
		String password = UnUser.getPassword();
		String nya = UnUser.getNya();
		String fecha_nac = UnUser.getFecha_nac();
		String obra = UnUser.getObraSocial();
		int tipo_usu = UnUser.getTipo_usu();
		
		return ("INSERT INTO USERS1 (dni, password, nya, fecha_nac, obraSocial,tipo_usu) VALUES ('"	+ dni + "', '" + password + "', '" + nya + "', '" + fecha_nac + "', '" + obra+"', '" + tipo_usu +"')");
	}
	
	@Override
	protected String updateSqlString(Usuario e) {
		int dni = e.getDni();
		String password = e.getPassword();
		String nya = e.getNya();
		String fecha_nac = e.getFecha_nac();
		String obra = e.getObraSocial();
		int tipo_usu = e.getTipo_usu();
		
		return ("UPDATE USERS1 SET pass = '" + password 
				+ "', nya = '" + nya 
				+ "', fecha_nac = '" + fecha_nac 
				+ "', obra = '" + obra 
				+ "', tipo_user = '" + tipo_usu 
				+ "' WHERE dni =" + dni);
	}
	
	@Override
	protected String BusquedaSimpleSqlString(int t) {
		
		return "SELECT * FROM USERS1 WHERE dni = '" + t + "'";
	}
	
	@Override
	protected Usuario resultsetToObject(ResultSet rs) throws SQLException {
		if(rs.next()) {
			int dni = rs.getInt("dni");
	        String password = rs.getString("password");
	        String nya = rs.getString("nya");
	        String fecha_nac = rs.getString("fecha_nac");
	        String obra = rs.getString("obraSocial");
	        int tipo_usu = rs.getInt("tipo_usu");
	        
	        Usuario u = new Usuario(dni, password, nya, fecha_nac, obra, tipo_usu);
	        
	        return u;
        }
		
		return null;
	}
	
	@Override
	protected List<Usuario> rsToList(ResultSet rs) throws SQLException{
		List<Usuario> resultado = new ArrayList<>();
        while (rs.next()) {
        	int dni = rs.getInt("dni");
	        String password = rs.getString("password");
	        String nya = rs.getString("nya");
	        String fecha_nac = rs.getString("fecha_nac");
	        String obra = rs.getString("obraSocial");
	        int tipo_usu = rs.getInt("tipo_usu");
            
            Usuario u = new Usuario(dni, password, nya, fecha_nac, obra, tipo_usu);
            resultado.add(u);
        }   
        
		return resultado;	
	}
	
	@Override
	protected String listaStringSql() {
		return "SELECT * FROM USERS1";
	}
	
	@Override
	protected String BorrarStringSql(int dni) {
		return "DELETE FROM USERS1 WHERE dni = '" + dni + "'";
	}

	@Override
	public Usuario recuperar(int enti) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
