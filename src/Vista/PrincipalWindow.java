package Vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Logic.Management;

public class PrincipalWindow extends JFrame {
	
	private Management management;
	
	private JTabbedPane barra;
	
	private CrudCausante causante;
	private CrudComLim comLimpieza;
	private CrudDerramamiento derramamiento;
	private CrudLimpieza limpieza;
	private CrudLocalizacion localizacion;

	public PrincipalWindow() {
		this.setBounds(200,50,800,500);
		this.setTitle("Petróleos");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);	
		inicializar();
		caracteristicas();
		agregar();
	}
	private void inicializar() {
		management = new Management();
		
		causante = new CrudCausante(management);
		comLimpieza = new CrudComLim(management);
		derramamiento = new CrudDerramamiento(management);
		limpieza = new CrudLimpieza(management);
		localizacion = new CrudLocalizacion(management);
		
		barra = new JTabbedPane();
		barra.setLocation(0, 0);
		barra.setSize(784, 461);
	}
	private void caracteristicas() {
		causante.setBounds(0,0,800,500);
		comLimpieza.setBounds(0,0,800,500);
		derramamiento.setBounds(0,0,800,500);
		limpieza.setBounds(0,0,800,500);
		localizacion.setBounds(0,0,800,500);
	}
	private void agregar() {
		barra.add("Causante", causante);
		barra.add("Localización", localizacion);
		barra.add("Derramamiento",derramamiento);
		barra.add("Compañia Limpieza",comLimpieza);
		barra.add("Limpieza",limpieza);
		getContentPane().add(barra);
	}
}
