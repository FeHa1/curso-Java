package Service;

import dao.InterBaseDAO;
import dao.UsuarioDAO;
import entidades.Usuario;

public class UsuarioService extends AbstractService<Usuario, InterBaseDAO<Usuario>>{
	
	public UsuarioService() {
		super(new UsuarioDAO());
	}
}
