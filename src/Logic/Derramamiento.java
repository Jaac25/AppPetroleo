package Logic;

import java.sql.Date;

public class Derramamiento {
	//Atributos
		private int idDerramamiento;
		private int idCausante;
		private int idLocalizacion;
		private String area;
		private Date fecha;
		private String magnitud;
	
	//Constructor
	public Derramamiento(int idDerramamiento, int idCausante, int idLocalizacion, String area, Date fecha,String magnitud) {
		this.idDerramamiento = idDerramamiento;
		this.idCausante = idCausante;
		this.idLocalizacion = idLocalizacion;
		this.area = area;
		this.fecha = fecha;
		this.magnitud = magnitud;
	}
	
	//Getters/Setters
		public int getIdDerramamiento() {
			return idDerramamiento;
		}
		public int getIdCausante() {
			return idCausante;
		}
		public int getIdLocalizacion() {
			return idLocalizacion;
		}
		public String getArea() {
			return area;
		}
		public Date getFecha() {
			return fecha;
		}
		public String getMagnitud() {
			return magnitud;
		}
}
