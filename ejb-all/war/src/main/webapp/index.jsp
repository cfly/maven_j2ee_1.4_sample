<%@page import="javax.rmi.PortableRemoteObject"%>
<%@page import="org.caofei.ejb.SampleRemote"%>
<%@page import="org.caofei.ejb.SampleRemoteHome"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.util.Properties"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!_</h1>
        <h1><%=new java.util.Date() %></h1>
        
        <%
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
		//ctx = new InitialContext();
		ctx = new InitialContext(jndiProps);

		
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
        %>
    </body>
</html>
