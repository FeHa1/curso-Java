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
		int tipo_usu = UnUser.getTipo_usu();
		
		return ("INSERT INTO USUARIOS (dni, password, nya, fecha_nac, tipo_usu) VALUES ('"	+ dni + "', '" + password + "', '" + nya + "', '" + fecha_nac + "', '" + tipo_usu +"')");
	}
	
	@Override
	protected String updateSqlString(Usuario e) {
		int dni = e.getDni();
		String password = e.getPassword();
		String nya = e.getNya();
		String fecha_nac = e.getFecha_nac();
		int tipo_usu = e.getTipo_usu();
		
		return ("UPDATE USUARIOS SET password = '" + password 
				+ "', nya = '" + nya 
				+ "', fecha_nac = '" + fecha_nac 
				+ "', tipo_usu = '" + tipo_usu 
				+ "' WHERE dni =" + dni);
	}
	
	@Override
	protected String BusquedaSimpleSqlString(int t) {
		
		return "SELECT * FROM USUARIOS WHERE dni = '" + t + "'";
	}
	
	@Override
	protected Usuario resultsetToObject(ResultSet rs) throws SQLException {
		if(rs.next()) {
			int dni = rs.getInt("dni");
	        String password = rs.getString("password");
	        String nya = rs.getString("nya");
	        String fecha_nac = rs.getString("fecha_nac");
	        int tipo_usu = rs.getInt("tipo_usu");
	        
	        Usuario u = new Usuario(dni, password, nya, fecha_nac, tipo_usu);
	        
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
	        int tipo_usu = rs.getInt("tipo_usu");
            
            Usuario u = new Usuario(dni, password, nya, fecha_nac, tipo_usu);
            resultado.add(u);
        }   
        
		return resultado;	
	}
	
	@Override
	protected String listaStringSql() {
		return "SELECT * FROM USUARIOS";
	}
	
	@Override
	protected String BorrarStringSql(int dni) {
		return "DELETE FROM USUARIOS WHERE dni = '" + dni + "'";
	}	
}
