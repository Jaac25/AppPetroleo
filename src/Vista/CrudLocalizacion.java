package Vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import Logic.Management;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class CrudLocalizacion extends JPanel {
	private Management management;
	
	private DefaultTableModel tmLocalizacion;
	private JTable tblLocalizacion;
	private JScrollPane jsLocalizacion;
	
	private URL urlFondo;
	private ImageIcon imgFondo;
	
	private JLabel lblId;
	private JLabel fondo;
	private JLabel lblPais;
	private JLabel lblLatitud;
	private JLabel lblLongitud;
	
	private JTextField txtId;
	private JTextField txtPais;
	private JTextField txtLatitud;
	private JTextField txtLongitud;

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
	
	public CrudLocalizacion(Management management) {
		this.management = management;
		this.setLayout(null);
		inicializar();
		caracteristicas();
		agregar();
		llenarTabla();
	}
	private void inicializar() {		
		matte  = BorderFactory.createMatteBorder(3,3,3,3, Color.WHITE);
		
		tmLocalizacion = new DefaultTableModel();
		tblLocalizacion = new JTable(tmLocalizacion);
		jsLocalizacion = new JScrollPane(tblLocalizacion);
		
		urlFondo = this.getClass().getResource("/Vista/Source/localizacionmundial.jpg");
		imgFondo = new ImageIcon(urlFondo);
		fondo = new JLabel(imgFondo);
				
		txtId = new JTextField();
		txtPais = new JTextField();
		txtLatitud = new JTextField();
		txtLongitud = new JTextField();
		lblId = new JLabel("ID:");
		lblPais = new JLabel("PAIS:");
		lblLatitud = new JLabel("LATITUD:");
		lblLongitud = new JLabel("LONGITUD:");
		
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
		txtId.setColumns(10);
		txtId.setBounds(159,41,150,30);
			
		fondo.setLocation(0, 0);
		fondo.setSize(800, 500);
		
		txtPais.setColumns(10);
		txtPais.setBounds(159,82,150,30);
		
		tblLocalizacion.setBounds(125,260,550,200);
		
		txtLatitud.setColumns(10);
		txtLatitud.setBounds(159,123,150,30);
		
		txtLongitud.setColumns(10);
		txtLongitud.setBounds(159,164,150,30);
		
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setBounds(25,41,100,30);
		
		lblPais.setForeground(Color.WHITE);
		lblPais.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPais.setBounds(25,82,100,30);
		
		lblLatitud.setForeground(Color.WHITE);
		lblLatitud.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLatitud.setBounds(25,123,100,30);
		
		lblLongitud.setForeground(Color.WHITE);
		lblLongitud.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLongitud.setBounds(25,164,100,30);
		
		String[]cabecera = {"Id","País","Latitud","Longitud"};
		tmLocalizacion.setColumnIdentifiers(cabecera);
		jsLocalizacion.setBounds(125,215,550,200);
		
		btnGuardar.setBorder(null);
		btnGuardar.setContentAreaFilled(false);
		btnGuardar.setBounds(440,28,50,50);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int idLocalizacion = Integer.parseInt(txtId.getText());
				String nombre = txtPais.getText();
				String latitud = txtLatitud.getText();
				String longitud = txtLongitud.getText();
				boolean verificar = management.addLocalizacion(idLocalizacion, nombre, latitud, longitud);
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
		btnModify.setBounds(543,28,50,50);
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
				int idLocalizacion = Integer.parseInt(""+tmLocalizacion.getValueAt(tblLocalizacion.getSelectedRow(), 0));
				int pos = idLocalizacion-1;
				String nombre = txtPais.getText();
				String latitud = txtLatitud.getText();
				String longitud = txtLongitud.getText();
				boolean verificar = management.modifyLocalizacion(pos, idLocalizacion, nombre, latitud, longitud);
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
		btnDelete.setBounds(440,110,50,50);
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
				int idLocalizacion = Integer.parseInt(""+tmLocalizacion.getValueAt(tblLocalizacion.getSelectedRow(), 0));
				boolean verificar = management.deleteLocalizacion(idLocalizacion);
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
		btnUpdate.setBounds(543,110,50,50);
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
			public void actionPerformed(ActionEvent arg0) {
				llenarTabla();
			}
		});	
	}
	private void agregar() {
		add(jsLocalizacion);
		add(txtId);
		add(txtPais);
		add(txtLatitud);
		add(txtLongitud);
		add(lblId);
		add(lblPais);
		add(lblLatitud);
		add(lblLongitud);
		
		add(btnGuardar);
		add(btnModify);
		add(btnDelete);
		add(btnUpdate);
		
		add(fondo);
	}
	private void limpiarTabla() {
		for (int i = 0; i < tblLocalizacion.getRowCount(); i++) {
			tmLocalizacion.removeRow(i);
			i--;
		}
	}
	private void llenarTabla() {
		limpiarTabla();
		String[][] datos = management.mostrarLocalizaciones();
		for (int i = 0; i < datos.length; i++) {
			tmLocalizacion.addRow(datos[i]);
			updateUI();
		}
	}
}
