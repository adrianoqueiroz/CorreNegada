package com.android.correnegada;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DetalhesMetaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalhes_meta);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalhes_meta, menu);
		return true;
	}

}
