package edu.agh.wsserver.gui;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import edu.agh.wsserver.R;
import edu.agh.wsserver.restlet.services.MainRestServer;
import edu.agh.wsserver.server.WSServer;
import edu.agh.wsserver.utils.NetworkUtils;

public class GuiServerFragment extends Fragment {
	public GuiServerFragment() {
	}

	private WSServer wsServer = new WSServer();
	private MainRestServer server;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.fragment_server,
				container, false);
		// Set up Start Server Buttons
		final Button startServerButton = (Button) rootView
				.findViewById(R.id.startServerButton);
		final Button stopServerButton = (Button) rootView
				.findViewById(R.id.stopServer);
		
		stopServerButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startServerButton.setEnabled(true);
				stopServerButton.setEnabled(false);
				wsServer.stop();
				server.stopServer();
			}
		});

		startServerButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stopServerButton.setEnabled(true);
				startServerButton.setEnabled(false);
				new GetLocalIpTask((TextView) rootView
						.findViewById(R.id.ipTextView)).execute();

				try {
					wsServer.start();
				} catch (IOException e) {
					Log.e(this.getClass().getSimpleName(), e.toString());
				}

				String ipAddress = ((TextView) rootView
						.findViewById(R.id.ipTextView)).getText().toString();

				server = new MainRestServer(ipAddress, 8182);
				new Thread(server).start();
			}
		});
		
		if(server != null){
			stopServerButton.setEnabled(true);
			startServerButton.setEnabled(false);
			new GetLocalIpTask((TextView) rootView
					.findViewById(R.id.ipTextView)).execute();
		} else {
			stopServerButton.setEnabled(false);
		}
		return rootView;
	}

	/**
	 * Przy _ukrywaniu_
	 */
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}
	
	/**
	 * Przy usuwaniu ekranu - to kurestwo nie jest wywolywane 
	 * przy wywolaniu detach() fragment managera
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (wsServer != null) {
			wsServer.stop();
		}
		if (server != null) {
			server.stopServer();
		}
	}

	static void setFinalStatic(Field field, Object newValue) throws Exception {
		field.setAccessible(true);

		Field modifiersField = Field.class.getDeclaredField("modifiers");
		modifiersField.setAccessible(true);
		modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

		field.set(null, newValue);
	}

	public static class GetLocalIpTask extends AsyncTask<Void, Void, String> {
		private TextView textView;

		public GetLocalIpTask(TextView textViewToUpdate) {
			this.textView = textViewToUpdate;
		}

		@Override
		protected String doInBackground(Void... params) {
			return NetworkUtils.getLocalIP() + ":" + 8182;
		}

		@Override
		protected void onPostExecute(String result) {
			textView.setText(result);
		}
	}
}
