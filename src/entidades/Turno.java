package entidades;

public class Turno extends Medico{
	private int turno;
	private String fecha;
	private String hora;
	
	public Turno() {
		
	}

	public Turno(int dni, String password, String nya, String fecha_nac, String obraSocial, int tipo_usu, int legMedico, boolean cobra, int turno, String fecha, String hora) {
		super(dni, password, nya, fecha_nac, obraSocial, tipo_usu, turno, cobra);
		this.turno = turno;
		this.fecha = fecha; 
		this.hora = hora; 
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
	
}
