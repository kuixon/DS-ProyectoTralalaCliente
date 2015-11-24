package remote;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.HashMap;

public class RMIServiceLocator {
	private ITralalaFacade service;
	private String serviceKey;

    /** Creates a new instance of RMIServiceLocator */
    public RMIServiceLocator() {
    }

    public void setService(String ip, String port, String name) {    
    	// Add your code to get the TARGET reference HERE
    	if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
    	
    	serviceKey = "//" + ip + ":" + port + "/" + name;
    	try {
    		service = (ITralalaFacade) java.rmi.Naming.lookup(serviceKey);
    		System.out.println("- Switching to server: " + ip + ":" + port + "/" + name);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public ITralalaFacade getService() {
    	System.out.println("Cogo el servicio para conectarme a Tralala");
    	return service;
    }
}
