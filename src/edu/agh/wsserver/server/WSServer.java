package edu.agh.wsserver.server;

import java.util.Map;

import android.util.Log;
import fi.iki.elonen.NanoHTTPD;

public class WSServer extends NanoHTTPD {
	
	public WSServer(int port) {
		super(port);
	}

	public WSServer() {
		this(8080);
	}

	@Override
	public Response serve(IHTTPSession session) {
		Method method = session.getMethod();
		String uri = session.getUri();
		Log.d(this.getClass().getSimpleName(), method + " '" + uri + "' ");

		String msg = "<html><body><h1>Hello server</h1>\n";
		Map<String, String> parms = session.getParms();
		if (parms.get("username") == null) {
			msg += "<form action='?' method='get'>\n" + "  <p>Your name: <input type='text' name='username'></p>\n" + "</form>\n";
		}
		else {
			msg += "<p>Hello, " + parms.get("username") + "!</p>";
		}

		msg += "</body></html>\n";

		return new NanoHTTPD.Response(msg);
	}
}
