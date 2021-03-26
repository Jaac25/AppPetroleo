package ConnectBD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Logic.Causante;
import Logic.Derramamiento;

public class ConnectDerramiento {
	private Connect connect = new Connect();
	private ArrayList<Derramamiento> derramientos = new ArrayList<Derramamiento>();
	private Connection con = null;
	public ArrayList<Derramamiento> leerDerramiento(){
		try {
			con = connect.getConection();
			if(con != null) {
				System.out.println("Conexión exitosa = Derramamiento");
				PreparedStatement ps;
				ResultSet resDerramamiento;
				ps = con.prepareStatement("Select * from derramamiento order by id_derramamiento");
				resDerramamiento = ps.executeQuery();
				while(resDerramamiento.next()) {
					Derramamiento derramamiento = new Derramamiento(resDerramamiento.getInt("id_Derramamiento"), resDerramamiento.getInt("id_Causante"), resDerramamiento.getInt("id_Localizacion"), resDerramamiento.getString("area"), resDerramamiento.getDate("fecha"), resDerramamiento.getString("magnitud"));
					derramientos.add(derramamiento);
				}
				con.close();
			}	
		} catch (Exception e) {
			System.out.println("Error 1: "+e);
		}
		return derramientos;
	}
	public boolean add(int idDerramamiento,int idCausante, int idLocalizacion,String area, Date fecha, String magnitud) {
		boolean verificar = true;
		try {
			con = connect.getConection();
			PreparedStatement ps;
			ps = con.prepareStatement("insert into derramamiento (id_Derramamiento,id_causante,id_Localizacion,area,fecha,magnitud) VALUES(?,?,?,?,?,?)");
			ps.setInt(1, idDerramamiento);
			ps.setInt(2, idCausante);
			ps.setInt(3, idLocalizacion);
			ps.setString(4, area);
			ps.setDate(5,fecha);
			ps.setString(6,magnitud);
			int res = ps.executeUpdate(); 
			if(res <= 0 ) {
				System.out.println(res);
				verificar = false;
			}
			con.close();
		}catch (Exception e) {
			System.out.println("Error 1 Guardar derramamiento: "+e);
		}
		return verificar;
	}
	public boolean modify(int idDerramamiento,int idCausante, int idLocalizacion,String area, Date fecha, String magnitud) {
		boolean verificar = true;
		try {
			con = connect.getConection();
			PreparedStatement ps;
			ps = con.prepareStatement("update derramamiento set id_causante = ?,id_localizacion = ?,area=?,fecha = ?, magnitud = ? where id_derramamiento = ?");
			ps.setInt(1, idCausante);
			ps.setInt(2, idLocalizacion);
			ps.setString(3, area);
			ps.setDate(4, fecha);
			ps.setString(5, magnitud);
			ps.setInt(6, idDerramamiento);
			int res = ps.executeUpdate(); 
			if(res <= 0 ) {
				verificar = false;
			}
			con.close();
		}catch (Exception e) {
			System.out.println("Error 1 Modificar Derramamiento: "+e);
		}
		return verificar;
	}
	public boolean delete(int idDerramammiento) {
		boolean verificar = true;
		try {
			con = connect.getConection();
			PreparedStatement ps;
			ps = con.prepareStatement("delete from derramamiento where id_Derramamiento = ?");
			ps.setInt(1, idDerramammiento);
			int res = ps.executeUpdate(); 
			if(res <= 0 ) {
				verificar = false;
			}
			con.close();
		}catch (Exception e) {
			System.out.println("Error 1 Eliminar Derramamiento: "+e);
		}
		return verificar;
	}
}
