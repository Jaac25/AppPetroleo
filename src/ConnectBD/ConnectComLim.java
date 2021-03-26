package ConnectBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ConnectBD.Connect;
import Logic.Compania_Limpieza;

public class ConnectComLim {
	private Connect connect = new Connect();
	private ArrayList<Compania_Limpieza> companias = new ArrayList<Compania_Limpieza>();
	private Connection con = null;
	public ArrayList<Compania_Limpieza> leerCompanias(){
		try {
			con = connect.getConection();
			if(con != null) {
				System.out.println("Conexi�n exitosa = Compa�ias");
				PreparedStatement ps;
				ResultSet resCompanias;
				ps = con.prepareStatement("select * from COMPA�IA_LIMPIEZA order by ID_COMPA�IA;");
				resCompanias = ps.executeQuery();
				while(resCompanias.next()) {
					Compania_Limpieza comLim = new Compania_Limpieza(resCompanias.getInt("id_compa�ia"), resCompanias.getString("nombre"), resCompanias.getString("direccion"),resCompanias.getString("telefono"));
					companias.add(comLim);
				}
				con.close();
			}	
		} catch (Exception e) {
			System.out.println("Error 1: "+e);
		}
		return companias;
	}
	public boolean add(int idCompania, String nombre, String telefono, String direccion) {
		boolean verificar = true;
		try {
			con = connect.getConection();
			PreparedStatement ps;
			ps = con.prepareStatement("insert into compa�ia_limpieza(id_compa�ia,nombre,direccion,telefono) VALUES(?,?,?,?)");
			ps.setInt(1, idCompania);
			ps.setString(2, nombre);
			ps.setString(3, direccion);
			ps.setString(4, telefono);
			int res = ps.executeUpdate(); 
			if(res <= 0 ) {
				verificar = false;
			}
			con.close();
		}catch (Exception e) {
			System.out.println("Error 1 Guardar Compania: "+e);
		}
		return verificar;
	}
	public boolean modify(int idComLim, String nombre, String telefono, String direccion) {
		boolean verificar = true;
		try {
			con = connect.getConection();
			PreparedStatement ps;
			ps = con.prepareStatement("update compa�ia_limpieza set nombre = ?,telefono = ?,direccion=? where id_compa�ia = ?");
//			ps = con.prepareStatement("update cliente set Nombres = ?,Apellidos = ?, cedula = ? where idCliente = ?");
			ps.setString(1, nombre);
			ps.setString(2, direccion);
			ps.setString(3, telefono);
			ps.setInt(4, idComLim);
			int res = ps.executeUpdate(); 
			if(res <= 0 ) {
				verificar = false;
			}
			con.close();
		}catch (Exception e) {
			System.out.println("Error 1 Modificar Compania: "+e);
		}
		return verificar;
	}
	public boolean delete(int idComLim) {
		boolean verificar = true;
		try {
			con = connect.getConection();
			PreparedStatement ps;
			ps = con.prepareStatement("delete from compa�ia_limpieza where id_compa�ia = ?");
			ps.setInt(1, idComLim);
			int res = ps.executeUpdate(); 
			if(res <= 0 ) {
				verificar = false;
			}
			con.close();
		}catch (Exception e) {
			System.out.println("Error 1 Eliminar Compania: "+e);
		}
		return verificar;
	}
}
