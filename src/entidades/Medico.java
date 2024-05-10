package entidades;

import dao.DAOException;
import Service.UsuarioService;

public class Medico extends Usuario{
	
	private int legMedico; //legajo medico es igual al dni usuario
	private double cobra;
	
	public Medico() {
		
	}
	
	public Medico(int legMedico, double cobra)  {
		
        super(legMedico, obtenerUsuario(legMedico).getPassword(), obtenerUsuario(legMedico).getNya(), obtenerUsuario(legMedico).getFecha_nac(), obtenerUsuario(legMedico).getObraSocial(), obtenerUsuario(legMedico).getTipo_usu());
        this.legMedico = legMedico;
        this.cobra = cobra;
    }

	public int getLegMedico() {
		return legMedico;
	}


	public void setLegMedico(int legMedico) {
		this.legMedico = legMedico;
	}


	public double isCobra() {
		return cobra;
	}


	public void setCobra(double cobra) {
		this.cobra = cobra;
	}
	
	private static Usuario obtenerUsuario(int legMedico) {
		UsuarioService serv = new UsuarioService();
        try {
			return serv.mostrar(legMedico);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Usuario x = new Usuario(404, "error", "error", "error", "error", 404);
			return x;
		}
	}
	
}
