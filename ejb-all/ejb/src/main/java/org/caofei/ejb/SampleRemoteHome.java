package org.caofei.ejb;
 
import java.rmi.RemoteException;
import javax.ejb.EJBHome;

public interface SampleRemoteHome extends EJBHome
{
	SampleRemote create() throws RemoteException;
}