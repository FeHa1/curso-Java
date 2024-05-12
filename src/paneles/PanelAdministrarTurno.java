package paneles;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.*;
import entidades.*;
import Service.*;
import tableModels.TurnoTableModel;

@SuppressWarnings("serial")
public class PanelAdministrarTurno extends JPanel implements ActionListener{

	//manejo de error
	private String ok; 
	private Label errorDisplay;
	
	//servicio
	private UsuarioService usuService;
	private MedicoService medService;
	private TurnoService turnoService;
	
	//botones
	private JButton reservarButton;
	private JButton eliminarButton;
	
	//listas
	private List<Medico> listaMedico;
	private List<Usuario> listaUsuarios;
	
	//combos
	private JComboBox<String> usuarioComboBox;
	private JComboBox<String> medicoComboBox;
	private JComboBox<Object> horaComboBox;
	private JComboBox<Object> fechaComboBox;
	private JComboBox<Integer> consultorioComboBox; 
	
	//paneles
	private JPanel panelPrincipal;
	private JPanel sidePanel;
	private TurnoTableModel modelo;
	private JTable tablaTurnos;
	private JScrollPane scrollPaneParaTabla;
	
	public PanelAdministrarTurno() {
		this.medService = new MedicoService();
		this.usuService = new UsuarioService();
		this.turnoService = new TurnoService();
		armarPanel();
	}
	
	//Establece el diseño del panel
	public void armarPanel() {
		String error = "";
		this.setLayout(new FlowLayout());
		this.panelPrincipal= new JPanel();
    	panelPrincipal.setLayout(new GridLayout(7,2));
    	
    	//Agrego display de error
    	this.ok = "Correcto"; //string de ejecucion exitosa
    	this.errorDisplay= new Label();
    	errorDisplay.setBackground(Color.BLACK);
		mostrarerror(ok); 
		add(errorDisplay);
		this.sidePanel= new JPanel();
		sidePanel.setLayout(new GridLayout(2,1));
		this.sidePanel.add(errorDisplay);
		this.sidePanel.add(panelPrincipal);
		
		//Etiqueta para seleccionar un médico
        JLabel medicoLabel = new JLabel("Médico: ");
        panelPrincipal.add(medicoLabel);
		
        //ComboBox para seleccionar un médico
        this.medicoComboBox = new JComboBox<>();
        try {
        	this.listaMedico = medService.listaTodosLosObjetos();
        	for (Medico elemento : this.listaMedico) {
        		String x = elemento.getNya();
                medicoComboBox.addItem(x);
            }
        	
		} catch (Exception e) {
			error = error + "Error carga medica";
		}
		
        panelPrincipal.add(medicoComboBox);
        
        
        //Etiqueta para seleccionar un usuario
 		JLabel usuarioLabel = new JLabel("Usuario: ");
 		panelPrincipal.add(usuarioLabel);
 		this.usuarioComboBox = new JComboBox<>();
        
 		try {
		    this.listaUsuarios=usuService.listaTodosLosObjetos();
		    for (Usuario usuario : listaUsuarios) {
		    	String x= usuario.getNya();
		        usuarioComboBox.addItem(x);
		    }
		} catch (Exception e) {
			error=error+"error carga usuarios";
		}
 		
		panelPrincipal.add(usuarioComboBox);

		//Etiqueta para ingresar fecha y hora
        fechaComboBox = new JComboBox<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        
        for (int i = 0; i < 60; i++) { //podes tener un turno como maximo por 2 meses (60 dias)
            fechaComboBox.addItem(dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        
        //Agregar el JComboBox al contenido del JFrame
        panelPrincipal.add(new JLabel("Fecha: "));
        panelPrincipal.add(fechaComboBox);
        
        //Campo de texto para ingresar fecha y hora
        this.horaComboBox = new JComboBox<>();
        for (int hora = 8; hora <= 20; hora++) { //horario que está abierto el consultorio (de 8 a 20)
            for (int minuto = 0; minuto < 6; minuto += 3) {
                String horaStr = hora + ":" + minuto + "0";
                horaComboBox.addItem(horaStr);
            }
        }
        
        panelPrincipal.add(new JLabel("Hora: "));
        panelPrincipal.add(horaComboBox);
        
        //campo consultorio
        JLabel consultorio = new JLabel("consultorio: ");
		panelPrincipal.add(consultorio);
        this.consultorioComboBox = new JComboBox<>();
        for (int i = 1; i <= 30; i++) {
            consultorioComboBox.addItem(i);
        }
        panelPrincipal.add(consultorioComboBox);
        
        
        //boton para consultar turno
        reservarButton = new JButton("Reservar Turno");
        reservarButton.addActionListener(this);
        this.panelPrincipal.add(reservarButton);
        eliminarButton = new JButton("Eliminar Turno");
        eliminarButton.addActionListener(this);
        add(eliminarButton);
        
        if(error.length()==0) {
        	mostrarerror(ok);
        }
        else {
        	mostrarerror(error);
        }
        
        //agregar modelo de tabla
        modelo = new TurnoTableModel();
        tablaTurnos = new JTable(modelo);
        scrollPaneParaTabla = new JScrollPane(tablaTurnos);
		this.add(scrollPaneParaTabla);
		
		this.add(sidePanel);
        refresh(); 
        revalidate();
    	repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==reservarButton) {
			Usuario usu = listaUsuarios.get(usuarioComboBox.getSelectedIndex());
			Medico med = listaMedico.get(medicoComboBox.getSelectedIndex());
			String fecha = (String) fechaComboBox.getSelectedItem();
			String hora = (String) horaComboBox.getSelectedItem();
			Integer consultorio = (Integer) consultorioComboBox.getSelectedItem();
			
			if(verificarValidezDeTurno(consultorio, fecha, hora, med, usu)) {
				//guardar turno
				try {
					Turno tur= new Turno(usu.getDni(), med.getLegMedico(), generarTurnoNuevo(), fecha, hora, consultorio); 
					this.turnoService.guardar(tur);
				} catch (Exception e2) {
					e2.printStackTrace();
					mostrarerror("Error base de datos al cargar turno");
				}
			}
			
			else {
				System.out.println("Turno no valido");
			}
			
			refresh();
		}
		
		if (e.getSource()==eliminarButton) {
			int filaSeleccionada = this.tablaTurnos.getSelectedRow();
			if(filaSeleccionada != -1) {
				int x = (int) this.modelo.getValueAt(filaSeleccionada,5);
				this.modelo.getContenido().remove(filaSeleccionada);
				modelo.fireTableDataChanged();
			
				try {
					this.turnoService.eliminar(x);
					System.out.println(x + "fue borrado (dato legajo)");
					mostrarerror(ok);
				} catch (ServiceException | DAOException e1) {
				// TODO Auto-generated catch block
					mostrarerror("La base de datos fallo");
					e1.printStackTrace();
				}
			}
			
			else {
				System.out.println("No se selecciono ninguna fila");
			}
			
			refresh();	
		}
		
	}
	
	//refrescar tabla
	public void refresh() {
		List<Turno> lista;
		try {
			lista = this.turnoService.listaTodosLosObjetos();
			for (Turno elem: lista) {
				System.out.println(elem.toString());
			}
			System.out.println(lista);
			modelo.setContenido(lista);
			modelo.fireTableDataChanged();
			
			revalidate();
			repaint();
			
		} catch (DAOException e1) {
			e1.printStackTrace();
			System.out.println("Error al cargar medicos en servicio");
			mostrarerror("ERROR Base de datos");
		} 
	}
	
	//display de error
	public void mostrarerror(String x) {
		if(x==this.ok) {
			errorDisplay.setForeground(Color.green);
			errorDisplay.setSize(100,20);
		}
		else {
			errorDisplay.setForeground(Color.RED);
			errorDisplay.setSize(800,50);
		}
		
		errorDisplay.setText(x);
		errorDisplay.validate();
		errorDisplay.repaint();
		System.out.println(x);
	}
	
	//verificar que el turno sea valido antes de cargar
	public boolean verificarValidezDeTurno(int consultorio, String fecha, String Hora, Medico med, Usuario usu) { 
		List<Turno> lista = null;
		if(med.getLegMedico()==usu.getDni()) {
			mostrarerror("el medico y el paciente no pueden ser iguales");
			return false;}
		try {
			lista = this.turnoService.listaTodosLosObjetos();
		} catch (DAOException e) {
			mostrarerror("error al cargar turnos");
			e.printStackTrace();
			return false;
		}
		String error="";
		Integer veriflvl=0;
		
		
		boolean loc1=verificarDisponibilidadConsultorio(lista, fecha,consultorio);
		boolean loc2=verificarQueElMedicoNoEsteEnOtroConsultorio(lista, fecha, consultorio, med.getLegMedico());
		boolean loc3=elMedicoEstaEnElConsultorio(lista, fecha, consultorio, med.getLegMedico());
		boolean disp1=verificarQueElMedicoEsteDisponible(lista, fecha, Hora, med);
		boolean disp2=verificarQueElUsuarioEsteDisponible(lista, fecha, Hora, usu);
		
		
		System.out.println("consultorio libre>"+loc1);
		System.out.println("medico no esta en otro consultorio>"+loc2);
		System.out.println("medico en cosultorio>"+loc3);
		System.out.println("med disp>"+disp1);
		System.out.println("pax disp>"+disp2);
		//loc es de localizacion
		if((loc1&&loc2) || (loc2&&loc3))//si el consultorio esta libre y el medico no esta en ningun otro consultorio
										//o si el medico no esta en otro consultorio y el medico esta en el consultorio
			{	
			veriflvl=1;
			System.out.println("Localizacion correcta");
			if(disp1&&disp2) {
				System.out.println("Disponibilidad de ambas partes");
				mostrarerror(ok);
				return true;
			}		
		}
		//seccion de error
		if(veriflvl==0) {
			if(!loc1){
				error = error + "consultorio ocupado, ";
			}
			if(!loc2) {
				error = error + "medico en otro consultorio";
			}
		}
		if(!disp1){
			error = error + "medico ocupado, ";
		}
		if(!disp2){
			error = error + "paciente ocupado";
		}
		mostrarerror(error);
		//no se pudo validar
		return false;
	}
	
	public boolean verificarQueElMedicoEsteDisponible(List<Turno> lista, String fecha, String Hora,Medico med) {
		for(Turno turn :lista) {
			if(turn.getFecha().equals(fecha) && turn.getHora().equals(Hora) && turn.getLegMedico()==med.getLegMedico() ) { 
				return false;
			}
		}
		return true;
	}
	
	public boolean verificarQueElUsuarioEsteDisponible(List<Turno> lista, String fecha, String Hora, Usuario use) {
		for(Turno turn :lista) {
			if(turn.getFecha().equals(fecha) && turn.getHora().equals(Hora) && turn.getDni()==use.getDni() ) { //comparo el 'dni' de Turno con el 'dni' de Usuario
					return false;
			}
		}
		return true;
	}
	

	public boolean verificarDisponibilidadConsultorio(List<Turno> lista, String fecha, int consultorio) {
		for(Turno turn :lista) {
			if(turn.getFecha().equals(fecha)) {
				if(turn.getConsultorio()==consultorio) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	public boolean elMedicoEstaEnElConsultorio(List<Turno> lista, String fecha, int consultorio, int legajo) {
		for(Turno turn :lista) {
			if (turn.getLegMedico()==legajo) {
				if(turn.getFecha().equals(fecha)) {
					if(turn.getConsultorio()==consultorio) {
						return true;
					}
				}
			}
		}
		return false;
	}
	

	public boolean verificarQueElMedicoNoEsteEnOtroConsultorio(List<Turno> lista, String fecha, int consultorio, int legajo) {
		for(Turno turn :lista) {
			if (turn.getLegMedico()==legajo) {
				if(turn.getFecha().equals(fecha)) {
					if(turn.getConsultorio()!=consultorio) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	
	public int generarTurnoNuevo() throws DAOException {
		int aux=0;
		List<Turno> x =this.turnoService.listaTodosLosObjetos();
		for(Turno turn : x)
		{
			if(turn.getTurno()>aux) {aux=turn.getTurno();}
		}
		return aux+1;
	}
}
