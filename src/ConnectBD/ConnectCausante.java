package ConnectBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Logic.Causante;

public class ConnectCausante {
	private Connect connect = new Connect();
	private ArrayList<Causante> causantes = new ArrayList<Causante>();
	private Connection con = null;
	public ArrayList<Causante> leerCausantes(){
		try {
			con = connect.getConection();
			if(con != null) {
				System.out.println("Conexión exitosa = Causantes");
				PreparedStatement ps;
				ResultSet resCausantes;
				ps = con.prepareStatement("Select * from causante order by id_Causante");
				resCausantes = ps.executeQuery();
				while(resCausantes.next()) {
					Causante causante = new Causante(resCausantes.getInt("ID_CAUSANTE"), resCausantes.getString("Nombre"), resCausantes.getString("TELEFONO"), resCausantes.getString("DIRECCION"));
					causantes.add(causante);
				}
				con.close();
			}	
		} catch (Exception e) {
			System.out.println("Error 1: "+e);
		}
		return causantes;
	}
	public boolean add(int idCausante, String nombre, String telefono, String direccion) {
		boolean verificar = true;
		try {
			con = connect.getConection();
			PreparedStatement ps;
			ps = con.prepareStatement("insert into causante (id_causante,nombre,telefono,direccion) VALUES(?,?,?,?)");
			ps.setInt(1, idCausante);
			ps.setString(2, nombre);
			ps.setString(3, telefono);
			ps.setString(4, direccion);
			int res = ps.executeUpdate(); 
			if(res <= 0 ) {
				verificar = false;
			}
			con.close();
		}catch (Exception e) {
			System.out.println("Error 1 Guardar causante: "+e);
		}
		return verificar;
	}
	public boolean modify(int idCausante, String nombre, String telefono, String direccion) {
		boolean verificar = true;
		try {
			con = connect.getConection();
			PreparedStatement ps;
			ps = con.prepareStatement("update causante set nombre = ?,telefono = ?,direccion=? where id_causante = ?");
//			ps = con.prepareStatement("update cliente set Nombres = ?,Apellidos = ?, cedula = ? where idCliente = ?");
			ps.setString(1, nombre);
			ps.setString(2, direccion);
			ps.setString(3, telefono);
			ps.setInt(4, idCausante);
			int res = ps.executeUpdate(); 
			if(res <= 0 ) {
				verificar = false;
			}
			con.close();
		}catch (Exception e) {
			System.out.println("Error 1 Modificar Causante: "+e);
		}
		return verificar;
	}
	public boolean delete(int idCausante) {
		boolean verificar = true;
		try {
			con = connect.getConection();
			PreparedStatement ps;
			ps = con.prepareStatement("delete from causante where id_Causante = ?");
			ps.setInt(1, idCausante);
			int res = ps.executeUpdate(); 
			if(res <= 0 ) {
				verificar = false;
			}
			con.close();
		}catch (Exception e) {
			System.out.println("Error 1 Eliminar Causante: "+e);
		}
		return verificar;
	}
}
