/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceImplement;

import RMI_Object.InterfaceAdministrator;
import bus.AdministratorBUS;
import dto.AdministratorDTO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
//import

/**
 *
 * @author minhtuan
 */
public class AdministratorImpl extends UnicastRemoteObject implements InterfaceAdministrator {

    public AdministratorImpl() throws RemoteException {
        super();
    }
    //Execute and check login
    public int CheckAdminLogin(String user, String password) throws RemoteException {
        int i = 0;
        try {
            i = AdministratorBUS.CheckAdminLogin(user, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return i;
    }
}
