package Vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Logic.Management;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.net.URL;
import java.sql.Date;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CrudDerramamiento extends JPanel {
	private Management management;
	private DefaultTableModel tmDerramamiento;
	private JTable tblDerramamiento;
	private JScrollPane jsDerramamiento;
	
	private JLabel lblId;
	private JLabel lblMagnitud;
	private JLabel lblArea;
	private JLabel lblFecha;
	private JLabel lblLocalizacion;
	private JLabel lblCausante;
	
	private URL urlFondo;
	private ImageIcon imgFondo;
	private JLabel lblFondo;
	
	private JTextField txtId;
	private JTextField txtMagnitud;
	private JTextField txtArea;
	private JDateChooser txtFecha;
	
	private JComboBox<String> jcbLocalizacion;
	private JComboBox<String> jcbCausante;

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
	
	public CrudDerramamiento(Management management) {
		this.management = management;
		setLayout(null);
		inicializar();
		caracteristicas();
		agregar();
		llenarTabla();
		mostrarListaCausante();
		mostrarListaLocalizacion();
	}
	private void inicializar() {	
		matte  = BorderFactory.createMatteBorder(3,3,3,3, Color.WHITE);
		
		tmDerramamiento = new DefaultTableModel();
		tblDerramamiento = new JTable(tmDerramamiento);
		jsDerramamiento = new JScrollPane(tblDerramamiento);
		
		txtId = new JTextField();
		
		txtMagnitud = new JTextField();
		
		txtArea = new JTextField();
		
		txtFecha = new JDateChooser();
		
		lblId = new JLabel("ID:");
		
		lblMagnitud = new JLabel("MAGNITUD:");
		
		lblArea = new JLabel("\u00C1REA:");
		
		lblFecha = new JLabel("FECHA:");
		
		lblLocalizacion = new JLabel("LOCALIZACIÓN:");
		jcbLocalizacion = new JComboBox<String>();
		
		lblCausante = new JLabel("CAUSANTE:");
		jcbCausante = new JComboBox<String>();
		
		urlFondo = this.getClass().getResource("/Vista/Source/derrame.jpg");
		imgFondo = new ImageIcon(urlFondo);
		lblFondo = new JLabel(imgFondo);
		
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
		lblId.setForeground(Color.WHITE);
		lblId.setBounds(27,31,100,30);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 15));

		lblArea.setForeground(Color.WHITE);
		lblArea.setBounds(27,113,100,30);
		lblArea.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setBounds(27,154,100,30);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		lblMagnitud.setBounds(27,72,100,30);
		lblMagnitud.setForeground(Color.WHITE);
		lblMagnitud.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		lblFondo.setSize(800, 500);
		
		txtId.setBounds(125,31,150,30);
		
		txtMagnitud.setBounds(125,72,150,30);
		
		txtArea.setBounds(125,113,150,30);
		
		txtFecha.setBounds(125,154,150,30);
		
		lblLocalizacion.setBounds(296,72,100,30);
		lblLocalizacion.setForeground(Color.WHITE);
		lblLocalizacion.setFont(new Font("Tahoma", Font.BOLD, 13));
		jcbLocalizacion.setBounds(400,72,150,30);
		jcbLocalizacion.addItem("Localizacion");
		
		lblCausante.setBounds(296,29,100,30);
		lblCausante.setForeground(Color.WHITE);
		lblCausante.setFont(new Font("Tahoma", Font.BOLD, 15));
		jcbCausante.setBounds(400,31,150,30);
		jcbCausante.addItem("Causante");
		
		btnGuardar.setBorder(null);
		btnGuardar.setContentAreaFilled(false);
		btnGuardar.setBounds(437,134,50,50);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int idDerramamiento = Integer.parseInt(txtId.getText());
				int idCausante = jcbCausante.getSelectedIndex();
				int idLocalizacion = jcbLocalizacion.getSelectedIndex();
				String area = txtArea.getText();
				String magnitud = txtMagnitud.getText();
				Date date = new java.sql.Date(txtFecha.getDate().getTime());
				boolean verificar = management.addDerramamiento(idDerramamiento, idCausante, idLocalizacion, area, date, magnitud);
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
				int idDerramamiento = Integer.parseInt(""+tmDerramamiento.getValueAt(tblDerramamiento.getSelectedRow(), 0));
				int pos = idDerramamiento-1;
				int idCausante = jcbCausante.getSelectedIndex();
				int idLocalizacion = jcbLocalizacion.getSelectedIndex();
				String area = txtArea.getText();
				String magnitud = txtMagnitud.getText();
				Date fecha = new java.sql.Date(txtFecha.getDate().getTime());
				boolean verificar = management.modifyDerramamiento(pos, idDerramamiento, idCausante, idLocalizacion, area, fecha, magnitud);
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
				int idDerramamiento = Integer.parseInt(""+tmDerramamiento.getValueAt(tblDerramamiento.getSelectedRow(), 0));
				boolean verificar = management.deleteDerramamiento(idDerramamiento);
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
			public void actionPerformed(ActionEvent arg0) {
				llenarTabla();
				mostrarListaCausante();
				mostrarListaLocalizacion();
			}
		});
		
		String[] cabecera = {"id","Causante","Localización","Área","Fecha","Magnitud"};
		tmDerramamiento.setColumnIdentifiers(cabecera);
		jsDerramamiento.setBounds(125,215,550,200);
	}
	private void agregar() {
		add(txtId);
		add(txtMagnitud);
		add(txtArea);
		add(txtFecha);
		add(lblId);
		add(lblMagnitud);
		add(lblArea);
		add(lblFecha);
		add(jsDerramamiento);
		
		add(lblCausante);
		add(jcbCausante);
		
		add(lblLocalizacion);
		add(jcbLocalizacion);
		
		add(btnGuardar);
		add(btnModify);
		add(btnDelete);
		add(btnUpdate);
		
		add(lblFondo);
	}
	private void limpiarTabla() {
		for (int i = 0; i < tblDerramamiento.getRowCount(); i++) {
			tmDerramamiento.removeRow(i);
			i--;
		}
	}
	private void llenarTabla() {
		limpiarTabla();
		String[][] datos = management.mostrarDerramamiento();
		for (int i = 0; i < datos.length; i++) {
			tmDerramamiento.addRow(datos[i]);
			updateUI();
		}
	}
	protected void mostrarListaCausante() {
		jcbCausante.removeAllItems();
		jcbCausante.addItem("Causante");
		String[][]datos = management.mostrarCausantes();
		for (int i = 0; i < datos.length; i++) {
			jcbCausante.addItem(datos[i][1]);
		}
	}
	protected void mostrarListaLocalizacion() {
		jcbLocalizacion.removeAllItems();
		jcbLocalizacion.addItem("Localizacion");
		String[][]datos = management.mostrarLocalizaciones();
		for (int i = 0; i < datos.length; i++) {
			jcbLocalizacion.addItem(datos[i][1]);
		}
	}
}
