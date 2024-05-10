package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entidades.*;
import Service.*;
import paneles.*;

public class PanelManager implements ActionListener{
	
	private JFrame frame;
	private JPanel nav;
	private JPanel vista;
	private JPanel loginPanel;
	
	//conexion con usuario
	private Usuario sesionUsu;
	private UsuarioService usuService;
	
	//menus
	JButton verMenu;
	JButton reporteButton;
	JButton pacienteButton;
	JButton medicoButton;
	JButton adminButton;
	
	//usuario normal
	JButton consultarTurnosButton;
	JButton consultarGananciaButton;
	
	//Login
	Label dniLabel;
	Label passLabel;
	Label errorLabel;
	
	//Campos de texto
	private JTextField dniText;
	private JTextField passText;
	
	//botones login
	JButton loginButton;
	private JButton logoutButton;
	private JButton consultarTurnoPropioButton;
	private JButton consultarTurnoPacienteButton;
	
	PanelManager(){
		this.frame = new JFrame("Turnera medica Federico Hayes");
	}
	
	private void createAndShowLogin() {
		//sesion
		this.usuService =  new UsuarioService();
		
		//frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	    frame.setVisible(true);
	    frame.setLayout(new BorderLayout());
	    frame.setSize(1200,550);
		
	    //panel de navegacion
	    this.nav= new JPanel();
	    frame.add(nav, BorderLayout.NORTH);
	
	    this.vista= new JPanel();
	    frame.add(vista, BorderLayout.CENTER);
	    
	    Login();
	}
	 
	//inicializacion
	public void executeGUI() {
	   createAndShowLogin();
	}
	 
	 //muestra el Login
	public void Login() {
		this.loginPanel = new JPanel();
		loginPanel.setLayout(new GridLayout(2,2));
		loginPanel.setSize(1000,800);
		
		//panel principal
		this.dniLabel= new Label("DNI:");
		this.passLabel= new Label("PASSWORD:");
		this.errorLabel= new Label("Sesion iniciada.");
		errorLabel.setForeground(Color.GREEN);
		errorLabel.setBackground(Color.BLACK);
	    	
		//login text field
		this.dniText= new JTextField();
		this.passText= new JTextField();
		
		//login button
		this.loginButton= new JButton("LOGIN");
		loginButton.addActionListener(this);
		
		//cargar panel login
		loginPanel.add(dniLabel);
		loginPanel.add(dniText);
		loginPanel.add(passLabel);
		loginPanel.add(passText); 
		loginPanel.add(errorLabel);
		loginPanel.add(loginButton);
		
		//agregar a vista
		this.vista.add(loginPanel);
		vista.revalidate();
		vista.repaint();
	}
	
	public void Navegacion(){
		this.verMenu= new JButton("MENU");
		verMenu.addActionListener(this);
		nav.add(verMenu);
		this.logoutButton = new JButton("LogOut");
    	logoutButton.addActionListener(this);
    	nav.add(logoutButton);
    	nav.add(new Label("                  bienvenido: "+ sesionUsu.getNya()));
		nav.revalidate();
		nav.repaint();
	}
	
	public void mostrar(JPanel panel) {
		vista.removeAll();
		vista.add(panel);
		vista.validate();
		vista.repaint();
    }
	    
	//tengo que hacer la parte del menu
	
	public void menu() {
		vista.removeAll();
		 
		int tipo = sesionUsu.getTipo_usu();
		
		if(tipo == 1) {
			this.reporteButton = new JButton("Reporte");
    		reporteButton.addActionListener(this);
    		this.medicoButton = new JButton("Cargar Medico");
    		medicoButton.addActionListener(this);
    		this.pacienteButton = new JButton("Cargar Usuario/Paciente");
    		pacienteButton.addActionListener(this);
    		this.adminButton = new JButton("Administrar turnos");
    		adminButton.addActionListener(this);
    		this.consultarGananciaButton = new JButton("Consultar ganancia");
    		consultarGananciaButton.addActionListener(this);
    		this.consultarTurnosButton = new JButton("Consultar turnos");
    		consultarTurnosButton.addActionListener(this);
    		vista.add(reporteButton);
    		vista.add(medicoButton);
    		vista.add(pacienteButton);
    		vista.add(adminButton);
    		vista.add(consultarGananciaButton);
    		vista.add(consultarTurnosButton);
		}
		
		if(tipo == 2) {
			this.consultarTurnoPacienteButton = new JButton("Consultar turno del paciente");
			consultarTurnoPacienteButton.addActionListener(this);
			vista.add(consultarTurnoPacienteButton);
		}
		
		this.consultarTurnoPropioButton = new JButton("Consultar turno de paciente");
		consultarTurnoPropioButton.addActionListener(this);
		
		vista.validate();
    	vista.repaint();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == verMenu) {
			menu();
		}
		
		else if(e.getSource() == consultarGananciaButton) {
			mostrar(new ReporteMedicoEntreFechas()); 		
		}
		
		else if(e.getSource() == adminButton) {
			mostrar(new TablaMedicoPanel());
		}
		
		else if(e.getSource() == consultarTurnoPropioButton) {
			mostrar(new ListaConsultas(sesionUsu, 1));
		}
		
		else if(e.getSource() == consultarTurnoPacienteButton) {
			mostrar(new ListaConsultas(sesionUsu, 2));
		}
		
		else if(e.getSource() == consultarTurnosButton) {
			mostrar(new ListaConsultas(sesionUsu, 3));
		}
		
		else if(e.getSource() == medicoButton) {
			mostrar(new TablaMedicoPanel());
		}
		else if(e.getSource() == adminButton) {
			//mostrar(new PanelAdministrarTurnos()); //todavia tengo que hacerlo
		}
		else if(e.getSource() == reporteButton) {
			
			mostrar(new ReporteGananciaMedico());
		}
		
		else if(e.getSource() == logoutButton) {
			this.vista.removeAll();
			this.nav.removeAll();
			executeGUI();
		}
		
		else if(e.getSource() == loginButton) {
			
			try {
				int dni = Integer.parseInt(this.dniText.getText());
				System.out.println("Iniciando sesion...");
				
				try {
					this.sesionUsu= usuService.mostrar(dni);
					System.out.println("Usuario + Password");
					System.out.println(sesionUsu.getDni());
					System.out.println(sesionUsu.getPassword());
					
					if(this.sesionUsu.getPassword().equals(passText.getText())){
						System.out.println("contraseña Correcta");
						menu();
						Navegacion();
					}
					else {
						System.out.println("Contraseña Incorrecta");
						System.out.println(sesionUsu.getPassword());
						System.out.println(passText.getText());
						mostrarerror("Contraseña Incorrecta");
					}
					
				}catch(Exception e2) {
					mostrarerror("El usuario no existe");
				}
				
			}catch(Exception e2){
				mostrarerror("No se reconoce el DNI");
			}
		}
	}
	
	private void mostrarerror(String x) {
    	this.errorLabel.setForeground(Color.red);
    	this.errorLabel.setText(x);
    	this.errorLabel.validate();
    	this.errorLabel.repaint();
    	this.vista.validate();
    	this.vista.repaint();
    	System.out.println(x);
	}
}
