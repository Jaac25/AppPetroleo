package ConnectBD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Logic.Localizacion;

public class ConnectLocalizacion {
	private Connect connect = new Connect();
	private ArrayList<Localizacion> localizaciones = new ArrayList<Localizacion>();
	private Connection con = null;
	public ArrayList<Localizacion> leerLocalizaciones(){
		try {
			con = connect.getConection();
			if(con != null) {
				System.out.println("Conexión exitosa = Localizaciones");
				PreparedStatement ps;
				ResultSet resLocalizaciones;
				ps = con.prepareStatement("Select * from localizacion order by id_localizacion");
				resLocalizaciones = ps.executeQuery();
				while(resLocalizaciones.next()) {
					Localizacion localizacion = new Localizacion(resLocalizaciones.getInt("ID_LOCALIZACION"), resLocalizaciones.getString("pais"),resLocalizaciones.getString("latitud"),resLocalizaciones.getString("longitud"));
					localizaciones.add(localizacion);
				}
				con.close();
			}	
		} catch (Exception e) {
			System.out.println("Error 1: "+e);
		}
		return localizaciones;
	}
	public boolean add(int idLocalizacion, String nombre, String latitud, String longitud) {
		boolean verificar = true;
		try {
			con = connect.getConection();
			PreparedStatement ps;
			ps = con.prepareStatement("insert into localizacion (id_localizacion,pais,latitud,longitud) VALUES(?,?,?,?)");
			ps.setInt(1, idLocalizacion);
			ps.setString(2, nombre);
			ps.setString(3, latitud);
			ps.setString(4, longitud);
			int res = ps.executeUpdate(); 
			if(res <= 0 ) {
				verificar = false;
			}
			con.close();
		}catch (Exception e) {
			System.out.println("Error 1 Guardar localizacion: "+e);
		}
		return verificar;
	}
	public boolean modify(int idLocalizacion, String nombre, String latitud, String longitud) {
		boolean verificar = true;
		try {
			con = connect.getConection();
			PreparedStatement ps;
			ps = con.prepareStatement("update localizacion set pais = ? , latitud = ? ,longitud = ? where id_localizacion = ?");
			ps.setString(1, nombre);
			ps.setString(2, latitud);
			ps.setString(3, longitud);
			ps.setInt(4, idLocalizacion);
			int res = ps.executeUpdate(); 
			if(res <= 0 ) {
				verificar = false;
			}
			con.close();
		}catch (Exception e) {
			System.out.println("Error 1 Modificar Localización: "+e);
		}
		return verificar;
	}
	public boolean delete(int idLocalizacion) {
		boolean verificar = true;
		try {
			con = connect.getConection();
			PreparedStatement ps;
			ps = con.prepareStatement("delete from localizacion where id_localizacion = ?");
			ps.setInt(1, idLocalizacion);
			int res = ps.executeUpdate(); 
			if(res <= 0 ) {
				verificar = false;
			}
			con.close();
		}catch (Exception e) {
			System.out.println("Error 1 Eliminar Localización: "+e);
		}
		return verificar;
	}
}
