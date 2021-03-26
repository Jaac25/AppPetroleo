package Logic;

import java.sql.Date;

public class Limpieza {
	//Atributos
		private int idLimpieza;
		private int idDerramamiento;
		private int idComLim;
		private Date fecha;
		private String inversion;
	
	//Constructor
		public Limpieza(int idLimpieza,int idDerramamiento, int idComLim, Date fecha,String inversion) {
			this.idLimpieza = idLimpieza;
			this.idDerramamiento = idDerramamiento;
			this.idComLim = idComLim;
			this.fecha = fecha;
			this.inversion = inversion;
		}
	//Getters/Setters

		public int getIdLimpieza() {
			return idLimpieza;
		}

		public int getIdDerramamiento() {
			return idDerramamiento;
		}

		public int getIdComLim() {
			return idComLim;
		}

		public Date getFecha() {
			return fecha;
		}
		public String getInversion() {
			return inversion;
		}
}
