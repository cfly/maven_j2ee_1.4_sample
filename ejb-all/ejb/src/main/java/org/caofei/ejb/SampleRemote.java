package org.caofei.ejb;
import java.rmi.RemoteException;
import javax.ejb.EJBObject;

public interface SampleRemote extends EJBObject
{
    String sayHai() throws RemoteException;
}