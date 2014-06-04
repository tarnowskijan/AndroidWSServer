package edu.agh.wsserver.utils;

import android.content.Context;

//FIXME wypierdolic ta klase
public class ApplicationUtils {
	private static final String LOG_TAG = "ApplicationUtils";
	
	ApplicationUtils(){}
	
	private static Context mainActivityContext;
	
	public static Context getMainActivityContext() {
		return mainActivityContext;
	}

	public static void setMainActivityContext(Context context) {
		ApplicationUtils.mainActivityContext = context;
	}
	
}
