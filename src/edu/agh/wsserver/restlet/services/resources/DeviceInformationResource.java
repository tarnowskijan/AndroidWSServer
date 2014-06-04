package edu.agh.wsserver.restlet.services.resources;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;

import edu.agh.wsserver.restlet.services.providers.DeviceInformationProvider;

public class DeviceInformationResource extends BasicResource {

	@Override
    @Get
    public Representation basicGetMethod() {
        return invoke(DeviceInformationProvider.class);
    }
  
}
