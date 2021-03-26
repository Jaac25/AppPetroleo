package Logic;

import java.util.ArrayList;

import ConnectBD.ConnectCausante;
import ConnectBD.ConnectDerramiento;
import ConnectBD.ConnectLimpieza;
import ConnectBD.ConnectLocalizacion;
import ConnectBD.ConnectComLim;

import java.sql.Date;

public class Management {
	
	private ConnectCausante connectCausante;
	private ConnectLocalizacion connectLocalizacion;
	private ConnectDerramiento connectDerramamiento;
	private ConnectComLim connectComLim;
	private ConnectLimpieza connectLimpieza;
	
	private ArrayList<Derramamiento> derramamientos;
	private ArrayList<Causante> causantes;
	private ArrayList<Localizacion> localizaciones;
	private ArrayList<Limpieza> limpiezas;
	private ArrayList<Compania_Limpieza> companiasLimpieza;
	
	//Constructor
		public Management() {
			derramamientos = new ArrayList<Derramamiento>();
			causantes = new ArrayList<Causante>();
			localizaciones = new ArrayList<Localizacion>();
			limpiezas = new ArrayList<Limpieza>();
			companiasLimpieza = new ArrayList<Compania_Limpieza>();
			
			connectCausante = new ConnectCausante();
			connectLocalizacion = new ConnectLocalizacion();
			connectDerramamiento = new ConnectDerramiento();
			connectComLim = new ConnectComLim();
			connectLimpieza = new ConnectLimpieza();
			
			cargarDatos();
		}
	
	private void cargarDatos() {
		causantes.addAll(connectCausante.leerCausantes());
		localizaciones.addAll(connectLocalizacion.leerLocalizaciones());
		derramamientos.addAll(connectDerramamiento.leerDerramiento());
		companiasLimpieza.addAll(connectComLim.leerCompanias());
		limpiezas.addAll(connectLimpieza.leerLimpiezas());
	}

	//Metodos
		//Derramamiento
			public boolean addDerramamiento(int idDerramamiento, int idCausante, int idLocalizacion, String area, Date fecha,String  magnitud){
				boolean verificar = connectDerramamiento.add(idDerramamiento, idCausante, idLocalizacion, area, fecha, magnitud);
				if(verificar) {
					Derramamiento derramamiento = new Derramamiento(idDerramamiento, idCausante, idLocalizacion, area, fecha, magnitud);
					derramamientos.add(derramamiento);
				}
				return verificar;
			}
			public boolean modifyDerramamiento(int pos,int idDerramamiento, int idCausante, int idLocalizacion, String area, Date fecha,String  magnitud) {
				boolean verificar = connectDerramamiento.modify(idDerramamiento, idCausante, idLocalizacion, area, fecha, magnitud);
				if(verificar) {
					Derramamiento derramamiento = new Derramamiento(idDerramamiento, idCausante, idLocalizacion, area, fecha, magnitud);
					derramamientos.set(pos,derramamiento);
				}
				return verificar;
			}
			public boolean deleteDerramamiento(int pos) {
				boolean verificar = true;
				Derramamiento derramamiento = buscarDerramamiento(pos);
				for (int i = 0; i < limpiezas.size(); i++) {
					if(derramamiento.getIdDerramamiento() == limpiezas.get(i).getIdDerramamiento()) {
						verificar = false;
						break;
					}
				}
				if(verificar) {
					verificar =connectDerramamiento.delete(pos);
					if(verificar) {
						derramamientos.remove(buscarDerramamiento(pos));
					}
				}	
				return verificar;
			}
			public String[][] mostrarDerramamiento(){
				String[][] datos = new String[derramamientos.size()][6];
				for (int i = 0; i < derramamientos.size(); i++) {
					datos[i][0] = String.valueOf(derramamientos.get(i).getIdDerramamiento());
					datos[i][1] = buscarCausante(derramamientos.get(i).getIdCausante()).getNombre();
					datos[i][2] = buscarLocalizacion(derramamientos.get(i).getIdLocalizacion()).getNombre();
					datos[i][3] = String.valueOf(derramamientos.get(i).getArea());
					datos[i][4] = String.valueOf(derramamientos.get(i).getFecha());
					datos[i][5] = String.valueOf(derramamientos.get(i).getMagnitud());
				}
				return datos;
			}
			private Derramamiento buscarDerramamiento(int idDerramamiento) {
				for (int i = 0; i < derramamientos.size(); i++) {
					if(idDerramamiento == derramamientos.get(i).getIdDerramamiento()) {
						return derramamientos.get(i);
					}
				}
				return null;
			}

			//Causante
				public boolean addCausante(int idCausante, String nombre, String telefono,String direccion) {
					boolean verificar = connectCausante.add(idCausante, nombre, telefono, direccion);
					if(verificar) {
						Causante causante = new Causante(idCausante, nombre, telefono, direccion);
						causantes.add(causante);
					}
					return verificar;
				}
				public boolean modifyCausante(int pos,int idCausante, String nombre, String telefono,String direccion) {
					boolean verificar = connectCausante.modify(idCausante, nombre, telefono, direccion);
					if(verificar) {
						Causante causante = new Causante(idCausante, nombre, telefono, direccion);
						causantes.set(pos,causante);
					}
					return verificar;
				}
				
				public String[][] mostrarCausantes(){
					String[][] datos = new String[causantes.size()][4];
					for (int i = 0; i < causantes.size(); i++) {
						datos[i][0] = String.valueOf(causantes.get(i).getIdCausante());
						datos[i][1] = causantes.get(i).getNombre();
						datos[i][2] = causantes.get(i).getTelefono();
						datos[i][3] = causantes.get(i).getDireccion();
					}
					return datos;
				}
				public boolean deleteCausante(int pos) {
					boolean verificar = true;
					Causante causante = buscarCausante(pos);
					for (int i = 0; i < derramamientos.size(); i++) {
						if(causante.getIdCausante() == derramamientos.get(i).getIdCausante()) {
							verificar = false;
							break;
						}
					}
					if(verificar) {
						verificar = connectCausante.delete(pos);
						if(verificar) {
							causantes.remove(buscarCausante(pos));
						}
					}	
					return verificar;
				}
				private Causante buscarCausante(int idCausante) {
					for (int i = 0; i < causantes.size(); i++) {
						if(idCausante == causantes.get(i).getIdCausante()) {
							return causantes.get(i);
						}
					}
					return null;
				}
				
			//Localizacion
				public boolean addLocalizacion(int idLocalizacion,String nombre, String latitud,String longitud) {
//					boolean verificar = true;
					boolean verificar = connectLocalizacion.add(idLocalizacion, nombre, latitud, longitud);
					if(verificar) {
						Localizacion localizacion = new Localizacion(idLocalizacion, nombre, latitud, longitud);
						localizaciones.add(localizacion);
					}
					return verificar;
				}
				public boolean modifyLocalizacion(int pos,int idLocalizacion,String nombre, String latitud,String longitud) {
					boolean verificar = connectLocalizacion.modify(idLocalizacion, nombre, latitud, longitud);
					if(verificar) {
						Localizacion localizacion = new Localizacion(idLocalizacion, nombre, latitud, longitud);
						localizaciones.set(pos,localizacion);
					}
					return verificar;
				}
				public boolean deleteLocalizacion(int pos) {
					boolean verificar = true;
					Localizacion localizacion = buscarLocalizacion(pos);
					for (int i = 0; i < derramamientos.size(); i++) {
						if(localizacion.getIdLocalizacion() == derramamientos.get(i).getIdLocalizacion()) {
							verificar = false;
							break;
						}
					}
					if(verificar) {
						verificar = connectLocalizacion.delete(pos);
						if(verificar) {
							localizaciones.remove(localizacion);
						}
					}	
					return verificar;
				}
				public String[][] mostrarLocalizaciones(){
					String[][] datos = new String[localizaciones.size()][4];
					for (int i = 0; i < localizaciones.size(); i++) {
						datos[i][0] = String.valueOf(localizaciones.get(i).getIdLocalizacion());
						datos[i][1] = localizaciones.get(i).getNombre();
						datos[i][2] = localizaciones.get(i).getLatitud();
						datos[i][3] = localizaciones.get(i).getLongitud();
					}
					return datos;
				}
				private Localizacion buscarLocalizacion(int idLocalizacion) {
					for (int i = 0; i < localizaciones.size(); i++) {
						if(idLocalizacion == localizaciones.get(i).getIdLocalizacion()) {
							return localizaciones.get(i);
						}
					}
					return null;
				}
			//CompañiaLimpieza
				public boolean addComLim(int idComLim,String nombre,String direccion, String telefono) {
					boolean verificar = connectComLim.add(idComLim, nombre, telefono, direccion);
					if(verificar) {
						Compania_Limpieza com = new Compania_Limpieza(idComLim, nombre, direccion, telefono);
						companiasLimpieza.add(com);
					}
					return verificar;
				}
				public boolean modifyComLim(int pos, int idComLim, String nombre, String telefono, String direccion) {
					boolean verificar = connectComLim.modify( idComLim,  nombre,  telefono,  direccion);
					if (verificar) {
						Compania_Limpieza com = new Compania_Limpieza(idComLim, nombre, direccion, telefono);
						companiasLimpieza.set(pos,com);
					}
					return verificar;
				}
				public boolean deleteComLim(int pos) {
					boolean verificar = true;
					System.out.println(pos);
					Compania_Limpieza comp = buscarComLim(pos);
					for (int i = 0; i < limpiezas.size(); i++) {
						if(comp.getIdComLim() == limpiezas.get(i).getIdComLim()) {
							verificar = false;
							break;
						}
					}
					if(verificar) {
						verificar = connectComLim.delete(pos);
						if(verificar) {
							companiasLimpieza.remove(buscarComLim(pos));
						}
					}	
					return verificar;
				}
				public String[][] mostrarComLim(){
					String[][] datos = new String[companiasLimpieza.size()][4];
					for (int i = 0; i < companiasLimpieza.size(); i++) {
						datos[i][0] = String.valueOf(companiasLimpieza.get(i).getIdComLim());
						datos[i][1] = companiasLimpieza.get(i).getNombre();
						datos[i][2] = companiasLimpieza.get(i).getDireccion();
						datos[i][3] = companiasLimpieza.get(i).getTelefono();
					}
					return datos;
				}
				private Compania_Limpieza buscarComLim(int idComLim) {
					for (int i = 0; i < companiasLimpieza.size(); i++) {
						if(idComLim == companiasLimpieza.get(i).getIdComLim()) {
							return companiasLimpieza.get(i);
						}
					}
					return null;
				}
			//Limpieza
				public boolean addLimpieza(int idLimpieza,int idDerramamiento,int idComLim, Date fecha, String inversion) {
					boolean verificar = connectLimpieza.add(idLimpieza, idDerramamiento, idComLim, fecha, inversion);
					if (verificar) {
						Limpieza lim = new Limpieza(idLimpieza, idDerramamiento, idComLim, fecha, inversion);
						limpiezas.add(lim);
					}
					return verificar;
				}
				public boolean modifyLimpieza(int pos,int idLimpieza,int idDerramamiento,int idComLim, Date fecha, String inversion) {
					boolean verificar = connectLimpieza.modify(idLimpieza, idDerramamiento, idComLim, fecha, inversion);
					if(verificar) {
						Limpieza limpieza = new Limpieza(idLimpieza, idDerramamiento, idComLim, fecha, inversion);
						limpiezas.set(pos, limpieza);
					}
					return verificar;
				}
				public boolean deleteLimpieza(int pos) {
					boolean verificar = connectLimpieza.delete(pos);
					if(verificar) {
						limpiezas.remove(buscarLimpieza(pos));
					}
					return verificar;
				}
				private Limpieza buscarLimpieza(int idLimpieza) {
					for (int i = 0; i < limpiezas.size(); i++) {
						if(idLimpieza == limpiezas.get(i).getIdLimpieza()) {
							return limpiezas.get(i);
						}
					}
					return null;
				}

				public String[][] mostrarLimpiezas() {
					String[][] datos = new String[limpiezas.size()][5];
					for (int i = 0; i < limpiezas.size(); i++) {
						datos[i][0] = String.valueOf(limpiezas.get(i).getIdLimpieza());
						datos[i][1] = String.valueOf(buscarDerramamiento(limpiezas.get(i).getIdDerramamiento()).getIdDerramamiento());
						datos[i][2] = buscarComLim(limpiezas.get(i).getIdComLim()).getNombre();
						datos[i][3] = String.valueOf(limpiezas.get(i).getFecha());
						datos[i][4] = String.valueOf(limpiezas.get(i).getInversion());
					}
					return datos;
				}

	//Getters/Setters

		public ArrayList<Derramamiento> getDerramamientos() {
			return derramamientos;
		}

		public ArrayList<Causante> getCausantes() {
			return causantes;
		}

		public ArrayList<Localizacion> getLocalizaciones() {
			return localizaciones;
		}

		public ArrayList<Limpieza> getLimpiezas() {
			return limpiezas;
		}

		public ArrayList<Compania_Limpieza> getCompaniasLimpieza() {
			return companiasLimpieza;
		}
}
