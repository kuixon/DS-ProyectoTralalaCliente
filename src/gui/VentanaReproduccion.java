package gui;

import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.ControllerReproduccion;
import javax.swing.JButton;
import javax.swing.JLabel;

import data.CancionDTO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaReproduccion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public ControllerReproduccion controller;
	private VentanaTralala vt;
	private JLabel label;
	
	/**
	 * Create the frame.
	 */
	public VentanaReproduccion(ControllerReproduccion cr, VentanaTralala vt) {
		try {
			   UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		   e.printStackTrace();
		}
		
		this.controller = cr;
		this.vt = vt;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(250, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volver();
			}
		});
		btnVolver.setBounds(75, 125, 89, 23);
		contentPane.add(btnVolver);
		
		label = new JLabel("");
		label.setBounds(10, 47, 224, 36);
		contentPane.add(label);
		
		JLabel lblLetra = new JLabel("Letra:");
		lblLetra.setBounds(10, 18, 55, 16);
		contentPane.add(lblLetra);
	}
	
	public void reproducir(CancionDTO cancionDTO) throws RemoteException {
		label.setText(cancionDTO.getLetra());
		controller.reproducir(vt.getUsername(), cancionDTO);
	}
	
	public void volver() {
		vt.setEnabled(true);
		this.dispose();
	}
}
