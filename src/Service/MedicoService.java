package Service;

import dao.InterBaseDAO;
import dao.MedicoDAO;
import entidades.Medico;

public class MedicoService extends AbstractService<Medico, InterBaseDAO<Medico>>{
	
	public MedicoService() {
		super(new MedicoDAO());
	}
}
