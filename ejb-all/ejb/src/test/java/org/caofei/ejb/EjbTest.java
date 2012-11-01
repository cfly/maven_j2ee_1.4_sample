package org.caofei.ejb;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

public class EjbTest {

	public static void main(String[] args) throws NamingException,
			RemoteException {
		// Properties props = new Properties();
		// props.setProperty("java.naming.factory.initial",
		// "org.jnp.interfaces.NamingContextFactory");
		// props.setProperty("java.naming.provider.url", "localhost:1099");
		// props.setProperty("java.naming.factory.url.pkgs",
		// "org.jboss.naming");
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
		// create a context passing these properties
		Context ctx;
		ctx = new InitialContext();
		// InitialContext ctx = new InitialContext(props);

		
//		context.lookup("ejb:" + appName + "/" + moduleName + "/"
//				+ distinctName + "/" + beanName + "!"
//				+ viewClassName);
		String name = null;
		//single ejb jar deploy
		name ="ejb:/ejb-1.0.0/SampleEJB!org.caofei.ejb.SampleRemoteHome";
		//ear deploy
		name ="ejb:ear-1.0.0/ejb-1.0.0/SampleEJB!org.caofei.ejb.SampleRemoteHome";
		
		Object objRef = ctx
				.lookup(name);

		SampleRemoteHome home = (SampleRemoteHome) PortableRemoteObject.narrow(
				objRef, SampleRemoteHome.class);

		SampleRemote remote = home.create();

		System.out.println(remote.sayHai());
	}

}
