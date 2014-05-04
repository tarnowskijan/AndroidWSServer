package edu.agh.wsserver.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.util.Log;

public class NetworkUtils {
	private static final String LOG_TAG = "Utils";

	/**
	 * Code adapted from http://stackoverflow.com/a/12854981
	 * @return Device external IP address or ERROR statement.
	 */
	public static String getExternalIP() {
		try {
			HttpClient httpclient = new DefaultHttpClient();
	        HttpGet httpget = new HttpGet("http://ip2country.sourceforge.net/ip2c.php?format=JSON");
	        // HttpGet httpget = new HttpGet("http://whatismyip.com.au/");
	        // HttpGet httpget = new HttpGet("http://www.whatismyip.org/");
	        HttpResponse response = httpclient.execute(httpget);
	        HttpEntity entity = response.getEntity();
	        if (entity != null && entity.getContentLength() > 0) {
	        	JSONObject json_data = new JSONObject(EntityUtils.toString(entity));
	        	return json_data.getString("ip");
	        } else {
	        	Log.e(LOG_TAG, "Response error: " + response.getStatusLine().toString());
	        }
		} catch (Exception e) {
			Log.e(LOG_TAG, e.toString());
		}
		return "ERROR";
	}
}