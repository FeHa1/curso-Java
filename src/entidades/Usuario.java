package entidades;

public class Usuario {
	
	protected int dni;
	protected String password;
	protected String nya;
	protected String fecha_nac;
	protected int tipo_usu; //1 a (administrador) , 2 m (medico), 3 p (paciente) 
	
	public Usuario() {
		
	}

	//constructor
	public Usuario(int dni, String password, String nya, String fecha_nac, int tipo_usu){
		this.dni=dni;
		this.password=password;
		this.nya=nya;
		this.fecha_nac=fecha_nac;
		this.tipo_usu=tipo_usu;
	}

	//click derecho, Source, Generate Getters and Setters
	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNya() {
		return nya;
	}

	public void setNya(String nya) {
		this.nya = nya;
	}

	public String getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(String fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public int getTipo_usu() {
		return tipo_usu;
	}

	public void setTipo_usu(int tipo_usu) {
		this.tipo_usu = tipo_usu;
	}
} 
