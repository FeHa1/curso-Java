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

import entidades.Usuario;
import Service.UsuarioService;

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
	JButton adminButton;
	JButton pacienteButton;
	JButton medicoButton;
	JButton reporteButton;
	
	//Login
	Label dniLabel;
	Label passLabel;
	Label errorLabel;
	
	//Campos de texto
	JTextField dniText;
	JTextField passText;
	JButton loginButton;
	
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
		
		if (tipo >= 2) {
    		this.reporteButton= new JButton("Reporte");
    		reporteButton.addActionListener(this);
    		vista.add(reporteButton);
    	}
		
		if(sesionUsu.getTipo_usu() == 3) {
			this.adminButton= new JButton("Administrar Medicos");
	    	adminButton.addActionListener(this);
	    	this.medicoButton= new JButton("Administrar Turnos");
	    	medicoButton.addActionListener(this);
	    	this.pacienteButton= new JButton("Administrar Paciente");
	    	pacienteButton.addActionListener(this);
	    	vista.add(adminButton);
	    	vista.add(medicoButton);
	    	vista.add(pacienteButton);
		}
		
		vista.validate();
    	vista.repaint();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == verMenu) {
			menu();
		}
		//cuando haga el 'TablaTurnoPanel' lo descomento
		/*
		else if(e.getSource() == medicoButton) {
			mostrar(new TablaTurnoPanel()); 
	    				
		}
		else if(e.getSource() == adminButton) {
			mostrar(new TablaMedicoPanel());
		}
		else if(e.getSource() == pacienteButton) {
			mostrar(new TablaUsuarioPanel());
		}
		else if(e.getSource() == reporteButton) {
			mostrar(new TablaReportePanel());
		}
		*/
		else if(e.getSource()== loginButton) {
			
			int dni = Integer.parseInt(this.dniText.getText());
			System.out.println("Iniciando sesion...");
			try {
				this.sesionUsu = usuService.muestraObjeto(dni); //preguntarle ésta parte a Tomi
				menu();
				Navegacion();
			} catch (Exception e2) {
				mostrarerror("DNI no existe.");
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
