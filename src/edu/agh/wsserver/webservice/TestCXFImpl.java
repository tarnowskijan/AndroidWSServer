package edu.agh.wsserver.webservice;

import jdk.javax.jws.WebService;

@WebService(endpointInterface = "edu.agh.wsserver.webservice.TestCXF",
serviceName = "TestCXF")
public class TestCXFImpl {
	 
	    public String sayHi(String text) {
	        System.out.println("sayHi called");
	        return "Hello " + text;
	    }
	 
	    public String sayHiToUser(String user) {
	        System.out.println("sayHiToUser called");
	        return "aaa";
	    }
	 
	}