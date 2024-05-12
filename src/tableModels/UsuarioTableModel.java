package tableModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entidades.*;

@SuppressWarnings({ "unused", "serial" })
public class UsuarioTableModel extends AbstractTableModel {

	private int dni;
	private String password;
	private String nya;
	private String fecha_nac;
	
	private static final int columna_dni = 0;
	private static final int columna_pass = 1;
	private static final int columna_nya = 2;
	private static final int columna_fecha_nac = 3;
	private static final int columna_tipo_usuario = 4;
	
	private String[] nombresColumnas = {"dni", "password", "nya", "fecha_nac", "permiso"};
	@SuppressWarnings("rawtypes")
	private Class[] tiposColumnas = {Integer.class, String.class, String.class, String.class, Integer.class};
	private List<Usuario> contenido;
	
	public UsuarioTableModel() {
		contenido = new ArrayList<Usuario>();
	}
	
	@Override
	public int getColumnCount() {
		return nombresColumnas.length;
	}
	
	public int getRowCount() {
		return contenido.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Usuario m = contenido.get(rowIndex);
		
		Object result = null;
		switch(columnIndex) {
		case columna_dni:
			result = m.getDni();
			break;
		case columna_pass:
			result = m.getPassword();
			break;
		case columna_nya:
			result = m.getNya();
			break;
		case columna_fecha_nac:
			result = m.getFecha_nac();
			break;
		case columna_tipo_usuario:
			result = m.getTipo_usu();
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
    
    public List<Usuario> getContenido() {
    	return contenido;
    }
    
    public void setContenido(List<Usuario> contenido) {
    	this.contenido = contenido;
    }
}
