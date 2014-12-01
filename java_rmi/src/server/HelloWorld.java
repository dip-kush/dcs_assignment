package server;



import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 */
public interface HelloWorld extends Remote {

    String getRecord(String s) throws RemoteException;
    String insertRecord(String fn,String ln, String phn, String email, String city, String state) throws RemoteException;
    String deleteRecord(String phn) throws RemoteException;
}

