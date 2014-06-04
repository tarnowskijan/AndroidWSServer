package edu.agh.wsserver.restlet.services.providers;

import edu.agh.wsserver.utils.DeviceUtils;
import edu.agh.wsserver.utils.NetworkUtils;

public class DeviceInformationProvider {
	
	public static String sensorInformation(){
		return DeviceUtils.getDeviceSensors();
	}
	
	public static String localIP(){
		return NetworkUtils.getLocalIP();
	}
	
	public static String externalIP(){
		return NetworkUtils.getExternalIP();
	}
}
