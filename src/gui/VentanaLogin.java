package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerLogin;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public ControllerLogin controller;
	private VentanaTralala vt;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public VentanaLogin(ControllerLogin cl, VentanaTralala vt) {
		try {
			   UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		   e.printStackTrace();
		}
		this.controller = cl;
		this.vt = vt;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(250, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(78, 11, 86, 14);
		contentPane.add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(78, 27, 86, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(78, 69, 86, 14);
		contentPane.add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setBounds(78, 86, 86, 23);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				entrar();
			}
		});
		btnEntrar.setBounds(78, 137, 89, 23);
		contentPane.add(btnEntrar);
	}
	
	public void entrar() {
		vt.setEnabled(true);
		if(this.controller.iniciarSesion(textField.getText(), textField_1.getText())) {
			vt.setUsername(textField.getText());
			vt.logueado();
		}
		this.dispose();
	}
}
