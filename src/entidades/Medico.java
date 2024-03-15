package entidades;

public class Medico extends Usuario{
	
	private int legMedico; //legajo medico es igual al dni usuario
	private boolean cobra; 
	
	public Medico() {
		
	}
	
	public Medico(int dni, String password, String nya, String fecha_nac, String obraSocial, int tipo_usu, int legMedico, boolean cobra) {
		super(dni, password, nya, fecha_nac, obraSocial, tipo_usu);
		this.legMedico = legMedico; 
		this.cobra = cobra; 
	}


	public int getLegMedico() {
		return legMedico;
	}


	public void setLegMedico(int legMedico) {
		this.legMedico = legMedico;
	}


	public boolean isCobra() {
		return cobra;
	}


	public void setCobra(boolean cobra) {
		this.cobra = cobra;
	}
}
