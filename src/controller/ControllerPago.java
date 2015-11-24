package controller;

import remote.RMIServiceLocator;

public class ControllerPago {

	public RMIServiceLocator rsl;
	
	public ControllerPago(RMIServiceLocator rsl) {
		this.rsl = rsl;
	}
}
