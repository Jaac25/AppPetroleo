package Vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import Logic.Management;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CrudComLim extends JPanel {
	private Management management;
	
	private DefaultTableModel tmComLim;
	private JTable tblDerramamiento;
	private JScrollPane jsDerramamiento;
	
	private URL urlFondo;
	private ImageIcon imgFondo;
	private JLabel lblFondo;
	
	private JLabel lblTelefono;
	private JLabel lblDireccion;
	private JLabel lblId;
	private JLabel lblNombre;

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
	
	public CrudComLim(Management management) {
		this.management = management;
		setLayout(null);
		inicializar();
		caracteristicas();
		agregar();
		llenarTabla();
	}
	private void inicializar() {		
		matte  = BorderFactory.createMatteBorder(3,3,3,3, Color.WHITE);
		
		tmComLim = new DefaultTableModel();
		tblDerramamiento = new JTable(tmComLim);
		jsDerramamiento = new JScrollPane(tblDerramamiento);
		
		urlFondo = this.getClass().getResource("/Vista/Source/img2petroleo.jpg");
		imgFondo = new ImageIcon(urlFondo);
		lblFondo = new JLabel(imgFondo);
		lblFondo.setSize(800, 500);
		
		txtId = new JTextField();
		lblTelefono = new JLabel("TELEFONO:");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTelefono.setForeground(Color.WHITE);
		
		lblDireccion = new JLabel("DIRECCION:");
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDireccion.setForeground(Color.WHITE);
		
		lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setForeground(Color.WHITE);
		
		lblNombre = new JLabel("NOMBRE:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombre.setForeground(Color.WHITE);
		
		txtNombre = new JTextField();
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
		lblTelefono.setBounds(10,156,100,30);
		lblDireccion.setBounds(10,115,100,30);
		lblId.setBounds(10,33,100,30);
		lblNombre.setBounds(10,74,100,30);

		txtId.setBounds(156,33,150,30);
		
		txtNombre.setBounds(156,74,150,30);
		
		txtDireccion.setBounds(156,115,150,30);
		
		txtTelefono.setBounds(156,156,150,30);
		
		String[] cabecera = {"id","Nombre","Direccion","Teléfono"};
		tmComLim.setColumnIdentifiers(cabecera);
		jsDerramamiento.setBounds(125,215,550,200);
		
		btnGuardar.setBorder(null);
		btnGuardar.setContentAreaFilled(false);
		btnGuardar.setBounds(440,28,50,50);
		btnGuardar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				btnGuardar.setBorder(matte);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnGuardar.setBorder(null);
			}
		});
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int idCausante = Integer.parseInt(txtId.getText());
				String nombre = txtNombre.getText();
				String telefono = txtTelefono.getText();
				String direccion = txtDireccion.getText();
				boolean verificar = management.addComLim(idCausante, nombre, direccion, telefono);
				if (verificar) {
					JOptionPane.showMessageDialog(null, "Se guardó exitosamente", "Guardado Exitoso", 1);
				}else {
					JOptionPane.showMessageDialog(null, "No se ha guardado", "Guardado Erróneo", 1);
				}
				llenarTabla();
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
				int idComLim = Integer.parseInt(""+tmComLim.getValueAt(tblDerramamiento.getSelectedRow(), 0));
				int pos = idComLim-1;
				String nombre = txtNombre.getText();
				String telefono = txtTelefono.getText();
				String direccion = txtDireccion.getText();
				boolean verificar = management.modifyComLim(pos, idComLim, nombre, telefono, direccion);
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
				int idComLim = Integer.parseInt(""+tmComLim.getValueAt(tblDerramamiento.getSelectedRow(), 0));
				boolean verificar = management.deleteComLim(idComLim);
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
		add(lblTelefono);
		add(lblDireccion);
		add(lblId);
		add(lblNombre);
		add(txtId);
		add(txtNombre);
		add(txtDireccion);
		add(txtTelefono);
		add(jsDerramamiento);
		
		add(btnGuardar);
		add(btnModify);
		add(btnDelete);
		add(btnUpdate);
		
		add(lblFondo);
	}
	private void limpiarTabla() {
		for (int i = 0; i < tblDerramamiento.getRowCount(); i++) {
			tmComLim.removeRow(i);
			i--;
		}
	}
	private void llenarTabla() {
		limpiarTabla();
		String[][] datos = management.mostrarComLim();
		for (int i = 0; i < datos.length; i++) {
			tmComLim.addRow(datos[i]);
			updateUI();
		}
	}
}
