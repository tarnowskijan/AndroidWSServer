package edu.agh.wsserver.webservice;

import jdk.javax.jws.WebMethod;
import jdk.javax.jws.WebService;

@WebService
public interface TestWS {
	@WebMethod String getHelloWorldString(String name);
}