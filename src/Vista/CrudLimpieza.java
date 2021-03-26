package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Logic.Management;

public class CrudLimpieza extends JPanel {
	private Management management;
	
	private DefaultTableModel tmLimpieza;
	private JTable tblLimpieza;
	private JScrollPane jsLimpieza;
	
	private JLabel lblId;
	private JLabel lblFecha;
	private JLabel lblInversion;
	
	private JTextField txtId;
	private JDateChooser txtFecha;
	private JTextField txtInversion;

	private JLabel lblDerramamiento;
	private JComboBox<String> jcbDerramamiento;
	
	private JLabel lblComLim;
	private JComboBox<String> jcbComLim;
	
	private URL urlGuardar;
	private ImageIcon imgGuardar;
	private JButton btnGuardar;
	
	private URL urlModify;
	private ImageIcon imgModify;
	private JButton btnModify;

	private URL urlDelete;
	private ImageIcon imgDelete;
	private JButton btnDelete;
	
	private URL urlUpdate;
	private ImageIcon imgUpdate;
	private JButton btnUpdate;
	
	private Border matte;
	
	private URL urlFondo;
	private ImageIcon imgFondo;
	private JLabel lblFondo;
	
	public CrudLimpieza(Management management) {
		this.management = management;
		this.setLayout(null);
		inicializar();
		caracteristicas();
		agregar();
		llenarTabla();
		mostrarListaComLim();
		mostrarListaDerramamiento();
	}
	private void inicializar() {		
		matte  = BorderFactory.createMatteBorder(3,3,3,3, Color.WHITE);
		
		tmLimpieza = new DefaultTableModel();
		tblLimpieza = new JTable(tmLimpieza);
		jsLimpieza = new JScrollPane(tblLimpieza);
		
		lblId = new JLabel("ID:");
		lblFecha = new JLabel("FECHA:");
		lblInversion = new JLabel("INVERSION:");
		
		urlFondo = this.getClass().getResource("/Vista/Source/servicio.jpg");
		imgFondo = new ImageIcon(urlFondo);
		lblFondo = new JLabel(imgFondo);
		txtId = new JTextField();
		txtFecha = new JDateChooser();
		txtInversion = new JTextField();
		
		lblDerramamiento = new JLabel("Derramamiento:");
		jcbDerramamiento = new JComboBox<String>();
		
		lblComLim = new JLabel("Com. Limpieza:");
		jcbComLim = new JComboBox<String>();
		
		txtId = new JTextField();
		
		urlGuardar = this.getClass().getResource("/Vista/Source/guardar.png");
		imgGuardar = new ImageIcon(urlGuardar);
		btnGuardar = new JButton(imgGuardar);		
		
		urlModify = this.getClass().getResource("/Vista/Source/modify.png");
		imgModify = new ImageIcon(urlModify);
		btnModify = new JButton(imgModify);
		
		urlDelete = this.getClass().getResource("/Vista/Source/Delete.png");
		imgDelete = new ImageIcon(urlDelete);
		btnDelete = new JButton(imgDelete);
		
		urlUpdate = this.getClass().getResource("/Vista/Source/update.png");
		imgUpdate = new ImageIcon(urlUpdate);
		btnUpdate = new JButton(imgUpdate);
	}
	private void caracteristicas() {
		lblFondo.setBounds(0,0,800, 500);
	
		txtId.setBounds(205,25, 150,30);
		
		lblId.setBounds(53,25,100,30);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setForeground(Color.WHITE);
		
		lblFecha.setBounds(53,66,100,30);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha.setForeground(Color.WHITE);
		
		lblInversion.setBounds(53,107,100,30);
		lblInversion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInversion.setForeground(Color.WHITE);
		
		txtId.setBounds(205,25,150,30);
		txtFecha.setBounds(205,66,150,30);
		txtInversion.setBounds(205,107,150,30);
		
		lblDerramamiento.setBounds(375,26,112,30);
		lblDerramamiento.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDerramamiento.setForeground(Color.WHITE);
		jcbDerramamiento.setBounds(497,27,150,30);
		jcbDerramamiento.addItem("Derramamiento");
		
		lblComLim.setBounds(375,66,100,30);
		lblComLim.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblComLim.setForeground(Color.WHITE);
		jcbComLim.setBounds(497,68,150,30);
		jcbComLim.addItem("Compañía");
		
		String[] cabecera = {"id","Derramamiento","Com. Limpieza","Fecha","Inversión"};
		tmLimpieza.setColumnIdentifiers(cabecera);
		jsLimpieza.setBounds(125,215,550,200);
		
		btnGuardar.setBorder(null);
		btnGuardar.setContentAreaFilled(false);
		btnGuardar.setBounds(437,134,50,50);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int idLimpieza = Integer.parseInt(txtId.getText());
				int idDerramamiento = jcbDerramamiento.getSelectedIndex();
				int idComLim = jcbComLim.getSelectedIndex();
				Date date = new java.sql.Date(txtFecha.getDate().getTime());
				String inversion = txtInversion.getText();
				boolean verificar = management.addLimpieza(idLimpieza, idDerramamiento, idComLim, date, inversion);
				if (verificar) {
					JOptionPane.showMessageDialog(null, "Se guardó exitosamente", "Guardado Exitoso", 1);
				}else {
					JOptionPane.showMessageDialog(null, "No se ha guardado", "Guardado Erróneo", 1);
				}
				llenarTabla();
			}
		});
		btnGuardar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				btnGuardar.setBorder(matte);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnGuardar.setBorder(null);
			}
		});
		
		btnModify.setBorder(null);
		btnModify.setContentAreaFilled(false);
		btnModify.setBounds(497,134,50,50);
		btnModify.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				btnModify.setBorder(matte);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnModify.setBorder(null);
			}
		});
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int idLimpieza = Integer.parseInt(""+tmLimpieza.getValueAt(tblLimpieza.getSelectedRow(), 0));
				int pos = idLimpieza-1;
				int idDerramamiento = jcbDerramamiento.getSelectedIndex();
				int idComLim = jcbComLim.getSelectedIndex();
				Date fecha = new java.sql.Date(txtFecha.getDate().getTime());
				String inversion = txtInversion.getText();
				boolean verificar = management.modifyLimpieza(pos, idLimpieza, idDerramamiento, idComLim, fecha, inversion);
				if (verificar) {
					JOptionPane.showMessageDialog(null, "Se modificó exitosamente", "Modificación Exitosa", 1);
				}else {
					JOptionPane.showMessageDialog(null, "No se ha modificado", "Modificación Errónea", 1);
				}
				llenarTabla();
			}
		});
		
		btnDelete.setBorder(null);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setBounds(562,134,50,50);
		btnDelete.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				btnDelete.setBorder(matte);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnDelete.setBorder(null);
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int idLimpieza = Integer.parseInt(""+tmLimpieza.getValueAt(tblLimpieza.getSelectedRow(), 0));
				boolean verificar = management.deleteLimpieza(idLimpieza);
				if (verificar) {
					JOptionPane.showMessageDialog(null, "Se eliminó exitosamente", "Eliminación Exitosa", 1);
				}else {
					JOptionPane.showMessageDialog(null, "No se ha eliminado", "Eliminación Errónea", 1);
				}
				llenarTabla();
			}
		});
		
		btnUpdate.setBorder(null);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setBounds(625,134,50,50);
		btnUpdate.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				btnUpdate.setBorder(matte);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnUpdate.setBorder(null);
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTabla();
				mostrarListaDerramamiento();
				mostrarListaComLim();
			}
		});
	}
	private void agregar() {
		add(lblId);
		add(lblFecha);
		add(lblInversion);
		
		add(txtId);
		add(txtFecha);
		add(txtInversion);
		add(jsLimpieza);
		
		add(lblDerramamiento);
		add(jcbDerramamiento);
		
		add(lblComLim);
		add(jcbComLim);

		add(btnGuardar);
		add(btnModify);
		add(btnDelete);
		add(btnUpdate);
		
		add(lblFondo);
	}
	private void limpiarTabla() {
		for (int i = 0; i < tblLimpieza.getRowCount(); i++) {
			tmLimpieza.removeRow(i);
			i--;
		}
	}
	private void llenarTabla() {
		limpiarTabla();
		String[][] datos = management.mostrarLimpiezas();
		for (int i = 0; i < datos.length; i++) {
			tmLimpieza.addRow(datos[i]);
			updateUI();
		}
	}
	protected void mostrarListaComLim() {
		jcbComLim.removeAllItems();
		jcbComLim.addItem("Compañía");
		String[][]datos = management.mostrarComLim();
		for (int i = 0; i < datos.length; i++) {
			jcbComLim.addItem(datos[i][1]);
		}
	}
	protected void mostrarListaDerramamiento() {
		jcbDerramamiento.removeAllItems();
		jcbDerramamiento.addItem("Derramamiento");
		String[][]datos = management.mostrarDerramamiento();
		for (int i = 0; i < datos.length; i++) {
			jcbDerramamiento.addItem(datos[i][1]+", "+datos[i][2]);
		}
	}
}
