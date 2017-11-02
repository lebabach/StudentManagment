package RMI_Object;

import dto.*;
import java.rmi.*;
import java.util.Vector;

/**
 *
 * @author dell
 */
public interface InterfaceAdministrator extends Remote {

    public int CheckAdminLogin(String user, String password) throws RemoteException;

   
}
