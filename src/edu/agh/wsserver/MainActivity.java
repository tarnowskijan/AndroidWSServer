package edu.agh.wsserver;

import java.io.IOException;

import javax.xml.ws.Endpoint;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import edu.agh.wsserver.server.WSServer;
import edu.agh.wsserver.utils.NetworkUtils;
import edu.agh.wsserver.webservice.TestWSImpl;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private WSServer wsServer = new WSServer();

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			// Set up Start Server Buttons
			final Button startServerButton = (Button) rootView.findViewById(R.id.startServerButton);
			final Button stopServerButton = (Button) rootView.findViewById(R.id.stopServer);
			stopServerButton.setEnabled(false);
			stopServerButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					startServerButton.setEnabled(true);
					stopServerButton.setEnabled(false);
					wsServer.stop();
				}
			});
			startServerButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					stopServerButton.setEnabled(true);
					startServerButton.setEnabled(false);
					new GetExternalIPTask((TextView) rootView.findViewById(R.id.ipTextView)).execute();
					Endpoint.publish("http://localhost:8081/hello/", new TestWSImpl());
					try {
						wsServer.start();
					} catch (IOException e) {
						Log.e(this.getClass().getSimpleName(), e.toString());
					}
				}
			});

			return rootView;
		}

		@Override
		public void onDestroy() {
			super.onDestroy();
			if (wsServer != null) {
				wsServer.stop();
			}
		}
	}

	public static class GetExternalIPTask extends AsyncTask<Void, Void, String> {
		private TextView textView;

		public GetExternalIPTask(TextView textViewToUpdate) {
			this.textView = textViewToUpdate;
		}

		@Override
		protected String doInBackground(Void... params) {
			return NetworkUtils.getExternalIP();
		}

		@Override
		protected void onPostExecute(String result) {
			textView.setText(result);
		}
	}
}
