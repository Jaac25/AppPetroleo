package Logic;

public class Causante {
	
	//Atributos
	private int idCausante;
	private String nombre;
	private String telefono;
	private String direccion;
	
	//Constructor
		public Causante(int idCausante, String nombre, String telefono,String direccion) {
			this.idCausante = idCausante;
			this.nombre = nombre;
			this.telefono = telefono;
			this.direccion = direccion;
		}
	//Getters/Setters

		public int getIdCausante() {
			return idCausante;
		}

		public String getNombre() {
			return nombre;
		}

		public String getTelefono() {
			return telefono;
		}

		public String getDireccion() {
			return direccion;
		}
		
}
