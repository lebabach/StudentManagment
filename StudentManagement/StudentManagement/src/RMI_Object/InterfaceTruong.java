/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI_Object;

import dto.TruongDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author bach
 */
public interface InterfaceTruong extends Remote {

    public Vector<TruongDTO> TruongAll() throws RemoteException;
    //Lay tat ca cac truong tu CSDL

    public Vector<TruongDTO> GetAllTruong_() throws RemoteException;
    //Them moi truong

    public int InsertTruong(String name) throws RemoteException;

    //Cap nhat thong tin cho truong
    public int UpdateTruong(int id, String name) throws RemoteException;
    //Xoa truong theo id

    public int DeleteTruongByID(int id) throws RemoteException;

    //Xoa truong theo name
    public int DeleteTruongByName(String name) throws RemoteException;

    //Tim kiem truong theo id
    public Vector<TruongDTO> SearchTruongById(int id) throws RemoteException;

    //Tim kiem truong theo name
    public Vector<TruongDTO> SearchTruongByName(String name) throws RemoteException;
}
