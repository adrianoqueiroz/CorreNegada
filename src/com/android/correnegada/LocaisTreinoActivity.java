package com.android.correnegada;

import android.app.Dialog;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocaisTreinoActivity extends FragmentActivity {

	GoogleMap googleMap;

	Marker marker = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locais_treino);

		setUpMapIfNeeded();

	}

	private void setUpMapIfNeeded() {
		// TODO Auto-generated method stub
		if (googleMap == null) {
			googleMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			if (googleMap != null) {
				setUpMap();
			}
		}
	}

	private void setUpMap() {
		// Enable MyLocation Layer of Google Map
		googleMap.setMyLocationEnabled(true);

		// Get LocationManager object from System Service LOCATION_SERVICE
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		// Create a criteria object to retrive provider
		Criteria criteria = new Criteria();

		// Get the name of the best provider
		String provider = locationManager.getBestProvider(criteria, true);

		// Get Current Location
		Location myLocation = locationManager.getLastKnownLocation(provider);

		// set map type
		googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

		// Get latitude of the current location
		double latitude = myLocation.getLatitude();

		// Get longitude of the current location
		double longitude = myLocation.getLongitude();

		// Create a LatLng object for the current location
		LatLng latLng = new LatLng(latitude, longitude);

		// show the current location in Google Map
		googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

		// zoom in the google map
		googleMap.animateCamera(CameraUpdateFactory.zoomTo(19));
		googleMap.addMarker(new MarkerOptions().position(
				new LatLng(latitude, longitude)).title("Você está aqui!"));
		

		
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
