package Vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.net.URL;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import Logic.Management;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CrudCausante extends JPanel {
	private Management management;
	
	private DefaultTableModel tmCausante;
	private JTable tblCausante;
	private JScrollPane jsCausante;
	
	private URL urlFondo;
	private ImageIcon imgFondo;
	
	private JLabel lblDireccion;
	private JLabel lblTelefono;
	private JLabel lblNombre;
	private JLabel fondo;
	private JLabel lblId;
	
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;

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
	
	public CrudCausante(Management management) {
		this.management = management;
		this.setLayout(null);
		inicializar();
		caracteristicas();
		agregar();
		llenarTabla();
	}
	private void inicializar() {		
		matte  = BorderFactory.createMatteBorder(3,3,3,3, Color.WHITE);
		
		tmCausante = new DefaultTableModel();
		tblCausante = new JTable(tmCausante);
		jsCausante = new JScrollPane(tblCausante);
		
		lblDireccion = new JLabel("DIRECCION:");
		
		urlFondo = this.getClass().getResource("/Vista/Source/imagen.jpg");
		imgFondo = new ImageIcon(urlFondo);
		fondo = new JLabel(imgFondo);
		
		txtId = new JTextField();
		txtNombre = new JTextField();
		lblTelefono = new JLabel("TELEFONO:");
		lblNombre = new JLabel("NOMBRE:");
		lblId = new JLabel("ID:");

		txtDireccion = new JTextField();
		txtTelefono = new JTextField();
		
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
		lblDireccion.setBounds(22,110,100,30);
		lblDireccion.setForeground(Color.WHITE);
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblTelefono.setBounds(22,151,100,30);
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblNombre.setBounds(22,69,100,30);
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		String[] cabecera = {"id","Nombre","Teléfono","Direccion"};
		tmCausante.setColumnIdentifiers(cabecera);
		jsCausante.setBounds(124,215,550,200);
		
		fondo.setBounds(0,0,800,500);
		
		lblId.setBounds(22,28,100,30);
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		txtId.setBounds(144,28,150,30);
		
		txtNombre.setBounds(144,69,150,30);
		
		txtDireccion.setBounds(144,110,150,30);
		
		txtTelefono.setBounds(144,154,150,30);
		
		btnGuardar.setBorder(null);
		btnGuardar.setContentAreaFilled(false);
		btnGuardar.setBounds(440,28,50,50);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int idCausante = Integer.parseInt(txtId.getText());
				String nombre = txtNombre.getText();
				String telefono = txtTelefono.getText();
				String direccion = txtDireccion.getText();
				boolean verificar = management.addCausante(idCausante, nombre, telefono, direccion);
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
				int idCausante = Integer.parseInt(""+tmCausante.getValueAt(tblCausante.getSelectedRow(), 0));
				int pos = idCausante-1;
				String nombre = txtNombre.getText();
				String telefono = txtTelefono.getText();
				String direccion = txtDireccion.getText();
				boolean verificar = management.modifyCausante(pos, idCausante, nombre, telefono, direccion);
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
				int idCausante = Integer.parseInt(""+tmCausante.getValueAt(tblCausante.getSelectedRow(), 0));
				boolean verificar = management.deleteCausante(idCausante);
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
		add(lblDireccion);
		add(txtId);
		add(txtNombre);
		add(lblTelefono);
		add(lblNombre);
		add(txtDireccion);
		add(txtTelefono);
		add(lblId);
		add(jsCausante);
		
		add(btnGuardar);
		add(btnModify);
		add(btnDelete);
		add(btnUpdate);
		
		add(fondo);
	}
	private void limpiarTabla() {
		for (int i = 0; i < tblCausante.getRowCount(); i++) {
			tmCausante.removeRow(i);
			i--;
		}
	}
	private void llenarTabla() {
		limpiarTabla();
		String[][] datos = management.mostrarCausantes();
		for (int i = 0; i < datos.length; i++) {
			tmCausante.addRow(datos[i]);
			updateUI();
		}
	}
}
