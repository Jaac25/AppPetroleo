package Logic;

public class Compania_Limpieza {
	//Atributos
		private int idComLim;
		private String nombre;
		private String direccion;
		private String telefono;
	//Constructor
		public Compania_Limpieza(int idComLim,String nombre, String direccion,String telefono) {
			this.idComLim = idComLim;
			this.nombre = nombre;
			this.direccion = direccion;
			this.telefono = telefono;
		}
	//Getters/Setters
		public int getIdComLim() {
			return idComLim;
		}
		public String getNombre() {
			return nombre;
		}
		public String getDireccion() {
			return direccion;
		}
		public String getTelefono() {
			return telefono;
		}
		
}
