package controller;

import java.rmi.RemoteException;

import data.CancionDTO;
import remote.RMIServiceLocator;

public class ControllerReproduccion {

	public RMIServiceLocator rsl;
	
	public ControllerReproduccion(RMIServiceLocator rsl) {
		this.rsl = rsl;
	}
	
	public void reproducir(String username, CancionDTO cancionDTO) throws RemoteException {
		rsl.getService().reproducir(username, cancionDTO);
	}
}
