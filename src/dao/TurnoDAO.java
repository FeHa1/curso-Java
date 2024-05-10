package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Turno;

//me salta error porque dice que faltan métodos pero es porque los que no entiendo están comentados
public class TurnoDAO extends BaseDAO<Turno>{
	
	@Override
	protected String insertSqlString(Turno UnTurno) {
		int dni = UnTurno.getDni();
		int legMedico = UnTurno.getLegMedico();
		int turno = UnTurno.getTurno();
		String fecha = UnTurno.getFecha();
		String hora = UnTurno.getHora();
		int consultorio = UnTurno.getConsultorio();
		
		return ("INSERT INTO TURNO (dni, legMedico, turno, fecha, hora, consultorio) VALUES ('"	+ dni + "', '" + legMedico + "', '" + turno + "', '" + fecha + "', '" + hora + "', '" + consultorio + "')");
	}

	
	@Override
	protected String updateSqlString(Turno UnTurno) {
		int dni = UnTurno.getDni();
		int legMedico = UnTurno.getLegMedico();
		int turno = UnTurno.getTurno();
		String fecha = UnTurno.getFecha();
		String hora = UnTurno.getHora();
		int consultorio = UnTurno.getConsultorio();
		String str="UPDATE TURNOS SET dni = '" + dni 
				+ "', legMedico = '" + legMedico 
				+ "', fecha = '" + fecha 
				+ "', hora = '" + hora 
				+ "', consultorio = '" + consultorio
				+ "' WHERE turno = '" + turno + "'";

		return str;
	}
	
	
	@Override
	protected String BusquedaSimpleSqlString(int turno) {
		return "SELECT * FROM turnos WHERE turno = '" + turno + "'";
	}
	
	
	//éste ResultSet está relacionado con la parte del código que necesito que tomi me aclare
	@Override
	protected Turno resultsetToObject(ResultSet rs) throws SQLException {
		if (rs.next()) {
			int turno = rs.getInt("turno");
			int dni = rs.getInt("dni");
    		int legMedico=rs.getInt("legMedico");
    		String fecha = rs.getString("fecha");
    		String hora = rs.getString("hora");
    		int consultorio = rs.getInt("consultorio");
	        Turno u = new Turno(dni, legMedico, turno, fecha, hora, consultorio);
	        return u;
        }
		else {
			return null;
		}
	}
	
	
	
	//éste ResultSet está relacionado con la parte del código que necesito que tomi me aclare
	
	@Override
	protected List<Turno> rsToList(ResultSet rs) throws SQLException{
		List<Turno> resultado = new ArrayList<>();
        while (rs.next()) {
        	int dni = rs.getInt("dni");
    		int legMedico = rs.getInt("legMedico");
    		int turno = rs.getInt("turno");
    		String fecha = rs.getString("fecha");
    		String hora = rs.getString("hora");
    		int consultorio = rs.getInt("consultorio");
            Turno u = new Turno(dni, legMedico, turno, fecha, hora, consultorio);
            resultado.add(u);
        }
        
		return resultado;	
	}

	@Override
	protected String listaStringSql() {
		return "SELECT * FROM turnos";
	}
	
	@Override
	protected String BorrarStringSql(int turno) {
		return "DELETE FROM turnos WHERE turno = '" + turno + "'";
	}
}
