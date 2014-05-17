package edu.agh.wsserver.webservice;

import jdk.javax.jws.WebService;

@WebService(endpointInterface = "edu.agh.wsserver.webservice.TestWS")
public class TestWSImpl implements TestWS {

	@Override
	public String getHelloWorldString(String name) {
		return "Hello " + name;
	}

}
