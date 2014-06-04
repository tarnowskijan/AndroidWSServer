package edu.agh.wsserver.restlet.services;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;
import org.restlet.routing.Template;

import android.util.Log;
import edu.agh.wsserver.restlet.services.resources.DeviceInformationResource;
import edu.agh.wsserver.restlet.services.resources.FaultResource;
import edu.agh.wsserver.restlet.services.resources.ServiceResource;

public class MainRestServer implements Runnable {
	private static final String LOG_TAG = "MainRestServer";
	
	private String ipAddress;
	private int port;
	private Server server;
	
	public MainRestServer(String ipAddress, int port){
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	@Override
    public void run() {
		Log.i(LOG_TAG+":run()", ipAddress);
		
        Component component = new Component();
        component.getClients().add(Protocol.HTTP);

        final Router router = new Router();
        router.setDefaultMatchingMode(Template.MODE_STARTS_WITH);
        router.setRoutingMode(Router.MODE_FIRST_MATCH);
        /*TODO jaki _sensowny_ path ustawic, zeby sparametryzowac klase?*/
        router.attach("/device", DeviceInformationResource.class);
        router.attach("/service", ServiceResource.class);
        router.attachDefault(FaultResource.class);
        
        Application application = new Application() {
            @Override
            public Restlet createInboundRoot() {
                router.setContext(getContext());
                return router;
            }
        };

        component.getDefaultHost().attach(application);
        try {
        	server = component.getServers().add(Protocol.HTTP, port);
        	server.start();
        } catch (Exception e) {
            Log.e(LOG_TAG, ipAddress, e);
        }
		
    }

	public void stopServer(){
		try {
			if(server != null && server.isStarted()){
				server.stop();
			}
		} catch (Exception e) {
			Log.e(LOG_TAG, ipAddress, e);
		}
	}
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public int getPort(){
		return this.port;
	}
}
