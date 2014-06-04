package edu.agh.wsserver.utils;

import java.util.List;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DeviceUtils {
	private static final String LOG_TAG = "DeviceUtils";
	
	DeviceUtils(){}
	
	/**
	 * Provides functionality for fetching device sensors info
	 * @return Device sensors info in JSON format or ERROR statement.
	 */
	public static final String getDeviceSensors(){
		Context ctx = ApplicationUtils.getMainActivityContext();
		SensorManager sensorManager = (SensorManager) ctx.getSystemService(ctx.SENSOR_SERVICE);
		
		if(sensorManager == null){
			return "ERROR";
		}
		
		String json = "";
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
			List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
			json = gson.toJson(sensors);
		} catch (Exception e) {
			Log.e(LOG_TAG, "", e);
		}
		return json;
	}

}

	