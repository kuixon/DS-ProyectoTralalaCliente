package controller;

import java.rmi.RemoteException;

import remote.RMIServiceLocator;

public class ControllerLogin {
	
	public RMIServiceLocator rsl;
	
	public ControllerLogin(RMIServiceLocator rsl) {
		this.rsl = rsl;
	}
	
	public boolean iniciarSesion(String username, String pass) {
		System.out.println("Estoy en el ControllerLogin iniciando sesion");
		try {
			System.out.println("Estoy dentro del try en el ControllerLogin iniciando sesion");
			return rsl.getService().iniciarSesion(username, pass);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
