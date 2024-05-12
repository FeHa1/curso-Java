package tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entidades.*;

@SuppressWarnings({ "unused", "serial" })
public class MedicoTableModel extends AbstractTableModel{

	private static final int columna_nombre = 0;
	private static final int columna_legajoMed = 1;
	private static final int columna_costo = 2;
	
	private String[] nombresColumnas = {"Nombre", "Legajo", "Costo"};
	@SuppressWarnings("rawtypes")
	private Class[] tiposColumnas = {String.class,Integer.class, Integer.class};
	private List<Medico> contenido;
	
	public MedicoTableModel() {
		contenido = new ArrayList<Medico>();
	}

	@Override
	public int getColumnCount() {
		return nombresColumnas.length;
	}
	
	@Override
	public int getRowCount() {
		return contenido.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Medico m = contenido.get(rowIndex);
		
		Object result = null;
		switch(columnIndex) {
		case columna_nombre:
			result = m.getNya();
			break;
		case columna_legajoMed:
			result = m.getLegMedico();
			break;
		case columna_costo:
			result = m.isCobra();
			break;
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
    
    public List<Medico> getContenido() {
    	return contenido;
    }
    
    public void setContenido(List<Medico> contenido) {
    	this.contenido = contenido;
    }

}
