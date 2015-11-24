package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ControllerTralala;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import data.CancionDTO;

public class VentanaTralala extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControllerTralala controller;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel dtm;
	private JTextField textFieldIP;
	private JTextField textFieldPuerto;
	private JLabel lblIp;
	private JLabel lblPuerto;
	private JButton btnConectar;
	private JButton btnLogin;
	private JButton btnBuscar;
	private JButton btnReproducir;
	private JButton btnTipoPago;
	private Set<CancionDTO> canciones;
	private String cancion;
	private String username;
	private JLabel lblMiembro;

	/**
	 * Create the frame.
	 */
	public VentanaTralala(final ControllerTralala controller) {
		try {
			   UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		   e.printStackTrace();
		}
		this.controller = controller;
		setTitle("Tralala");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		btnLogin.setBounds(395, 11, 89, 23);
		contentPane.add(btnLogin);
		btnLogin.setEnabled(false);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscar();
				scrollPane.setVisible(true);
				table.setVisible(true);
			}
		});
		btnBuscar.setBounds(183, 69, 89, 23);
		contentPane.add(btnBuscar);
		btnBuscar.setEnabled(false);
		
		lblMiembro = new JLabel("");
		lblMiembro.setBounds(364, 40, 120, 14);
		contentPane.add(lblMiembro);
		
		textField = new JTextField();
		textField.setBounds(30, 70, 143, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setEnabled(false);
		
		btnTipoPago = new JButton("Tipo pago");
		btnTipoPago.setBounds(395, 69, 89, 23);
		contentPane.add(btnTipoPago);
		btnTipoPago.setEnabled(false);
		
		btnReproducir = new JButton("Reproducir");
		btnReproducir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					reproducir(cancion);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnReproducir.setBounds(200, 322, 100, 23);
		contentPane.add(btnReproducir);
		btnReproducir.setEnabled(false);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(68, 123, 357, 188);
		contentPane.add(scrollPane);
		scrollPane.setVisible(false);
		
		table = new JTable();
		table.setBounds(68, 123, 357, 188);
		table.setVisible(false);
		
		scrollPane.setViewportView(table);
		
		lblIp = new JLabel("IP:");
		lblIp.setBounds(30, 15, 20, 14);
		contentPane.add(lblIp);
		
		textFieldIP = new JTextField();
		textFieldIP.setText("127.0.0.1");
		textFieldIP.setBounds(51, 12, 86, 22);
		contentPane.add(textFieldIP);
		textFieldIP.setColumns(10);
		
		lblPuerto = new JLabel("Puerto:");
		lblPuerto.setBounds(147, 15, 42, 14);
		contentPane.add(lblPuerto);
		
		textFieldPuerto = new JTextField();
		textFieldPuerto.setText("1099");
		textFieldPuerto.setBounds(192, 12, 42, 23);
		contentPane.add(textFieldPuerto);
		textFieldPuerto.setColumns(10);
		
		btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.conectar(textFieldIP.getText(), textFieldPuerto.getText());
				lblIp.setVisible(false);
				textFieldIP.setVisible(false);
				lblPuerto.setVisible(false);
				textFieldPuerto.setVisible(false);
				btnConectar.setVisible(false);
				btnLogin.setEnabled(true);
			}
		});
		btnConectar.setBounds(244, 11, 89, 23);
		contentPane.add(btnConectar);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int fila = table.rowAtPoint(e.getPoint());
				if ((fila > -1)) {
					cancion = (String) dtm.getValueAt(fila, 0);
					System.out.println("Cancion = "+cancion);
					btnReproducir.setEnabled(true);
				}
			}
		});
	}
	
	public void buscar() {
		canciones = controller.buscar(textField.getText());
		dtm = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				//All cells can`t be edited
				return false;
			}
		};
		dtm.addColumn("Titulo");
		dtm.addColumn("Duracion");
		dtm.addColumn("Fecha de salida");
		dtm.addColumn("Precio");
		
		for(Iterator<CancionDTO> i = canciones.iterator(); i.hasNext();) {
			CancionDTO cancion = i.next();
			Object[] obj = new Object[4];			
			obj[0] = cancion.getTitulo();
			obj[1] = cancion.getDuracion();
			obj[2] = cancion.getFechaSalida();
			obj[3] = cancion.getPrecRep();
			
			dtm.addRow(obj);
		}
		table.setModel(dtm);
	}
	
	public void login() {
		this.controller.login(this);
	}
	
	public void logueado() {
		btnLogin.setVisible(false);
		lblMiembro.setText("Bienvenido "+username+"!");
		textField.setEnabled(true);
		btnBuscar.setEnabled(true);
		btnTipoPago.setEnabled(true);
	}
	
	public void reproducir(String cancion) throws RemoteException {
		CancionDTO cancionDTO = null;
		for(Iterator<CancionDTO> i = this.canciones.iterator(); i.hasNext();) {
			CancionDTO cDTO = i.next();
			if(cDTO.getTitulo().equals(cancion))
				cancionDTO = cDTO;
		}
		this.controller.reproducir(cancionDTO, this);
	}
	
	public void setUsername(String user) {
		this.username = user;
	}
	
	public String getUsername() {
		return this.username;
	}
}
