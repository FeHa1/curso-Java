package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Medico;

public class MedicoDAO extends BaseDAO<Medico>{
	
	//acá sobreescribo todos los métodos abstractos que tengo en "BaseDAO"
	
	@Override
	protected String insertSqlString(Medico entidad) {
		int legajo = entidad.getLegMedico();
        boolean cobra = entidad.isCobra();
        
		return "INSERT INTO medicos(legMedico, cobra) VALUES ('" + legajo + "', '" + cobra + "')";
	}
	
	@Override
	protected String updateSqlString(Medico entidad) {
		int legajo = entidad.getLegMedico();
        boolean cobra = entidad.isCobra();
        
		return "UPDATE medicos set (legMedico, cobra) VALUES ('" + legajo + "', '" + cobra + "')";
	}

	@Override
	protected String BusquedaSimpleSqlString(int t) {
		
		return "SELECT * FROM medicos WHERE legMedico = '" + t + "'";
	}
	
	//TENGO QUE PROBAR ÉSTA ENTIDAD CUANDO TENGA LA BASE DE DATOS PORQUE TIRA ERROR
	@Override
	protected Medico resultsetToObject(ResultSet rs) throws SQLException {
		if (rs.next()) {
			//pongo todos éstos atributos porque estoy creando un nuevo medico, y el 'medico' heredaba todos los atributos de 'usuario'
			int dni = rs.getInt("dni");
	        String password = rs.getString("password");
	        String nya = rs.getString("nya");
	        String fecha_nac = rs.getString("fecha_nac");
	        String obra = rs.getString("obraSocial");
	        int tipo_usu = rs.getInt("tipo_usu");
	        
        	int legajo = rs.getInt("legMedico");
            boolean cobra = rs.getBoolean("cobra");
            
            Medico u = new Medico(dni, password, nya, fecha_nac, obra, tipo_usu, legajo, cobra);
            
            return u;
        }
		
		else {
        	return null;
        }
	}
	
	@Override
	protected List<Medico> rsToList(ResultSet rs) throws SQLException {
		List<Medico> resultado = new ArrayList<>();
        while (rs.next()) {
        	int dni = rs.getInt("dni");
	        String password = rs.getString("password");
	        String nya = rs.getString("nya");
	        String fecha_nac = rs.getString("fecha_nac");
	        String obra = rs.getString("obraSocial");
	        int tipo_usu = rs.getInt("tipo_usu");
	        
	        int legajo = rs.getInt("legMedico");
            boolean cobra = rs.getBoolean("cobra");
            
            Medico u = new Medico(dni, password, nya, fecha_nac, obra, tipo_usu, legajo, cobra);
            
            resultado.add(u);
        }   
        
		return resultado;
	}
	
	@Override
	protected String listaStringSql() {     
		
		return "SELECT * FROM medicos";
	}
	
	@Override
	protected String BorrarStringSql(int x) {
		
		return "DELETE FROM medicos WHERE legMedico = '" + x + "'";
	}	
}
