package Service;

import dao.InterBaseDAO;
import dao.TurnoDAO;
import entidades.Turno;


public class TurnoService extends AbstractService<Turno, InterBaseDAO<Turno>>{
	public TurnoService() {
		super(new TurnoDAO());
	}
}
