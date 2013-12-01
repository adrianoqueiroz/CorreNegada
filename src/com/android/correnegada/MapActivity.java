package com.android.correnegada;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;


public class MapActivity  extends com.google.android.maps.MapActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
	
		MapView mapa = (MapView)findViewById(R.id.mapa);
		mapa.setBuiltInZoomControls(true);
		mapa.displayZoomControls(true);
		
		Intent it = getIntent();
		int latitude = (int)(it.getDoubleExtra("latitude", 0)*1E6);
		int longitude = (int)(it.getDoubleExtra("longitude", 0)*1E6);
		
		MapController mc = mapa.getController();
		mc.animateTo(new GeoPoint(latitude, longitude));
		mc.setZoom(30);
		mapa.invalidate();
	}
	
	@Override
	protected boolean isRouteDisplayed() {

		
		return false;
	}

	

}
