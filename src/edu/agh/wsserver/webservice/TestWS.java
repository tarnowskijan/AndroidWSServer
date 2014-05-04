package edu.agh.wsserver.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface TestWS {
	@WebMethod String getHelloWorldString(String name);
}
