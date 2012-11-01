package org.caofei;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.caofei.ejb.SampleRemote;
import org.caofei.ejb.SampleRemoteHome;

/**
 * Hello world!
 *
 */
public class AppEJB 
{
    public static void main( String[] args ) throws RemoteException, NamingException
    {
        System.out.println(exec());
    }

	public static String exec() throws RemoteException, NamingException{
	    String jndiName = "ejb:/ejb-1.0.0/SampleEJB!org.caofei.ejb.SampleRemoteHome";
	    
		Properties jndiProps = new Properties();
		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jboss.naming.remote.client.InitialContextFactory");
		jndiProps.put(Context.PROVIDER_URL, "remote://127.0.0.1:4447");
		jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		// username
		jndiProps.put(Context.SECURITY_PRINCIPAL, "app");
		// password
		jndiProps.put(Context.SECURITY_CREDENTIALS, "(Nttdata)");
		// This is an important property to set if you want to do EJB
		// invocations via the remote-naming project
		jndiProps.put("jboss.naming.client.ejb.context", true);

	    // ホームオブジェクト取得
	    InitialContext context = new InitialContext(jndiProps);
	    Object obj = context.lookup(jndiName);
	
	    // ホームオブジェクトが、引数で指定された型にキャスト可能であることを確認
	    SampleRemoteHome home = (SampleRemoteHome)PortableRemoteObject.narrow(obj,SampleRemoteHome.class);
	
	    SampleRemote r = home.create();
		return r.sayHai();
	}
}
