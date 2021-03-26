package Logic;

public class Localizacion{
	private int idLocalizacion;
	private String nombre;
	private String latitud;
	private String longitud;
	
	//Constructor
	public Localizacion(int idLocalizacion, String nombre,String latitud,String longitud) {
		this.idLocalizacion = idLocalizacion;
		this.nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;
	}
	//Getters/Setters

	public int getIdLocalizacion() {
		return idLocalizacion;
	}

	public String getNombre() {
		return nombre;
	}

	public String getLatitud() {
		return latitud;
	}

	public String getLongitud() {
		return longitud;
	}
		
}
