package edu.agh.wsserver.restlet.services.resources;

import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;

public class FaultResource extends BasicResource {

	@Override
    @Get
	public Representation basicGetMethod() {
		return new StringRepresentation("There is no resource for your request. Try one of the following: "
				+ getReference().getBaseRef().toString() + "/service?someMethod or " 
				+ getReference().getBaseRef().toString() + "/device?someMethod");
	}
		
}
