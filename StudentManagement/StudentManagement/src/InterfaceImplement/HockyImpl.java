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
 * @author bach
 */
public class HockyImpl extends UnicastRemoteObject implements InterfaceHocky {

    public HockyImpl() throws RemoteException {
        super();
    }
    public Vector<HocKyDTO> GetHocKyByTruong(String tentruong) throws RemoteException {
        return HockyBUS.GetHocKyByTruong(tentruong);
    }
}
