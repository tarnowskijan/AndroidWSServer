package edu.agh.wsserver.webservice;

import jdk.javax.jws.WebService;




@WebService
public interface TestCXF {
	
	 
	    String sayHi(String text);
	 
	 
	    /* Advanced usecase of passing an Interface in.  JAX-WS/JAXB does not
	     * support interfaces directly.  Special XmlAdapter classes need to
	     * be written to handle them
	     */
	    String sayHiToUser(String user);
	 
	}