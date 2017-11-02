/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceImplement;

import RMI_Object.*;
import bus.*;
import dto.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class KhoiImpl extends UnicastRemoteObject implements InterfaceHocky {

    public KhoiImpl() throws RemoteException {
        super();
    }

    public Vector<HocKyDTO> GetHocKyByTruong(String tentruong) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
