package entidades;

public class Turno {
	//el medico y el usuario se van a conectar por medio de 'dni' y 'legMedico' 
	private int dni;
	private int legMedico;
	private int turno;
	private String fecha;
	private String hora;
	private int consultorio;
	
	public Turno() {
		
	}
	
	public Turno(int dni, int legMedico, int turno, String fecha, String hora, int consultorio) {
		this.setDni(dni);
		this.setLegMedico(legMedico);
		this.setTurno(turno);
		this.setFecha(fecha);
		this.setHora(hora);
		this.setConsultorio(consultorio);
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public int getLegMedico() {
		return legMedico;
	}

	public void setLegMedico(int legMedico) {
		this.legMedico = legMedico;
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public int getConsultorio() {
		return consultorio;
	}
	
	public void setConsultorio(int consultorio) {
		this.consultorio = consultorio;
	}
}
