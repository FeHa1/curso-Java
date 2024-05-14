package paneles;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Service.*;
import dao.DAOException;
import dao.ObjDupliException;
import entidades.*;
import tableModels.MedicoTableModel;


@SuppressWarnings({ "serial", "unused" })
public class TablaMedicoPanel extends AbstractCRUD implements ActionListener{

	//tabla
	private JTable tablaMedicos;
	private MedicoTableModel modelo;
	private JScrollPane scrollPaneParaTabla;
	
	//servicio
	private MedicoService servicio;
	
	//campos
	private JTextField campoLegajo;
	private JTextField campoCostoXConsulta;
	
	public TablaMedicoPanel() {
		armarPanel();
		this.ok="Correcto!";
		this.servicio= new MedicoService();
		refresh();
	}
	
	private void armarPanel() {
		//campos
		this.campoLegajo = new JTextField();
		agregarCampoCarga("legajo:", campoLegajo);
		this.campoCostoXConsulta = new JTextField();
		agregarCampoCarga("Costo:", campoCostoXConsulta);
		//tabla
		modelo = new MedicoTableModel();
		tablaMedicos = new JTable(modelo);
		scrollPaneParaTabla = new JScrollPane(tablaMedicos);
		this.add(scrollPaneParaTabla);
	}
	
	@Override
	protected void agregar() {
		//seteo de verificadores
		
		try {
			String error = "Error: ";
			double costo=-1;
			int leg=-1;
			boolean verif= true;
			boolean verifleg= true;
			boolean verifcosto= true;
			
			/*DESPUES ME FIJO TODO ÉSTO*/
			//validacion de numeros
			try {
				costo = Double.parseDouble( this.campoCostoXConsulta.getText());
				leg = Integer.parseInt( this.campoLegajo.getText());
				try {
					if(!validarNum(leg)) {error=error+"error de legajo usuario no encontrado";}
					verifleg = (validarNum(leg)&&validarLeg(leg));
				} catch (Exception e2) {
					error = error + "error de legajo usuario no encontrado";
					verifleg = false;
				}
				
				if(!validarNum(leg)) {
					error = error + "error de legajo usuario fuera de rango";
				}
				
				verifcosto = validarNum(costo);
				if(!validarNum(costo)) {
					error = error + "costo fuera de rango,";
				}
			
			} catch (Exception e2) {
				error = error + "solo es posible numeros en leg. y costo,";
				verif= false;	
			}
			
			//si todo esta validado correctamente se ejecuta la carga
			if(verif && verifcosto && verifleg ) {
				
				//medico
				Medico x1 = new Medico(leg, costo);
				
				//cambiar permiso de usuario
				UsuarioService servUsu = new UsuarioService(); // recordar que todo esto esta encerrado dentro de un try, en caso de que falle la base de datos
				
				Usuario usu= servUsu.mostrar(leg);
				if (usu.getTipo_usu()==3){
					usu.setTipo_usu(2);
				}
				
				servUsu.modificar(usu);
				//persistir en DB
				this.servicio.guardar(x1);
				refresh();
				mostrarerror(ok);
			}
			
			else {
				mostrarerror(error);
			}
			
		} catch (DAOException e2) {
			
			e2.printStackTrace();
			System.out.println("error de carga");
			mostrarerror("ERROR EN CARGA");
			// TODO: implementar validacion y ventana emergente
			
		} catch (ObjDupliException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void borrar() {
		int filaSeleccionada = this.tablaMedicos.getSelectedRow();
		if(filaSeleccionada != -1) {
			int x = (int) this.modelo.getValueAt(filaSeleccionada,1);
			System.out.println(x+"fue borrado (dato legajo)");
		
			this.modelo.getContenido().remove(filaSeleccionada);
		
			modelo.fireTableDataChanged();
		
			try {
				this.servicio.eliminar(x);//borro el estatus de medico
				UsuarioService servUsu = new UsuarioService(); 
				Usuario usu= servUsu.mostrar(x);
				if (usu.getTipo_usu()==2) {//remuevo permisos
					usu.setTipo_usu(3); 
					servUsu.modificar(usu);
				}
				
			} catch (DAOException | ServiceException e1) {			
				e1.printStackTrace();
			}
			
		}else {
			System.out.println("no se selecciono ninguna fila");
		}		
	}

	@Override
	protected void refresh() {
		List<Medico> lista;
		try {
			lista = this.servicio.listaTodosLosObjetos();
			System.out.println(lista);
			modelo.setContenido(lista);
			modelo.fireTableDataChanged();
			mostrarerror(ok);
			
		} catch (DAOException e1) {
			e1.printStackTrace();
			System.out.println("error al cargar medicos en servicio");
			mostrarerror("ERROR Base de datos");
		} 
	}
	
	//seccion de validaciones
	public boolean validarNum(double costo) {
		if (costo>0 && costo<2000000000) {
			return true;
			
		}
		else {
			return false;
		}
	}
	
	public boolean validarStr(String cadena) {
	    
		if (cadena.length()==0) {
			return false;
		}
	    
		for (int i = 0; i < cadena.length(); i++) {
	        char caracter = cadena.charAt(i);
	        if (!Character.isLetter(caracter) ) {
	        	if(caracter != ',') {
	        		if(caracter != ' ') {
			        	return false;
			        }
	        	}
	        }
	     }
		
		return true;
	 }
	
	
	public boolean validarLeg(int leg) {
	    UsuarioService x = new UsuarioService();
	    try {
	        Usuario usuario = x.mostrar(leg);
	        if (usuario != null && usuario.getDni() > 0) {
	            return true;
	        } else {
	            return false;
	        }
	    } catch (DAOException e) {
	        e.printStackTrace();
	        return false;
	    }   
	}

	    
	/*
	public boolean validarLeg(int leg) {
		UsuarioService x= new UsuarioService();
		try {
			if (x.mostrar(leg).getDni()>0) {
				return true;
			}else {
				return true;
			}
		} catch (DAOException e) {
			
			e.printStackTrace();
			return false;
		}	
	}
	*/
}
