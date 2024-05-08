package tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entidades.*;

@SuppressWarnings({ "unused", "serial" })
public class TurnoTableModel extends AbstractTableModel {

	private static final int columna_legajoMedico = 0;
	private static final int columna_fecha = 1;
	private static final int columna_hora = 2;
	private static final int columna_nroConsultorio = 3;
	private static final int columna_dniPaciente = 4;
	private static final int columna_iDTurno = 5;
	
	private String[] nombresColumnas = {"legajoMedico", "fecha", "hora", "dni", "turno"}; //"nroConsultorio"
	@SuppressWarnings("rawtypes")
	private Class[] tiposColumnas = {Integer.class, String.class,String.class,String.class,String.class, Integer.class};
	private List<Turno> contenido;
	
	public TurnosTableModel() {
		contenido = new ArrayList<Turno>();
	}
	
	public int getColumnCount() {
		return nombresColumnas.length;
	}
	
	public int getRowCount() {
		return contenido.size();
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Turno m = contenido.get(rowIndex);
		
		Object result = null;
		switch(columnIndex) {
		case columna_legajoMedico:
			result = m.getLegMedico();
			break;
		case columna_fecha:
			result = m.getFecha();
			break;
		case columna_hora:
			result = m.getHora();
			break;
			/*	todavia no tengo consultorio
		case columna_nroConsultorio:
			result = m.getNroConsultorio();
			break;*/
		case columna_dniPaciente:
			result = m.getDni();
			break;
		case columna_iDTurno:
			result = m.getTurno();
			break;
		
		/*si el índice de columna no coincide con ninguno de los casos específicos (por ejemplo, 
		  no es columna_dni, columna_pass, etc.), se establece un valor predeterminado (cadena vacía) 
		  para la celda.*/
		default:
			result = new String("");
		}
		
		return result;
	}
	
	public String getColumnName(int col) {
        return nombresColumnas[col];
    }

	
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Class getColumnClass(int col) {
        return tiposColumnas[col];
    }
    

    public List<Turno> getContenido() {
    	return contenido;
    }
    
    public void setContenido(List<Turno> contenido) {
    	this.contenido = contenido;
    }
	
}
