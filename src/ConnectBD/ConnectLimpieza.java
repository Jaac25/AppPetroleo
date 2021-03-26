package ConnectBD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Logic.Limpieza;

public class ConnectLimpieza {
	private Connect connect = new Connect();
	private ArrayList<Limpieza> limpiezas = new ArrayList<Limpieza>();
	private Connection con = null;
	public ArrayList<Limpieza> leerLimpiezas(){
		try {
			con = connect.getConection();
			if(con != null) {
				System.out.println("Conexión exitosa = Limpieza");
				PreparedStatement ps;
				ResultSet resLimpiezas;
				ps = con.prepareStatement("select * from servicio_limpieza order by id_servicio");
				resLimpiezas = ps.executeQuery();
				while(resLimpiezas.next()) {
					Limpieza limpieza = new Limpieza(resLimpiezas.getInt("id_servicio"), resLimpiezas.getInt("id_Derramamiento"), resLimpiezas.getInt("id_Compania"), 
							resLimpiezas.getDate("fecha"), resLimpiezas.getString("inversion"));
					limpiezas.add(limpieza);
				}
				con.close();
			}	
		} catch (Exception e) {
			System.out.println("Error 1: "+e);
		}
		return limpiezas;
	}
	public boolean add(int idLimpieza,int idDerramamiento,int idComLim,Date fecha, String inversion) {
		boolean verificar = true;
		try {
			con = connect.getConection();
			PreparedStatement ps;
			ps = con.prepareStatement("insert into servicio_limpieza (id_servicio,id_Derramamiento,id_Compania,fecha,inversion) VALUES(?,?,?,?,?)");
			ps.setInt(1, idLimpieza);
			ps.setInt(2, idDerramamiento);
			ps.setInt(3, idComLim);
			ps.setDate(4,fecha);
			ps.setString(5,inversion);
			int res = ps.executeUpdate(); 
			if(res <= 0 ) {
				verificar = false;
			}
			con.close();
		}catch (Exception e) {
			System.out.println("Error 1 Guardar limpieza: "+e);
		}
		return verificar;
	}
	public boolean modify(int idLimpieza,int idDerramamiento,int idComLim,Date fecha, String inversion) {
		boolean verificar = true;
		try {
			con = connect.getConection();
			PreparedStatement ps;
			ps = con.prepareStatement("update servicio_limpieza set id_derramamiento = ?,id_compania = ? , fecha = ? ,inversion = ? where id_servicio = ?");
			ps.setInt(1, idDerramamiento);
			ps.setInt(2, idComLim);
			ps.setDate(3, fecha);
			ps.setString(4, inversion);
			ps.setInt(5, idLimpieza);
			int res = ps.executeUpdate(); 
			if(res <= 0 ) {
				verificar = false;
			}
			con.close();
		}catch (Exception e) {
			System.out.println("Error 1 Modificar Limpieza: "+e);
		}
		return verificar;
	}
	public boolean delete(int idLimpieza) {
		boolean verificar = true;
		try {
			con = connect.getConection();
			PreparedStatement ps;
			ps = con.prepareStatement("delete from servicio_limpieza where id_servicio = ?");
			ps.setInt(1, idLimpieza);
			int res = ps.executeUpdate(); 
			if(res <= 0 ) {
				verificar = false;
			}
			con.close();
		}catch (Exception e) {
			System.out.println("Error 1 Eliminar Limpieza: "+e);
		}
		return verificar;
	}
}
