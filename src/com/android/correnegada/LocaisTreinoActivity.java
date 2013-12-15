package com.android.correnegada;

	import android.app.Dialog;
	import android.os.Bundle;
	import android.support.v4.app.FragmentActivity;

	import com.google.android.gms.common.ConnectionResult;
	import com.google.android.gms.common.GooglePlayServicesUtil;
	import com.google.android.gms.maps.GoogleMap;
	import com.google.android.gms.maps.SupportMapFragment;
	import com.google.android.gms.maps.model.Marker;

	public class LocaisTreinoActivity extends FragmentActivity {

		GoogleMap googleMap;

		Marker marker = null;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_locais_treino);

			// Getting Google Play availability status
			int status = GooglePlayServicesUtil
					.isGooglePlayServicesAvailable(getBaseContext());

			// Showing status
			if (status != ConnectionResult.SUCCESS) { // Google Play Services are
														// not available

				int requestCode = 10;
				Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this,
						requestCode);
				dialog.show();

			} else { // Google Play Services are available

				// Getting reference to the SupportMapFragment of activity_main.xml
				SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager()
						.findFragmentById(R.id.map);

				// Getting GoogleMap object from the fragment
				googleMap = fm.getMap();

			}

		}

	}
