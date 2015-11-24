package controller;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Set;

import data.CancionDTO;
import gui.VentanaLogin;
import gui.VentanaReproduccion;
import gui.VentanaTralala;
import remote.RMIServiceLocator;

public class ControllerTralala {
	
	public RMIServiceLocator rsl;
	private int estado = 0;
	
	public ControllerTralala(String[] args) {
		rsl = new RMIServiceLocator();
		new VentanaTralala(this).setVisible(true);
	}
	
	public RMIServiceLocator getRMI() {
		return this.rsl;
	}
	
	public void conectar(String ip, String port) {
		String name = "tralala";
		rsl.setService(ip, port, name);
//		try {
//			rsl.getService().seleccionarTipoPago();
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public Set<CancionDTO> buscar(String artista) {
		try {
			return rsl.getService().buscar(artista);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void login(VentanaTralala vt) {
		ControllerLogin cl = new ControllerLogin(this.rsl);
		VentanaLogin vl = new VentanaLogin(cl, vt);
		vl.setVisible(true);
		vt.setEnabled(false);
	}
	
	public void reproducir(CancionDTO cancionDTO, VentanaTralala vt) throws RemoteException {		
		ControllerReproduccion cr = new ControllerReproduccion(this.rsl);
		VentanaReproduccion vr = new VentanaReproduccion(cr, vt);
		vr.setVisible(true);
		vr.reproducir(cancionDTO);
		vt.setEnabled(false);
	}
	
	public static void main(String [] args) {
		new ControllerTralala(args);
	}
}
