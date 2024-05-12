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
        double cobra = entidad.isCobra();
        
		return "INSERT INTO MEDICOS(legMedico, cobra) VALUES ('" + legajo + "', '" + cobra + "')";
	}
	
	@Override
	protected String updateSqlString(Medico entidad) {
		int legajo = entidad.getLegMedico();
        double cobra = entidad.isCobra();
        
		return "UPDATE MEDICOS set (legMedico, cobra) VALUES ('" + legajo + "', '" + cobra + "')";
	}

	@Override
	protected String BusquedaSimpleSqlString(int t) {
		
		return "SELECT * FROM MEDICOS WHERE legMedico = '" + t + "'";
	}
	
	//TENGO QUE PROBAR ÉSTA ENTIDAD CUANDO TENGA LA BASE DE DATOS PORQUE TIRA ERROR
	@Override
	protected Medico resultsetToObject(ResultSet rs) throws SQLException {
		if (rs.next()) {
        	int legajo = rs.getInt("legMedico");
            double cobra = rs.getDouble("cobra");
            
            Medico u = new Medico(legajo, cobra);
            
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
	        
	        int legajo = rs.getInt("legMedico");
            double cobra = rs.getDouble("cobra");
            
            Medico u = new Medico(legajo, cobra);
            
            resultado.add(u);
        }   
        
		return resultado;
	}
	
	@Override
	protected String listaStringSql() {     
		
		return "SELECT * FROM MEDICOS";
	}
	
	@Override
	protected String BorrarStringSql(int x) {
		
		return "DELETE FROM MEDICOS WHERE legMedico = '" + x + "'";
	}	
}
