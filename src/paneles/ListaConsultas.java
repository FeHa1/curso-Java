package paneles;

import java.awt.BorderLayout;
import java.awt.Label;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import entidades.*;
import Service.*;
import dao.DAOException;

@SuppressWarnings("serial")
public class ListaConsultas extends JPanel{
	private List<Turno> listaTurnos; //almacena una lista de objetos Turno
	private TurnoService servicioTurno; //interactua con la capa de servicios de Turno
	private MedicoService servicioMedico; //interactua con la capa de servicios de Medico
	private UsuarioService servicioUsuario;	//interactua con la capa de servicios de Usuario
	private Usuario sesion; //representa la sesion actual del usuario
	private DefaultTableModel tableModel; //es un modelo de tabla que se utiliza para almacenar y mostrar los datos en la tabla.
	
	public ListaConsultas(Usuario sesion, int modo) {// modo 1 admin, 2 medico, 3 paciente
		setLayout(new BorderLayout());
		
		try {
			this.sesion = sesion;
			this.servicioTurno = new TurnoService();
			this.servicioMedico = new MedicoService();
			this.servicioUsuario = new UsuarioService();
			this.listaTurnos= servicioTurno.listaTodosLosObjetos();
			this.tableModel = new DefaultTableModel();
			tableModel.addColumn("Fecha");
	        tableModel.addColumn("Hora");
	        tableModel.addColumn("Médico");
	        tableModel.addColumn("Paciente");
	        
	        if(modo == 1) {
	        	for (Turno turno : listaTurnos) {
	                cargarTurnoTabla(turno);
	        	}
	        }
	        
	        else if(modo == 2) {
	        	for (Turno turno : listaTurnos) {
	            	if(turno.getLegMedico()==this.sesion.getDni()){
	            		cargarTurnoTabla(turno);
	            		}
	            	}
	        }
			
	        else if(modo == 3) {
	        	for(Turno turno : listaTurnos) {
	        			if(turno.getDni() == this.sesion.getDni()) { //pregunta si el 'dni' de Turno es igual al 'dni' de Usuario (pasa que les puse a las variables el mismo nombre
	        			cargarTurnoTabla(turno);
	        		}
	        	}
	        }
	        
		}catch (DAOException e) {
			e.printStackTrace();
			this.add(new Label("Error base de datos"));
		}
	}

	private void cargarTurnoTabla(Turno turno) throws DAOException{
		String fecha = turno.getFecha();
		String hora = turno.getHora();
		String medicoNombreApellido = servicioMedico.mostrar(turno.getLegMedico()).getNya();
		String pacienteNombreApellido = servicioUsuario.mostrar(turno.getDni()).getNya(); //tengo que ver que ésto no me genere conflicto porque 'getDni' es el método tanto en Usuario como en Turno
		tableModel.addRow(new Object[]{fecha, hora, medicoNombreApellido,pacienteNombreApellido});
	}
}
