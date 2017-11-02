/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI_Object;

import dto.HocKyDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author bach
 */
public interface InterfaceHocky extends Remote {

    public Vector<HocKyDTO> GetHocKyByTruong(String tentruong) throws RemoteException;
}