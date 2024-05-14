package paneles;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class AbstractCRUD extends JPanel implements ActionListener{

	private JButton botonLlenar;
	private JButton botonAgregar;
	private JButton botonBorrar;

	//botones generales
	private JPanel botonera;
	private JPanel botones;
	private JPanel campos;
	protected String ok; 
	private Label errorDisplay;
	
	
	AbstractCRUD(){
		this.setLayout(new FlowLayout());
		//configuracion de layout de paneles
		this.botonera= new JPanel();
		this.botonera.setLayout(new GridLayout(3, 1));
		this.botones= new JPanel();
		this.botones.setLayout(new FlowLayout());
		
		this.campos= new JPanel();
		this.campos.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.botones.setBackground(Color.cyan);
		this.campos.setBackground(Color.BLUE);
		this.errorDisplay= new Label();
		errorDisplay.setBackground(Color.BLACK);
		mostrarerror(ok);
		this.botonera.add(errorDisplay);
		//botones
		this.botonLlenar = new JButton("Refrescar datos");
		botonLlenar.addActionListener(this);
		this.botones.add(botonLlenar);

		this.botonBorrar = new JButton("Borrar Fila");
		botonBorrar.addActionListener(this);
		this.botones.add(botonBorrar);

		this.botonAgregar = new JButton("Ingresar usuario");
		botonAgregar.addActionListener(this);
		this.botones.add(botonAgregar);
				
				
		this.botonera.add(botones);//agregar botones a botonera
		this.botonera.add(campos);//agregar campos a botonera
		this.add(botonera);//agregar bottonera al panel principal
	}
	
	protected void agregarCampoCarga(String nombre, JTextField campo) {
		JPanel comboCampo = new JPanel();
		comboCampo.setLayout(new GridLayout(1,2));
		comboCampo.setSize(500,200);
		Label nombreCampoobra= new Label();
		nombreCampoobra.setText(nombre);
		comboCampo.add(nombreCampoobra);
		comboCampo.add(campo);
		campos.add(comboCampo);
	}
	
	protected void mostrarerror(String x) {
		if(x==this.ok) {
			errorDisplay.setForeground(Color.green);
		}
		else {
			errorDisplay.setForeground(Color.RED);
		}
		errorDisplay.setText(x);
		errorDisplay.validate();
		errorDisplay.repaint();
		System.out.println(x);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonAgregar) {agregar();} 
		else if(e.getSource() == botonBorrar) {borrar();}
		else if(e.getSource()==botonLlenar) {refresh();}
	}
	
	
	protected abstract void agregar();
	protected abstract void borrar();
	protected abstract void refresh();
}
